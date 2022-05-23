package com.tencent.food.recommend.controller;

import com.tencent.food.recommend.common.ResultData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("upload")
public class FileController {

    @Value("${image.path}")
    private String imagePath;

    @PostMapping(value = "image")
    public ResultData<String> fileUpload(@RequestParam(value = "file") MultipartFile file, Model model, HttpServletRequest request) {
        if (file.isEmpty()) {
            return ResultData.success("文件为空空");
        }
        String fileName = file.getOriginalFilename();  // 文件名
        if (fileName == null || fileName.length() == 0) {
            return ResultData.success("文件名为空");
        }
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        String filePath = imagePath; // 上传后的路径
        fileName = System.currentTimeMillis() + suffixName; // 新文件名
        File dest = new File(filePath + fileName);
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

        return ResultData.success(filePath + fileName);
    }
}