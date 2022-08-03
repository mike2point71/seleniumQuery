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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

/**
 * <pre>
 * $("selector").submit();
 * </pre>
 *
 * @author acdcjunior
 *
 * @since 0.9.0
 */
public class SubmitFunction {

    private static final Log LOGGER = LogFactory.getLog(SubmitFunction.class);
    
    private SubmitFunction() {}

    public static void submit(SeleniumQueryObject seleniumQueryObject) {
        for (WebElement webElement : seleniumQueryObject) {
            try {
                webElement.submit();
            } catch (StaleElementReferenceException e) {
                LOGGER.warn(".submit() was called on an element that is not present anymore. Ignoring further elements on the matched set. (Maybe the .submit() execution on previous elements changed the page.)");
                LOGGER.debug(".submit() exception follows.", e);
                return;
            }
        }
    }

}
