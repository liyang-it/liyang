package com.liyang.controller;

import com.liyang.tools.saveUrlFIle;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

@Controller
@CrossOrigin
@RequestMapping("/file")
public class fileController {
  public void outNotFoundResource(HttpServletResponse response, String fileName) {
    try {
      String str = "<h1>访问的('"+fileName+"')资源不存在!</h1><br><h2>Not Found "+fileName+"</h2>"; // 向客户端输出的信息
      OutputStream out = response.getOutputStream();
      response.setCharacterEncoding("utf-8");
      response.setHeader("content-type","text/html;charset=utf-8"); //想客户端输出为 html 格式以及编码格式
      byte bytes[] = str.getBytes();
      out.write(bytes);
      out.flush();
      out.close();
    }catch(IOException e){
    }
  }
//  @RequestMapping(value = "/pic/{fileName}")
//  public void getPic(@PathVariable(value = "fileName") String fileName, HttpServletResponse response){
//    try {
//      String fileType = fileName.substring(fileName.indexOf(".")+1);
//      OutputStream out  = response.getOutputStream();
//      File file = new File(constant.PIC_PATH + fileName);
//      if(!file.exists()){
//        outNotFoundResource(response,fileName);
//      }else{
//        long startTime = System.currentTimeMillis();
////            response.setHeader("content-disposition","attachment;filename="+fileName); // 设置下载名称
//////            response.setHeader("Pragma", "No-cache");
//////            response.setHeader("Cache-Control", "no-cache");
//////            response.setDateHeader("Expires", 0L);
//        response.setHeader("Cache-Control","max-age=31104000");
//        if(fileType.equals("pdf")){
//          response.setContentType("application/pdf"); //输出PDF
//          log.info("输出PDF");
//        }else{
//          response.setContentType("image/png"); //输出图片 如果设置 image/* 则变成下载
//          log.info("输出图片");
//        }
//        InputStream input = new FileInputStream(file); // 得到文件字节输入
////                byte[] bytes = new byte[3064]; // 使用字节持续读取输出 文件
////                while ((input.read(bytes)!= -1)){
////                    out.write(bytes);
////                }
//        ByteArrayOutputStream bos = new ByteArrayOutputStream(input.available()); // 字节数组輸出 文件
//        byte[] bytes = new byte[input.available()]; //创建字节数组
//        input.read(bytes);
//        bos.write(bytes);
//        out.write(bos.toByteArray());
//        out.flush();
//        out.close();
//        input.close();
//        long stopTime = System.currentTimeMillis();
//        log.info("输出图片耗时:[{}]豪秒",(stopTime - startTime));
//      }
//    }catch (Exception e){
//      e.printStackTrace();
//    }
//  }

  // 断点续传方式 读取视频
  @RequestMapping("/getTempVideo/{fileName}")
  public void outVideo(@PathVariable(value = "fileName") String fileName, HttpServletResponse response, HttpServletRequest request){
    try {
      OutputStream out = response.getOutputStream();
      File file = new File(saveUrlFIle.path + fileName);

      if(!file.exists()){
        outNotFoundResource(response,fileName);
      }else{
        long fileLength = file.length();
        RandomAccessFile randomFile = new RandomAccessFile(file, "r");//只读模式
        long contentLength = randomFile.length();
        String range = request.getHeader("Range");
        int start = 0, end = 0;
        if(range != null && range.startsWith("bytes=")){
          String[] values = range.split("=")[1].split("-");
          start = Integer.parseInt(values[0]);
          if(values.length > 1){
            end = Integer.parseInt(values[1]);
          }
        }
        int requestSize = 0;
        if(end != 0 && end > start){
          requestSize = end - start + 1;
        } else {
          requestSize = Integer.MAX_VALUE;
        }

        response.setContentType("video/mp4");
        response.setHeader("Accept-Ranges", "bytes");
        response.setHeader("ETag", fileName);
        response.setHeader("Last-Modified", new Date().toString());
        //第一次请求只返回content length来让客户端请求多次实际数据
        if(range == null){
          response.setHeader("Content-length", contentLength + "");
        }else{
          //以后的多次以断点续传的方式来返回视频数据
          response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);//206
          long requestStart = 0, requestEnd = 0;
          String[] ranges = range.split("=");
          if(ranges.length > 1){
            String[] rangeDatas = ranges[1].split("-");
            requestStart = Integer.parseInt(rangeDatas[0]);
            if(rangeDatas.length > 1){
              requestEnd = Integer.parseInt(rangeDatas[1]);
            }
          }
          long length = 0;
          if(requestEnd > 0){
            length = requestEnd - requestStart + 1;
            response.setHeader("Content-length", "" + length);
            response.setHeader("Content-Range", "bytes " + requestStart + "-" + requestEnd + "/" + contentLength);
          }else{
            length = contentLength - requestStart;
            response.setHeader("Content-length", "" + length);
            response.setHeader("Content-Range", "bytes "+ requestStart + "-" + (contentLength - 1) + "/" + contentLength);
          }
        }
        ServletOutputStream outs = response.getOutputStream();
        int needSize = requestSize;
        randomFile.seek(start);
        while(needSize > 0){
          byte[] buffer = new byte[4096];
          int len = randomFile.read(buffer);
          if(needSize < buffer.length){
            outs.write(buffer, 0, needSize);
          } else {
            outs.write(buffer, 0, len);
            if(len < buffer.length){
              break;
            }
          }
          needSize -= buffer.length;
        }
        randomFile.close();
        out.close();
      }
    }catch(Exception e){

    }
  }
}

