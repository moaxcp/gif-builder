package com.github.moaxcp.gifbuilder;

import com.squareup.gifencoder.GifEncoder;
import com.squareup.gifencoder.ImageOptions;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.immutables.value.Value;

@Value.Immutable
public abstract class AbstractGifSpec {
  private int maxWidth;
  private int maxHeight;
  private int minWidth;
  private int minHeight;
  private int backgroundColor;

  public abstract Path file();
  public abstract List<ImageSpec> images();

  @Value.Default
  public int defaultDelay() {
    return 500;
  }

  @Value.Default
  public int loop() {
    return 0;
  }

  @Value.Default
  public GifDisposalMethod defaultDisposalMethod() {
    return GifDisposalMethod.NONE;
  }

  @Value.Default
  public boolean defaultInterlace() {
    return false;
  }

  @Value.Check
  protected void check() {
    if(loop() < 0) {
      throw new IllegalArgumentException("loop must not be less than zero.");
    }
    if(defaultDelay() < 0) {
      throw new IllegalArgumentException("defaultDelay must not be less than one.");
    }
  }

  public void create() throws IOException {
    initDimentions();
    backgroundColor = guessBackgroundColor();
    writeGif();
  }

  private void initDimentions() {
    for(var imageSpec : images()) {
      var image = imageSpec.image();
      if(image.getWidth() > maxWidth) {
        maxWidth = image.getWidth();
      }
      if(image.getHeight() > maxHeight) {
        maxHeight = image.getHeight();
      }
      if(image.getWidth() < minWidth) {
        minWidth = image.getWidth();
      }
      if(image.getHeight() < minHeight) {
        minHeight = image.getHeight();
      }
    }
  }

  private BufferedImage normalizeImage(BufferedImage image) {
    BufferedImage background = null;
    if(minWidth != maxWidth || minHeight != maxHeight) {
      background = createOutputImage(backgroundColor, maxWidth, maxHeight, BufferedImage.TYPE_3BYTE_BGR);
    }
    if (background != null) {
      return centerOnOutput(background, image);
    }
    return image;
  }

  private int guessBackgroundColor() {
    return images().get(0).image().getRGB(0, 0);
  }

  private BufferedImage createOutputImage(int background, int width, int height, int type) {
    BufferedImage out = new BufferedImage(width, height, type);
    Graphics2D g2d = out.createGraphics();
    g2d.setBackground(new Color(background));
    g2d.clearRect(0, 0, width, height);
    g2d.dispose();
    return out;
  }

  private BufferedImage centerOnOutput(BufferedImage output, BufferedImage image) {
    int x = (output.getWidth() - image.getWidth()) / 2;
    int y = (output.getHeight() - image.getHeight()) / 2;
    Graphics2D g2d = output.createGraphics();
    g2d.drawImage(image, x, y, image.getWidth(), image.getHeight(), null);
    g2d.dispose();
    return output;
  }

  private void writeGif() throws IOException {
    try(var out = new FileOutputStream(file().toFile())) {
      var encoder = new GifEncoder(out, maxWidth, maxHeight, loop());
      for (var imageSpec : images()) {
        var image = imageSpec.image();
        if(!(imageSpec.imageLeftPosition().isPresent() || imageSpec.imageTopPosition().isPresent())) {
          image = normalizeImage(image);
        }
        var rgb = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
        var options = options(imageSpec);
        encoder.addImage(rgb, image.getWidth(), options);
      }
      encoder.finishEncoding();
    }
  }

  private ImageOptions options(ImageSpec imageSpec) {
    var options = new ImageOptions();

    if(imageSpec.delay().isPresent()) {
      options.setDelay(imageSpec.delay().get(), TimeUnit.MILLISECONDS);
    } else {
      options.setDelay(defaultDelay(), TimeUnit.MILLISECONDS);
    }

    if(imageSpec.disposalMethod().isPresent()) {
      options.setDisposalMethod(imageSpec.disposalMethod().get().getValue());
    } else {
      options.setDisposalMethod(defaultDisposalMethod().getValue());
    }

    return options;
  }
}
