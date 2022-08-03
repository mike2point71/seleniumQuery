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

package ht.mikewrig.seleniumquery.utils;

import static org.apache.commons.lang3.ArrayUtils.contains;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.safari.SafariDriver;

import com.google.common.annotations.VisibleForTesting;
import ht.mikewrig.seleniumquery.SeleniumQueryObject;

/**
 * Contains some utility functions for dealing with WebDrivers, such as inspecting their version.
 *
 * @author acdcjunior
 * @author ricardo-sc
 * @since 0.9.0
 */
public class DriverVersionUtils {

	private static DriverVersionUtils instance = new DriverVersionUtils();

	private static final Log LOGGER = LogFactory.getLog(DriverVersionUtils.class);

	private Map<WebDriver, Map<String, Boolean>> driverSupportedPseudos = new HashMap<>();

	public static DriverVersionUtils getInstance() {
		return instance;
	}

    @VisibleForTesting // this method only exists for testing purposes (as this class is a singleton)
	public static void overrideSingletonInstance(DriverVersionUtils instance) {
		DriverVersionUtils.instance = instance;
	}

	public boolean hasNativeSupportForPseudo(WebDriver driver, String pseudoClass) {
        Map<String, Boolean> driverMap = driverSupportedPseudos.computeIfAbsent(driver, k -> new HashMap<>());
		Boolean supports = driverMap.get(pseudoClass);
		if (supports == null) {
			boolean supported = testPseudoClassNativeSupport(pseudoClass, driver);
			driverMap.put(pseudoClass, supported);
			return supported;
		}
		return supports;
	}

	private boolean testPseudoClassNativeSupport(String pseudo, SearchContext context) {
		try {
			By.cssSelector("#AAA_SomeIdThatShouldNotExist"+pseudo).findElements(context);
			return true;
		} catch (Exception ignored) {
			return false;
		}
	}



    public static boolean isSafariDriver(WebDriver driver) {
        return isDriver(driver, SafariDriver.class, BrowserType.SAFARI);
    }

    public static boolean isEdgeDriver(WebDriver driver) {
        return isDriver(driver, EdgeDriver.class, BrowserType.EDGE);
    }
    public static boolean isDriver(WebDriver driver, Class<?> clazz, String... browserNames) {
        return clazz.isInstance(driver) || isDriverByName(driver, browserNames);
    }

    public static boolean isDriverByName(WebDriver driver, String... browserNames) {
        return driver instanceof HasCapabilities && contains(browserNames, ((HasCapabilities) driver).getCapabilities().getBrowserName());
    }

    public static boolean isFirefoxDriver(WebDriver driver) {
        return isDriver(driver, FirefoxDriver.class, BrowserType.FIREFOX);
    }

}
