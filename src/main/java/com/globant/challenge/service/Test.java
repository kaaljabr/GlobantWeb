package com.globant.challenge.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Test {
	
	public static void main(String[] args) {
		List<String> filesList = new ArrayList<String>();
		try {
			Files.walk(Paths.get("E:\\dev\\directory")).forEach(filePath -> {
			    if (Files.isRegularFile(filePath)) {
			        System.out.println(filePath.getFileName());			        
			    }
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
