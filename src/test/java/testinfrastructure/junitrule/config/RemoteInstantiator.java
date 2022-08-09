package testinfrastructure.junitrule.config;

import ht.mikewrig.seleniumquery.browser.BrowserFunctions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import testinfrastructure.junitrule.annotation.*;

import java.lang.annotation.Annotation;
import java.net.MalformedURLException;
import java.net.URL;

import static java.lang.String.format;

@SuppressWarnings("deprecation")
public class RemoteInstantiator extends DriverInstantiator {

    public static RemoteInstantiator REMOTE_CHROME = new RemoteInstantiator("Chrome", new DesiredCapabilities(), ChromeOnly.class);
    public static RemoteInstantiator REMOTE_CHROME_OSX = new RemoteInstantiator("Chrome", new DesiredCapabilities(), "OS X 10.11", ChromeOnly.class);
    public static RemoteInstantiator REMOTE_CHROME_W7 = new RemoteInstantiator("Chrome", new DesiredCapabilities(), "Windows 7", ChromeOnly.class);

    public static RemoteInstantiator REMOTE_FIREFOX = new RemoteInstantiator("Firefox", new DesiredCapabilities(), FirefoxOnly.class);
    public static RemoteInstantiator REMOTE_FIREFOX_OSX = new RemoteInstantiator("Firefox", new DesiredCapabilities(), "OS X 10.11", FirefoxOnly.class);
    public static RemoteInstantiator REMOTE_FIREFOX_W7 = new RemoteInstantiator("Firefox", new DesiredCapabilities(), "Windows 7", FirefoxOnly.class);

    public static RemoteInstantiator REMOTE_IE_10 = ie("10", "Windows 7");
    public static RemoteInstantiator REMOTE_IE_11 = ie("11", "Windows 8.1");

    public static RemoteInstantiator REMOTE_SAFARI = new RemoteInstantiator("Safari", new DesiredCapabilities(), SafariOnly.class);

    public static RemoteInstantiator REMOTE_EDGE = new RemoteInstantiator("Edge", new DesiredCapabilities(), "Windows 10", EdgeOnly.class);

    public static RemoteInstantiator REMOTE_OPERA = new RemoteInstantiator("Opera", new DesiredCapabilities(), "Windows 7", OperaOnly.class);

    private static RemoteInstantiator ie(String version, String platform) {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platform", platform);
        desiredCapabilities.setCapability("version", version);
        return new RemoteInstantiator("IE " + version + " on " + platform, desiredCapabilities, ChromeOnly.class);
    }

    private final DesiredCapabilities capabilities;

    private RemoteInstantiator(String driverDescription, DesiredCapabilities desiredCapabilities, Class<? extends Annotation> driverOnlyAnnotation) {
        super("Remote " + driverDescription, driverOnlyAnnotation);
        capabilities = desiredCapabilities;
    }

    private RemoteInstantiator(String driverDescription, DesiredCapabilities desiredCapabilities,
                               String platform, Class<? extends Annotation> driverOnlyAnnotation) {
        super("Remote " + driverDescription + " on " + platform, driverOnlyAnnotation);
        capabilities = desiredCapabilities;
        capabilities.setCapability("platform", platform);
    }

    @Override public void instantiateDriver(BrowserFunctions $) {
        try {
            String sauceUser = System.getenv("SAUCE_USERNAME");
            String sauceKey = System.getenv("SAUCE_ACCESS_KEY");

            RemoteWebDriver remoteChrome = new RemoteWebDriver(new URL(format("http://%s:%s@ondemand.saucelabs.com:80/wd/hub", sauceUser, sauceKey)), capabilities);
            $.driver().use(remoteChrome);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

}
