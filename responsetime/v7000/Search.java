package osc_e2e.responsetime.v7000;

import org.junit.Test;

public class Search extends ResponseTimeMeasureBase {

	@Test
	public void SearchTest() throws Exception {
		TCID = "AppTunTime";
		result = "FAIL";
	
		double clickSearchButtonTime = 0;
		double completeSearchTime = 0;
		double timeResult[] = new double[10];
		
		for (int i = 0; i < 10; i++) {
			setUp();

			Thread.sleep(5000);
			driver.findElementById("com.skt.skaf.A000Z00040:id/btn_search").click();

			Thread.sleep(2000);
			driver.findElementById("com.skt.skaf.A000Z00040:id/searchbar_keyword_edittext").click();

			Thread.sleep(2000);
			driver.findElementById("com.skt.skaf.A000Z00040:id/searchbar_keyword_edittext").sendKeys("데칼코마니");

			Thread.sleep(2000);
			driver.findElementById("com.skt.skaf.A000Z00040:id/searchbar_search_icon").click();
			
			clickSearchButtonTime = System.nanoTime();

			while (true) {
				try {
					driver.findElementById("com.skt.skaf.A000Z00040:id/search_screenshot_image").isDisplayed();
					completeSearchTime = System.nanoTime();
					break;
				} catch (Exception e) {}
			}
			
			timeResult[i] = (completeSearchTime - clickSearchButtonTime) / 1000000000.0;
		}
		
		for (int i = 0; i < 10; i++) {
			System.out.print(String.format("%.2f", timeResult[i]) + "\t");
		}		
		
		result = "PASS";

	}

}