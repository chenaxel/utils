package com.axel.test;

import com.axel.bean.Field;
import com.axel.utils.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2019/9/3
 */
public class ExcelUtilTest {

	@Test
	public void create() {

		List<Field> columns = Arrays.stream(new Field[]{new Field().setName("id").setValue("id").setValid(true), new Field().setName("项目").setValue("name").setValid(true)}).map(item -> new Field().setName(item.getName()).setValue(item.getValue()).setValid(item.getValid())).collect(Collectors.toList());
		List<TestBean> data = new ArrayList<>();
		data.add(new TestBean().setId("1").setName("项目1"));
		data.add(new TestBean().setId("2").setName("项目2"));
		HSSFWorkbook workbook = ExcelUtil.create(data, columns, "sheet");
		try {
			workbook.write(new FileOutputStream("d://data//tempFile//testExcel.xls"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}