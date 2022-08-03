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

package ht.mikewrig.seleniumquery;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * THe seleniumQuery config util class.
 *
 * @author acdcjunior
 * @author ricardo-sc
 * @since 0.9.0
 */
public class SeleniumQueryConfig {

	private static final Log LOGGER = LogFactory.getLog(SeleniumQueryConfig.class);

	private static final String PROP_GLOBAL_TIMEOUT = "GLOBAL_TIMEOUT";
	private static final String PROP_WAITUNTIL_TIMEOUT = "WAITUNTIL_TIMEOUT";
	private static final String PROP_WAITUNTIL_POLLING_INTERVAL = "WAITUNTIL_POLLING_INTERVAL";

	/**
	 * All times are in milliseconds.
	 */
	private static long globalTimeout = 1501;
	private static long waitUntilTimeout = 10001;
	private static long waitUntilPollingInterval = 901;

	private static Properties properties;

	static {
		loadPropertiesFiles();
	}

	private static void loadPropertiesFiles() {
		try {
			loadPropertiesFileFromClasspath();

			globalTimeout = getLongProperty(PROP_GLOBAL_TIMEOUT, globalTimeout);
			waitUntilTimeout = getLongProperty(PROP_WAITUNTIL_TIMEOUT, waitUntilTimeout);
			waitUntilPollingInterval = getLongProperty(PROP_WAITUNTIL_POLLING_INTERVAL, waitUntilPollingInterval);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void loadPropertiesFileFromClasspath() throws IOException {
		properties = new Properties();
		LOGGER.debug("Attempting to load properties from seleniumQuery.properties in classpath.");
		InputStream inputStream = SeleniumQueryConfig.class.getClassLoader().getResourceAsStream("seleniumQuery.properties");
		if (inputStream == null) {
			LOGGER.info("No seleniumQuery.properties found in classpath, falling back to defaults.");
		} else {
            try {
                properties.load(inputStream);
            } catch (Exception e) {
                LOGGER.info("Problem while loading properties. Falling back to defaults.", e);
            }
            inputStream.close();
        }
    }

	private static long getLongProperty(String propertyName, long defaultValue) {
		String propertyAsString = SeleniumQueryConfig.properties.getProperty(propertyName);
		if (propertyAsString == null || propertyAsString.trim().isEmpty()) {
			return defaultValue;
		}
		return Long.valueOf(propertyAsString);
	}

	public static String get(String property) {
		return properties.getProperty(property);
	}

	public static long getGlobalTimeout() {
		return globalTimeout;
	}
	public static long getWaitUntilTimeout() {
		return waitUntilTimeout;
	}
	public static long getWaitUntilPollingInterval() {
		return waitUntilPollingInterval;
	}

}
