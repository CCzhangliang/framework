package com.zl.framework.util;

import org.apache.commons.lang.StringUtils;

public class PsUtil {

	public static String convertToNull(String str) {
		if (StringUtils.isBlank(str)) {
			return null;
		}
		return StringUtils.trim(str);
	}
	
	public static boolean isNullOrBlank(String str) {
		if (StringUtils.isBlank(str)||str==null||"null".equalsIgnoreCase(str)) {
			return true;
		}
		return false;
	}
	
	public static String formatNulltoBlank(String str) {
		if (StringUtils.isBlank(str)||str==null||"null".equalsIgnoreCase(str)) {
			return "";
		}
		return str;
	}
	
	public static String getIsStarted(Object flg){
		if(flg ==null){
			return "false";
		}else if("true".equals(flg.toString())){
			return "true";
		}else if("false".equals(flg.toString())){
			return "false";
		}
		return "false";
	}
}
