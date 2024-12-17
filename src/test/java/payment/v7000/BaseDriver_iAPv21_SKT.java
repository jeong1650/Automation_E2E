package payment.v7000;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class BaseDriver_iAPv21_SKT {

public static AndroidDriver<AndroidElement> driver;
public static String result = "";
public static String TCID = "";
public static String UDID = "R3CW90K2WLV";
protected Dimension size;

	
	@Before
	public void setUp() throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "appium");
		capabilities.setCapability("unicodeKeyboard", true);
		capabilities.setCapability("udid", UDID);
		capabilities.setCapability("appPackage", "com.v21test.fun.portrait");
		capabilities.setCapability("appActivity", "com.gaa.iap.apisample.MainActivity");
		capabilities.setCapability("platformVersion", "14");
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}	

	public void Password() throws Exception {
	   Thread.sleep(2000);
	   driver.findElementByXPath("//android.widget.Button[@text='1']").click();
		   
	   Thread.sleep(2000);
	   driver.findElementByXPath("//android.widget.Button[@text='0']").click();
		   
	   Thread.sleep(2000);
	   driver.findElementByXPath("//android.widget.Button[@text='2']").click();
		   
	   Thread.sleep(2000);
	   driver.findElementByXPath("//android.widget.Button[@text='9']").click();
		   
	   Thread.sleep(2000);
	   driver.findElementByXPath("//android.widget.Button[@text='3']").click();
		   
	   Thread.sleep(2000);
	   driver.findElementByXPath("//android.widget.Button[@text='8']").click();
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
				String filePath = "C:\\ProgramData\\Jenkins\\.jenkins\\workspace\\iAPv21_SKT_E2E\\failSC\\";
				FileUtils.copyFile(scrFile, new File(filePath + FileName +".png"));
				File pngOriginal = new File(filePath + FileName +".png");
				File pngResized = new File(filePath + FileName +".png");
				resizeImage(pngOriginal, pngResized, 360, 777, "png");
			} catch (Exception e) {
				System.out.println(e.toString());
			}	
		}
		System.out.println("======================================");
		System.out.println("        IAPv21_SKTResult : " + result);
		System.out.println("                 END                 ");
		System.out.println("======================================");

		try {
			Runtime.getRuntime().exec("adb shell am force-stop com.skt.skaf.OA00018282");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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