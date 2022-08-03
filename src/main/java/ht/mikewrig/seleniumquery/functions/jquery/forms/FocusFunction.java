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

package ht.mikewrig.seleniumquery.functions.jquery.forms;

import ht.mikewrig.seleniumquery.SeleniumQueryObject;
import ht.mikewrig.seleniumquery.utils.DriverVersionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * $("selector").focus();
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class FocusFunction {
	
	private static final Log LOGGER = LogFactory.getLog(FocusFunction.class);
	
	private FocusFunction() {}

	public static SeleniumQueryObject focus(SeleniumQueryObject caller) {
		List<WebElement> elements = caller.get();
		WebDriver driver = caller.getWebDriver();
		elements.forEach(webElement -> {
			focusElement(driver, webElement);
		});
		return caller;
	}

	private static void focusElement(WebDriver driver, WebElement elementToBeFocused) {
		elementToBeFocused.sendKeys(Keys.NULL);
	}

}
