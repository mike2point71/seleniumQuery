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

import ht.mikewrig.seleniumquery.by.common.AttributeEvaluatorUtils;
import ht.mikewrig.seleniumquery.by.secondgen.finder.CssFinder;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.attribute.AstCssEqualsOrHasAttributeCondition;
import ht.mikewrig.seleniumquery.utils.SelectorUtils;

/**
 * [simple]
 * [restart="never"]
 *
 * Case INsensitive!
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class CssEqualsOrHasAttributeCondition extends CssAttributeConditionBase {

    public CssEqualsOrHasAttributeCondition(AstCssEqualsOrHasAttributeCondition astCssEqualsOrHasAttributeCondition) {
        super(astCssEqualsOrHasAttributeCondition);
    }

    protected CssFinder toCSS() {
        if (this.getWantedValue() != null) {
            return super.toCSS();
        }
        return new CssFinder("[" + this.getCssEscapedAttributeName() + "]");
    }

    @Override
    protected String symbol() {
        return "=";
    }

    protected String toXPath() {
        if (this.getWantedValue() != null) {
            String escapedWantedValue = SelectorUtils.intoEscapedXPathString(this.getWantedValue());
            return AttributeEvaluatorUtils.toXPathAttribute(this.getAttributeName()) + "=" + escapedWantedValue;
        }
        return AttributeEvaluatorUtils.toXPathAttribute(this.getAttributeName());
    }

}
