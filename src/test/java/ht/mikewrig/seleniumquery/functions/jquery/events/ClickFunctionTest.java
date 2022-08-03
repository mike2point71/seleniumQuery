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

package ht.mikewrig.seleniumquery.functions.jquery.events;

import ht.mikewrig.seleniumquery.SeleniumQueryException;
import ht.mikewrig.seleniumquery.SeleniumQueryObject;
import org.junit.Test;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebElement;
import testinfrastructure.testdouble.org.apache.commons.logging.LogInjector;
import testinfrastructure.testdouble.org.apache.commons.logging.LogSpy;
import testinfrastructure.testdouble.org.openqa.selenium.WebElementClickSpy;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static testinfrastructure.testdouble.ht.mikewrig.seleniumquery.SeleniumQueryObjectMother.createStubSeleniumQueryObjectWithElements;
import static testinfrastructure.testdouble.org.openqa.selenium.WebElementClickSpy.createWebElementClickSpy;
import static testinfrastructure.testdouble.org.openqa.selenium.WebElementMother.createWebElementClickable;
import static testinfrastructure.testdouble.org.openqa.selenium.WebElementMother.createWebElementUnclickableHidden;

public class ClickFunctionTest {

    @Test
    public void click__shouldReturnPassedSeleniumQueryObject() {
        // given
        SeleniumQueryObject sqo = createStubSeleniumQueryObjectWithElements();
        // when
        SeleniumQueryObject returnedSqo = ClickFunction.click(sqo);
        // then
        assertThat(returnedSqo, is(sqo));
    }

    @Test
    public void click__shouldCallClickOnEveryElementIfNoneThrowsException() {
        // given
        WebElementClickSpy webElementClickSpyOne = createWebElementClickSpy();
        WebElementClickSpy webElementClickSpyTwo = createWebElementClickSpy();
        SeleniumQueryObject sqo = createStubSeleniumQueryObjectWithElements(webElementClickSpyOne, webElementClickSpyTwo);
        // when
        ClickFunction.click(sqo);
        // then
        webElementClickSpyOne.assertNumberOfTimesClicked(1);
        webElementClickSpyTwo.assertNumberOfTimesClicked(1);
    }

    @Test
    public void click__shouldCallClickOnEveryElementEvenIfSomeThrowExceptions() {
        // given
        WebElement unclickableHiddenWebElement = createWebElementUnclickableHidden();
        WebElementClickSpy webElementClickSpy = createWebElementClickSpy();
        SeleniumQueryObject sqo = createStubSeleniumQueryObjectWithElements(unclickableHiddenWebElement, webElementClickSpy);
        // when
        ClickFunction.click(sqo);
        // then
        webElementClickSpy.assertNumberOfTimesClicked(1);
    }

    @Test
    public void click__shouldLogInfoIfAnyElementThrewException() {
        // given
        LogSpy logSpy = LogInjector.injectLogSpy(ClickFunction.class);
        SeleniumQueryObject sqo = createStubSeleniumQueryObjectWithElements(createWebElementUnclickableHidden(), createWebElementClickable());
        // when
        ClickFunction.click(sqo);
        // then
        logSpy.assertInfoWithExceptionWasLogged(ElementNotVisibleException.class);
    }

    @Test(expected = SeleniumQueryException.class)
    public void click__shouldThrowExceptionIfNoElementIsClickable() {
        // given
        WebElement unclickableHiddenWebElement = createWebElementUnclickableHidden();
        WebElement unclickableHiddenWebElementTwo = createWebElementUnclickableHidden();
        SeleniumQueryObject sqo = createStubSeleniumQueryObjectWithElements(unclickableHiddenWebElement, unclickableHiddenWebElementTwo);
        // when
        ClickFunction.click(sqo);
        // then
        // exception is thrown
    }

}
