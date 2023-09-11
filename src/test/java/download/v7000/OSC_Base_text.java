package download.v7000;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import com.google.common.collect.ImmutableMap;
public class OSC_Base_text {

	public static AndroidDriver<AndroidElement> driver;
	public static String result = "";
	public static String TCID = "";
	protected Dimension size;
	String productname;
	protected double width;

	@Before
	public void setUp() throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "appium");
		capabilities.setCapability("unicodeKeyboard", true);
		capabilities.setCapability("resetKeyboard", "true");
		capabilities.setCapability("udid", "R3CRB0E2RM");
		capabilities.setCapability("appPackage", "com.skt.skaf.A000Z00040");
		capabilities.setCapability("appActivity", "A000Z00040");
		capabilities.setCapability("platformVersion", "13.0.0");
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:7723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	public void popupCheck() throws Exception {
		Thread.sleep(3000);
		try {
			Thread.sleep(3000);
			driver.findElementByXPath("//*[@text='닫기']").isDisplayed();
			Thread.sleep(3000);
			driver.findElementByXPath("//*[@text='닫기']").click();
		} catch (Exception e) {
			System.out.println("popupfail");
		}
		Thread.sleep(3000);
		try {
			Thread.sleep(3000);
			driver.findElementById("com.skt.skaf.A000Z00040:id/buttonConfirm").isDisplayed();
			Thread.sleep(3000);
			driver.findElementById("com.skt.skaf.A000Z00040:id/buttonConfirm").click();
		} catch (Exception e) {
			System.out.println("permissionAgreeFail");
		}			
		Thread.sleep(3000);
		try {
			Thread.sleep(3000);
			driver.findElementById("com.skt.skaf.A000Z00040:id/do_after_btn").isDisplayed();
			Thread.sleep(3000);
			driver.findElementById("com.skt.skaf.A000Z00040:id/do_after_btn").click();
		} catch (Exception e) {
			System.out.println("memberskip fail");
		}
	}
	
	public void SearchClick() {
		driver.executeScript("mobile: performEditorAction", ImmutableMap.of("action", "search"));
	}
	
	public static void upSwipe(double starty) {
		driver.context("NATIVE_APP");
		Dimension size = driver.manage().window().getSize();
		int startY = (int) (size.height * starty);
		int endY = (int) (size.height * 0.10);
		int end = size.width / 2;
		driver.swipe(end, startY, end, endY, 1000);
	}

	public static void downSwipe(double starty) {
		driver.context("NATIVE_APP");
		Dimension size = driver.manage().window().getSize();
		int startY = (int) (size.height * starty);
		int endY = (int) (size.height * 0.90);
		int end = size.width / 2;
		driver.swipe(end, startY, end, endY, 1000);
	}
	// 찾을때까지 하단 스크롤
	public WebElement downscroll(By by) {
		int x = (int) (size.width / 2);
		int y = (int) (size.height * 0.60);
		int endy = (int) (size.height * 0.10);
		WebElement element = null;
		int i;
		for (i = 0; i < 60; i++) {
			try {
				Thread.sleep(50);

				element = driver.findElement(by);
				break;
			} catch (NoSuchElementException nsee) {
				System.out.println("없음");
			} catch (InterruptedException e) {
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
			}
			driver.swipe(x, y, x, endy, 1000);
		}
		System.out.println("찾음" + " - " + element.getText());
		return element;
	}

	// 찾을때까지 상단 스크롤
	public WebElement upscroll(By by) {
		int x = (int) (size.width / 2);
		int y = (int) (size.height * 0.80);
		int halfHeight = (int) (size.height * 0.30);
		WebElement element = null;
		int i;
		for (i = 0; i < 15; i++) {
			try {
				Thread.sleep(50);
				element = driver.findElement(by);
				break;
			} catch (NoSuchElementException nsee) {
				System.out.println("없음");
			} catch (InterruptedException e) {
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
			}
			driver.swipe(x, halfHeight, x, y, 1000);
		}
		System.out.println("찾음" + " - " + element.getText());
		return element;
	}
	
	public WebElement shortdownscroll(By by) {
		int x = (int) (size.width / 2);
		int y = (int) (size.height * 0.35);
		int endy = (int) (size.height * 0.20);
		WebElement element = null;
		int i;
		for (i = 0; i < 15; i++) {
			try {
				Thread.sleep(50);
				element = driver.findElement(by);
				break;
			} catch (NoSuchElementException nsee) {
				System.out.println("없음");
			} catch (InterruptedException e) {
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
			}
			driver.swipe(x, y, x, endy, 1000);
		}
		System.out.println("찾음" + " - " + element.getText());
		return element;
	}
	
	public String makeXPath(String textType, String text) {
		return "//*[@" + textType + "='" + text + "']";
	}

	@After
	public void finish() {
		System.out.println("───────────────────────────────────────────");
		System.out.println(TCID +" : "+ result);
		driver.navigate().back();
		driver.navigate().back();
		driver.pressKeyCode(AndroidKeyCode.HOME);
		try {
			Runtime.getRuntime().exec("adb shell am force-stop com.skt.skaf.OA00018282");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.quit();
		System.out.println("───────────────────────────────────────────");
	}
}