package osc_e2e.responsetime.v7000;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

public class Search extends ResponseTimeMeasureBase{
	
	@Test
	public void AppRunTest() throws Exception {
		TCID = "AppTunTime";
		result = "FAIL";
		StopWatch time = new StopWatch();
		
		double startAppRunTime = 0;
		double resultAppRunTime = 0;
		
		setUp();
		
		Thread.sleep(5000);
		driver.findElementById("com.skt.skaf.A000Z00040:id/btn_search").click();
		
		Thread.sleep(2000);
		driver.findElementById("com.skt.skaf.A000Z00040:id/searchbar_keyword_edittext").click();
		
		Thread.sleep(2000);
		driver.findElementById("com.skt.skaf.A000Z00040:id/searchbar_keyword_edittext").sendKeys("데칼코마니");
		
		Thread.sleep(2000);
		driver.findElementById("com.skt.skaf.A000Z00040:id/searchbar_search_icon").click();
		time.start();
		startAppRunTime = System.nanoTime();
		
		while (true) {
			try {
				driver.findElementById("com.skt.skaf.A000Z00040:id/search_screenshot_image").isDisplayed();
				time.stop();
				resultAppRunTime = (System.nanoTime() - startAppRunTime) / 1000000000.0;
				break;
			} catch (Exception e) {
				System.out.println("fail");
			}
		}
		
		System.out.println("--------------------------------------------------");
		System.out.println(" / " + " App Run Time / " + String.format("%.2f", resultAppRunTime) + "s");
		System.out.println(time.getTime());
		System.out.println("--------------------------------------------------");
				
		result = "PASS";

	}
	
}