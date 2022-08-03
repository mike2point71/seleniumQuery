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

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ht.mikewrig.seleniumquery.SeleniumQueryObject;
import ht.mikewrig.seleniumquery.internal.fluentfunctions.FluentBehaviorModifier;
import ht.mikewrig.seleniumquery.internal.fluentfunctions.evaluators.EvaluationReport;
import ht.mikewrig.seleniumquery.internal.fluentfunctions.evaluators.Evaluator;
import ht.mikewrig.seleniumquery.internal.fluentfunctions.getters.Getter;

/**
 * @author acdcjunior
 * @since 0.18.0
 */
public class IsBlankEvaluator<GETTERTYPE> implements Evaluator<Void, GETTERTYPE> {

	private static final Log LOGGER = LogFactory.getLog(IsBlankEvaluator.class);

	private Getter<GETTERTYPE> getter;

	public IsBlankEvaluator(Getter<GETTERTYPE> getter) {
		this.getter = getter;
	}

	@Override
	public EvaluationReport<GETTERTYPE> evaluate(SeleniumQueryObject seleniumQueryObject, Void unused) {
		LOGGER.debug("Evaluating isBlank()...");
        GETTERTYPE actualValue = getter.get(seleniumQueryObject);
        LOGGER.debug("Evaluating isBlank()... got " + getter + ": " + quoteValue(actualValue) + ". Wanted blank.");
        boolean satisfiesConstraints = StringUtils.isBlank(actualValue == null ? null : actualValue + "");
        return new EvaluationReport<>(actualValue, satisfiesConstraints);
	}

	@Override
	public String describeEvaluatorFunction(Void unused, FluentBehaviorModifier fluentBehaviorModifier) {
        return getter.toString() + fluentBehaviorModifier.asFunctionName() + ".isBlank()";
	}

    @Override
    public String getterAsString() {
        return getter.toString();
    }

    @Override
    public String describeExpectedValue(Void unused) {
        return "be blank";
    }

}
