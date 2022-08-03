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

package ht.mikewrig.seleniumquery.functions.jquery.manipulation;

import ht.mikewrig.seleniumquery.SeleniumQueryObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.dom.html.HTMLElement;

import java.lang.reflect.Method;
import java.util.List;

/**
 * $("selector").html()
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class HtmlFunction {

	private static final Log LOGGER = LogFactory.getLog(HtmlFunction.class);
	
	private HtmlFunction() {}

	public static String html(SeleniumQueryObject seleniumQueryObject) {
		return html(seleniumQueryObject.get());
	}

	/**
	 * Returns the HTML of the first element of the list.
	 * @param elements The list of elements.
	 * @return The HTML of the first element.
	 */
	public static String html(List<WebElement> elements) {
		if (elements.isEmpty()) {
			return null;
		}
		return html(elements.get(0));
	}

	private static String html(WebElement element) {
		return element.getAttribute("innerHTML");
	}


}
