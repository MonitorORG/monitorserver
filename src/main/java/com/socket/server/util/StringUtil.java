package com.socket.server.util;

import java.util.Arrays;
import java.util.List;

public class StringUtil {
	
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

}
