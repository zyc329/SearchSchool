package com.zyc.cloud.search.admin.school.model;

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
@Table(name = "tb_pic")
public class PicDo extends BaseDo {

	/*图片id*/
	@Id
	@Column(name = "pic_id")
	private String picId;

	/*图片名称*/
	@Column(name = "pic_name")
	private String picName;

	/*图片类型*/
	@Column(name="pic_type")
	private String picType;

	/*图片地址src*/
	@Column(name="pic_src")
	private Integer picSrc;

}
