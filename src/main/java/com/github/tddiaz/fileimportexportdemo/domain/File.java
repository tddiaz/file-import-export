package com.github.tddiaz.fileimportexportdemo.domain;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String name;
    private String contentType;

    public File() {}

    public File(String name, String contentType) {
        this.name = name;
        this.contentType = contentType;
    }
}
