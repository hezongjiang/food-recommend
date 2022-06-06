package com.tencent.food.recommend.common.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class ImageUpload {

    private static final String FILE_PATH = "/root/food_recommend/images/"; // 上传后的路径

    /**
     * 上传图片
     * @param image 文件内容
     */
    public static String upload(MultipartFile image) {
        if (image.isEmpty()) {
            return null;
        }
        String fileName = image.getOriginalFilename();  // 文件名
        if (fileName == null || fileName.length() == 0) {
            return null;
        }
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        fileName = System.currentTimeMillis() + suffixName; // 新文件名
        String distPath = FILE_PATH + fileName;
        File dest = new File(distPath);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            image.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return distPath;
    }
}
