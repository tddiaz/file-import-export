package com.github.tddiaz.fileimportexportdemo.controller;

import com.github.tddiaz.fileimportexportdemo.service.FileData;
import com.github.tddiaz.fileimportexportdemo.service.FileService;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("/files")
public class FileExportController {

    private final FileService fileService;

    public FileExportController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping
    public ModelAndView files() {
        ModelAndView modelAndView = new ModelAndView("views/file-export");
        modelAndView.addObject("files", fileService.findAll());

        return modelAndView;
    }

    @GetMapping("/export/{fileName}")
    @ResponseStatus(HttpStatus.OK)
    public void export(@PathVariable("fileName") String fileName,
                                 HttpServletResponse httpServletResponse) throws IOException {

        final FileData fileData = fileService.exportFile(fileName);

        httpServletResponse.setContentType(fileData.getContentType());
        httpServletResponse.setHeader(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", fileData.getName().replace(" ", "_")));

        IOUtils.copy(fileData.getInputStream(), httpServletResponse.getOutputStream());

        httpServletResponse.flushBuffer();
    }
}
