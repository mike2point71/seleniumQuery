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

package ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition;

import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.CssCondition;

public class AstCssAndCondition implements AstCssCondition {

    private CssCondition firstCondition;
    private CssCondition secondCondition;

    public AstCssAndCondition(CssCondition firstCondition, CssCondition secondCondition) {
        this.firstCondition = firstCondition;
        this.secondCondition = secondCondition;
    }

    public CssCondition getFirstCondition() {
        return firstCondition;
    }

    public CssCondition getSecondCondition() {
        return secondCondition;
    }

    @Override
    public <T> T accept(AstCssConditionVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
