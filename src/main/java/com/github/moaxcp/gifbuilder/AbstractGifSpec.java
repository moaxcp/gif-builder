package com.github.moaxcp.gifbuilder;

import java.nio.file.Path;
import java.util.List;
import org.immutables.value.Value;

@Value.Immutable
public abstract class AbstractGifSpec {
  public abstract Path file();
  public abstract List<ImageSpec> images();

  @Value.Default
  public int defaultDelay() {
    return 50;
  }

  @Value.Default
  public int loop() {
    return 0;
  }

  @Value.Default
  public DisposalMethod defaultDisposalMethod() {
    return DisposalMethod.NONE;
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
}
