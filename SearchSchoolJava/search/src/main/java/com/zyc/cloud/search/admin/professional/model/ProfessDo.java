package com.zyc.cloud.search.admin.professional.model;

import com.zyc.cloud.search.utils.base.BaseDo;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author ZengYc
 * @Date 2020/11/5 21:17
 */
@Data
@Table(name = "tb_professional")
public class ProfessDo extends BaseDo {

    /*专业id*/
    @Id
    @Column(name = "professional_id")
    private String professionalId;

    /*专业名称*/
    @Column(name = "professional_name")
    private String professionalName;

    /*专业名称*/
    @Column(name = "professional_introduction")
    private String professionalIntroduction;

    /*学校id*/
    @Column(name="school_id")
    private String schoolId;

    /*学院id*/
    @Column(name="college_id")
    private String collegeId;
}
