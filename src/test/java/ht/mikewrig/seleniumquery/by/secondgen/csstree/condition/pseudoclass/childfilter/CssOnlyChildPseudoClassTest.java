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

package ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter;

import org.junit.Test;

import static ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.PseudoClassAssertFinderUtils.AssertPseudoClass.assertPseudoClass;
import static ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.PseudoClassTestUtils.assertQueriesOnSelector;

public class CssOnlyChildPseudoClassTest {

    private static final String ONLY_CHILD_PSEUDO = ":only-child";
    private static final String ONLY_CHILD_XPATH_EXPRESSION = ".//*[../*[last() = 1]]";

    @Test
    public void translate() {
        assertQueriesOnSelector(ONLY_CHILD_PSEUDO).yieldPseudoClass(CssOnlyChildPseudoClass.class);
    }

    @Test
    public void toElementFinder__when_driver_does_NOT_have_native_support() {
        assertPseudoClass(new CssOnlyChildPseudoClass()).whenNotNativelySupported().translatesToPureXPath(ONLY_CHILD_XPATH_EXPRESSION);
    }

}
