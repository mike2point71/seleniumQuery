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

package ht.mikewrig.seleniumquery.by.firstgen.css.combinators;

import ht.mikewrig.seleniumquery.by.common.preparser.ArgumentMap;
import ht.mikewrig.seleniumquery.by.firstgen.css.CssSelector;
import ht.mikewrig.seleniumquery.by.firstgen.xpath.XPathComponentCompilerService;
import ht.mikewrig.seleniumquery.by.firstgen.xpath.component.AdjacentComponent;
import ht.mikewrig.seleniumquery.by.firstgen.xpath.component.ConditionSimpleComponent;
import ht.mikewrig.seleniumquery.by.firstgen.xpath.component.TagComponent;
import ht.mikewrig.seleniumquery.utils.SelectorUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.SiblingSelector;

import static ht.mikewrig.seleniumquery.by.firstgen.css.CssSelectorMatcherService.elementMatchesSelector;

/**
 * E + F
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class DirectAdjacentCssSelector implements CssSelector<SiblingSelector, TagComponent> {

	@Override
	public boolean is(WebDriver driver, WebElement element, ArgumentMap argumentMap, SiblingSelector siblingSelector) {
		WebElement previousElement = SelectorUtils.getPreviousSibling(element);
		return aPreviousElementExists(previousElement) &&
				elementMatchesSelector(driver, previousElement, argumentMap, siblingSelector.getSelector()) &&
				elementMatchesSelector(driver, element, argumentMap, siblingSelector.getSiblingSelector());
	}

	private boolean aPreviousElementExists(WebElement previousElement) {
		return previousElement != null;
	}

	@Override
	public TagComponent toXPath(ArgumentMap argumentMap, SiblingSelector siblingSelector) {
		TagComponent previousCompiledExpression = XPathComponentCompilerService.compileSelector(argumentMap, siblingSelector.getSelector());
		TagComponent siblingSelectorCompiledAdjacentExpression = XPathComponentCompilerService.compileSelector(argumentMap, siblingSelector.getSiblingSelector());

		ConditionSimpleComponent positionOne = new ConditionSimpleComponent("[position() = 1]");
		TagComponent siblingAtPositionOne = siblingSelectorCompiledAdjacentExpression.cloneAndCombineTo(positionOne);
		
		return AdjacentComponent.combine(previousCompiledExpression, siblingAtPositionOne);
	}

}
