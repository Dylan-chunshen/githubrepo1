package platform.utils;



import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ExcelExportor extends HttpServlet {

	private static final long serialVersionUID = -825770153435407989L;

	private WritableWorkbook wwb = null;
	
	private WritableSheet sheet = null;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		request.setCharacterEncoding("UTF-8");
		String content = request.getParameter("c");
		if (content == null) {
			Writer out = response.getWriter();
			response.setCharacterEncoding("UTF-8");
			out.write("<html><body>No Content</body></html>");
			return;
		}
		try {
			export(content, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void export(String content, HttpServletResponse response)
			throws IOException, RowsExceededException, WriteException {
		response.setContentType("application/ms-excel");
		String sheetName = getCaption(content);
		if (sheetName == null) {
			sheetName = "Sheet1";
		}
		sheetName = sheetName.replaceAll(":", "").replaceAll("[)]", "")
				.replaceAll("[(]", "");
		/*response.addHeader("Content-Disposition", "attachment; filename="
				+ new String(sheetName.getBytes("UTF-8"), "ISO-8859-1")
				+ ".xls");*/
		response.addHeader("Content-Disposition", "attachment; filename="
				+ new String(sheetName.getBytes(), "ISO-8859-1")
				+ ".xls");
		OutputStream os = response.getOutputStream();
		wwb = Workbook.createWorkbook(os);
		wwb.setProtected(false);
		sheet = wwb.createSheet(sheetName, 0);

		// 设置字体等内容
		WritableFont wf = new WritableFont(WritableFont.ARIAL, 10);
		WritableCellFormat wcf = new WritableCellFormat(wf);
		wcf.setAlignment(Alignment.CENTRE); // 水平居中
		wcf.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直居中
		wcf.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);

		int row = 0;
		int col = 0;
		Label label = null;
		if (sheetName.trim().length() > 30) {
			label = new Label(col, row, sheetName);
			sheet.addCell(label);
			row++;
		}
		List<TD> listBody = getContent(content);
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		for (TD td : listBody) {
			if (td == null) {
				row++;
				col = 0;
				continue;
			}
			while (map.get(col + "-" + row) != null) {
				col++;
			}
			if (td.colspan > 1 || td.rowspan > 1) {
				sheet.mergeCells(col, row, col + td.colspan - 1, row
						+ td.rowspan - 1);
				for (int i = col; i <= col + td.colspan - 1; i++) {
					for (int j = row; j <= row + td.rowspan - 1; j++) {
						map.put(i + "-" + j, true);
					}
				}
			}
			sheet.setColumnView(col, 10);//update by lixinhai on 2015年1月16日14:11:21  修改导出Excel的列宽，25改成10
			label = new Label(col, row, td.content, wcf);
			sheet.addCell(label);
			map.put(col + "-" + row, true);
			col += td.colspan;
		}
		wwb.write();
		wwb.close();
	}

	private String getCaption(String content) {
		int begin = content.indexOf("<caption");
		int end = content.indexOf("</caption>");
		if (begin == -1 || end == -1) {
			return null;
		}
		begin = content.indexOf(">", begin);
		if (begin == -1) {
			return null;
		}
		return content.substring(begin + 1, end);
	}

	public List<TD> getContent(String content)
			throws UnsupportedEncodingException {
		int begin = -1;
		int end = -1;
		int index = -1;
		String numberStr;
		int number;
		String[] tables = content.split("</table>");
		List<TD> list = new ArrayList<TD>();
		for (String table : tables) {
			// 先分thead和tbody以区别th和td
			String[] theads = table.split("</thead>");
			int count = 1;
			for (String thead : theads) {
				if (count == 1) {
					String[] trs = thead.split("</tr>");
					for (String tr : trs) {
						if(tr.indexOf("<tr>") == -1){
							continue;
						}
						number = 1;
						String[] ss = tr.split("</th>");
						for (String s : ss) {
							begin = s.indexOf("<th");
							if (begin == -1) {
								continue;
							}
							s = s.substring(begin + 3);
							index = s.indexOf(">");
							TD td = new TD();
							begin = s.indexOf("rowspan=");
							if (begin != -1) {
								end = s.indexOf(" ", begin);
								if (end == -1) {
									end = index;
								}
								numberStr = s.substring(begin + 8, end)
										.replace('"', ' ').replace('\'', ' ')
										.trim();
								number = Integer.parseInt(numberStr);
								td.rowspan = number;
							}
							begin = s.indexOf("colspan=");
							if (begin != -1) {
								end = s.indexOf(" ", begin);
								index = s.indexOf(">", begin);
								if (end == -1) {
									end = index;
								}
								if (end > index) {
									end = index;
								}
								numberStr = s.substring(begin + 8, end)
										.replace('"', ' ').replace('\'', ' ')
										.trim();
								number = Integer.parseInt(numberStr);
								td.colspan = number;
							}
							td.content = s.substring(index + 1)
									.replaceAll("\\<.*?\\>", "")
									.replaceAll(" ", "").trim();
							list.add(td);
						}
						list.add(null);
					}
					count++;
				} else {
					String[] trs = thead.split("</tr>");
					for (String tr : trs) {
						if(tr.indexOf("<tr>") == -1){
							continue;
						}
						number = 1;
						String[] ss = tr.split("</td>");
						for (String s : ss) {
							begin = s.indexOf("<td");
							if (begin == -1) {
								continue;
							}
							s = s.substring(begin + 3);
							index = s.indexOf(">");
							TD td = new TD();
							begin = s.indexOf("rowspan=");
							if (begin != -1) {
								end = s.indexOf(" ", begin);
								if (end == -1) {
									end = index;
								}
								numberStr = s.substring(begin + 8, end)
										.replace('"', ' ').replace('\'', ' ')
										.trim();
								number = Integer.parseInt(numberStr);
								td.rowspan = number;
							}
							begin = s.indexOf("colspan=");
							if (begin != -1) {
								end = s.indexOf(" ", begin);
								index = s.indexOf(">", begin);
								if (end == -1) {
									end = index;
								}
								if (end > index) {
									end = index;
								}
								numberStr = s.substring(begin + 8, end)
										.replace('"', ' ').replace('\'', ' ')
										.trim();
								number = Integer.parseInt(numberStr);
								td.colspan = number;
							}
							td.content = s.substring(index + 1)
									.replaceAll("\\<.*?\\>", "")
									.replaceAll(" ", "").trim();
							list.add(td);
						}
						list.add(null);
					}
				}
			}
		}
		return list;
	}

	class TD {
		int rowspan = 1;
		int colspan = 1;
		String content;
	}
	
	public void test() {
		System.out.println("我就试试能不能直接注入");
	}

}
