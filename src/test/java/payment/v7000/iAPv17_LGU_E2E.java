package payment.v7000;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.spreadsheet.CellEntry;
import com.google.gdata.data.spreadsheet.CellFeed;
import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.data.spreadsheet.WorksheetFeed;

public class iAPv17_LGU_E2E extends BaseDriver_iAPv17_LGU {

	/* -----------------------------------------------------------------------------
	 * @ Editor = Park Cheolmin
	 * @ TCID = iAPv17_Daily_SKT
	 * -----------------------------------------------------------------------------
	*/
	@Test
	public void iAPv17_Daily_LGU() throws Exception {
		TCID = "iAPv17_Daily_LGU ";
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
		driver.findElementByXPath("//android.view.View[@text='공백일 경우 등록된 상품명']").isDisplayed();
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

		Thread.sleep(1500);
		upSwipe(0.90);

		Thread.sleep(2000);
		driver.findElementByXPath("//android.view.View[@resource-id='btnSelectMainPaymethod_telebillcarrier']").click();
		System.out.println("-----ClickDcbButton");

		driver.findElementByXPath("//android.view.View[@text='위 내용을 확인하였으며 구매진행에 동의합니다.']").click();
		System.out.println("-----ClickPurchaseAgreement");

		Thread.sleep(2000);
		driver.swipe(700, 2050, 700, 2050, -1); // 결제버튼 SM-G925L
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

		Thread.sleep(3000);
		upSwipe(0.70);
		
		Thread.sleep(3000);
		driver.findElementByXPath("//*[@text='확인']").click();

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
			PrintWriter pw = new PrintWriter(new FileWriter("C:\\PP_LGU_CSV\\LGU_v17.csv", true));

			pw.write(csv.toString());

			pw.flush();
			pw.close();
		} catch (Exception e) {

		}
		
		String sheetId = "1dou3BOX9bmTQ6XjtspX1-7V-tbmxFJ--wOrltiyW1bE"; // 시트 Key
        String sheetName = new SimpleDateFormat("yyyy-MM").format(System.currentTimeMillis()); // 년-월 단위 분리를 위한 시트명 생성
        String keyFileName = "norse-bond-259605-5482c1faa5f5.p12";
        String id = "test-957@norse-bond-259605.iam.gserviceaccount.com";
        
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        URL url = classloader.getResource(keyFileName); // p12 Key
        final File file = new File(url.getFile());
        
        // 이하 인증부 계정 외 수정 금지
        HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
        JsonFactory JSON_FACTORY = new JacksonFactory();

        Credential credential = new GoogleCredential.Builder().setTransport(HTTP_TRANSPORT)
                .setJsonFactory(JSON_FACTORY)
                .setServiceAccountId(id) // 생성 계정
                .setTokenServerEncodedUrl("https://accounts.google.com/o/oauth2/token")
                .setServiceAccountScopes(Arrays.asList("https://www.googleapis.com/auth/drive", "https://spreadsheets.google.com/feeds", "https://docs.google.com/feeds"))
                .setServiceAccountPrivateKeyFromP12File(file).build();

        SpreadsheetService service = new SpreadsheetService("MySpreadsheetIntegration-v1");
        service.setOAuth2Credentials(credential); 
        service.getFeed(new URL("https://spreadsheets.google.com/feeds/spreadsheets/private/full"), SpreadsheetFeed.class);
        // 인증부 종료
        
        // 문서 접근
        WorksheetEntry worksheet = null;
        
		WorksheetFeed worksheetFeed = service.getFeed(getFeedWorksheetsURL(sheetId), WorksheetFeed.class);
		List<WorksheetEntry> worksheets = worksheetFeed.getEntries();

		for(WorksheetEntry ws : worksheets) { // 해당 시트 탐색
			if(ws.getTitle().getPlainText().equals(sheetName)) {
				worksheet = ws;
				break;
			}
		}
		
		if (worksheet == null) { // 해당 시트 없을 시 시트 생성
			worksheet = new WorksheetEntry();
			worksheet.setTitle(new PlainTextConstruct(sheetName));
			worksheet.setColCount(10);
			worksheet.setRowCount(1000);
			worksheet = service.insert(getFeedWorksheetsURL(sheetId), worksheet);
			URL cellFeedUrl = worksheet.getCellFeedUrl();
			CellFeed cellFeed = service.getFeed(cellFeedUrl, CellFeed.class);
			CellEntry cellEntry = new CellEntry(1, 1, "Time");
			cellFeed.insert(cellEntry);
			cellEntry = new CellEntry(1, 2, "InappEnterResult");
			cellFeed.insert(cellEntry);
			cellEntry = new CellEntry(1, 3, "CompletePayment");
			cellFeed.insert(cellEntry);
		}		
		csv.append(time + "," + inappEnterResult + "," + CompletePayment + "\n");
		// Fetch the list feed of the worksheet.
		URL listFeedUrl = worksheet.getListFeedUrl();
		service.getFeed(listFeedUrl, ListFeed.class);
        
        ListEntry row = new ListEntry();
        row.getCustomElements().setValueLocal("Time", time);
        row.getCustomElements().setValueLocal("InappEnterResult", String.format("%.2f",inappEnterResult));
        row.getCustomElements().setValueLocal("CompletePayment", String.format("%.2f",CompletePayment));
        
        service.insert(listFeedUrl, row);
		
		System.out.println("close");
	}
	
	 public static URL getFeedWorksheetsURL(String sheetId) throws Exception {
	    	return new URL("https://spreadsheets.google.com/feeds/worksheets/" + sheetId + "/private/full");
	    }

}
	