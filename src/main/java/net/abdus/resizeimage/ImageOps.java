package net.abdus.resizeimage;

import java.awt.*;
import java.net.URL;
import java.util.Base64;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.awt.geom.AffineTransform;

class ImageOps {
	private BufferedImage base64ToBin(String base64) throws IOException {
		final byte[] imgBytes = Base64.getDecoder().decode(base64);
		final ByteArrayInputStream byteArrInputStream = new ByteArrayInputStream(imgBytes);
		final BufferedImage image = ImageIO.read(byteArrInputStream);
		return image;
	}

	private static BufferedImage downloadImageFromUrl(String url) throws IOException {
		InputStream remoteImage = new URL(url).openStream();
		BufferedImage img = ImageIO.read(remoteImage);
		return img;
	}

	public static String scaled(BufferedImage img, double scale) throws IOException {
		int width = (int) ((double) img.getWidth() * scale);
		int height = (int) ((double) img.getHeight() * scale);
		BufferedImage resized = new BufferedImage(width, height, img.getType());
		AffineTransform affineTransform = AffineTransform.getScaleInstance(scale, scale);

		Graphics2D graphics2d = resized.createGraphics();
		graphics2d.drawRenderedImage(img, affineTransform);
		graphics2d.dispose();

		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ImageIO.write(resized, "PNG", output);

		return Base64.getEncoder().encodeToString(output.toByteArray());
	}

	public String scaleImage(String base64, double scale) {
		try {
			BufferedImage img = this.base64ToBin(base64);
			return scaled(img, scale);
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}

	}

	public String scaleRemoteImage(String url, double scale) {
		try {
			BufferedImage img = downloadImageFromUrl(url);
			return scaled(img, scale);
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
