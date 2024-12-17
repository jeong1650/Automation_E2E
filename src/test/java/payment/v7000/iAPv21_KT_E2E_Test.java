package payment.v7000;

import lib.Cmd;
import org.junit.Test;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class iAPv21_KT_E2E_Test extends BaseDriver_iAPv21_KT {

	/* -----------------------------------------------------------------------------
	 * @ Editor = Jeong Inho
	 * @ TCID = iAPv21_Daily_KT
	 * -----------------------------------------------------------------------------
	*/
	@Test
	public void iAPv21_Daily_KT() throws Exception {
		TCID = "iAPv21_Daily_KT ";
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
		driver.findElementByXPath("//android.widget.TextView[@text=\"isBillingSupportedExtraParams\"]").click();;
		Thread.sleep(2000);

		driver.findElementByXPath("//android.widget.TextView[@text=\"getPurchaseIntentExtraParams\"]").click();
		System.out.println("-----Enter_getPurchaseIntent");
		Thread.sleep(2000);

		driver.findElementByXPath("//android.widget.EditText[@text=\"상품 아이디\"]").click();
		Thread.sleep(2000);

		driver.findElementByXPath("//android.widget.EditText[@text=\"상품 아이디\"]").sendKeys("p5001");
		System.out.println("-----Enter productID");
		Thread.sleep(2000);

		driver.findElementByXPath("//android.widget.TextView[@text=\"선택\"]").click();
		System.out.println("-----Select productType");
		Thread.sleep(2000);

		driver.findElementByXPath("//android.widget.TextView[@text=\"inapp\"]").click();
		System.out.println("-----Enter productType");
		Thread.sleep(2000);

		driver.findElementById("com.v21test.fun.portrait:id/menu_request").click();

		System.out.println("-----CallPurchaseAPI");

		double startTimeInappEnter = System.nanoTime();
		System.out.println(startTimeInappEnter);

		Thread.sleep(2000);
		totalThreadSleepTimeInapp += 2;
		driver.findElementByXPath("//android.view.View[@text=\"구독형 상품 (FUN-세로팝업)\"]").isDisplayed();

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

		driver.swipe(555, 2035, 555, 2035, 0);
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

		String PurchaseInfo = driver.findElementByXPath("(//android.widget.TextView[@resource-id=\"com.v21test.fun.portrait:id/text\"])[3]").getText();
		String Result[] = PurchaseInfo.split(":");

		String divide = Result[6];
		String divide_data[] = divide.split(",");

		String token = divide_data[0];
		String purchaseToken = token.substring(2,22);
		System.out.println("Token: " + purchaseToken);
		System.out.println("-----getpurchaseToken");

		System.out.println("--------------------------------------------------");
		System.out.println(" / " + "IAPv21 inappEnterTime E2E Result / " + inappEnterResult + "s");
		System.out.println(" / " + "IAPv21 CompletePayment E2E Result / " + CompletePayment + "s");
		System.out.println("--------------------------------------------------");

		Thread.sleep(2500);
		driver.findElementByXPath("//android.widget.TextView[@text=\"getPurchaseIntentExtraParams\"]").click();

		Thread.sleep(2500);
		driver.findElementByXPath("//android.widget.TextView[@text=\"consumePurchaseExtraParams\"]").click();
		System.out.println("-----Click_consumePurchase");

		Thread.sleep(2500);
		driver.findElementByXPath("(//android.widget.EditText[@resource-id=\"com.v21test.fun.portrait:id/value\"])[3]").click();
		Thread.sleep(2000);
		driver.findElementByXPath("(//android.widget.EditText[@resource-id=\"com.v21test.fun.portrait:id/value\"])[3]").sendKeys(purchaseToken);
		System.out.println("-----putPurchaseId");

		Thread.sleep(2500);
		driver.findElementById("com.v21test.fun.portrait:id/menu_request").click();
		System.out.println("-----CallConsumeAPI");

		Thread.sleep(3000);
		String ResponseCode = driver.findElementById("com.v21test.fun.portrait:id/text").getText();
		if (ResponseCode.contains("responseCode: 0, message: 성공")){
			System.out.println("-----SuccessConsume");
		} else {
			System.out.println("-----FailConsume");
		}

		ResultPrint();

		SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd kk:mm");
		Date date = new Date();
		String time = sdf.format(date);

		StringBuffer csv = new StringBuffer();

		csv.append(time + "," + inappEnterResult + "," + CompletePayment + "\n");

		try {
			PrintWriter pw = new PrintWriter(new FileWriter("C:\\PP_SKT_CSV\\SKT_v21.csv", true));

			pw.write(csv.toString());

			pw.flush();
			pw.close();
		} catch (Exception e) {

		}
	}
}
	