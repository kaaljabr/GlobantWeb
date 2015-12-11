package com.globant.challenge.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.globant.challenge.exception.ServiceException;

@Service
public class FileServiceImpl implements FileService {

	private static Logger log = LoggerFactory.getLogger(FileServiceImpl.class);

	public FileServiceImpl() {

	}

	@Override
	public List<String> readFiles(String directory) throws ServiceException {
		List<String> filesList = new ArrayList<String>();
		try {
			Files.walk(Paths.get(directory)).forEach(filePath -> {
				if (Files.isRegularFile(filePath)) {
					log.debug(filePath.getFileName().toString());
					filesList.add(filePath.getFileName().toString());
				}
			});
		} catch (IOException e) {
			log.error("IOException could directory not found {}", directory);
			throw new ServiceException("Directory not found", e);
		}
		return filesList;
	}

}
