package osc_e2e.responsetime.v7000;

import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class ResponseTimeMeasureBase {

	public static AndroidDriver<AndroidElement> driver;
	public static String result = "";
	public static String TCID = "";
	protected Dimension size;
	String productname;
	WebElement element;

	public void setUp() throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "appium");
		capabilities.setCapability("unicodeKeyboard", true);
		capabilities.setCapability("udid", "R39KA0CNB4");
		capabilities.setCapability("appPackage", "com.skt.skaf.A000Z00040");
		capabilities.setCapability("appActivity", "A000Z00040");
		capabilities.setCapability("platformVersion", "7.0");
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:9723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}	

	@After
	public void sessionexit() throws Exception {
		driver.quit();
	}

	public void Check() throws Exception {
		try {
			if (driver.findElementById("com.skt.skaf.A000Z00040:id/event_popup_close").isDisplayed())
				driver.findElementById("com.skt.skaf.A000Z00040:id/event_popup_close").click();
		} catch (Exception e) {

		}

		try {
			if (driver.findElementById("com.skt.skaf.A000Z00040:id/btn_member_change_id_skip").isDisplayed())
				driver.findElementById("com.skt.skaf.A000Z00040:id/btn_member_change_id_skip").click();
		} catch (Exception e) {
		}
	}
}