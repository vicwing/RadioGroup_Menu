package com.example.cdj.myapplication.mainfunction.function2;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cdj.myapplication.R;
import com.example.cdj.myapplication.http.HttpHelper;
import com.example.cdj.myapplication.utils.imagecache.ImageCacheHelper;
import com.socks.library.KLog;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by cdj on 2016/2/1.
 */
public class FragmentSecond extends Fragment {
    @Bind(R.id.iv_logotest)
    ImageView ivLogotest;
    private TextView textview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.content_main2, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textview = (TextView) view.findViewById(R.id.textview);
//        textview
        String picUrl = "http://yun.qfangimg.com/group1/1080x1920/M00/3C/51/wagAK1Xef6yAS6fZAAByfHOhSN8659.png";

//        KLog.d("外存储  "+ getActivity().getExternalCacheDir());
//        loadAndSaveBitmap(picUrl);
//        KLog.d("外存储  "+ getActivity().getExternalCacheDir());
    }


    /**
     * 下载图片.保存到本地.
     * @param pictureUrl
     */
    private void loadAndSaveBitmap(final String pictureUrl) {
        new AsyncTask<String, Integer, Bitmap>() {
            @Override
            protected Bitmap doInBackground(String... params) {
                Bitmap bitmap = HttpHelper.getImg(getActivity(), pictureUrl);
                //        BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
                //        ivLogotest.setBackgroundDrawable(bitmapDrawable);
                //        ivLogotest.setImageDrawable(bitmap);
                if (bitmap != null) {
                    ImageCacheHelper imageCacheHelper = new ImageCacheHelper(getActivity());
                    File imgPath = imageCacheHelper.saveBitmapAndReturn(bitmap, pictureUrl);
                    // BitmapDrawable(BitmapFactory.decodeFile(imgPath.toString()));
                    KLog.i("网上下载图片大小bitmap=" + bitmap.getByteCount() / 1024);
                    if (imgPath!=null)

                        KLog.d("成功保存imgPath==" + imgPath.toString());
                    return bitmap;
                }
                return bitmap;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                ivLogotest.setImageBitmap(bitmap);
            }
        }.execute(pictureUrl);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}