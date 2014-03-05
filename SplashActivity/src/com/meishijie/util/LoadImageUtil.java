package com.meishijie.util;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class LoadImageUtil {
	
	public static byte[] loadImage(String path){
		byte[] data = null ;
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(path);
		try {
			Log.i("img", path);
			HttpResponse response = httpClient.execute(httpGet);
			if(response.getStatusLine().getStatusCode() == 200){
				data = EntityUtils.toByteArray(response.getEntity());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			httpClient.getConnectionManager().shutdown();
		}
		return data;
	}
}
