package org.littletonrobotics.networkalerts;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Helper utilities for images.
 */
public class ImageUtilities {
  /**
   * Scales an image DOWN, using scale factor in Constants class.
   */
  public static ImageView getScaledViewOfImage(Image img, Rectangle2D viewPort) {    
    ImageView view = new ImageView(img);
    view.setFitWidth(img.getWidth() * Constants.m_scaleImages);
    view.setFitHeight(img.getHeight() * Constants.m_scaleImages);
    view.setPreserveRatio(true);
    view.setSmooth(true);

    if (viewPort != null) {
      view.setViewport(viewPort);
    }

    return view;
  }
}
