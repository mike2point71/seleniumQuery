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

import org.openqa.selenium.WebElement;

import ht.mikewrig.seleniumquery.SeleniumQueryObject;
import ht.mikewrig.seleniumquery.internal.fluentfunctions.FluentBehaviorModifier;
import ht.mikewrig.seleniumquery.internal.fluentfunctions.evaluators.EvaluationReport;
import ht.mikewrig.seleniumquery.internal.fluentfunctions.evaluators.Evaluator;

public class IsHiddenEvaluator implements Evaluator<Void, Object> {

	public static IsHiddenEvaluator IS_HIDDEN_EVALUATOR = new IsHiddenEvaluator();

	IsHiddenEvaluator() {	}

    @Override
	@SuppressWarnings("ConstantConditions")
	public EvaluationReport<Object> evaluate(SeleniumQueryObject seleniumQueryObject, Void selector) {
        int originalSize = seleniumQueryObject.size();
        int visibleSize = seleniumQueryObject.filter(WebElement::isDisplayed).size();
        boolean satisfiesConstraints = satisfiesConstraints(originalSize, visibleSize);
        return new EvaluationReport<>(createObtainedValue(originalSize, visibleSize), satisfiesConstraints);
	}

    boolean satisfiesConstraints(int originalSize, int visibleSize) {
        return originalSize > 0 && visibleSize == 0;
    }

    private Object createObtainedValue(int originalSize, int visibleSize) {
	    // we return this instead of "empty" because "empty" will be rendered between quotes, and we don't want that
        return new Object() {
            @Override
            public String toString() {
                if (originalSize == 0) {
                    return "an empty element set";
                }
                int hiddenSize = originalSize - visibleSize;
                if (hiddenSize == 0) {
                    return "a " + originalSize + " element set, with no hidden elements";
                }
                if (visibleSize == 1) {
                    return "a " + originalSize + " element set, of which 1 was not hidden";
                }
                return "a " + originalSize + " element set, of which " + visibleSize + " were not hidden";
            }
        };
    }

    @Override
	public String describeEvaluatorFunction(Void selector, FluentBehaviorModifier fluentBehaviorModifier) {
        return "isHidden()";
	}

    @Override
    public String getterAsString() {
        return "matched element set";
    }

    @Override
    public String describeExpectedValue(Void selector) {
        return "be not empty and have only hidden elements";
    }

}
