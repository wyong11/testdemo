package com.sy.giteaapi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class changeFiles
{
 /**复制文件
  * 
  * @author chen_weixian
  * Mar 11, 2012   11:33:19 PM
  * @param path 需要复制文件的路径
  * @param savePath 文件保存路径（复制到的路径）
  * @throws Exception
  */
 public void change2Image(String path, String savePath) throws Exception
 {
  File file = new File(path);
  if (!file.exists())
  {
   System.out.println("文件不存在！");
   return ;
  }
  // 复制到的路径如不存在就创建
  File saveFile = new File(savePath);
  if (!saveFile.exists())
  {
   saveFile.mkdirs();
  }
  // 新文件全路径
  String savePathNew = "";
  for (File fbean : file.listFiles())
  {
   if (fbean.isFile())
   {
    System.out.println(fbean.getName() + "\t" + fbean.getAbsolutePath());
//    savePathNew = savePath + File.separator + fbean.getName()+ ".jpg";
    // 把文件名称中含有.tbi格式的转化为.jpg格式  
    savePathNew = savePath + File.separator + (fbean.getName().replaceAll(".docx", ".txt"));
    // 开始复制 
             copy(fbean ,new File(savePathNew));      
   }
  }
 }

 /**拷贝文件
  * 
  * @author chen_weixian
  * Mar 11, 2012   11:31:59 PM
  * @param fromFile
  * @param toFile
  * @throws Exception
  */
 private static void copy(File fromFile, File toFile) throws Exception{  
  if (!fromFile.exists())
  {
   System.out.println("来源文件为空！");
  }
  if (!toFile.exists())
  {
   System.out.println("创建新文件。。");
   toFile.createNewFile();
  }
  FileInputStream  fis = new FileInputStream(fromFile);
        System.out.println("fromFile :" + fromFile.getAbsolutePath());
        FileOutputStream fos = new FileOutputStream(toFile);
        System.out.println("toFile :" + toFile.getAbsolutePath());

        int len = 0;  
        byte[] buf = new byte[1024];  
        while((len = fis.read(buf)) != -1){  
         fos.write(buf,0,len);  
        }

        fis.close();  
        fos.close(); 

    } 


 /** 测试
  * @author chen_weixian
  * Mar 11, 2012   10:19:56 PM
  * @param args
  */
 public static void main(String[] args)
 {
//  String path = "E:/temp";
  String path = "C:/Users/wyong/Desktop/第三次作业/11";
  String savePath = "C:/Users/wyong/Desktop/copy";
  changeFiles change2Image = new changeFiles();
  try
  {
   change2Image.change2Image(path, savePath);
  }
  catch (Exception e)
  {
   e.printStackTrace();
  }
  System.out.println("完成");
 }

}