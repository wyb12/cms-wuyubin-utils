package com.wuyubin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 吴宇斌
 *
 * 2019年11月9日
 */
public class FileUtils {
	/**
	 * 
	 * @Title: readFile  
	 * 文件切割读取
	 * @param @param fileName
	 * @param @return
	 * @param @throws IOException     
	 * @return List<String>    
	 * @throws
	 */
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
	/**
	 * 返回文件以指定单位大小表示
	 * @Title: fileSize  
	 * @Description: TODO  
	 * @param      
	 * @return void    
	 * @throws
	 */
	public static void fileSize(){

		File file=new File("");

		if (file.exists() && file.isFile()) {

			String fileName = file.getName();

			System.out.println("文件"+fileName+"的大小是："+file.length());

		}
	}
	/**
	 * 
	 * @Title: ReadFileByBytes  
	 * 按字节读取文件
	 * @param @param filename     
	 * @return void    
	 * @throws
	 */
	public static void ReadFileByBytes(String filename) {

		File file = new File(filename);

		InputStream is = null;

		try {

			is = new FileInputStream(file);

			int index = 0;

			while (-1 != (index = is.read())) {

				System.out.write(index);

			}

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		} finally {

			try {

				if (null != is)

					is.close();

			} catch (IOException e) {

				e.printStackTrace();

			}

		}

		System.out.println("-----------------------------------");

		try {

			is = new FileInputStream(file);

			byte[] tempbyte = new byte[1000];

			int index = 0;

			while (-1 != (index = is.read(tempbyte))) {

				System.out.write(tempbyte, 0, index);

			}

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		} finally {

			try {

				if (null != is)

					is.close();

			} catch (IOException e) {

				e.printStackTrace();

			}

		}

	}
	/**
	 * 
	 * @Title: ReadFileByChar  
	 * 按字符读取文件 
	 * @param @param filename     
	 * @return void    
	 * @throws
	 */
	public static void ReadFileByChar(String filename) {

		File file = new File(filename);

		InputStream is = null;

		Reader isr = null;

		try {

			is = new FileInputStream(file);

			isr = new InputStreamReader(is);

			int index = 0;

			while (-1 != (index = isr.read())) {

				System.out.print((char) index);

			}

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (null != is)

					is.close();

				if (null != isr)

					isr.close();

			} catch (IOException e) {

				e.printStackTrace();

			}

		}

	}
	/**
	 * 
	 * @Title: Write2FileByBuffered  
	 * 写入文本文件  
	 * @param @param filename     
	 * @return void    
	 * @throws
	 */
	public static void Write2FileByBuffered(String filename) {

		File file = new File(filename);

		FileOutputStream fos = null;

		OutputStreamWriter osw = null;

		BufferedWriter bw = null;

		try {

			if (!file.exists()) {

				file.createNewFile();

			}

			fos = new FileOutputStream(file);

			osw = new OutputStreamWriter(fos);

			bw = new BufferedWriter(osw);

			bw.write("Write2FileByBuffered");

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			if (null != bw) {

				try {

					bw.close();

				} catch (IOException e) {

					e.printStackTrace();

				}

			}

			if (null != osw) {

				try {

					osw.close();

				} catch (IOException e) {

					e.printStackTrace();

				}

			}

			if (null != fos) {

				try {

					fos.close();

				} catch (IOException e) {

					e.printStackTrace();

				}

			}

		}

	}
	/**
	 * 
	 * @Title: Write2FileByFileWriter  
	 * 通过FileWriter写文件 
	 * @param @param filename     
	 * @return void    
	 * @throws
	 */
	public static void Write2FileByFileWriter(String filename) {

		File file = new File(filename);

		FileWriter fw = null;

		try {

			if (!file.exists()) {

				file.createNewFile();

			}

			fw = new FileWriter(file);

			fw.write("Write2FileByFileWriter");

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			if (null != fw) {

				try {

					fw.close();

				} catch (IOException e) {

					e.printStackTrace();

				}

			}

		}

	}
	/**
	 * 
	* @Title: download  
	* 网络文件下载  
	* @param @param realPath
	* @param @param request
	* @param @param response
	* @param @param filename
	* @param @throws FileNotFoundException     
	* @return void    
	* @throws
	 */
	public static void download(String realPath,HttpServletRequest request,HttpServletResponse response,String filename) throws FileNotFoundException {

		/* // 下载本地文件

		String fileName = "Operator.doc".toString(); // 文件的默认保存名

		 */ // 读到流中

		InputStream inStream = new FileInputStream(realPath+filename);// 文件的存放路径

		// 设置输出的格式

		response.reset();

		response.setContentType("bin");

		response.addHeader("Content-Disposition", "attachment; filename=\"" + filename

				+"\"");
		// 循环取出流中的数据

		byte[] b = new byte[1024];

		int len;

		try {

			while ((len = inStream.read(b)) > 0)

				response.getOutputStream().write(b, 0, len);

			inStream.close();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

}
