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

package testinfrastructure;

import static ht.mikewrig.seleniumquery.SeleniumQuery.$;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import ht.mikewrig.seleniumquery.SeleniumQuery;
import ht.mikewrig.seleniumquery.SeleniumQueryObject;
import ht.mikewrig.seleniumquery.browser.BrowserFunctions;
import testinfrastructure.testutils.SauceLabsUtils;

public class EndToEndTestUtils {

    private static final String TEST_SRC_FOLDER = "src/test/java/";

    /**
     * Asserts that a select matches the given IDs
     * -param {String} a - Assertion name
     * -param {String} b - Sizzle selector
     * -param {String} c - Array of ids to construct what is expected
     * -example t("Check for something", "//[a]", ["foo", "baar"]);
     * -result returns true if "//[a]" return two elements with the IDs 'foo' and 'baar'
     */
    public static NegativeAbleTest t(String assertionName, String selector, String... expectedIds) {
        List<String> actualIds = asList(ids(selector));
        List<String> expectedIdsList = asList(expectedIds);
        assertEquals(assertionName + " --> Lists differ!", expectedIdsList.toString(), actualIds.toString());
        return new NegativeAbleTest(assertionName);
    }

    public static NegativeAbleTest t(String assertionName, String selector, int expectedMatchedSetSize) {
        int actualIds = ids(selector).length;
        assertEquals(assertionName + " --> Lists differ!", expectedMatchedSetSize, actualIds);
        return new NegativeAbleTest(assertionName);
    }

    public static String classNameToTestFileUrl(Class<?> clazz) {
        String classFullName = clazz.getName();
        return classNameToTestFileUrl(classFullName);
    }

    public static String classNameToTestFileUrl(String classFullName) {
        String classPath = classFullName.replace('.', '/');
        String htmlPath = TEST_SRC_FOLDER + classPath + ".html";
        File file = new File(htmlPath);
        if (!file.exists()) {
            throw new RuntimeException("File " + htmlPath + " does not exist.");
        }
        return file.toURI().toString();
    }

    public static void openUrl(String urlToOpen) {
        openUrl(urlToOpen, SeleniumQuery.$);
    }

    public static void openUrl(Class<?> clazz, BrowserFunctions $) {
        openUrl(classNameToTestFileUrl(clazz), $);
    }

    public static void openUrl(String urlToOpen, BrowserFunctions $) {
        SauceLabsUtils.jobName(urlToOpen, $);
        $.url(SauceLabsUtils.fixUrlForRemoteTest(urlToOpen));
    }

    @SuppressWarnings("unused")
    protected void tIS(String assertionName, String selector, String[] expectedIds) {
        for (String expectedId : expectedIds) {
            System.out.println("$(\"#"+expectedId+"\").is(\""+selector+"\")");
            assertTrue(assertionName, $("#"+expectedId).is(selector));
        }
    }

    private static String[] ids(String selector) {
        SeleniumQueryObject f = $(selector);
        List<String> actualIds = new ArrayList<>();
        f.forEach(webElement -> {
            actualIds.add(id(webElement));
        });
        return actualIds.toArray(new String[actualIds.size()]);
    }

    /**
     * Gets the @id of the given element.
     * @param webElement element to extract id.
     * @return the ID attribute.
     */
    public static String id(WebElement webElement) {
        return webElement.getAttribute("id");
    }

    /**
     * Gets the @id of the first element in the matched set.
     * @param sq matched set to extract id of first element from.
     * @return the ID attribute.
     */
    public static String id(SeleniumQueryObject sq) {
        return id(sq.get(0));
    }

    /**
     * Gets the @ids of all elements in the matched set.
     * @param sq matched set to extract ids from.
     * @return list of IDs.
     */
    public static List<String> ids(SeleniumQueryObject sq) {
        List<String> ids = new ArrayList<>(sq.size());
        sq.forEach(element -> {
            ids.add(id(element));
        });
        return ids;
    }

    public static void equal(Object o1, Object o2, String msg) {
        assertEquals(msg, o2, o1);
    }

    /**
     * The "negative" is just some assertion to make sure your test is really working (and not just silently failing).
     */
    @SuppressWarnings("WeakerAccess")
    public static class NegativeAbleTest {
        private String originalAssertionName;
        private NegativeAbleTest(String originalAssertionName) {
            this.originalAssertionName = originalAssertionName;
        }
        public void negative(String selector, String... ids) {
            t("NEGATION of: "+originalAssertionName, selector, ids);
        }
        public void negative(String selector, int expectedMatchedSetSize) {
            t("NEGATION of: "+originalAssertionName, selector, expectedMatchedSetSize);
        }
    }

}
