package com.wotrd.dubbo.common.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用javacv截取视频的缩略图
 */
public class Thumbnails {
    public static void main(String []args){
        try {
            saveVideoThumbnail("/home/wotrd/a.mp4","/home/wotrd/test.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void saveVideoThumbnail(String videoPath, String imagePath) throws IOException {
        //ffmpeg -i xxx.mp4 -y -f image2 -t 0.001 -s 125x125 xxx.jpg
        List cmd = new ArrayList();
        cmd.add("ffmpeg");// 视频提取工具的位置
        cmd.add("-i");
        cmd.add(videoPath);
        cmd.add("-y");
        cmd.add("-f");
        cmd.add("image2");
        cmd.add("-t");
        cmd.add("0.001");
        cmd.add("-s");
        cmd.add("125x125");
        cmd.add(imagePath);
        ProcessBuilder builder = new ProcessBuilder();
        builder.command(cmd);
        builder.start();
    }

}
