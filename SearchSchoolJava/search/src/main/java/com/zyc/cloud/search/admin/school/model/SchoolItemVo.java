package com.zyc.cloud.search.admin.school.model;

import lombok.Data;

import java.util.Date;

@Data
public class SchoolItemVo{

	private String schoolId;

	private String schoolName;

	private String schoolSrc;

	private String schoolType;

	private String schoolIntroduction;

	private String schoolUrl;

	private String schoolImgSrc;

	private String schoolTel;

	private String schoolHead;

	private Date schoolTime;

	private String schoolRankingSize;

	private String schoolSeachSize;

	private String isRanking;

	private String schoolPic;

	private String professionalId;

	private String professionalName;

	private String collegeId;
}
