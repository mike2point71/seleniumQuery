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

package ht.mikewrig.seleniumquery.internal;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.common.base.Predicate;
import ht.mikewrig.seleniumquery.SeleniumQueryFluentFunction;
import ht.mikewrig.seleniumquery.SeleniumQueryObject;
import ht.mikewrig.seleniumquery.functions.SeleniumQueryFunctions;
import ht.mikewrig.seleniumquery.functions.as.SeleniumQueryPlugin;
import ht.mikewrig.seleniumquery.functions.as.StandardPlugins;
import ht.mikewrig.seleniumquery.functions.sq.StreamFunction;
import ht.mikewrig.seleniumquery.internal.fluentfunctions.SqFluentFunction;
import ht.mikewrig.seleniumquery.internal.fluentfunctions.assertthat.FluentAssertThat;
import ht.mikewrig.seleniumquery.internal.fluentfunctions.waituntil.FluentWaitUntil;
import ht.mikewrig.seleniumquery.internal.functions.seleniumquery.EvalFunction;
import ht.mikewrig.seleniumquery.utils.ListUtils;

/**
 * Main implementation of {@link SeleniumQueryObject}.
 *
 * @author acdcjunior
 * @author ricardo-sc
 * @since 0.14.0
 */
class SqObject
    implements
    SeleniumQueryObject,
    InternalTargetableSqObject,
    EvalFunction {

    static final SeleniumQueryObject NOT_BUILT_BASED_ON_A_PREVIOUS_OBJECT = null;

	private SeleniumQueryFunctions seleniumQueryFunctions;

	private WebDriver driver;

    private By by;
	private List<WebElement> elements;

	/**
	 * The previous (or "parent") element, meaning this SeleniumQueryObject was created as result
	 * of calling a "destructive" function (such as {@link #not(String)}) on that element.<br>
	 * This property is retrieved by a call to {@link #end()}.
	 *
	 * @since 0.9.0
	 */
	private SeleniumQueryObject previous;

    SqObject(SeleniumQueryFunctions seleniumQueryFunctions, WebDriver driver, By by) {
        this(seleniumQueryFunctions, driver, by, driver.findElements(by), NOT_BUILT_BASED_ON_A_PREVIOUS_OBJECT);
	}

    SqObject(SeleniumQueryFunctions seleniumQueryFunctions, WebDriver driver, By by, List<WebElement> webElements, SeleniumQueryObject previous) {
        this.seleniumQueryFunctions = seleniumQueryFunctions;
		this.driver = driver;
		this.by = by;
		this.elements = ListUtils.toImmutableRandomAccessList(webElements);
		this.previous = previous;
	}

    @Override
    public SeleniumQueryObject target() {
        return this;
    }

    @Override
    public SeleniumQueryObject refresh() {
        this.elements = ListUtils.toImmutableRandomAccessList(driver.findElements(by));
        return this;
    }

    @Override
    public final SeleniumQueryFluentFunction waitUntil() {
		return new SqFluentFunction(this, new FluentWaitUntil());
	}

    @Override
    public SeleniumQueryFluentFunction assertThat() {
        return new SqFluentFunction(this, new FluentAssertThat());
    }

    @Override
    public final SeleniumQueryFluentFunction waitUntil(long waitUntilTimeout) {
		return new SqFluentFunction(this, new FluentWaitUntil(waitUntilTimeout));
	}

	@Override
    public final SeleniumQueryFluentFunction waitUntil(long waitUntilTimeout, long waitUntilPollingInterval) {
		return new SqFluentFunction(this, new FluentWaitUntil(waitUntilTimeout, waitUntilPollingInterval));
	}

	@Override
    public StandardPlugins as() {
		return new StandardPlugins(this);
	}

	@Override
    public <PLUGIN> PLUGIN as(SeleniumQueryPlugin<PLUGIN> pluginFunction) {
		return pluginFunction.as(this);
	}

	@Override
	public Iterator<WebElement> iterator() {
		return this.elements.iterator();
	}

	@Override
    public WebDriver getWebDriver() {
		return this.driver;
	}

	@Override
    public By getBy() {
		return this.by;
	}

    @Override
    public SeleniumQueryFunctions getSeleniumQueryFunctions() {
        return seleniumQueryFunctions;
    }

	@Override
    public int size() {
		return this.elements.size();
	}

	@Override
    public SeleniumQueryObject not(String selector) {
		return seleniumQueryFunctions.notSelector(this, selector);
	}

	@Override
    public SeleniumQueryObject first() {
		return seleniumQueryFunctions.first(this);
	}

	@Override
    public SeleniumQueryObject last() {
		return seleniumQueryFunctions.last(this);
	}

	@Override
    public SeleniumQueryObject eq(int index) {
		return seleniumQueryFunctions.eqIndex(this, index);
	}

	@Override
    public String text() {
		return seleniumQueryFunctions.text(this);
	}

	@Override
    public SeleniumQueryObject click() {
		return seleniumQueryFunctions.click(this);
	}

    @Override
    public SeleniumQueryObject waitViewClick() {
        return seleniumQueryFunctions.waitViewClick(this);
    }

	@Override
	public SeleniumQueryObject dblclick() {
		return seleniumQueryFunctions.dblclick(this);
	}

	@Override
    public SeleniumQueryObject val(String value) {
        return seleniumQueryFunctions.valueWrite(this, value);
	}

    @Override
    public SeleniumQueryObject val(Number value) {
        return seleniumQueryFunctions.valueWrite(this, value);
	}

    @Override
    public String val() {
		return seleniumQueryFunctions.valueRead(this);
	}

    @Override
    public SeleniumQueryObject end() {
		return this.previous;
	}

	@Override
    public SeleniumQueryObject find(String selector) {
		return seleniumQueryFunctions.findSelector(this, selector);
	}

	@Override
    public String attr(String attributeName) {
		return seleniumQueryFunctions.attributeRead(this, attributeName);
	}

	@Override
    public SeleniumQueryObject attr(String attributeName, Object value) {
		return seleniumQueryFunctions.attributeWrite(this, attributeName, value);
	}

	@Override
    public <T> T prop(String propertyName) {
		return seleniumQueryFunctions.propertyRead(this, propertyName);
	}

    @Override
    public SeleniumQueryObject prop(String propertyName, Object value) {
		return seleniumQueryFunctions.propertyWrite(this, propertyName, value);
	}

    @Override
    public WebElement get(int index) {
		return seleniumQueryFunctions.getIndex(this, index);
	}

	@Override
    public List<WebElement> get() {
		return this.elements;
	}

	@Override
    public SeleniumQueryObject removeAttr(String attributeNames) {
		return seleniumQueryFunctions.removeAttribute(this, attributeNames);
	}

	@Override
    public String html() {
		return seleniumQueryFunctions.html(this);
	}

	@Override
    public boolean is(String selector) {
		return seleniumQueryFunctions.isSelector(this, selector);
	}

	@Override
    public boolean hasClass(String className) {
		return seleniumQueryFunctions.hasClass(this, className);
	}

	@Override
    public WebElement[] toArray() {
		return seleniumQueryFunctions.toArray(this);
	}

	@Override
    public SeleniumQueryObject closest(String selector) {
		return seleniumQueryFunctions.closestSelector(this, selector);
	}

	@Override
    public SeleniumQueryObject focus() {
		return seleniumQueryFunctions.focus(this);
	}

	@Override
    public SeleniumQueryObject children() {
		return seleniumQueryFunctions.children(this);
	}

	@Override
    public SeleniumQueryObject children(String selector) {
		return seleniumQueryFunctions.childrenSelector(this, selector);
	}

	@Override
    public SeleniumQueryObject parent() {
		return seleniumQueryFunctions.parent(this);
	}

	@Override
    public SeleniumQueryObject parent(String selector) {
		return seleniumQueryFunctions.parentSelector(this, selector);
	}

	@Override
    public SeleniumQueryObject submit() {
		return seleniumQueryFunctions.submit(this);
	}

	@Override
    @SuppressWarnings("unused")
	public SeleniumQueryObject selectOptionByVisibleText(String text) {
		return as().select().selectByVisibleText(text);
	}

	@Override
    @SuppressWarnings("unused")
	public SeleniumQueryObject selectOptionByValue(String value) {
		return as().select().selectByValue(value);
	}

	@Override
	public String toString() {
		return this.by.toString();
	}

    @Override
    @SuppressWarnings("Guava")
    public SeleniumQueryObject filter(Predicate<WebElement> filterFunction) {
		return seleniumQueryFunctions.filterPredicate(this, filterFunction == null ? null : filterFunction::apply);
	}

    @Override
    public SeleniumQueryObject filter(String selector) {
		return seleniumQueryFunctions.filterSelector(this, selector);
	}

    @Override
    public SeleniumQueryObject each(EachFunction function) {
		return seleniumQueryFunctions.each(this, function);
	}

    @Override
    public Stream<WebElement> stream() {
        return new StreamFunction().apply(this);
    }

}
