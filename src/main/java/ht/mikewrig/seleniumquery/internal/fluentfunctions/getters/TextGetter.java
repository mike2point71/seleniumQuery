package ht.mikewrig.seleniumquery.internal.fluentfunctions.getters;

import ht.mikewrig.seleniumquery.SeleniumQueryObject;

public class TextGetter implements Getter<String> {

	public static TextGetter TEXT_GETTER = new TextGetter();

	private TextGetter() { }

    @Override
    public String get(SeleniumQueryObject seleniumQueryObject) {
		return seleniumQueryObject.text();
	}

	@Override
	public String toString() {
		return "text()";
	}

}
