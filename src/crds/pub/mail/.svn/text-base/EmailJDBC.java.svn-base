package crds.pub.mail;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import crds.pub.util.CommonMethod;
import crds.pub.util.Constant;
import crds.pub.util.JdbcTemplateExtend;

public class EmailJDBC implements Serializable{
	private static final long serialVersionUID = 1L;

	Logger log = Logger.getLogger(EmailJDBC.class);

	JdbcTemplateExtend jdbcTemplate = null;

	public void setJdbcTemplate(JdbcTemplateExtend jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,String>> debtRatFinishSendEmailContent(int flag, String companyCode,String...debtCodes){
		StringBuffer sql = new StringBuffer();
		if(flag==1){
			sql.append("select emri.*,cbi.company_name,cmri.rating_company erating_company,cmri.rating_date erating_date,cmri.rating_class erating_class,cmri.rating_change erating_change,");
			sql.append(" (select bra_rating from SYSTEM_INOUT_RATING_INFO where rating_number=emri.FINALLY_RATING) FINALLY_RATING_A");
			sql.append("  from evaluation_m_rating_info emri");
			sql.append(" left join(");
			sql.append(CommonMethod.getMDRatReferenceMaxSQL(flag,companyCode,debtCodes));
			sql.append(" ) cmri on cmri.company_code=emri.company_code");
			sql.append(" left join company_basic_info cbi on cbi.company_code=emri.company_code");
			sql.append(" where emri.company_code='").append(companyCode).append("' and emri.rating_status='RV'");
		} else if(flag==2){
			sql.append("select edri.*,cdi.debt_name,cdri.company_code ecompany_code,cdri.debt_code edebt_code,cdri.rating_company_name erating_company,cdri.rating_date erating_date,cdri.rating_class erating_class,cdri.rating_change erating_change,");
			sql.append(" (select bra_rating from SYSTEM_INOUT_RATING_INFO where rating_number=edri.FINALLY_RATING) FINALLY_RATING_A");
			sql.append("  from evaluation_d_rating_info edri");
			sql.append(" left join(");
			sql.append(CommonMethod.getMDRatReferenceMaxSQL(flag,companyCode,debtCodes));
			sql.append(" ) cdri on cdri.company_code=edri.company_code and cdri.debt_code=edri.debt_code");
			sql.append(" left join company_debt_info cdi on cdi.company_code=edri.company_code and cdi.union_code=edri.debt_code");
			sql.append(" where edri.company_code='").append(companyCode).append("' and edri.rating_status='RV'");
			if(debtCodes!=null && debtCodes.length>0){
				boolean lenFlag = debtCodes.length>1;
				sql.append("             and edri.debt_code").append(lenFlag?" in (":"=");
				sql.append(Constant.arraysToString("'", "','", debtCodes)).append(lenFlag?")":"");
			}
		}
		return jdbcTemplate.queryForList(sql.toString());
	}


}
