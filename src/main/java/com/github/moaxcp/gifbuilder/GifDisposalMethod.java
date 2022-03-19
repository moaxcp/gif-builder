package com.github.moaxcp.gifbuilder;

import com.squareup.gifencoder.DisposalMethod;

public enum GifDisposalMethod {
  NONE(DisposalMethod.UNSPECIFIED),
  DO_NOT_DISPOSE(DisposalMethod.DO_NOT_DISPOSE),
  RESTORE_TO_BACKGROUND(DisposalMethod.RESTORE_TO_BACKGROUND),
  RESTORE_TO_PREVIOUS(DisposalMethod.RESTORE_TO_PREVIOUS);

  private DisposalMethod value;

  GifDisposalMethod(DisposalMethod value) {
    this.value = value;
  }

  DisposalMethod getValue() {
    return value;
  }

  public String toString() {
    return value.toString();
  }
}
