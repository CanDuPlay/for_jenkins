package com.zklcsoftware.common.web.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class DownUtil {

	public static void downloadCollect(HttpServletRequest request, HttpServletResponse response, String fileName, String path) {
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
//            String filename = file.getName();
            // 取得文件的后缀名。
//            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            String filedisplay =  fileName;
            response.addHeader("Content-Disposition", "attachment;filename="+ filedisplay);
            if (request.getHeader("USER-AGENT").toLowerCase().indexOf("firefox") > 0 ? false : true) {
                filedisplay = "attachment;filename="  + URLEncoder.encode(filedisplay, "UTF-8");
            } else {
                filedisplay = "attachment;filename=" + new String(filedisplay.getBytes(), "ISO-8859-1");
            }
            response.setContentType("application/x-msdownload;charset=UTF-8");
            response.setHeader("Content-Disposition", filedisplay);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
	
	public static void download(String path, HttpServletResponse response, String filename) {
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
//            String filename = file.getName();
            // 取得文件的后缀名。
//            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

	public static void downVideoList(HttpServletRequest request,
			HttpServletResponse response, List<String> fileUrlList) {
		response.reset();// 清空输出流
		OutputStream out = null;
		File zip = new File(getRealPath(request) + "/" + getFileName() + ".zip");// 压缩文件
		List<String> fileNames = new ArrayList<String>();// 用于存放生成的文件名称
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			String filedisplay = getFileName() + ".zip";
			response.addHeader("Content-Disposition", "attachment;filename="
					+ filedisplay);
			if (request.getHeader("USER-AGENT").toLowerCase()
					.indexOf("firefox") > 0 ? false : true) {
				filedisplay = "attachment;filename="
						+ URLEncoder.encode(filedisplay, "UTF-8");
			} else {
				filedisplay = "attachment;filename="
						+ new String(filedisplay.getBytes(), "ISO-8859-1");
			}
			response.setContentType("application/x-msdownload;charset=UTF-8");
			response.setHeader("Content-Disposition", filedisplay);
			response.addHeader("Pargam", "no-cache");
			response.addHeader("Cache-Control", "no-cache");
			for (int i = 0; i < fileUrlList.size(); i++) {
				String string = fileUrlList.get(i);
				String fileName = string.substring(string.lastIndexOf("/") + 1,
						string.length());
				String file = getRealPath(request) + "/" + getFileName() + fileName;
				fileNames.add(file);
				FileOutputStream o = new FileOutputStream(file);
				URL url = new URL(fileUrlList.get(i));
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setConnectTimeout(5 * 1000);
				InputStream inStream = conn.getInputStream();// 通过输入流获取数据
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = inStream.read(buffer)) != -1) {
					o.write(buffer, 0, len);
				}
				inStream.close();
				o.close();
			}

			// 循环结束
			out = response.getOutputStream();

			File[] srcfile = new File[fileNames.size()];
			for (int i = 0, n1 = fileNames.size(); i < n1; i++) {
				srcfile[i] = new File(fileNames.get(i));
			}
			ZipFiles(srcfile, zip);
			FileInputStream inStream = new FileInputStream(zip);
			byte[] buf = new byte[4096];
			int readLength;
			while (((readLength = inStream.read(buf)) != -1)) {
				out.write(buf, 0, readLength);
			}
			inStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void downVideo(HttpServletRequest request,
			HttpServletResponse response, String fileUrl) {
		response.reset();// 清空输出流
		OutputStream out = null;
		// 截取文件名
		String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1,
				fileUrl.length());
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			String filedisplay = format.format(date) + fileName;
			response.addHeader("Content-Disposition", "attachment;filename="
					+ filedisplay);
			if (request.getHeader("USER-AGENT").toLowerCase()
					.indexOf("firefox") > 0 ? false : true) {
				filedisplay = "attachment;filename="
						+ URLEncoder.encode(filedisplay, "UTF-8");
			} else {
				filedisplay = "attachment;filename="
						+ new String(filedisplay.getBytes(), "ISO-8859-1");
			}
			response.setContentType("application/x-msdownload;charset=UTF-8");
			response.setHeader("Content-Disposition", filedisplay);
			response.addHeader("Pargam", "no-cache");
			response.addHeader("Cache-Control", "no-cache");
			URL url = new URL(fileUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5 * 1000);
			InputStream inStream = conn.getInputStream();// 通过输入流获取数据
			out = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = inStream.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			inStream.close();
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 获取文件名字
	public static String getFileName() {
		// 文件名获取
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String f = format.format(date);
		return f;
	}

	// 获取项目的绝对地址
	public static String getRealPath(HttpServletRequest request) {
		String realPath = request.getSession().getServletContext()
				.getRealPath("/");
		// System.out.println(realPath);
		return realPath;
	}

	// 压缩文件
	public static void ZipFiles(File[] srcfile, File zipfile) {
		byte[] buf = new byte[1024];
		try {
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
					zipfile));
			for (int i = 0; i < srcfile.length; i++) {
				FileInputStream in = new FileInputStream(srcfile[i]);
				out.putNextEntry(new ZipEntry(srcfile[i].getName()));
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				out.closeEntry();
				in.close();
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
