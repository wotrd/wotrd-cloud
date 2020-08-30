package com.wotrd.dubbo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

@Slf4j
@RequestMapping("ueditor")
@RestController
public class UEditorController {

    @Value("${ueditor_img_upload_dir}")
    private String imgUploadDir;

    /**
     * @Description 获取config.json配置文件
     */
    @RequestMapping("getConfig")
    public void getConfig(HttpServletResponse response) {
        Resource res = new ClassPathResource("config.json");
        InputStream is = null;
        response.setHeader("Content-Type", "text/html");
        try {
            is = new FileInputStream(res.getFile());
            StringBuffer sb = new StringBuffer();
            byte[] b = new byte[1024];
            int length;
            while (-1 != (length = is.read(b))) {
                sb.append(new String(b, 0, length, "utf-8"));
            }
            String result = sb.toString().replaceAll("/\\*(.|[\\r\\n])*?\\*/", "");
            JSONObject json = JSON.parseObject(result);
            PrintWriter out = response.getWriter();
            out.print(json.toString());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    /**
     * 富文本上传的文件地址
     *
     * @param files
     */
    @PostMapping("upload")
    public void upload(@RequestParam("upfile") MultipartFile[] files, HttpServletResponse response) throws IOException {
        FileOutputStream outputStream = null;
        String fileName = null;
        try {
            MultipartFile file = files[0];
            fileName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            fileName = UUID.randomUUID().toString() + fileName;
            outputStream = new FileOutputStream(imgUploadDir + fileName);
            try {
                outputStream.write(file.getBytes());
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        } catch (FileNotFoundException e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
        JSONObject jsobject = new JSONObject();
        jsobject.put("state", "SUCCESS");
        jsobject.put("url", fileName);
        jsobject.put("title", "");
        jsobject.put("original", "");

        response.getOutputStream().write(jsobject.toString().getBytes());

    }
}
