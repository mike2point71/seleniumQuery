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

package ht.mikewrig.seleniumquery.by.firstgen.css.pseudoclasses;

import ht.mikewrig.seleniumquery.by.common.elementfilter.ElementFilter;
import ht.mikewrig.seleniumquery.by.firstgen.xpath.component.ConditionSimpleComponent;
import ht.mikewrig.seleniumquery.utils.SelectorUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static ht.mikewrig.seleniumquery.by.firstgen.css.pseudoclasses.PseudoClassOnlySupportedThroughIsOrFilterException.pseudoClassNotSupportedWhenUsedDirectly;

/**
 * :hidden
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class HiddenPseudoClass implements PseudoClass<ConditionSimpleComponent> {
	
	private static final String HIDDEN_PSEUDO_CLASS_NO_COLON = "hidden";

    public static final ElementFilter HIDDEN_FILTER = new PseudoClassFilter(new HiddenPseudoClass());

	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return HIDDEN_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}

	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		return !SelectorUtils.isVisible(element);
	}

	@Override
	public ConditionSimpleComponent pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		pseudoClassNotSupportedWhenUsedDirectly(HIDDEN_PSEUDO_CLASS_NO_COLON);

		// we can't use XPath because it can't see the styles affecting the element's classes, which can pretty much
		// turn any element, including <html> itself or <head>, visible.
		return new ConditionSimpleComponent(HIDDEN_FILTER);
	}
	
}
