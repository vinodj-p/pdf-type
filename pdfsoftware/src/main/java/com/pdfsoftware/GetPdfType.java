package com.pdfsoftware;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class GetPdfType {
	
	private static final String filename = "c://temp/test.pdf";
	
	public static void main(String[] args) throws IOException {
		System.out.println("Pdf is :" + checkPdf(filename));

	}
	
	private static String checkPdf(String filename) throws IOException
	{
	    int pageCount = 0;
	    int dPageCount = 0;
	      PDDocument doc = PDDocument.load(new File(filename));
	
	      dPageCount = doc.getNumberOfPages();
	      for(PDPage page:doc.getPages())
	      {
	          PDResources resource = page.getResources();
	          for(COSName xObjectName:resource.getXObjectNames())
	            {
	                PDXObject xObject = resource.getXObject(xObjectName);
	                if (xObject instanceof PDImageXObject)
	                {
	                    ((PDImageXObject) xObject).getImage();
	                    pageCount++;
	                }
	
	            }
	
	      }
	      doc.close();
	     if(pageCount==dPageCount)  // pdf pages if equal to the images
	     {
	         return "Scanned pdf";
	     }
	     else
	     {
	         return "Searchable pdf";
	     }
	}
}
