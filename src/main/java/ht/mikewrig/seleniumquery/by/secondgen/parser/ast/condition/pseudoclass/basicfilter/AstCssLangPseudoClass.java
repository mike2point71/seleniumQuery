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

package ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.basicfilter;

import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.AstCssFunctionalPseudoClassCondition;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.AstCssPseudoClassConditionVisitor;

public class AstCssLangPseudoClass extends AstCssFunctionalPseudoClassCondition<String> {

    // :lang(), similar to :not(), gets translated into :lang-sq() by the pre-parser
    public static final String PSEUDO = "lang-sq";

    /* when used without args, such as "div:lang", the pre-parser does not translate it. it is invalid,
          but we still match it, so we can return a proper error message */
    public static final String PSEUDO_PURE_LANG = "lang";

    public AstCssLangPseudoClass(String argument) {
        super(argument);
    }

    @Override
    public <T> T accept(AstCssPseudoClassConditionVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
