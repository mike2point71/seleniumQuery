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

package ht.mikewrig.seleniumquery.functions.jquery.manipulation;

import ht.mikewrig.seleniumquery.SeleniumQueryObject;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

/**
 * $("#selector").text();
 *
 * @author acdcjunior
 * @author ricardo-sc
 * @since 0.9.0
 */
public class TextFunction {

	private TextFunction() {}

	public static String text(SeleniumQueryObject seleniumQueryObject) {
		return text(seleniumQueryObject.get());
	}

	public static String text(List<WebElement> webElements) {
        return webElements.stream().map(WebElement::getText).collect(Collectors.joining(" "));
	}

}
