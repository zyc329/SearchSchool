package com.zyc.cloud.search.admin.college.model;

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
@Table(name = "tb_college")
public class CollegeDo  extends BaseDo {

    /*学院id*/
    @Id
    @Column(name = "college_id")
    private String collegeId;

    /*学校id*/
    @Column(name = "school_id")
    private String schoolId;

    /*学院名称*/
    @Column(name="college_name")
    private String collegeName;

    /*学院介绍*/
    @Column(name="college_introduction")
    private String collegeIntroduction;

    /*学院负责人*/
    @Column(name="college_head")
    private String collegeHead;
}
