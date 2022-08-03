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

package ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter;

import org.junit.Test;

import static ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.PseudoClassAssertFinderUtils.AssertPseudoClass.assertPseudoClass;
import static ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.PseudoClassTestUtils.assertQueriesOnSelector;

public class CssOddPseudoClassTest {

    private static final String ODD_PSEUDO = ":odd";
    private static final String ODD_XPATH_EXPRESSION = ".//*[(position() mod 2) = 0]";

    @Test
    public void translate() {
        assertQueriesOnSelector(ODD_PSEUDO).yieldPseudoClass(CssOddPseudoClass.class);
    }

    @Test
    public void toElementFinder__when_driver_does_NOT_have_native_support() {
        assertPseudoClass(new CssOddPseudoClass()).whenNotNativelySupported().translatesToPureXPath(ODD_XPATH_EXPRESSION);
    }


}
