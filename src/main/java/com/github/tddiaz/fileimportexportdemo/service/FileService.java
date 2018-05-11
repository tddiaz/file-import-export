package com.github.tddiaz.fileimportexportdemo.service;

import com.github.tddiaz.fileimportexportdemo.domain.File;
import com.github.tddiaz.fileimportexportdemo.repository.FileRepository;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

@Service
public class FileService {

    private final FileStorageService fileStorageService;
    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository, FileStorageService fileStorageService) {
        this.fileRepository = fileRepository;
        this.fileStorageService = fileStorageService;
    }

    public void importFile(FileData fileData) {
        fileStorageService.store(fileData);
        fileRepository.save(new File(fileData.getName(), fileData.getContentType()));
    }

    public FileData exportFile(String fileName) throws FileNotFoundException {
        File file = fileRepository.findByName(fileName);
        InputStream fileInputStream = fileStorageService.getFile(fileName);

        return new FileData(fileName, file.getContentType(), fileInputStream);
    }

    public List<File> findAll() {
        return fileRepository.findAll();
    }

}
