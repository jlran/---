package com.github.jlran.utils;

import java.util.UUID;

public class UUIDUtils {
	public static String getUUID(){
		return  UUID.randomUUID().toString().replace("-", "");
	}
}
