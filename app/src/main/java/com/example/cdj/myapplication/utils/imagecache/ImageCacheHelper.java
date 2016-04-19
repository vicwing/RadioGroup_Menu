/**   
 * @FileName: ImageCacheHelper.java 
 * @Package:com.qfang.qfangmobile.cache 
 * @Description: TODO
 * @author: Administrator  
 * @date:2013-12-23 下午3:45:23 
 * @version V1.0   
 */
package com.example.cdj.myapplication.utils.imagecache;

import android.content.Context;
import android.graphics.Bitmap;

import java.io.File;

/**
 * @ClassName: ImageCacheHelper
 * @Description: TODO
 * @author: Administrator
 * @date:2013-12-23 下午3:45:23
 */
public class ImageCacheHelper {
	/**
	 *  
	 */
	public ImageCacheHelper(Context context) {
		// TODO Auto-generated constructor stub
		memoryCache = new ImageMemoryCache(context);
		fileCache = new ImageFileCache();
	}

	private ImageMemoryCache memoryCache;
	private ImageFileCache fileCache;

	public void clearFileCache() {
		fileCache.clearCache();
	}

	/*** 获得一张图片,从三个地方获取,首先是内存缓存,然后是文件缓存,最后从网络获取 ***/
	public Bitmap getBitmap(String url) {
		// 从内存缓存中获取图片
		Bitmap result = memoryCache.getBitmapFromCache(url);
		if (result == null) {
			// 文件缓存中获取
			result = fileCache.getImage(url);
			if (result == null) {
				// 从网络获取
				result = ImageGetFromHttp.downloadBitmap(url);
				if (result != null) {
					fileCache.saveBitmap(result, url);
					memoryCache.addBitmapToCache(url, result);
				}
			} else {
				// 添加到内存缓存
				System.out.println("hit the disk!");
				memoryCache.addBitmapToCache(url, result);
			}
		} else {
			System.out.println("hit the mem!");
		}
		return result;
	}

	/** 从缓存获取Bitmap */
	public Bitmap getBitmapFromCache(String url) {
		// 从内存缓存中获取图片
		Bitmap bitmap = memoryCache.getBitmapFromCache(url);
		if (bitmap == null) {
			// 文件缓存中获取
			bitmap = fileCache.getImage(url);
			if (bitmap != null) {
				// 添加到内存缓存
				memoryCache.addBitmapToCache(url, bitmap);
			}
		} else {
			System.out.println("hit the mem!");
		}
		return bitmap;
	}

	/** 从网络获取Bitmap */
	public Bitmap downloadBitmap(String url) {
		Bitmap bitmap = null;
		// 从网络获取
		bitmap = ImageGetFromHttp.downloadBitmap(url);
		if (bitmap != null) {
			fileCache.saveBitmap(bitmap, url);
			memoryCache.addBitmapToCache(url, bitmap);
		}
		return bitmap;
	}

	/** 保存图片到本地 */
	public File saveBitmapAndReturn(Bitmap bm, String url) {
		return fileCache.saveBitmapAndReturn(bm, url);
	}
}
