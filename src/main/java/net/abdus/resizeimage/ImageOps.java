package net.abdus.resizeimage;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Base64;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;

class ImageOps {
	private BufferedImage base64ToBin(String base64) throws IOException {
		final byte[] imgBytes = Base64.getDecoder().decode(base64);
		final ByteArrayInputStream byteArrInputStream = new ByteArrayInputStream(imgBytes);
		final BufferedImage image = ImageIO.read(byteArrInputStream);
		return image;
	}

	public String scaleImage(String base64, double scale) {
		try {
			BufferedImage img = this.base64ToBin(base64);
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
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}

	}
}
