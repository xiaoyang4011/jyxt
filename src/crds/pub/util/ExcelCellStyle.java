package crds.pub.util;

/**
 * @specification :
 * @version : 1.0
 * @author : liuxx
 * @date : Dec 17, 2008 11:26:25 AM
 * @email : liuxx.adam@gmail.com
 */
public class ExcelCellStyle {

	private int rowNum = -1;//行号
	
	private int colNum = -1;//列号
	
	private String blodweight;//粗体


	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public int getColNum() {
		return colNum;
	}

	public void setColNum(int colNum) {
		this.colNum = colNum;
	}

	public String getBlodweight() {
		return blodweight;
	}

	public void setBlodweight(String blodweight) {
		this.blodweight = blodweight;
	}
}
