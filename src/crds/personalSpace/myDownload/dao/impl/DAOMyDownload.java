package crds.personalSpace.myDownload.dao.impl;

import java.util.List;

import crds.personalSpace.myDownload.dao.IDAOMyDownload;
import crds.personalSpace.myDownload.web.form.FormMyDownload;
import crds.pub.util.JdbcTemplateExtend;

public class DAOMyDownload implements IDAOMyDownload {
	private JdbcTemplateExtend jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplateExtend jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	@SuppressWarnings("unchecked")
	//查询全部
	public List myLoad(FormMyDownload form1) throws Exception {
		// TODO Auto-generated method stub

		StringBuffer countsql = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		countsql = new StringBuffer("select count(*) from resupload");
		sql = new StringBuffer(
				"select * from resupload,resource where resupload.resource_id=resource.resource_id");

		form1.setRowsCount(jdbcTemplate.queryForInt(countsql.toString()));
		return jdbcTemplate.queryForListPage(sql.toString(), form1
				.getStartPosition(), form1.getRowsPerPage());
	}
	//条件查询
	@SuppressWarnings("unchecked")
	public List myLoad1(FormMyDownload form1) throws Exception {
		// TODO Auto-generated method stub

		StringBuffer countsql = new StringBuffer();
		StringBuffer sql = new StringBuffer();

		if (form1.getStart_time()==""||form1.getEnd_time()=="") {
			countsql = new StringBuffer("select count(*) from resupload");
			sql = new StringBuffer("select * from resupload,resource where resupload.resource_id=resource.resource_id");
		} else {
			countsql = new StringBuffer(
					"select count(*) from resupload where download_time between '"+form1.getStart_time()+"' and '"+form1.getEnd_time()+"'");
			sql = new StringBuffer("select * from resupload,resource where download_time between '"+form1.getStart_time()+"' and '"+form1.getEnd_time()+"' and resupload.resource_id=resource.resource_id");
		}
		form1.setRowsCount(jdbcTemplate.queryForInt(countsql.toString()));
		return jdbcTemplate.queryForListPage(sql.toString(), form1
				.getStartPosition(), form1.getRowsPerPage());
	}

}
