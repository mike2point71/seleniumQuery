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

package ht.mikewrig.seleniumquery.internal.fluentfunctions.evaluators.matches;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ht.mikewrig.seleniumquery.SeleniumQueryObject;
import ht.mikewrig.seleniumquery.internal.fluentfunctions.FluentBehaviorModifier;
import ht.mikewrig.seleniumquery.internal.fluentfunctions.evaluators.EvaluationReport;
import ht.mikewrig.seleniumquery.internal.fluentfunctions.evaluators.Evaluator;
import ht.mikewrig.seleniumquery.internal.fluentfunctions.getters.Getter;

/**
 * Evaluator that tests the elements' values against a string regex.
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class MatchesStringRegexEvaluator<GETTERTYPE> implements Evaluator<String, GETTERTYPE> {

    private static final Log LOGGER = LogFactory.getLog(MatchesStringRegexEvaluator.class);

	private Getter<GETTERTYPE> getter;

	public MatchesStringRegexEvaluator(Getter<GETTERTYPE> getter) {
		this.getter = getter;
	}

	@Override
    public EvaluationReport<GETTERTYPE> evaluate(SeleniumQueryObject seleniumQueryObject, String regex) {
        LOGGER.debug("Evaluating .matches(<regex>)...");
        GETTERTYPE actualValue = getter.get(seleniumQueryObject);
        LOGGER.debug("Evaluating .matches(<regex>)... got " + getter + ": " + quoteValue(actualValue) + ". Wanted: <" + quoteArg(regex) + ">.");
        boolean satisfiesConstraints = actualValue != null && actualValue.toString().matches(regex);
        return new EvaluationReport<>(actualValue, satisfiesConstraints);
    }

	@Override
	public String describeEvaluatorFunction(String regex, FluentBehaviorModifier fluentBehaviorModifier) {
		return getter.toString() + fluentBehaviorModifier.asFunctionName() + ".matches(" + quoteArg(regex) + ")";
	}

    @Override
    public String getterAsString() {
        return getter.toString();
    }

    @Override
    public String describeExpectedValue(String regex) {
        return "match regex " + quoteArg(regex);
    }

}
