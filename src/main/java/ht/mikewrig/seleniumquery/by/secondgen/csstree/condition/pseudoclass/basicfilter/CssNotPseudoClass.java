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

import static ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.basicfilter.AstCssNotPseudoClass
    .PSEUDO_PURE_NOT;

import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;

import ht.mikewrig.seleniumquery.by.firstgen.css.pseudoclasses.UnsupportedPseudoClassException;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.CssPseudoClassCondition;
import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.finderfactorystrategy.MaybeNativelySupportedPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.finder.CssFinder;
import ht.mikewrig.seleniumquery.by.secondgen.finder.XPathAndFilterFinder;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.basicfilter.AstCssNotPseudoClass;

/**
 * :not(selectorlist)
 *
 * @author acdcjunior
 * @since 0.17.0
 */
public class CssNotPseudoClass implements CssPseudoClassCondition, MaybeNativelySupportedPseudoClass {

    private final AstCssNotPseudoClass astCssNotPseudoClass;

    public CssNotPseudoClass(AstCssNotPseudoClass astCssNotPseudoClass) {
        this.astCssNotPseudoClass = astCssNotPseudoClass;
    }

    @Override
    public String pseudoClassForCSSNativeSupportCheck(WebDriver webDriver) {
        return ":"+PSEUDO_PURE_NOT+"(div)";
    }

    @Override
    public CssFinder toCssWhenNativelySupported(WebDriver webDriver) {
        String cssString = toChainedNotSelectors(webDriver);
        assertCssDoesNotContainUnsupportedSelectors(cssString);
        return new CssFinder(cssString);
    }

    private String toChainedNotSelectors(WebDriver webDriver) {
        return astCssNotPseudoClass.getArgument().stream()
            .map(cssSelector -> ":" + PSEUDO_PURE_NOT + "(" + cssSelector.toElementFinder(webDriver).toCssString() + ")")
            .collect(Collectors.joining());
    }

    private void assertCssDoesNotContainUnsupportedSelectors(String cssString) {
        if (StringUtils.containsAny(cssString, ' ', '>', '~', '+')) {
            throw new UnsupportedPseudoClassException(":not() with descendant (a b, a>b) or sibling (a+b, a~b) selectors as argument is not yet supported.");
        }
    }

    @Override
    public XPathAndFilterFinder toXPath(WebDriver webDriver) {
        String joinedXPathExps = astCssNotPseudoClass.getArgument().stream()
            .map(cssSelector -> cssSelector.toElementFinder(webDriver).getXPathAndFilterFinder().getRawXPathExpression())
            .collect(Collectors.joining(" | "));
        return XPathAndFilterFinder.pureXPath("not("+joinedXPathExps+")");
    }

}
