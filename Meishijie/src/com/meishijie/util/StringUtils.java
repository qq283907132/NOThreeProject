package com.meishijie.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class StringUtils {
	
	public static String decodeMeishiString(int paramInt, String paramString) {
		int i = paramInt % 6;
		String str1 = paramString;
		if (i == 0)
			str1 = str1.replace("@", "4").replace("$", "E").replace("!", "B")
					.replace("&", "C");
		try {
			while (true) {
				if (i == 1){
					str1 = str1.replace("^", "5").replace("(", "A")
							.replace("@", "1").replace("&", "D");
				}
				else if (i == 2){
					str1 = str1.replace("#", "8").replace(")", "C")
							.replace("@", "%").replace("&", "E");
				}
				else if (i == 3){
					str1 = str1.replace("*", "E").replace("@", "D")
							.replace("#", "C").replace("^", "A");
				}
				else if (i == 4){
					str1 = str1.replace("@", "D").replace("$", "%")
							.replace("&", "E").replace("!", "B");
				}
				else if (i == 5){
					str1 = str1.replace("&", "0").replace("!", "9")
							.replace("@", "7").replace("*", "8");
				}
				String str2 = URLDecoder.decode(str1, "UTF-8");
				return str2;
			}
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
			localUnsupportedEncodingException.printStackTrace();
			return str1;
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return str1;
	}
	
	
	
	public static String encodeMeishiString(int paramInt, String paramString) {
		int i = paramInt % 6;
		String str1 = null;
		try {
			
			str1 = URLEncoder.encode(paramString, "UTF-8");
			
			if (i == 0)
				str1 = str1.replace("4", "@").replace("E", "$").replace("B", "!")
						.replace("C", "&");
			
			while (true) {
				if (i == 1){
					str1 = str1.replace("5", "^").replace("A", "(")
							.replace("1", "@").replace("D", "&");
				}
				else if (i == 2){
					str1 = str1.replace("8", "#").replace("C", ")")
							.replace("%", "@").replace("E", "&");
				}
				else if (i == 3){
					str1 = str1.replace("E", "*").replace("D", "@")
							.replace("C", "#").replace("A", "^");
				}
				else if (i == 4){
					str1 = str1.replace("D", "@").replace("%", "$")
							.replace("E", "&").replace("B", "!");
				}
				else if (i == 5){
					str1 = str1.replace("0", "&").replace("9", "!")
							.replace("7", "@").replace("8", "*");
				}
				break;
			}
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return str1;
	}
}
