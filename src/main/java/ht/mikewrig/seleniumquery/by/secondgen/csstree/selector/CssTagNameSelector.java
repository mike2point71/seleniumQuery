/*
 * Copyright (c) 2016 seleniumQuery authors
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

package ht.mikewrig.seleniumquery.by.secondgen.csstree.selector;

import ht.mikewrig.seleniumquery.by.common.elementfilter.ElementFilterList;
import ht.mikewrig.seleniumquery.by.secondgen.finder.CssFinder;
import ht.mikewrig.seleniumquery.by.secondgen.finder.ElementFinder;
import ht.mikewrig.seleniumquery.by.secondgen.finder.ElementFinderUtils;
import ht.mikewrig.seleniumquery.by.secondgen.finder.XPathAndFilterFinder;
import org.openqa.selenium.WebDriver;

import static ht.mikewrig.seleniumquery.by.secondgen.finder.CssFinder.fromTag;

/**
 * Element or tag selector. Example: {@code "div"}.
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class CssTagNameSelector implements CssSelector {

    private String tagName;

    public CssTagNameSelector(String tagName) {
        this.tagName = "".equals(tagName) ? "*" : tagName;
    }

    public String getTagName() {
        return tagName;
    }

    @Override
    public ElementFinder toElementFinder(WebDriver webDriver) {
        XPathAndFilterFinder xPathAndFilterFinder = new XPathAndFilterFinder(toXPath(), ElementFilterList.FILTER_NOTHING_LIST) {
            @Override
            public String getXPathExpression() {
                return ".//*[" + getRawXPathExpression() + "]";
            }
        };
        return new ElementFinder(webDriver, toCSS(), xPathAndFilterFinder);
    }

    @Override
    public ElementFinder toElementFinder(ElementFinder leftFinder) {
        CssFinder combinedCssSelector = leftFinder.getCssFinder().merge(toCSS());
        String combinedXPathExp = ElementFinderUtils.conditionalSimpleXPathMerge(leftFinder.getXPathExpression(), toXPath());
        return new ElementFinder(combinedCssSelector, combinedXPathExp, leftFinder);
    }

    private String toXPath() {
        return "*".equals(this.tagName) ? "true()" : "self::"+tagName;
    }

    private CssFinder toCSS() {
        return fromTag(this.tagName);
    }

}
