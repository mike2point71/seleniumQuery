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
import static ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.PseudoClassTestUtils.assertQueriesOnSelector;
import static ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form.CssInputTypeAttributePseudoClassTest.TYPE_ATTR_LOWER_CASE;

public class CssSubmitPseudoClassTest {

    private static final String SUBMIT_PSEUDO = ":submit";
    private static final String SUBMIT_XPATH_EXPRESSION = ".//*[" +
            "(" +
                "(self::input and " + TYPE_ATTR_LOWER_CASE + " = 'submit')" +
            " or " +
                "(self::button and " +
                                    "(" + TYPE_ATTR_LOWER_CASE + " = 'submit' or not(@type))" +
                ")" +
            ")" +
        "]";

    @Test
    public void translate() {
        assertQueriesOnSelector(SUBMIT_PSEUDO).yieldPseudoClass(CssSubmitPseudoClass.class);
    }

    @Test
    public void toElementFinder__REGARDLESS_of_driver_native_support() {
        assertPseudoClass(new CssSubmitPseudoClass()).whenNotNativelySupported().translatesToPureXPath(SUBMIT_XPATH_EXPRESSION);
    }

}
