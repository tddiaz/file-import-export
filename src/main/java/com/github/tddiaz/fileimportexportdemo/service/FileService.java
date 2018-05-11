package com.github.tddiaz.fileimportexportdemo.service;

import com.github.tddiaz.fileimportexportdemo.domain.File;
import com.github.tddiaz.fileimportexportdemo.repository.FileRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileService {

    private final FileStorageService fileStorageService;
    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository, FileStorageService fileStorageService) {
        this.fileRepository = fileRepository;
        this.fileStorageService = fileStorageService;
    }

    public void importFile(MultipartFile multipartFile) throws IOException {
        fileStorageService.store(new FileData(multipartFile.getBytes(), multipartFile.getOriginalFilename(), multipartFile.getContentType()));
        fileRepository.save(new File(multipartFile.getOriginalFilename(), multipartFile.getContentType()));
    }

}
