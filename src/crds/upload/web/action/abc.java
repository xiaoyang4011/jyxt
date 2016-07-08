package crds.upload.web.action;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.TimeZone;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

public class abc {
	
	@SuppressWarnings("deprecation")
	@Test
	public void write(){
		
		Workbook wb = new HSSFWorkbook();
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream("G:/POI/w12.xls");
			Sheet sheet = wb.createSheet("统计数据");
			    Row row = sheet.createRow((short) 0);
		        Cell cell = row.createCell((short) 0);
		        cell.setCellValue("中国石油大学胜利学院就业统计");
		        row =sheet.createRow((short)1);
		        row =sheet.createRow((short)2);

		        Row row1 = sheet.getRow(1);
		        Cell cell1 =row1.createCell((short)0);
		        cell1.setCellValue("招聘单位");
		        Cell cell2 = row1.createCell((short)1);
		        cell2.setCellValue("2009年");
		        Cell cell3 =row1.createCell((short)3);
		        cell3.setCellValue("2010年");
		        Cell cell4 =row1.createCell((short)5);
		        cell4.setCellValue("2011年");
		        Cell cell5 =row1.createCell((short)7);
		        cell5.setCellValue("2012年");
		        Cell cell6 =row1.createCell((short)9);
		        cell6.setCellValue("2013年");
		        Row row2 = sheet.getRow(2);
		        Cell c1 =row2.createCell((short)1);
		        c1.setCellValue("人数");
		        Cell c2 =row2.createCell((short)2);
		        c2.setCellValue("就业率");
		        Cell c3 =row2.createCell((short)3);
		        c3.setCellValue("人数");
		        Cell c4 =row2.createCell((short)4);
		        c4.setCellValue("就业率");
		        Cell c5 =row2.createCell((short)5);
		        c5.setCellValue("人数");
		        Cell c6 =row2.createCell((short)6);
		        c6.setCellValue("就业率");
		        Cell c7 =row2.createCell((short)7);
		        c7.setCellValue("人数");
		        Cell c8 =row2.createCell((short)8);
		        c8.setCellValue("就业率");
		        Cell c9 =row2.createCell((short)9);
		        c9.setCellValue("人数");
		        Cell c10 =row2.createCell((short)10);
		        c10.setCellValue("就业率");
		        //Row row1 = sheet.getRow(1);
		        //Cell cell1 = row1.createCell((short)3);
		        //cell1.setCellValue("good");
		        
		        sheet.addMergedRegion(new CellRangeAddress(
		                1, //first row (0-based)
		                2, //last row  (0-based)
		                0, //first column (0-based)
		                0 //last column  (0-based)
		        ));
		        sheet.addMergedRegion(new CellRangeAddress(0,0,0,9));
		        sheet.addMergedRegion(new CellRangeAddress(1,1,1,2));
		        sheet.addMergedRegion(new CellRangeAddress(1,1,3,4));
		        sheet.addMergedRegion(new CellRangeAddress(1,1,5,6));
		        sheet.addMergedRegion(new CellRangeAddress(1,1,7,8));
		        sheet.addMergedRegion(new CellRangeAddress(1,1,9,10));





		
			
			try {
				wb.write(fos);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(fos!=null)
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}
