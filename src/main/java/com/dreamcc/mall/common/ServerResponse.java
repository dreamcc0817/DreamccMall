package com.dreamcc.mall.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @Title: DreamccMall
 * @Package: com.dreamcc.mall.common
 * @Description:
 * @Author: dreamcc
 * @Date: 2018/8/11 11:23
 * @Version: V1.0
 */
@Slf4j
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
public class ServerResponse<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private int status;
	private String msg;
	private T data;

	private ServerResponse(int status) {
		this.status = status;
	}

	private ServerResponse(int status, T data) {
		this.status = status;
		this.data = data;
	}

	private ServerResponse(int status, String msg, T data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	private ServerResponse(int status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	@JsonIgnore
	public boolean isSuccess() {
		return this.status == ResponseCode.SUCCESS.getCode();
	}

	public int getStatus() {
		return status;
	}

	public T getData() {
		return data;
	}

	public String getMsg() {
		return msg;
	}


	public static <T> ServerResponse<T> createBySuccess() {
		log.info(ResponseCode.SUCCESS.getCode()+"");
		return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
	}

	public static <T> ServerResponse<T> createBySuccessMessage(String msg) {
		log.info(ResponseCode.SUCCESS.getCode()+"",msg);
		return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), msg);
	}

	public static <T> ServerResponse<T> createBySuccess(T data) {
		log.info(ResponseCode.SUCCESS.getCode()+" ",data);
		return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), data);
	}

	public static <T> ServerResponse<T> createBySuccess(String msg, T data) {
		log.info(msg,data);
		return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), msg, data);
	}


	public static <T> ServerResponse<T> createByError() {
		log.error(ResponseCode.ERROR.getCode()+"", ResponseCode.ERROR.getDesc());
		return new ServerResponse<T>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
	}


	public static <T> ServerResponse<T> createByErrorMessage(String errorMessage) {
		log.error(errorMessage);
		return new ServerResponse<T>(ResponseCode.ERROR.getCode(), errorMessage);
	}

	public static <T> ServerResponse<T> createByErrorCodeMessage(int errorCode, String errorMessage) {
		log.error(errorCode+"",errorMessage);
		return new ServerResponse<T>(errorCode, errorMessage);
	}
}
