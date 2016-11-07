package com.example.cdj.myapplication.mainfunction.function2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cdj.myapplication.Bean.SecListItemEntity;
import com.example.cdj.myapplication.R;
import com.example.cdj.myapplication.baseadapter.SecListItemAdapter;
import com.example.cdj.myapplication.baseadapter.adapterhelper.QuickAdapter;
import com.example.cdj.myapplication.loadmore.LoadMoreContainer;
import com.example.cdj.myapplication.loadmore.LoadMoreHandler;
import com.example.cdj.myapplication.loadmore.LoadMoreListViewContainer;
import com.orhanobut.logger.Logger;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by cdj onCallBackData 2016/2/1.
 */
public class FragmentSecond extends Fragment {
    @Bind(R.id.iv_logotest)
    ImageView ivLogotest;
    private TextView textview;
    private LoadMoreListViewContainer loadMoreListViewContainer;
    private ListView mListView;
    private QuickAdapter<SecListItemEntity> mAdapter;
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

        loadMoreListViewContainer = (LoadMoreListViewContainer) view.findViewById(R.id.load_more_list_view_container);
        setLoadMoreDefaultFootView(loadMoreListViewContainer);

        mListView = (ListView) view.findViewById(R.id.load_more_small_image_list_view);
        mAdapter = new SecListItemAdapter(getActivity(), R.layout.item_list_secondlist);
        mListView.setAdapter(mAdapter);
    }


    /**
     * 设置默认的加载更多.
     * @param loadMoreListViewContainer
     */
    private void setLoadMoreDefaultFootView(final LoadMoreListViewContainer loadMoreListViewContainer) {
        // load more container
        loadMoreListViewContainer.useDefaultHeader();
        //设定view可以加载更多
        loadMoreListViewContainer.setAutoLoadMore(true);
        loadMoreListViewContainer.setShowLoadingForFirstPage(true);
        loadMoreListViewContainer.setLoadMoreHandler(new LoadMoreHandler() {
            @Override
            public void onLoadMore(LoadMoreContainer loadMoreContainer) {
//                currentPage++;
                Logger.d("LoadMoreHandler  加载更多..............currentPage ");
//                if (currentPage<=pageCount){
//                    requestUpdate(String.valueOf(currentPage));
//                } else {
//                    loadMoreListViewContainer.loadMoreFinish(true,false);
//                }
            }
        });
    }
    /**
     * 下载图片.保存到本地.
     * @param pictureUrl
     */
    private void loadAndSaveBitmap(final String pictureUrl) {
//        new AsyncTask<String, Integer, Bitmap>() {
//            @Override
//            protected Bitmap doInBackground(String... params) {
//                Bitmap bitmap = HttpHelper.getImg(getActivity(), pictureUrl);
//                //        BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
//                //        ivLogotest.setBackgroundDrawable(bitmapDrawable);
//                //        ivLogotest.setImageDrawable(bitmap);
//                if (bitmap != null) {
//                    ImageCacheHelper imageCacheHelper = new ImageCacheHelper(getActivity());
//                    File imgPath = imageCacheHelper.saveBitmapAndReturn(bitmap, pictureUrl);
//                    // BitmapDrawable(BitmapFactory.decodeFile(imgPath.toString()));
//                    KLog.i("网上下载图片大小bitmap=" + bitmap.getByteCount() / 1024);
//                    if (imgPath!=null)
//
//                        KLog.d("成功保存imgPath==" + imgPath.toString());
//                    return bitmap;
//                }
//                return bitmap;
//            }
//
//            @Override
//            protected void onPostExecute(Bitmap bitmap) {
//                super.onPostExecute(bitmap);
//                ivLogotest.setImageBitmap(bitmap);
//            }
//        }.execute(pictureUrl);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}