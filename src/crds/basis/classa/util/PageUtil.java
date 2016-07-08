package crds.basis.classa.util;


import java.io.Serializable;

public class PageUtil implements Serializable{
	private int total; // ����ҳ

	private int pageno; // ��ǰ�ǵڼ�ҳ

	private int prepageno; // ��һҳ

	private int nextpageno; // ��һҳ

	private int pagesize; // ÿҳ����

	private int totalnum; // ������

	public PageUtil(String spageno, int totalnum, int pagesize) {
		this.pagesize = pagesize;
		this.totalnum = totalnum;

		// 1ҳ�봦�?��������null����������abc��pageno=1��
		pageno = 1;
		try {
			if (spageno != null)
				pageno = Integer.parseInt(spageno);
		} catch (NumberFormatException e) {
		}
		// 2 ����һ����ҳ
		total = totalnum % pagesize == 0 ? (totalnum / pagesize) : (totalnum
				/ pagesize + 1);
		
		// 3 �ж�ҳ���Ƿ��ڷ�Χ��
		if (pageno > total)
			pageno = total;
		if (pageno < 1)
			pageno = 1;
		
		// 4 ��������ҳ��,����ǵ�1ҳ��û����ҳ,��������ҳ��û����ҳ
		if (pageno > 1)
			prepageno = pageno - 1;
		if (pageno < total)
			nextpageno = pageno + 1;

	}

	public int getNextpageno() {
		return nextpageno;
	}

	public void setNextpageno(int nextpageno) {
		this.nextpageno = nextpageno;
	}

	public int getPageno() {
		return pageno;
	}

	public void setPageno(int pageno) {
		this.pageno = pageno;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getPrepageno() {
		return prepageno;
	}

	public void setPrepageno(int prepageno) {
		this.prepageno = prepageno;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getTotalnum() {
		return totalnum;
	}

	public void setTotalnum(int totalnum) {
		this.totalnum = totalnum;
	}

}
