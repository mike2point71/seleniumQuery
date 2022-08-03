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

package ht.mikewrig.seleniumquery.by.secondgen.finder;

import org.junit.Test;

import static ht.mikewrig.seleniumquery.by.secondgen.finder.CssFinder.fromTag;
import static ht.mikewrig.seleniumquery.by.secondgen.finder.CssFinder.universalSelector;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CssFinderTest {

    @Test(expected = IllegalArgumentException.class)
    public void cssMerge__should_throw_exception_if_both_css_havent_universalSelector_as_tag() {
        fromTag("div").merge(fromTag("span"));
    }

    @Test
    public void cssMerge__should_let_it_be_if_both_tags_are_not_universalSelector__but_are_equal() {
        CssFinder mergedCss = fromTag("div").merge(fromTag("div"));
        assertThat(mergedCss.toString(), is("div"));
    }

    @Test
    public void cssMerge__should_merge() {
        CssFinder mergedCss = fromTag("div").merge(new CssFinder(".clz"));
        assertThat(mergedCss.toString(), is("div.clz"));
    }

    @Test
    public void cssMerge__should_remove_asterisk() {
        CssFinder mergedCss = universalSelector().merge(new CssFinder(".clz"));
        assertThat(mergedCss.toString(), is(".clz"));
    }

    @Test
    public void cssMerge__when_merging_to_NULL_OBJECT_should_return_NULL_OBJECT() {
        CssFinder mergedCss = universalSelector().merge(CssFinder.CSS_NOT_NATIVELY_SUPPORTED);
        assertThat(mergedCss, is(CssFinder.CSS_NOT_NATIVELY_SUPPORTED));
    }

    @Test
    public void cssMerge__when_merging_to_NULL_OBJECT_should_return_NULL_OBJECT2() {
        CssFinder mergedCss = CssFinder.CSS_NOT_NATIVELY_SUPPORTED.merge(universalSelector());
        assertThat(mergedCss, is(CssFinder.CSS_NOT_NATIVELY_SUPPORTED));
    }

}
