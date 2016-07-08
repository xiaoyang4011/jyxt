/*
 * FCKeditor - The text editor for internet
 * Copyright (C) 2003-2005 Frederico Caldeira Knabben
 * 
 * Licensed under the terms of the GNU Lesser General Public License:
 * 		http://www.opensource.org/licenses/lgpl-license.php
 * 
 * For further information visit:
 * 		http://www.fckeditor.net/
 * 
 * File Name: SimpleUploaderServlet.java
 * 	Java File Uploader class.
 * 
 * Version:  2.3
 * Modified: 2005-08-11 16:29:00
 * 
 * File Authors:
 * 		Simone Chiaretta (simo@users.sourceforge.net)
 */ 

package crds.pub.FCKeditor.uploader;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;

import crds.pub.util.CommonMethod;
import crds.pub.util.Constant;
import crds.pub.util.FormUserOperation;


/**
 * Servlet to upload files.<br>
 *
 * This servlet accepts just file uploads, eventually with a parameter specifying file type
 *
 * @author Simone Chiaretta (simo@users.sourceforge.net)
 */
@SuppressWarnings("unchecked")
public class SimpleUploaderServlet extends HttpServlet {
	private static final long serialVersionUID = -1732614516586791105L;
	private static String baseDir;
	private static boolean debug=false;
	private static boolean enabled=false;

	private static Hashtable allowedExtensions;
	private static Hashtable deniedExtensions;

	/**
	 * Initialize the servlet.<br>
	 * Retrieve from the servlet configuration the "baseDir" which is the root of the file repository:<br>
	 * If not specified the value of "/UserFiles/" will be used.<br>
	 * Also it retrieve all allowed and denied extensions to be handled.
	 *
	 */
	public void init() throws ServletException {

		debug=(new Boolean(getInitParameter("debug"))).booleanValue();


		baseDir=getInitParameter("baseDir");
		enabled=(new Boolean(getInitParameter("enabled"))).booleanValue();
		if(baseDir==null)
			baseDir="/UserFiles/";
		String realBaseDir=getServletContext().getRealPath(baseDir);
		File baseFile=new File(realBaseDir);
		if(!baseFile.exists()){
			baseFile.mkdir();
		}

		allowedExtensions = new Hashtable(3);
		deniedExtensions = new Hashtable(3);

		allowedExtensions.put("File",stringToArrayList(getInitParameter("AllowedExtensionsFile")));
		deniedExtensions.put("File",stringToArrayList(getInitParameter("DeniedExtensionsFile")));

		allowedExtensions.put("Image",stringToArrayList(getInitParameter("AllowedExtensionsImage")));
		deniedExtensions.put("Image",stringToArrayList(getInitParameter("DeniedExtensionsImage")));

		allowedExtensions.put("Flash",stringToArrayList(getInitParameter("AllowedExtensionsFlash")));
		deniedExtensions.put("Flash",stringToArrayList(getInitParameter("DeniedExtensionsFlash")));

	}


	/**
	 * Manage the Upload requests.<br>
	 *
	 * The servlet accepts commands sent in the following format:<br>
	 * simpleUploader?Type=ResourceType<br><br>
	 * It store the file (renaming it in case a file with the same name exists) and then return an HTML file
	 * with a javascript command in it.
	 *
	 */	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session=request.getSession();
		FormUserOperation formUser = Constant.getFormUserOperation(request);
		String fck_task_id=(String)request.getSession().getAttribute("fck_task_id");
		if(fck_task_id==null){
			fck_task_id="temp_task_id";
		}

		response.setContentType("text/html; charset=UTF-8");
		response.setHeader("Cache-Control","no-cache");
		PrintWriter out = response.getWriter();

		String typeStr=request.getParameter("Type");

		String currentPath=baseDir+formUser.getCompany_code()+"/"+fck_task_id+"/"+typeStr;
		String currentDirPath=getServletContext().getRealPath(currentPath);

		File currentDir=new File(currentDirPath);
		if(!currentDir.exists()){
			currentDir.mkdirs();
		}

		String retVal="0";
		String newName="";
		String fileUrl="";
		String errorMessage="";

		if(enabled) {		
			org.apache.commons.fileupload.DiskFileUpload upload = new org.apache.commons.fileupload.DiskFileUpload();
			try {
				List items = upload.parseRequest(request);

				Map fields=new HashMap();

				Iterator iter = items.iterator();
				while (iter.hasNext()) {
					FileItem item = (FileItem) iter.next();
					if (item.isFormField())
						fields.put(item.getFieldName(),item.getString());
					else
						fields.put(item.getFieldName(),item);
				}
				FileItem uplFile=(FileItem)fields.get("NewFile");
				String fileNameLong=uplFile.getName();
				fileNameLong=fileNameLong.replace('\\','/');

				String[] pathParts=fileNameLong.split("/");
				String fileName=pathParts[pathParts.length-1];

				String nameWithoutExt=getNameWithoutExtension(fileName);
				String ext=getExtension(fileName);
				File pathToSave=new File(currentDirPath,fileName);

				//获取当前服务器IP
				String server_ip=(String)session.getAttribute("server_ip");
				if(server_ip==null){
					server_ip=CommonMethod.getServerIP(request)+":"+request.getServerPort();//获取当前服务器IP地址
				}
				String url="http://"+server_ip+currentPath.substring(2,currentPath.length());

				fileUrl=url+"/"+fileName;
				if(extIsAllowed(typeStr,ext)) {
					int counter=1;
					while(pathToSave.exists()){
						newName=nameWithoutExt+"("+counter+")"+"."+ext;
						fileUrl=url+"/"+newName;
						retVal="201";
						pathToSave=new File(currentDirPath,newName);
						counter++;
					}
					uplFile.write(pathToSave);
				}
				else {
					retVal="202";
					errorMessage="";
				}
			}catch (Exception ex) {
				if (debug) ex.printStackTrace();
				retVal="203";
			}
		}
		else {
			retVal="1";
			errorMessage="This file uploader is disabled. Please check the WEB-INF/web.xml file";
		}


		out.println("<script type=\"text/javascript\">");
		out.println("window.parent.OnUploadCompleted("+retVal+",'"+fileUrl+"','"+newName+"','"+errorMessage+"');");
		out.println("</script>");
		out.flush();
		out.close();

	}


	/*
	 * This method was fixed after Kris Barnhoorn (kurioskronic) submitted SF bug #991489
	 */
	private static String getNameWithoutExtension(String fileName) {
		return fileName.substring(0, fileName.lastIndexOf("."));
	}

	/*
	 * This method was fixed after Kris Barnhoorn (kurioskronic) submitted SF bug #991489
	 */
	private String getExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".")+1);
	}



	/**
	 * Helper function to convert the configuration string to an ArrayList.
	 */

	private ArrayList stringToArrayList(String str) {

		String[] strArr=str.split("\\|");

		ArrayList tmp=new ArrayList();
		if(str.length()>0) {
			for(int i=0;i<strArr.length;++i) {
				tmp.add(strArr[i].toLowerCase());
			}
		}
		return tmp;
	}


	/**
	 * Helper function to verify if a file extension is allowed or not allowed.
	 */

	private boolean extIsAllowed(String fileType, String ext) {

		ext=ext.toLowerCase();

		ArrayList allowList=(ArrayList)allowedExtensions.get(fileType);
		ArrayList denyList=(ArrayList)deniedExtensions.get(fileType);

		if(allowList.size()==0)
			if(denyList.contains(ext))
				return false;
			else
				return true;

		if(denyList.size()==0)
			if(allowList.contains(ext))
				return true;
			else
				return false;

		return false;
	}

}

