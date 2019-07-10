package com.muke.miaosha.result;

import java.io.Serializable;

public class Result<T> implements Serializable {
	private int code;
	private String msg;
	private T data;

	private Result(T data) {
		this.code = 0;
		this.msg = "success";
		this.data = data;
	}

	private Result(CodeMsg cm) {
		if(cm == null) {
			return;
		}
		this.code = cm.getCode();
		this.msg = cm.getMsg();
	}

	/**
	 * 成功时候的调用
	 * 	返回Result
	 * 	用data构造
	 */
	public static <T> Result<T> success(T data){
		return new  Result<T>(data);
	}
	
	/**
	 * 失败时候的调用
	 * 用CodeMsg做参数构造Result
	 */
	public static <T> Result<T> error(CodeMsg cm){
		return new  Result<T>(cm);
	}


	public int getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
	public T getData() {
		return data;
	}
}
