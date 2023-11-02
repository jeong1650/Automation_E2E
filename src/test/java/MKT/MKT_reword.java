package MKT;

import org.junit.Test;
import org.openqa.selenium.By;

public class MKT_reword extends OSC_Base_text {

	/* -----------------------------------------------------------------------------
	 * @ Writer = Jeong In-Ho
	 * @ Date = 2021-11-30
	 * @ ExpectedResult = 테스트 후 마케팅 사전예약 초기화
	 * @ Editor =
	 * @ EditDate =
	 * @
	 * -----------------------------------------------------------------------------
	 */

	@Test
	public void RewordCheck() throws Exception {
		TCID = "RewordCheck";
		result = "FAIL";
		size = driver.manage().window().getSize();
		popupCheck();
        Thread.sleep(3000);
		try {
			driver.findElementById("com.skt.skaf.A000Z00040:id/btn_benefit_layout").click();
			Thread.sleep(3000);
		}catch (Exception e){
			driver.findElementById("com.skt.skaf.A000Z00040:id/btn_benefit").click();
			Thread.sleep(3000);
		}

		try {
			driver.findElementByXPath("//android.view.View[@text='날마다 혜택 체크']").click();
			Thread.sleep(3000);
		}catch (Exception e){
			driver.findElementByXPath("//*[@resource-id='lottie_1']").click();
			Thread.sleep(3000);
		}
		downscroll(By.xpath("//android.widget.TextView[@text='오늘의 혜택 받기']")).click();

		Thread.sleep(2000);
	
		driver.findElementByXPath("//android.widget.TextView[@text='오늘의 혜택 받기 완료']").isDisplayed();
		
		result = "PASS";
	}
}