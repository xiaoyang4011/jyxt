package crds.personalSpace.myUpload.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import crds.personalSpace.myUpload.dao.IDAOMyUpload;
import crds.personalSpace.myUpload.web.form.FormMyUpload;
import crds.pub.util.JdbcTemplateExtend;

public class DAOMyUpload implements IDAOMyUpload {
	private JdbcTemplateExtend jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplateExtend jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	@SuppressWarnings("unchecked")
	//查询全部
	public List myLoad(FormMyUpload form1) throws Exception {
		// TODO Auto-generated method stub

		StringBuffer countsql = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		countsql = new StringBuffer("select count(*) from resource");
		sql = new StringBuffer(
				"select * from resource");

		form1.setRowsCount(jdbcTemplate.queryForInt(countsql.toString()));
		return jdbcTemplate.queryForListPage(sql.toString(), form1
				.getStartPosition(), form1.getRowsPerPage());
	}
	//条件查询
	@SuppressWarnings("unchecked")
	public List myLoad1(FormMyUpload form1) throws Exception {
		// TODO Auto-generated method stub

		StringBuffer countsql = new StringBuffer();
		StringBuffer sql = new StringBuffer();

		if (form1.getStart_time()==""||form1.getEnd_time()=="") {
			countsql = new StringBuffer("select count(*) from resource");
			sql = new StringBuffer("select * from resource");
		} else {
			countsql = new StringBuffer(
					"select count(*) from resource where upload_time between '"+form1.getStart_time()+"' and '"+form1.getEnd_time()+"'");
			sql = new StringBuffer("select * from resource where upload_time between '"+form1.getStart_time()+"' and '"+form1.getEnd_time()+"'");
		}
		form1.setRowsCount(jdbcTemplate.queryForInt(countsql.toString()));
		return jdbcTemplate.queryForListPage(sql.toString(), form1
				.getStartPosition(), form1.getRowsPerPage());
	}

}
