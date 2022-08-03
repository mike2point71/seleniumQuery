package ht.mikewrig.seleniumquery.internal.fluentfunctions.getters;

import ht.mikewrig.seleniumquery.SeleniumQueryObject;

public class SizeGetter implements Getter<Integer> {

	public static SizeGetter SIZE_GETTER = new SizeGetter();

	private SizeGetter() { }

    @Override
    public Integer get(SeleniumQueryObject seleniumQueryObject) {
		return seleniumQueryObject.size();
	}

	@Override
	public String toString() {
		return "size()";
	}

}
