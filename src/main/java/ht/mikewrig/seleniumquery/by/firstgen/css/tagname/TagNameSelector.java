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

package ht.mikewrig.seleniumquery.by.firstgen.css.tagname;

import ht.mikewrig.seleniumquery.by.common.preparser.ArgumentMap;
import ht.mikewrig.seleniumquery.by.firstgen.css.CssSelector;
import ht.mikewrig.seleniumquery.by.firstgen.xpath.component.TagComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.ElementSelector;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * $("tagname")
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class TagNameSelector implements CssSelector<ElementSelector, TagComponent> {

	@Override
	public boolean is(WebDriver driver, WebElement element, ArgumentMap argumentMap, ElementSelector elementSelector) {
		String name = elementSelector.getLocalName();
		return isBlank(name) || name.equalsIgnoreCase(element.getTagName());
	}

	@Override
	public TagComponent toXPath(ArgumentMap argumentMap, ElementSelector selector) {
		String tagName = selector.toString();
		return new TagComponent(isBlank(tagName) ? "*" : tagName);
	}

}
