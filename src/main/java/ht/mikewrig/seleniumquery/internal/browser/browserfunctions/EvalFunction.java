package ht.mikewrig.seleniumquery.internal.browser.browserfunctions;

import org.openqa.selenium.JavascriptExecutor;

import ht.mikewrig.seleniumquery.internal.browser.InternalTargetableBrowserFunctions;

public interface EvalFunction extends InternalTargetableBrowserFunctions {

    @SuppressWarnings("unchecked")
    default <T> T eval(String javaScriptCode, Object... args) {
        return (T) ((JavascriptExecutor) target().driver().get()).executeScript(javaScriptCode, args);
    }

}
