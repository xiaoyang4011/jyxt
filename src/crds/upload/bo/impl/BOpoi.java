package crds.upload.bo.impl;


import crds.upload.bo.IBOpoi;
import crds.upload.dao.IDAOpoi;
import crds.upload.web.form.UploadForm;

public class BOpoi implements IBOpoi{
	private IDAOpoi dao;
	public void setDAOpoi(IDAOpoi dao) {
		this.dao = dao;
	}
	public boolean Add(UploadForm form) throws Exception {
		return (dao.Add(form));
	}


}

