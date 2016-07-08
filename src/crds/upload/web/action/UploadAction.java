package crds.upload.web.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.springframework.web.struts.MappingDispatchActionSupport;
import crds.system.user.web.form.AddUser;
import crds.upload.bo.IBOpoi;
import crds.upload.web.form.UploadForm;







	public final class UploadAction extends MappingDispatchActionSupport{
		private Map<String, BufferedImage> map = null;
		private LinkedHashMap<String, LinkedHashMap<String, String>> map_data=null;
		

		public IBOpoi getBO() {
			return (IBOpoi) this.getWebApplicationContext().getBean(
			"BOpoi");
		}
		
		
		
		
	private String getCellValue(Cell c){
		String o =null;
		switch (c.getCellType()){
		case  Cell.CELL_TYPE_BLANK:
		o = "";break;
		case Cell.CELL_TYPE_BOOLEAN:
			o=String.valueOf(c.getBooleanCellValue());break;
		case Cell.CELL_TYPE_FORMULA:
			o=String.valueOf(c.getCellFormula());break;
		case Cell.CELL_TYPE_NUMERIC:
			o=String.valueOf(c.getNumericCellValue());break;
		case Cell.CELL_TYPE_STRING:
			o=String.valueOf(c.getStringCellValue());break;
		default:
			o=null;
			break;
			
		
		}
		return o;
	}
	
	public ActionForward test(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
			UploadForm form1=(UploadForm)form;
			AddUser form2=(AddUser)form;
			FormFile file = form1.getFile(); 
			TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
			  Calendar ca = Calendar.getInstance();
			    int year = ca.get(Calendar.YEAR);//获取年份
			    int month=ca.get(Calendar.MONTH);//获取月份
			    int day=ca.get(Calendar.DATE);//获取日
			    int hour=ca.get(Calendar.HOUR_OF_DAY);//小时
			    int minute=ca.get(Calendar.MINUTE);//分
			    int second=ca.get(Calendar.SECOND);//秒
			    String filename = file.getFileName();  
			    String name = year+""+(month + 1 )+ "" + day + ""+ hour + "" + minute + "" + second;
			    String f_name = name+""+filename;//重命名文件
			FileOutputStream fos = new FileOutputStream("d:\\"+f_name+""); //创建输出流  
		    fos.write(file.getFileData()); //写入  
		    fos.flush();//释放  
		    fos.close(); //关闭  
		try {
			Workbook wb =WorkbookFactory.create(new File("d:"+f_name+""));
			Sheet sheet = wb.getSheetAt(0);
			Row row = sheet.getRow(0);
			
			//Cell cell =row.getCell(0);
			//System.out.println(cell.getCellType());
			//System.out.println(cell.getStringCellValue());
			
			for(int i=1;i<=sheet.getLastRowNum();i++)
			{	row = sheet.getRow(i);
				form1.setID(getCellValue(row.getCell(0)));
				form1.setAge(getCellValue(row.getCell(1)));
				form1.setName(getCellValue(row.getCell(2)));
				if(form1!=null){
					getBO().Add(form1);
				}
			
			}
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mapping.findForward("succ");
	}

}
