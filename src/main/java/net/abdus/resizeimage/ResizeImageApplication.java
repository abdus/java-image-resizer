package net.abdus.resizeimage;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.abdus.resizeimage.RequestBody.UrlEntity;

@SpringBootApplication
@RestController
public class ResizeImageApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResizeImageApplication.class, args);
	}

	@PostMapping("/resize")
	public String resize(@RequestBody UrlEntity urlEntity) throws IOException {

		ImageOps imgOps = new ImageOps();
		String imageInBase64 = imgOps.scaleImage(urlEntity.url, 0.01);

		return imageInBase64 != null ? "data:image/png;base64," + imageInBase64 : "SOMETHING WENT WRONG";
	}

}
