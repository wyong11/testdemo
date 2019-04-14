package com.sy.giteaapi;

import java.io.BufferedWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;

import org.textmining.text.extraction.WordExtractor;

/**
 * 测试将指定目录下的doc转换为txt
 * */
public class GetFiles {
    public static void main(String[] args) {
        String src = "C:/Users/wyong/Desktop/copy";
        String dest = "C:/Users/wyong/Desktop/copy";
        wordToTxt(src, dest);
    }

    public static void wordToTxt(String src, String dest) {
        File dir = new File(src);
        File[] files = dir.listFiles();
        int i = 0;
        for (File file : files) { // 遍历指定目录
            try {
                toTxt(file, dest);
                i++;
            } catch (Exception e) {
                System.out
                        .println("文件" + file.getAbsolutePath() + "转化为txt文件失败");
            }
        }
        System.out.println("成功转化了 " + i + " 个文件");
    }

    private static void toTxt(File file, String dest) throws Exception {
        WordExtractor extractor = new WordExtractor(); // 利用WordExtractor解析Word文档—读取Word文档中的文本
        try {
            String content = extractor.extractText(new FileInputStream(file));
            // 用于测试当前正在转换哪个文档
            System.out.println(file.getName() + "....." + content.length());
            if (content.length() > 1000) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(dest
                        + "/" + file.getName().replaceAll("\\.doc$", ".txt")));
                writer.write(content);
                writer.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(file.getName() + "出现问题");
            throw e;
        }
    }
}
