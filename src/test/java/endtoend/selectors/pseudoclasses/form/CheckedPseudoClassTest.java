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

package endtoend.selectors.pseudoclasses.form;

import ht.mikewrig.seleniumquery.SeleniumQueryObject;
import ht.mikewrig.seleniumquery.by.firstgen.css.pseudoclasses.PseudoClassOnlySupportedThroughIsOrFilterException;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import testinfrastructure.SecondGenSelectorSystemDetector;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

import static ht.mikewrig.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * {@link SelectedPseudoClassTest} has some :checked tests as well.
 */
public class CheckedPseudoClassTest {

	@ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

	@Test(expected = PseudoClassOnlySupportedThroughIsOrFilterException.class)
	public void checked_pseudoClass__is_not_supported() {
        SecondGenSelectorSystemDetector.assumeFirstGenSelectorSystem();
		$(":checked");
	}

	@Test
	public void checkedPseudo() {
        SecondGenSelectorSystemDetector.assumeSecondGenSelectorSystem();
		assertThat($("*").size(), is(14+7+3+7));
		assertThat($(":checked").size(), is(4+7+7));
	}

	@Test
	public void checkedPseudo_with_tag_option() {
        SecondGenSelectorSystemDetector.assumeSecondGenSelectorSystem();
		assertThat($("option:checked").size(), is(2));
	}

	@Test
	public void checked_filter() {
		assertThat($("option").filter(":checked").get(), is($("#opt1,#opt3").get()));
	}

	@Test
	public void checkedPseudo_with_tag_input() {
        SecondGenSelectorSystemDetector.assumeSecondGenSelectorSystem();
		assertThat($("input:checked").size(), is(2+7+7));
	}

	@Test
	public void checkedPseudo_with_tag_input_checkbox() {
        SecondGenSelectorSystemDetector.assumeSecondGenSelectorSystem();
		assertThat($("input[type=checkbox]:checked").size(), is(1+7));
	}

	@Test
	public void checkedPseudo_with_tag_input_radio() {
        SecondGenSelectorSystemDetector.assumeSecondGenSelectorSystem();
		assertThat($("input[type=radio]:checked").size(), is(1+7));
	}

    @Test
    public void checked_selector_with_not() {
        SecondGenSelectorSystemDetector.assumeSecondGenSelectorSystem();
    	assertThat($(":not(:checked)").size(), is(10+3)); // the number 13 is correct, in case you were wondering!!
    }

    @Test
    public void checked_selector_with_IS() {
    	assertThat($("#chk1").is(":checked"), is(true));
    	assertThat($("#chk2").is(":checked"), is(false));

    	assertThat($("#rad1").is(":checked"), is(true));
    	assertThat($("#rad2").is(":checked"), is(false));

    	assertThat($("#opt1").is(":checked"), is(true));
    	assertThat($("#opt2").is(":checked"), is(false));

    	assertThat($("#nc1").is(":checked"), is(false));
    	assertThat($("#nc2").is(":checked"), is(false));
    	assertThat($("#nc3").is(":checked"), is(false));

    	assertThat($(".c[type=checkbox]").is(":checked"), is(true));
    	assertThat($(".c[type=radio]").is(":checked"), is(true));
    }

	@Test
	public void checkedPseudo__must_be_aware_of_input_type_but_not_checked_value() {
		SeleniumQueryObject $cClass = $(".c");
		assertThat($cClass.size(), is(17));
		assertThat($cClass.filter(":checked").size(), is(14));
	}

}
