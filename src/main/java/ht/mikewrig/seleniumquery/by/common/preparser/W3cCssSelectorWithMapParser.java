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

package ht.mikewrig.seleniumquery.by.common.preparser;

import java.io.StringReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.css.sac.CSSException;
import org.w3c.css.sac.CSSParseException;
import org.w3c.css.sac.ErrorHandler;
import org.w3c.css.sac.InputSource;
import org.w3c.css.sac.SelectorList;

import com.steadystate.css.parser.SACParserCSS3;
import ht.mikewrig.seleniumquery.SeleniumQueryException;
import ht.mikewrig.seleniumquery.by.common.preparser.CssSelectorPreParser.PreParsedSelector;
import ht.mikewrig.seleniumquery.by.common.preparser.w3cwithmap.W3cCssSelectorListWithMap;

public class W3cCssSelectorWithMapParser {

	private static final Log LOGGER = LogFactory.getLog(W3cCssSelectorWithMapParser.class);

    private static final SACParserCSS3 SAC_CSS3_PARSER = new SACParserCSS3();

	public static W3cCssSelectorListWithMap parseSelector(String selector) {
        PreParsedSelector preParsedSelector = CssSelectorPreParser.preParseSelector(selector);
		SelectorList selectorList = parseSelectorIntoParseTree(preParsedSelector.getTransformedSelector());
		return new W3cCssSelectorListWithMap(selectorList, preParsedSelector.getArgumentMap());
	}

    /**
	 * Parses a selector into a parse tree using SAC CSS3 Parser.
	 */
	private static SelectorList parseSelectorIntoParseTree(String selector) {
		try {
            return SAC_CSS3_PARSER.parseSelectors(new InputSource(new StringReader(selector)));
        } catch (Exception e) {
            throw new SeleniumQueryException("Impossible to parse selector \""+selector+"\": "+e.getMessage(), e);
        }
	}

    static {
	    SAC_CSS3_PARSER.setErrorHandler(new ErrorHandler() {
	    	@Override
			public void warning(final CSSParseException cssParseException) throws CSSException {
				LOGGER.warn(cssParseException.toString(), cssParseException);
			}

	    	@Override
			public void error(final CSSParseException cssParseException) throws CSSException {
				throw cssParseException;
			}

	    	@Override
			public void fatalError(final CSSParseException cssParseException) throws CSSException {
				throw cssParseException;
			}
		});
    }

}
