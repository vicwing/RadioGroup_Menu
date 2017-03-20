package com.example.cdj.myapplication.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

public class CircleCornerTextView extends TextView {
	
	public CircleCornerTextView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
//	int textColor;
//	public void setTextColor(int color){
//		
//		this.textColor=color;
//	}
	
	public String getText() {
		return text;
	}
	
	String text ="";
	  private int mAscent; 
	public void setText(String text){
		this.text = text;
		if(text !=null && text.length()<=4){
			this.text =" "+ text +" ";
		}
		requestLayout();
//		LayoutParams layoutParams=getLayoutParams();
//		FontMetrics fm = paint.getFontMetrics();  
////		layoutParams.width=(int) paint.measureText(text)+getPaddingLeft()+getPaddingRight();
////		layoutParams.height=(int)Math.ceil(fm.descent - fm.ascent)+getPaddingLeft()+getPaddingRight();  
//		
//		layoutParams.width=100;
//		layoutParams.height=100;  
//		setLayoutParams(layoutParams);
	}
	

	public void setTextSize(int textSize) {
		this.textSize = textSize;
		paint.setTextSize(textSize);
	}

	int strokerWidth=1;
	
	public CircleCornerTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		paint = new Paint();
		borderColor= Color.parseColor("#50adda");
		//paint.setColor(); // 设置画笔颜色
		paint.setTextSize(textSize);
		paint.setStrokeWidth(strokerWidth);
		//paint.setColor(Color.BLACK); // 设置画笔颜色
		//paint.setAlpha(200); // 设置透明度
		paint.setAntiAlias(true);
	}

	public CircleCornerTextView(Context context, AttributeSet attrs,
								int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	
	


	Paint paint;
	int borderColor;
	int textColor;
	int bgColor= Color.TRANSPARENT;
	public void setBgColor(int bgColor) {
		this.bgColor = bgColor;
	}
	
	public void setTextColor(int textColor) {
		this.textColor = textColor;
	}
	public int getTextColor() {
		if(textColor==0){
			return borderColor;
		}
		return textColor;
	}


	protected float textSize=35;
	protected float paddingLeft=0;
	protected float paddingRight=0;
	protected float paddingTop=0;
	protected float paddingBottom=0;

	protected float lineSpace=5;
	protected int lineWidth=320;
	protected float lineHeight=20;
	protected int lineCount;
	protected int maxLines=-1;//最大的行数

	@Override
	    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {  
	        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
//		  setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), result);
	  }
	  
	    private int measureWidth(int measureSpec) {  
	        int result = 0;  
	        int specMode = MeasureSpec.getMode(measureSpec);
	        int specSize = MeasureSpec.getSize(measureSpec);
	  
	        if (specMode == MeasureSpec.EXACTLY) {
	            // We were told how big to be  
	            result = specSize;  
	        } else {  
	            // Measure the text
	            result = (int) paint.measureText(text) + getPaddingLeft() + getPaddingRight();
	            if (specMode == MeasureSpec.AT_MOST) {
	                // Respect AT_MOST value if that was what is called for by  
	                // measureSpec  
	                result = Math.min(result, specSize);// 60,480
	            }  
	        }  
	  
	        return result;  
	    }  
	  
	    private int measureHeight(int measureSpec) {  
	        int result = 0;  
	        int specMode = MeasureSpec.getMode(measureSpec);
	        int specSize = MeasureSpec.getSize(measureSpec);
	  
	        mAscent = (int) paint.ascent();  
	        if (specMode == MeasureSpec.EXACTLY) {
	            // We were told how big to be  
	            result = specSize;  
	        } else {  
	            // Measure the text (beware: ascent is a negative number)
	//	            result = (int) (-mAscent + paint.descent()) + getPaddingTop() + getPaddingBottom();
		            result = (int) (-mAscent + paint.descent()) + getPaddingTop() + getPaddingBottom();
//					getTextViewHeight(this);
//					getDesiredHeight(getLayout());
	            if (specMode == MeasureSpec.AT_MOST) {
	                // Respect AT_MOST value if that was what is called for by  
	                // measureSpec  
	                result = Math.min(result, specSize);
	            }  
	        }  
	        return result*2;
	    }  
	
	
	
	public void setBorderColor(int color) {
		borderColor = color;
	}
	
	RectF rect=new RectF();
	Rect targetRect=new Rect();
 
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		getLineCount();
		Logger.d("lineCount  "+getLineCount());
		int radius = getMeasuredWidth() < getMeasuredHeight() ? getMeasuredWidth()/2 : getMeasuredHeight()/2;
		
		if(bgColor!= Color.TRANSPARENT){
			paint.setColor(bgColor);
			paint.setStyle(Paint.Style.FILL);
//			paint.setAlpha(254); // 设置透明度
			rect.set(strokerWidth, strokerWidth, getMeasuredWidth()-strokerWidth, getMeasuredHeight()-strokerWidth);
			canvas.drawRoundRect(rect, radius,radius, paint); // 画圆
		}

		paint.setColor(borderColor);
		paint.setStyle(Paint.Style.STROKE);
//		paint.setAlpha(254); // 设置透明度

		//设置字体的长度
//		rect.set(strokerWidth, strokerWidth, getMeasuredWidth() - strokerWidth, getMeasuredHeight() - strokerWidth);
		String prefixStr = text.substring(0, 2);
		String contentStr = text.substring(2, text.length());
		float prefixTextWidth = paint.measureText(prefixStr);

		float priefixTextRight = prefixTextWidth+getPaddingRight();
		rect.set(strokerWidth, strokerWidth, priefixTextRight, getMeasuredHeight() - strokerWidth);

//		Logger.d("prefix "+prefixStr+"left="+strokerWidth+"  top="+strokerWidth+"  right="+prefixTextWidth+"bottom="+(getMeasuredHeight() - strokerWidth));
		canvas.drawRoundRect(rect, radius, radius, paint); // 画圆
		drawPreText(canvas, prefixStr,radius);



		//主要内容的text
		darawContentText(canvas, contentStr, (int) priefixTextRight);
//		darawContentText1(canvas);

//		Logger.d("textWidth  "+paint.measureText(text));
//		canvas.drawRect(rect,paint);

		//System.out.println("drawing !!!!!!!!!");

		//canvas.drawColor(Color.BLUE); //设置背景色

	}

	private void darawContentText1(Canvas canvas) {

		TextPaint textPaint = new TextPaint();
		textPaint.setColor(Color.BLUE);
		textPaint.setTextSize(30);
		Rect rect = new Rect(0,0,300,400);
		String text = "这是一串需要进行换行显示的文字。";
		//文字自动换行
		StaticLayout layout = new StaticLayout(text, textPaint, rect.width(), Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F,true);
		canvas.save();
		textPaint.setTextAlign(Paint.Align.LEFT);
		//文字的位置
		canvas.translate(rect.left , rect.top);
		layout.draw(canvas);
		canvas.restore();
	}

	private void darawContentText(Canvas canvas, String contentStr, int priefixTextRight) {
//		paint.setStyle(Paint.Style.FILL);
//		FontMetrics fontMetrics = paint.getFontMetrics();
//		paint.setTextAlign(Align.CENTER);
//		paint.setColor(getTextColor());
////		targetRect.set(0, 0, getMeasuredWidth(), getMeasuredHeight());
//		targetRect.set(priefixTextRight, 0, getMeasuredWidth(), getMeasuredHeight());
//		int baseline = (int) (targetRect.top + (targetRect.bottom - targetRect.top - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top);
//		canvas.drawText(contentStr,targetRect.centerX(),baseline, paint);

		Rect rect = new Rect(priefixTextRight,0,getWidth(),getHeight()*2);
		FontMetrics fontMetrics = paint.getFontMetrics();
		TextPaint textPaint = new TextPaint();
		textPaint.setColor(getTextColor());
		textPaint.setTextSize(textSize);
//		textPaint.setAntiAlias(true);
		StaticLayout layout = new StaticLayout(contentStr, textPaint, getWidth()/2, Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);
		canvas.save();
		textPaint.setTextAlign(Paint.Align.LEFT);
		canvas.translate(rect.left,rect.top);
		layout.draw(canvas);
		canvas.restore();//别忘了restore
		Logger.d("rect  widht "+rect.width()+" height "+rect.height());
		Logger.d("view  width "+getWidth()+" height "+getHeight()+" textHeight "+getTextViewHeight(this));
	}

	private int getTextViewHeight(TextView pTextView) {
		Layout layout = pTextView.getLayout();
		int desired = layout.getLineTop(pTextView.getLineCount());
		int padding = pTextView.getCompoundPaddingTop() + pTextView.getCompoundPaddingBottom();
		return desired + padding;
	}

	/**
	 * 绘制边框字体
	 * @param canvas
	 * @param prefixStr
	 * @param radius
     */
	private void drawPreText(Canvas canvas, String prefixStr,int radius) {
		paint.setStyle(Paint.Style.FILL);
		paint.setTextAlign(Align.CENTER);
		paint.setColor(getTextColor());
		FontMetrics fontMetrics = paint.getFontMetrics();
		int baseline = (int) (rect.top + (rect.bottom - rect.top - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top);
		paint.measureText(prefixStr);
		canvas.drawText(prefixStr,getPaddingLeft()+radius,baseline, paint);
	}

	private int getDesiredHeight(Layout layout) {
		if (layout == null) {
			return 0;
		}

		// 行数
		int linecount = this.getLineCount();
		// padding
		int pad = getCompoundPaddingTop() + getCompoundPaddingBottom();
		// 期望值
		int desired = layout.getLineTop(linecount);

		desired += pad;

		// Check against our minimum height
		desired = Math.max(desired, getSuggestedMinimumHeight());

		return desired;
	}
}
