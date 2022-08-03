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

package ht.mikewrig.seleniumquery.by.secondgen.parser.translator.condition.attribute;

import static ht.mikewrig.seleniumquery.by.secondgen.parser.translator.condition.attribute.TranslatorsTestUtils
    .parseAndAssertFirstCssCondition;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.CssCondition;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.attribute.CssIdAttributeCondition;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.selector.CssConditionalSelector;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.selector.CssSelector;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.selector.CssTagNameSelector;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ParseTreeBuilder;

public class CssIdAttributeConditionTranslatorTest {

    @Test
    public void translate() {
        // given
        CssSelector cssSelector = ParseTreeBuilder.parse("#ball").firstSelector();
        assertThat(cssSelector, instanceOf(CssConditionalSelector.class));
        // when
        CssSelector sqCssSelector = ((CssConditionalSelector) cssSelector).getCssSelector();
        CssCondition cssCondition = ((CssConditionalSelector) cssSelector).getCssCondition();
        // then
        assertThat(sqCssSelector, instanceOf(CssTagNameSelector.class));
        assertThat(((CssTagNameSelector) sqCssSelector).getTagName(), is("*"));

        assertThat(cssCondition, instanceOf(CssIdAttributeCondition.class));
        assertThat(((CssIdAttributeCondition) cssCondition).getId(), is("ball"));
    }

    @Test
    public void translate__should_translate_regular_and_escaped_ids() {
        new IdVerifier().verify();
    }

    @SuppressWarnings("deprecation")
    static class IdVerifier extends ConditionTranslatorVerifier {
        IdVerifier() { super("#"); }
        @Override
        public CssCondition verifyTranslation(String actualSelector, String expectedId) {
            // given
            // selector arg
            // when
            CssIdAttributeCondition cssCondition = parseAndAssertFirstCssCondition(prefix + actualSelector, CssIdAttributeCondition.class);
            // then
            assertThat(cssCondition.getId(), is(expectedId));
            return cssCondition;
        }
    }

}
