package osc_e2e.responsetime.v7000;

import org.junit.Test;

public class RunApp extends ResponseTimeMeasureBase{
	
	@Test
	public void RunAppTest() throws Exception {
		TCID = "RunApp";
		result = "FAIL";
		
		double appStartTime = 0;
		double mainEnterTime = 0;
		double resultTime[] = new double[10];
		
		for(int i = 0; i < 10; i++) {
			setUp();
			appStartTime = System.nanoTime();
			
			while (true) {
				try {
					driver.findElementById("com.skt.skaf.A000Z00040:id/titleView").isDisplayed();
					mainEnterTime = System.nanoTime();
					break;
				} catch (Exception e) {}
			}
			
			resultTime[i] = (mainEnterTime - appStartTime) / 1000000000.0;
		}
		
		for (int i = 0; i < 10; i++) {
			System.out.print(String.format("%.2f", resultTime[i]) + "\t");
		}		
		
		result = "PASS";
	}
}