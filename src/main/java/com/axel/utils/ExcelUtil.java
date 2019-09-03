package com.axel.utils;

import com.axel.bean.Field;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.util.List;


/**
 * create or modify excel files
 *
 * @author chenzhaohui
 * @date 2019/9/2
 */
public final class ExcelUtil {


	public static HSSFWorkbook create(List data, List<Field> fields, String sheetName) {

		if (data == null || data.isEmpty()) {
			throw new RuntimeException("data 不能为空");
		}
		if (fields == null || fields.isEmpty()) {
			throw new RuntimeException("fields 不能为空");
		}
		sheetName = StringUtils.isBlank(sheetName) ? "sheet1" : sheetName;
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(sheetName);
		HSSFRow row = sheet.createRow(0);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		HSSFCell cell;
		for (int i = 0; i < fields.size(); i++) {
			if (fields.get(i).getValid()) {
				cell = row.createCell(i);
				cell.setCellValue(fields.get(i).getName());
				cell.setCellStyle(style);
			}
		}
		for (int i = 0; i < data.size(); i++) {
			row = sheet.createRow(i + 1);
			Object object = data.get(i);
			for (int j = 0; j < fields.size(); j++) {
				try {
					String fieldName = fields.get(j).getValue();
					java.lang.reflect.Field field = object.getClass().getDeclaredField(fieldName);
					//设置对象的访问权限，保证对private的属性的访问
					field.setAccessible(true);
					String value = String.valueOf(field.get(object));
					row.createCell(j).setCellValue(value);
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return wb;
	}
}
