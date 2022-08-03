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

package ht.mikewrig.seleniumquery.by.secondgen.csstree.condition;

import org.w3c.css.sac.Condition;

public class CssUnknownConditionException extends RuntimeException {

    public CssUnknownConditionException(Condition condition) {
        super("CSS condition " + condition.getClass().getSimpleName() + " (type=" + condition.getConditionType() + ") is invalid or not supported!");
    }

}
