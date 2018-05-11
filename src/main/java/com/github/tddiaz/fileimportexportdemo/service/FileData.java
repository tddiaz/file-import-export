package com.github.tddiaz.fileimportexportdemo.service;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Data
public class FileData {

    private final String name;

    private final String contentType;

    private final InputStream inputStream;

    public FileData(MultipartFile multipartFile) throws IOException {
        this.name = multipartFile.getOriginalFilename();
        this.contentType = multipartFile.getContentType();
        this.inputStream = multipartFile.getInputStream();
    }

    public FileData(String name, String contentType, InputStream inputStream) {
        this.name = name;
        this.contentType = contentType;
        this.inputStream = inputStream;
    }
}
