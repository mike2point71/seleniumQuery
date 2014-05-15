package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import static io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses.PseudoClassFilter.PSEUDO_CLASS_VALUE_NOT_USED;
import static io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses.PseudoClassFilter.SELECTOR_NOT_USED;
import io.github.seleniumquery.by.selector.CompiledSelector;
import io.github.seleniumquery.by.selector.SqCSSFilter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Selector;

/**
 * http://api.jquery.com/submit-selector/
 * 
 * @since 1.0.0
 * @author acdcjunior
 */
public class SubmitPseudoClass implements PseudoClass {
	
	private static final SubmitPseudoClass instance = new SubmitPseudoClass();
	public static SubmitPseudoClass getInstance() {
		return instance;
	}
	private SubmitPseudoClass() { }
	
	private static final String SUBMIT = "submit";
	private static final String INPUT = "input";
	private static final String BUTTON = "button";
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return SUBMIT.equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		return (
					INPUT.equals(element.getTagName()) && SUBMIT.equalsIgnoreCase(element.getAttribute("type"))
			   )
				||
			   (
				   BUTTON.equals(element.getTagName()) && (
						   									element.getAttribute("type") == null ||
						   									SUBMIT.equalsIgnoreCase(element.getAttribute("type"))
						   								  )
			  );
	}
	
	private static final SqCSSFilter submitPseudoClassFilter = new PseudoClassFilter(getInstance(),
																		SELECTOR_NOT_USED, PSEUDO_CLASS_VALUE_NOT_USED);
	@Override
	public CompiledSelector compilePseudoClass(WebDriver driver, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		// :submit is an extension selector, nobody implements it natively
		return CompiledSelector.createFilterOnlySelector(submitPseudoClassFilter);
	}
	
}