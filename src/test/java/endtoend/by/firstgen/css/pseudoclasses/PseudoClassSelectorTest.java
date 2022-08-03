package endtoend.by.firstgen.css.pseudoclasses;

import static ht.mikewrig.seleniumquery.SeleniumQuery.$;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import ht.mikewrig.seleniumquery.by.firstgen.css.pseudoclasses.UnsupportedPseudoClassException;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

public class PseudoClassSelectorTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Test(expected = UnsupportedPseudoClassException.class)
    public void invalid_pseudo__with_braces() {
        $(":invalid-pseudo(123)");
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalid_pseudo__without_braces() {
        $(":invalid-pseudo");
    }

}
