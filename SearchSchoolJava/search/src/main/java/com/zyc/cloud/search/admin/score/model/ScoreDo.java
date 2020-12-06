package com.zyc.cloud.search.admin.score.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author ZengYc
 * @Date 2020/11/5 21:17
 */
@Data
@Table(name = "tb_score")
public class ScoreDo {

    /*分数表id*/
    @Id
    @Column(name = "score_id")
    private String scoreId;

    /*学校id*/
    @Column(name = "school_id")
    private String schoolId;

    /*年份*/
    @Column(name="year")
    private String year;

    /*分数*/
    @Column(name="score")
    private String score;

    /*专业id*/
    @Column(name="professional_id")
    private String professionalId;
}
