package com.github.tddiaz.fileimportexportdemo.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
@Slf4j
public class FileStorageService {

    private final File rootFile;

    public FileStorageService(RootFolder rootFolder) {
        this.rootFile = rootFolder.getFile();
    }

    public void store(FileData fileData) {

        final File file = new File(rootFile, fileData.getName());

        if (file.exists()) { // override file if exists
            file.delete();
        }

        OutputStream outputStream = null;
        InputStream inputStream = null;

        try {

            inputStream = fileData.getInputStream();
            outputStream = new BufferedOutputStream(new FileOutputStream(file));

            IOUtils.copy(inputStream, outputStream);

            outputStream.flush();

        } catch (IOException e) {
            throw new RuntimeException(String.format("Unable to store file \"%s\" under \"%s\"", fileData.getName(), rootFile), e);
        } finally {
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(outputStream);
        }

        log.info("Stored new file: {}", file.getName());
    }

    public InputStream getFile(String fileName) throws FileNotFoundException {

        final File file = new File(rootFile, fileName);

        if (!file.exists()) {
            throw new FileNotFoundException(String.format("Couldn't find file \"%s\" under \"%s\"", fileName, rootFile));
        }
        log.info("Found file {}", fileName);
        return new FileInputStream(file);
    }

}
