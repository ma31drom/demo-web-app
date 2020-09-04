package by.pvt.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.hibernate.cfg.IndexOrUniqueKeySecondPass;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileSystemStorageService implements StorageService {

	@Override
	public String save(InputStream fileStream) {
		String string = UUID.randomUUID().toString();

		File file = new File(string);
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			IOUtils.copy(fileStream, fileOutputStream);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return file.getAbsolutePath();
	}

	@Override
	public InputStream read(String fileName) {
		try {
			return new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

}
