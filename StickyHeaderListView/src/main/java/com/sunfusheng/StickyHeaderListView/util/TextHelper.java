/**   
 * @FileName: TextHelper.java 
 * @Package:com.qfang.qfangmobile.util 
 * @Description: TODO
 * @author: Administrator  
 * @date:2013-12-25 上午11:21:11 
 * @version V1.0   
 */
package com.sunfusheng.StickyHeaderListView.util;

import android.text.TextUtils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName: TextHelper 
 * @Description: TODO
 * @author: Administrator
 * @date:2013-12-25 上午11:21:11 
 */
public class TextHelper {

	public static boolean isEmptyOrZero(String str){
		return TextUtils.isEmpty(str) || "0".equals(str.trim()) || "null".equals(str.trim());
	}
	
	public static String PROPERTY_UNIT = "元/㎡·月";//物业费单位
	static DecimalFormat   df   =   new   DecimalFormat("#####0.##");

	/**
	 *  pattern="0.00#"
		小数部分最多1位， 整数部分出现了0
	 */
	static DecimalFormat   df1   =   new   DecimalFormat("0.0"); //保留一位小数点

	static DecimalFormat   df2   =   new   DecimalFormat("#");
	static public String floatTwo(Double value){
		return df.format(value);
	}
	
	static public String StringToFloatTwo(String value,String suffix,String emptyStr){
		return StringToFloatTwo("", value, suffix, emptyStr);
	}
	
	static public String StringToFloatTwo(String prefix, String value, String suffix, String emptyStr){
		if(isEmpty(value)){
			return emptyStr;
		}
		Double doubleValue=Double.parseDouble(value);
		return prefix+floatTwo(doubleValue)+suffix; 
	}


	static DecimalFormat df3 = new DecimalFormat("######0");//四舍五入
	static public String DoubleToInt(String value){
		if(isEmpty(value)){
			return null;
		}
		
		Double doubleValue=Double.parseDouble(value);
		return df3.format(doubleValue); 
	}
	
	static public String StringToFloatTwo(String value,String prefix){ 
		return StringToFloatTwo(value, prefix, "暂无信息");
	}
	
	static public String floatToInt(Double value){
		return df2.format(value);
	}
	
	static public String formatTimeNoShowYear(Date date) {
		String todaySDF = "今天 HH:mm";
		String yesterDaySDF = "昨天 HH:mm";
		String otherSDF = "MM-dd HH:mm";
		return formatTime(date, todaySDF, yesterDaySDF, otherSDF);
	}
	
	static public String formatTime(Date date){
		String todaySDF = "今天 HH:mm";
		String yesterDaySDF = "昨天 HH:mm";
		String otherSDF = "yyyy-MM-dd HH:mm";
		return formatTime(date, todaySDF, yesterDaySDF, otherSDF);
		
	}
	
