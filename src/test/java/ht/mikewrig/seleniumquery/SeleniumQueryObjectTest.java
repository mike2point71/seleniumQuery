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

package ht.mikewrig.seleniumquery;

import org.junit.Test;
import org.openqa.selenium.WebElement;
import testinfrastructure.testdouble.PseudoTestDoubleException;
import testinfrastructure.testdouble.ht.mikewrig.seleniumquery.SeleniumQueryObjectMother;
import testinfrastructure.testdouble.ht.mikewrig.seleniumquery.functions.SeleniumQueryFunctionsMock;

import java.util.function.Predicate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;
import static testinfrastructure.testdouble.ht.mikewrig.seleniumquery.SeleniumQueryObjectDummy.createSeleniumQueryObjectDummy;
import static testinfrastructure.testdouble.ht.mikewrig.seleniumquery.SeleniumQueryObjectMother.createStubSeleniumQueryObjectWithSeleniumQueryFunctions;
import static testinfrastructure.testdouble.ht.mikewrig.seleniumquery.functions.MethodMockConfiguration.configureReturnValue;

public class SeleniumQueryObjectTest {

    private static SeleniumQueryFunctionsMock createMockSeleniumQueryFunctions() {
        return new SeleniumQueryFunctionsMock();
    }

    @Test
    public void propRead() {
        // given
        SeleniumQueryFunctionsMock seleniumQueryFunctions = new SeleniumQueryFunctionsMock();
        SeleniumQueryObject seleniumQueryObject = createStubSeleniumQueryObjectWithSeleniumQueryFunctions(seleniumQueryFunctions);

        String propertyName = "propertyName";
        String configuredPropertyValue = "propertyValue";
        seleniumQueryFunctions.propertyReadMethod = configureReturnValue(configuredPropertyValue).forArgs(seleniumQueryObject, propertyName);
        // when
        Object returnedPropertyValue = seleniumQueryObject.prop(propertyName);
        // then
        assertThat(returnedPropertyValue, is(configuredPropertyValue));
    }

    @Test
    public void propWrite() {
        // given
        SeleniumQueryFunctionsMock seleniumQueryFunctions = new SeleniumQueryFunctionsMock();
        SeleniumQueryObject seleniumQueryObject = createStubSeleniumQueryObjectWithSeleniumQueryFunctions(seleniumQueryFunctions);

        String propertyName = "propertyName";
        String propertyValue = "propertyValue";
        SeleniumQueryObject configuredReturningObject = createSeleniumQueryObjectDummy();
        seleniumQueryFunctions.propertyWriteMethod = configureReturnValue(configuredReturningObject).forArgs(seleniumQueryObject, propertyName, propertyValue);
        // when
        SeleniumQueryObject returnedObject = seleniumQueryObject.prop(propertyName, propertyValue);
        // then
        assertThat(returnedObject, is(configuredReturningObject));
    }

    @Test
    public void valRead() {
        // given
        SeleniumQueryFunctionsMock seleniumQueryFunctions = new SeleniumQueryFunctionsMock();
        SeleniumQueryObject seleniumQueryObject = createStubSeleniumQueryObjectWithSeleniumQueryFunctions(seleniumQueryFunctions);

        String configuredValue = "configuredValue";
        seleniumQueryFunctions.valueReadMethod = configureReturnValue(configuredValue).forArgs(seleniumQueryObject);
        // when
        String returnedValue = seleniumQueryObject.val();
        // then
        assertThat(returnedValue, is(configuredValue));
    }

    @Test
    public void valWriteString() {
        // given
        SeleniumQueryFunctionsMock seleniumQueryFunctions = createMockSeleniumQueryFunctions();
        SeleniumQueryObject seleniumQueryObject = createStubSeleniumQueryObjectWithSeleniumQueryFunctions(seleniumQueryFunctions);

        String propertyValue = "propertyValue";
        SeleniumQueryObject configuredReturningObject = createSeleniumQueryObjectDummy();
        seleniumQueryFunctions.valueWriteStringMethod = configureReturnValue(configuredReturningObject).forArgs(seleniumQueryObject, propertyValue);
        // when
        SeleniumQueryObject returnedObject = seleniumQueryObject.val(propertyValue);
        // then
        assertThat(returnedObject, is(configuredReturningObject));
    }

    @Test
    public void valWriteNumber() {
        // given
        SeleniumQueryFunctionsMock seleniumQueryFunctions = createMockSeleniumQueryFunctions();
        SeleniumQueryObject seleniumQueryObject = createStubSeleniumQueryObjectWithSeleniumQueryFunctions(seleniumQueryFunctions);

        Number propertyValue = 1.0;
        SeleniumQueryObject configuredReturningObject = createSeleniumQueryObjectDummy();
        seleniumQueryFunctions.valueWriteNumberMethod = configureReturnValue(configuredReturningObject).forArgs(seleniumQueryObject, propertyValue);
        // when
        SeleniumQueryObject returnedObject = seleniumQueryObject.val(propertyValue);
        // then
        assertThat(returnedObject, is(configuredReturningObject));
    }

