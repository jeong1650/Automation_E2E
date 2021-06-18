package responsetime.v6000;
import static org.junit.Assert.*;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Response_time_OSC {	
   
   public static AndroidDriver<AndroidElement> driver;
   public static String result = "";
   public static String TCID = "";
   String productname;
   WebElement element;
   
   @Before
   public void setUp() throws Exception {
       DesiredCapabilities capabilities = new DesiredCapabilities();
       capabilities.setCapability("deviceName", "appium");
       capabilities.setCapability("unicodeKeyboard", true);
       capabilities.setCapability("appPackage", "com.skt.skaf.A000Z00040");
       capabilities.setCapability("appActivity", ".A000Z00040");
       capabilities.setCapability("platformVersion", "7.1.2");
       driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
       driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
             
   }
                
   @After
   public void sessionexit() throws Exception{
   driver.quit();
   }

   public void Check() throws Exception{
       try
         {
           if(driver.findElementById("com.skt.skaf.A000Z00040:id/event_popup_close").isDisplayed())
               driver.findElementById("com.skt.skaf.A000Z00040:id/event_popup_close").click();      
       }
       catch(Exception e){
    	 
          }

       try
      {            
           if(driver.findElementById("com.skt.skaf.A000Z00040:id/btn_member_change_id_skip").isDisplayed())
               driver.findElementById("com.skt.skaf.A000Z00040:id/btn_member_change_id_skip").click();
       }
       catch(Exception e){
       }      
   }   
   

//---------------------------------------------------------------------------------------------------------------------------
   // 작성자 = 김광현M
   // TEST Title = 홈 > 게임카테고리 응답시간
   // 예상 결과 =
//---------------------------------------------------------------------------------------------------------------------------
   
   @Test
   public void RUN001() throws Exception{
   double resultTime = 0;
   int count = 0;
   for (int i = 0; i < 10; i++) {
	   try{
		   double totalThreadSleepTime = 0;	   
		   Check();  
		   
		   driver.findElementById("com.skt.skaf.A000Z00040:id/home_logo").isDisplayed();
		   
		   double startTime = System.nanoTime();
		   System.out.println(startTime);
//--------------------------------------------------------------------TC--------------------------------------------------------------------
		   driver.findElementById("com.skt.skaf.A000Z00040:id/carditem_category_1").click();
   
		   Thread.sleep(2000);
		   totalThreadSleepTime += 2;
		   assertEquals("게임", driver.findElementById("com.skt.skaf.A000Z00040:id/category_text").getText());
		   driver.findElementByXPath("//android.widget.TextView[@text='랭킹']").isDisplayed();
//--------------------------------------------------------------------TC--------------------------------------------------------------------
		   double endTime = System.nanoTime();
		   System.out.println(endTime);
		   double RS = Double.parseDouble(String.format("%.2f",(((endTime - startTime)/1000000000) - totalThreadSleepTime)));
		   resultTime += RS;
		   System.out.println("--------------------------------------------------");
		   count++;
		   System.out.println(count + " / " + "게임 응답시간 / " + RS + "초");
		   System.out.println("--------------------------------------------------");
		   
		   Thread.sleep(2000);
		   setUp();
		   
  			} catch (Exception e) {
  				count++;
  				System.out.println("******************************");
  				System.out.println(count + " / " + "Fail");
  				System.out.println("******************************");
  				
  				Thread.sleep(2000);
  				setUp();
  				continue;
  			}
    	}   
   System.out.println("--------------------------------------------------");
   System.out.println("원스토어 게임 카테고리 진입 평균 응답시간  /  " + String.format("%.2f",(resultTime/10)));
   System.out.println("--------------------------------------------------");   
   
   }
  
//---------------------------------------------------------------------------------------------------------------------------
   // 작성자 = 김광현M
   // TEST Title = 홈 > 앱카테고리 응답시간
   // 예상 결과 =
//---------------------------------------------------------------------------------------------------------------------------
   @Test
   public void RUN002() throws Exception{
   double resultTime = 0;
   int count = 0;
   for (int i = 0; i < 10; i++) {
	   try {			
		   double totalThreadSleepTime = 0;	   
		   Check();  
			   
		   driver.findElementById("com.skt.skaf.A000Z00040:id/home_logo").isDisplayed();
			   
		   double startTime = System.nanoTime();
		   System.out.println(startTime);
//--------------------------------------------------------------------TC--------------------------------------------------------------------
		   driver.findElementById("com.skt.skaf.A000Z00040:id/carditem_category_2").click();
   
		   Thread.sleep(2000);
		   totalThreadSleepTime += 2;
		   assertEquals("앱", driver.findElementById("com.skt.skaf.A000Z00040:id/category_text").getText());
		   driver.findElementByXPath("//android.widget.TextView[@text='랭킹']").isDisplayed();
//--------------------------------------------------------------------TC--------------------------------------------------------------------   
		   double endTime = System.nanoTime();
		   System.out.println(endTime);
		   double RS = Double.parseDouble(String.format("%.2f",(((endTime - startTime)/1000000000) - totalThreadSleepTime)));
		   resultTime += RS;
		   System.out.println("--------------------------------------------------");
		   count++;
		   System.out.println(count + " / " + "App 응답시간 / " + RS + "초");
		   System.out.println("--------------------------------------------------");
		   
		   Thread.sleep(2000);
		   setUp();

	   		}catch (Exception e) {
		   		count++;
		   		System.out.println("******************************");
		   		System.out.println(count + " / " + "Fail");
		   		System.out.println("******************************");
		   		
		   		Thread.sleep(2000);
		   		setUp();
		   		continue;
	   		}	   
   		}
   System.out.println("--------------------------------------------------");
   System.out.println("원스토어 App 카테고리 진입 평균 응답시간  /  " + String.format("%.2f",(resultTime/10)));
   System.out.println("--------------------------------------------------");
   
   }
   
//---------------------------------------------------------------------------------------------------------------------------
   // 작성자 = 김광현M
   // TEST Title = 홈 > TV방송 카테고리 응답시간
   // 예상 결과 =
//---------------------------------------------------------------------------------------------------------------------------
   @Test
   public void RUN003() throws Exception{
   double resultTime = 0;
   int count = 0;
   for (int i = 0; i < 10; i++) {
	   try {
		   double totalThreadSleepTime = 0;	   
		   Check();  
				   
		   driver.findElementById("com.skt.skaf.A000Z00040:id/home_logo").isDisplayed();
				   
		   double startTime = System.nanoTime();
		   System.out.println(startTime);
//--------------------------------------------------------------------TC--------------------------------------------------------------------
		   driver.findElementById("com.skt.skaf.A000Z00040:id/carditem_category_3").click();
   
		   Thread.sleep(2000);
		   totalThreadSleepTime += 2;
		   assertEquals("TV방송", driver.findElementById("com.skt.skaf.A000Z00040:id/category_text").getText());
		   driver.findElementByXPath("//android.widget.TextView[@text='랭킹']").isDisplayed();
//--------------------------------------------------------------------TC--------------------------------------------------------------------
		   double endTime = System.nanoTime();
		   System.out.println(endTime);
		   double RS = Double.parseDouble(String.format("%.2f",(((endTime - startTime)/1000000000) - totalThreadSleepTime)));
		   resultTime += RS;
		   System.out.println("--------------------------------------------------");
		   count++;
		   System.out.println(count + " / " + "TV방송 응답시간 / " + RS + "초");
		   System.out.println("--------------------------------------------------");
		   
		   Thread.sleep(2000);
		   setUp();

  			}catch (Exception e) {
  				count++;
  				System.out.println("******************************");
  				System.out.println(count + " / " + "Fail");
  				System.out.println("******************************");
  				
  				Thread.sleep(2000);
  				setUp();
  				continue;
  			}	   
   		}
   System.out.println("--------------------------------------------------");
   System.out.println("원스토어 TV방송 카테고리 진입 평균 응답시간  /  " + String.format("%.2f",(resultTime/10)));
   System.out.println("--------------------------------------------------");
   
   }
   
//---------------------------------------------------------------------------------------------------------------------------
   // 작성자 = 김광현M
   // TEST Title = 홈 > 영화 카테고리 응답시간
   // 예상 결과 =
//---------------------------------------------------------------------------------------------------------------------------
   @Test
   public void RUN004() throws Exception{
   double resultTime = 0;
   int count = 0;
   for (int i = 0; i < 10; i++) {
	   try {
		   double totalThreadSleepTime = 0;
		   Check();  
					   
		   driver.findElementById("com.skt.skaf.A000Z00040:id/home_logo").isDisplayed();
					   
		   double startTime = System.nanoTime();
		   System.out.println(startTime);
//--------------------------------------------------------------------TC--------------------------------------------------------------------
		   driver.findElementById("com.skt.skaf.A000Z00040:id/carditem_category_4").click();
		   
		   Thread.sleep(2000);
		   totalThreadSleepTime += 2;
		   assertEquals("영화", driver.findElementById("com.skt.skaf.A000Z00040:id/category_text").getText());
		   driver.findElementByXPath("//android.widget.TextView[@text='랭킹']").isDisplayed();
//--------------------------------------------------------------------TC--------------------------------------------------------------------
		   double endTime = System.nanoTime();
		   System.out.println(endTime);
		   double RS = Double.parseDouble(String.format("%.2f",(((endTime - startTime)/1000000000) - totalThreadSleepTime)));
		   resultTime += RS;
		   System.out.println("--------------------------------------------------");
		   count++;
		   System.out.println(count + " / " + "영화 응답시간 / " + RS + "초");
		   System.out.println("--------------------------------------------------");
		   
		   Thread.sleep(2000);
		   setUp();

	   		}catch (Exception e) {
	   			count++;
	   			System.out.println("******************************");
	   			System.out.println(count + " / " + "Fail");
	   			System.out.println("******************************");
	   			
	   			Thread.sleep(2000);
	   			setUp();
	   			continue;
	   		}	   
   		}
   System.out.println("--------------------------------------------------");
   System.out.println("원스토어 영화 카테고리 진입 평균 응답시간  /  " + String.format("%.2f",(resultTime/10)));
   System.out.println("--------------------------------------------------");
   
   }
   
//---------------------------------------------------------------------------------------------------------------------------
   // 작성자 = 김광현M
   // TEST Title = 홈 > 북스 카테고리 응답시간
   // 예상 결과 =
//---------------------------------------------------------------------------------------------------------------------------
   @Test
   public void RUN005() throws Exception{
   double resultTime = 0;
   int count = 0;
   for (int i = 0; i < 10; i++) {
	   try {
		   double totalThreadSleepTime = 0;
		   Check();  
					   
		   driver.findElementById("com.skt.skaf.A000Z00040:id/home_logo").isDisplayed();
						   
		   double startTime = System.nanoTime();
		   System.out.println(startTime);
//--------------------------------------------------------------------TC--------------------------------------------------------------------
		   driver.findElementById("com.skt.skaf.A000Z00040:id/carditem_category_5").click();
   
		   Thread.sleep(2000);
		   totalThreadSleepTime += 2;
		   assertEquals("북스", driver.findElementById("com.skt.skaf.A000Z00040:id/category_text").getText());
		   driver.findElementByXPath("//android.widget.TextView[@text='랭킹']").isDisplayed();
//--------------------------------------------------------------------TC--------------------------------------------------------------------
		   double endTime = System.nanoTime();
		   System.out.println(endTime);
		   double RS = Double.parseDouble(String.format("%.2f",(((endTime - startTime)/1000000000) - totalThreadSleepTime)));
		   resultTime += RS;
		   System.out.println("--------------------------------------------------");
		   count++;
		   System.out.println(count + " / " + "북스 응답시간 / " + RS + "초");
		   System.out.println("--------------------------------------------------");
		   
		   Thread.sleep(2000);
		   setUp();

	   		}catch (Exception e) {
		   		count++;
  				System.out.println("******************************");
  				System.out.println(count + " / " + "Fail");
  				System.out.println("******************************");
  				
  				Thread.sleep(2000);
  				setUp();
  				continue;
	   		}	   
   		}
   System.out.println("--------------------------------------------------");
   System.out.println("원스토어 북스 카테고리 진입 평균 응답시간  /  " + String.format("%.2f",(resultTime/10)));
   System.out.println("--------------------------------------------------");  
   
   }
   
//---------------------------------------------------------------------------------------------------------------------------
   // 작성자 = 김광현M
   // TEST Title = 홈 > 웹툰 카테고리 응답시간
   // 예상 결과 =
//---------------------------------------------------------------------------------------------------------------------------
   @Test
   public void RUN006() throws Exception{
   double resultTime = 0;
   int count = 0;
   for (int i = 0; i < 10; i++) {
	   try {
		   double totalThreadSleepTime = 0;	   
		   Check();  
						   
		   driver.findElementById("com.skt.skaf.A000Z00040:id/home_logo").isDisplayed();
							   
		   double startTime = System.nanoTime();
		   System.out.println(startTime);
//--------------------------------------------------------------------TC--------------------------------------------------------------------
		   driver.findElementById("com.skt.skaf.A000Z00040:id/carditem_category_6").click();
   
		   Thread.sleep(2000);
		   totalThreadSleepTime += 2;
		   assertEquals("웹툰", driver.findElementById("com.skt.skaf.A000Z00040:id/category_text").getText());
		   driver.findElementByXPath("//android.widget.TextView[@text='요일별 웹툰']").isDisplayed();
//--------------------------------------------------------------------TC--------------------------------------------------------------------
		   double endTime = System.nanoTime();
		   System.out.println(endTime);
		   double RS = Double.parseDouble(String.format("%.2f",(((endTime - startTime)/1000000000) - totalThreadSleepTime)));
		   resultTime += RS;
		   System.out.println("--------------------------------------------------");
		   count++;
		   System.out.println(count + " / " + "웹툰 응답시간 / " + RS + "초");
		   System.out.println("--------------------------------------------------");
		   
		   Thread.sleep(2000);
		   setUp();

  			}catch (Exception e) {
  				count++;
				System.out.println("******************************");
				System.out.println(count + " / " + "Fail");
				System.out.println("******************************");
				
				Thread.sleep(2000);
				setUp();
				continue;				
  			}	   
		}
   System.out.println("--------------------------------------------------");
   System.out.println("원스토어 웹툰 카테고리 진입 평균 응답시간  /  " + String.format("%.2f",(resultTime/10)));
   System.out.println("--------------------------------------------------");
   
   }     
   
//---------------------------------------------------------------------------------------------------------------------------
   // 작성자 = 김광현M
   // TEST Title = 홈 > 음악 카테고리 응답시간
   // 예상 결과 =
//---------------------------------------------------------------------------------------------------------------------------
   @Test
   public void RUN007() throws Exception{
   double resultTime = 0;
   int count = 0;
   for (int i = 0; i < 10; i++) {
	   try {
		   double totalThreadSleepTime = 0;
		   Check();  
							   
		   driver.findElementById("com.skt.skaf.A000Z00040:id/home_logo").isDisplayed();
								   
		   double startTime = System.nanoTime();
		   System.out.println(startTime);
//--------------------------------------------------------------------TC--------------------------------------------------------------------
		   driver.findElementById("com.skt.skaf.A000Z00040:id/carditem_category_7").click();
   
		   Thread.sleep(2000);
		   totalThreadSleepTime += 2;
		   assertEquals("음악", driver.findElementById("com.skt.skaf.A000Z00040:id/category_text").getText());
		   driver.findElementByXPath("//android.widget.TextView[@text='랭킹']").isDisplayed();
//--------------------------------------------------------------------TC--------------------------------------------------------------------
		   double endTime = System.nanoTime();
		   System.out.println(endTime);
		   double RS = Double.parseDouble(String.format("%.2f",(((endTime - startTime)/1000000000) - totalThreadSleepTime)));
		   resultTime += RS;
		   System.out.println("--------------------------------------------------");
		   count++;
		   System.out.println(count + " / " + "음악 응답시간 / " + RS + "초");
		   System.out.println("--------------------------------------------------");
		   
		   Thread.sleep(2000);
		   setUp();

			}catch (Exception e) {
				count++;
				System.out.println("******************************");
				System.out.println(count + " / " + "Fail");
				System.out.println("******************************");
				
				Thread.sleep(2000);
				setUp();
				continue;
			}	   
   		}
   System.out.println("--------------------------------------------------");
   System.out.println("원스토어 음악 카테고리 진입 평균 응답시간  /  " + String.format("%.2f",(resultTime/10)));
   System.out.println("--------------------------------------------------");   
		   
   }     
   
//---------------------------------------------------------------------------------------------------------------------------
   // 작성자 = 김광현M
   // TEST Title = 홈 > 쇼핑 카테고리 응답시간
   // 예상 결과 =
//---------------------------------------------------------------------------------------------------------------------------
   @Test
   public void RUN008() throws Exception{
   double resultTime = 0;
   int count = 0;
   for (int i = 0; i < 10; i++) {
	   try {
		   double totalThreadSleepTime = 0;
		   Check();  
								   
		   driver.findElementById("com.skt.skaf.A000Z00040:id/home_logo").isDisplayed();
									   
		   double startTime = System.nanoTime();
		   System.out.println(startTime);
//--------------------------------------------------------------------TC--------------------------------------------------------------------
		   driver.findElementById("com.skt.skaf.A000Z00040:id/carditem_category_8").click();
   
		   Thread.sleep(2000);
		   totalThreadSleepTime += 2;
		   assertEquals("쇼핑", driver.findElementById("com.skt.skaf.A000Z00040:id/category_text").getText());
		   driver.findElementByXPath("//android.widget.TextView[@text='랭킹']").isDisplayed();
//--------------------------------------------------------------------TC--------------------------------------------------------------------
		   double endTime = System.nanoTime();
		   System.out.println(endTime);
		   double RS = Double.parseDouble(String.format("%.2f",(((endTime - startTime)/1000000000) - totalThreadSleepTime)));
		   resultTime += RS;
		   System.out.println("--------------------------------------------------");
		   count++;
		   System.out.println(count + " / " + "쇼핑 응답시간 / " + RS + "초");
		   System.out.println("--------------------------------------------------");
		   
		   Thread.sleep(2000);
		   setUp();

	   		}catch (Exception e) {
	   			count++;
	   			System.out.println("******************************");
	   			System.out.println(count + " / " + "Fail");
	   			System.out.println("******************************");
	   			
	   			Thread.sleep(2000);
	   			setUp();
	   			continue;
	   		}	   
		}
   System.out.println("--------------------------------------------------");
   System.out.println("원스토어 쇼핑 카테고리 진입 평균 응답시간  /  " + String.format("%.2f",(resultTime/10)));
   System.out.println("--------------------------------------------------");   
		   
   }   
   
//---------------------------------------------------------------------------------------------------------------------------
   // 작성자 = 김광현M
   // TEST Title = 카테고리 상품상세화면 진입시간
   // 예상 결과 =
//---------------------------------------------------------------------------------------------------------------------------
   @Test
   public void RUN009() throws Exception{
   double resultTime = 0;
   int count = 0;
   for (int i = 0; i < 10; i++) {
	   try {
		   double totalThreadSleepTime = 0;	   
		   Check();  
   
		   driver.findElementById("com.skt.skaf.A000Z00040:id/home_logo").isDisplayed();
		   driver.findElementById("com.skt.skaf.A000Z00040:id/carditem_category_1").click();
		   assertEquals("게임", driver.findElementById("com.skt.skaf.A000Z00040:id/category_text").getText());
		   driver.findElementByXPath("//android.widget.TextView[@text='랭킹']").isDisplayed();
   
		   Thread.sleep(1000);
		   double startTime = System.nanoTime();
		   System.out.println(startTime);
//--------------------------------------------------------------------TC--------------------------------------------------------------------
		   driver.findElementById("com.skt.skaf.A000Z00040:id/carditem_channelname").click();   
   
		   Thread.sleep(2000);
		   totalThreadSleepTime += 2;
		   driver.findElementById("com.skt.skaf.A000Z00040:id/mainTitle").isDisplayed();
		   driver.findElementById("com.skt.skaf.A000Z00040:id/text").isDisplayed();   
//--------------------------------------------------------------------TC--------------------------------------------------------------------
		   double endTime = System.nanoTime();
		   System.out.println(endTime);
		   double RS = Double.parseDouble(String.format("%.2f",(((endTime - startTime)/1000000000) - totalThreadSleepTime)));
		   resultTime += RS;
		   System.out.println("--------------------------------------------------");
		   count++;
		   System.out.println(count + " / " + "게임 상품 상세화면 응답시간 / " + RS + "초");
		   System.out.println("--------------------------------------------------");
		   
		   Thread.sleep(2000);
		   setUp();

	   		}catch (Exception e) {
	   			count++;
	   			System.out.println("******************************");
	   			System.out.println(count + " / " + "Fail");
	   			System.out.println("******************************");
	   			
	   			Thread.sleep(2000);
	   			setUp();
	   			continue;
	   		}	   
		}
   System.out.println("--------------------------------------------------");
   System.out.println("원스토어 게임 상품 상세화면 진입 평균 응답시간  /  " + String.format("%.2f",(resultTime/10)));
   System.out.println("--------------------------------------------------");   
		   
   }   
   
//---------------------------------------------------------------------------------------------------------------------------
   // 작성자 = 김광현M
   // TEST Title = 카테고리 상품상세화면 진입시간
   // 예상 결과 =
//---------------------------------------------------------------------------------------------------------------------------
   @Test
   public void RUN010() throws Exception{
   double resultTime = 0;
   int count = 0;
   for (int i = 0; i < 10; i++) {
	   try {
		   double totalThreadSleepTime = 0;	   
		   Check();  
	   
		   driver.findElementById("com.skt.skaf.A000Z00040:id/home_logo").isDisplayed();
		   driver.findElementById("com.skt.skaf.A000Z00040:id/carditem_category_2").click();
		   assertEquals("앱", driver.findElementById("com.skt.skaf.A000Z00040:id/category_text").getText());
		   driver.findElementByXPath("//android.widget.TextView[@text='랭킹']").isDisplayed();
   
		   Thread.sleep(1000);
		   double startTime = System.nanoTime();
		   System.out.println(startTime);
//--------------------------------------------------------------------TC--------------------------------------------------------------------
		   driver.findElementById("com.skt.skaf.A000Z00040:id/carditem_channelname").click();   
   
		   Thread.sleep(2000);
		   totalThreadSleepTime += 2;
		   driver.findElementById("com.skt.skaf.A000Z00040:id/mainTitle").isDisplayed();
		   driver.findElementById("com.skt.skaf.A000Z00040:id/text").isDisplayed();
//--------------------------------------------------------------------TC--------------------------------------------------------------------
		   double endTime = System.nanoTime();
		   System.out.println(endTime);
		   double RS = Double.parseDouble(String.format("%.2f",(((endTime - startTime)/1000000000) - totalThreadSleepTime)));
		   resultTime += RS;
		   System.out.println("--------------------------------------------------");
		   count++;
		   System.out.println(count + " / " + "App 상품 상세화면 응답시간 / " + RS + "초");
		   System.out.println("--------------------------------------------------");
		   
		   Thread.sleep(2000);
		   setUp();

	   		}catch (Exception e) {
	   			count++;
	   			System.out.println("******************************");
	   			System.out.println(count + " / " + "Fail");
	   			System.out.println("******************************");
	   			
	   			Thread.sleep(2000);
	   			setUp();
	   			continue;
	   		}	   
		}
   System.out.println("--------------------------------------------------");
   System.out.println("원스토어 App 상품 상세화면 진입 평균 응답시간  /  " + String.format("%.2f",(resultTime/10)));
   System.out.println("--------------------------------------------------");   
		   
   }   
   
//---------------------------------------------------------------------------------------------------------------------------
   // 작성자 = 김광현M
   // TEST Title = 카테고리 상품상세화면 진입시간
   // 예상 결과 =
//---------------------------------------------------------------------------------------------------------------------------
   @Test
   public void RUN011() throws Exception{
   double resultTime = 0;
   int count = 0;
   for (int i = 0; i < 10; i++) {
	   try {
		   double totalThreadSleepTime = 0;	   
		   Check();  
		   
		   driver.findElementById("com.skt.skaf.A000Z00040:id/home_logo").isDisplayed();
		   driver.findElementById("com.skt.skaf.A000Z00040:id/carditem_category_3").click();
		   assertEquals("TV방송", driver.findElementById("com.skt.skaf.A000Z00040:id/category_text").getText());
		   driver.findElementByXPath("//android.widget.TextView[@text='랭킹']").isDisplayed();
   
		   Thread.sleep(1000);
		   double startTime = System.nanoTime();
		   System.out.println(startTime);
//--------------------------------------------------------------------TC--------------------------------------------------------------------
		   driver.findElementById("com.skt.skaf.A000Z00040:id/carditem_channelname").click();   
   
		   Thread.sleep(2000);
		   totalThreadSleepTime += 2;
		   driver.findElementById("com.skt.skaf.A000Z00040:id/mainTitle").isDisplayed();
		   driver.findElementById("com.skt.skaf.A000Z00040:id/text").isDisplayed();
//--------------------------------------------------------------------TC--------------------------------------------------------------------
		   double endTime = System.nanoTime();
		   System.out.println(endTime);
		   double RS = Double.parseDouble(String.format("%.2f",(((endTime - startTime)/1000000000) - totalThreadSleepTime)));
		   resultTime += RS;
		   System.out.println("--------------------------------------------------");
		   count++;
		   System.out.println(count + " / " + "TV방송 상품 상세화면 응답시간 / " + RS + "초");
		   System.out.println("--------------------------------------------------");
		   
		   Thread.sleep(2000);
		   setUp();

		   	}catch (Exception e) {
		   		count++;
		   		System.out.println("******************************");
		   		System.out.println(count + " / " + "Fail");
		   		System.out.println("******************************");
		   		
		   		Thread.sleep(2000);
		   		setUp();
		   		continue;
		   	}	   
		}
   System.out.println("--------------------------------------------------");
   System.out.println("원스토어 TV방송 상품 상세화면 진입 평균 응답시간  /  " + String.format("%.2f",(resultTime/10)));
   System.out.println("--------------------------------------------------");   
		   
   }   
   
//---------------------------------------------------------------------------------------------------------------------------
   // 작성자 = 김광현M
   // TEST Title = 카테고리 상품상세화면 진입시간
   // 예상 결과 =
//---------------------------------------------------------------------------------------------------------------------------
   @Test
   public void RUN012() throws Exception{
   double resultTime = 0;
   int count = 0;
   for (int i = 0; i < 10; i++) {
	   try {
		   double totalThreadSleepTime = 0;
		   Check();  
			   
		   driver.findElementById("com.skt.skaf.A000Z00040:id/home_logo").isDisplayed();
		   driver.findElementById("com.skt.skaf.A000Z00040:id/carditem_category_4").click();
		   assertEquals("영화", driver.findElementById("com.skt.skaf.A000Z00040:id/category_text").getText());
		   driver.findElementByXPath("//android.widget.TextView[@text='랭킹']").isDisplayed();
   
		   Thread.sleep(1000);
		   double startTime = System.nanoTime();
		   System.out.println(startTime);
//--------------------------------------------------------------------TC--------------------------------------------------------------------
		   driver.findElementById("com.skt.skaf.A000Z00040:id/carditem_channelname").click();   
   
		   Thread.sleep(2000);
		   totalThreadSleepTime += 2;
		   driver.findElementById("com.skt.skaf.A000Z00040:id/mainTitle").isDisplayed();
		   driver.findElementById("com.skt.skaf.A000Z00040:id/text").isDisplayed();   
//--------------------------------------------------------------------TC--------------------------------------------------------------------
		   double endTime = System.nanoTime();
		   System.out.println(endTime);
		   double RS = Double.parseDouble(String.format("%.2f",(((endTime - startTime)/1000000000) - totalThreadSleepTime)));
		   resultTime += RS;
		   System.out.println("--------------------------------------------------");
		   count++;
		   System.out.println(count + " / " + "영화 상품 상세화면 응답시간 / " + RS + "초");
		   System.out.println("--------------------------------------------------");
		   
		   Thread.sleep(2000);
		   setUp();

	   		}catch (Exception e) {
	   			count++;
	   			System.out.println("******************************");
	   			System.out.println(count + " / " + "Fail");
	   			System.out.println("******************************");
	   			
	   			Thread.sleep(2000);
	   			setUp();
	   			continue;
	   		}	   
		}
   System.out.println("--------------------------------------------------");
   System.out.println("원스토어 영화 상품 상세화면 진입 평균 응답시간  /  " + String.format("%.2f",(resultTime/10)));
   System.out.println("--------------------------------------------------");   
		   
   }  
   
//---------------------------------------------------------------------------------------------------------------------------
   // 작성자 = 김광현M
   // TEST Title = 카테고리 상품상세화면 진입시간
   // 예상 결과 =
//---------------------------------------------------------------------------------------------------------------------------
   @Test
   public void RUN013() throws Exception{
   double resultTime = 0;
   int count = 0;
   for (int i = 0; i < 10; i++) {
	   try {
		   double totalThreadSleepTime = 0;
		   Check();  
				   
		   driver.findElementById("com.skt.skaf.A000Z00040:id/home_logo").isDisplayed();
		   driver.findElementById("com.skt.skaf.A000Z00040:id/carditem_category_6").click();
		   assertEquals("웹툰", driver.findElementById("com.skt.skaf.A000Z00040:id/category_text").getText());
		   driver.findElementByXPath("//android.widget.TextView[@text='요일별 웹툰']").isDisplayed();
   
		   Thread.sleep(1000);
		   double startTime = System.nanoTime();
		   System.out.println(startTime);
//--------------------------------------------------------------------TC--------------------------------------------------------------------
		   driver.findElementById("com.skt.skaf.A000Z00040:id/carditem_thumbnail_line").click();   
   
		   Thread.sleep(2000);
		   totalThreadSleepTime += 2;
		   driver.findElementById("com.skt.skaf.A000Z00040:id/detail_webtoon_metadata_title").isDisplayed();
		   driver.findElementById("com.skt.skaf.A000Z00040:id/detail_webtoon_summary_btn_first_view").isDisplayed();   
//--------------------------------------------------------------------TC--------------------------------------------------------------------
		   double endTime = System.nanoTime();
		   System.out.println(endTime);
		   double RS = Double.parseDouble(String.format("%.2f",(((endTime - startTime)/1000000000) - totalThreadSleepTime)));
		   resultTime += RS;
		   System.out.println("--------------------------------------------------");
		   count++;
		   System.out.println(count + " / " + "웹툰 상품 상세화면 응답시간 / " + RS + "초");
		   System.out.println("--------------------------------------------------");
		   
		   Thread.sleep(2000);
		   setUp();

	   		}catch (Exception e) {
	   			count++;
	   			System.out.println("******************************");
	   			System.out.println(count + " / " + "Fail");
	   			System.out.println("******************************");
	   			
	   			Thread.sleep(2000);
	   			setUp();
	   			continue;
	   		}	   
		}
   System.out.println("--------------------------------------------------");
   System.out.println("원스토어 웹툰 상품 상세화면 진입 평균 응답시간  /  " + String.format("%.2f",(resultTime/10)));
   System.out.println("--------------------------------------------------");   
		   
   }  
   
//---------------------------------------------------------------------------------------------------------------------------
   // 작성자 = 김광현M
   // TEST Title = 카테고리 상품상세화면 진입시간
   // 예상 결과 =
//---------------------------------------------------------------------------------------------------------------------------
   @Test
   public void RUN014() throws Exception{
   double resultTime = 0;
   int count = 0;
   for (int i = 0; i < 10; i++) {
	   try {		   
		   double totalThreadSleepTime = 0;
		   Check();  
					   
		   driver.findElementById("com.skt.skaf.A000Z00040:id/home_logo").isDisplayed();
		   driver.findElementById("com.skt.skaf.A000Z00040:id/carditem_category_7").click();
		   assertEquals("음악", driver.findElementById("com.skt.skaf.A000Z00040:id/category_text").getText());
		   driver.findElementByXPath("//android.widget.TextView[@text='랭킹']").isDisplayed();
   
		   Thread.sleep(1000);
		   double startTime = System.nanoTime();
		   System.out.println(startTime);
//--------------------------------------------------------------------TC--------------------------------------------------------------------
		   driver.findElementById("com.skt.skaf.A000Z00040:id/carditem_channelname").click();   
   
		   Thread.sleep(2000);
		   totalThreadSleepTime += 2;
		   driver.findElementById("com.skt.skaf.A000Z00040:id/mainTitle").isDisplayed();
		   driver.findElementById("com.skt.skaf.A000Z00040:id/text").isDisplayed();
//--------------------------------------------------------------------TC--------------------------------------------------------------------
		   double endTime = System.nanoTime();
		   System.out.println(endTime);
		   double RS = Double.parseDouble(String.format("%.2f",(((endTime - startTime)/1000000000) - totalThreadSleepTime)));
		   resultTime += RS;
		   System.out.println("--------------------------------------------------");
		   count++;
		   System.out.println(count + " / " + "음악 상품 상세화면 응답시간 / " + RS + "초");
		   System.out.println("--------------------------------------------------");
		   
		   Thread.sleep(2000);
		   setUp();
		   
		   	}catch (Exception e) {
		   		count++;
		   		System.out.println("******************************");
		   		System.out.println(count + " / " + "Fail");
		   		System.out.println("******************************");
		   		
		   		Thread.sleep(2000);
		   		setUp();
		   		continue;
		   	}	   
		}
   System.out.println("--------------------------------------------------");
   System.out.println("원스토어 음악 상품 상세화면 진입 평균 응답시간  /  " + String.format("%.2f",(resultTime/10)));
   System.out.println("--------------------------------------------------");   
		   
   }  

//---------------------------------------------------------------------------------------------------------------------------
   // 작성자 = 김광현M
   // TEST Title = 카테고리 상품상세화면 진입시간
   // 예상 결과 =
//---------------------------------------------------------------------------------------------------------------------------
   @Test
   public void RUN015() throws Exception{
   double resultTime = 0;
   int count = 0;
   for (int i = 0; i < 10; i++) {
	   try {
		   double totalThreadSleepTime = 0;	   
		   Check();  
						   
		   driver.findElementById("com.skt.skaf.A000Z00040:id/home_logo").isDisplayed();
		   driver.findElementById("com.skt.skaf.A000Z00040:id/carditem_category_8").click();
		   assertEquals("쇼핑", driver.findElementById("com.skt.skaf.A000Z00040:id/category_text").getText());
		   driver.findElementByXPath("//android.widget.TextView[@text='랭킹']").isDisplayed();
   
		   Thread.sleep(1000);
		   double startTime = System.nanoTime();
		   System.out.println(startTime);
//--------------------------------------------------------------------TC--------------------------------------------------------------------
		   driver.findElementById("com.skt.skaf.A000Z00040:id/carditem_channelname").click();   
   
		   Thread.sleep(2000);
		   totalThreadSleepTime += 2;
		   driver.findElementById("com.skt.skaf.A000Z00040:id/mainTitle").isDisplayed();
		   driver.findElementById("com.skt.skaf.A000Z00040:id/text").isDisplayed();
//--------------------------------------------------------------------TC--------------------------------------------------------------------
		   double endTime = System.nanoTime();
		   System.out.println(endTime);
		   double RS = Double.parseDouble(String.format("%.2f",(((endTime - startTime)/1000000000) - totalThreadSleepTime)));
		   resultTime += RS;
		   System.out.println("--------------------------------------------------");
		   count++;
		   System.out.println(count + " / " + "쇼핑 상품 상세화면 응답시간 / " + RS + "초");
		   System.out.println("--------------------------------------------------");
		   
		   Thread.sleep(2000);
		   setUp();

	   		}catch (Exception e) {
	   			count++;
	   			System.out.println("******************************");
	   			System.out.println(count + " / " + "Fail");
	   			System.out.println("******************************");
	   			
	   			Thread.sleep(2000);
	   			setUp();
	   			continue;
	   		}	   
   		}
   System.out.println("--------------------------------------------------");
   System.out.println("원스토어 쇼핑 상품 상세화면 진입 평균 응답시간  /  " + String.format("%.2f",(resultTime/10)));
   System.out.println("--------------------------------------------------");   
		   
   }    

//---------------------------------------------------------------------------------------------------------------------------
   // 작성자 = 김광현M
   // TEST Title = 검색결과 응답시간
   // 예상 결과 =
//---------------------------------------------------------------------------------------------------------------------------
   @Test
   public void RUN016() throws Exception{
   double resultTime = 0;
   int count = 0;
   for (int i = 0; i < 10; i++) {
	   try {
		   double totalThreadSleepTime = 0;
		   Check();  
							   
		   driver.findElementById("com.skt.skaf.A000Z00040:id/home_logo").isDisplayed();
		   driver.findElementById("com.skt.skaf.A000Z00040:id/actionbar_menu_search").click();
   
		   Thread.sleep(1000);   
		   try  {
			   if(driver.findElementById("com.skt.skaf.A000Z00040:id/search_history_item_delete").isDisplayed()){    
				   List<AndroidElement> deletebuttons = driver.findElementsById("com.skt.skaf.A000Z00040:id/search_history_item_delete");
				   for (int u = 0; u < deletebuttons.size(); u++) {
					   deletebuttons.get(u).click();
				   }
			   }
		   }
		   catch(NoSuchElementException e){
		   }
   
		   Thread.sleep(1000);
		   driver.findElementById("com.skt.skaf.A000Z00040:id/searchbar_keyword_edittext").sendKeys("꾼");
         
		   Thread.sleep(1000);
		   double startTime = System.nanoTime();
		   System.out.println(startTime);
//--------------------------------------------------------------------TC--------------------------------------------------------------------
		   driver.findElementById("com.skt.skaf.A000Z00040:id/searchbar_search_icon").click();
   
		   Thread.sleep(2000);
		   totalThreadSleepTime += 2;
		   driver.findElementById("com.skt.skaf.A000Z00040:id/search_result_catetory_name").isDisplayed();
		   driver.findElementById("com.skt.skaf.A000Z00040:id/carditem_channelname").isDisplayed();   
//--------------------------------------------------------------------TC--------------------------------------------------------------------
		   double endTime = System.nanoTime();
		   System.out.println(endTime);
		   double RS = Double.parseDouble(String.format("%.2f",(((endTime - startTime)/1000000000) - totalThreadSleepTime)));
		   resultTime += RS;
		   System.out.println("--------------------------------------------------");
		   count++;
		   System.out.println(count + " / " + "검색결과 응답시간 / " + RS + "초");
		   System.out.println("--------------------------------------------------");
		   
		   Thread.sleep(2000);
		   setUp();

				}catch (Exception e) {
					count++;
					System.out.println("******************************");
					System.out.println(count + " / " + "Fail");
					System.out.println("******************************");
					
					Thread.sleep(2000);
					setUp();
					continue;
				}	   
   }
   System.out.println("--------------------------------------------------");
   System.out.println("원스토어 검색결과 진입 평균 응답시간  /  " + String.format("%.2f",(resultTime/10)));
   System.out.println("--------------------------------------------------");   
		   
   }
   
 //---------------------------------------------------------------------------------------------------------------------------
   // 작성자 = 김광현M
   // TEST Title = App 실행
   // 예상 결과 =
//---------------------------------------------------------------------------------------------------------------------------
   @Test
   public void RUN017() throws Exception{
   double resultTime = 0;
   int count = 0;
   for (int i = 0; i < 10; i++) {
	   try {
		   double totalThreadSleepTime = 0;	   
		   double startTime = System.nanoTime();
		   System.out.println(startTime);
//--------------------------------------------------------------------TC--------------------------------------------------------------------
		   driver.findElementById("com.skt.skaf.A000Z00040:id/btn_member_change_id_skip").click();      
			   
		   Thread.sleep(2000);
		   totalThreadSleepTime += 2;
		   driver.findElementByXPath("//android.widget.TextView[@text='사전예약']").isDisplayed();
//--------------------------------------------------------------------TC--------------------------------------------------------------------
		   double endTime = System.nanoTime();
		   System.out.println(endTime);
		   double RS = Double.parseDouble(String.format("%.2f",(((endTime - startTime)/1000000000) - totalThreadSleepTime)));
		   resultTime += RS;
		   System.out.println("--------------------------------------------------");
		   count++;
		   System.out.println(count + " / " + "App 실행 응답시간 / " + RS + "초");
		   System.out.println("--------------------------------------------------");
		   
		   Thread.sleep(2000);
		   setUp();

	   		} catch (Exception e) {
	   			count++;
	   			System.out.println("******************************");
	   			System.out.println(count + " / " + "Fail");
	   			System.out.println("******************************");
	   			
	   			Thread.sleep(2000);
	   			setUp();
	   			continue;
	   		}	      
   		}
   System.out.println("--------------------------------------------------");
   System.out.println("원스토어 실행 평균 응답시간  /  " + String.format("%.2f",(resultTime/10)));
   System.out.println("--------------------------------------------------");
   }      
   
}