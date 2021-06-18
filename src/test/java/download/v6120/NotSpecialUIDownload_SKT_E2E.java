package download.v6120;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class NotSpecialUIDownload_SKT_E2E extends BaseDriver_Download_SKT {

	/*
	 * -----------------------------------------------------------------------------
	 * 
	 * @ Editor = Lim HyeongSun
	 * 
	 * @ TCID = search_in_download
	 * -----------------------------------------------------------------------------
	 */
	@Test
	public void NotSpecialUIDownload_Daily_SKT() throws Exception {
		TCID = "Not_Special_UI_Download_E2E_SKT ";
		result = "FAIL";
		size = driver.manage().window().getSize();

		double totalThreadSleepTime = 0;
		double totalThreadSleepTimesearch = 0;
		double totalThreadSleepTimedownload = 0;

		popupCheck();

		Thread.sleep(2000);
		driver.findElementById("com.skt.skaf.A000Z00040:id/actionbar_menu_search").click(); // 검색 버튼 선택
		System.out.println("------searchButtonClick-------");

		Thread.sleep(2000);
		driver.findElementById("com.skt.skaf.A000Z00040:id/searchbar_keyword_edittext").click(); // 검색어 입력
		driver.findElementById("com.skt.skaf.A000Z00040:id/searchbar_keyword_edittext").sendKeys("다방");
		System.out.println("------searchKeywordwrite-------");

		Thread.sleep(2000);
		driver.findElementById("com.skt.skaf.A000Z00040:id/searchbar_search_icon").click(); // 검색어 입력 후 버튼 선택
		System.out.println("------searchiconClick-------");

		double startTimesearchEnter = System.nanoTime();
		System.out.println(startTimesearchEnter);

		Thread.sleep(3000);
		totalThreadSleepTimesearch += 3;
		driver.findElementByXPath("//android.widget.TextView[@text='다방 - 대한민국 부동산 매물 최다보유']")
				.isDisplayed();

		double endTimesearchEnter = System.nanoTime();
		System.out.println(endTimesearchEnter);
		double searchEnterResult = Double.parseDouble(String.format("%.2f",
				(((endTimesearchEnter - startTimesearchEnter) / 1000000000) - totalThreadSleepTimesearch)));

		Thread.sleep(3000);
		driver.findElementByXPath("//android.widget.TextView[@text='다방 - 대한민국 부동산 매물 최다보유']")
				.click(); // 상품명 선택
		System.out.println("------goodsDetailenter-------");

		double startTimeCompletein = System.nanoTime();
		System.out.println(startTimeCompletein);

		Thread.sleep(3000);
		totalThreadSleepTime += 3;
		driver.findElementByXPath("//android.widget.TextView[@text='다방 - 대한민국 부동산 매물 최다보유']")
				.isDisplayed(); // 상품명 확인
		System.out.println("------goodsDetailenterComplete-------");

		double endTimeCompletein = System.nanoTime();
		System.out.println(endTimeCompletein);
		double CompleteEnterResult = Double.parseDouble(String.format("%.2f",
				(((endTimeCompletein - startTimeCompletein) / 1000000000) - totalThreadSleepTime)));

		Thread.sleep(3000);
		driver.findElementById("com.skt.skaf.A000Z00040:id/text").click(); // 다운로드 선택

		// Thread.sleep(3000);
		// driver.findElementByXPath("//android.widget.TextView[@text='30MB가 넘는 상품을 데이터
		// 네트워크로 다운로드하도록 요청하였습니다. 통화료가 발생할 수 있으니 주의하세요.']").isDisplayed();
		// driver.findElementById("com.skt.skaf.A000Z00040:id/buttonConfirm").click();
		// // 30MB 이상 다운로드 안내 팝업

		double startTimeCompletedownload = System.nanoTime();
		System.out.println(startTimeCompletedownload);

		Thread.sleep(50000);
		totalThreadSleepTime += 50;
		driver.findElementByXPath("//android.widget.TextView[@text='실행']").isDisplayed(); // 다운로드 => 실행 버튼으로 바뀜 확인

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
			PrintWriter pw = new PrintWriter(
					new FileWriter("C:\\Not_Special_UI_Download_SKT_CSV\\SKT_DL_E2E.csv", true));

			pw.write(csv.toString());

			pw.flush();
			pw.close();
		} catch (Exception e) {

		}
		System.out.println("close");

	}

}