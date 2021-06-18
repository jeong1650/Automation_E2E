package download.v7000;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

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
		driver.findElementById("com.skt.skaf.A000Z00040:id/searchbar_keyword_edittext").sendKeys("알약");
		System.out.println("------searchKeywordwrite-------");

		Thread.sleep(2000);
		driver.findElementById("com.skt.skaf.A000Z00040:id/searchbar_search_icon").click(); // 검색어 입력 후 버튼 선택
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

		Thread.sleep(50000);
		totalThreadSleepTime += 50;
		driver.findElementByXPath("//*[@text='실행']").isDisplayed(); // 다운로드 => 실행 버튼으로 바뀜 확인

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
		System.out.println("close");

	}

}