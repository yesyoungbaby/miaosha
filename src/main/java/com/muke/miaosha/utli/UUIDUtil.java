package com.muke.miaosha.utli;

import java.util.UUID;

/**
 * 生成token
 */
public class UUIDUtil {
	public static String uuid() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
