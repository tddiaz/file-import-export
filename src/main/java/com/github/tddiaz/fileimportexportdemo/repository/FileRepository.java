package com.github.tddiaz.fileimportexportdemo.repository;

import com.github.tddiaz.fileimportexportdemo.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, String>{

    File findByName(String name);
}
