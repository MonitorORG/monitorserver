package com.monitor.server.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.socket.server.util.ConstantsUtil;

@Controller 
public class AgentDownloadController {
	
	@Autowired
	private ConstantsUtil constantsUtil;
	
	 @RequestMapping("/agent-download")  
      public void downloadFile(HttpServletRequest request, HttpServletResponse response){		   
		 response.setCharacterEncoding("utf-8");  
         response.setContentType("multipart/form-data");
         response.setHeader("Content-Disposition", "attachment;fileName=" + constantsUtil.getAgentDownloadFilename());  
         
         System.out.println("Reaed agentDownloadPath: " + constantsUtil.getAgentDownloadPath());
         System.out.println("Filename: " + constantsUtil.getAgentDownloadFilename());
         
         File downloadFile = new File(constantsUtil.getAgentDownloadPath());
         String mimeType = request.getServletContext().getMimeType(constantsUtil.getAgentDownloadPath());
         if (mimeType == null) {
             mimeType = "application/zip";
         }
         System.out.println("MIME type: " + mimeType);
  
         // set content attributes for the response
         response.setContentType(mimeType);
         response.setContentLength((int) downloadFile.length());
          
         try {
        	 
             InputStream inputStream = new FileInputStream(downloadFile);
              OutputStream os = response.getOutputStream();  
              
              byte[] b=new byte[1024];  
              int length;  
              while((length = inputStream.read(b)) > 0){  
                  os.write(b, 0, length);  
              }  
              
              inputStream.close();
              
         } catch (FileNotFoundException e) {  
              e.printStackTrace();  
         } catch (IOException e) {  
              e.printStackTrace();  
         }  
     } 
}
