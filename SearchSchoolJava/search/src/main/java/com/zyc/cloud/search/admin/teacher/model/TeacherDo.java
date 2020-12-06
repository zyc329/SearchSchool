package com.zyc.cloud.search.admin.teacher.model;

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
@Table(name = "tb_teacher")
public class TeacherDo extends BaseDo {

    /*教师id*/
    @Id
    @Column(name = "teacher_id")
    private String teacherId;

    /*所处学校id*/
    @Column(name = "school_id")
    private String schoolId;

    /*教师名称*/
    @Column(name = "teacher_name")
    private String teacherName;

    /*教师证件照*/
    @Column(name = "profile_picture")
    private String profilePicture;

    /*教师简介*/
    @Column(name = "teacher_introduction")
    private String teacherIntroduction;

    /*教学工龄*/
    @Column(name = "teaching_age")
    private String teachingAge;

    /*职称*/
    @Column(name = "title")
    private String title;

    /*学院id*/
    @Column(name = "college_id")
    private String collegeId;

    /*专业id*/
    @Column(name = "professional_id")
    private String professionalId;
}
