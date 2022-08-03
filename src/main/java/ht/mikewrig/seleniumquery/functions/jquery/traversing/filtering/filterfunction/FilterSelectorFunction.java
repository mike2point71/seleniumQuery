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

package ht.mikewrig.seleniumquery.functions.jquery.traversing.filtering.filterfunction;

import ht.mikewrig.seleniumquery.SeleniumQueryObject;
import ht.mikewrig.seleniumquery.by.SeleniumQueryInvalidBy;
import ht.mikewrig.seleniumquery.functions.jquery.traversing.filtering.IsFunction;
import org.openqa.selenium.WebElement;

import java.util.List;

import static ht.mikewrig.seleniumquery.internal.SqObjectFactory.instance;

/**
 * $("selector").filter("selector")
 *
 * @author acdcjunior
 * @since 0.11.0
 */
public class FilterSelectorFunction {

    public SeleniumQueryObject filter(SeleniumQueryObject seleniumQueryObject, String selector) {
        List<WebElement> filteredWebElements = filterElements(seleniumQueryObject, selector);
        return instance().create(seleniumQueryObject.getSeleniumQueryFunctions(),
                seleniumQueryObject.getWebDriver(),
                new SeleniumQueryInvalidBy(seleniumQueryObject.getBy(), ".filter(\""+selector+"\")"),
                filteredWebElements,
                seleniumQueryObject);
    }

    private List<WebElement> filterElements(SeleniumQueryObject seleniumQueryObject, String selector) {
        return new IsFunction.CompiledSelector(selector).filter(seleniumQueryObject.getWebDriver(),
                seleniumQueryObject.get());
    }

}
