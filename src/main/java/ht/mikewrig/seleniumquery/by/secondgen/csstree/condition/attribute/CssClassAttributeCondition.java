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

package ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.attribute;

import org.unbescape.css.CssEscape;

import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.CssCondition;
import ht.mikewrig.seleniumquery.by.secondgen.finder.CssFinder;
import ht.mikewrig.seleniumquery.by.secondgen.finder.ElementFinder;
import ht.mikewrig.seleniumquery.by.secondgen.finder.ElementFinderUtils;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.attribute.AstCssClassAttributeCondition;

/**
 * .class
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class CssClassAttributeCondition implements CssCondition {

    private final AstCssClassAttributeCondition astCssClassAttributeCondition;

    public CssClassAttributeCondition(AstCssClassAttributeCondition astCssClassAttributeCondition) {
        this.astCssClassAttributeCondition = astCssClassAttributeCondition;
    }

    public String getClassName() {
        return astCssClassAttributeCondition.unescapedClassName;
    }

    @Override
    public ElementFinder toElementFinder(ElementFinder leftFinder) {
        CssFinder newCssSelector = leftFinder.getCssFinder().merge(toCSS());
        String newXPathExpression = ElementFinderUtils.conditionalSimpleXPathMerge(leftFinder.getXPathExpression(), toXPath());
        return new ElementFinder(newCssSelector, newXPathExpression, leftFinder);
    }

    private CssFinder toCSS() {
        return new CssFinder("." + CssEscape.escapeCssIdentifier(this.astCssClassAttributeCondition.unescapedClassName));
    }

    private String toXPath() {
        return "contains(concat(' ', normalize-space(@class), ' '), ' " + astCssClassAttributeCondition.unescapedClassName + " ')";
    }

}
