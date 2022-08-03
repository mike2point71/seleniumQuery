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

package ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.seleniumquery;

import org.openqa.selenium.WebDriver;

import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.finderfactorystrategy
    .AlwaysNativelySupportedPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.finder.CssFinder;
import ht.mikewrig.seleniumquery.by.secondgen.finder.XPathAndFilterFinder;

/**
 * :present
 * https://github.com/seleniumQuery/seleniumQuery/wiki/seleniumQuery-Selectors#extra---seleniumquery-only-selectors
 *
 * Matches all elements that are attached to the DOM. It is the "identity" selector, basically. It
 * should not affect the other selector it is used with.
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class CssPresentPseudoClass implements AlwaysNativelySupportedPseudoClass {

    @Override
    public CssFinder toCssWhenNativelySupported(WebDriver webDriver) {
        return CssFinder.universalSelector();
    }

    @Override
    public XPathAndFilterFinder toXPath(WebDriver webDriver) {
        return XPathAndFilterFinder.pureXPath("true()");
    }

}
