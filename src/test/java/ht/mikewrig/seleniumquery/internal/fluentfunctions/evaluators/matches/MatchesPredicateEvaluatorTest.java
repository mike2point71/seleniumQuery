package ht.mikewrig.seleniumquery.internal.fluentfunctions.evaluators.matches;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static testinfrastructure.testdouble.ht.mikewrig.seleniumquery.SeleniumQueryObjectMother
    .createStubSeleniumQueryObjectWithElements;

import java.util.function.Predicate;

import org.junit.Test;

import ht.mikewrig.seleniumquery.SeleniumQueryObject;
import ht.mikewrig.seleniumquery.functions.jquery.manipulation.TextFunctionTest;
import ht.mikewrig.seleniumquery.internal.fluentfunctions.FluentBehaviorModifier;
import ht.mikewrig.seleniumquery.internal.fluentfunctions.evaluators.EvaluationReport;
import ht.mikewrig.seleniumquery.internal.fluentfunctions.getters.TextGetter;

public class MatchesPredicateEvaluatorTest {

    private final MatchesPredicateEvaluator<String> matchesPredicateEvaluator = new MatchesPredicateEvaluator<>(TextGetter.TEXT_GETTER);
    private final Predicate<String> lambda = text -> text.contains("a");

    @Test
    public void evaluates__success() {
        // given
        SeleniumQueryObject sqo = createStubSeleniumQueryObjectWithElements(new TextFunctionTest.WebElementText("aaa"), new TextFunctionTest.WebElementText("bbb"));
        // when
        EvaluationReport evaluate = matchesPredicateEvaluator.evaluate(sqo, lambda);
        // then
        assertTrue(evaluate.isSatisfiesConstraints());
    }

    @Test
    public void evaluates__fail() {
        // given
        SeleniumQueryObject sqo = createStubSeleniumQueryObjectWithElements(new TextFunctionTest.WebElementText("zzz"), new TextFunctionTest.WebElementText("bbb"));
        // when
        EvaluationReport evaluate = matchesPredicateEvaluator.evaluate(sqo, lambda);
        // then
        assertFalse(evaluate.isSatisfiesConstraints());
    }

    @Test
    public void stringFor() {
        // when
        String stringFor = matchesPredicateEvaluator.describeEvaluatorFunction(lambda, FluentBehaviorModifier.NEGATED_BEHAVIOR);
        // then
        assertEquals("text().not().matches(<predicate function>)", stringFor);
    }

}
