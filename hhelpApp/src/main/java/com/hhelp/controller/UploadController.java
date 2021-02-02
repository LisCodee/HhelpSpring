package com.hhelp.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hhelp.HhelpApplication;
import com.hhelp.anno.LoginToken;
import com.hhelp.domain.User;
import com.hhelp.service.IUserService;
import com.hhelp.utils.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author: Jingxin Li
 * @Date: 2020-06-10 17:26
 */

@RestController
@LoginToken
public class UploadController {

    protected static final Logger logger = LoggerFactory.getLogger(HhelpApplication.class);
    private static String path = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "images//";
    //private static String path = "/home/hhelp/target/classes/images/";

    private static String url_fix = "http://180.76.184.216/";

    @Autowired
    private IUserService userService;


    /**
     * 上传文件
     * @param file
     * @return
     */
    @PostMapping("/uploadFile")
    @LoginToken
    public JSONArray uploadImg(int user_id, @RequestParam(value = "file") MultipartFile file){
        User user = userService.findUserById(user_id);
        if (user == null)
            throw new RuntimeException("503 用户身份验证失败");
        JSONArray jsons = new JSONArray();
        System.out.println(user_id);
        if (file.isEmpty())
            return Util.getJsonArray(7, "文件为空", null);
        String fileName = file.getOriginalFilename();  // 文件名

        logger.info("上传文件名：" + fileName);

        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        fileName = UUID.randomUUID() + suffixName;//为避免名称重复，新的文件名
        File dest = new File(path + fileName);//目标存储位置

        //判断上级目录是否存在
        if (! dest.getParentFile().exists()){
            dest.getParentFile().mkdir();
        }
        //保存图片
        try {
            file.transferTo(dest);
            logger.info("上传成功：" + fileName);
            JSONObject jsonObject = new JSONObject();
            String url = url_fix + fileName;
            jsonObject.put("url", url);      //生成图片url
            user.setHead_img(url);           //修改用户头像
            userService.editUser(user);     //修改用户头像
            jsons.add(jsonObject);
            logger.info("用户头像url" + user.getHead_img());
            return Util.getJsonArray(0, "", jsons);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(fileName + "上传失败！\n" + e.getMessage());
            JSONArray res = Util.getJsonArray(6, "上传失败", null);
            return res;
        }
    }


}
