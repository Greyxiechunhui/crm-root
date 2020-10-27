package com.crm.cn.controller;


import com.crm.cn.http.AxiosResult;
import com.crm.cn.upload.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Part;
import java.io.IOException;

@RestController
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("upload")
    public AxiosResult upload(@RequestPart("file") Part part) throws IOException {
        return uploadService.uploadService(part.getInputStream(),part.getSubmittedFileName(),part.getSize());
    }
}
