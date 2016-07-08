package crds.upload.dao.impl;

import crds.pub.util.JdbcTemplateExtend;
import crds.upload.dao.IDAOpoi;
import crds.upload.web.form.UploadForm;

public class DAOpoi implements IDAOpoi{
	// 重写的JdbcTemplateExtend
	private JdbcTemplateExtend jdbcTemplate;
    
	public void setJdbcTemplate(JdbcTemplateExtend jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}	
	public boolean Add(UploadForm form) throws Exception{
		String name = form.getName();
		String ID = form.getID();
		String age = form.getAge();
		String sql="insert into test1(ID,name,age)values('"+ID+"','"+name+"','"+age+"')";
		int dao=jdbcTemplate.update(sql);
		if(dao!=0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
