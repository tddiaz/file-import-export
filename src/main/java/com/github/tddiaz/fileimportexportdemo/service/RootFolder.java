package com.github.tddiaz.fileimportexportdemo.service;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;

import java.io.File;

@Slf4j
@Getter
public class RootFolder {

    private final File file;

    public RootFolder(Resource storageDirectory) {
        log.info("Storage Directory: {}", storageDirectory);

        try {
            this.file = storageDirectory.getFile();

            boolean exists = this.file.exists();

            if (!exists) {
                if (this.file.mkdirs()) {
                    log.info("Successfully created a local directory: {}", this.file.getAbsolutePath());
                } else {
                    throw new RuntimeException("Cannot create directory: " + this.file);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
