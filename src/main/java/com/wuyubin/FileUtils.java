package com.wuyubin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴宇斌
 *
 * 2019年11月9日
 */
public class FileUtils {
	public static List<String> readFile(String fileName) throws IOException {
		//创建一个集合存入解析的字符串
		List<String> lines = new ArrayList<String>();
		//创建文件
		File file = new File(fileName);
		//读取字符串为UTF-8
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
		
		String str = null;
		while((str = reader.readLine()) != null) {
			lines.add(str);
		}
		return lines;
	}	
}
