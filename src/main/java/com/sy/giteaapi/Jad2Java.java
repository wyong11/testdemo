package com.sy.giteaapi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.io.OutputStream;
 
import javax.swing.text.DefaultEditorKit.CopyAction;
 
public class Jad2Java {
 
	public static void main(String[] args) throws Exception {
		File srcDir = new File("C:/Users/wyong/Desktop/copy2/12");
		if(!(srcDir.exists() && srcDir.isDirectory())) {
			throw new Exception("目录不存在");
		}
		File[] files = srcDir.listFiles(
		   new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
			
				return name.endsWith(".docx");
			}
		   }
		);
       System.out.println(files.length);		
	   File destDir = new File("C:/Users/wyong/Desktop/copy");
	   if(!destDir.exists()) {
		   destDir.mkdir();
	   }
	   for(File f: files) {
		   FileInputStream fileInputStream = new FileInputStream(f);
		   String destFileName  = f.getParent().replaceAll("\\.docx$", ".doc");
		   FileOutputStream fileOutputStream = new FileOutputStream(new File(destDir, destFileName));
		   copy(fileInputStream, fileOutputStream);
		   fileInputStream.close();
		   fileOutputStream.close();
	   }	
	}
	
	private static void copy(InputStream ips, OutputStream ops) throws Exception {
		int len = 0;
		byte[] buf = new byte[1024];
		while((len = ips.read(buf)) != -1) {
			ops.write(buf, 0, len);
		}
	}
	
}