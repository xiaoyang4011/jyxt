package crds.pub.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jfree.chart.JFreeChart;

/**
 * @specification :POI方式导出到excel
 * @version : 1.0
 * @author : liuxx
 * @date : Oct 29, 2008 2:04:48 PM
 * @email : liuxx.adam@gmail.com
 */
public class ExportToExcelByPOI {

	static Logger logger = Logger.getLogger(ExportToExcelByPOI.class);

	private static int imgWidth = 400;

	private static int imgHeight = 300;

	/**
	 * @specification :将数据和图片写入到excel
	 * @param :OutputStream
	 *            out,输出流
	 * @param :Map
	 *            dataMap<String,List>,数据集合,key可作为多页显示时的sheet标签名,value为每页显示的数据集List,List中的每一个对象为同一页需要显示的数据对象
	 * @param :String
	 *            type,是否分sheet显示,one为单页,其他为多页
	 * @param :String
	 *            srcFilePath,合并excel的文件路径
	 * @return :void
	 * @exception :IOException
	 */

	@SuppressWarnings("unchecked")
	public static void write(OutputStream out, Map dataMap, String type,
			String srcFilePath, String realPath) throws IOException {
		int rowNum = 0;// 行
		int colNum = 2;// 起始列
		int rowSpacing = 3;// 行间隔

		HSSFWorkbook wb = null;
		if (srcFilePath != null && !"".equals(srcFilePath)) {// 读取原文件,将新数据插入到其中
			File file = new File(srcFilePath);
			if (!file.exists()) {
				logger.error("需要合并的excel文件不存在");
				return;
			}
			FileInputStream srcFile = new FileInputStream(srcFilePath);
			wb = new HSSFWorkbook(srcFile);
		} else {// 构建新的excel
			wb = new HSSFWorkbook();
		}

		// 将原来文件中的图片加入进来
		List srcPicList = wb.getAllPictures();
		for (int i = 0; i < srcPicList.size(); i++) {
			HSSFPictureData srcPic = (HSSFPictureData) srcPicList.get(i);
			wb.addPicture(srcPic.getData(), HSSFWorkbook.PICTURE_TYPE_JPEG);
		}

		HSSFSheet sheet = null;
		HSSFPatriarch patriarch = null;

		sheet = null;
		if ("one".equals(type)) {// 单页显示时
			if (srcFilePath != null && !"".equals(srcFilePath)) {// 从原文件中取得第一个工作表
				sheet = wb.getSheetAt(0);
				rowNum = sheet.getLastRowNum() + 1;// 取得之前数据占用的行号,从后面插入数据
			} else {
				sheet = wb.createSheet();// Excel文件中的一个工作表
			}
			patriarch = sheet.createDrawingPatriarch();
		}
		if (dataMap == null) {
			dataMap = new LinkedHashMap();
		}
		Set set = dataMap.entrySet();
		Iterator it = set.iterator();
		while (it.hasNext()) {// 取得每一页数据对象

			Map.Entry entry = (Entry) it.next();
			String key = (String) entry.getKey();// sheet名称
			Object objList = entry.getValue();
			if (objList == null) {
				continue;
			}
			List list = (List) objList;// 每一页数据对象
			if (!"one".equals(type)) {// 分页时,每次创建一个sheet

				String sheetName = "";
				if (key != null && !"".equals(key)) {
					sheetName = key.replaceAll("[\\\\?*/\\[\\]]+", "");
				}
				sheet = wb.createSheet(sheetName);// Excel文件中的一个工作表
				patriarch = sheet.createDrawingPatriarch();
				rowNum = 0;
			}
			HSSFFont f  = wb.createFont();   
			for (int x = 0; x < list.size(); x++) {
				Object obj = list.get(x);// 数据对象
				if (obj == null) {
					continue;
				}
				if (obj.getClass().isArray()) {// 数据表格
					rowNum += rowSpacing;
					String[][] data = (String[][]) obj;
					for (int i = 0; i < data.length; i++) {
						HSSFRow row = sheet.createRow(i + rowNum);
						if (data[i] != null) {
							for (int j = 0; j < data[i].length; j++) {
								if(x==0 && i==0 && (j==0 ||j==2||j==4||j==6||j==8||j==10)){
									f.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗   
									HSSFCellStyle cellStyle = wb.createCellStyle();   
									cellStyle.setFont(f);
									HSSFCell cell = row.createCell((short) (j + colNum));
									cell.setCellStyle(cellStyle);
									cell.setCellValue(new HSSFRichTextString(
										data[i][j]));
								}else{
									HSSFCell cell = row
											.createCell((short) (j + colNum));
									cell.setCellValue(new HSSFRichTextString(
											data[i][j]));
								}
							}
						}
					}
					rowNum += data.length;
				} else if ("org.jfree.chart.JFreeChart".equals(obj.getClass()
						.getName())) {// 图表
					rowNum += rowSpacing;
					JFreeChart chart = (JFreeChart) obj;
					ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();// 字节输出流，用来写二进制文件
					BufferedImage bufferImg = chart.createBufferedImage(
							imgWidth, imgHeight);
					ImageIO.write(bufferImg, "png", byteArrayOut);
					HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023,
							255, (short) colNum, rowNum,
							(short) (imgWidth / 87 + colNum), imgHeight / 19
									+ rowNum);
					// 插入图片
					patriarch.createPicture(anchor, wb.addPicture(byteArrayOut
							.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
					if (x == 0) {
						String filepath = realPath + "pub/images/def.jpg";
						// excel中图片位置
						HSSFClientAnchor anchor_def = new HSSFClientAnchor(0,
								0, 1023, 255, (short) (colNum + 5), rowNum + 8,
								(short) (colNum + 5), rowNum + 14);
						// 先把读进来的图片放到一个ByteArrayOutputStream中，以便产生ByteArray
						ByteArrayOutputStream byteArrayOut_def = new ByteArrayOutputStream();
						try {
							BufferedImage bufferImg_def = ImageIO
									.read(new File(filepath));
							ImageIO.write(bufferImg_def, "jpg",
									byteArrayOut_def);
							patriarch.createPicture(anchor_def, wb.addPicture(
									byteArrayOut_def.toByteArray(),
									HSSFWorkbook.PICTURE_TYPE_JPEG));
						} catch (Exception e) {
							logger.error("蜡烛图说明未找到。");
						}
					}
					colNum +=7;//lie
//					rowNum += imgHeight / 19 + 1;
				}
				rowNum -=3;
			}
			colNum =2;
		}
		wb.write(out);
	}

//	int rowNum = 0;// 行
//	int colNum = 2;// 起始列
//	int rowSpacing = 3;// 行间隔
	/**
	 * @specification :将数据和图片写入到excel,并可通过dataStyleMap指定单元格样式
	 * @param :OutputStream
	 *            out,输出流
	 * @param :Map
	 *            dataMap<String,List>,数据集合,key可作为多页显示时的sheet标签名,value为每页显示的数据集List,List中的每一个对象为同一页需要显示的数据对象
	 * @param :String
	 *            type,是否分sheet显示,one为单页,其他为多页
	 * @param :String
	 *            srcFilePath,合并excel的文件路径
	 * @param :Map
	 *            dataStyleMap,数据集合的样式,对应dataMap
	 * @return :void
	 * @exception :IOException
	 */
	@SuppressWarnings("unchecked")
	public static void write(OutputStream out, Map dataMap, String type,
			String srcFilePath, Map dataStyleMap) throws IOException {
		int rowNum = 0;// 行
		int colNum = 0;// 行
		int defColSpacing = 2;// 默认起始列
		int defRowSpacing = 3;// 默认行间隔

		HSSFWorkbook wb = null;
		if (srcFilePath != null && !"".equals(srcFilePath)) {// 读取原文件,将新数据插入到其中
			File file = new File(srcFilePath);
			if (!file.exists()) {
				logger.error("需要合并的excel文件不存在");
				return;
			}
			FileInputStream srcFile = new FileInputStream(srcFilePath);
			wb = new HSSFWorkbook(srcFile);
		} else {// 构建新的excel
			wb = new HSSFWorkbook();
		}

		// 将原来文件中的图片加入进来
		List srcPicList = wb.getAllPictures();
		for (int i = 0; i < srcPicList.size(); i++) {
			HSSFPictureData srcPic = (HSSFPictureData) srcPicList.get(i);
			wb.addPicture(srcPic.getData(), HSSFWorkbook.PICTURE_TYPE_JPEG);
		}

		HSSFSheet sheet = null;
		HSSFPatriarch patriarch = null;

		sheet = null;
		if ("one".equals(type)) {// 单页显示时
			if (srcFilePath != null && !"".equals(srcFilePath)) {// 从原文件中取得第一个工作表
				sheet = wb.getSheetAt(0);
				rowNum = sheet.getLastRowNum() + 1;// 取得之前数据占用的行号,从后面插入数据
			} else {
				sheet = wb.createSheet();// Excel文件中的一个工作表
			}
			patriarch = sheet.createDrawingPatriarch();
		}
		if (dataMap == null) {
			dataMap = new LinkedHashMap();
		}
		if (dataStyleMap == null) {
			dataStyleMap = new LinkedHashMap();
		}
		Set set = dataMap.entrySet();
		Iterator it = set.iterator();
		while (it.hasNext()) {// 取得每一页数据对象

			Map.Entry entry = (Entry) it.next();
			String key = (String) entry.getKey();// sheet名称
			Object objList = entry.getValue();
			if (objList == null) {
				continue;
			}

			List partStyList = (List) dataStyleMap.get(key);
			List list = (List) objList;// 每一页数据对象
			if (!"one".equals(type)) {// 分页时,每次创建一个sheet
				String sheetName = "";
				if (key != null && !"".equals(key)) {
					sheetName = key.replaceAll("[\\\\?*/\\[\\]]+", "");
				}
				sheet = wb.createSheet(sheetName);// Excel文件中的一个工作表
				patriarch = sheet.createDrawingPatriarch();
				rowNum = 0;
			}
			for (int x = 0; x < list.size(); x++) {
				Object obj = list.get(x);// 数据对象
				if (obj == null) {
					continue;
				}

				ExcelPartStyle partStyle = new ExcelPartStyle();
				if (partStyList != null && partStyList.size() > 0
						&& partStyList.get(x) != null) {
					partStyle = (ExcelPartStyle) partStyList.get(x);
				}
				int rowSpacing = defRowSpacing;
				int colSpacing = defColSpacing;
				if (partStyle.isUse()) {
					rowSpacing = partStyle.getFrontSpaceRow();
					colSpacing = partStyle.getFrontSpaceCol();
				}
				if (obj.getClass().isArray()) {// 数据表格
					rowNum += rowSpacing;
					colNum = colSpacing;
					String[][] data = (String[][]) obj;
					for (int i = 0; i < data.length; i++) {
						HSSFRow row = sheet.createRow(i + rowNum);
						if (data[i] != null) {
							for (int j = 0; j < data[i].length; j++) {
								HSSFCell cell = row
										.createCell((short) (j + colNum));
								cell.setCellValue(new HSSFRichTextString(
										data[i][j]));
								// 设置单元格样式
								if (partStyle.isUse()) {
									List cellStyleList = partStyle
											.getCellList();
									if (cellStyleList != null) {
										for (int k = 0; k < cellStyleList
												.size(); k++) {
											ExcelCellStyle cellStyle = (ExcelCellStyle) cellStyleList
													.get(k);
											if (cellStyle.getRowNum() == i + 1
													&& cellStyle.getColNum() == j + 1) {
												HSSFCellStyle style = getCellStyle(
														wb, cellStyle);
												cell.setCellStyle(style);
											}
										}
									}
								}
							}
						}
					}
					rowNum += data.length;
				} else if ("org.jfree.chart.JFreeChart".equals(obj.getClass()
						.getName())) {// 图表
					rowNum += rowSpacing;
					colNum = colSpacing;
					JFreeChart chart = (JFreeChart) obj;
					ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();// 字节输出流，用来写二进制文件
					BufferedImage bufferImg = chart.createBufferedImage(
							imgWidth, imgHeight);
					ImageIO.write(bufferImg, "png", byteArrayOut);
					HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023,
							255, (short) colNum, rowNum,
							(short) (imgWidth / 87 + colNum), imgHeight / 19
									+ rowNum);
					// 插入图片
					patriarch.createPicture(anchor, wb.addPicture(byteArrayOut
							.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
					rowNum += imgHeight / 19 + 1;
				}
			}
		}
		wb.write(out);
	}

	/**
	 * @specification :设置单元格样式
	 * @param :HSSFWorkbook
	 *            wb,excel工作表
	 * @param :ExcelCellStyle
	 *            cellStyle,单元格样式信息
	 * @return :HSSFCellStyle,单元格样式
	 * @exception :NAN
	 */
	private static HSSFCellStyle getCellStyle(HSSFWorkbook wb,
			ExcelCellStyle cellStyle) {
		HSSFCellStyle style = wb.createCellStyle();
		HSSFFont font = wb.createFont();
		if ("bold".equalsIgnoreCase(cellStyle.getBlodweight())) {
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
		}
		style.setFont(font);
		return style;
	}

	/**
	 * @specification :将数据和图片写入到excel
	 * @param :OutputStream
	 *            out,输出流
	 * @param :Map
	 *            dataMap<String,List>,数据集合,key可作为多页显示时的sheet标签名,value为每页显示的数据集List,List中的每一个对象为同一页需要显示的数据对象
	 * @param :String
	 *            type,是否分sheet显示,one为单页,其他为多页
	 * @param :String
	 *            srcFilePath,合并excel的文件路径
	 * @return :void
	 * @exception :IOException
	 */

	@SuppressWarnings("unchecked")
	public static void write(OutputStream out, Map dataMap, String type,
			String srcFilePath) throws IOException {
		int rowNum = 0;// 行
		int colNum = 2;// 起始列
		int rowSpacing = 3;// 行间隔

		HSSFWorkbook wb = null;
		if (srcFilePath != null && !"".equals(srcFilePath)) {// 读取原文件,将新数据插入到其中
			File file = new File(srcFilePath);
			if (!file.exists()) {
				logger.error("需要合并的excel文件不存在");
				return;
			}
			FileInputStream srcFile = new FileInputStream(srcFilePath);
			wb = new HSSFWorkbook(srcFile);
		} else {// 构建新的excel
			wb = new HSSFWorkbook();
		}

		// 将原来文件中的图片加入进来
		List srcPicList = wb.getAllPictures();
		for (int i = 0; i < srcPicList.size(); i++) {
			HSSFPictureData srcPic = (HSSFPictureData) srcPicList.get(i);
			wb.addPicture(srcPic.getData(), HSSFWorkbook.PICTURE_TYPE_JPEG);
		}

		HSSFSheet sheet = null;
		HSSFPatriarch patriarch = null;

		sheet = null;
		if ("one".equals(type)) {// 单页显示时
			if (srcFilePath != null && !"".equals(srcFilePath)) {// 从原文件中取得第一个工作表
				sheet = wb.getSheetAt(0);
				rowNum = sheet.getLastRowNum() + 1;// 取得之前数据占用的行号,从后面插入数据
			} else {
				sheet = wb.createSheet();// Excel文件中的一个工作表
			}
			patriarch = sheet.createDrawingPatriarch();
		}
		if (dataMap == null) {
			dataMap = new LinkedHashMap();
		}
		Set set = dataMap.entrySet();
		Iterator it = set.iterator();
		while (it.hasNext()) {// 取得每一页数据对象

			Map.Entry entry = (Entry) it.next();
			String key = (String) entry.getKey();// sheet名称
			Object objList = entry.getValue();
			if (objList == null) {
				continue;
			}
			List list = (List) objList;// 每一页数据对象
			if (!"one".equals(type)) {// 分页时,每次创建一个sheet

				String sheetName = "";
				if (key != null && !"".equals(key)) {
					sheetName = key.replaceAll("[\\\\?*/\\[\\]]+", "");
				}
				sheet = wb.createSheet(sheetName);// Excel文件中的一个工作表
				patriarch = sheet.createDrawingPatriarch();
				rowNum = 0;
			}
			for (int x = 0; x < list.size(); x++) {
				Object obj = list.get(x);// 数据对象
				if (obj == null) {
					continue;
				}
				if (obj.getClass().isArray()) {// 数据表格
					rowNum += rowSpacing;
					String[][] data = (String[][]) obj;
					for (int i = 0; i < data.length; i++) {
						HSSFRow row = sheet.createRow(i + rowNum);
						if (data[i] != null) {
							for (int j = 0; j < data[i].length; j++) {
								HSSFCell cell = row
										.createCell((short) (j + colNum));
								cell.setCellValue(new HSSFRichTextString(
										data[i][j]));
							}
						}
					}
					rowNum += data.length;
				} else if ("org.jfree.chart.JFreeChart".equals(obj.getClass()
						.getName())) {// 图表
					rowNum += rowSpacing;
					JFreeChart chart = (JFreeChart) obj;
					ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();// 字节输出流，用来写二进制文件
					BufferedImage bufferImg = chart.createBufferedImage(
							imgWidth, imgHeight);
					ImageIO.write(bufferImg, "png", byteArrayOut);
					HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023,
							255, (short) colNum, rowNum,
							(short) (imgWidth / 87 + colNum), imgHeight / 19
									+ rowNum);
					// 插入图片
					patriarch.createPicture(anchor, wb.addPicture(byteArrayOut
							.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
					rowNum += imgHeight / 19 + 1;
				}
			}
		}
		wb.write(out);
	}

}