	static  SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
	static  SimpleDateFormat sdf2 =   new SimpleDateFormat( "yyyy-MM-dd" );
	
	
	static public String formatTime(String time){
		String result="暂无";
		try {
			result= TextHelper.formatTime(sdf.parse(time));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
	
	static public String formatStringToYearString(String time,String prefix,String emptyStr){
		String result=emptyStr;
		if(!TextUtils.isEmpty(time)){
			try {
				
				Calendar dateCalendar = Calendar.getInstance();
				dateCalendar.setTime(sdf2.parse(time));
				result=String.valueOf(dateCalendar.get(Calendar.YEAR))+prefix;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	static public String formatDateNoTime(String time){
		String result="暂无";
		if(TextUtils.isEmpty(time)){
			return result;
		}
		
		try {
			String todaySDF = "今天";
			String yesterDaySDF = "昨天";
			String otherSDF = "yyyy-MM-dd";
			result= TextHelper.formatTime(sdf.parse(time),todaySDF, yesterDaySDF, otherSDF);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
	
	static public String formatTime(Date date,String todaySDF,String yesterDaySDF,String otherSDF) {
		// 将传入时间与当前时间进行对比，是否今天昨天
			SimpleDateFormat sfd = null;
			String time = "";
			Calendar dateCalendar = Calendar.getInstance();
			dateCalendar.setTime(date);
			Date now = new Date();
			Calendar targetCalendar = Calendar.getInstance();
			targetCalendar.setTime(now);
			targetCalendar.set(Calendar.HOUR_OF_DAY, 0);
			targetCalendar.set(Calendar.MINUTE, 0);
			if (dateCalendar.after(targetCalendar)) {
				targetCalendar.add(Calendar.DATE, 1);
				if(dateCalendar.before(targetCalendar)){
					sfd = new SimpleDateFormat(todaySDF);
					time = sfd.format(date);
					return time;
				}
				 
				targetCalendar.add(Calendar.DATE, -1);
				
				
			} else {
				targetCalendar.add(Calendar.DATE, -1);
				if (dateCalendar.after(targetCalendar)) {
					sfd = new SimpleDateFormat(yesterDaySDF);
					time = sfd.format(date);
					return time;
				}
			}
			sfd = new SimpleDateFormat(otherSDF);
			time = sfd.format(date);
			return time;
		}

	
	static public String formatPrice(String price){
		return replaceNullTOTarget(price, "暂无信息  ","","");
	}
	
	static public String formatPrice(String price,String suffix){
		return replaceNullTOTarget(price, "暂无信息  ",suffix,"");
	}
	
	static public String formatPriceForInt(String price,String suffix){
		return  formatPriceForInt(price, suffix,"暂无信息  ");
//		if(price!=null){
//			int dotIndex=price.indexOf(".");
//			if(dotIndex>-1){			
//				return replaceNullTOTarget(price.substring(0, dotIndex), "暂无信息  ",suffix,"");
//			}
//		} 
//		return replaceNullTOTarget(price, "暂无信息  ",suffix,"");
	}
	
	static public String formatPriceForInt(String price,String suffix,String target){
		return formatPriceForInt(price,suffix,"",target);
	}
	
	static public String formatPriceForInt(String price,String suffix,String prefix,String target){
		if(!TextUtils.isEmpty(price) && !"null".equals(price) && !"0".equals(price)){
//			int dotIndex=price.indexOf(".");
//			if(dotIndex>-1){			
//				return replaceNullTOTarget(price.substring(0, dotIndex), target,suffix,"",false);
//			}
			return  replaceNullTOTarget(DoubleToInt(price), target,suffix,prefix);
		} 
		return replaceNullTOTarget(price, target,suffix,prefix,false);
	}
	
	static public String formatPriceForIntWithWang(String price){
		 
		return formatPriceForIntWithWang(price, "万");
	}
	
	static public String formatPriceForIntWithWang(String price,String suffix){
		 
		return formatPriceForIntWithWang(price,suffix,"");
	}
	
	static public String formatPriceForIntWithWang(String price,String suffix,String prefix){
		if(!TextUtils.isEmpty(price) && !"null".equals(price)){
				Double priceint= (Double.parseDouble(price)/10000f);
				return replaceNullTOTarget(df3.format(priceint) , "暂无信息  ",suffix,prefix);
			 
		} 
		return replaceNullTOTarget(price, "暂无信息  ","","");
	}
	
	static public double formatPriceForIntWithWangReturnDouble(String price){
		if(!TextUtils.isEmpty(price) && !"null".equals(price)){
			 
				Double priceint= (Double.parseDouble(price)/10000d);
				return Double.parseDouble(df3.format(priceint));
			 
		} 
		return 0d;
	}
	
	
	 static public String formatPriceWithTwoFloat(String price,String suffix){
	    	return formatPriceWithTwoFloat(price, suffix,"");
	 }
	 
	 static public String formatPriceWithTwoFloat(String price,String suffix,String prefix){
	    	return StringToFloatTwo(prefix, price, suffix, "暂无");
	 }
	

    static public String formatPriceForFloatWithWangWithTwoFloat(String price){
    	return formatPriceForFloatWithWangWithTwoFloat(price, "");
    }


	static public String formatPriceForFloatWithWangWithTwoFloat(String price,String suffix){

		if(!TextUtils.isEmpty(price) && !"null".equals(price)){

			Double priceF=Double.parseDouble(price);

			if(priceF>10000f){
				return replaceNullTOTarget(TextHelper.floatTwo(priceF/10000f) , "暂无信息  ","万元"+suffix,"");
			}
			else{
				return replaceNullTOTarget(TextHelper.floatTwo(priceF), "暂无信息  ","元"+suffix,"");
			}

		}
		return  "暂无信息  ";
	}
    
    
    static public String formatPriceForFloatOnlyWangWithTwoFloat(String prefix,String price,String suffix,String emptyStr){
    	
        if(!TextUtils.isEmpty(price) && !"null".equals(price)){

        	Double priceF=Double.parseDouble(price);

			return replaceNullTOTarget(TextHelper.floatTwo(priceF/10000f) , emptyStr,suffix,prefix);

        }
        return  emptyStr;
    }
	/**
	 * 多位数转换 ,价格,取整数结果.example:1,500,000=150万
	 */
	static public String formatPriceToIntWithWan(String price){

		if(!TextUtils.isEmpty(price) && !"null".equals(price)){

			Double priceF=Double.parseDouble(price);

			if(priceF>10000f){
				return replaceNullTOTarget(TextHelper.floatToInt(priceF / 10000f) , "暂无信息  ","万","");
			}
			else{
				return replaceNullTOTarget(TextHelper.floatToInt(priceF), "暂无信息  ","元","");
			}

		}
		return  "暂无信息  ";
	}

	/**
	 * 价格走势图,
	 * 多位数转换 ,价格保留两位数.example:1,500,000=150.2万
	 */
	static public String pricToWan(String price){

		if(!TextUtils.isEmpty(price) && !"null".equals(price)){

			Double priceF=Double.parseDouble(price);
			
//				double  priceD= priceF / 10000f;
				return replaceNullTOTarget(df1.format(priceF/10000f) ,"","万","");

		}
		return  null;
	}
	
	/**
	 * @param price  价格
	 * @param prefix  前缀描述
	 * @param emptyStr 当输入为0时,显示emptyStr
	 * @return
	 */
	static public String formatPriceForDoubleWithWang(double price, String prefix,String emptyStr){
		if(price!=0){
			Double priceint= price/10000d;
			return replaceNullTOTarget(TextHelper.floatTwo(priceint) ,"","万",prefix);
		}
		return emptyStr;
	}
	
	static public String toFromTo(String from,String to){
		return toFromTo(from, to, "");
	}
	
	static public String toFromTo(String from,String to,String suffix){
		 return toFromTo(from, to, "暂无信息", suffix);
		
	}
	
	static public String toFromTo(String from,String to,String nullToTargetStr,String suffix){
		if(TextUtils.isEmpty(from) || "null".equals(from) || TextUtils.isEmpty(to) || "null".equals(to)){
			return nullToTargetStr;
		}
		if(from.equals(to)){
			return to+suffix;
		}
		else{
			return from+"-"+to+suffix;
		}
		
	}
	
	
	static public boolean isEmpty(String text){
		return TextUtils.isEmpty(text) || "null".equals(text);
	}
	
	static public String replaceNullTOTarget(String text,String targetStr,String suffix,String prefix,boolean showZero){
		if(TextUtils.isEmpty(text) || "null".equals(text) || "\n".equals(text) || (!showZero && "0".equals(text) )){
			return targetStr;
		}
		 
		return prefix+text+suffix;
	}
	

	static public String replaceNullTOTarget(String text,String targetStr,String suffix,String prefix){
		return replaceNullTOTarget(text, targetStr, suffix, prefix, true);
	}
	
	static public String replaceNullTOTarget(String text,String targetStr,String suffix){
		 
		return replaceNullTOTarget(text, targetStr, suffix,"");
	}
	
	static public String replaceNullTOTarget(String text,String targetStr){
		if(TextUtils.isEmpty(text) || "null".equals(text)){
			return targetStr;
		}
		return text;
	}
	
	static public String replaceNullTOEmpty(String text){
		return replaceNullTOTarget(text, "","","");
	} 
	
	static public String replaceNullTOEmpty(String text,String suffix){
		return replaceNullTOTarget(text, "",suffix,"");
	} 
	
	static public String replaceNullTOEmpty(String text,String suffix,String prefix){
		return replaceNullTOTarget(text, "",suffix,prefix);
	} 
	
	static public String limitStringLength(String text,int length){
		text=replaceNullTOEmpty(text);
		if(text.length()>length){
			return text.substring(0, length)+"...";
		}
		return text;
	} 
	
	
	static public String getYear(String time,String suffix,String emptyStr){
		if(time==null){
			return emptyStr;
		}
		else{
			Calendar calendar=Calendar.getInstance();
			calendar.setTimeInMillis(Long.parseLong(time));
			
			return calendar.get(Calendar.YEAR)+suffix;
		}
		
	}
	
	static public String yyyy_MM_DD="yyyy-MM-dd";
	static public String yyyy="yyyy";
	static public String formatDate(String time,String formatStr){
		if (TextUtils.isEmpty(time)){
			return null;
		}
		SimpleDateFormat sfd = new SimpleDateFormat(formatStr);
		return sfd.format(new Date(Long.parseLong(time)));
	}
	static public String getArea(String area){
		return TextUtils.isEmpty(area) ? "暂无信息" :  area+"㎡";
	}
	static public String getArea(String area,String target){
		return TextUtils.isEmpty(area) ? target: area+"㎡";
	}
	
	/**
	 * 返回面积的整数
	 * @param area
	 * @param target
	 * @return
	 */
	static public String getAreaInt(String area,String target){
		return TextUtils.isEmpty(area) ? target:(int) Double.parseDouble(area)+"㎡";
	}

//
//	/**
//	 *
//	 * @param self
//	 * @param prefix     前缀
//	 * @param content
//	 * @param replaceContent  数据为空时显示的数据
//	 * @return
//	 */
//	static public Spannable getSpannableText(Context self,String prefix,String content,String replaceContent){
//		SpannableStringBuilder stringBuilder=null;
//		if (!TextUtils.isEmpty(content)){
//			 stringBuilder = new SpannableStringBuilder(prefix+content);
//		}else {//
//			 stringBuilder = new SpannableStringBuilder(prefix);
//			 stringBuilder.append(replaceContent);
//		}
//		stringBuilder.setSpan(new ForegroundColorSpan(self.getResources().getColor(R.color.grey_888888)), 0, prefix.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
////		if (TextUtils.isEmpty(content)) {
////			stringBuilder.append(replaceContent);
////		}
//		stringBuilder.setSpan(new ForegroundColorSpan(self.getResources().getColor(R.color.black2)), prefix.length(), stringBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//		stringBuilder.setSpan(new AbsoluteSizeSpan(ScreenUtil.Dp2Px(self,15)), 0, stringBuilder.length(),Spannable.SPAN_INCLUSIVE_INCLUSIVE);
//		return stringBuilder;
//	}
//
//	/**
//	 * 当数据为null时显示" 暂无"
//	 * @param self
//	 * @param prefix
//	 * @param content
//	 * @return
//	 */
//	static public Spannable getSpannableText(Context self,String prefix,String content){
//		return getSpannableText(self,prefix,content,"暂无");
//	}
//	/**
//	 * 当content数据为null时,返回null
//	 * @param self
//	 * @param prefix
//	 * @param content
//	 * @return
//	 */
//	static public Spannable getSpannableNull2Empty(Context self, String prefix, String content ){
//		if (TextUtils.isEmpty(content)) {
//			return null;
//		}else{
//			SpannableStringBuilder word = new SpannableStringBuilder(prefix+content);
//			word.setSpan(new ForegroundColorSpan(self.getResources().getColor(R.color.grey_888888)), 0, prefix.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//			word.setSpan(new ForegroundColorSpan(self.getResources().getColor(R.color.black2)), prefix.length(), word.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//			word.setSpan(new AbsoluteSizeSpan(ScreenUtil.Dp2Px(self,15)), 0, word.length(),Spannable.SPAN_INCLUSIVE_INCLUSIVE);
//			return word;
//		}
//	}
//	/**
//	 * 当content数据为null时不显示这个字段
//	 * @param self
//	 * @param prefix
//	 * @param content
//	 * @return
//	 */
//	static public Spannable getSpannableNull2Empty(Context self, String prefix, String content , int rsid){
//		if (TextUtils.isEmpty(content)) {
//			return null;
//		}else{
//			SpannableStringBuilder word = new SpannableStringBuilder(prefix+content);
//			word.setSpan(new ForegroundColorSpan(self.getResources().getColor(R.color.grey_888888)), 0, prefix.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//			word.setSpan(new ForegroundColorSpan(self.getResources().getColor(R.color.black2)), prefix.length(), word.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//			word.setSpan(new AbsoluteSizeSpan(ScreenUtil.Dp2Px(self,15)), 0, word.length(),Spannable.SPAN_INCLUSIVE_INCLUSIVE);
//			return word;
//		}
//	}
//
//	static public Spannable getSpannableText(Context self,String prefix,int prefixColor, String content, int contenColor,String replaceContent){
//		SpannableStringBuilder word = new SpannableStringBuilder(prefix+content);
//		word.setSpan(new ForegroundColorSpan(prefixColor), 0, prefix.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//		if (TextUtils.isEmpty(content)) {
//			word.append(replaceContent);
//		}
//		word.setSpan(new ForegroundColorSpan(contenColor), prefix.length(), word.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//		word.setSpan(new AbsoluteSizeSpan(ScreenUtil.Dp2Px(self,15)), 0, word.length(),Spannable.SPAN_INCLUSIVE_INCLUSIVE);
//		return word;
//	}
//	static public Spannable getSpannableText(Context self,String prefix,int prefixColor, String content, int contenColor){
//		return getSpannableText(self,prefix,prefixColor,content,contenColor,"");
//	}
//	/**
//	 * 获取房价走势图的 markview的字体
//	 * @param self
//	 * @param prefix
//	 * @param content
//	 * @return
//	 */
//	static public Spannable getLineChartMarkerViewText(Context self,String prefix, String content){
//		SpannableStringBuilder word = new SpannableStringBuilder(prefix+content);
//		word.setSpan(new ForegroundColorSpan(self.getResources().getColor(R.color.grey_cecece)), 0, prefix.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//		word.setSpan(new ForegroundColorSpan(self.getResources().getColor(R.color.white)), prefix.length(), word.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//		word.setSpan(new AbsoluteSizeSpan(ScreenUtil.Dp2Px(self,15)), 0, word.length(),Spannable.SPAN_INCLUSIVE_INCLUSIVE);
//		return word;
//	}
////	DecimalFormat decimalFormat = new DecimalFormat("#");
//	/**
//	 * 取正整数.四舍五入.
//	 * @param content
//	 * @return
//	 */
//	public static String  decimalInt(String content){
//		if (!TextUtils.isEmpty(content)) {
//			double parseDouble = Double.parseDouble(content);
//			return df2.format(parseDouble);
//		}
//		return  null;
//	}
//	/**
//	 * 获取物业管理费
//	 * @param minProperty 最低物业费
//	 * @param maxProperty 最高物业费
//	 * @return
//	 */
//	public static String getPropertyCharge(BigDecimal minProperty ,BigDecimal maxProperty){
//		if (minProperty==null) {
//			minProperty = new BigDecimal(0);
//		}
//		if(maxProperty==null){
//			maxProperty = new BigDecimal(0);
//		}
//		return getPropertyCharge(minProperty.floatValue(), maxProperty.floatValue());
//	}
//
//	/**
//	 * 获取物业管理费
//	 * @param minProperty 最低物业费
//	 * @param maxProperty 最高物业费
//	 * @return
//	 */
//	public static String getPropertyCharge(float minProperty ,float maxProperty){
//		if (minProperty==0&&maxProperty==0) {
//			return "未知";
//		}
//		if(minProperty==0){
//			return maxProperty+PROPERTY_UNIT;
//		}
//		if(maxProperty==0){
//			return minProperty+PROPERTY_UNIT;
//		}
//
//		return minProperty+"-"+maxProperty+PROPERTY_UNIT;
//	}
//
//	/**
//	 * 设置房价列表页.均价字段的 颜色 字体大小
//	 * @param price
//	 * @return
//	 */
//	public static SpannableStringBuilder setAvgPriceSpan(Context ctx,int price,String unit) {
//		String priceStr = String.valueOf(price);
//		SpannableStringBuilder word = new SpannableStringBuilder(price+unit);
//	    word.setSpan(new StyleSpan(Typeface.BOLD), 0, priceStr.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//		word.setSpan(new AbsoluteSizeSpan(ScreenUtil.Dp2Px(ctx,12)), priceStr.length(), word.length(),Spannable.SPAN_INCLUSIVE_INCLUSIVE);
//		return word;
//	}
//
//	public static SpannableStringBuilder setAvgPriceSpan(Context ctx,
//			String price, String unit) {
//		SpannableStringBuilder word = new SpannableStringBuilder(price+unit);
//		word.setSpan(new StyleSpan(Typeface.BOLD), 0, price.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//		word.setSpan(new AbsoluteSizeSpan(ScreenUtil.Dp2Px(ctx,12)), price.length(), word.length(),Spannable.SPAN_INCLUSIVE_INCLUSIVE);
//		return word;
//	}
}
