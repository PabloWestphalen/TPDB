package com.jin;

import java.util.Hashtable;

import javax.servlet.ServletException;

import org.apache.catalina.servlets.DefaultServlet;
import org.apache.naming.resources.FileDirContext;
import org.apache.naming.resources.ProxyDirContext;

public class FileServingServlet extends DefaultServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		super.init();
		final Hashtable<String, Object> env = new Hashtable<String, Object>();
		env.put(ProxyDirContext.HOST, resources.getHostName());
		env.put(ProxyDirContext.CONTEXT, resources.getContextName());
		final String docBase = System.getenv("OPENSHIFT_DATA_DIR") + "/uploads";
		final FileDirContext context = new FileDirContext(env);
		context.setDocBase(docBase);
		resources = new ProxyDirContext(env, context);
	}
}