package osc_e2e.reponsetime.v7000;

import org.junit.Test;

public class AppRun extends ResponseTimeMeasureBase{
	
	@Test
	public void AppRunTest() throws Exception {
		TCID = "StartAppTime";
		result = "FAIL";
		
		double startAppRunTime = 0;
		double resultAppRunTime = 0;
		
		setUp();
		startAppRunTime = System.nanoTime();
		
		while (true) {
			try {
				driver.findElementById("com.skt.skaf.A000Z00040:id/titleView").isDisplayed();
				resultAppRunTime = Double.parseDouble(String.format("%.2f", ((System.nanoTime() - startAppRunTime) / 1000000000.0)));
				break;
			} catch (Exception e) {}
		}
		
		System.out.println("--------------------------------------------------");
		System.out.println(" / " + " App Run Time / " + resultAppRunTime + "s");
		System.out.println("--------------------------------------------------");
				
		result = "PASS";

	}
	
}