package org.littletonrobotics.networkalerts;

import javafx.scene.Group;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * Constructs image of Extender and Claw, into a single
 * image.
 */
public class ExtenderAndClaw {
  private final Image m_armExtenderImage = new Image(getClass().getResourceAsStream(
      "img/ArmExtender.png"));
  private final Image m_armClawClosedImage = new Image(getClass().getResourceAsStream(
      "img/ArmClawClosed.png"));
  private final Image m_armClawOpenedImage = new Image(getClass().getResourceAsStream(
      "img/ArmClawOpen.png"));

  /**
   * Constructor.
   */
  public ExtenderAndClaw() {
  }

  /**
   * Returns an image of the extender and claw.
   */
  public Image getExtenderAndClawImage(boolean isClawOpen) {
    Image clawToUse = isClawOpen
        ? m_armClawOpenedImage :
        m_armClawClosedImage;

    // The open claw needs to be shifted left a few pixels, to avoid gap between it
    // and the exxtender.
    double pxLeftMarginClaw = isClawOpen
        ? Constants.m_OpenClawMarginPixels
        : Constants.m_ClosedClawMarginPixels;

    ImageView extenderView = ImageUtilities.getScaledViewOfImage(m_armExtenderImage, null);
    ImageView clawView =  ImageUtilities.getScaledViewOfImage(clawToUse, null);

    double groupHeight = Math.max(
        m_armExtenderImage.getHeight(),
        clawToUse.getHeight());

    // Combine image views
    @SuppressWarnings("checkstyle:VariableDeclarationUsageDistance")
    Group group = new Group(extenderView, clawView);

    clawView.setLayoutX(
          (m_armExtenderImage.getWidth() - pxLeftMarginClaw)
           * Constants.m_scaleImages);

    clawView.setLayoutY(
          (groupHeight - clawToUse.getHeight())
          * Constants.m_scaleImages / 2);

    extenderView.setLayoutY(
          (groupHeight - m_armExtenderImage.getHeight())
          * Constants.m_scaleImages / 2);

    // Create snapshot of combined image
    SnapshotParameters parameters = new SnapshotParameters();
    parameters.setFill(Color.TRANSPARENT);
    return group.snapshot(parameters, null);
  }
}
