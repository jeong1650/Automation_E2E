package osc_e2e.responsetime.v7000;

import org.junit.Test;

public class EnterAppDetail extends ResponseTimeMeasureBase{
	
	@Test
	public void EnterGameDetailTest() throws Exception {
		TCID = "EnterGameDetailTest";
		result = "FAIL";
		
		double clickProductTitleTime = 0;
		double enterDetailTime = 0;
		double resultTime[] = new double[10];
				
		for(int i = 0; i < 10; i++) {
			setUp();
			
			Thread.sleep(5000);
			driver.findElementById("com.skt.skaf.A000Z00040:id/btn_search").click();
			
			Thread.sleep(2000);
			driver.findElementById("com.skt.skaf.A000Z00040:id/searchbar_keyword_edittext").click();
			
			Thread.sleep(2000);
			driver.findElementById("com.skt.skaf.A000Z00040:id/searchbar_keyword_edittext").sendKeys("카카오톡");
			
			Thread.sleep(2000);
			driver.findElementById("com.skt.skaf.A000Z00040:id/searchbar_search_icon").click();
			
			Thread.sleep(2000);
			driver.findElementById("com.skt.skaf.A000Z00040:id/search_result_item_app_title").click();
			clickProductTitleTime = System.nanoTime();
			
			while (true) {
				try {
					driver.findElementById("com.skt.skaf.A000Z00040:id/lay_root").isDisplayed();
					enterDetailTime = System.nanoTime();
					break;
				} catch (Exception e) {}
			}
			resultTime[i] = (enterDetailTime - clickProductTitleTime) / 1000000000.0;
		}
		
		for (int i = 0; i < 10; i++) {
			System.out.print(String.format("%.2f", resultTime[i]) + "\t");
		}	
						
		result = "PASS";
	}
}