package com.SpringBootreportpdfexample;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.Document;

@RestController
@RequestMapping("api")
public class pdfcontroller {
	
	@Autowired
	PDFGenerator pdfGenerator;
	
	
	@GetMapping(value = "/report", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> getReport() {
	    byte[] pdfContent = pdfGenerator.generatePdfReport();
	    ByteArrayInputStream bis = new ByteArrayInputStream(pdfContent);
	    HttpHeaders headers = new HttpHeaders();
	    headers.add("Content-Disposition", "inline; filename=report.pdf");
	    return ResponseEntity
	            .ok()
	            .headers(headers)
	            .contentType(MediaType.APPLICATION_PDF)
	            .body(new InputStreamResource(bis));
	}
}
