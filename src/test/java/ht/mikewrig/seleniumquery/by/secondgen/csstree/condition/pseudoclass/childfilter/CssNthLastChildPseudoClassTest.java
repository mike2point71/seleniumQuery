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

package ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter;

import static ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.PseudoClassAssertFinderUtils.AssertPseudoClass
    .assertPseudoClass;
import static ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.PseudoClassAssertFinderUtils
    .assertPseudoSupportsBothPureCssAndPureXPathWhenNativelySupported;
import static ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.PseudoClassTestUtils.assertQueriesOnSelector;

import org.junit.Test;

import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.childfilter.AstCssNthLastChildPseudoClass;

/**
 * IMPORTANT:
 * This class has less tests than the :nth-child pseudo. This is intentional. They use the
 * same implementation (see {@link NthArgument}) and there's no point in testing all degenerate
 * cases here, as we did in :nth-child. We're lazy.
 */
public class CssNthLastChildPseudoClassTest {

    private static final String NTH_LAST_CHILD_PSEUDO = ":nth-last-child";
    private static final String NTH_LAST_CHILD_PSEUDO_USED_IN_NATIVE_SUPPORT_CHECK = NTH_LAST_CHILD_PSEUDO + "(1)";

    @Test
    public void translate() {
        assertQueriesOnSelector(NTH_LAST_CHILD_PSEUDO).withAllKindsOfArguments().yieldFunctionalPseudoclassWithCorrectlyTranslatedArguments(CssNthLastChildPseudoClass.class);
    }

    @Test
    public void toElementFinder() {
        assertNthLastChildArgumentYields("2",     ":nth-last-child(2)",     ".//*[(last()+1-position()) = 2]");
        assertNthLastChildArgumentYields("3",     ":nth-last-child(3)",     ".//*[(last()+1-position()) = 3]");
        assertNthLastChildArgumentYields("5n",    ":nth-last-child(5n)",    ".//*[((last()+1-position()) - 0) mod 5 = 0 and (last()+1-position()) >= 0]");
        assertNthLastChildArgumentYields("-5n",   ":nth-last-child(-5n)",   ".//*[((last()+1-position()) - 0) mod -5 = 0 and (last()+1-position()) <= 0]");
        assertNthLastChildArgumentYields("2n+3",  ":nth-last-child(2n+3)",  ".//*[((last()+1-position()) - 3) mod 2 = 0 and (last()+1-position()) >= 3]");
        assertNthLastChildArgumentYields("2n-3",  ":nth-last-child(2n-3)",  ".//*[((last()+1-position()) - -3) mod 2 = 0 and (last()+1-position()) >= -3]");
        assertNthLastChildArgumentYields("-2n+3", ":nth-last-child(-2n+3)", ".//*[((last()+1-position()) - 3) mod -2 = 0 and (last()+1-position()) <= 3]");
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    private void assertNthLastChildArgumentYields(String nthArgument, String expectedCSS, String expectedXPath) {
        String pseudoThatTheDriverWillTestForNativeSupport = NTH_LAST_CHILD_PSEUDO_USED_IN_NATIVE_SUPPORT_CHECK;
        assertPseudoSupportsBothPureCssAndPureXPathWhenNativelySupported(
                pseudoThatTheDriverWillTestForNativeSupport,
                new CssNthLastChildPseudoClass(new AstCssNthLastChildPseudoClass(nthArgument)),
                expectedCSS,
                expectedXPath
        );
        assertPseudoClass(new CssNthLastChildPseudoClass(new AstCssNthLastChildPseudoClass(nthArgument))).whenNotNativelySupported().translatesToPureXPath(expectedXPath);
    }

}
