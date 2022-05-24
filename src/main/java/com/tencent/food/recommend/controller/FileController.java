package com.tencent.food.recommend.controller;

import com.alibaba.fastjson.JSONObject;
import com.tencent.food.recommend.common.ResultData;
import com.tencent.food.recommend.common.enums.ReturnCode;
import com.tencent.food.recommend.common.utils.HttpClientUtil;
import com.tencent.food.recommend.response.RecognizeFoodRes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("upload")
public class FileController {

    @Value("${image.path}")
    private String imagePath;

    @PostMapping(value = "image")
    public ResultData<List<String>> fileUpload(@RequestParam(value = "file") MultipartFile file, Model model, HttpServletRequest request) {
        if (file.isEmpty()) {
            return ResultData.fail(ReturnCode.RC999.getCode(), "文件为空空");
        }
        String fileName = file.getOriginalFilename();  // 文件名
        if (fileName == null || fileName.length() == 0) {
            return ResultData.fail(ReturnCode.RC999.getCode(), "文件为空空");
        }
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        String filePath = imagePath; // 上传后的路径
        fileName = System.currentTimeMillis() + suffixName; // 新文件名
        String distPath = filePath + fileName;
        File dest = new File(distPath);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        String filename = "/images/" + fileName;
//        model.addAttribute("filename", filename);
        Map<String, String> param = new HashMap<>();
        param.put("image_path", distPath);
        String s = HttpClientUtil.doGet("http://127.0.0.1:8080/identify_picture", param);
        if (!StringUtils.hasText(s)) {
            return ResultData.fail(ReturnCode.RC999.getCode(), "识别失败");
        }
        RecognizeFoodRes res = JSONObject.parseObject(s, RecognizeFoodRes.class);
        if (res == null || CollectionUtils.isEmpty(res.getContent())) {
            return ResultData.fail(ReturnCode.RC999.getCode(), "识别失败");
        }
        return ResultData.success(res.getContent());
    }
}