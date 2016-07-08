package crds.pub.util;

import org.apache.poi.hssf.usermodel.HSSFCell; 

public class Excelutil {

	
	public static String formatCell(HSSFCell hssfCell) { 
		if(hssfCell==null)
			 return "";
		else{
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) { 
            // 返回布尔类型的值 
            return String.valueOf(hssfCell.getBooleanCellValue()); 
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) { 
            // 返回数值类型的值 
            return String.valueOf(hssfCell.getNumericCellValue()); 
        } else { 
            // 返回字符串类型的值 
            return String.valueOf(hssfCell.getStringCellValue()); 
        } 
    } 
	}
}
