# gif-builder

A java dsl for building gifs using [gifencoder](https://github.com/square/gifencoder).

# DSL

The dsl starts with static methods from `GifMethods`. Start with a `GifSpec.Builder`.

```
gif("image.gif");
```

Various options can be added including loop and the defaults for delay and disposal method. Delay is in milliseconds although this will be converted to deciseconds (standard for GIF).

Note: interlace and transparency is not supported by gifencoder and so are not supported here.

```
gif("image.gif")
    .defaultDelay(100)
    .defaultDisposalMethod(RESTORE_TO_BACKGROUND);
```

Once defaults are set images can be added. This uses the `ImageSpec`.
```
gif("image.gif")
    .defaultDelay(100)
    .defaultDisposalMethod(RESTORE_TO_BACKGROUND)
    .addImages(image(image1))
    .addImages(image(image2));
```

Images are BufferedImages.

An `ImageSpec` can contain other options for the gif sequence: delay, disposalMethod, leftPosition, topPosition.

Once the images are added to the `GifSpec` the specification can be built.

```
gif("image.gif")
    .defaultDelay(100)
    .defaultDisposalMethod(RESTORE_TO_BACKGROUND)
    .addImages(image(image1))
    .addImages(image(image2))
    .build();
```

The image can then be created.

```
gif("image.gif")
    .defaultDelay(100)
    .defaultDisposalMethod(RESTORE_TO_BACKGROUND)
    .addImages(image(image1))
    .addImages(image(image2))
    .build()
    .create();
```

# Conventions

* GIF size will match largest image
* If image is smaller than size it will be centered with a background
* Background of smaller image is guessed from pixel 1,1

# Example

Animate a moving square

```java
public class Main {

  private static int count = 1;

  public static void main(String... args) throws IOException {
    gif("gif.gif")
        .addImages(image(incrementImage()))
        .addImages(image(incrementImage()))
        .addImages(image(incrementImage()))
        .addImages(image(incrementImage()))
        .addImages(image(incrementImage()))
        .build()
        .create();
  }

  public static BufferedImage incrementImage() throws IOException {
    var image = new BufferedImage(600, 600, BufferedImage.TYPE_3BYTE_BGR);
    var graphics = image.getGraphics();
    graphics.setColor(Color.WHITE);
    graphics.drawRect(10 * count, 10 * count, 50, 50);
    graphics.dispose();
    ImageIO.write(image, "png", new File("image" + count + ".png"));
    count++;
    return image;
  }
}
```

# Versions

## 0.1.0

Initial implementation