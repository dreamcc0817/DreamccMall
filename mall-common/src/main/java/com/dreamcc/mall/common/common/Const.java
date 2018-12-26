package com.dreamcc.mall.common.common;

/**
 * @Title: DreamccMall
 * @Package: com.dreamcc.mall.common
 * @Description:
 * @Author: dreamcc
 * @Date: 2018/8/11 12:39
 * @Version: V1.0
 */
public class Const {

	public static final String CURRENT_USER = "currentUser";

	public static final String EMAIL = "email";

	public static final String USERNAME = "username";

	public static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static final String COOKIE_DOMAIN = "";

	public static final String COOKIE_NAME = "";


	public enum ProductStatusEnum {
		ON_SALE(1, "on line");
		private String value;
		private int code;

		ProductStatusEnum(int code, String value) {
			this.value = value;
			this.code = code;
		}

		public String getValue() {
			return value;
		}

		public int getCode() {
			return code;
		}
	}

	public interface Role {
		int ROLE_CUSTOMER = 0;
		int ROLE_ADMIN = 1;
	}

	public interface Cart {
		int CHECKED = 1;//shopping cart checked
		int UN_CHECKED = 0;//shopping cart unchecked

		String LIMIT_NUM_FAIL = "LIMIT_NUM_FAIL";
		String LIMIT_NUM_SUCCESS = "LIMIT_NUM_SUCCESS";
	}

	public enum OrderStatusEnum {
		CANCELED(0, "cancel"),
		NO_PAY(10, "no pay"),
		PAID(20, "paid"),
		SHIPPED(40, "delivered"),
		ORDER_SUCCESS(50, "order complete"),
		ORDER_CLOSE(60, "order close");


		OrderStatusEnum(int code, String value) {
			this.code = code;
			this.value = value;
		}

		private String value;
		private int code;

		public String getValue() {
			return value;
		}

		public int getCode() {
			return code;
		}

		public static OrderStatusEnum codeOf(int code) {
			for (OrderStatusEnum orderStatusEnum : values()) {
				if (orderStatusEnum.getCode() == code) {
					return orderStatusEnum;
				}
			}
			throw new RuntimeException("not found enum");
		}
	}

	public enum PayPlatformEnum {
		ALIPAY(1, "支付宝");

		PayPlatformEnum(int code, String value) {
			this.code = code;
			this.value = value;
		}

		private String value;
		private int code;

		public String getValue() {
			return value;
		}

		public int getCode() {
			return code;
		}
	}

	public enum PaymentTypeEnum {
		ONLINE_PAY(1, "pay online");

		PaymentTypeEnum(int code, String value) {
			this.code = code;
			this.value = value;
		}

		private String value;
		private int code;

		public String getValue() {
			return value;
		}

		public int getCode() {
			return code;
		}


		public static PaymentTypeEnum codeOf(int code) {
			for (PaymentTypeEnum paymentTypeEnum : values()) {
				if (paymentTypeEnum.getCode() == code) {
					return paymentTypeEnum;
				}
			}
			throw new RuntimeException("not found enum");
		}
	}
}
