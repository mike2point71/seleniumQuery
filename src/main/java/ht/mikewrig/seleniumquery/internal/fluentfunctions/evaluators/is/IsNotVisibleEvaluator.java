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

package ht.mikewrig.seleniumquery.internal.fluentfunctions.evaluators.is;

import ht.mikewrig.seleniumquery.internal.fluentfunctions.FluentBehaviorModifier;

public class IsNotVisibleEvaluator extends IsHiddenEvaluator {

	public static IsNotVisibleEvaluator IS_NOT_VISIBLE_EVALUATOR = new IsNotVisibleEvaluator();

    @Override
    boolean satisfiesConstraints(int originalSize, int visibleSize) {
        return visibleSize == 0;
    }

    @Override
	public String describeEvaluatorFunction(Void selector, FluentBehaviorModifier fluentBehaviorModifier) {
        return "isNotVisible()";
	}

    @Override
    public String describeExpectedValue(Void selector) {
        return "be empty or have only hidden elements";
    }

}
