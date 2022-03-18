package com.github.moaxcp.gifbuilder;

import java.awt.image.RenderedImage;
import java.nio.file.Path;

public class GifMethods {
  public static ImageSpec image(RenderedImage image) {
    return ImageSpec.builder().image(image).build();
  }

  public static GifSpec.Builder gif(Path file) {
    return GifSpec.builder().file(file);
  }

  public static GifSpec.Builder gif(String file) {
    return GifSpec.builder().file(Path.of(file));
  }
}
