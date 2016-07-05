package com.sunfusheng.StickyHeaderListView.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.sunfusheng.StickyHeaderListView.R;
import com.sunfusheng.StickyHeaderListView.model.OperationEntity;
import com.sunfusheng.StickyHeaderListView.ui.GridViewCompat;
import com.sunfusheng.StickyHeaderListView.ui.GridViewUtils;
import com.sunfusheng.StickyHeaderListView.view.StickyListView.StickyListHeadersAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sunfusheng on 16/4/20.
 */
public class FilterMoreHeaderOperationAdapter extends BaseListAdapter<OperationEntity> {

    public FilterMoreHeaderOperationAdapter(Context context) {
        super(context);
    }

    public FilterMoreHeaderOperationAdapter(Context context, List<OperationEntity> list) {
        super(context, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_operation, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        OperationEntity entity = getItem(position);

        holder.tvTitle.setText(entity.getTitle());
        mImageManager.loadUrlImage(entity.getImage_url(), holder.ivImage);
        if (TextUtils.isEmpty(entity.getSubtitle())) {
            holder.tvSubtitle.setVisibility(View.INVISIBLE);
        } else {
            holder.tvSubtitle.setVisibility(View.VISIBLE);
            holder.tvSubtitle.setText(entity.getSubtitle());
        }

        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.iv_image)
        ImageView ivImage;
        @Bind(R.id.tv_title)
        TextView tvTitle;
        @Bind(R.id.tv_subtitle)
        TextView tvSubtitle;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    /**
     * 新房筛选更多,界面的adapter
     * 标题可以固定
     */
    public static class FilterMoreStickyListGridAdapter extends BaseAdapter implements
            StickyListHeadersAdapter, SectionIndexer {

        private final Context mContext;
        private String[] mCountries;
        private int[] mSectionIndices;
        private Character[] mSectionLetters;
        private LayoutInflater mInflater;

        public FilterMoreStickyListGridAdapter(Context context) {
            mContext = context;
            mInflater = LayoutInflater.from(context);
            mCountries = context.getResources().getStringArray(R.array.countries);
            mSectionIndices = getSectionIndices();
            mSectionLetters = getSectionLetters();
        }

        private int[] getSectionIndices() {
            ArrayList<Integer> sectionIndices = new ArrayList<Integer>();
            char lastFirstChar = mCountries[0].charAt(0);
            sectionIndices.add(0);
            for (int i = 1; i < mCountries.length; i++) {
                if (mCountries[i].charAt(0) != lastFirstChar) {
                    lastFirstChar = mCountries[i].charAt(0);
                    sectionIndices.add(i);
                }
            }
            int[] sections = new int[sectionIndices.size()];
            for (int i = 0; i < sectionIndices.size(); i++) {
                sections[i] = sectionIndices.get(i);
            }
            return sections;
        }

        private Character[] getSectionLetters() {
            Character[] letters = new Character[mSectionIndices.length];
            for (int i = 0; i < mSectionIndices.length; i++) {
                letters[i] = mCountries[mSectionIndices[i]].charAt(0);
            }
            return letters;
        }

        @Override
        public int getCount() {
            return mCountries.length;
        }

        @Override
        public Object getItem(int position) {
            return mCountries[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.gridview_filter_more, parent, false);
                holder.gridView = (GridViewCompat) convertView.findViewById(R.id.my_gridview);
    //            holder.text = (TextView) convertView.findViewById(R.id.text);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.gridView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            holder.gridView .setAdapter(new FilterMoreGridAdapter(mContext));
            holder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> view, View arg1, int pos, long id) {
                    // We need to invalidate all views on 4.x versions
                    GridViewCompat gridView = (GridViewCompat) view;
                    gridView.invalidateViews();
    //                gridView.invalidate();
                }

            });

            // 计算GridView宽度,
            GridViewUtils.updateGridViewLayoutParams(holder.gridView, 4);
            Log.d("123","mCountries[position]  "+position+"   "+mCountries[position]);
            return convertView;
        }

        @Override
        public View getHeaderView(int position, View convertView, ViewGroup parent) {
            HeaderViewHolder holder;

            if (convertView == null) {
                holder = new HeaderViewHolder();
                convertView = mInflater.inflate(R.layout.item_list_filter_more_grid_title, parent, false);
                holder.text = (TextView) convertView.findViewById(R.id.text);
                convertView.setTag(holder);
            } else {
                holder = (HeaderViewHolder) convertView.getTag();
            }

            // set header text as first char in name
            CharSequence headerChar = mCountries[position].subSequence(0, 1);
            holder.text.setText(headerChar);
            Log.d("123","headerChar  "+headerChar+"   position"+"   parent "+parent);
            return convertView;
        }

        /**
         * Remember that these have to be static, postion=1 should always return
         * the same Id that is.
         */
        @Override
        public long getHeaderId(int position) {
            // return the first character of the country as ID because this is what
            // headers are based upon
            return mCountries[position].subSequence(0, 1).charAt(0);
        }

        @Override
        public int getPositionForSection(int section) {
            if (mSectionIndices.length == 0) {
                return 0;
            }

            if (section >= mSectionIndices.length) {
                section = mSectionIndices.length - 1;
            } else if (section < 0) {
                section = 0;
            }
            return mSectionIndices[section];
        }

        @Override
        public int getSectionForPosition(int position) {
            for (int i = 0; i < mSectionIndices.length; i++) {
                if (position < mSectionIndices[i]) {
                    return i - 1;
                }
            }
            return mSectionIndices.length - 1;
        }

        @Override
        public Object[] getSections() {
            return mSectionLetters;
        }

        public void clear() {
            mCountries = new String[0];
            mSectionIndices = new int[0];
            mSectionLetters = new Character[0];
            notifyDataSetChanged();
        }

        public void restore() {
            mCountries = mContext.getResources().getStringArray(R.array.countries);
            mSectionIndices = getSectionIndices();
            mSectionLetters = getSectionLetters();
            notifyDataSetChanged();
        }

        class HeaderViewHolder {
            TextView text;

        }

        class ViewHolder {
            TextView text;
            GridViewCompat gridView;
        }

    }
}
