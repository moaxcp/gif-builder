package com.github.moaxcp.gifbuilder;

public enum DisposalMethod {
  NONE("none"),
  DO_NOT_DISPOSE("doNotDispose"),
  RESTORE_TO_BACKGROUND("restoreToBackgroundColor"),
  RESTORE_TO_PREVIOUS("restoreToPrevious");

  private String value;

  private DisposalMethod(String value) {
    this.value = value;
  }

  public String toString() {
    return value;
  }
}
