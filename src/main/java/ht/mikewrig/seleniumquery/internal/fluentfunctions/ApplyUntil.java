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

package ht.mikewrig.seleniumquery.internal.fluentfunctions;

import java.util.function.Predicate;
import java.util.regex.Pattern;

import org.hamcrest.Matcher;

import ht.mikewrig.seleniumquery.SeleniumQueryFluentAndOrThen;
import ht.mikewrig.seleniumquery.SeleniumQueryFluentFunctionEvaluateIf;
import ht.mikewrig.seleniumquery.SeleniumQueryObject;
import ht.mikewrig.seleniumquery.internal.fluentfunctions.evaluators.ContainsEvaluator;
import ht.mikewrig.seleniumquery.internal.fluentfunctions.evaluators.ContainsIgnoreCaseEvaluator;
import ht.mikewrig.seleniumquery.internal.fluentfunctions.evaluators.Evaluator;
import ht.mikewrig.seleniumquery.internal.fluentfunctions.evaluators.comparison.GreaterThanEvaluator;
import ht.mikewrig.seleniumquery.internal.fluentfunctions.evaluators.comparison.LessThanEvaluator;
import ht.mikewrig.seleniumquery.internal.fluentfunctions.evaluators.is.IsBlankEvaluator;
import ht.mikewrig.seleniumquery.internal.fluentfunctions.evaluators.is.IsEqualToEvaluator;
import ht.mikewrig.seleniumquery.internal.fluentfunctions.evaluators.matches.MatchesHamcrestMatcherEvaluator;
import ht.mikewrig.seleniumquery.internal.fluentfunctions.evaluators.matches.MatchesPatternEvaluator;
import ht.mikewrig.seleniumquery.internal.fluentfunctions.evaluators.matches.MatchesPredicateEvaluator;
import ht.mikewrig.seleniumquery.internal.fluentfunctions.evaluators.matches.MatchesStringRegexEvaluator;
import ht.mikewrig.seleniumquery.internal.fluentfunctions.getters.Getter;

/**
 * Functions used in the waitUntil().
 *
 * @param <GETTERTYPE> The type returned by the getter and TYPE OF THE ARGUMENT used in the end function.
 *
 * @author acdcjunior
 * @since 0.9.0
 */
class ApplyUntil<GETTERTYPE> implements SeleniumQueryFluentFunctionEvaluateIf<GETTERTYPE> {

	private final FluentFunction fluentFunction;
	private final Getter<GETTERTYPE> getter;
	private final SeleniumQueryObject seleniumQueryObject;
    private final FluentBehaviorModifier fluentBehaviorModifier;

    ApplyUntil(FluentFunction fluentFunction, Getter<GETTERTYPE> getter, SeleniumQueryObject seleniumQueryObject) {
        this(fluentFunction, getter, seleniumQueryObject, FluentBehaviorModifier.REGULAR_BEHAVIOR);
	}

	private ApplyUntil(FluentFunction fluentFunction, Getter<GETTERTYPE> getter, SeleniumQueryObject seleniumQueryObject,
                       FluentBehaviorModifier fluentBehaviorModifier) {
		this.fluentFunction = fluentFunction;
		this.getter = getter;
		this.seleniumQueryObject = seleniumQueryObject;
		this.fluentBehaviorModifier = fluentBehaviorModifier;
	}

	@Override
	public SeleniumQueryFluentAndOrThen isEqualTo(GETTERTYPE valueToEqual) {
        return andOrThen(new IsEqualToEvaluator<>(getter), valueToEqual);
	}

    @Override
    public SeleniumQueryFluentAndOrThen isBlank() {
        return andOrThen(new IsBlankEvaluator<>(getter), null);
    }

    private <V> AndOrThen andOrThen(Evaluator<V, GETTERTYPE> evaluator, V value) {
        return new AndOrThen(fluentFunction.apply(evaluator, value, seleniumQueryObject, this.fluentBehaviorModifier), this.fluentFunction);
    }

    @Override
	public SeleniumQueryFluentAndOrThen contains(String string) {
        return andOrThen(new ContainsEvaluator<>(getter), string);
	}

    @Override
    public SeleniumQueryFluentAndOrThen containsIgnoreCase(String string) {
        return andOrThen(new ContainsIgnoreCaseEvaluator<>(getter), string);
    }

	@Override
	public SeleniumQueryFluentAndOrThen isGreaterThan(Number valueToCompare) {
        return andOrThen(new GreaterThanEvaluator<>(getter), valueToCompare);
	}

    @Override
	public SeleniumQueryFluentAndOrThen isLessThan(Number valueToCompare) {
        return andOrThen(new LessThanEvaluator<>(getter), valueToCompare);
	}

	@Override
	public SeleniumQueryFluentFunctionEvaluateIf<GETTERTYPE> not() {
		return new ApplyUntil<>(fluentFunction, getter, seleniumQueryObject, this.fluentBehaviorModifier.negate());
	}

    @Override
    public SeleniumQueryFluentAndOrThen matches(String regex) {
        return andOrThen(new MatchesStringRegexEvaluator<>(getter), regex);
    }

    @Override
    public SeleniumQueryFluentAndOrThen matches(Pattern regexPattern) {
        return andOrThen(new MatchesPatternEvaluator<>(getter), regexPattern);
    }

    @Override
    public SeleniumQueryFluentAndOrThen matches(Matcher<GETTERTYPE> hamcrestMatcher) {
        return andOrThen(new MatchesHamcrestMatcherEvaluator<>(getter), hamcrestMatcher);
    }

    @Override
    public SeleniumQueryFluentAndOrThen matches(Predicate<GETTERTYPE> predicateFunction) {
        return andOrThen(new MatchesPredicateEvaluator<>(getter), predicateFunction);
    }

}
