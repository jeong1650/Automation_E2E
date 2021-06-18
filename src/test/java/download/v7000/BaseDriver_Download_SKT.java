package download.v7000;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.junit.Before;
import org.codehaus.plexus.util.FileUtils;
import org.junit.After;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class BaseDriver_Download_SKT {

public static AndroidDriver<AndroidElement> driver;
public static String result = "";
public static String TCID = "";
protected Dimension size;
String productname;
	
	@Before
	public void setUp() throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "appium");
		capabilities.setCapability("unicodeKeyboard", true);
		capabilities.setCapability("udid", "R3CM406ZMP");
		capabilities.setCapability("appPackage", "com.skt.skaf.A000Z00040");
		capabilities.setCapability("appActivity", "A000Z00040");
		capabilities.setCapability("platformVersion", "10");
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:7723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}	

	public void popupCheck() throws Exception {
		Thread.sleep(3000);
		try {
			driver.findElementById("com.skt.skaf.A000Z00040:id/event_popup_close").isDisplayed();
			System.out.println("----------eventPopup----------");
			driver.findElementById("com.skt.skaf.A000Z00040:id/event_popup_close").click();
		} catch (Exception e) {
			System.out.println("popupfail");
		}
		Thread.sleep(3000);
		try {
			driver.findElementById("com.skt.skaf.A000Z00040:id/do_after_btn").isDisplayed();
			System.out.println("----------memberChangePopup----------");
			driver.findElementById("com.skt.skaf.A000Z00040:id/do_after_btn").click();
		} catch (Exception e) {
			System.out.println("memberskip fail");
		}
	}
	
	public void ResultPrint() throws Exception {
	   System.out.println("-----------------------------------");
	   result = "PASS";
	   System.out.println(" --- " + TCID + result + " ---");   
	   System.out.println("-----------------------------------");
	   driver.quit();	
	}

	// 좌표, 하단 스크롤
	public static void upSwipe(double starty) {
		driver.context("NATIVE_APP");
		Dimension size = driver.manage().window().getSize();
		int startY = (int) (size.height * starty);
		int endY = (int) (size.height * 0.10);
		int end = size.width / 2;
		driver.swipe(end, startY, end, endY, 1000);
	}	
	
	@After
	public void end() {
		if (result != "PASS") {
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY_MM_dd_kk_mm");
			Date date = new Date();
			String FileName = sdf.format(date);
			try { 
				FileUtils.copyFile(scrFile, new File("C:\\Program Files (x86)\\Jenkins\\workspace\\Download_SKT_E2E\\"+ FileName +".png"));				
				File pngOriginal = new File("C:\\Program Files (x86)\\Jenkins\\workspace\\Download_SKT_E2E\\"+ FileName +".png");
				File pngResized = new File("C:\\Program Files (x86)\\Jenkins\\workspace\\Download_SKT_E2E\\"+ FileName +".png");
				resizeImage(pngOriginal, pngResized, 360, 777, "png");
			} catch (Exception e) {
				System.out.println(e.toString());
			}	
		}
		System.out.println("======================================");
		System.out.println("        DownloadSKTResult : " + result);
		System.out.println("                 END                 ");
		System.out.println("======================================");
		driver.quit();		
	}

	private void resizeImage(File originalImage, File resizedImage, int width, int height, String format) {
		try {
			BufferedImage original = ImageIO.read(originalImage);
			BufferedImage resized = new BufferedImage(width, height, original.getType());
			Graphics2D g2 = resized.createGraphics();
			g2.drawImage(original, 0, 0, width, height, null);
			g2.dispose();
			ImageIO.write(resized, format, resizedImage);			
		} catch (Exception e) {
		}		
	}
}