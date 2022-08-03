/*
 * Copyright (c) 2017 seleniumQuery authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form;

import static ht.mikewrig.seleniumquery.by.common.AttributeEvaluatorUtils.TYPE_ATTR_LC_VAL;

import org.openqa.selenium.WebDriver;

import ht.mikewrig.seleniumquery.by.firstgen.css.pseudoclasses.CheckedPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.finderfactorystrategy.MaybeNativelySupportedPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.finder.CssFinder;
import ht.mikewrig.seleniumquery.by.secondgen.finder.XPathAndFilterFinder;
import ht.mikewrig.seleniumquery.utils.DriverVersionUtils;

/**
 * <p>
 * https://developer.mozilla.org/en-US/docs/Web/CSS/:checked
 * </p>
 *
 * <br>
 * <h1>#Cross-Driver</h1>
 * In PhantomJSDriver and HtmlUnitDriver, <code>document.querySelectorAll(":checked")</code> does not work
 * for {@code <option>} tags, so we should consider it as not supported!
 * <br>
 * Issue in PhantomJS: https://github.com/ariya/phantomjs/issues/11550
 * <br>
 * We have a test (endtoend.crossdriver.driverbugs.PhantomJSAndHtmlUnitCheckedSelectorBugTest) that asserts these
 * bugs continue to exist.
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class CssCheckedPseudoClass implements MaybeNativelySupportedPseudoClass {

    static final String CHECKED_PSEUDO = ":checked";

    @Override
    public boolean isThisCSSPseudoClassNativelySupportedOn(WebDriver webDriver) {
//        return isDriverWhereCheckedSelectorHasNoBugs(webDriver)  && MaybeNativelySupportedPseudoClass.super.isThisCSSPseudoClassNativelySupportedOn(webDriver);
        return MaybeNativelySupportedPseudoClass.super.isThisCSSPseudoClassNativelySupportedOn(webDriver);
    }

    @Override
    public CssFinder toCssWhenNativelySupported(WebDriver webDriver) {
        return new CssFinder(CHECKED_PSEUDO);
    }

    @Override
    public XPathAndFilterFinder toXPath(WebDriver webDriver) {
        return new XPathAndFilterFinder(xPathExpression(), CheckedPseudoClass.CHECKED_FILTER);
    }

    private String xPathExpression() {
        return "((self::input and ("+ TYPE_ATTR_LC_VAL +" = 'radio' or "+ TYPE_ATTR_LC_VAL +" = 'checkbox')) or self::option)";
    }

//    static boolean isDriverWhereCheckedSelectorHasNoBugs(WebDriver webDriver) {
//        DriverVersionUtils driverVsUtils = DriverVersionUtils.getInstance();
//        return !driverVsUtils.isPhantomJSDriver(webDriver);
//    }

}
