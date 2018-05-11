package com.github.tddiaz.fileimportexportdemo.controller;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImportRequest {

    private MultipartFile file;
}
