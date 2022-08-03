/*
 * Copyright (c) 2015 seleniumQuery authors
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

import ht.mikewrig.seleniumquery.by.firstgen.xpath.component.ConditionSimpleComponent;
import ht.mikewrig.seleniumquery.utils.DriverVersionUtils;
import ht.mikewrig.seleniumquery.utils.SelectorUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * https://developer.mozilla.org/en-US/docs/Web/CSS/:enabled
 *
 * #Cross-Driver
 * HtmlUnitDriver has problems with :enabled, so we consider it can never be handler by the browser
 * by "problems" we mean it is inconsistent, changing depending on what browser it is attempting to emulate.
 *
 * @author acdcjunior
 *
 * @since 0.9.0
 */
public class EnabledPseudoClass implements PseudoClass<ConditionSimpleComponent> {

	private static final String ENABLED_PSEUDO_CLASS_NO_COLON = "enabled";

	private static final String OPTGROUP_TAG = "optgroup";
	private static final String OPTION_TAG = "option";
	private static final List<String> ENABLEABLE_TAGS = DisabledPseudoClass.DISABLEABLE_TAGS;

	public static final String ENABLED_XPATH = "(" +
            DisabledPseudoClass.DISABLEABLE_TAGS_XPATH +
        " and " +
            "not(" + DisabledPseudoClass.DISABLED_XPATH_CONDITION + ")" +
        ")";

	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return ENABLED_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}

	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		// #Cross-Driver
		// When there is a not disabled <option> under a disabled <optgroup>, HtmlUnitDriver considers
		// the <option> to be enabled, when it is not
		return element.isEnabled() && ENABLEABLE_TAGS.contains(element.getTagName());
	}


	@Override
	public ConditionSimpleComponent pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		return new ConditionSimpleComponent("[" + ENABLED_XPATH + "]");
	}

}
