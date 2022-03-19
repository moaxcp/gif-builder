package com.github.moaxcp.gifbuilder;

import java.awt.image.BufferedImage;
import java.util.Optional;
import org.immutables.value.Value;

/**
 * Specification for an image within the gif file.
 */
@Value.Immutable
public abstract class AbstractImageSpec {
  public abstract BufferedImage image();

  public abstract Optional<Integer> delay();

  public abstract Optional<GifDisposalMethod> disposalMethod();

  public abstract Optional<Integer> imageLeftPosition();

  public abstract Optional<Integer> imageTopPosition();

  @Value.Check
  protected void check() {
    delay().ifPresent(delay -> {
      if(delay < 0) {
        throw new IllegalArgumentException("delay must not be less than one.");
      }
    });
    imageLeftPosition().ifPresent(imageLeftPosition -> {
      if(imageLeftPosition < 0 || imageLeftPosition > 65535) {
        throw new IllegalArgumentException("imageLeftPosition must be a value of 0 to 65535.");
      }
    });
    imageTopPosition().ifPresent(imageTopPosition -> {
      if(imageTopPosition < 0 || imageTopPosition > 65535) {
        throw new IllegalArgumentException("imageTopPosition must be a value of 0 to 65535.");
      }
    });
  }
}
