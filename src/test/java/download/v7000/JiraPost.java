package download.v7000;



import org.json.simple.JSONObject;
import org.junit.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class JiraPost extends Download_SKT_E2E_Test {

@Test
public void JiraTest() throws Exception{
    String str = Files.readString(Paths.get("C:\\Download_SKT_Result\\SKT_DL_result.txt"));
    double total_Scripts = 26;
    double failcount;

    ArrayList<Integer> P_list = new ArrayList<Integer>();
    File c_note = new File("C:\\Download_SKT_Result\\PassCount.txt");
    try{
        BufferedReader br = new BufferedReader(new FileReader(c_note));
        String cou = br.readLine();
        while(cou != null){
            P_list.add(Integer.valueOf(cou));
            cou = br.readLine();
        }
        for(int i = 0; i < P_list.size(); i++){  // 저장된 Array의 크기만큼 루프

            PcountSum += P_list.get(i);
        }
        br.close();
    } catch (NullPointerException e){ // null이 있을 경우
        System.out.println("null");
    } catch (FileNotFoundException e){ // 파일을 찾을 수 없는 경우
        e.getStackTrace();
        System.out.println("파일 못찾음");
    } catch (IOException e){ // 파일 읽기 중 에러가 발생한 경우
        e.getStackTrace();
        System.out.println("파일 읽기중 에러");
    }

    failcount = total_Scripts - PcountSum;

    double Total_Percent = PcountSum / total_Scripts;

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date(System.currentTimeMillis());

    SimpleDateFormat Monformat = new SimpleDateFormat("MM-dd");
    Date Mdate = new Date(System.currentTimeMillis());


    JSONObject Bon = new JSONObject();
    JSONObject data1 = new JSONObject();
    data1.put("key","QARESULT");
    JSONObject data2 = new JSONObject();
    data2.put("id","11341");

    Bon.put("project",data1);
    Bon.put("summary","OSC D/L E2E 결과"+"("+ Monformat.format(Mdate)+")");
    Bon.put("description",str);
    Bon.put("issuetype",data2);
    Bon.put("customfield_12145",PcountSum);
    Bon.put("customfield_12146",failcount);
    Bon.put("customfield_12147",total_Scripts);
    Bon.put("customfield_12148",Math.round(Total_Percent*100));
    Bon.put("duedate",format.format(date));

    JSONObject result = new JSONObject();

    result.put("fields",Bon);

    System.out.println(result);


    String apiUrl = "https://jira.onestorecorp.com/rest/api/2/issue";

    String token = "NDE1MTE4Njk0MTg5OgZlK2oolYQoO22CUcWM9fa+GhS4";

    URL url = null;
    String readLine = null;
    StringBuilder buffer = null;
    OutputStream outputStream = null;
    BufferedReader bufferedReader = null;
    BufferedWriter bufferedWriter = null;
    HttpURLConnection urlConnection = null;

    int connTimeout = 5000;
    int readTimeout = 3000;



    try
    {
        url = new URL(apiUrl);

        urlConnection = (HttpURLConnection)url.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setConnectTimeout(connTimeout);
        urlConnection.setReadTimeout(readTimeout);
        urlConnection.setRequestProperty("Authorization", "Bearer " + token);
        urlConnection.setRequestProperty("Content-Type", "application/json");
        urlConnection.setDoOutput(true);
        urlConnection.setInstanceFollowRedirects(true);

        outputStream = urlConnection.getOutputStream();

        bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
        bufferedWriter.write(result.toString());
        bufferedWriter.flush();

        buffer = new StringBuilder();
        if(urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK)
        {
            bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));
            while((readLine = bufferedReader.readLine()) != null)
            {
                buffer.append(readLine).append("\n");
            }
        }
        else
        {
            buffer.append("\"code\" : \""+urlConnection.getResponseCode()+"\"");
            buffer.append(", \"message\" : \""+urlConnection.getResponseMessage()+"\"");
        }
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    }
    finally
    {
        try
        {
            if (bufferedWriter != null) { bufferedWriter.close(); }
            if (outputStream != null) { outputStream.close(); }
            if (bufferedReader != null) { bufferedReader.close(); }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }


    System.out.println("결과 : " + buffer.toString());

    FileDelete();
}

    public static void FileDelete(){
        String TextFilePath = "C:\\Download_SKT_Result\\SKT_DL_result.txt";
        File DeleteText = new File(TextFilePath);
        if (DeleteText.exists()){
            DeleteText.delete();
        }else {
            System.out.println("파일이 존재하지 않습니다.");
        }

        String CountPath = "C:\\Download_SKT_Result\\PassCount.txt";
        File Deletecount = new File(CountPath);
        if (Deletecount.exists()){
            Deletecount.delete();
        }else {
            System.out.println("파일이 존재하지 않습니다.");
        }
    }

}