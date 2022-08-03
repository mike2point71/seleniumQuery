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
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.attribute.AstCssAttributeConditionBase;

/**
 * A class that holds an attribute name and a wanted value.
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public abstract class CssAttributeConditionBase implements CssCondition {

    private final AstCssAttributeConditionBase astCssAttributeConditionBase;

    CssAttributeConditionBase(AstCssAttributeConditionBase astCssAttributeConditionBase) {
        this.astCssAttributeConditionBase = astCssAttributeConditionBase;
    }

    public String getAttributeName() {
        return astCssAttributeConditionBase.attributeName;
    }

    public String getWantedValue() {
        return astCssAttributeConditionBase.wantedValue;
    }

    @Override
    public ElementFinder toElementFinder(ElementFinder leftFinder) {
        CssFinder newCssSelector = leftFinder.getCssFinder().merge(toCSS());
        String newXPathExpression = ElementFinderUtils.conditionalSimpleXPathMerge(leftFinder.getXPathExpression(), toXPath());
        return new ElementFinder(newCssSelector, newXPathExpression, leftFinder);
    }

    protected CssFinder toCSS() {
        return new CssFinder("[" + getCssEscapedAttributeName() + symbol() + "'" + CssEscape.escapeCssString(this.astCssAttributeConditionBase.wantedValue) + "']");
    }

    String getCssEscapedAttributeName() {
        return CssEscape.escapeCssIdentifier(this.astCssAttributeConditionBase.attributeName);
    }

    protected abstract String symbol();

    protected abstract String toXPath();

}
