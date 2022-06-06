package com.tencent.food.recommend.controller;

import com.alibaba.fastjson.JSON;
import com.tencent.food.recommend.common.ResultData;
import com.tencent.food.recommend.common.enums.ReturnCode;
import com.tencent.food.recommend.common.translate.TransApi;
import com.tencent.food.recommend.common.translate.TranslateResult;
import com.tencent.food.recommend.common.utils.HttpClientUtil;
import com.tencent.food.recommend.common.utils.ImageUpload;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("upload")
public class FileController {

    @Value("${image.path}")
    private String imagePath;

    private final TransApi transApi = new TransApi();

    @PostMapping(value = "image")
    public ResultData<List<String>> fileUpload(@RequestParam(value = "file") MultipartFile file, Model model, HttpServletRequest request) {
//        if (file.isEmpty()) {
//            return ResultData.fail(ReturnCode.RC999.getCode(), "文件为空空");
//        }
//        String fileName = file.getOriginalFilename();  // 文件名
//        if (fileName == null || fileName.length() == 0) {
//            return ResultData.fail(ReturnCode.RC999.getCode(), "文件为空空");
//        }
//        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
//        String filePath = imagePath; // 上传后的路径
//        fileName = System.currentTimeMillis() + suffixName; // 新文件名
//        String distPath = filePath + fileName;
//        File dest = new File(distPath);
//        if (!dest.getParentFile().exists()) {
//            dest.getParentFile().mkdirs();
//        }
//        try {
//            file.transferTo(dest);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        String distPath = ImageUpload.upload(file);
        if (distPath == null) {
            return ResultData.fail(ReturnCode.RC999.getCode(), "文件为空空");
        }
        Map<String, String> param = new HashMap<>();
        param.put("image_path", distPath);
        String s = HttpClientUtil.doGet("http://127.0.0.1:8082/identify_picture", param);
        if (!StringUtils.hasText(s)) {
            return ResultData.fail(ReturnCode.RC999.getCode(), "识别失败");
        }
        RecognizeFoodRes res = JSON.parseObject(s, RecognizeFoodRes.class);
        if (res == null || CollectionUtils.isEmpty(res.getContent())) {
            return ResultData.fail(ReturnCode.RC999.getCode(), "识别失败");
        }
        List<String> collect = res.getContent().stream().distinct().map(e -> {
            String transResult = transApi.getTransResult(e, "auto", "zh");
            TranslateResult translateResult = JSON.parseObject(transResult, TranslateResult.class);
            if (translateResult == null || CollectionUtils.isEmpty(translateResult.getTransResult())) {
                return e;
            }
            String dst = translateResult.getTransResult().get(0).getDst();
            return unicode2String(dst);
        }).collect(Collectors.toList());
        return ResultData.success(collect);
    }

    public static void main(String[] args) {
        TransApi transApi = new TransApi();
        String transResult = transApi.getTransResult("orange", "auto", "zh");
        TranslateResult translateResult = JSON.parseObject(transResult, TranslateResult.class);
        String s = new FileController().unicode2String(translateResult.getTransResult().get(0).getDst());
        System.out.println(s);
    }


    private String unicode2String(String unicode) {
        if (unicode == null || unicode.length() < 1) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        int i = -1;
        int pos = 0;

        while ((i = unicode.indexOf("\\u", pos)) != -1) {
            sb.append(unicode.substring(pos, i));
            if (i + 5 < unicode.length()) {
                pos = i + 6;
                sb.append((char) Integer.parseInt(unicode.substring(i + 2, i + 6), 16));
            }
        }
        //如果pos位置后，有非中文字符，直接添加
        sb.append(unicode.substring(pos));

        return sb.toString();
    }
}