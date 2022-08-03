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

package ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form;

import org.junit.Test;

import static ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.PseudoClassAssertFinderUtils.AssertPseudoClass.assertPseudoClass;
import static ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.PseudoClassAssertFinderUtils.assertPseudoSupportsBothPureCssAndPureXPathWhenNativelySupported;
import static ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.PseudoClassTestUtils.assertQueriesOnSelector;

public class CssEnabledPseudoClassTest {

    private static final String ENABLED_PSEUDO = ":enabled";
    private static final String ENABLED_XPATH_EXPRESSION = ".//*[" +
        "(" +
            "(self::input or self::button or self::optgroup or self::option or self::select or self::textarea)" +
            " and " +
            "not(" +
                "(" +
                    "(@disabled and (self::input or self::button or self::optgroup or self::option or self::select or self::textarea))" +
                    " or " +
                    "(self::option and ancestor::optgroup[@disabled])" +
                ")" +
            ")" +
        ")" +
    "]";

    @Test
    public void translate() {
        assertQueriesOnSelector(ENABLED_PSEUDO).yieldPseudoClass(CssEnabledPseudoClass.class);
    }

    @Test
    public void toElementFinder__when_driver_has_native_support() {
        assertPseudoSupportsBothPureCssAndPureXPathWhenNativelySupported(
            new CssEnabledPseudoClass(),
            ENABLED_PSEUDO,
            ENABLED_XPATH_EXPRESSION
        );
    }

    @Test
    public void toElementFinder__when_driver_does_NOT_have_native_support() {
        assertPseudoClass(new CssEnabledPseudoClass()).whenNotNativelySupported().translatesToPureXPath(ENABLED_XPATH_EXPRESSION);
    }

}
