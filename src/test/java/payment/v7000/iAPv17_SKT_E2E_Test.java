package payment.v7000;

import static org.junit.Assert.*;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import lib.Cmd;
import org.junit.Test;

public class iAPv17_SKT_E2E_Test extends BaseDriver_iAPv17_SKT {

	/* -----------------------------------------------------------------------------
	 * @ Editor = Jeong Inho
	 * @ TCID = iAPv17_Daily_SKT
	 * -----------------------------------------------------------------------------
	*/
	@Test
	public void iAPv17_Daily_SKT() throws Exception {
		TCID = "iAPv17_Daily_SKT ";
		result = "FAIL";
		size = driver.manage().window().getSize();

		Cmd cmd = new Cmd();
		String oscVer = cmd.execCommand(cmd.inputCommand("adb -s " + UDID + " shell dumpsys package com.skt.skaf.A000Z00040 | grep -m 1 versionName")).trim();
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
		   driver.findElementByXPath("//*[@content-desc='혜택 조회']").isDisplayed();
		   System.out.println("-----No Coupon");
		} catch (Exception e) {
			driver.findElementByXPath("//*[@text='혜택']").click();
			Thread.sleep(1500);
			driver.findElementByXPath("//*[@text='포인트 삭제']").click();
			Thread.sleep(1500);
			driver.findElementByXPath("//android.widget.TextView[@text='할인 적용을 취소하시겠습니까?']").isDisplayed();
			driver.findElementByXPath("//*[@text='확인']").click();
			Thread.sleep(1500);
			driver.findElementByXPath("//*[@text='이전페이지']").click();
			Thread.sleep(1500);
			System.out.println("-----Coupon Cancel");
		}

		driver.swipe(537, 2127, 537, 2127, -1); // 결제버튼 SM-G991N / Galaxy S21
		System.out.println("-----ClickPurchaseButton");
		Thread.sleep(2500);

		try{
			driver.findElementByXPath("//*[@text='원스토어 통신과금서비스 이용약관에 동의하십니까?']").isDisplayed();
			driver.findElementByXPath("//*[@text='동의']").click();
			Thread.sleep(1500);
		}catch (Exception e){
			System.out.println("-----AgreeState");
		}

		System.out.println("-----EnterPasswordPage");
		Password();

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

		result = "PASS";

		Thread.sleep(4000);
		upSwipe(0.70);

		Thread.sleep(4000);
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

		Thread.sleep(2500);
		driver.findElementById("com.onestore.iap.apisample.test1:id/btn_api_call").click();
		System.out.println("-----CallConsumeAPI");

		Thread.sleep(3000);
		assertEquals("0 : 성공", driver.findElementById("com.onestore.iap.apisample.test1:id/tv_log").getText());
		System.out.println("-----SuccessConsume");

		ResultPrint();

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
	}
}
	