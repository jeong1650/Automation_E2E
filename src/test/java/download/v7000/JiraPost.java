package download.v7000;



import org.json.simple.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static download.v7000.Download_SKT_E2E_Test.passcount;



public class JiraPost extends Download_SKT_E2E_Test {
//    public static ArrayList<Integer> passlist = new ArrayList<>();
static double passsum = 0;

    public static void main(String[] args) throws Exception {

        String str = Files.readString(Paths.get("C:\\Download_SKT_Result\\SKT_DL_result.txt"));
        double total_Scripts = 26;
        double failcount;


        failcount = total_Scripts - PcountSum;

        double Total_Percent = PcountSum / total_Scripts;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());

        JSONObject Bon = new JSONObject();
        JSONObject data1 = new JSONObject();
        data1.put("key","QARESULT");
        JSONObject data2 = new JSONObject();
        data2.put("id","11341");

        Bon.put("project",data1);
        Bon.put("summary",TCID);
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