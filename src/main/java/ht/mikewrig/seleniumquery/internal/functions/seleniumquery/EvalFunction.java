package ht.mikewrig.seleniumquery.internal.functions.seleniumquery;

import org.openqa.selenium.JavascriptExecutor;

import ht.mikewrig.seleniumquery.SeleniumQueryObject;
import ht.mikewrig.seleniumquery.internal.InternalTargetableSqObject;

public interface EvalFunction extends InternalTargetableSqObject {

    @SuppressWarnings("unchecked")
    default <T> T eval(String javaScriptCode, Object... args) {
        SeleniumQueryObject target = target();
        return (T) ((JavascriptExecutor) target.getWebDriver()).executeScript(javaScriptCode, target.toArray(), args);
    }

}
