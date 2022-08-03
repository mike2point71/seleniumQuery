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

import java.util.ArrayList;
import java.util.List;

import org.w3c.css.sac.Selector;

import ht.mikewrig.seleniumquery.by.common.preparser.ArgumentMap;
import ht.mikewrig.seleniumquery.by.common.preparser.W3cCssSelectorWithMapParser;
import ht.mikewrig.seleniumquery.by.common.preparser.w3cwithmap.W3cCssSelectorListWithMap;
import ht.mikewrig.seleniumquery.by.common.preparser.w3cwithmap.W3cCssSelectorWithMap;
import ht.mikewrig.seleniumquery.by.firstgen.css.CssSelector;
import ht.mikewrig.seleniumquery.by.firstgen.css.CssSelectorFactory;
import ht.mikewrig.seleniumquery.by.firstgen.xpath.component.TagComponent;

public class XPathComponentCompilerService {

	private XPathComponentCompilerService() {}

	public static TagComponentList compileSelectorList(String selector) {
		W3cCssSelectorListWithMap parsedSelectorList = W3cCssSelectorWithMapParser.parseSelector(selector);

    	List<TagComponent> tagComponents = new ArrayList<>(parsedSelectorList.size());
      parsedSelectorList.forEach(w3cCssSelectorWithMap -> {
          tagComponents.add(compileIntoTagComponent(w3cCssSelectorWithMap));
      });
    	return new TagComponentList(tagComponents);
	}

    private static TagComponent compileIntoTagComponent(W3cCssSelectorWithMap w3cCssSelectorWithMap) {
        return compileSelector(w3cCssSelectorWithMap.getArgumentMap(), w3cCssSelectorWithMap.getSelector());
    }

    public static TagComponent compileSelector(ArgumentMap argumentMap, Selector selector) {
        CssSelector<Selector, TagComponent> cssSelector = CssSelectorFactory.parsedSelectorToCssSelector(selector);
        return cssSelector.toXPath(argumentMap, selector);
    }

}
