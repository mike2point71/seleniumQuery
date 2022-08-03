/*
 * Copyright (c) 2015 seleniumQuery authors
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

package ht.mikewrig.seleniumquery.by.firstgen.xpath.component;

import ht.mikewrig.seleniumquery.by.common.elementfilter.ElementFilter;
import ht.mikewrig.seleniumquery.by.common.elementfilter.ElementFilterList;

import java.util.List;

/*
 * if CONDITIONAL_SIMPLE, then the expr can be just appended to other, such as:
 * //*[@other][@thisSelector]
 *
 * @see also {@link ht.mikewrig.seleniumquery.by.firstgen.xpath.component.ConditionToAllComponent}
 */
public class ConditionSimpleComponent extends ConditionComponent {

    private static final String EMPTY_XPATH_EXPRESSION = "";

    /**
     * Creates a XPath Component that is empty (has no XPath expression) and no Element Filter.
     */
    public ConditionSimpleComponent() {
        this(ElementFilter.FILTER_NOTHING);
    }

    public ConditionSimpleComponent(String xPathExpression) {
        this(xPathExpression, ElementFilter.FILTER_NOTHING);
    }

    public ConditionSimpleComponent(ElementFilter filter) {
        this(EMPTY_XPATH_EXPRESSION, filter);
    }

    public ConditionSimpleComponent(String xPathExpression, ElementFilter filter) {
        super(xPathExpression, ComponentUtils.toElementFilterList(filter));
    }

    protected ConditionSimpleComponent(String xPathExpression, List<XPathComponent> combinatedComponents, ElementFilterList elementFilterList) {
        super(xPathExpression, combinatedComponents, elementFilterList);
    }

    @Override
    public String mergeIntoExpression(String sourceXPathExpression) {
        return merge(sourceXPathExpression, this.xPathExpression);
    }

    @Override
    public String mergeExpressionAsCondition(String sourceXPathExpression) {
        return mergeAsCondition(sourceXPathExpression, this.xPathExpression);
    }

    public static String merge(String sourceXPathExpression, String otherXPathExpression) {
        if (sourceXPathExpression.endsWith("]")) {
            // because the previous was merged as a conditional, and we are a conditional as well, so we just 'AND it
            return sourceXPathExpression.substring(0, sourceXPathExpression.length()-1) + " and " + otherXPathExpression.substring(1);
        }
        return sourceXPathExpression + otherXPathExpression;
    }
    public static String mergeAsCondition(String sourceXPathExpression, String otherXPathExpression) {
        if (sourceXPathExpression.equals(MATCH_EVERYTHING_XPATH_CONDITIONAL)) {
            return ComponentUtils.removeBraces(otherXPathExpression);
        }
        return sourceXPathExpression + " and " + ComponentUtils.removeBraces(otherXPathExpression);
    }

    @Override
    public ConditionSimpleComponent cloneComponent() {
        return new ConditionSimpleComponent(this.xPathExpression, this.combinatedComponents, this.elementFilterList);
    }

    @Override
    public ConditionSimpleComponent cloneAndCombineTo(Combinable other) {
        XPathComponent otherCopy = other.cloneComponent();
        return new ConditionSimpleComponent(this.xPathExpression,
                ComponentUtils.joinComponents(this.combinatedComponents, otherCopy),
                ComponentUtils.joinFilters(this.elementFilterList, otherCopy));
    }

}
