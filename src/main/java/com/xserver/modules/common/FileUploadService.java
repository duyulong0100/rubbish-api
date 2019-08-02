package com.xserver.modules.common;

import com.xserver.common.BaseService;
import com.xserver.common.Response;
import com.xserver.util.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static com.xserver.common.ErrorCodeConstant.EC_COMMON_FILE_UPLOAD_ERR;

@Service
public class FileUploadService extends BaseService {

    @Value("${multipart.file.static.url}")
    private String staticUrl;

    private int sizeAllowed = 500;// 500Kb

    public Response<String> uploadFile(MultipartFile file) {
        Response<String> response = new Response<>();
        try {
            if (file != null && !file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                String suffix = FileUtils.getFileSuffix(fileName);
                // if (!multipartSuffixAllowed.contains(suffix)) {
                // return error(EC_COMMON_FILE_SUFFIX_ERR);
                // }
                // if (!isSizeOk(file.getSize())) {
                // return error(EC_COMMON_FILE_SIZE_ERR);
                // }
                String destName = UUID.randomUUID().toString().replace("-", "") + suffix;
                File destFile = new File(destName);
                file.transferTo(destFile);
                response.setData(staticUrl + File.separator + destName);
            }
        } catch (IOException e) {
            return error(EC_COMMON_FILE_UPLOAD_ERR);
        }
        return response;
    }

    private boolean isSizeOk(long size) {
        double fileSize = size / 1024;
        if (fileSize > sizeAllowed) {
            return false;
        }
        return true;

    }
}
