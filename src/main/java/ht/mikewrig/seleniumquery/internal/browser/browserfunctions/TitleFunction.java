package ht.mikewrig.seleniumquery.internal.browser.browserfunctions;

import ht.mikewrig.seleniumquery.internal.browser.InternalTargetableBrowserFunctions;

public interface TitleFunction extends InternalTargetableBrowserFunctions {

    @Override
    default String title() {
        return target().driver().get().getTitle();
    }

}
