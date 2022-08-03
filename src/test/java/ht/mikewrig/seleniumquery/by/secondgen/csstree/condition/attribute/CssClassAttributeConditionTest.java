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

package ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.attribute;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import ht.mikewrig.seleniumquery.by.secondgen.finder.ElementFinder;
import ht.mikewrig.seleniumquery.by.secondgen.finder.ElementFinderUtilsTest;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.attribute.AstCssClassAttributeCondition;

public class CssClassAttributeConditionTest {

    @Test
    public void toElementFinder() {
        // given
        CssClassAttributeCondition classAttributeCondition = new CssClassAttributeCondition(new AstCssClassAttributeCondition("clazz"));
        ElementFinder previous = ElementFinderUtilsTest.UNIVERSAL_SELECTOR_FINDER;
        // when
        ElementFinder elementFinder = classAttributeCondition.toElementFinder(previous);
        // then
        assertThat(elementFinder.toCssString(), is(".clazz"));
        assertThat(elementFinder.canFetchThroughCssAlone(), is(true));
        assertThat(elementFinder.getXPathExpression(), is(".//*[contains(concat(' ', normalize-space(@class), ' '), ' clazz ')]"));
        assertThat(elementFinder.getElementFilterList().getElementFilters(), empty());
    }

}
