package ht.mikewrig.seleniumquery.internal.fluentfunctions.getters;

import ht.mikewrig.seleniumquery.SeleniumQueryObject;

public class HtmlGetter implements Getter<String> {

	public static HtmlGetter HTML_GETTER = new HtmlGetter();

	private HtmlGetter() { }

    @Override
    public String get(SeleniumQueryObject seleniumQueryObject) {
		return seleniumQueryObject.html();
	}

	@Override
	public String toString() {
		return "html()";
	}

}
