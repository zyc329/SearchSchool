package com.zyc.cloud.search.admin.school.controller;

import com.zyc.cloud.search.admin.school.model.PicDo;
import com.zyc.cloud.search.admin.school.model.SchoolDo;
import com.zyc.cloud.search.admin.school.service.SchoolService;
import com.zyc.cloud.search.admin.user.model.UserDo;
import com.zyc.cloud.search.text.model.Text;
import com.zyc.cloud.search.utils.PageResult;
import com.zyc.cloud.search.utils.ResultUtil;
import com.zyc.cloud.search.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZengYc
 * @Date 2020/11/5 21:07
 */
@RestController
@RequestMapping(value = "/school")
@CrossOrigin
public class SchoolController {

	@Autowired
	private SchoolService schoolService;

	/**
	 * 新增学校
	 */
	@PostMapping("shcoolAdd")
	public ResultUtil shcoolAdd(@RequestBody SchoolDo schoolDo) {
		SchoolDo item = new SchoolDo();
		item.setSchoolName(schoolDo.getSchoolName());
		if (schoolService.findList(item).size() > 0){
			return new ResultUtil(false, StatusCode.ERROR, "学校已存在！");
		}
		schoolService.add(schoolDo);
		return new ResultUtil(true, StatusCode.OK, "新增成功！");
	}

	/**
	 * 新增
	 */
	@PostMapping("picAdd")
	public ResultUtil picAdd(@RequestBody List<PicDo> picDos) {
		schoolService.picAdd(picDos);
		return new ResultUtil(true, StatusCode.OK, "新增成功！");
	}

	/**
	 * 条件查询
	 */
	@PostMapping("schoolFindList")
	public ResultUtil<List<SchoolDo>> schoolFindList(@RequestBody SchoolDo schoolDo) {
		List<SchoolDo> texts = schoolService.findList(schoolDo);
		return new ResultUtil<List<SchoolDo>>(true, StatusCode.OK, "条件查询成功", texts);
	}

	/**
	 * 修改
	 */
	@PostMapping("schoolUpdate")
	public ResultUtil schoolUpdate(@RequestBody SchoolDo schoolDo) {

		Integer flag = schoolService.updateById(schoolDo);
		String message = "";
		Integer code = StatusCode.OK;
		if (flag != 1){
			message="修改失败！";
		}else{
			message="修改成功！";
			code = StatusCode.ERROR;
		}
		return new ResultUtil(true, code, message);
	}

	/**
	 * 根据ID删除
	 */
	@PostMapping("schoolDelete")
	public ResultUtil schoolDelete(@RequestParam(value = "schoolId") String schoolId) {
		schoolService.delete(schoolId);
		return new ResultUtil(true, StatusCode.OK, "删除成功！");
	}

	/**
	 * 根据主键ID查询
	 */
	@PostMapping("schoolFindId")
	public ResultUtil<Text> schoolFindId(@RequestParam(value = "schoolId") String schoolId) {
		Map<String,Object> item = new HashMap<>();
		// 调用Service实现查询
		SchoolDo school = schoolService.findById(schoolId);
		item.put("school",school);
		List<PicDo> pics = schoolService.findPicsById(school.getSchoolPic());
		item.put("pic",pics);
		return ResultUtil.success("查询成功", item);
	}

	/**
	 * 根据主键ID查询
	 */
	@PostMapping("schoolSeach")
	public ResultUtil<Text> schoolSeach(@RequestParam(value = "schoolId") String schoolId) {
		schoolService.addSchoolSeach(schoolId);
		return new ResultUtil(true, StatusCode.OK,"预览成功");
	}

	/**
	 * 条件+分页查询实现
	 */
	@PostMapping("/schoolFindPage")
	public ResultUtil<PageResult<UserDo>> userFindPage(@RequestBody(required = false) SchoolDo schoolDo,
													   @RequestParam(required = false, defaultValue = "1") int page,
													   @RequestParam(required = false, defaultValue = "10") int size,
													   @RequestParam(required = false) String ranking,
													   @RequestParam(required = false) String seach){
		PageResult<SchoolDo> pageResult =schoolService.findPage(schoolDo,page,size,ranking,seach);
		return new ResultUtil<PageResult<UserDo>>(true, StatusCode.OK, "分页查询成功",pageResult);
	}
}
