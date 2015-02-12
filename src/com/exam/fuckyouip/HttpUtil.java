package com.exam.fuckyouip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by tang on 2014/8/20.
 */
public class HttpUtil {
    /**
     * 获取HttpURLConnection
     * params[0] url
     * params[1] HttpURLConnection方法：GET or POST,etc
     * params[2] 请求数据类型
     * @return
     */
    public static String sendRequest(String... params){
        HttpURLConnection conn = null;
        try {
            URL urls = new URL(params[0]);
            conn = (HttpURLConnection) urls.openConnection();
            // 设置连接数据,方法
            conn.setConnectTimeout(8000);
            conn.setReadTimeout(8000);
            conn.setRequestMethod(params.length > 1 ? params[1] :"GET");        // 如果填写了
            conn.setDoOutput(true);
            conn.setRequestProperty("Charset","utf-8");
            if (params.length > 2){         // 设置请求数据类型
                 conn.setRequestProperty("Content-Type",params[2]);
            }
            // 取得服务器返回的数据
            InputStream in = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder builder = new StringBuilder();
            String temp;
            while ((temp = reader.readLine()) != null){
                builder.append(temp);
            }
            return builder.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            // 关闭链接
            if (conn != null){
                conn.disconnect();
            }
        }
        return null;
    }
    
 // 获取外网IP
 	public static String getNetIp(String host) {
 		URL infoUrl = null;
 		InputStream inStream = null;
 		try {
 			// infoUrl = new URL("http://city.ip138.com/ip2city.asp");
 			infoUrl = new URL(host);
 			URLConnection connection = infoUrl.openConnection();
 			HttpURLConnection httpConnection = (HttpURLConnection) connection;
 			int responseCode = httpConnection.getResponseCode();
 			if (responseCode == HttpURLConnection.HTTP_OK) {
 				inStream = httpConnection.getInputStream();
 				BufferedReader reader = new BufferedReader(
 						new InputStreamReader(inStream, "utf-8"));
 				StringBuilder strber = new StringBuilder();
 				String line = null;
 				while ((line = reader.readLine()) != null)
 					strber.append(line + "\n");
 				inStream.close();
 				// 从反馈的结果中提取出IP地址
 				int start = strber.indexOf("[");
 				int end = strber.indexOf("]", start + 1);
 				line = strber.substring(start + 1, end);
 				return line;
 			}
 		} catch (MalformedURLException e) {
 			e.printStackTrace();
 		} catch (IOException e) {
 			e.printStackTrace();
 		}
 		return null;
 	}

	public static FakeInfo parseJSON(String result) {
		try {
			FakeInfo info = new FakeInfo();
			JSONObject obj = new JSONObject(result);
			if("success".equals((obj.getString("errMsg")))){
				JSONObject data = obj.getJSONObject("retData");
				info.setIp(data.optString("ip"));
				info.setCountry(data.optString("country"));
				info.setProvince(data.optString("province"));
				info.setCity(data.optString("city"));
				info.setDistrict(data.optString("district"));
				info.setCarrier(data.optString("carrier"));
				
				return info;
			} 
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
}
