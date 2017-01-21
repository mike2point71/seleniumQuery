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

package io.github.seleniumquery.by.secondgen;

import io.github.seleniumquery.by.EnhancedElementFinder;
import io.github.seleniumquery.by.secondgen.csstree.CssSelectorList;
import io.github.seleniumquery.by.secondgen.csstree.selector.CssSelector;
import io.github.seleniumquery.by.secondgen.parser.SQParseTreeBuilder;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author acdcjunior
 * @since 0.10.0
 */
public class SecondGenEnhancedElementFinder implements EnhancedElementFinder {

    // TODO this code is not tested at all!
    @Override
    public List<WebElement> findElements(SearchContext context, String selector) {
        CssSelectorList parse = SQParseTreeBuilder.parse(selector);
        Set<WebElement> elements = new LinkedHashSet<>();
        for (CssSelector s : parse) {
            List<WebElement> elementsFound = s.toElementFinder((WebDriver) context).findWebElements(context);
            elements.addAll(elementsFound);
        }
        return new ArrayList<>(elements);
    }

}
