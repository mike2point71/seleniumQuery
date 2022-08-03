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

package ht.mikewrig.seleniumquery.functions.jquery.traversing.filtering;

import ht.mikewrig.seleniumquery.SeleniumQueryObject;
import ht.mikewrig.seleniumquery.by.SeleniumQueryBy;
import ht.mikewrig.seleniumquery.internal.SqObjectFactory;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * $("selector").not("selector")
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class NotFunction {
	
	private NotFunction() {}
	
	public static SeleniumQueryObject not(SeleniumQueryObject seleniumQueryObject, String selector) {
		List<WebElement> elements = seleniumQueryObject.get();
		List<WebElement> filteredElements = new ArrayList<>(elements);
		
		List<WebElement> elementsToExclude = seleniumQueryObject.getWebDriver().findElements(SeleniumQueryBy.byEnhancedSelector(selector));
		filteredElements.removeAll(elementsToExclude);

		return SqObjectFactory.instance().createWithInvalidSelector(seleniumQueryObject.getWebDriver(), filteredElements, seleniumQueryObject);
	}

}
