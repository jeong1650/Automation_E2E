package download.v7000;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.common.collect.ImmutableMap;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.spreadsheet.CellEntry;
import com.google.gdata.data.spreadsheet.CellFeed;
import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.data.spreadsheet.WorksheetFeed;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class Download_SKT_E2E extends BaseDriver_Download_SKT {

	/* -----------------------------------------------------------------------------
	 * @ Editor = Park Cheolmin
	 * @ TCID = Download_E2E
	 * -----------------------------------------------------------------------------
	 */	
	@Test
	public void Download_Daily_SKT() throws Exception {
		TCID = "Download_E2E_SKT ";
		result = "FAIL";
		size = driver.manage().window().getSize();

		double totalThreadSleepTime = 0;
		double totalThreadSleepTimesearch = 0;
		double totalThreadSleepTimedownload = 0;

		popupCheck();

		Thread.sleep(2000);
		driver.findElementById("com.skt.skaf.A000Z00040:id/btn_search").click(); // 검색 버튼 선택
		System.out.println("------searchButtonClick-------");

		Thread.sleep(2000);
		driver.findElementById("com.skt.skaf.A000Z00040:id/searchbar_keyword_edittext").click(); // 검색어 입력
		
		Thread.sleep(2000);
		driver.findElementById("com.skt.skaf.A000Z00040:id/searchbar_keyword_edittext").sendKeys("알약");
		System.out.println("------searchKeywordwrite-------");

		Thread.sleep(2000);
        driver.executeScript("mobile: performEditorAction", ImmutableMap.of("action", "search"));
		System.out.println("------searchiconClick-------");

		double startTimesearchEnter = System.nanoTime();
		System.out.println(startTimesearchEnter);

		Thread.sleep(3000);
		totalThreadSleepTimesearch += 3;
		driver.findElementByXPath("//*[@text='알약M - 파일 청소 및 메신저 파일 정리, 국내 1위 무료 바이러스+스미싱 보안']").isDisplayed();

		double endTimesearchEnter = System.nanoTime();
		System.out.println(endTimesearchEnter);
		double searchEnterResult = Double.parseDouble(String.format("%.2f",
				(((endTimesearchEnter - startTimesearchEnter) / 1000000000) - totalThreadSleepTimesearch)));

		Thread.sleep(3000);
		driver.findElementByXPath("//*[@text='알약M - 파일 청소 및 메신저 파일 정리, 국내 1위 무료 바이러스+스미싱 보안']").click(); // 상품명 선택
		System.out.println("------goodsDetailenter-------");

		double startTimeCompletein = System.nanoTime();
		System.out.println(startTimeCompletein);

		Thread.sleep(3000);
		totalThreadSleepTime += 3;
		driver.findElementByXPath("//*[@text='알약M - 파일 청소 및 메신저 파일 정리, 국내 1위 무료 바이러스+스미싱 보안']").isDisplayed(); // 상품명 확인
		System.out.println("------goodsDetailenterComplete-------");

		double endTimeCompletein = System.nanoTime();
		System.out.println(endTimeCompletein);
		double CompleteEnterResult = Double.parseDouble(String.format("%.2f",
				(((endTimeCompletein - startTimeCompletein) / 1000000000) - totalThreadSleepTime)));

		Thread.sleep(3000);
		driver.findElementById("com.skt.skaf.A000Z00040:id/text").click(); // 다운로드 선택

		double startTimeCompletedownload = System.nanoTime();
		System.out.println(startTimeCompletedownload);

		while(true) {
			try {
				driver.findElementByXPath("//*[@text='실행']").isDisplayed();
				break;
			} catch(Exception e) {}
		}
		
		double endTimeCompletedownload = System.nanoTime();
		
		System.out.println(endTimeCompletedownload);
		double CompletedownloadResult = Double.parseDouble(String.format("%.2f",
				(((endTimeCompletedownload - startTimeCompletedownload) / 1000000000) - totalThreadSleepTimedownload)));

		System.out.println("--------------------------------------------------");
		System.out.println(" / " + " searchEnter E2E Result / " + searchEnterResult + "s");
		System.out.println(" / " + " CompleteEnter E2E Result / " + CompleteEnterResult + "s");
		System.out.println(" / " + " Completedownload E2E Result / " + CompletedownloadResult + "s");
		System.out.println("--------------------------------------------------");

		ResultPrint();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd kk:mm");
		Date date = new Date();
		String time = sdf.format(date);

		StringBuffer csv = new StringBuffer();
		csv.append(time + "," + searchEnterResult + "," + CompleteEnterResult + "," + CompletedownloadResult + "\n");

		try {
			PrintWriter pw = new PrintWriter(new FileWriter("C:\\Download_SKT_CSV\\SKT_DL_E2E.csv", true));

			pw.write(csv.toString());

			pw.flush();
			pw.close();
		} catch (Exception e) {

		}
		
		String sheetId = "1ctXLYn6qQcl20ZbthEWSuusnyaBXf_m9NeeyRmDgQsM"; // 시트 Key
        String sheetName = new SimpleDateFormat("yyyy-MM").format(System.currentTimeMillis()); // 년-월 단위 분리를 위한 시트명 생성
        String keyFileName = "norse-bond-259605-eb1188cf3bcf.p12";
        String id = "test2-165@norse-bond-259605.iam.gserviceaccount.com";
        
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
			cellEntry = new CellEntry(1, 2, "SearchEnter");
			cellFeed.insert(cellEntry);
			cellEntry = new CellEntry(1, 3, "CompleteEnter");
			cellFeed.insert(cellEntry);
			cellEntry = new CellEntry(1, 4, "Completedownload");
			cellFeed.insert(cellEntry);
		}		

		// Fetch the list feed of the worksheet.
		URL listFeedUrl = worksheet.getListFeedUrl();
		service.getFeed(listFeedUrl, ListFeed.class);
        
        ListEntry row = new ListEntry();
        row.getCustomElements().setValueLocal("Time", time);
        row.getCustomElements().setValueLocal("SearchEnter", String.format("%.2f",searchEnterResult));
        row.getCustomElements().setValueLocal("CompleteEnter", String.format("%.2f",CompleteEnterResult));
        row.getCustomElements().setValueLocal("Completedownload", String.format("%.2f",CompletedownloadResult));
        service.insert(listFeedUrl, row);
   
		
		System.out.println("close");

	}
	
	  public static URL getFeedWorksheetsURL(String sheetId) throws Exception {
	    	return new URL("https://spreadsheets.google.com/feeds/worksheets/" + sheetId + "/private/full");
	    }

}