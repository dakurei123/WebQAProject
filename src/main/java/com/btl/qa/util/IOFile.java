package com.btl.qa.util;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class IOFile {
	// Lấy đường dẫn
	private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));

	// Xử lý ảnh, mới save lên server thôi. Ấn vào ảnh để upload.
	public static String saveImage(MultipartFile image, String Id) throws IOException {
		if (image.getSize() != 0) { // Kiểm tra xem user có sửa ảnh không?
			Path staticPath = Paths.get("static");
			Path imagePath = Paths.get(Id);

			if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))) {
				Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
			} // Chưa có thì tạo

			Path file = CURRENT_FOLDER.resolve(staticPath) // Đường dẫn file
					.resolve(imagePath).resolve(image.getOriginalFilename());
			try (OutputStream os = Files.newOutputStream(file)) {
				os.write(image.getBytes());
			} // Ghi file vào máy
			return (imagePath.resolve(image.getOriginalFilename()).toString());
		}
		return null;
	}
}
