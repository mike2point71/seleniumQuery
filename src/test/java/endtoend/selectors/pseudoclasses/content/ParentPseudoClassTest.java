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

package endtoend.selectors.pseudoclasses.content;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

import static ht.mikewrig.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ParentPseudoClassTest {

	@ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();
	
	// http://jsbin.com/lutim/6/edit
	@Test
	public void parentPseudoClass() {
		assertThat($("#d1").is(":parent"), is(true));
		assertThat($("#d2").is(":parent"), is(false));

		assertThat($("#d3").is(":parent"), is(true));
		assertThat($("#d4").is(":parent"), is(true));

		assertThat($("#d5").is(":parent"), is(true));

		assertThat($("#d10").is(":parent"), is(true));
		assertThat($("#d11").is(":parent"), is(false));
		assertThat($("#d12").is(":parent"), is(true));
		assertThat($("#d13").is(":parent"), is(false));
		
		assertThat($("#d14").is(":parent"), is(true));
	}
	
}
