package net.abdus.resizeimage;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.abdus.resizeimage.RequestResponseEntity.Resize;

@SpringBootApplication
@RestController
public class ResizeImageApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResizeImageApplication.class, args);
	}

	@PostMapping("/resize")
	public Resize.ResponseBody resize(@RequestBody Resize.RequestBody requestBody) throws IOException {
		ImageOps imgOps = new ImageOps();

		if (requestBody.base64 != null && !requestBody.base64.isEmpty() && requestBody.scale > 0) {
			return imgOps.scaleImage(requestBody.base64, requestBody.scale);
		} else if (requestBody.url != null && !requestBody.url.isEmpty() && requestBody.scale > 0) {
			return imgOps.scaleRemoteImage(requestBody.url, requestBody.scale);
		}

		return new Resize.ResponseBody(null, null);
	}

}
