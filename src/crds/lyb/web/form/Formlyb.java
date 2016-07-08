package crds.lyb.web.form;

import crds.pub.util.AbstractForm;

public final class Formlyb extends AbstractForm {
	
	private static final long serialVersionUID = 1L;
	String new_id;
	String new_title;
	String new_date;
	String new_author;
	String news;
	String new_type;
	public String getNew_type() {
		return new_type;
	}
	public void setNew_type(String newType) {
		new_type = newType;
	}
	public String getNew_id() {
		return new_id;
	}
	public void setNew_id(String newId) {
		new_id = newId;
	}
	public String getNew_title() {
		return new_title;
	}
	public void setNew_title(String newTitle) {
		new_title = newTitle;
	}
	public String getNew_date() {
		return new_date;
	}
	public void setNew_date(String newDate) {
		new_date = newDate;
	}
	public String getNew_author() {
		return new_author;
	}
	public void setNew_author(String newAuthor) {
		new_author = newAuthor;
	}
	public String getNews() {
		return news;
	}
	public void setNews(String news) {
		this.news = news;
	}
	
	
}
