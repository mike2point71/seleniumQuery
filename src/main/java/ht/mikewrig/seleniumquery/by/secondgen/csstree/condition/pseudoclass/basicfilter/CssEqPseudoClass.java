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

package ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter;

import org.openqa.selenium.WebDriver;

import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.CssPseudoClassCondition;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.finderfactorystrategy.NeverNativelySupportedPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.finderfactorystrategy.XPathMergeStrategy;
import ht.mikewrig.seleniumquery.by.secondgen.finder.XPathAndFilterFinder;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.basicfilter.AstCssEqPseudoClass;

/**
 * :eq(index)
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class CssEqPseudoClass implements CssPseudoClassCondition, NeverNativelySupportedPseudoClass {

    private final AstCssEqPseudoClass astCssEqPseudoClass;

    public CssEqPseudoClass(AstCssEqPseudoClass astCssEqPseudoClass) {
        this.astCssEqPseudoClass = astCssEqPseudoClass;
    }

    @Override
    public XPathAndFilterFinder toXPath(WebDriver webDriver) {
        int index = astCssEqPseudoClass.getArgument();
        if (index >= 0) {
            return XPathAndFilterFinder.pureXPath("position() = " + (index + 1));
        }
        int positionFromLast = -index - 1;
        if (positionFromLast == 0) {
            return XPathAndFilterFinder.pureXPath("position() = last()");
        }
        return XPathAndFilterFinder.pureXPath("position() = (last()-" + positionFromLast + ")");
    }

    @Override
    public XPathMergeStrategy xPathMergeStrategy() {
        return XPathMergeStrategy.CONDITIONAL_TO_ALL_XPATH_MERGE;
    }

}
