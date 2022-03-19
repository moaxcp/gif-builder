package com.github.moaxcp.gifbuilder;


import org.immutables.value.Value;

@Value.Immutable
abstract class AbstractCreateInfo {
  public abstract int maxWidth();
  public abstract int maxHeight();
  public abstract int minWidth();
  public abstract int minHeight();
  public abstract int backgroundColor();
}
