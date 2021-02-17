package osc_e2e.responsetime.v7000;

import org.junit.Test;

public class DownloadInstall extends ResponseTimeMeasureBase {

	@Test
	public void DownloadInstallTest() throws Exception {
		TCID = "AppTunTime";
		result = "FAIL";
	
		double clickDownloadButtonTime = 0;
		double completeDownloadTime = 0;
		double completeInstallTime = 0;
		double downloadTime[] = new double[10];
		double installTime[] = new double[10];
		
		for (int i = 0; i < 3; i++) {
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
			
			Thread.sleep(2000);
			driver.findElementByXPath("//*[@text='다운로드']").click();
			clickDownloadButtonTime = System.nanoTime();
			
			try {
				driver.findElementById("com.skt.skaf.A000Z00040:id/btn1").click();
				clickDownloadButtonTime = System.nanoTime();
			} catch(Exception e) {}
			
			while (true) {
				try {
					driver.findElementByXPath("//*[@text='설치중']").isDisplayed();
					completeDownloadTime = System.nanoTime();
					break;
				} catch (Exception e) {}
			}
			
			while (true) {
				try {
					driver.findElementByXPath("//*[@text='실행']").isDisplayed();
					completeInstallTime = System.nanoTime();
					break;
				} catch (Exception e) {}
			}
			downloadTime[i] = (completeDownloadTime - clickDownloadButtonTime) / 1000000000.0;
			installTime[i] = (completeInstallTime - completeDownloadTime) / 1000000000.0;
			
			Runtime.getRuntime().exec("adb uninstall com.kakao.talk");
		}
		
		for (int i = 0; i < 3; i++) {
			System.out.print(String.format("%.2f", downloadTime[i]) + "\t");
		}	
		
		System.out.println();
		
		for (int i = 0; i < 3; i++) {
			System.out.print(String.format("%.2f", installTime[i]) + "\t");
		}		
		
		result = "PASS";

	}

}