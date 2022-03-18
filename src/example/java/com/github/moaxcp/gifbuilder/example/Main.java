package com.github.moaxcp.gifbuilder.example;

import com.github.moaxcp.gifbuilder.GifBuilder;
import com.github.moaxcp.gifbuilder.GifSpec;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

import static com.github.moaxcp.gifbuilder.GifMethods.gif;
import static com.github.moaxcp.gifbuilder.GifMethods.image;

public class Main {

  private static int count;

  public static void main(String... args) throws IOException {
    GifSpec gifSpec = gif("image.gif")
        .addImages(image(incrementImage()))
        .addImages(image(incrementImage()))
        .addImages(image(incrementImage()))
        .addImages(image(incrementImage()))
        .addImages(image(incrementImage()))
        .build();

    new GifBuilder()
        .file(Path.of("./image.gif"))
        .image(incrementImage())
        .image(incrementImage())
        .delay(100)
        .build();
  }

  public static BufferedImage incrementImage() {
    var image = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
    var graphics = image.createGraphics();
    graphics.drawRect(10 * count, 10 * count, 50, 50);

    return image;
  }
}
