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

import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.finderfactorystrategy.NeverNativelySupportedPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.finder.XPathAndFilterFinder;

/**
 * :text
 * https://api.jquery.com/text-selector/
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class CssTextPseudoClass implements NeverNativelySupportedPseudoClass {

    @Override
    public XPathAndFilterFinder toXPath(WebDriver webDriver) {
        return XPathAndFilterFinder.pureXPath("(self::input and (" + TYPE_ATTR_LC_VAL + " = 'text' or not(@type)))");
    }

}
