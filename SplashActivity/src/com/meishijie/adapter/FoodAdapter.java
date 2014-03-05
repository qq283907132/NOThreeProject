package com.meishijie.adapter;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.meishijie.entity.NewsContent;
import com.meishijie.main.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class FoodAdapter extends BaseAdapter {
	private List<NewsContent> foodList;
	private LayoutInflater inflater;
	
	public FoodAdapter(Context context){
		inflater = LayoutInflater.from(context);
	}
	
	public void setData(List<NewsContent> foodList){
		this.foodList = foodList;
	}
	
	@Override
	public int getCount() {
		return foodList.size();
	}

	@Override
	public Object getItem(int position) {
		return foodList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View container, ViewGroup parent) {
		View layout = null;
		if(container == null){
			layout = inflater.inflate(R.layout.fooder_menu_item, parent, false);	
		}else{
			layout = container;
		}
		
		TextView name = (TextView) layout.findViewById(R.id.foodname);
		TextView flavor = (TextView) layout.findViewById(R.id.flavor);
		TextView craft = (TextView) layout.findViewById(R.id.craft);
		
		TextView diff = (TextView) layout.findViewById(R.id.diff);
		TextView time = (TextView) layout.findViewById(R.id.time);
		ImageView titlepic = (ImageView)layout.findViewById(R.id.pic);
		ProgressBar picBar = (ProgressBar) layout.findViewById(R.id.pb);
		
		NewsContent newsContent  = foodList.get(position);
		
		name.setText(newsContent.getTitle());
		flavor.setText(newsContent.getKouwei());
		craft.setText(newsContent.getGongyi());
		diff.setText(newsContent.getMake_diff());
		time.setText(newsContent.getMake_time());
		
		//new LoadImageTask(titlepic,picBar).execute(newsContent.getTitlepic());
		
		return layout;
	}
	
	/*public class LoadImageTask extends AsyncTask<String, Integer, byte[]>{

		private ImageView titlePic;
		private ProgressBar picBar; 
		
		
		public LoadImageTask(ImageView titlepic, ProgressBar picBar) {
			this.titlePic = titlepic;
			this.picBar = picBar;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			picBar.setVisibility(View.VISIBLE);
		}
		
		@Override
		protected byte[] doInBackground(String... params) {
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(params[0]);
			byte[] result = null;//图片的所有内容
			InputStream inputStream = null;
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			try {
				HttpResponse httpResponse = httpClient.execute(httpGet);
				long file_length = httpResponse.getEntity().getContentLength();// 文件总长度
				int total_length = 0;
				byte[] data = new byte[1024];
				int len = 0;
				if (httpResponse.getStatusLine().getStatusCode() == 200) {
					inputStream = httpResponse.getEntity().getContent();
					while ((len = inputStream.read(data)) != -1) {
						total_length += len;
						int progress_value = (int) ((total_length / (float) file_length) * 100);
						publishProgress(progress_value);//发布刻度单位
						outputStream.write(data, 0, len);
					}
				}
				result = outputStream.toByteArray();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				httpClient.getConnectionManager().shutdown();
			}
			return result;
		}

		
		@Override
		protected void onPostExecute(byte[] result) {
			super.onPostExecute(result);
			Bitmap bitmap = BitmapFactory.decodeByteArray(result, 0, result.length);
			titlePic.setImageBitmap(bitmap);
			picBar.setVisibility(View.GONE);
		}
		
		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			picBar.setProgress(values[0]);
		}	
	}*/
}
