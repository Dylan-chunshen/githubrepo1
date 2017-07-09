package com.edubean.entrust.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import platform.utils.LoginUtil;
import wegov.platform.RightQHelper;
import wfc.facility.hibernate.FilterListHelper;
import wfc.facility.right.common.RoleInfo;
import wfc.facility.right.exception.RightException;
import wfc.facility.util.FilterList;
import wfc.facility.util.IPHelper;

import com.edubean.common.service.CommonService;
import com.edubean.common.util.Fl885Const;
import com.edubean.consult.bo.ConsultBo;
import com.edubean.entrust.bo.EntrustBo;
import com.edubean.lawcase.bo.KeywordsBo;

@Controller
@RequestMapping("entrust/")
public class EntrustController {
	
	@Autowired
	private CommonService commonService;
	
	/**
	 * 页面跳转
	 * 1. 如果是区县用户则跳转到区县页面
	 */
	@RequestMapping("entrust-edit.html")
	public String consult_edit(HttpServletRequest request,
			HttpServletResponse response,@RequestParam HashMap<String, Object> conditions,
			Map<String, Object> model) throws RightException{
			return "/page/entrust/entrust-edit";
	}
	
	/**
	 * 保存法律委托信息
	 * @param request
	 * @param response
	 * @param conditions
	 * @param model
	 * @return
	 * @throws RightException
	 */
	@RequestMapping("entrustSave.do")
	public String consultSave(HttpServletRequest request,
			HttpServletResponse response,@RequestParam HashMap<String, Object> conditions,
			Map<String, Object> model) throws RightException{
		HttpSession session    = request.getSession();
		String phone = (session.getAttribute("phone")!=null)?session.getAttribute("phone").toString():((conditions.containsKey("phone"))?conditions.get("phone").toString().trim():"");
		String entrustContent = (conditions.containsKey("entrustContent"))?conditions.get("entrustContent").toString().trim():"";
		
		if(StringUtils.isNotBlank(entrustContent)){
			EntrustBo entrustBo = new EntrustBo();
			entrustBo.setPhone(phone);
			entrustBo.setTitle((StringUtils.isNotBlank(entrustContent)&&entrustContent.length()>150?entrustContent.substring(0, 150):entrustContent));
			entrustBo.setContent(entrustContent);
			entrustBo.setIp(request.getRemoteHost());
			entrustBo.setIp(IPHelper.getIpAddr(request));
			entrustBo.setCreatetime(new Date());
			entrustBo.setIsremoved("0");
			commonService.save(entrustBo);
			model.put("entrustBo", entrustBo);
		}
		return "/page/entrust/entrust-view";
	}	
	
