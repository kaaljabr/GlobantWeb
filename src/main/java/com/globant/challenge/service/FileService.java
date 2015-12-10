package com.globant.challenge.service;

import java.util.List;



import com.globant.challenge.exception.ServiceException;

public interface FileService {

	public List<String> readFiles(String directory) throws ServiceException;

}
