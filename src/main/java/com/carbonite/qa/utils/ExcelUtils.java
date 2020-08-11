package com.carbonite.qa.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.carbonite.qa.base.TestBase;

public class ExcelUtils extends TestBase {

	public static FileInputStream fi;
	public static FileOutputStream fo;

	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;

	public static int getRowCount(String xFile, String xSheet) throws Exception {

		int rowCount;

		fi = new FileInputStream(xFile);

		wb = new XSSFWorkbook(fi);

		ws = wb.getSheet(xSheet);

		rowCount = ws.getLastRowNum();

		wb.close();

		fi.close();

		return rowCount;

	}

	public static int getCellCount(String xFile, String xSheet, int rowno) throws IOException {

		int colCount;

		fi = new FileInputStream(xFile);

		wb = new XSSFWorkbook(fi);

		ws = wb.getSheet(xSheet);

		row = ws.getRow(rowno);

		colCount = row.getLastCellNum();

		wb.close();

		fi.close();

		return colCount;

	}

	@SuppressWarnings("deprecation")
	public static String getCellData(String xFile, String xSheet, int rowno, int colno) throws IOException {

		String data;

		fi = new FileInputStream(xFile);

		wb = new XSSFWorkbook(fi);

		ws = wb.getSheet(xSheet);

		row = ws.getRow(rowno);
		cell = row.getCell(colno);

		cell.setCellType(cell.CELL_TYPE_STRING);

		data = cell.getStringCellValue();

		return data;

	}

}