	/**
	 * 获取列表数据
	 * @param request
	 * @param response
	 * @param conditions
	 * @param page
	 * @param rows
	 * @param flag
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/listModel.ajax")
	public Map<String, Object> listModel(HttpServletRequest request,
			HttpServletResponse response,@RequestParam HashMap<String, Object> conditions,String name,
			int page,int rows,String flag, Map<String, Object> model){
//		Query query = Query.from(ModelBo.class).filter(conditions, null, FilterOperator.LIKE);
//		HttpSession session = request.getSession();
//		String roleTypeCode = (session.getAttribute("roleTypeCode")!=null)?session.getAttribute("roleTypeCode").toString():"";
//		String stType       = (conditions.containsKey("stType"))?conditions.get("stType").toString().trim():"";
////		1. 如果是模板文件-市局看所有，警种看各自
//		if("model".equalsIgnoreCase(stType)){
//			if(!Fl885Const.ROLE_ID_SJ.equalsIgnoreCase(roleTypeCode)){
//				query.filter(FilterOperator.EQUAL, "stFileType", roleTypeCode);
//			}
//		}else if("data".equalsIgnoreCase(stType)){
//			if(Fl885Const.ROLE_ID_SJ.equalsIgnoreCase(roleTypeCode)){
////				市局看全部警种
//				if(!(conditions.containsKey("stFileType")&&conditions.get("stFileType").toString().equalsIgnoreCase(Fl885Const.ROLE_ID_SJ))){
//					query.filter(FilterOperator.NOTEQUAL, "stFileType",Fl885Const.ROLE_ID_SJ);
//				}else{
////				市局看自己
//					query.filter(FilterOperator.EQUAL, "stFileType",Fl885Const.ROLE_ID_SJ);
//				}
//			}else if(Fl885Const.ROLE_ID_QX.equalsIgnoreCase(roleTypeCode)){
////				区县看市局
//				query.filter(FilterOperator.EQUAL, "stFileType",Fl885Const.ROLE_ID_SJ);
//			}else if(StringUtils.isNotBlank(roleTypeCode)){
////				警种看自己
//				query.filter(FilterOperator.EQUAL, "stFileType", roleTypeCode);
//			}
//		}else if("statics".equalsIgnoreCase(stType)){
////			通过stType过滤即可
//		}
//		query.orderBy(new Order(OrderOperator.DESC, "createdTime")); 
//		Page modelPage = commonService.findAll(query, Pages.setPageAndSize(page,rows));
//		Map<String, Object> jsonMap = EasyUIJsonUtils.convertJpaPageToJson(modelPage,"yyyy-MM-dd HH:mm:ss");
		return null;
	}
	
	/**
	 * 获取某个模板
	 * @param conditions
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/getModelBy.ajax")
	public Map<String, Object> getModelBy(HttpServletRequest request,
			HttpServletResponse response,@RequestParam HashMap<String, Object> conditions,Map<String, Object> model){
//		Map<String, Object> jsonMap = new HashMap<String, Object>();
//		Query query = Query.from(ModelBo.class).filter(conditions, null, FilterOperator.EQUAL);
//		query.orderBy(new Order(OrderOperator.DESC, "createdTime"));
//		List modelList = commonService.findAll(query);
//		if(modelList!=null&&modelList.size()>0){
//			ModelBo modelBo = (ModelBo)modelList.get(0);
//			jsonMap.put("model", modelBo);
//		}
		return null;
	}
	
	/**
	 * 跳转到模板列表页面
	 * @param request
	 * @param response
	 * @param conditions
	 * @param model
	 * @return
	 * @throws RightException 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/listModel.html")
	public String list_region(HttpServletRequest request,
			HttpServletResponse response,@RequestParam HashMap<String, Object> conditions,
			Map<String, Object> model) throws RightException{
		HttpSession session = request.getSession();
		String roleTypeCode = (session.getAttribute("roleTypeCode")!=null)?session.getAttribute("roleTypeCode").toString():"";
		RightQHelper  roleHelper= new RightQHelper();
		FilterList filterList   = new FilterList();
		filterList.add(RoleInfo.class.getName(), FilterListHelper.NOT_EQUAL, "roleId", Fl885Const.ROLE_ID_QX);
		filterList.add(RoleInfo.class.getName(), FilterListHelper.NOT_EQUAL, "roleId", Fl885Const.ROLE_ID_SJ);
		List<RoleInfo> listRole = roleHelper.getRoles("JXKH", filterList);
		List<Map<String, Object>> listRoleMap = new ArrayList<Map<String, Object>>();
		if(listRole!=null&&listRole.size()>0){
			for(RoleInfo roleBo:listRole){
				listRoleMap.add(LoginUtil.transRoleBoToMap(roleBo));
			}
		}
		model.put("listRoleMap", listRoleMap);
		model.put("allJz", (conditions.containsKey("allJz"))?conditions.get("allJz").toString():"0");
		model.put("stFileType", (conditions.containsKey("allJz")&&"0".equalsIgnoreCase(conditions.get("allJz").toString()))?roleTypeCode:"");
		return "model/modelList";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/listDatasfileSj.html")
	public String list_datasfile_sj(HttpServletRequest request,
			HttpServletResponse response,@RequestParam HashMap<String, Object> conditions,String stFileType,
			Map<String, Object> model) throws RightException{
		RightQHelper  roleHelper= new RightQHelper();
		FilterList filterList   = new FilterList();
		filterList.add(RoleInfo.class.getName(), FilterListHelper.NOT_EQUAL, "roleId", Fl885Const.ROLE_ID_QX);
		filterList.add(RoleInfo.class.getName(), FilterListHelper.NOT_EQUAL, "roleId", Fl885Const.ROLE_ID_SJ);
		List<RoleInfo> listRole = roleHelper.getRoles("JXKH", filterList);
		List<Map<String, Object>> listRoleMap = new ArrayList<Map<String, Object>>();
		if(listRole!=null&&listRole.size()>0){
			for(RoleInfo roleBo:listRole){
				listRoleMap.add(LoginUtil.transRoleBoToMap(roleBo));
			}
		}
		model.put("listRoleMap", listRoleMap);
		model.put("allJz", (conditions.containsKey("allJz"))?conditions.get("allJz").toString():"0");
		return "datas/datasfileListSj";
	}
	
	@RequestMapping("/listDatasfileJz.html")
	public String list_datasfile_jz(HttpServletRequest request,
			HttpServletResponse response,@RequestParam HashMap<String, Object> conditions,String stFileType,
			Map<String, Object> model) throws RightException{
		return "datas/datasfileListJz";
	}
	
	/**
	 * 跳转到模板上传页面
	 * @param id
	 * @param flag
	 * @param model
	 * @return
	 * @throws RightException 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/uploadModel.html")
	public String uploadModel(String id,String flag,Map<String, Object> model) throws RightException{
		RightQHelper  roleHelper= new RightQHelper();
		FilterList filterList   = new FilterList();
		filterList.add(RoleInfo.class.getName(), FilterListHelper.NOT_EQUAL, "roleId", Fl885Const.ROLE_ID_QX);
		filterList.add(RoleInfo.class.getName(), FilterListHelper.NOT_EQUAL, "roleId", Fl885Const.ROLE_ID_SJ);
		List<RoleInfo> listRole = roleHelper.getRoles("JXKH", filterList);
		List<Map<String, Object>> listRoleMap = new ArrayList<Map<String, Object>>();
		if(listRole!=null&&listRole.size()>0){
			for(RoleInfo roleBo:listRole){
				listRoleMap.add(LoginUtil.transRoleBoToMap(roleBo));
			}
		}
		model.put("listRoleMap", listRoleMap);
		return "model/upLoadModelFile";
	}
	
	/**
	 * 跳转到数据文件上传页面
	 * @param id
	 * @param flag
	 * @param model
	 * @return
	 */
	@RequestMapping("/uploadDatasfile.html")
	public String upload_datasfile(HttpServletRequest request,String id,String flag,Map<String, Object> model){
		HttpSession session = request.getSession();
		String roleTypeCode = (session.getAttribute("roleTypeCode")!=null)?session.getAttribute("roleTypeCode").toString():"";
		if(Fl885Const.ROLE_ID_SJ.equalsIgnoreCase(roleTypeCode)){
			return "datas/upLoadDatasfileSj";
		}else{
			return "datas/upLoadDatasfileJz";
		}
	}
	
