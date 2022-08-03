package ht.mikewrig.seleniumquery.internal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import ht.mikewrig.seleniumquery.SeleniumQueryConfig;
import ht.mikewrig.seleniumquery.SeleniumQueryObject;
import ht.mikewrig.seleniumquery.fluentfunctions.waituntil.SeleniumQueryTimeoutException;
import ht.mikewrig.seleniumquery.internal.fluentfunctions.FluentBehaviorModifier;
import ht.mikewrig.seleniumquery.internal.fluentfunctions.evaluators.Evaluator;

public class InternalExceptionFactory {

    public static <EVALUATORARG, GETTERTYPE> SeleniumQueryTimeoutException newTimeoutException(TimeoutException sourceException,
                                                                                               SeleniumQueryObject seleniumQueryObject,
                                                                                               EVALUATORARG value,
                                                                                               FluentBehaviorModifier fluentBehaviorModifier,
                                                                                               Evaluator<EVALUATORARG, GETTERTYPE> evaluator,
                                                                                               GETTERTYPE lastValue) {
        String message = String.format(
            "Timeout while waiting for %s.waitUntil().%s.\n\n%s",
            seleniumQueryObject,
            evaluator.describeEvaluatorFunction(value, fluentBehaviorModifier),
            evaluator.expectedVsActualMessage(fluentBehaviorModifier, value, lastValue, "last ")
        );
        SeleniumQueryTimeoutException ex = new SeleniumQueryTimeoutException(message, sourceException);

        try {
            saveErrorPage(seleniumQueryObject.getWebDriver());
            saveErrorScreenshot(seleniumQueryObject.getWebDriver());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ex;
    }

    private static void saveErrorScreenshot(WebDriver webDriver) throws IOException {
        if (webDriver instanceof TakesScreenshot) {
            File srcFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(srcFile, new File(SeleniumQueryConfig.get("ERROR_PAGE_SCREENSHOT_LOCATION")));
        }
    }

    private static void saveErrorPage(WebDriver webDriver) throws FileNotFoundException {
        PrintWriter out = new PrintWriter(SeleniumQueryConfig.get("ERROR_PAGE_HTML_LOCATION"));
        out.println(webDriver.getPageSource());
        out.close();
    }

}
