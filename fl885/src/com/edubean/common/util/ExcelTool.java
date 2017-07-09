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
 * �������Excel���뵼���Ĺ�����
 * @author Dylan
 * @date   2016-01-05
 */
public class ExcelTool{
	
	private HSSFCellStyle style_title;
	private HSSFFont font_title;
	private HSSFCellStyle style_cell;
	private HSSFFont font_cell;
	private HSSFWorkbook workbook;
	
//	����
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
	 * �����ļ���ָ���ļ�·�� filePath ���� D:/home/ ��  D:/home
	 * 1. ����List����JavaBean����Ӧ���ṩJavaBean��Class�Լ�������Ҫ��ʾ���У��п���ͨ��@@()���Ž��мӹ���
	 * 2. ����List����Object�����(ͨ������ֱ����SQL��ѯ�õ��Ľ��)��ô������Ҫ����Դ�����ʲô����(����1,2,3�ȣ�����һ��Ҫһһ��Ӧ��)�������õ�ֵ����ͨ��@@()���мӹ���
	 * @param fileName         �����ļ�����
	 * @param sheetName        ���ӱ�sheet����
	 * @param headers          ��ͷ��������
	 * @param columns          �������ƣ�����list<Object[]>����֪�����������ʲô���ƣ�����һ��Ҫ������object[]һ�¶��Ÿ���
	 * @param dataset          ����Դ  list<Object> or List<Object[]>
	 * @param entityClass      ����� list<Object��ָ����JavaBean��
	 * @param wholePath        ָ�����ļ�·��
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void exportExcelFileToPath(String fileName, String sheetName, String[] headers, String[] columns, Collection dataset, Class<?> entityClass,String filePath){
//		��ȡDataSet�е����ݸ�ֵ��excel
		if(dataset!=null&&dataset instanceof List<?>){
			List<Object[]> objArrList = ((List<Object[]>)dataset);
			List<Object>      objList = ((List<Object>)dataset);
			if(objArrList.get(0)!=null && objArrList.get(0) instanceof Object[]){
				this.exportExcelByObjArrList(sheetName, headers, columns, objArrList, entityClass);
			}else{
				this.exportExcelByObjList(sheetName, headers, columns, objList, entityClass);
			}
		}
		
//		�����ļ�
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
	 * ��������ֱ���ṩ����excel�ļ�
	 * 1. ����List����JavaBean����Ӧ���ṩJavaBean��Class�Լ�������Ҫ��ʾ���У��п���ͨ��@@()���Ž��мӹ���
	 * 2. ����List����Object�����(ͨ������ֱ����SQL��ѯ�õ��Ľ��)��ô������Ҫ����Դ�����ʲô����(����1,2,3�ȣ�����һ��Ҫһһ��Ӧ��)�������õ�ֵ����ͨ��@@()���мӹ���
	 * @param fileName         �����ļ�����
	 * @param sheetName        ���ӱ�sheet����
	 * @param headers          ��ͷ��������
	 * @param columns          �������ƣ�����list<Object[]>����֪�����������ʲô���ƣ�����һ��Ҫ������object[]һ�¶��Ÿ���
	 * @param dataset          ����Դ  list<Object> or List<Object[]>
	 * @param entityClass      ����� list<Object��ָ����JavaBean��
	 * @param response         ҳ�����
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
	 * ����һ��ͨ�õķ�����������JAVA�ķ�����ƣ���ȡJAVA ListObj�е����ݽ��мӹ�[�����@@()���ŵĻ�]����ֵ�����ӱ�Ԫ��
	 * 1. �����ʱ����ͳһ����"yyyy-MM-dd";
	 * 2. ����ж�������������ͼƬ��ʾ������
	 * @param title ��������
	 * @param headers ���������������
	 * @param columns ��Ҫ��ȡ����
	 * @param listObj ��Ҫ��ʾ��javaBean��List
	 */
	public void exportExcelByObjList(String title, String[] headers,String[] columns, List<Object> listObj, Class<?> entityClass){
//		����sheet����ʼ����Ԫ���ȣ���ͷ��ʽ�����嵥Ԫ����ʽ��
		HSSFSheet sheet = workbook.createSheet(title);
		sheet.setDefaultColumnWidth((short) 25);
		HSSFCellStyle headerCellStyle = this.getHeaderCellStyle();
		HSSFCellStyle bodyCellStyle   = this.getBodyCellStyle();
		
		// ����������ͼ������������ע�͵Ĵ�С��λ�á����ݡ�����
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,0, 0, 0, (short) 4, 2, (short) 6, 5));
		comment.setString(new HSSFRichTextString("������POI�����ע�ͣ�"));
		comment.setAuthor("leno");
		
		// ���ͷ����ֵ
		HSSFRow row = sheet.createRow(0);
		for(short i = 0; i < headers.length; i++){
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(headerCellStyle);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}
		
