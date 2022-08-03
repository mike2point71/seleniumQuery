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

import org.openqa.selenium.WebDriver;

import ht.mikewrig.seleniumquery.by.firstgen.css.pseudoclasses.DisabledPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.finderfactorystrategy.MaybeNativelySupportedPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.finder.CssFinder;
import ht.mikewrig.seleniumquery.by.secondgen.finder.XPathAndFilterFinder;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.form.AstCssDisabledPseudoClass;

/**
 * :disabled
 * https://api.jquery.com/disabled-selector/
 * https://developer.mozilla.org/en-US/docs/Web/CSS/:disabled
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class CssDisabledPseudoClass implements MaybeNativelySupportedPseudoClass {

    private static final String DISABLED_PSEUDO = ":" + AstCssDisabledPseudoClass.PSEUDO;

    @Override
    public CssFinder toCssWhenNativelySupported(WebDriver webDriver) {
        return new CssFinder(DISABLED_PSEUDO);
    }

    @Override
    public XPathAndFilterFinder toXPath(WebDriver webDriver) {
        return XPathAndFilterFinder.pureXPath(DisabledPseudoClass.DISABLED_XPATH_CONDITION);
    }

}
