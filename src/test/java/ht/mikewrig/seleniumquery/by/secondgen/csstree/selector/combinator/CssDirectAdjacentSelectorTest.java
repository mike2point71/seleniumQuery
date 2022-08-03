/*
 * Copyright (c) 2016 seleniumQuery authors
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

package ht.mikewrig.seleniumquery.by.secondgen.csstree.selector.combinator;

import ht.mikewrig.seleniumquery.by.secondgen.csstree.selector.CssTagNameSelector;
import ht.mikewrig.seleniumquery.by.secondgen.finder.ElementFinder;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertThat;
import static testinfrastructure.testdouble.org.openqa.selenium.WebDriverDummy.createWebDriverDummy;

public class CssDirectAdjacentSelectorTest {

    @Test
    public void toElementFinder() {
        // given
        CssTagNameSelector aTagSelector = new CssTagNameSelector("a");
        CssTagNameSelector bTagSelector = new CssTagNameSelector("b");
        CssDirectAdjacentSelector directAdjacentSelector = new CssDirectAdjacentSelector(aTagSelector, bTagSelector);
        // when
        ElementFinder elementFinder = directAdjacentSelector.toElementFinder(createWebDriverDummy());
        // then
        assertThat(elementFinder.toCssString(), is("a+b"));
        assertThat(elementFinder.canFetchThroughCssAlone(), is(true));
        assertThat(elementFinder.getXPathExpression(), is(".//*[self::a]/following-sibling::*[position() = 1 and self::b]"));
        assertThat(elementFinder.getElementFilterList().getElementFilters(), empty());
    }

}
