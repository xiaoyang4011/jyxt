package crds.pub.util;

import java.util.List;

/**
 * @specification :
 * @version : 1.0
 * @author : liuxx
 * @date : Dec 17, 2008 11:26:41 AM
 * @email : liuxx.adam@gmail.com
 */
public class ExcelPartStyle {

	private List<ExcelCellStyle> cellList;//单元格
	
	private int frontSpaceRow = -99999999;//前空行
	
	private int backSpaceRow = -99999999;//后空行
	
	private int frontSpaceCol = -99999999;//前空列
	
	private int backSpaceCol = -99999999;//后空列
	
	private boolean isUse = false;//是否使用样式信息,默认不使用

	public boolean isUse() {
		return isUse;
	}

	public void setUse(boolean isUse) {
		this.isUse = isUse;
	}

	public List<ExcelCellStyle> getCellList() {
		return cellList;
	}

	public void setCellList(List<ExcelCellStyle> cellList) {
		this.cellList = cellList;
	}

	public int getFrontSpaceRow() {
		return frontSpaceRow;
	}

	public void setFrontSpaceRow(int frontSpaceRow) {
		this.frontSpaceRow = frontSpaceRow;
	}

	public int getBackSpaceRow() {
		return backSpaceRow;
	}

	public void setBackSpaceRow(int backSpaceRow) {
		this.backSpaceRow = backSpaceRow;
	}

	public int getFrontSpaceCol() {
		return frontSpaceCol;
	}

	public void setFrontSpaceCol(int frontSpaceCol) {
		this.frontSpaceCol = frontSpaceCol;
	}

	public int getBackSpaceCol() {
		return backSpaceCol;
	}

	public void setBackSpaceCol(int backSpaceCol) {
		this.backSpaceCol = backSpaceCol;
	}
}