		// ������嵥Ԫ��ֵ�����������ݣ����������� [������ƻ�ȡ��Ӧ�����Ե�ֵ]
		if(listObj!=null&&listObj.size()>0){
			for(int index=0;index<listObj.size();index++){
				row = sheet.createRow(index+1);
				Object bean = entityClass.cast(listObj.get(index));
				
				for(short i = 0; i < columns.length; i++){
					HSSFCell cell = row.createCell(i);
					cell.setCellStyle(bodyCellStyle);
					
//					1.��ȡ��ʼ���� 
//					2.�������ĳ�������[���ַ�'@@()���������ĳ�ȡ'(����еĻ�)]
//					3.���������Լ�t��ȡ����ֵ[�����'@@()'�Ļ�������ֵ�����ٴεļӹ�]
					String fieldName = columns[i];
					HashMap<String, String> cmpMap = this.getClassMethodFieldName(fieldName);
					Object valuen = this.getFiledValueByMapFromBean(entityClass, bean, cmpMap);
					
//					1.ֵΪ����ʱ���л�ͼ��
//					2.ֵΪ����ʱ����cell��ֵ��
					if(valuen!=null && valuen instanceof byte[]){
						// ΪͼƬʱ�����и�60px���п�80px,ע�����ﵥλ��һ������
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
	 * ����һ��ͨ�õķ�����������JAVA�ķ�����ƣ���ȡJAVA ListObj[]�е����ݽ��мӹ�[�����@@()���ŵĻ�]����ֵ�����ӱ�Ԫ��
	 * 1. �����ʱ����ͳһ����"yyyy-MM-dd";
	 * 2. ����ж�������������ͼƬ��ʾ������
	 * @param title ��������
	 * @param headers ���������������
	 * @param listObj[] ��Ҫ��ʾ��javaBean��List
	 */
	public void exportExcelByObjArrList(String title, String[] headers,String[] columns, List<Object[]> objArrList, Class<?> entityClass){
//		����sheet����ʼ����Ԫ���ȣ���ͷ��ʽ�����嵥Ԫ����ʽ��
		HSSFSheet sheet = workbook.createSheet(title);
		sheet.setDefaultColumnWidth((short) 25);
		HSSFCellStyle headerCellStyle = this.getHeaderCellStyle();
		HSSFCellStyle bodyCellStyle   = this.getBodyCellStyle();
		
		// ����������ͼ������������ע�͵Ĵ�С��λ�á����ݡ�����
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,0, 0, 0, (short) 4, 2, (short) 6, 5));
		comment.setString(new HSSFRichTextString("������POI�����ע�ͣ�"));
		comment.setAuthor("leno");
		
		// ���ͷ����ֵ
		HSSFRow row = sheet.createRow(0);
		for(short i = 0; i < headers.length; i++){
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(headerCellStyle);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}
		
		// ������嵥Ԫ��ֵ�����������ݣ����������� [������ƻ�ȡ��Ӧ�����Ե�ֵ]
		if(objArrList!=null&&objArrList.size()>0){
			for(int index=0;index<objArrList.size();index++){
				row = sheet.createRow(index+1);
				Object[] valArr = objArrList.get(index);
				
				for(short i = 0; i < columns.length; i++){
					HSSFCell cell = row.createCell(i);
					cell.setCellStyle(bodyCellStyle);
					
//					1.��ȡ��ʼ���� 
//					2.�������ĳ�������[���ַ�'@@()���������ĳ�ȡ'(����еĻ�)]
//					3.���������Լ�t��ȡ����ֵ[�����'@@()'�Ļ�������ֵ�����ٴεļӹ�]
					String fieldName = columns[i];
					HashMap<String, String> cmpMap = this.getClassMethodFieldName(fieldName);
					Object valuen = this.getFiledValueByMapFromBean(null, valArr[i], cmpMap);
					
//					1.ֵΪ����ʱ���л�ͼ��
//					2.ֵΪ����ʱ����cell��ֵ��
					if(valuen!=null && valuen instanceof byte[]){
						// ΪͼƬʱ�����и�60px���п�80px,ע�����ﵥλ��һ������
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
	 * ��ȡ����Դ����������ĳ����ֵ��ֵ�����ӱ�Ԫ��
	 * @param cell       ��Ԫ��
	 * @param param      ����ֵ
	 * @return HSSFCell  ��װ֮��ĵ�Ԫ��      
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
	 * ����ԭʼ������Ϊ������������������[���û��@@()������ֱ�ӻ�ȡ����]
	 * @param cell       ��Ԫ��
	 * @param param      ����ֵ
	 * @return HSSFCell  ��װ֮��ĵ�Ԫ��      
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
	 * ��ȡ�ж�Ӧ��ֵ������Ǵ���@@()���ŵ�����ô��ȡֵ֮���ٶ�����д��������ͼƬ·���򷵻�������
	 * @param entityClass ����еĻ���ô�������bean�����Ի�ȡ���������Ϊnull��beanȡ��list<Object[]>�е�object[i]�Ѿ��ǵ�Ԫ��ɸ�ֵ��״̬
	 * @param cmfMap      �����ࡢ�������������Ƶ�Map
	 * @return bean       ��ȡbean�е�����ֵ�����Map���мӹ���ͷ�������ô��entityClass�и����Զ�Ӧ��ֵ���мӹ�֮�󷵻أ����û��entityClass����ֳɵ�beanֱ�ӽ��мӹ��󷵻�      
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
	 * ͨ��java������ƻ�ȡĳ����ĳ�����ӹ�ĳ������ֵ
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
	 * ��װ���ӱ��ͷ��ʽ
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
	 * ��װ���ӱ�Ԫ����ʽ
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
	 * ��InputStreamת��ΪHSSFSheet
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
