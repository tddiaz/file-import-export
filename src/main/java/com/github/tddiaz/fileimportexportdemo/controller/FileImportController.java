package com.github.tddiaz.fileimportexportdemo.controller;

import com.github.tddiaz.fileimportexportdemo.service.FileData;
import com.github.tddiaz.fileimportexportdemo.service.FileService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@RestController
@RequestMapping("/files/import")
public class FileImportController {

    private final FileService fileService;

    public FileImportController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping
    public ModelAndView importFile(@ModelAttribute("importRequest") ImportRequest importRequest) throws IOException {

        final MultipartFile multipartFile = importRequest.getFile();

        fileService.importFile(new FileData(multipartFile));

        final ModelAndView modelAndView = getModelView();
        modelAndView.addObject("success", true);

        return modelAndView;
    }

    @GetMapping
    public ModelAndView getUploadPage() {
        return getModelView();
    }

    private ModelAndView getModelView() {
        final ModelAndView modelAndView = new ModelAndView("views/file-import");
        modelAndView.addObject("importRequest", new ImportRequest());

        return modelAndView;
    }
}
