package download.v7000;

import java.io.*;
import java.nio.IntBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import io.appium.java_client.android.AndroidElement;
import org.junit.Test;

import com.google.common.collect.ImmutableMap;
import lib.Cmd;

import java.util.List;


public class Download_SKT_E2E_Test extends BaseDriver_Download_SKT {
	public static int passcount ;
	public static double PcountSum = 0;
	/* -----------------------------------------------------------------------------
	 * @ Editor = Park Cheolmin
	 * @ TCID = Download_E2E
	 * -----------------------------------------------------------------------------
	 */	
	@Test
	public void Download_Daily_SKT() throws Exception {
		TCID = "Download_E2E_SKT";
		result = "FAIL";
		size = driver.manage().window().getSize();
		Cmd cmd = new Cmd();
		System.out.println(cmd.execCommand(cmd.inputCommand("adb -s " + UDID + " uninstall com.naver.labs.translator")).trim());
		String oscVer = cmd.execCommand(cmd.inputCommand("adb -s " + UDID + " shell dumpsys package com.skt.skaf.A000Z00040 | grep -m 1 versionName")).trim();
		String ossVer = cmd.execCommand(cmd.inputCommand("adb -s " + UDID + " shell dumpsys package com.skt.skaf.OA00018282 | grep -m 1 versionName")).trim();

		System.out.println("OSC " + oscVer);
		System.out.println("OSS " + ossVer);

		String productTitle = "네이버 파파고 - AI 통번역";
		String xPathProductTitle = "//*[@text='" + productTitle + "']";

		popupCheck();

		Thread.sleep(2000);
		driver.findElementById("com.skt.skaf.A000Z00040:id/btn_search").click(); // 검색 버튼 선택
		System.out.println("------searchButtonClick-------");

		Thread.sleep(2000);
		driver.findElementById("com.skt.skaf.A000Z00040:id/searchbar_keyword_edittext").click(); // 검색어 입력

		Thread.sleep(2000);
		driver.findElementById("com.skt.skaf.A000Z00040:id/searchbar_keyword_edittext").sendKeys(productTitle);
		System.out.println("------searchKeywordwrite-------");

		Thread.sleep(2000);
		driver.executeScript("mobile: performEditorAction", ImmutableMap.of("action", "search"));
		System.out.println("------searchiconClick-------");

		double startTimesearchEnter = System.nanoTime();
		System.out.println(startTimesearchEnter);

		int i = 0;
		while (i < 10000) {
			try {
				driver.findElementById("com.skt.skaf.A000Z00040:id/search_result_item_app_title").isDisplayed();
				break;
			} catch (Exception e) {
				Thread.sleep(10);
				i++;
			}
		}

		double endTimesearchEnter = System.nanoTime();

		System.out.println(endTimesearchEnter);

		double searchEnterResult = Double.parseDouble(String.format("%.2f",
				(((endTimesearchEnter - startTimesearchEnter) / 1000000000))));

		Thread.sleep(3000);
		List<AndroidElement> titleList = driver.findElementsById("com.skt.skaf.A000Z00040:id/search_result_item_app_title");

		for (AndroidElement title : titleList) {
			if (title.getText().equals(productTitle)) {
				title.click();
				break;
			}
		}

		System.out.println("------goodsDetailenter-------");

		double startTimeCompletein = System.nanoTime();
		System.out.println(startTimeCompletein);

		i = 0;
		while (i < 10000) {
			try {
				driver.findElementById("com.skt.skaf.A000Z00040:id/main_info_product_name_text_view").isDisplayed();
				break;
			} catch (Exception e) {
				Thread.sleep(10);
				i++;
			}
		}
		System.out.println("------goodsDetailenterComplete-------");

		double endTimeCompletein = System.nanoTime();
		System.out.println(endTimeCompletein);
		double CompleteEnterResult = Double.parseDouble(String.format("%.2f",
				(((endTimeCompletein - startTimeCompletein) / 1000000000))));

		Thread.sleep(3000);
		driver.findElementById("com.skt.skaf.A000Z00040:id/text").click(); // 다운로드 선택

		double startTimeCompletedownload = System.nanoTime();
		System.out.println(startTimeCompletedownload);

		try{
            driver.findElementById("com.skt.skaf.A000Z00040:id/btn1").click();
			Thread.sleep(3000);
		}catch(Exception e){

		}

		i = 0;
		while (i < 10000) {
			try {
				driver.findElementByXPath("//*[@text='실행']").isDisplayed();
				break;
			} catch (Exception e) {
				Thread.sleep(10);
				driver.findElementById("com.skt.skaf.A000Z00040:id/main_info_product_name_text_view");
				i++;
			}
		}

		double endTimeCompletedownload = System.nanoTime();
		System.out.println(endTimeCompletedownload);
		double CompletedownloadResult = Double.parseDouble(String.format("%.2f",
				((endTimeCompletedownload - startTimeCompletedownload) / 1000000000)));

		System.out.println("--------------------------------------------------");
		System.out.println(" / " + " searchEnter E2E Result / " + searchEnterResult + "s");
		System.out.println(" / " + " CompleteEnter E2E Result / " + CompleteEnterResult + "s");
		System.out.println(" / " + " Completedownload E2E Result / " + CompletedownloadResult + "s");
		System.out.println("--------------------------------------------------");
		result = "PASS";

		if (result.contains("PASS")){
			passcount++;
		}

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
		StringBuffer resultdata = new StringBuffer();
		if (result.contains("PASS")){
			String PassReselt = "<strong style=\"color:blue;\">PASS</strong>";
			resultdata.append("- " + TCID + ":" + PassReselt+ "<br>" + "수행 완료 시간 : " + time + "<br>");

		} else {
			String FailResult = "<strong style=\"color:red;\">FAIL</strong>";
			resultdata.append("- " + TCID + ":" + FailResult+ "<br>" + "수행 완료 시간 : " + time + "<br>");
		}


		try {
			PrintWriter pw = new PrintWriter(new FileWriter("C:\\Download_SKT_Result\\SKT_DL_result.txt", true));

			pw.write(resultdata.toString());

			pw.flush();
			pw.close();
		} catch (Exception e) {

		}

		StringBuffer Pcount = new StringBuffer();
		Pcount.append(String.valueOf(passcount)+ "\n");
		try {
			PrintWriter pw = new PrintWriter(new FileWriter("C:\\Download_SKT_Result\\PassCount.txt", true));

			pw.write(Pcount.toString());

			pw.flush();
			pw.close();
		} catch (Exception e) {

		}

	}
}