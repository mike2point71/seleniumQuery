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

package ht.mikewrig.seleniumquery.by.secondgen.csstree.selector;

import org.openqa.selenium.WebDriver;

import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.CssCondition;
import ht.mikewrig.seleniumquery.by.secondgen.finder.ElementFinder;

/**
 * Conditional selector, simply an union of a selector and a condition.
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class CssConditionalSelector implements CssSelector {

    private final CssSelector cssSelector;
    private final CssCondition cssCondition;

    public CssConditionalSelector(CssSelector cssSelector, CssCondition cssCondition) {
        this.cssSelector = cssSelector;
        this.cssCondition = cssCondition;
    }

    public CssSelector getCssSelector() {
        return cssSelector;
    }

    public CssCondition getCssCondition() {
        return cssCondition;
    }

    @Override
    public ElementFinder toElementFinder(WebDriver webDriver) {
        ElementFinder elementFinder = cssSelector.toElementFinder(webDriver);
        return cssCondition.toElementFinder(elementFinder);
    }

    @Override
    public ElementFinder toElementFinder(ElementFinder leftFinder) {
        ElementFinder elementFinder = cssSelector.toElementFinder(leftFinder);
        return cssCondition.toElementFinder(elementFinder);
    }

}
