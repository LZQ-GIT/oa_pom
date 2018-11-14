package com.qf.oa.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

/**
 * @Authoer lzq
 * @DateTime 2018/11/2  12:49
 * @Version 1.0
 */
@Controller
@RequestMapping("imgController")
@ResponseBody
public class ImgController {
    @RequestMapping("upload")
    public String upload(MultipartFile file){
        InputStream in = null;
        OutputStream out = null;
        String path = null;
        try {
            in = file.getInputStream();
            path = "G:/images/"+ UUID.randomUUID();
            out = new FileOutputStream(path);
            IOUtils.copy(in,out);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        String jsonPath = "{\"path\":\""+path+"\"}";
        return jsonPath;
    }

    /**
     * 将浏览器要访问本地磁盘的图片通过服务器响应给浏览器
     * @param path
     * @param response
     */
    @RequestMapping("getPath")
    public void getPath(String path, HttpServletResponse response){
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(path);
            out = response.getOutputStream();
            IOUtils.copy(in,out);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
