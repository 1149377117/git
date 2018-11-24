package com.Blog_Server.Dao.ly;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

	public static String replaceAll(String string, String regex, String... values) {
		if (string != null && values.length > 0) {
			List<String[]> groups = getGroupList(string, regex);
			for (int i = 0; i < groups.size(); i++) {
				string = string.replaceFirst(regex, values[i % values.length]);
			}
		}
		return string;
	}

	/**
	 * 捕获字符串的中的组
	 * 
	 * @param str
	 * @param regex
	 * @return
	 */
	public static String[] getGroup(String str, String regex) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		if (m.matches()) {
			String[] ret = new String[m.groupCount()];
			for (int i = 1; i <= m.groupCount(); i++) {
				ret[i - 1] = m.group(i);
			}
			return ret;
		} else {
			return new String[0];
		}
	}

	/**
	 * 捕获字符串的中的组(组中有组)
	 * 
	 * @param str
	 * @param regex
	 * @return
	 */
	public static List<String[]> getGroupList(String str, String regex) {
		List<String[]> ret = new ArrayList<String[]>();
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		int index = 0;
		while (m.find(index)) {
			String[] s = new String[m.groupCount()];
			for (int i = 1; i <= m.groupCount(); i++) {
				s[i - 1] = m.group(i);
			}
			ret.add(s);
			index = m.end();
		}
		return ret;
	}

	public static void main(String[] args) {
		String a = "name = ${name} and ${sn}";
		String r = "(\\$\\{([\\w\\.]+)\\})";
		Object o = getGroupList(a, r);
		System.out.println(o);
		System.out.println(getGroup(a, r));
		System.out.println(replaceAll(a, r, "000"));
	}

}
