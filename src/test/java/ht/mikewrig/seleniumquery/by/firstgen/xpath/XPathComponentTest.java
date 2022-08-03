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

package ht.mikewrig.seleniumquery.by.firstgen.xpath;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.w3c.css.sac.SelectorList;

import ht.mikewrig.seleniumquery.by.common.preparser.W3cCssSelectorWithMapParser;
import ht.mikewrig.seleniumquery.by.common.preparser.w3cwithmap.W3cCssSelectorListWithMap;
import ht.mikewrig.seleniumquery.by.firstgen.xpath.component.TagComponent;

public class XPathComponentTest {

    @Test
    public void toXPath__id() {
        TagComponentList compileSelectorList = XPathComponentCompilerService.compileSelectorList("#ball");
        assertThat(compileSelectorList.toXPath(), is("(.//*[@id = 'ball'])"));
    }

    @Test
    public void toXPath__and_conditional() {
        TagComponentList compileSelectorList = XPathComponentCompilerService.compileSelectorList(".a.b");
        assertThat(compileSelectorList.toXPath(), is("(.//*[contains(concat(' ', normalize-space(@class), ' '), ' a ') and contains(concat(' ', normalize-space(@class), ' '), ' b ')])"));
    }

    @Test(expected = UnsupportedConditionalSelector.class)
    public void toXPath__not_body_div_span() {
        TagComponent xPathExpr = selectorToExpression("span:not(body div span)");
        String xPath = xPathExpr.toXPath();
        assertThat(xPath, is("(.//span[not(local-name() = 'span' and ancestor::div[ancestor::body])])"));
    }

    @Test
    public void toXPath__id_text() {
        TagComponent xPathExpr = selectorToExpression("#myID :text");
        String xPath = xPathExpr.toXPath();
        assertThat(xPath, is("(.//*[@id = 'myID']//*[self::input and (translate(@type,'TEXT','text') = 'text' or not(@type))])"));
    }

    @Test
    public void toXPath__only_child() {
        TagComponent xPathExpr = selectorToExpression("#idz a:only-child");
        String xPath = xPathExpr.toXPath();
        assertThat(xPath, is("(.//*[@id = 'idz']//*[self::a and ../*[last() = 1]])"));
    }

    public static TagComponent selectorToExpression(String selector) {
        W3cCssSelectorListWithMap cssParsedSelectors = W3cCssSelectorWithMapParser.parseSelector(selector);
        SelectorList selectorList = cssParsedSelectors.getSelectorList();
        return XPathComponentCompilerService.compileSelector(cssParsedSelectors.getArgumentMap(), selectorList.item(0));
    }

    @Test
    public void toXPathCondition__first() {
        TagComponent xPathExpr = selectorToExpression(":first");
        String xPathCondition = xPathExpr.toXPathCondition();
        assertThat(xPathCondition, is("position() = 1"));
    }

    @Test
    public void toXPathCondition__div_first() {
        TagComponent xPathExpr = selectorToExpression("div:first");
        String xPathCondition = xPathExpr.toXPathCondition();
        assertThat(xPathCondition, is("local-name() = 'div' and position() = 1"));
    }

    @Test
    public void toXPathCondition__class() {
        TagComponent xPathExpr = selectorToExpression(".cls");
        String xPathCondition = xPathExpr.toXPathCondition();
        assertThat(xPathCondition, is("contains(concat(' ', normalize-space(@class), ' '), ' cls ')"));
    }

    @Test
    public void toXPathCondition__escaped_class() {
        TagComponent xPathExpr = selectorToExpression(".foo\\:bar");
        String xPathCondition = xPathExpr.toXPathCondition();
        assertThat(xPathCondition, is("contains(concat(' ', normalize-space(@class), ' '), ' foo:bar ')"));
    }

}
