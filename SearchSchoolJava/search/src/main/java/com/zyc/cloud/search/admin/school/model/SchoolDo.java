package com.zyc.cloud.search.admin.school.model;

import com.zyc.cloud.search.utils.base.BaseDo;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author ZengYc
 * @Date 2020/11/5 21:17
 */
@Data
@Table(name = "tb_school")
public class SchoolDo extends BaseDo {

	/*学校id*/
	@Id
	@Column(name = "school_id")
	private String schoolId;

	/*学校名称*/
	@Column(name = "school_name")
	private String schoolName;

	/*学校具体地址*/
	@Column(name="school_src")
	private String schoolSrc;

	/*学校类型 0专科 1本科*/
	@Column(name="school_type")
	private String schoolType;

	/*学校简介*/
	@Column(name="school_introduction")
	private String schoolIntroduction;

	/*学校官网网址 */
	@Column(name="school_url")
	private String schoolUrl;

	/*学校校标src */
	@Column(name="school_img_src")
	private String schoolImgSrc;

	/*招生热线*/
	@Column(name="school_tel")
	private String schoolTel;

	/*招生办负责人*/
	@Column(name="school_head")
	private String schoolHead;

	/*创校时间*/
	@Column(name="school_time")
	private Date schoolTime;

	/*推荐排名*/
	@Column(name="school_ranking_size")
	private String schoolRankingSize;

	/*查看次数*/
	@Column(name="school_seach_size")
	private String schoolSeachSize;

	/*是否推荐 0否，1是*/
	@Column(name="is_ranking")
	private String isRanking;

	/*图片表*/
	@Column(name="school_pic")
	private String schoolPic;

}
