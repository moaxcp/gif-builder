package com.github.moaxcp.gifbuilder;

import java.awt.image.BufferedImage;
import java.nio.file.Path;

public class GifMethods {

  /**
   * Creates an ImageSpec for given image.
   * @param image
   * @return
   */
  public static ImageSpec image(BufferedImage image) {
    return ImageSpec.builder().image(image).build();
  }

  /**
   * Creates a GifSpec.Builder for given file.
   * @param file
   * @return
   */
  public static GifSpec.Builder gif(Path file) {
    return GifSpec.builder().file(file);
  }

  /**
   * Creates a GifSpec.Builder for given file.
   * @param file
   * @return
   */
  public static GifSpec.Builder gif(String file) {
    return GifSpec.builder().file(Path.of(file));
  }
}
