package com.jin;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;

public class ImageUtils {

	public static BufferedImage fillTransparentPixels(BufferedImage image,
			Color fillColor) {
		int w = image.getWidth();
		int h = image.getHeight();
		BufferedImage image2 = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image2.createGraphics();
		g.setColor(fillColor);
		g.fillRect(0, 0, w, h);
		g.drawRenderedImage(image, null);
		g.dispose();
		return image2;
	}

	public static File createThumbnail(File input, String format,
			Boolean keepOriginal) {
		File output = null;
		try {
			BufferedImage image = ImageIO.read(input);
			String imageName = input.getPath();

			String outputFileName = imageName.substring(0,
					imageName.lastIndexOf("."));

			output = new File(outputFileName + "." + "jpg");
			image = Scalr.resize(image, Scalr.Method.QUALITY,
					Scalr.Mode.FIT_TO_WIDTH, 150, 100, Scalr.OP_ANTIALIAS);

			if (image.getColorModel().getTransparency() != Transparency.OPAQUE) {
				image = fillTransparentPixels(image, Color.WHITE);
			}

			ImageIO.write(image, "jpg", output);

			if (!keepOriginal) {
				input.delete();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;
	}
}
