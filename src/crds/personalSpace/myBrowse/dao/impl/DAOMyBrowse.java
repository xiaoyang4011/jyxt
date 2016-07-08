package crds.personalSpace.myBrowse.dao.impl;

import java.util.List;

import crds.personalSpace.myBrowse.dao.IDAOMyBrowse;
import crds.personalSpace.myBrowse.web.form.FormMyBrowse;
import crds.pub.util.JdbcTemplateExtend;

public class DAOMyBrowse implements IDAOMyBrowse {
	private JdbcTemplateExtend jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplateExtend jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	@SuppressWarnings("unchecked")
	//查询全部
	public List myLoad(FormMyBrowse form1) throws Exception {
		// TODO Auto-generated method stub

		StringBuffer countsql = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		countsql = new StringBuffer("select count(*) from resource_browse");
		sql = new StringBuffer(
				"select * from resource_browse,resource where resource_browse.resource_id=resource.resource_id");

		form1.setRowsCount(jdbcTemplate.queryForInt(countsql.toString()));
		return jdbcTemplate.queryForListPage(sql.toString(), form1
				.getStartPosition(), form1.getRowsPerPage());
	}
	//条件查询
	@SuppressWarnings("unchecked")
	public List myLoad1(FormMyBrowse form1) throws Exception {
		// TODO Auto-generated method stub

		StringBuffer countsql = new StringBuffer();
		StringBuffer sql = new StringBuffer();

		if (form1.getStart_time()==""||form1.getEnd_time()=="") {
			countsql = new StringBuffer("select count(*) from resource_browse");
			sql = new StringBuffer("select * from resource_browse,resource where resource_browse.resource_id=resource.resource_id");
		} else {
			countsql = new StringBuffer(
					"select count(*) from resource_browse where browse_time between '"+form1.getStart_time()+"' and '"+form1.getEnd_time()+"'");
			sql = new StringBuffer("select * from resource_browse,resource where browse_time between '"+form1.getStart_time()+"' and '"+form1.getEnd_time()+"' and resource_browse.resource_id=resource.resource_id");
		}
		form1.setRowsCount(jdbcTemplate.queryForInt(countsql.toString()));
		return jdbcTemplate.queryForListPage(sql.toString(), form1
				.getStartPosition(), form1.getRowsPerPage());
	}

}
