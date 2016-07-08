package crds.personalSpace.myModify.dao.impl;

import java.util.List;

import crds.personalSpace.myModify.dao.IDAOMyModify;
import crds.personalSpace.myModify.web.form.FormMyModify;
import crds.pub.util.JdbcTemplateExtend;

public class DAOMyModify implements IDAOMyModify{
	private JdbcTemplateExtend jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplateExtend jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	@SuppressWarnings("unchecked")
	//查询全部
	public List myLoad(FormMyModify form1) throws Exception {
		// TODO Auto-generated method stub

		StringBuffer countsql = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		countsql = new StringBuffer("select count(*) from resource where modify_time>0 ");
		sql = new StringBuffer(
				"select * from resource where modify_time>0");

		form1.setRowsCount(jdbcTemplate.queryForInt(countsql.toString()));
		return jdbcTemplate.queryForListPage(sql.toString(), form1
				.getStartPosition(), form1.getRowsPerPage());
	}
	//条件查询
	@SuppressWarnings("unchecked")
	public List myLoad1(FormMyModify form1) throws Exception {
		// TODO Auto-generated method stub

		StringBuffer countsql = new StringBuffer();
		StringBuffer sql = new StringBuffer();

		if (form1.getStart_time()==""||form1.getEnd_time()=="") {
			countsql = new StringBuffer("select count(*) from resource where modify_time>0");
			sql = new StringBuffer("select * from resource where modify_time>0");
		} else {
			countsql = new StringBuffer(
					"select count(*) from resource where modify_time between '"+form1.getStart_time()+"' and '"+form1.getEnd_time()+"'");
			sql = new StringBuffer("select * from resource where modify_time between '"+form1.getStart_time()+"' and '"+form1.getEnd_time()+"'");
		}
		form1.setRowsCount(jdbcTemplate.queryForInt(countsql.toString()));
		return jdbcTemplate.queryForListPage(sql.toString(), form1
				.getStartPosition(), form1.getRowsPerPage());
	}
}
