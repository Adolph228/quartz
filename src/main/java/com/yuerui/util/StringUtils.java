package com.yuerui.util;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




public class StringUtils extends org.apache.commons.lang3.StringUtils {
	private static final String CHARSET_NAME = "UTF-8";
	/**
	 * 转换为字节数组
	 * 
	 * @param str
	 * @return
	 */
	public static byte[] getBytes(String str) {
		if (str != null) {
			try {
				return str.getBytes(CHARSET_NAME);
			} catch (UnsupportedEncodingException e) {
				return null;
			}
		} else {
			return null;
		}
	}
	

	public static boolean isIdIsNull(Long id){
		if(id == null || id.equals(0L)){
			return true;
		}else{
			return false;
		}
	}
	

	public static String getStrByLength(String str,int length){
		if(isBlank(str)){
			return "";
		}
		str=str.trim();
		if(str.length()<=length){
			return str;
		}
		return str.substring(0, length)+"……";
	}
	
	public static boolean isLong(String str){

		if (str == null) {
			return false;
		}
		
		try {
			Long.valueOf(str.trim());
		} catch (Exception e) {
			return false;
		}
		
		
		return true; 
		
	
	}
	
	public static boolean isInteger(String str){

		if (str == null) {
			return false;
		}
		
		try {
			Integer.valueOf(str.trim());
		} catch (Exception e) {
			return false;
		}
		return true; 
		
	
	}

	/**
	 * 字符串是否数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		if (str == null) {
			return false;
		}
		
		try {
			Double.valueOf(str.trim());
		} catch (Exception e) {
			return false;
		}
		
		
		return true; 
		
	}

	/**
	 * 转换为字节数组
	 * 
	 * @param str
	 * @return
	 */
	public static String toString(byte[] bytes) {
		try {
			return new String(bytes, CHARSET_NAME);
		} catch (UnsupportedEncodingException e) {
			return EMPTY;
		}
	}

	/**
	 * 是否包含字符串
	 * 
	 * @param str
	 *            验证字符串
	 * @param strs
	 *            字符串组
	 * @return 包含返回true
	 */
	public static boolean inString(String str, String... strs) {
		if (str != null) {
			for (String s : strs) {
				if (str.equals(trim(s))) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 替换掉HTML标签方法
	 */
	public static String replaceHtml(String html) {
		if (isBlank(html)) {
			return "";
		}
		String regEx = "<.+?>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(html);
		String s = m.replaceAll("");
		return s;
	}

	/**
	 * 替换为手机识别的HTML，去掉样式及属性，保留回车。
	 * 
	 * @param html
	 * @return
	 */
	public static String replaceMobileHtml(String html) {
		if (html == null) {
			return "";
		}
		return html.replaceAll("<([a-z]+?)\\s+?.*?>", "<$1>");
	}
	
}
