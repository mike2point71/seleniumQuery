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

package ht.mikewrig.seleniumquery.internal.fluentfunctions.evaluators.comparison;

import java.math.BigDecimal;

import ht.mikewrig.seleniumquery.internal.fluentfunctions.getters.Getter;

/**
 * Evaluates if the argument is smaller than the element's value.
 *
 * @author acdcjunior
 * @since 0.13.0
 */
public class LessThanEvaluator<GETTERTYPE> extends ComparisonEvaluator<GETTERTYPE> {

	public LessThanEvaluator(Getter<GETTERTYPE> getter) {
		super(getter);
	}

	@Override
	protected boolean compare(BigDecimal elementValueAsNumber, BigDecimal numberToCompare) {
		return elementValueAsNumber.compareTo(numberToCompare) < 0;
	}

	@Override
	protected String getFunctionName() {
		return "isLessThan";
	}

    @Override
    public String describeExpectedValue(Number value) {
        return "be less than " + value;
    }

}
