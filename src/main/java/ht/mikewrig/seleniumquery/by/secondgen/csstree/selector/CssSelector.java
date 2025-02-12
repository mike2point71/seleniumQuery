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

package ht.mikewrig.seleniumquery.by.secondgen.csstree.selector;

import ht.mikewrig.seleniumquery.by.secondgen.finder.ElementFinder;
import org.openqa.selenium.WebDriver;

public interface CssSelector {

    /**
     * Converts this selector into a finder.
     * @param webDriver The driver that will be checked for native CSS support of selectors.
     * @return The finder, having the best strategy to find elements based on this selector.
     *
     * Note: this must be a {@link WebDriver} and not just a SearchContext because many selectors down the way use it to
     * check what browser is it and adapt accordingly - e.g. :checked, where PhantomJS has bugs. Also it uses to detect
     * which selectors are available natively and which are not and it caches the result per driver. If it were a SearchContext
     * it would only cache per SearchContext, meaning many elements could trigger the native-test.
     */
    ElementFinder toElementFinder(WebDriver webDriver);

    /**
     * Appends this selector's finder into a previously generated finder, which always represent
     * the leftmost part of the selector/expression, generated up to this point.
     * The csstree is traversed "in-order".
     *
     * @param leftFinder The finder generated by the leftmost part of the tree.
     * @return The leftFinder with this selector's behavior appended.
     */
    ElementFinder toElementFinder(ElementFinder leftFinder);

}
