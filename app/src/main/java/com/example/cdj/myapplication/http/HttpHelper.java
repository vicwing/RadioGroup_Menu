package com.example.cdj.myapplication.http;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;

import com.example.cdj.myapplication.utils.imagecache.ImageCacheHelper;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName: HttpHelper
 * @Description: Http帮助工具类
 * @author: Administrator
 * @date:2013-12-2 上午9:35:37
 */
public class HttpHelper {

	private static final String TAG = HttpHelper.class.getSimpleName();

	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			Log.i("NetWorkState", "Unavailabel");
			return false;
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						Log.i("NetWorkState", "Availabel");
						return true;
					}
				}
			}
		}
		return false;
	}



	public static boolean tryloginWithoutUI(final Context context) {

		return tryloginWithoutUI(context, true);
	}

	public static boolean tryloginWithoutUI(final Context context, boolean isJumpToLoginActivity) {
		return true;
	}


	static public Bitmap getImg(Context context, String imageUrl) {
		
		imageUrl = getImageUrl(imageUrl);
		if (false) {
			ImageCacheHelper	imageCacheHelper = new ImageCacheHelper(context);
			return  imageCacheHelper.getBitmap(imageUrl);
		}
		return getNetBitmap(imageUrl);
	}


	/**
	 * 获取网络图片bitmap,没有压缩,使用时注意oom
	 * @param imageUrl
	 * @param context
	 * @param imageUrl
	 * @return Bitmap
	 */
	public static Bitmap getImgNoCache(Context context, String imageUrl) {
		
		imageUrl = getImageUrl(imageUrl);
//		if (isCache) {
//			return ((DemoApplication) mContext.getApplicationContext()).getImageCacheHelper().getBitmap(imageUrl);
//		}
		Bitmap netBitmap = getNetBitmap(imageUrl);
		
		return netBitmap;
	}



	private static String getImageUrl(String imageUrl) {
		if(TextUtils.isEmpty(imageUrl)){
			return null;
		}
		
		imageUrl = imageUrl.replace(" ", "%20");

		imageUrl = imageUrl.replace(" ", "%C2%A0");
		
		System.out.println(imageUrl);
		return imageUrl;
	}
	

	/**
	 * 获取网络图片转换成bitmap,没有压缩,使用时注意oom
	 * @param imageUrl
	 * @return Bitmap
	 */
	private static Bitmap getNetBitmap(String imageUrl) {
		Bitmap bitmap = null;
		try {
			// httpGet连接对象
			HttpGet httpRequest = new HttpGet(imageUrl);
			// 取得HttpClient 对象
			HttpClient httpclient = new DefaultHttpClient();
			// 请求httpClient ，取得HttpRestponse
			HttpResponse httpResponse = httpclient.execute(httpRequest);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// 取得相关信息 取得HttpEntiy
				HttpEntity httpEntity = httpResponse.getEntity();
				// 获得一个输入流
				InputStream is = httpEntity.getContent();
//				Config.debug(is.available());
//				Config.debug("Get, Yes!");
				bitmap = BitmapFactory.decodeStream(is);
				is.close();
			}

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bitmap;
	}
}
