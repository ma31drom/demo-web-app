package by.pvt.service;

import java.io.InputStream;

public interface StorageService {

	String save(InputStream fileStream);
	
	InputStream read(String fileName);
	
}
