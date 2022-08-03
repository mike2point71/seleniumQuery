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

package ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.childfilter;

import org.openqa.selenium.WebDriver;

import ht.mikewrig.seleniumquery.by.secondgen.csstree.condition.pseudoclass.finderfactorystrategy.MaybeNativelySupportedPseudoClass;
import ht.mikewrig.seleniumquery.by.secondgen.finder.CssFinder;
import ht.mikewrig.seleniumquery.by.secondgen.finder.XPathAndFilterFinder;
import ht.mikewrig.seleniumquery.by.secondgen.parser.ast.condition.pseudoclass.childfilter.AstCssNthChildPseudoClass;

/**
 * :nth-child()
 * https://api.jquery.com/nth-child-selector/
 * https://developer.mozilla.org/pt-BR/docs/Web/CSS/:nth-child
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class CssNthChildPseudoClass implements MaybeNativelySupportedPseudoClass {

    private final AstCssNthChildPseudoClass astCssNthChildPseudoClass;

    public CssNthChildPseudoClass(AstCssNthChildPseudoClass astCssNthChildPseudoClass) {
        this.astCssNthChildPseudoClass = astCssNthChildPseudoClass;
    }

    @Override
    public String pseudoClassForCSSNativeSupportCheck(WebDriver webDriver) {
        return ":"+AstCssNthChildPseudoClass.PSEUDO+"(1)";
    }

    @Override
    public CssFinder toCssWhenNativelySupported(WebDriver webDriver) {
        NthArgument nthArgument = getNthChildArgument();
        return new CssFinder(":"+AstCssNthChildPseudoClass.PSEUDO+"("+nthArgument.toCSS()+")");
    }

    @Override
    public XPathAndFilterFinder toXPath(WebDriver webDriver) {
        NthArgument nthArgument = getNthChildArgument();
        return XPathAndFilterFinder.pureXPath(nthArgument.toXPath("position()"));
    }

    private NthArgument getNthChildArgument() {
        return new NthArgument(astCssNthChildPseudoClass.getArgument());
    }

}
