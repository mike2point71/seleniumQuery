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

package ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.basicfilter;

import static ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.PseudoClassAssertFinderUtils.AssertPseudoClass
    .assertPseudoClass;
import static ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.PseudoClassAssertFinderUtils
    .assertPseudoClassHasElementFinderWhenNativelySupported;
import static ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.PseudoClassTestUtils.assertQueriesOnSelector;
import static org.hamcrest.collection.IsEmptyCollection.empty;

import org.junit.Test;

import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.PseudoClassAssertFinderUtils;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ParseTreeBuilder;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.basicfilter.AstCssNotPseudoClass;

public class CssNotPseudoClassTest {

    private static final String NOT_PSEUDO = ":not";

    @Test
    public void translate() {
        assertQueriesOnSelector(NOT_PSEUDO).withSelectorArguments().yieldFunctionalPseudoclassWithCorrectlyTranslatedSelectorArguments(
            CssNotPseudoClass.class,
            "Impossible to parse selector \":not\"",
            "Error while parsing pseudo-class `:not("
        );
    }

    @Test
    public void toElementFinder__when_driver_has_native_support() {
        assertPseudoClassHasElementFinderWhenNativelySupported(
                ":not(div)",
                new CssNotPseudoClass(new AstCssNotPseudoClass(ParseTreeBuilder.parse("span"))), // :not(span)
                ":not(span)",
                PseudoClassAssertFinderUtils.PURE_CSS_IS_SUPPORTED,
                ".//*[not(self::span)]",
                empty()
        );
    }

    @Test
    public void toElementFinder__when_driver_does_NOT_have_native_support() {
        CssNotPseudoClass notTag = new CssNotPseudoClass(new AstCssNotPseudoClass(ParseTreeBuilder.parse("tag"))); // :not(tag)
        assertPseudoClass(notTag).whenNotNativelySupported().translatesToPureXPath(".//*[not(self::tag)]");
    }

    @Test
    public void toElementFinder__when_driver_has_native_support_BUT_inner_css_can_be_separated() {
        assertPseudoClassHasElementFinderWhenNativelySupported(
                ":not(div)",
                new CssNotPseudoClass(new AstCssNotPseudoClass(ParseTreeBuilder.parse("h1,h2"))), // :not(h1,h2)
                ":not(h1):not(h2)",
                PseudoClassAssertFinderUtils.PURE_CSS_IS_SUPPORTED,
                ".//*[not(self::h1 | self::h2)]",
                empty()
        );
    }

    /*
     * The selectors below are much more complicated than I anticipated. When inside the not(),
     * the XPath conditional must be "reversed" to have the same semantics of the :not() in CSS.
     * e.g. :not(h3 h4) in CSS does not become not(h3//h4) in XPath, it becomes not(h4 and ancestor::h3
     *
     * Due to that, below I just leave a sketch of what the expected XPath conditionals
     * inside the not() would be.
     *
     * The tests just expect that an exception is thrown, letting the user know that is not
     * a supported selector.
     * If throwing an exception was the final expected behavior, these tests could be much simpler, of course. I just
     * left them like this because I expect to get back to them as soon as possible.
     */
    @Test(expected = ht.mikewrig.seleniumquery.by.firstgen.css.pseudoclasses.UnsupportedPseudoClassException.class)
    public void toElementFinder__not_and_general_ancestor() {
        assertPseudoClassHasElementFinderWhenNativelySupported(
                ":not(div)",
                new CssNotPseudoClass(new AstCssNotPseudoClass(ParseTreeBuilder.parse("h3 h4"))), // :not(h3 h4)
                "*",
                PseudoClassAssertFinderUtils.PURE_CSS_IS_NOT_SUPPORTED,
                ".//*[not(self::h4 and ANY-ANCESTOR/*[self::h3])]",
                empty()
        );
    }

    @Test(expected = ht.mikewrig.seleniumquery.by.firstgen.css.pseudoclasses.UnsupportedPseudoClassException.class)
    public void toElementFinder__not_and_direct_ancestor() {
        assertPseudoClassHasElementFinderWhenNativelySupported(
                ":not(div)",
                new CssNotPseudoClass(new AstCssNotPseudoClass(ParseTreeBuilder.parse("h3>h4"))), // :not(h3>h4)
                "*",
                PseudoClassAssertFinderUtils.PURE_CSS_IS_NOT_SUPPORTED,
                ".//*[not(self::h4 and DIRECT-ANCESTOR/*[self::h3])]",
                empty()
        );
    }

    @Test(expected = ht.mikewrig.seleniumquery.by.firstgen.css.pseudoclasses.UnsupportedPseudoClassException.class)
    public void toElementFinder__not_and_direct_sibling() {
        assertPseudoClassHasElementFinderWhenNativelySupported(
                ":not(div)",
                new CssNotPseudoClass(new AstCssNotPseudoClass(ParseTreeBuilder.parse("h3+h4"))), // :not(h3+h4)
                "*",
                PseudoClassAssertFinderUtils.PURE_CSS_IS_NOT_SUPPORTED,
                ".//*[not(self::h4 and DIRECT-SIBLING/*[self::h3])]",
                empty()
        );
    }

    @Test(expected = ht.mikewrig.seleniumquery.by.firstgen.css.pseudoclasses.UnsupportedPseudoClassException.class)
    public void toElementFinder__not_and_general_sibling() {
        assertPseudoClassHasElementFinderWhenNativelySupported(
                ":not(div)",
                new CssNotPseudoClass(new AstCssNotPseudoClass(ParseTreeBuilder.parse("h3+h4"))), // :not(h3~h4)
                "*",
                PseudoClassAssertFinderUtils.PURE_CSS_IS_NOT_SUPPORTED,
                ".//*[not(self::h4 and GENERAL-SIBLING/*[self::h3])]",
                empty()
        );
    }

}
