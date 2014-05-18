package com.socket.server.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;

public class StringUtil {
	
	public static final String EMPTY = "";
	
	public static boolean isEmpty(String str) {
		boolean result = true;
		
		if (str != null) {
			result = str.trim().isEmpty();
		}
		
		return result;
	}
	
	public static String[] getProcessArray(String processStr) {
		if (!isEmpty(processStr)) {
			return processStr.replaceAll(ProcessStatus.START_SYMBOL, "").split(ProcessStatus.END_SYMBOL_REGEX);
		}
		return new String[0];
	}
	
	public static List<String> getProcessList(String processStr) {
		 return Arrays.asList(getProcessArray(processStr));
	}
	
	public static boolean isEquals(String str1, String str2) {
		if (str1 == str2) return true;
		
		if ((str1 != null && str1.equals(str2)) ||
				(str2 != null && str2.equals(str1))) {
			return true;
		}
		return false;
	}
	
	public static String makeMD5(String password) {   
		MessageDigest md;   
	   try {   
		    // 生成一个MD5加密计算摘要   
		    md = MessageDigest.getInstance("MD5");   
		    // 计算md5函数   
		    md.update(password.getBytes());   
		    // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符   
		    // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值   
		    String pwd = new BigInteger(1, md.digest()).toString(16);   
		    System.err.println(pwd);   
		    return pwd;   
	   } catch (Exception e) {   
		   e.printStackTrace();   
	   }   
	   return password;   
	}

}
