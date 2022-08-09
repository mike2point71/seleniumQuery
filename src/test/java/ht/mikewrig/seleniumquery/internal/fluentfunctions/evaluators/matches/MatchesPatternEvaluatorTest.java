package ht.mikewrig.seleniumquery.internal.fluentfunctions.evaluators.matches;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static testinfrastructure.testdouble.io.github.seleniumquery.SeleniumQueryObjectMother
    .createStubSeleniumQueryObjectWithElements;

import java.util.regex.Pattern;

import org.junit.Test;

import ht.mikewrig.seleniumquery.SeleniumQueryObject;
import ht.mikewrig.seleniumquery.functions.jquery.manipulation.TextFunctionTest;
import ht.mikewrig.seleniumquery.internal.fluentfunctions.FluentBehaviorModifier;
import ht.mikewrig.seleniumquery.internal.fluentfunctions.evaluators.EvaluationReport;
import ht.mikewrig.seleniumquery.internal.fluentfunctions.getters.TextGetter;

public class MatchesPatternEvaluatorTest {

    private final MatchesPatternEvaluator matchesPatternEvaluator = new MatchesPatternEvaluator(TextGetter.TEXT_GETTER);
    private final Pattern caseInsensitivePattern = Pattern.compile("A{3} B{3}", Pattern.CASE_INSENSITIVE);

    @Test
    public void evaluates__success() {
        // given
        SeleniumQueryObject elements = createStubSeleniumQueryObjectWithElements(new TextFunctionTest.WebElementText("aaa"), new TextFunctionTest.WebElementText("bbb"));
        // when
        EvaluationReport evaluate = matchesPatternEvaluator.evaluate(elements, caseInsensitivePattern);
        // then
        assertTrue(evaluate.isSatisfiesConstraints());
    }

    @Test
    public void evaluates__fail() {
        // given
        SeleniumQueryObject elements = createStubSeleniumQueryObjectWithElements(new TextFunctionTest.WebElementText("zzz"), new TextFunctionTest.WebElementText("bbb"));
        // when
        EvaluationReport evaluate = matchesPatternEvaluator.evaluate(elements, caseInsensitivePattern);
        // then
        assertFalse(evaluate.isSatisfiesConstraints());
    }

    @Test
    public void stringFor() {
        // when
        String stringFor = matchesPatternEvaluator.describeEvaluatorFunction(caseInsensitivePattern, FluentBehaviorModifier.NEGATED_BEHAVIOR);
        // then
        assertEquals("text().not().matches(\"A{3} B{3}\")", stringFor);
    }

}
