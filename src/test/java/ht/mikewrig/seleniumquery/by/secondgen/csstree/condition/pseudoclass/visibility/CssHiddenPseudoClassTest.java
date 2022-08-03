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

package ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.visibility;

import ht.mikewrig.seleniumquery.by.firstgen.css.pseudoclasses.HiddenPseudoClass;
import org.junit.Test;

import static ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.PseudoClassTestUtils.assertFilterOnlyPseudoGeneratesFilter;
import static ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.PseudoClassTestUtils.assertQueriesOnSelector;

public class CssHiddenPseudoClassTest {

    @Test
    public void translate() {
        assertQueriesOnSelector(":hidden").yieldPseudoClass(CssHiddenPseudoClass.class);
    }

    @Test
    public void toElementFinder__no_browser_has_native_support() {
        assertFilterOnlyPseudoGeneratesFilter(new CssHiddenPseudoClass(), HiddenPseudoClass.HIDDEN_FILTER);
    }

}
