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

package ht.mikewrig.seleniumquery.functions.jquery.traversing.treetraversal;

import ht.mikewrig.seleniumquery.SeleniumQueryObject;
import ht.mikewrig.seleniumquery.internal.SqObjectFactory;
import ht.mikewrig.seleniumquery.utils.SelectorUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <pre>
 * $("selector").parent()
 * $("selector").parent(selector)
 * </pre>
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class ParentFunction {

	private static final String NO_FILTER_SELECTOR_PROVIDED = null;
	
	private ParentFunction() {}

	public static SeleniumQueryObject parent(SeleniumQueryObject caller) {
		return parent(caller, NO_FILTER_SELECTOR_PROVIDED);
	}

	public static SeleniumQueryObject parent(SeleniumQueryObject caller, String selector) {
		Set<WebElement> alreadyInsertedParents = new HashSet<>();
		WebDriver callerWebDriver = caller.getWebDriver();

		List<WebElement> elements = caller.get();
		List<WebElement> parents = new ArrayList<>(elements.size());
		for (WebElement element : elements) {
			WebElement parentElement = SelectorUtils.parent(element);
			if (parentElement != null && !alreadyInsertedParents.contains(parentElement) && parentMatchesSelector(callerWebDriver, selector, parentElement)) {
				parents.add(parentElement);
				alreadyInsertedParents.add(parentElement);
			}
		}
		return SqObjectFactory.instance().createWithInvalidSelector(callerWebDriver, parents, caller);
	}

	private static boolean parentMatchesSelector(WebDriver callerWebDriver, String selector, WebElement parentElement) {
		//noinspection StringEquality
		return selector == NO_FILTER_SELECTOR_PROVIDED || createParentElement(callerWebDriver, parentElement).is(selector);
	}

	private static SeleniumQueryObject createParentElement(WebDriver callerWebDriver, WebElement parentElement) {
		return SqObjectFactory.instance().createWithInvalidSelectorAndNoPrevious(callerWebDriver, parentElement);
	}

}
