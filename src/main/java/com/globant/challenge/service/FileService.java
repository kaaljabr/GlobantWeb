package com.globant.challenge.service;

import java.util.List;

import com.globant.challenge.exception.ServiceException;

public interface FileService {
	/**
	 * This method is used to return a list of file names in a given directory
	 *
	 * @param directory
	 *            Folder path to look for files inside
	 * @return List of files in the provided directory
	 * @throws ServiceException
	 *             Any error thrown by DAO layer is wrapped and thrown as ServiceException
	 */
	public List<String> readFiles(String directory) throws ServiceException;

}
