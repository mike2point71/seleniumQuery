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

import ht.mikewrig.seleniumquery.by.firstgen.css.pseudoclasses.PseudoClassOnlySupportedThroughIsOrFilterException;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

import static ht.mikewrig.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TabbablePseudoClassTest {
	
	@ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();
	
	@Test
	public void tabbable_is() {
		assertThat($("input:eq(0)").is(":tabbable"), is(true));
		assertThat($("input:eq(2)").is(":tabbable"), is(false));
	}

	@Test
	public void tabbable_filter() {
		assertThat($("input").filter(":tabbable").get(), is($(".tabbable-yes").get()));
	}

	@Test(expected = PseudoClassOnlySupportedThroughIsOrFilterException.class)
	public void tabbablePseudoClass() {
		assertThat($(":tabbable").get(), is($(".tabbable-yes").get()));
	}
	
}
