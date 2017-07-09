package com.edubean.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

/**
 * 处理关于Excel导入导出的工具类
 * @author Dylan
 * @date   2016-01-05
 */
public class ExcelTool{
	
	private HSSFCellStyle style_title;
	private HSSFFont font_title;
	private HSSFCellStyle style_cell;
	private HSSFFont font_cell;
	private HSSFWorkbook workbook;
	
//	导入
	private String sheetName;
	private int sheetNumber;
	private int rowNumber;
//	private HSSFWorkbook workbook;
	private HSSFSheet sheet;
	private HSSFRow row;
//	private FormulaEvaluator evaluator;
	
	public  ExcelTool() {
		workbook = new HSSFWorkbook();   
		style_title = workbook.createCellStyle();
		style_cell = workbook.createCellStyle();
		font_title = workbook.createFont();
		font_cell = workbook.createFont();
	}
	
	/**
	 * 导出文件到指定文件路径 filePath 例如 D:/home/ 或  D:/home
	 * 1. 对于List中是JavaBean的则应该提供JavaBean的Class以及各个需要显示的列，列可以通过@@()符号进行加工；
	 * 2. 对于List中是Object数组的(通常这是直接由SQL查询得到的结果)那么仅仅需要数据源和随便什么列名(例如1,2,3等，但是一定要一一对应齐)，列所得的值可以通过@@()进行加工；
	 * @param fileName         导出文件名称
	 * @param sheetName        电子表sheet名称
	 * @param headers          表头各列名称
	 * @param columns          各列名称（对于list<Object[]>）不知道列名的随便什么名称，但是一定要数量与object[]一致逗号隔开
	 * @param dataset          数据源  list<Object> or List<Object[]>
	 * @param entityClass      如果是 list<Object则指定其JavaBean类
	 * @param wholePath        指定的文件路径
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void exportExcelFileToPath(String fileName, String sheetName, String[] headers, String[] columns, Collection dataset, Class<?> entityClass,String filePath){
//		获取DataSet中的数据赋值给excel
		if(dataset!=null&&dataset instanceof List<?>){
			List<Object[]> objArrList = ((List<Object[]>)dataset);
			List<Object>      objList = ((List<Object>)dataset);
			if(objArrList.get(0)!=null && objArrList.get(0) instanceof Object[]){
				this.exportExcelByObjArrList(sheetName, headers, columns, objArrList, entityClass);
			}else{
				this.exportExcelByObjList(sheetName, headers, columns, objList, entityClass);
			}
		}
		
//		导出文件
		FileOutputStream fos;
		try {
			if(StringUtils.isNotBlank(filePath)){
				filePath = (filePath +"/"+fileName.trim() + ".xls").trim().replaceAll("//", "/");
				fos = new FileOutputStream(filePath);
				workbook.write(fos); 
				fos.close(); 
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * 生成流，直接提供下载excel文件
	 * 1. 对于List中是JavaBean的则应该提供JavaBean的Class以及各个需要显示的列，列可以通过@@()符号进行加工；
	 * 2. 对于List中是Object数组的(通常这是直接由SQL查询得到的结果)那么仅仅需要数据源和随便什么列名(例如1,2,3等，但是一定要一一对应齐)，列所得的值可以通过@@()进行加工；
	 * @param fileName         导出文件名称
	 * @param sheetName        电子表sheet名称
	 * @param headers          表头各列名称
	 * @param columns          各列名称（对于list<Object[]>）不知道列名的随便什么名称，但是一定要数量与object[]一致逗号隔开
	 * @param dataset          数据源  list<Object> or List<Object[]>
	 * @param entityClass      如果是 list<Object则指定其JavaBean类
	 * @param response         页面对象
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void createExcelFlowForDownLoad(String fileName, String sheetName, String[] headers, String[] columns, Collection dataset, Class<?> entityClass, HttpServletResponse response){
		if(dataset!=null&&dataset instanceof List<?>){
			List<Object[]> objArrList = ((List<Object[]>)dataset);
			List<Object>      objList = ((List<Object>)dataset);
			if(objArrList.get(0)!=null && objArrList.get(0) instanceof Object[]){
				this.exportExcelByObjArrList(sheetName, headers, columns, objArrList, entityClass);
			}else{
				this.exportExcelByObjList(sheetName, headers, columns, objList, entityClass);
			}
		}
        
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		BufferedInputStream bis  = null;
        BufferedOutputStream bos = null;
        InputStream is           = null;    
		try {
			workbook.write(os);
			byte[] content = os.toByteArray();
			is = new ByteArrayInputStream(content);
		
			response.reset();
	        response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName.toString() + ".xls").getBytes(), "iso-8859-1"));
	        ServletOutputStream out = response.getOutputStream();
			
	        bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))){
                bos.write(buff, 0, bytesRead);
            }
		} catch (IOException e1) {
			e1.printStackTrace();
		}finally {
			if (bis != null){
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
            if (bos != null){
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
            if (is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
            if (os != null){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        }
	}
	
	/**
	 * 这是一个通用的方法，利用了JAVA的反射机制，抽取JAVA ListObj中的数据进行加工[如果有@@()符号的话]并赋值给电子表单元格；
	 * 1. 如果有时间则统一的用"yyyy-MM-dd";
	 * 2. 如果有二进制码流则都用图片显示出来；
	 * @param title 表格标题名
	 * @param headers 表格属性列名数组
	 * @param columns 需要抽取的列
	 * @param listObj 需要显示的javaBean的List
	 */
	public void exportExcelByObjList(String title, String[] headers,String[] columns, List<Object> listObj, Class<?> entityClass){
//		建立sheet，初始化单元格宽度，表头样式、主体单元格样式；
		HSSFSheet sheet = workbook.createSheet(title);
		sheet.setDefaultColumnWidth((short) 25);
		HSSFCellStyle headerCellStyle = this.getHeaderCellStyle();
		HSSFCellStyle bodyCellStyle   = this.getBodyCellStyle();
		
		// 声明顶级画图管理器、定义注释的大小、位置、内容、作者
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,0, 0, 0, (short) 4, 2, (short) 6, 5));
		comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
		comment.setAuthor("leno");
		
		// 表格头部赋值
		HSSFRow row = sheet.createRow(0);
		for(short i = 0; i < headers.length; i++){
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(headerCellStyle);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}
		
		// 表格主体单元格赋值遍历集合数据，产生数据行 [反射机制获取相应列属性的值]
		if(listObj!=null&&listObj.size()>0){
			for(int index=0;index<listObj.size();index++){
				row = sheet.createRow(index+1);
				Object bean = entityClass.cast(listObj.get(index));
				
				for(short i = 0; i < columns.length; i++){
					HSSFCell cell = row.createCell(i);
					cell.setCellStyle(bodyCellStyle);
					
//					1.获取初始列名 
//					2.对列名的初步处理[对字符'@@()处理方法名的抽取'(如果有的话)]
//					3.根据列名以及t获取属性值[如果有'@@()'的话对属性值进行再次的加工]
					String fieldName = columns[i];
					HashMap<String, String> cmpMap = this.getClassMethodFieldName(fieldName);
					Object valuen = this.getFiledValueByMapFromBean(entityClass, bean, cmpMap);
					
//					1.值为码流时进行画图；
//					2.值为其他时进行cell赋值；
					if(valuen!=null && valuen instanceof byte[]){
						// 为图片时设置行高60px，列宽80px,注意这里单位的一个换算
						row.setHeightInPoints(60);
						sheet.setColumnWidth(i, (short) (35.7 * 80));
						byte[] bsValue = (byte[]) valuen;
						HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,1023, 255, (short) i, index+1, (short) i, index+1);
						anchor.setAnchorType(2);
						patriarch.createPicture(anchor, workbook.addPicture(bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
					}else{
						cell=this.setValuetoCell(cell, valuen);
					}
				}
			}
		}
	}
	
	/**
	 * 这是一个通用的方法，利用了JAVA的反射机制，抽取JAVA ListObj[]中的数据进行加工[如果有@@()符号的话]并赋值给电子表单元格；
	 * 1. 如果有时间则统一的用"yyyy-MM-dd";
	 * 2. 如果有二进制码流则都用图片显示出来；
	 * @param title 表格标题名
	 * @param headers 表格属性列名数组
	 * @param listObj[] 需要显示的javaBean的List
	 */
	public void exportExcelByObjArrList(String title, String[] headers,String[] columns, List<Object[]> objArrList, Class<?> entityClass){
//		建立sheet，初始化单元格宽度，表头样式、主体单元格样式；
		HSSFSheet sheet = workbook.createSheet(title);
		sheet.setDefaultColumnWidth((short) 25);
		HSSFCellStyle headerCellStyle = this.getHeaderCellStyle();
		HSSFCellStyle bodyCellStyle   = this.getBodyCellStyle();
		
		// 声明顶级画图管理器、定义注释的大小、位置、内容、作者
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,0, 0, 0, (short) 4, 2, (short) 6, 5));
		comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
		comment.setAuthor("leno");
		
		// 表格头部赋值
		HSSFRow row = sheet.createRow(0);
		for(short i = 0; i < headers.length; i++){
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(headerCellStyle);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}
		
		// 表格主体单元格赋值遍历集合数据，产生数据行 [反射机制获取相应列属性的值]
		if(objArrList!=null&&objArrList.size()>0){
			for(int index=0;index<objArrList.size();index++){
				row = sheet.createRow(index+1);
				Object[] valArr = objArrList.get(index);
				
				for(short i = 0; i < columns.length; i++){
					HSSFCell cell = row.createCell(i);
					cell.setCellStyle(bodyCellStyle);
					
//					1.获取初始列名 
//					2.对列名的初步处理[对字符'@@()处理方法名的抽取'(如果有的话)]
//					3.根据列名以及t获取属性值[如果有'@@()'的话对属性值进行再次的加工]
					String fieldName = columns[i];
					HashMap<String, String> cmpMap = this.getClassMethodFieldName(fieldName);
					Object valuen = this.getFiledValueByMapFromBean(null, valArr[i], cmpMap);
					
//					1.值为码流时进行画图；
//					2.值为其他时进行cell赋值；
					if(valuen!=null && valuen instanceof byte[]){
						// 为图片时设置行高60px，列宽80px,注意这里单位的一个换算
						row.setHeightInPoints(60);
						sheet.setColumnWidth(i, (short) (35.7 * 80));
						byte[] bsValue = (byte[]) valuen;
						HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,1023, 255, (short) i, index+1, (short) i, index+1);
						anchor.setAnchorType(2);
						patriarch.createPicture(anchor, workbook.addPicture(bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
					}else{
						cell=this.setValuetoCell(cell, valuen);
					}
				}
			}
		}
	}
	
	/**
	 * 获取数据源解析出来的某属性值赋值给电子表单元格
	 * @param cell       单元格
	 * @param param      属性值
	 * @return HSSFCell  组装之后的单元格      
	 */
	@SuppressWarnings("deprecation")
	private HSSFCell setValuetoCell(HSSFCell cell,Object param){
		try {
			if (param instanceof Integer) {
			    int in = ((Integer) param).intValue();
			    cell.setCellValue(in);
			} else if (param instanceof String) {
			    String s = (String) param;
			    cell.setCellValue(s);
			} else if (param instanceof Double) {
			    double d = ((Double) param).doubleValue();
			    cell.setCellValue(d);
			} else if (param instanceof Float) {
			    float f = ((Float) param).floatValue();
			    cell.setCellValue(f);
			} else if (param instanceof Long) {
			    long l = ((Long) param).longValue();
			    cell.setCellValue(l);
			} else if (param instanceof Boolean) {
			    boolean b = ((Boolean) param).booleanValue();
			    cell.setCellValue(b);
			} else if (param instanceof Date){
				 Date d = (Date) param;
				 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				 cell.setCellValue(sdf.format(d));
			} else if(param!=null){
				cell.setCellValue(String.valueOf(param));
			}else{
				cell.setCellValue("");
			}
		} catch (Exception e) {
			cell.setCellValue("");
		}
		return cell;
	}
	
	@SuppressWarnings("deprecation")
	public static String getStringValueOfCell(HSSFCell cell){
		String result = "";
		if(cell!=null){
			if(cell.getCellType()==1){
				result = cell.getStringCellValue();
			}else if(cell.getCellType()==0){
				result = cell.getNumericCellValue()+"";
			}else if(cell.getCellType()==3){
				result = cell.getRichStringCellValue().toString();
			}
		}
		return result;
	}
	
	/**
	 * 改造原始的列名为类名、方法名、列名[如果没有@@()符号则直接获取列名]
	 * @param cell       单元格
	 * @param param      属性值
	 * @return HSSFCell  组装之后的单元格      
	 */
	private HashMap<String, String> getClassMethodFieldName(String oldfieldName){
		HashMap<String, String> cmfMap = new HashMap<String, String>();
		if(StringUtils.isNotBlank(oldfieldName)){
			if(oldfieldName.indexOf("@")>-1&&oldfieldName.indexOf("(")>-1&&oldfieldName.indexOf(")")>-1){
				String className = StringUtils.substringBetween(oldfieldName,"@", "@");
				String methodName= StringUtils.substringBetween(oldfieldName,"@", "(").replaceAll("@", "").replace(className, "").trim();
				String fieldName = StringUtils.substringBetween(oldfieldName,"(", ")");
				cmfMap.put("className", className);
				cmfMap.put("methodName", methodName);
				cmfMap.put("fieldName", fieldName);
			}else{
				cmfMap.put("fieldName", oldfieldName.trim());
			}
		}
		
		return cmfMap;
	}
	
	/**
	 * 获取列对应的值。如果是带有@@()符号的列那么获取值之后再对其进行处理。如果是图片路径则返回其码流
	 * @param entityClass 如果有的话那么必须进行bean的属性获取解析，如果为null则bean取自list<Object[]>中的object[i]已经是单元格可赋值的状态
	 * @param cmfMap      包含类、方法、属性名称的Map
	 * @return bean       吸取bean中的属性值，如果Map中有加工类和方法，那么对entityClass中该属性对应的值进行加工之后返回，如果没有entityClass则对现成的bean直接进行加工后返回      
	 */
	private Object getFiledValueByMapFromBean(Class<?> entityClass, Object bean, HashMap<String,String> cmfMap){
		Object resuletObj = null;
		if(cmfMap!=null&&cmfMap.size()>1){
			String className = cmfMap.get("className");
			String methodName= cmfMap.get("methodName");
			String fieldName = cmfMap.get("fieldName");
			
			if(entityClass!=null){
				String getMethodName = "get"+ fieldName.substring(0, 1).toUpperCase()+ fieldName.substring(1);
				Method getMethod = null;
				try {
					getMethod = entityClass.getMethod(getMethodName,new Class[] {});
					resuletObj = getMethod.invoke(bean, new Object[] {});
					if(StringUtils.isNotBlank(className)&&StringUtils.isNotBlank(methodName)&&StringUtils.isNotBlank(fieldName)){
						resuletObj = this.getResultByClassMethodParam(className, methodName, resuletObj);
					}
				} catch (NoSuchMethodException e1) {
					e1.printStackTrace();
				} catch (SecurityException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}else{
				resuletObj = this.getResultByClassMethodParam(className, methodName, bean);
			}
		}else{
			if(entityClass!=null){
				String fieldName = cmfMap.get("fieldName");
				String getMethodName = "get"+ fieldName.substring(0, 1).toUpperCase()+ fieldName.substring(1);
				Method getMethod = null;
				try {
					getMethod = entityClass.getMethod(getMethodName,new Class[] {});
					resuletObj = getMethod.invoke(bean, new Object[] {});
				} catch (NoSuchMethodException e1) {
					e1.printStackTrace();
				} catch (SecurityException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}else{
				resuletObj = bean;
			}
		}
		
		return resuletObj;
	}
	
	/**
	 * 通过java反射机制获取某类中某方法加工某参数的值
	 * @param className
	 * @param methodName
	 * @param paramValue
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Object getResultByClassMethodParam(String className, String methodName, Object paramValue){
		Object value = null;
		try {
			if(StringUtils.isNotBlank(className)&&StringUtils.isNotBlank(methodName)){
				Object object = Class.forName(className).newInstance();
				Class clazz   = object.getClass();
				Method getMethod = clazz.getMethod(methodName,Object.class);
				value = getMethod.invoke(object, paramValue);
			}
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
		} catch (SecurityException e1) {
			e1.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return value;
	}
	
	/**
	 * 组装电子表表头样式
	 * @return HSSFCellStyle HeaderCellStyle
	 */
	private HSSFCellStyle getHeaderCellStyle(){
		HSSFCellStyle style1 = workbook.createCellStyle();
		style1.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		style1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style1.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style1.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont font1 = workbook.createFont();
		font1.setColor(HSSFColor.VIOLET.index);
		font1.setFontHeightInPoints((short) 12);
		font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		style1.setFont(font1);
		return style1;
	}
	
	/**
	 * 组装电子表单元格样式
	 * @return HSSFCellStyle BodyCellStyle
	 */
	private HSSFCellStyle getBodyCellStyle(){
		HSSFCellStyle style2 = workbook.createCellStyle();
		style2.setFillForegroundColor(HSSFColor.WHITE.index);
		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFFont font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		style2.setFont(font2);
		return style2;
	}
	
	/**
	 * 将InputStream转化为HSSFSheet
	 * @param is
	 * @return
	 */
	public static List<HSSFSheet> transExcelFileToSheet(InputStream is){
		List<HSSFSheet> sheetsList = new ArrayList<HSSFSheet>();
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(is);
			for(int i=0;i<workbook.getNumberOfSheets();i++){
				HSSFSheet sheet = workbook.getSheetAt(i);
				sheetsList.add(sheet);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sheetsList;
	}
	
	public HSSFCellStyle getStyle_title() {
		return style_title;
	}
	
	public HSSFFont getFont_title() {
		return font_title;
	}

	public HSSFCellStyle getStyle_cell() {
		return style_cell;
	}

	public HSSFFont getFont_cell() {
		return font_cell;
	}
}