    @Test
    public void filterPredicateFunction() {
        // given
        SeleniumQueryFunctionsMock seleniumQueryFunctions = createMockSeleniumQueryFunctions();
        SeleniumQueryObject seleniumQueryObject = createStubSeleniumQueryObjectWithSeleniumQueryFunctions(seleniumQueryFunctions);
        SeleniumQueryObject configuredReturningObject = createSeleniumQueryObjectDummy();

        @SuppressWarnings("Guava")
        com.google.common.base.Predicate<WebElement> stubFilterFunction = input -> { throw new PseudoTestDoubleException(); };
        Predicate<WebElement> stubFilterFunctionApplyMethod = new Predicate<WebElement>() {
            @Override
            public boolean test(WebElement webElement) { return false; }
            @SuppressWarnings({"unchecked", "EqualsWhichDoesntCheckParameterClass"}) @Override
            public boolean equals(Object actual) {
                try {
                    ((Predicate<WebElement>) actual).test(null);
                    fail("stub is configured to throw exception");
                } catch (PseudoTestDoubleException ignored) {
                    // exception is expected
                }
                return true;
            }
        };
        seleniumQueryFunctions.filterPredicateMethod = configureReturnValue(configuredReturningObject).forArgs(seleniumQueryObject, stubFilterFunctionApplyMethod);
        // when
        SeleniumQueryObject returnedObject = seleniumQueryObject.filter(stubFilterFunction);
        // then
        assertThat(returnedObject, is(configuredReturningObject));
    }

    @Test
    public void filterSelectorFunction() {
        // given
        SeleniumQueryFunctionsMock seleniumQueryFunctions = createMockSeleniumQueryFunctions();
        SeleniumQueryObject seleniumQueryObject = createStubSeleniumQueryObjectWithSeleniumQueryFunctions(seleniumQueryFunctions);
        SeleniumQueryObject configuredReturningObject = createSeleniumQueryObjectDummy();

        String selector = "selector";
        seleniumQueryFunctions.filterSelectorMethod = configureReturnValue(configuredReturningObject).forArgs(seleniumQueryObject, selector);
        // when
        SeleniumQueryObject returnedObject = seleniumQueryObject.filter(selector);
        // then
        assertThat(returnedObject, is(configuredReturningObject));
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    public void isSelectorFunction() {
        // given
        SeleniumQueryFunctionsMock seleniumQueryFunctions = createMockSeleniumQueryFunctions();
        SeleniumQueryObject seleniumQueryObject = createStubSeleniumQueryObjectWithSeleniumQueryFunctions(seleniumQueryFunctions);
        boolean configuredReturningValue = true;

        String configuredSelector = "selector";
        seleniumQueryFunctions.isSelectorMethod = configureReturnValue(configuredReturningValue).forArgs(seleniumQueryObject, configuredSelector);
        // when
        boolean returnedValue = seleniumQueryObject.is(configuredSelector);
        // then
        assertThat(returnedValue, is(configuredReturningValue));
    }

    @Test
    public void eachFunction() {
        // given
        SeleniumQueryFunctionsMock seleniumQueryFunctions = createMockSeleniumQueryFunctions();
        SeleniumQueryObject seleniumQueryObject = createStubSeleniumQueryObjectWithSeleniumQueryFunctions(seleniumQueryFunctions);
        SeleniumQueryObject configuredReturningObject = createSeleniumQueryObjectDummy();

        SeleniumQueryObject.EachFunction eachFunctionArgument = SeleniumQueryObjectMother.createDummyEachFunction();
        seleniumQueryFunctions.eachMethod = configureReturnValue(configuredReturningObject).forArgs(seleniumQueryObject, eachFunctionArgument);
        // when
        SeleniumQueryObject returnedObject = seleniumQueryObject.each(eachFunctionArgument);
        // then
        assertThat(returnedObject, is(configuredReturningObject));
    }

    @Test
    public void clickFunction() {
        // given
        SeleniumQueryFunctionsMock seleniumQueryFunctions = createMockSeleniumQueryFunctions();
        SeleniumQueryObject seleniumQueryObject = createStubSeleniumQueryObjectWithSeleniumQueryFunctions(seleniumQueryFunctions);

        SeleniumQueryObject configuredReturningObject = createSeleniumQueryObjectDummy();
        seleniumQueryFunctions.clickMethod = configureReturnValue(configuredReturningObject).forArgs(seleniumQueryObject);
        // when
        SeleniumQueryObject returnedObject = seleniumQueryObject.click();
        // then
        assertThat(returnedObject, is(configuredReturningObject));
    }

    @Test
    public void dblclickFunction() {
        // given
        SeleniumQueryFunctionsMock seleniumQueryFunctions = createMockSeleniumQueryFunctions();
        SeleniumQueryObject seleniumQueryObject = createStubSeleniumQueryObjectWithSeleniumQueryFunctions(seleniumQueryFunctions);

        SeleniumQueryObject configuredReturningObject = createSeleniumQueryObjectDummy();
        seleniumQueryFunctions.dblclickMethod = configureReturnValue(configuredReturningObject).forArgs(seleniumQueryObject);
        // when
        SeleniumQueryObject returnedObject = seleniumQueryObject.dblclick();
        // then
        assertThat(returnedObject, is(configuredReturningObject));
    }

}
