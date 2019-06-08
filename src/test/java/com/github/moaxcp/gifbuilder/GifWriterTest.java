package com.github.moaxcp.gifbuilder;

import mockit.Injectable;
import org.junit.jupiter.api.Test;

import javax.imageio.stream.ImageOutputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GifWriterTest {

    @Injectable
    private ImageOutputStream out;
}
