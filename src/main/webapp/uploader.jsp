<%@ page  
   import="java.io.*"   
   import="java.util.*"   
   import="org.apache.commons.fileupload.*"  
   contentType="text/plain"  
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="tpdb"%>
<tpdb:page name="contribute">
  <tpdb:content>
  <%  
   boolean isMultipart = FileUpload.isMultipartContent(request);  
   if (isMultipart) {  
      // Create a new file upload handler  
      DiskFileUpload upload = new DiskFileUpload();  
        
      // Set upload parameters  
      upload.setSizeMax(50*1024*1024); //50Mb  
      upload.setRepositoryPath("/");  
        
      // Parse the request  
      List items = upload.parseRequest(request);  
        
      Iterator it = items.iterator();  
      while (it.hasNext()) {  
         FileItem fitem = (FileItem) it.next();  
         if (!fitem.isFormField()) {  
%><%= fitem.getName() %> - <%= fitem.getSize() %> bytes  
<%  
         }  
      }  
   }  
%>  
  </tpdb:content>
  <tpdb:sidebar />
</tpdb:page>