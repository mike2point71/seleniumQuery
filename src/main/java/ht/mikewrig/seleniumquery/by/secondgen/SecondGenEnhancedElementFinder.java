/*
 * Copyright (c) 2017 seleniumQuery authors
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

package ht.mikewrig.seleniumquery.by.secondgen;

import ht.mikewrig.seleniumquery.by.EnhancedElementFinder;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.CssSelectorList;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.selector.CssSelector;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ParseTreeBuilder;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author acdcjunior
 * @since 0.10.0
 */
public class SecondGenEnhancedElementFinder implements EnhancedElementFinder {

    // TODO this code has no UTs
    @Override
    public List<WebElement> findElements(SearchContext context, String selector) {
        CssSelectorList cssSelectorList = ParseTreeBuilder.parse(selector);

        return cssSelectorList.stream()
            .flatMap(cssSelector -> cssSelector.toElementFinder((WebDriver) context).findWebElements(context).stream())
            .collect(Collectors.toList());
    }

}
