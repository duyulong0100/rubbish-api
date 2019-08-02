package com.xserver.modules.common;

import com.xserver.common.BaseController;
import com.xserver.common.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
@Api(tags = { "文件上传接口文档" })
public class FileUploadController extends BaseController {

    @Autowired
    private FileUploadService fileUploadService;

    @ApiOperation(value = "文件上传", notes = "图片上传")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Response<String> upload(@RequestParam(value = "file") MultipartFile file) {
        return fileUploadService.uploadFile(file);
    }
}
