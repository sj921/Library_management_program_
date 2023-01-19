package lmp.util;
/*
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
public class ExcelIO {
	public static void main(String[] args) {
		String path = "C:\\javaFullStack_LDH\\repos\\Library_Management_Program\\examplefiles\\"; // 파일 경로 설정
		String filename = "BookList1.xls"; // 파일명 설정

		writeText(readExcel(path, filename));
	}

	public static StringBuilder readExcel(String path, String filename) {
		StringBuilder booklist = new StringBuilder();
		try {
			FileInputStream file = new FileInputStream(path + filename);
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			NumberFormat f = NumberFormat.getInstance();
			f.setGroupingUsed(false); // 지수로 안나오게 설정

			// 시트 갯수
			int sheetNum = workbook.getNumberOfSheets();

			for (int s = 0; s < sheetNum; s++) {
				HSSFSheet sheet = workbook.getSheetAt(s);
				// 행 갯수
				int rows = sheet.getPhysicalNumberOfRows();

				for (int r = 0; r < rows; r++) {
					HSSFRow row = sheet.getRow(r);

					int cells = row.getPhysicalNumberOfCells();

					System.out.print("/" + r + "/");
					booklist.append("/" + r + "/");
					for (int c = 0; c < cells; c++) {
						HSSFCell cell = row.getCell(c);

						String value = "";
						if (cell != null) {
							// 타입 체크
							switch (cell.getCellType()) {
							case STRING:
								value = cell.getStringCellValue();
								break;
							case NUMERIC:
								value = f.format(cell.getNumericCellValue()) + "";
								break;
							case BLANK:
								value = cell.getBooleanCellValue() + "";
								break;
							case ERROR:
								value = cell.getErrorCellValue() + "";
								break;
							}
						}
						System.out.print("" + value + "/");
						booklist.append(value + "/");
					}
					System.out.println();
					booklist.append("\n");
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return booklist;
	}

	public static void writeText(StringBuilder booklist) {
		File file = new File("C:\\javaFullStack_LDH\\repos\\Library_Management_Program\\examplefiles\\BookList1.txt");
		try (
				FileWriter fout = new FileWriter(file); 
				BufferedWriter out = new BufferedWriter(fout);
			) {
			out.write(booklist.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
*/

