package osc_e2e.payment.v6120;

import static org.junit.Assert.*;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class iAPv17_SKT_E2E extends BaseDriver_iAPv17_SKT {

	/* -----------------------------------------------------------------------------
	 * @ Editor = KimKwangHyun M
	 * @ TCID = iAPv17_Daily_SKT
	 * -----------------------------------------------------------------------------
	*/
	@Test
	public void iAPv17_Daily_SKT() throws Exception {
		TCID = "iAPv17_Daily_SKT ";
		result = "FAIL";
		size = driver.manage().window().getSize();

		double totalThreadSleepTime = 0;
		double totalThreadSleepTimeInapp = 0;

		Thread.sleep(2000);
		driver.findElementById("com.onestore.iap.apisample.test1:id/spinner_api_list").click();

		Thread.sleep(2000);
		driver.findElementByXPath("//android.widget.CheckedTextView[@text='getPurchaseIntent()']").click();
		System.out.println("-----Enter_getPurchaseIntent");

		Thread.sleep(2000);
		driver.findElementById("com.onestore.iap.apisample.test1:id/btn_api_default").click();
		System.out.println("-----Click_btn_api_default");

		Thread.sleep(2000);
		driver.findElementById("com.onestore.iap.apisample.test1:id/btn_api_call").click();

		System.out.println("-----CallPurchaseAPI");

		double startTimeInappEnter = System.nanoTime();
		System.out.println(startTimeInappEnter);

		Thread.sleep(2000);
		totalThreadSleepTimeInapp += 2;
		driver.findElementByXPath("//android.widget.TextView[@text='공백일 경우 등록된 상품명']").isDisplayed();

		System.out.println("-----EnterPaymentPage");

		double endTimeInappEnter = System.nanoTime();
		System.out.println(endTimeInappEnter);
		double inappEnterResult = Double.parseDouble(String.format("%.2f",
				(((endTimeInappEnter - startTimeInappEnter) / 1000000000) - totalThreadSleepTimeInapp)));

		Thread.sleep(1500);
		try {
			driver.findElementById("btnChangeCoupon").isDisplayed();
			driver.findElementById("btnToggleDcListItem_tcoupon").click();
			System.out.println("-----CancelCoupon");

			try {
				driver.findElementByXPath("//android.view.View[@text='쿠폰 적용을 취소하시겠습니까?']").isDisplayed();
				driver.findElementById("btnLayerPopConfirm").click();
				System.out.println("-----CancleCouponPopupClose");
			} catch (Exception e) {
			}
		} catch (Exception e) {
		}

		try {
			driver.findElementById("btnToggleDcListItem_gamecash").isDisplayed();
			driver.findElementById("btnToggleDcListItem_gamecash").click();
			System.out.println("-----CancleGameCash");

			try {
				driver.findElementById("//android.view.View[@text='할인 적용을 취소하시겠습니까?']").isDisplayed();
				driver.findElementById("btnLayerPopConfirm").click();
				System.out.println("-----CancleGameCashPopupClose");
			} catch (Exception e) {
				driver.findElementById("btnToggleDcListItem_gamecash").click();
			}
		} catch (Exception e) {
		}

		Thread.sleep(1500);
		upSwipe(0.90);

		Thread.sleep(2000);
		driver.findElementByXPath("//android.view.View[@resource-id='btnSelectMainPaymethod_telebillcarrier']").click();
		System.out.println("-----ClickDcbButton");

		driver.findElementByXPath("//android.widget.TextView[@text='위 내용을 확인하였으며 구매진행에 동의합니다.']").click();
		System.out.println("-----ClickPurchaseAgreement");

		Thread.sleep(2000);
		driver.swipe(550, 2050, 550, 2050, -1); // 결제버튼 SM-N971N / Galaxy Note10

		System.out.println("-----ClickPurchaseButton");

		Thread.sleep(2000);
		try {
			if (driver.findElementByXPath("//android.view.View[@text='SK텔레콤 휴대폰결제 이용안내']").isDisplayed())
				;
			Thread.sleep(2500);
			driver.findElementByXPath("//android.view.View[@text='동의']").click();
		} catch (Exception d) {
		}

		Thread.sleep(2500);
		System.out.println("-----EnterPasswordPage");
		Password();
		System.out.println("-----PassPassword");
		double startTimeCompletePayment = System.nanoTime();
		System.out.println(startTimeCompletePayment);

		Thread.sleep(2000);
		totalThreadSleepTime += 2;
		driver.findElementByXPath("//android.view.View[@text='결제 완료']").isDisplayed();
		double endTimeCompletePayment = System.nanoTime();
		System.out.println(endTimeCompletePayment);
		double CompletePayment = Double.parseDouble(String.format("%.2f",
				(((endTimeCompletePayment - startTimeCompletePayment) / 1000000000) - totalThreadSleepTime)));

		Thread.sleep(4000);
		upSwipe(0.70);
		Thread.sleep(4000);
// 		driver.findElementById("btnPayResultConfirm").click();
//   	driver.swipe(550, 1360, 550, 1360, 0);
		driver.swipe(530, 1800, 530, 1800, 0);

		System.out.println("--------------------------------------------------");
		System.out.println(" / " + "IAPv17 inappEnterTime E2E Result / " + inappEnterResult + "s");
		System.out.println(" / " + "IAPv17 CompletePayment E2E Result / " + CompletePayment + "s");
		System.out.println("--------------------------------------------------");

		Thread.sleep(2500);
		driver.findElementById("com.onestore.iap.apisample.test1:id/spinner_api_list").click();

		Thread.sleep(2500);
		driver.findElementByXPath("//android.widget.CheckedTextView[@text='consumePurchase()']").click();
		System.out.println("-----Click_consumePurchase");

		Thread.sleep(2500);
		String purchaseId = driver.findElementById("com.onestore.iap.apisample.test1:id/tv_log").getText();
		System.out.println("-----getPurchaseId");

		Thread.sleep(2500);
		driver.findElementById("com.onestore.iap.apisample.test1:id/et_purchase_id").click();
		driver.findElementById("com.onestore.iap.apisample.test1:id/et_purchase_id").clear();
		driver.findElementById("com.onestore.iap.apisample.test1:id/et_purchase_id").sendKeys(purchaseId);
		System.out.println("-----putPurchaseId");

		Thread.sleep(2500);
		driver.findElementById("com.onestore.iap.apisample.test1:id/btn_api_call").click();
		System.out.println("-----CallConsumeAPI");

		Thread.sleep(2500);
		assertEquals("0 : 성공", driver.findElementById("com.onestore.iap.apisample.test1:id/tv_log").getText());
		System.out.println("-----SuccessConsume");

		ResultPrint();
		result = "PASS";

		SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd kk:mm");
		Date date = new Date();
		String time = sdf.format(date);

		StringBuffer csv = new StringBuffer();

		csv.append(time + "," + inappEnterResult + "," + CompletePayment + "\n");

		try {
			PrintWriter pw = new PrintWriter(new FileWriter("C:\\PP_SKT_CSV\\SKT_v17.csv", true));

			pw.write(csv.toString());

			pw.flush();
			pw.close();
		} catch (Exception e) {

		}
		System.out.println("close");

	}

}
