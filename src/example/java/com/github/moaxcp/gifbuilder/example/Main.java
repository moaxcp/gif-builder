package com.github.moaxcp.gifbuilder.example;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import static com.github.moaxcp.gifbuilder.GifMethods.gif;
import static com.github.moaxcp.gifbuilder.GifMethods.image;

public class Main {

  private static int count = 1;

  public static void main(String... args) throws IOException {
    gif("example.gif")
        .addImages(image(incrementImage()))
        .addImages(image(incrementImage()))
        .addImages(image(incrementImage()))
        .addImages(image(incrementImage()))
        .addImages(image(incrementImage()))
        .build()
        .create();
  }

  public static BufferedImage incrementImage() throws IOException {
    var image = new BufferedImage(600, 600, BufferedImage.TYPE_3BYTE_BGR);
    var graphics = image.getGraphics();
    graphics.setColor(Color.WHITE);
    graphics.drawRect(10 * count, 10 * count, 50, 50);
    graphics.dispose();
    ImageIO.write(image, "png", new File("image" + count + ".png"));
    count++;
    return image;
  }
}
