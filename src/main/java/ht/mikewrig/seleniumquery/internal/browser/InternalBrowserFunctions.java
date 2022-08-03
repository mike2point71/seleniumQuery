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

package ht.mikewrig.seleniumquery.internal.browser;

import static java.lang.String.format;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ht.mikewrig.seleniumquery.browser.BrowserFunctions;
import ht.mikewrig.seleniumquery.browser.driver.SeleniumQueryDriver;
import ht.mikewrig.seleniumquery.internal.browser.browserfunctions.EvalFunction;
import ht.mikewrig.seleniumquery.internal.browser.browserfunctions.TitleFunction;
import org.openqa.selenium.interactions.InputSource;

/**
 * Default internal implementation of {@link BrowserFunctions}.
 *
 * @since 0.18.0
 */
public class InternalBrowserFunctions
    implements
    BrowserFunctions,
    InternalTargetableBrowserFunctions,
    // function implementations are added as "mixins" via default methods
    EvalFunction,
    TitleFunction {

    private static final Log LOGGER = LogFactory.getLog(InternalBrowserFunctions.class);

    private SeleniumQueryDriver globalDriver = new SeleniumQueryDriver();

    @Override
    public BrowserFunctions target() {
        return this;
    }

    @Override
    public SeleniumQueryDriver driver() {
        return globalDriver;
    }

    @Override
    public String url() {
        return driver().get().getCurrentUrl();
    }

    @Override
    public BrowserFunctions url(String urlToOpen) {
        LOGGER.debug(format("Opening URL: %s", urlToOpen));
        driver().get().get(urlToOpen);
        return this;
    }

    @Override
    public BrowserFunctions url(File fileToOpenAsURL) {
        return url(fileToOpenAsURL.toURI().toString());
    }

    @Override
    public BrowserFunctions maximizeWindow() {
        LOGGER.debug("Maximizing window.");
        driver().get().manage().window().maximize();
        return this;
    }

    @Override
    public BrowserFunctions quit() {
        driver().quit();
        return this;
    }

}
