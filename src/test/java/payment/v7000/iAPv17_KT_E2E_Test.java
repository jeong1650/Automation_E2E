package payment.v7000;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Date;

import lib.Cmd;
import org.junit.Test;

public class iAPv17_KT_E2E_Test extends BaseDriver_iAPv17_KT {

	/* -----------------------------------------------------------------------------
	 * @ Editor = Park Cheolmin
	 * @ TCID = iAPv17_Daily_SKT
	 * -----------------------------------------------------------------------------
	*/
	@Test
	public void iAPv17_Daily_KT() throws Exception {
		TCID = "iAPv17_Daily_KT ";
		result = "FAIL";
		size = driver.manage().window().getSize();

		Cmd cmd = new Cmd();
		String oscVer = cmd.execCommand(cmd.inputCommand("adb -s " + UDID + " shell dumpsys package com.kt.olleh.storefront | grep -m 1 versionName")).trim();
		String ossVer = cmd.execCommand(cmd.inputCommand("adb -s " + UDID + " shell dumpsys package com.skt.skaf.OA00018282 | grep -m 1 versionName")).trim();

		System.out.println("OSC " + oscVer);
		System.out.println("OSS " + ossVer);

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

		Thread.sleep(3000);
		totalThreadSleepTimeInapp += 3;
		driver.findElementByXPath("//*[@text='공백일 경우 등록된 상품명']").isDisplayed();
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
				System.out.println("-----CancelCouponPopupClose");
			} catch (Exception e) {
			}
		} catch (Exception e) {
		}

		try {
			driver.findElementById("btnToggleDcListItem_gamecash").isDisplayed();
			driver.findElementById("btnToggleDcListItem_gamecash").click();
			System.out.println("-----CancelGameCash");
			try {
				driver.findElementById("//android.view.View[@text='할인 적용을 취소하시겠습니까?']").isDisplayed();
				driver.findElementById("btnLayerPopConfirm").click();
				System.out.println("-----CancelGameCashPopupClose");
			} catch (Exception e) {
				driver.findElementById("btnToggleDcListItem_gamecash").click();
			}
		} catch (Exception e) {
		}

		Thread.sleep(3000);
		upSwipe(0.90);
		upSwipe(0.90);
		upSwipe(0.90);

		Thread.sleep(2000);
		driver.findElementByXPath("//*[@resource-id='btnSelectMainPaymethod_telebillcarrier']").click();
		System.out.println("-----ClickDcbButton");

		driver.findElementByXPath("//*[@text='위 내용을 확인하였으며 구매진행에 동의합니다.']").click();
		System.out.println("-----ClickPurchaseAgreement");

		Thread.sleep(3000);
		driver.swipe(550, 2050, 550, 2050, 0);  //결제버튼 SM-G73NK
//		driver.findElementByXPath("//*[@resource-id='divPaySubmitDiscountInfo']").click();// Swipe 인식이 되지 않아, resource-id 로 변경
		System.out.println("-----ClickPurchaseButton");
		try{
			Thread.sleep(2500);
			System.out.println("-----EnterPasswordPage");
			Password();
		}catch (Exception e){
			driver.swipe(550, 1930, 550, 1930, 0);
			Thread.sleep(2500);
			System.out.println("-----EnterPasswordPage");
			Password();
		}

		System.out.println("-----PassPassword");
		double startTimeCompletePayment = System.nanoTime();
		System.out.println(startTimeCompletePayment);

		Thread.sleep(2000);
		totalThreadSleepTime += 2;
		driver.findElementByXPath("//*[@text='결제 완료']").isDisplayed();
		double endTimeCompletePayment = System.nanoTime();
		System.out.println(endTimeCompletePayment);
		double CompletePayment = Double.parseDouble(String.format("%.2f",
				(((endTimeCompletePayment - startTimeCompletePayment) / 1000000000) - totalThreadSleepTime)));

		Thread.sleep(3000);
		upSwipe(0.70);
		Thread.sleep(3000);
		driver.findElementByXPath("//*[@text='확인']").click();

		Thread.sleep(2500);
		String purchaseId = driver.findElementById("com.onestore.iap.apisample.test1:id/tv_log").getText();
		System.out.println("PID: " + purchaseId);
		System.out.println("-----getPurchaseId");

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
		driver.findElementById("com.onestore.iap.apisample.test1:id/et_purchase_id").click();
		driver.findElementById("com.onestore.iap.apisample.test1:id/et_purchase_id").clear();
		driver.findElementById("com.onestore.iap.apisample.test1:id/et_purchase_id").sendKeys(purchaseId);
		System.out.println("-----putPurchaseId");

		Thread.sleep(7000);
		driver.findElementById("com.onestore.iap.apisample.test1:id/btn_api_call").click();
		System.out.println("-----CallConsumeAPI");
//   driver.findElementByXPath("//android.widget.Button[@text='호출']").isDisplayed();
//   System.out.println("findButton");
//   driver.findElementByXPath("//android.widget.Button[@text='호출']").click();

		Thread.sleep(3000);
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
			PrintWriter pw = new PrintWriter(new FileWriter("C:\\PP_KT_CSV\\KT_v17.csv", true));

			pw.write(csv.toString());

			pw.flush();
			pw.close();
		} catch (Exception e) {

		}
	}
}