	/**
	 * 上传excel文件-
	 * 1.市局上报模板文件      model
	 * 2.市局上市局汇总文件  data
	 * 3.市局上报专用于统计的数据文件[数据项的值入库] statics
	 * 4.警种上报绩效文件     data
	 * @param file
	 * @param conditions conditions.get("stType")中：model为模板文件,data为数据文件
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/upLoadModel.do")
	public String upLoadModel(HttpServletRequest request,HttpServletResponse response,@RequestParam(value="file",required= false) MultipartFile file,
		@RequestParam HashMap<String, Object> conditions,Map<String, Object> model,String flag) throws UnsupportedEncodingException{
//		if(file!=null){
//			String ny = (conditions.containsKey("stYearMonth")&&conditions.get("stYearMonth")!=null&&StringUtils.isNotBlank(conditions.get("stYearMonth").toString()))?conditions.get("stYearMonth").toString():DateUtil.getStrDate(new Date(), "yyyy-MM");
////			市局用户需要制定，各警种民警按照用户角色设置类型
//			CommonsMultipartFile cmf= (CommonsMultipartFile)file;
//			modelService.insertModelWithAttach(request, ny, conditions.get("stFileType").toString(), conditions.get("stType").toString(), cmf);
//			if(conditions.containsKey("stType")&&conditions.get("stType").toString().equalsIgnoreCase("statics")){modelService.impExcel(cmf, ny);}
			return "success";
//		}else{
//			return "fail";
//		}
	}
	
	@RequestMapping("/downLoadModel.do")
	public String downLoadModel(HttpServletRequest request,HttpServletResponse response,
			@RequestParam HashMap<String, Object> conditions,Map<String, Object> model,String flag) throws UnsupportedEncodingException{
//		String id = conditions.get("id").toString();
//		if(StringUtils.isNotBlank(id)){
//			ModelBo modelBo = (ModelBo)commonService.find(ModelBo.class, id.trim());
////			byte[]  content = FileUtil.getFileBytes(modelBo.getStFilePath());
//			byte[]  content = modelBo.getBlContent();
//			FileUtil.createFileFlowForDownLoad(modelBo.getStFileName(), content, response);
//		}
		return null;
	}
	
	/***
	 * 连同区县用户的评论信息删除市局上报的绩效数据汇总文件
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteFileOfSj.do")
	public String deleteFileOfSj(HttpServletRequest request,HttpServletResponse response,
			@RequestParam HashMap<String, Object> conditions,Map<String, Object> model){
		String id     = (conditions.containsKey("id"))?conditions.get("id").toString().trim():"";
//		String result = modelService.deleDatasFileWithJudges(id);
		String result = "";
		return result;
	}
	
}
