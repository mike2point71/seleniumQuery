package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.selector.CompiledCssSelector;
import io.github.seleniumquery.selector.CssFilter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FirstPseudoClass implements PseudoClass {

	private static final FirstPseudoClass instance = new FirstPseudoClass();
	public static FirstPseudoClass getInstance() {
		return instance;
	}
	private FirstPseudoClass() { }

	private static final String FIRST_PSEUDO_CLASS_NO_COLON = "first";
	
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return FIRST_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		return EqPseudoClass.isEq(driver, element, pseudoClassSelector, 0);
	}

	@Override
	public CompiledCssSelector compilePseudoClass(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		// no browser supports :first natively
		CssFilter firstPseudoClassFilter = new PseudoClassFilter(getInstance(), pseudoClassSelector);
		return CompiledCssSelector.createFilterOnlySelector(firstPseudoClassFilter);
	}

}