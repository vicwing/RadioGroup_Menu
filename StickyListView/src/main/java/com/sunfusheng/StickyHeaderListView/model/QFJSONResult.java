package com.sunfusheng.StickyHeaderListView.model;

import java.io.Serializable;

/**
 * @ClassName: JSONResult 
 * @Description: 请求结果抽象类
 * @author: Administrator
 * @date:2013-12-2 上午9:35:51
 * @param <T> 
 */ 
public class QFJSONResult<T> implements Serializable {

	public QFJSONResult() {
		// TODO Auto-generated constructor stub
	}

	String message;
	String status;
	private T result;

	/** 
	 * @return result 
	 */
	public T getResult() {
		return result;
	}
	
	/**   
	 * @param result the result to set   
	 */
	public void setResult(T result) {
		this.result = result;
	}
	
	/** 
	 * @return message 
	 */
	public String getMessage() {
		return message;
	}
	/**   
	 * @param message the message to set   
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/** 
	 * @return status 
	 */
	public String getStatus() {
		return status;
	}
	
	/**   
	 * @param status the status to set   
	 */
	public void setStatus(String status) {
		this.status = status;
	} 
	
}
