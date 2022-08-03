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

package ht.mikewrig.seleniumquery.functions.jquery.events;

import ht.mikewrig.seleniumquery.SeleniumQueryException;
import ht.mikewrig.seleniumquery.SeleniumQueryObject;
import org.apache.commons.logging.Log;
import org.openqa.selenium.WebElement;

import java.util.List;

import static java.lang.String.format;

class ClickFunctionUtils {
	
	private ClickFunctionUtils() {}

    static void reportIfThereWasAnyElementNotClicked(Log logger, SeleniumQueryObject seleniumQueryObject, List<WebElement> elements,
                                                     int numberOfNotClickedElements, Exception lastCaughtException, WebElement elementThatThrewLastCaughtException) {
        if (numberOfNotClickedElements > 0) {
            boolean noElementWasClicked = numberOfNotClickedElements == elements.size();
            if (noElementWasClicked) {
                throw new SeleniumQueryException(format("The matched set from %s only contains elements that are not visible " +
                                "and, as such, nor a user, nor seleniumQuery may interact with it.\n Referring element: %s",
                        seleniumQueryObject, toString(elementThatThrewLastCaughtException)), lastCaughtException);
            } else {
                logger.info(format("The matched set from %s contains %d hidden element(s) " +
                                "and, as such, nor a user, nor seleniumQuery may interact with it.\n Last non-clicked element: %s",
                        seleniumQueryObject, numberOfNotClickedElements, toString(elementThatThrewLastCaughtException)), lastCaughtException);
            }
        }
    }

    public static String toString(WebElement element) {
        return "\n\t id attribute: \"" + element.getAttribute("id") + "\"" +
               "\n\t class attribute: \"" + element.getAttribute("class") + "\"" +
               "\n\t name attribute: \"" + element.getAttribute("name") + "\"" +
               "\n\t tag: \"" + element.getTagName() + "\"" +
               "\n\t text: \"" + element.getText() + "\"" +
               "\n\t value attribute: " + element.getAttribute("value") + "\"" +
               "\n\t size()/dimension: " + element.getSize() +
               "\n\t isDisplayed(): " + element.isDisplayed() +
               "\n\t isEnabled(): " + element.isEnabled() +
               "\n\t toString(): " + element + "\n";
    }

}
