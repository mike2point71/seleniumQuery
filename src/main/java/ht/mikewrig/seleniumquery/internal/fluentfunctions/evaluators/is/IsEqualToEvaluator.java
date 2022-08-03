/*
 * Copyright (c) 2016 seleniumQuery authors
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

import java.util.Objects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ht.mikewrig.seleniumquery.SeleniumQueryObject;
import ht.mikewrig.seleniumquery.internal.fluentfunctions.FluentBehaviorModifier;
import ht.mikewrig.seleniumquery.internal.fluentfunctions.evaluators.EvaluationReport;
import ht.mikewrig.seleniumquery.internal.fluentfunctions.evaluators.Evaluator;
import ht.mikewrig.seleniumquery.internal.fluentfunctions.getters.Getter;

public class IsEqualToEvaluator<T> implements Evaluator<T, T> {

	private static final Log LOGGER = LogFactory.getLog(IsEqualToEvaluator.class);

	private Getter<T> getter;

	public IsEqualToEvaluator(Getter<T> getter) {
		this.getter = getter;
	}

	@Override
	public EvaluationReport<T> evaluate(SeleniumQueryObject seleniumQueryObject, T valueToEqual) {
		LOGGER.debug("Evaluating isEqualTo()...");
		T actualValue = getter.get(seleniumQueryObject);
        LOGGER.debug("Evaluating isEqualTo()... got " + getter + ": " + quoteValue(actualValue) + ". Wanted: " + quoteValue(actualValue) + ".");
        boolean satisfiesConstraints = Objects.equals(actualValue, valueToEqual);
        return new EvaluationReport<>(actualValue, satisfiesConstraints);
	}

	@Override
	public String describeEvaluatorFunction(T valueToEqual, FluentBehaviorModifier fluentBehaviorModifier) {
        return getter.toString() + fluentBehaviorModifier.asFunctionName() + ".isEqualTo(" + quoteValue(valueToEqual) + ")";
	}

    @Override
    public String getterAsString() {
        return getter.toString();
    }

    @Override
    public String describeExpectedValue(T valueToEqual) {
        return "equal " + quoteValue(valueToEqual);
    }

}
