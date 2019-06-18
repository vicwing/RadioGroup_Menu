/*
 * Copyright (C) 2012 Jake Wharton
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.cdj.myapplication.widget.pageindicator;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;

import com.example.cdj.myapplication.R;
import com.orhanobut.logger.Logger;

import java.math.BigDecimal;

/**
 * 类描述：滚动下划线视图,结合Recyclview使用.
 * 创建人：vicwing
 * 创建时间：2019-05-22 11:36
 * 最后修改人：vicwing
 */
public class LinePageIndicator extends BasePageIndicator {

    private final Paint mPaintbackground = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint mPaintSelected = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float mLineWidth;
    private float strokeHeight;
    private boolean lineTypeHasCorner;

    public LinePageIndicator(Context context) {
        this(context, null);
    }

    public LinePageIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.vpiLinePageIndicatorStyle);
    }

    public LinePageIndicator(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (isInEditMode()) return;
        final Resources res = getResources();
        //Load defaults from resources
        final int defaultSelectedColor = res.getColor(R.color.default_line_indicator_selected_color);
        final int defaultUnselectedColor = res.getColor(R.color.default_line_indicator_unselected_color);
        final float defaultLineWidth = res.getDimension(R.dimen.default_line_indicator_line_width);
        final float defaultStrokeWidth = res.getDimension(R.dimen.default_line_indicator_stroke_width);
        //Retrieve styles attributes
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LinePageIndicator, defStyle, 0);

        mLineWidth = a.getDimension(R.styleable.LinePageIndicator_lineWidth, defaultLineWidth);
        strokeHeight = a.getDimension(R.styleable.LinePageIndicator_strokeWidth, defaultStrokeWidth);
        setStrokeWidth(strokeHeight);
        mPaintbackground.setColor(a.getColor(R.styleable.LinePageIndicator_unselectedColor, defaultUnselectedColor));
        mPaintSelected.setColor(a.getColor(R.styleable.LinePageIndicator_selectedColor, defaultSelectedColor));
        Drawable background = a.getDrawable(R.styleable.LinePageIndicator_android_background);
        lineTypeHasCorner = a.getBoolean(R.styleable.LinePageIndicator_lineTypeCorner, false);
        if (background != null) {
            setBackgroundDrawable(background);
        }
        a.recycle();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Logger.d("onDraw:   " + "width = [" + getWidth() + "]" + "height = " + getHeight());
        if (mRecyclerView == null) {
            return;
        }
        final int pageCount = pageCount();
        if (pageCount <= 1) {
            setVisibility(INVISIBLE);
            return;
        }

        if (mCurrentPage >= pageCount) {
            setCurrentItem(pageCount - 1);
            return;
        }


        float lastPageWidth = mLineWidth * (mPageColumn - getLastPageItemColumn()) / mPageColumn;
        final float indicatorTotalWidth = pageCount * mLineWidth - lastPageWidth;
//        final float indicatorTotalWidth = (pageCount * mLineWidth);

        final float paddingTop = getPaddingTop();
        final float paddingLeft = getPaddingLeft();
        final float paddingRight = getPaddingRight();

        float verticalOffset = paddingTop + ((getHeight() - paddingTop - getPaddingBottom()) / 2.0f);
        //居中偏移量
        float gravtyLeft = (getWidth() - indicatorTotalWidth) / 2;
        float horizontalOffset = paddingLeft + gravtyLeft;
        float radius = 0;
        if (lineTypeHasCorner) {
            radius = strokeHeight / 2;
            mPaintbackground.setStrokeCap(Paint.Cap.ROUND);
            mPaintSelected.setStrokeCap(Paint.Cap.ROUND);
        }
        float starx = horizontalOffset;
        float endx = horizontalOffset + indicatorTotalWidth;


        canvas.drawLine(starx + radius, verticalOffset, endx - radius, verticalOffset, mPaintbackground);
        float dx = getdx(indicatorTotalWidth);
        float startxSelect = horizontalOffset + dx;
        float endXSelet = startxSelect + mLineWidth;
        canvas.drawLine(startxSelect + radius, verticalOffset, endXSelet - radius, verticalOffset, mPaintSelected);

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Logger.d("onDraw:   " + " pageCount = [" + pageCount() + "]" + " eachPageItemCount =  " + eachPageItemCount());
    }

    private float getdx(float mLineWidth) {
        float div = div(horizontalOffset, getRecycleViewWidth());
        return (div * mLineWidth);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    /**
     * Determines the width of this view
     *
     * @param measureSpec A measureSpec packed into an int
     * @return The width of the view, honoring constraints from measureSpec
     */
    private int measureWidth(int measureSpec) {
        float result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if ((specMode == MeasureSpec.EXACTLY) || (mRecyclerView == null)) {
            //We were told how big to be
            result = specSize;
        } else {
            //Calculate the width according the views count
            final int count = pageCount();
            result = getPaddingLeft() + getPaddingRight() + (count * mLineWidth) + ((count - 1));
            //Respect AT_MOST value if that was what is called for by measureSpec
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return (int) Math.ceil(result);
    }

    /**
     * Determines the height of this view
     *
     * @param measureSpec A measureSpec packed into an int
     * @return The height of the view, honoring constraints from measureSpec
     */
    private int measureHeight(int measureSpec) {
        float result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            //We were told how big to be
            result = specSize;
        } else {
            //Measure the height
            result = mPaintSelected.getStrokeWidth() + getPaddingTop() + getPaddingBottom();
            //Respect AT_MOST value if that was what is called for by measureSpec
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return (int) Math.ceil(result);
    }

    public void setUnselectedColor(int unselectedColor) {
        mPaintbackground.setColor(unselectedColor);
        invalidate();
    }

    public int getUnselectedColor() {
        return mPaintbackground.getColor();
    }

    public void setSelectedColor(int selectedColor) {
        mPaintSelected.setColor(selectedColor);
        invalidate();
    }

    public int getSelectedColor() {
        return mPaintSelected.getColor();
    }

    public void setLineWidth(float lineWidth) {
        mLineWidth = lineWidth;
        invalidate();
    }

    public float getLineWidth() {
        return mLineWidth;
    }

    public void setStrokeWidth(float lineHeight) {
        mPaintSelected.setStrokeWidth(lineHeight);
        mPaintbackground.setStrokeWidth(lineHeight);
        invalidate();
    }

    public float getStrokeWidth() {
        return mPaintSelected.getStrokeWidth();
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        SavedState savedState = (SavedState) state;
        super.onRestoreInstanceState(savedState.getSuperState());
        mCurrentPage = savedState.currentPage;
        requestLayout();
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState savedState = new SavedState(superState);
        savedState.currentPage = mCurrentPage;
        return savedState;
    }

    static class SavedState extends BaseSavedState {
        int currentPage;

        public SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            currentPage = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeInt(currentPage);
        }

        @SuppressWarnings("UnusedDeclaration")
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            @Override
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            @Override
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }

    public float div(float v1, float v2) {
        BigDecimal b1 = new BigDecimal(Float.toString(v1));
        BigDecimal b2 = new BigDecimal(Float.toString(v2));
        return b1.divide(b2, 10, BigDecimal.ROUND_HALF_UP).floatValue();
    }


}