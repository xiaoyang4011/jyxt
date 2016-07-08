package crds.stud.web.action;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import crds.stud.bo.IBOStud;
import crds.stud.web.form.FormStud;
import crds.upload.web.form.UploadForm;
import crds.zhaosheng.web.form.FormZs;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.springframework.web.struts.MappingDispatchActionSupport;



public final class ActionStud extends MappingDispatchActionSupport {
	
	private Map<String, BufferedImage> map = null;
	private LinkedHashMap<String, LinkedHashMap<String, String>> map_data=null;
	

	public IBOStud getBO() {
		return (IBOStud) this.getWebApplicationContext().getBean(
		"BOStud");
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
	
	public ActionForward Add_Stud(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
		UploadForm form1=(UploadForm)form;
		FormStud form2 =(FormStud)form;
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
			form2.setUser_ID(getCellValue(row.getCell(0)));
			form2.setStu_name(getCellValue(row.getCell(1)));
			form2.setStu_sex(getCellValue(row.getCell(2)));
			form2.setStu_birthday(getCellValue(row.getCell(3)));
			form2.setStu_zzmm(getCellValue(row.getCell(4)));
			form2.setStu_mz(getCellValue(row.getCell(5)));
			form2.setStu_sfzh(getCellValue(row.getCell(6)));
			form2.setStu_jtzz(getCellValue(row.getCell(7)));
			form2.setStu_yzbm(getCellValue(row.getCell(8)));
			form2.setStu_phone(getCellValue(row.getCell(9)));
			
			
			if(form1!=null){
				getBO().Add_stud(form2);
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

	
	
	
	
	@SuppressWarnings("unchecked")
	public ActionForward BroStud(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) throws Exception{

		FormStud form2 =(FormStud)form;
	
		if(form2.getRowsPerPage()==0)
		{
			form2.setRowsPerPage(12);
		}
		
		List list = getBO().Bro_stud(form2);

		request.setAttribute("list",list);
		request.setAttribute("form2",form2);
		return mapping.findForward("succ");	
}
	
}