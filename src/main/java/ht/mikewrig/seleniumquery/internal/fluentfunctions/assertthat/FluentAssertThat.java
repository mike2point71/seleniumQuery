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

package ht.mikewrig.seleniumquery.internal.fluentfunctions.assertthat;

import ht.mikewrig.seleniumquery.SeleniumQueryObject;
import ht.mikewrig.seleniumquery.fluentfunctions.assertthat.SeleniumQueryAssertionError;
import ht.mikewrig.seleniumquery.internal.fluentfunctions.FluentBehaviorModifier;
import ht.mikewrig.seleniumquery.internal.fluentfunctions.FluentFunction;
import ht.mikewrig.seleniumquery.internal.fluentfunctions.evaluators.EvaluationReport;
import ht.mikewrig.seleniumquery.internal.fluentfunctions.evaluators.Evaluator;

public class FluentAssertThat implements FluentFunction {

    @Override
    public <EVALUATORARG, GETTERTYPE> SeleniumQueryObject apply(Evaluator<EVALUATORARG, GETTERTYPE> evaluator,
                                                                EVALUATORARG value,
                                                                SeleniumQueryObject seleniumQueryObject,
                                                                FluentBehaviorModifier fluentBehaviorModifier) {
        EvaluationReport<GETTERTYPE> evaluationReport = evaluator.evaluate(seleniumQueryObject, value);
        if (fluentBehaviorModifier.isNotExpectedBehavior(evaluationReport)) {
            throw new SeleniumQueryAssertionError(
                String.format("Failed assertion %s.assertThat().%s.\n\n%s",
                    seleniumQueryObject,
                    evaluator.describeEvaluatorFunction(value, fluentBehaviorModifier),
                    evaluator.expectedVsActualMessage(fluentBehaviorModifier, value, evaluationReport.getLastValue(), "")
                )
            );
        }
        return seleniumQueryObject;
    }

}
