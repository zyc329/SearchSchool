package com.zyc.cloud.search.text.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author ZengYc
 */
@Table(name = "tb_text")
@Data
public class Text {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Column(name = "text_name")
    private String textName;

    @Column(name = "text_age")
    private String textAge;

    @Column(name = "text_sex")
    private String textSex;

}
