package com.github.tddiaz.fileimportexportdemo.configuration;

import com.github.tddiaz.fileimportexportdemo.service.RootFolder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class RootFolderConfig {

    @Value("${storage.directory}")
    private Resource storageDirectory;

    @Bean
    public RootFolder rootFolder() {
        return new RootFolder(storageDirectory);
    }
}
