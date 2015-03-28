/*
 * Copyright (c) 2015 seleniumQuery authors
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

package io.github.seleniumquery.by.csstree.condition.pseudoclass.childfilter;

import io.github.seleniumquery.by.locator.SQLocator;
import org.junit.Test;
import org.openqa.selenium.InvalidSelectorException;

import static io.github.seleniumquery.by.csstree.condition.pseudoclass.PseudoClassTestUtils.assertFunctionalPseudo;
import static io.github.seleniumquery.by.csstree.condition.pseudoclass.PseudoClassTestUtils.createPseudoClassSelectorAppliedToUniversalSelector;
import static io.github.seleniumquery.by.locator.SQLocatorUtilsTest.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class SQCssNthChildPseudoClassTest {

    private static final String NTH_CHILD_PSEUDO = ":nth-child";

    private static final SQLocator UNIVERSAL_SELECTOR_LOCATOR_SUPPORTING_NTHCHILD_NATIVELY = universalSelectorLocator(
            createMockDriverWithNativeSupportFor(":nth-child(1)")
    );
    private static final SQLocator UNIVERSAL_SELECTOR_LOCATOR_NOT_SUPPORTING_NTHCHILD_NATIVELY = UNIVERSAL_SELECTOR_LOCATOR;

    @Test
    public void translate() {
        assertFunctionalPseudo(NTH_CHILD_PSEUDO, SQCssNthChildPseudoClass.class);
    }

//    @Test
//    public void toSQLocator__when_driver_has_native_support() {
//        assertPseudoSupportsBothPureCssAndPureXPathWhenNativelySupported(
//                new SQCssEnabledPseudoClass(),
//                ENABLED_PSEUDO,
//                ENABLED_XPATH_EXPRESSION
//        );
//    }

    @Test
    public void toSQLocator__nthChild_should_throw_exception_if_argument_is_not_valid() {
        assertNthChildArgumentIsNotValid("a");
        assertNthChildArgumentIsNotValid("");
        assertNthChildArgumentIsNotValid("+");
        assertNthChildArgumentIsNotValid("-");
        assertNthChildArgumentIsNotValid("+ 1");
        assertNthChildArgumentIsNotValid(" ");
        assertNthChildArgumentIsNotValid("oddy");
        assertNthChildArgumentIsNotValid("eveny");
        assertNthChildArgumentIsNotValid("1n-");
        assertNthChildArgumentIsNotValid("1n+");
        assertNthChildArgumentIsNotValid("1 n");
        assertNthChildArgumentIsNotValid("1n+1 1");
    }

    private void assertNthChildArgumentIsNotValid(String nthChildArgument) {
        assertNthChildArgumentIsNotValidOnLocator(nthChildArgument, UNIVERSAL_SELECTOR_LOCATOR_SUPPORTING_NTHCHILD_NATIVELY);
        assertNthChildArgumentIsNotValidOnLocator(nthChildArgument, UNIVERSAL_SELECTOR_LOCATOR_NOT_SUPPORTING_NTHCHILD_NATIVELY);
    }

    private void assertNthChildArgumentIsNotValidOnLocator(String nthChildArgument, SQLocator universalSelectorLocator) {
        try {
            nthChild(nthChildArgument).toSQLocator(universalSelectorLocator);
            fail("Should consider *:nth-child("+nthChildArgument+") to be invalid.");
        } catch (InvalidSelectorException e) {
            assertThat(e.getMessage(), containsString(":nth-child()"));
            assertThat(e.getMessage(), containsString(nthChildArgument));
        }
    }

    private SQCssNthChildPseudoClass nthChild(String nthChildArgument) {
        return new SQCssNthChildPseudoClass(createPseudoClassSelectorAppliedToUniversalSelector(nthChildArgument));
    }

//    @Test
//    public void toXPathCondition__class() {
//        TagComponent xPathExpr = selectorToExpression(":nth-child(odd)");
//        String xPathCondition = xPathExpr.toXPathCondition();
//        assertThat(xPathCondition, is("contains(concat(' ', normalize-space(@class), ' '), ' cls ')"));
//    }
//    public static TagComponent selectorToExpression(String selector) {
//        CSSParsedSelectorList CSSParsedSelectorList = CSSSelectorParser.parseSelector(selector);
//        SelectorList selectorList = CSSParsedSelectorList.getSelectorList();
//        return XPathComponentCompilerService.compileSelector(CSSParsedSelectorList.getStringMap(), selectorList.item(0));
//    }

}