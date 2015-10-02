package com.sns.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by ElNino on 15/9/8.
 */
@Controller
@RequestMapping("/file")
public class uploadController {
    @RequestMapping("/upload")
    @ResponseBody
    public Map upload(
            @RequestParam(value = "file", required = true) MultipartFile file,
            HttpServletRequest request
    ) {
            System.out.println("进入上传文件");
            String fname = file.getOriginalFilename();
            System.out.println("文件名"+fname);
            String path = request.getSession().getServletContext().getRealPath("image");
            Date date = new Date();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");
            String fileName = sdf.format(date)+".jpg";
            File targetFile = new File(path, fileName);
            if(!targetFile.exists()){
                targetFile.mkdirs();
            }
             try {
                file.transferTo(targetFile);
            } catch (Exception e) {
                 e.printStackTrace();
            }

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("code",0);
            map.put("filenmae","/sns/image/"+fileName);
            map.put("fname",fname);
            return map;

    }
}
