package com.zyc.cloud.search.admin.school.controller;

import com.zyc.cloud.search.admin.college.model.CollegeDo;
import com.zyc.cloud.search.admin.college.service.CollegeService;
import com.zyc.cloud.search.admin.professional.model.ProfessDo;
import com.zyc.cloud.search.admin.professional.service.ProfessService;
import com.zyc.cloud.search.admin.school.model.PicDo;
import com.zyc.cloud.search.admin.school.model.SchoolDo;
import com.zyc.cloud.search.admin.school.model.SchoolItemVo;
import com.zyc.cloud.search.admin.school.service.SchoolService;
import com.zyc.cloud.search.admin.teacher.model.TeacherDo;
import com.zyc.cloud.search.admin.teacher.service.TeacherService;
import com.zyc.cloud.search.admin.user.model.UserDo;
import com.zyc.cloud.search.utils.PageResult;
import com.zyc.cloud.search.utils.ResultUtil;
import com.zyc.cloud.search.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

	@Autowired
	private CollegeService collegeService;

	@Autowired
	private ProfessService professService;

	@Autowired
	private TeacherService teacherService;

	/**
	 * 新增学校
	 */
	@PostMapping("schoolAdd")
	public ResultUtil schoolAdd(@RequestBody SchoolDo schoolDo) {
		SchoolDo item = new SchoolDo();
		item.setSchoolName(schoolDo.getSchoolName());
		if (schoolService.findList(item).size() > 0){
			return new ResultUtil(false, StatusCode.ERROR, "学校已存在！");
		}
		schoolService.add(schoolDo);
		return new ResultUtil(true, StatusCode.OK, "新增成功！");
	}

	/**
	 * 查询所有
	 */
	@PostMapping("schoolAll")
	public ResultUtil<List<SchoolDo>> userFindAll() {
		List<SchoolDo> SchoolDos = schoolService.findAll();
		return new ResultUtil<List<SchoolDo>>(true, StatusCode.OK, "查询成功", SchoolDos);
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
	public ResultUtil<Map> schoolFindId(@RequestParam(value = "schoolId") String schoolId) {
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
	@PostMapping("schoolSearch")
	public ResultUtil<SchoolDo> schoolSearch(@RequestParam(value = "schoolId") String schoolId) {
		schoolService.addSchoolSearch(schoolId);
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

	/**
	 * 根据主键ID查询详细
	 */
	@PostMapping("detailFindId")
	public ResultUtil<Map> detailFindId(@RequestParam(value = "schoolId") String schoolId) {
		Map<String,Object> item = new HashMap<>();
		// 调用Service实现查询
		SchoolDo school = schoolService.findById(schoolId);
		item.put("school",school);
		List<PicDo> pics = schoolService.findPicsById(school.getSchoolPic());
		item.put("pic",pics);

		//专业
		CollegeDo collegeDo = new CollegeDo();
		collegeDo.setSchoolId(schoolId);
		List<CollegeDo> colleges = collegeService.findList(collegeDo);
		item.put("colleges",colleges);
		//学院
		ProfessDo professDo = new ProfessDo();
		professDo.setSchoolId(schoolId);
		List<ProfessDo> profess = professService.findList(professDo);
		item.put("profess",profess);

		//教师
		TeacherDo teacherDo = new TeacherDo();
		teacherDo.setSchoolId(schoolId);
		List<TeacherDo> teachers = teacherService.findList(teacherDo);
		item.put("teachers",teachers);
		return ResultUtil.success("查询成功", item);
	}

	/**
	 * 查询
	 */
	@PostMapping("selectItem")
	public ResultUtil<List<SchoolItemVo>> selectItem(@RequestBody HashMap<String,Object> map) {
		HashMap<String,String> example = (HashMap<String,String>)map.get("example");
		ArrayList<String> roleNameList = (ArrayList<String>)map.get("roleNameList");
		List<SchoolItemVo> SchoolItemVos = schoolService.selectItem(example,roleNameList);
		return new ResultUtil<List<SchoolItemVo>>(true, StatusCode.OK, "条件查询成功",SchoolItemVos);
	}

	@PutMapping("addCount")
	public void addCount(@RequestParam String schoolId){
		schoolService.addCount(schoolId);
	}
}
