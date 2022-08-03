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

package ht.mikewrig.seleniumquery.by.secondgen.finder;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * This object is capable of finding {@link WebElement}s through CSS on a given {@link SearchContext}.
 * <br><br>
 * Components:<br>
 *  Given the selector: {@code "* div > p:visible"}
 * <br><br>
 *  Then:<br>
 *   {@code "div > "} would be leftPart<br>
 *   {@code "p"} would be tag<br>
 *   {@code ":visible"} would be rightPart<br>
 *
 * <br><br>
 * If, during the time this finder is being built, at some point it decides the CSS Selector asked
 * by the user can't be translated directly into another CSS Selector (e.g. it is an extended selector, such as :hidden),
 * then {@link CssFinder#CSS_NOT_NATIVELY_SUPPORTED} (the "Null Object") should be returned, what will
 * mean the element finder will use XPath(+Filter) instead of CSS to fetch the elements.
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class CssFinder {

    private static final String UNIVERSAL_SELECTOR = "*";

    public static final CssFinder CSS_NOT_NATIVELY_SUPPORTED = new CssFinder("", UNIVERSAL_SELECTOR, "") {
        @Override
        public CssFinder merge(CssFinder rightCSS) {
            return this;
        }
        @Override
        public boolean canFetchAllElementsOfTheQueryByItself() {
            return false;
        }
    };

    private String leftPart;
    private String tag;
    private String rightPart;

    public CssFinder(String leftPart, String tag, String rightPart) {
        this.leftPart = leftPart;
        this.tag = tag;
        this.rightPart = rightPart;
    }

    public CssFinder(String tag, String rightPart) {
        this("", tag, rightPart);
    }

    public CssFinder(String rightPart) {
        this("", UNIVERSAL_SELECTOR, rightPart);
    }

    public static CssFinder fromTag(String tag) {
        return new CssFinder("", tag, "");
    }

    public static CssFinder universalSelector() {
        return fromTag(UNIVERSAL_SELECTOR);
    }

    private boolean hasUniversalSelector() {
        return UNIVERSAL_SELECTOR.equals(tag);
    }

    private String getLeftPart() {
        return leftPart;
    }

    public String getTag() {
        return tag;
    }

    private String getRightPart() {
        return rightPart;
    }

    @Override
    public String toString() {
        if (hasUniversalSelector() && !rightPart.isEmpty()) {
            return leftPart + rightPart;
        }
        return leftPart + tag + rightPart;
    }

    /**
     * Merges two CSS selector parts into one.
     * The current instance will be the left part of the merged selector.
     *
     * @param rightCSS The right part of the merged selector.
     * @return The two parts merged as a CSS selector.
     */
    public CssFinder merge(CssFinder rightCSS) {
        if (!rightCSS.canFetchAllElementsOfTheQueryByItself()) {
            return CSS_NOT_NATIVELY_SUPPORTED;
        }
        if (selectorsHaveDifferentTags(this, rightCSS) && noneOfTheTagsIsTheUniversalSelector(this, rightCSS)) {
            // TODO throw custom exception here, catch somewhere above so we can warn the user about useless selectors (not fail silently like jQuery)
            throw new IllegalArgumentException("The attempted selector has two element (tag) selectors at the same level. " +
                    "It is incorrect and would never fetch any elements (as no element has more than one tag).");
        }
        if (this.hasUniversalSelector()) {
            return new CssFinder(
                    this.getLeftPart(),
                    rightCSS.getTag(),
                    this.getRightPart() + rightCSS.getRightPart()
            );
        } else {
            return new CssFinder(
                    this.getLeftPart(),
                    this.getTag(),
                    this.getRightPart() + rightCSS.getRightPart()
            );
        }
    }

    private static boolean selectorsHaveDifferentTags(CssFinder leftCssSelector, CssFinder rightSCssSelector) {
        return !leftCssSelector.getTag().equals(rightSCssSelector.getTag());
    }

    private static boolean noneOfTheTagsIsTheUniversalSelector(CssFinder leftCssSelector, CssFinder rightSCssSelector) {
        return !leftCssSelector.hasUniversalSelector() && !rightSCssSelector.hasUniversalSelector();
    }

    public boolean canFetchAllElementsOfTheQueryByItself() {
        return true;
    }

    // TODO this method is not unit tested, it was inserted during refactoring, so it MAY have some test (I'm too lazy to check).
    // we need to test the IF condition, though, as it was kind of inserted during refactoring
    // so, yeah, just do the unit test for the if and then be happy!
    // TODO actually, maybe this method should be inlined (and the test moved to the class as well)
    public CssFinder combineAsLeftPart(String combinator) {
        if (this.canFetchAllElementsOfTheQueryByItself()) {
            return new CssFinder(this.toString() + combinator, UNIVERSAL_SELECTOR, "");
        }
        return CSS_NOT_NATIVELY_SUPPORTED;
    }

    // TODO dont know if this has unit tests
    public List<WebElement> findElements(SearchContext context) {
        String finalCssSelector = this.toString();
        return new By.ByCssSelector(finalCssSelector).findElements(context);
    }

}
