package org.littletonrobotics.networkalerts;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * Draws entire Robot arm.
 */
public class RobotArmRenderer {
  private ExtenderAndClaw m_extenderAndClaw = new ExtenderAndClaw();

  /**
   * Constructor.
   */
  public RobotArmRenderer() {
  }

  /**
   * Redraw the entire robot arm.
   */
  public void redrawRobotArm(
      GraphicsContext gcSmall,
      double smallCanvasWidth,
      double smallCanvasHeight,
      ArmPosition armPosition,
      ExtenderPosition extenderPosition) {

    gcSmall.setFill(Color.ROYALBLUE);
    gcSmall.fillRect(0, 0, smallCanvasWidth, smallCanvasHeight);

    Image resizedImage = m_extenderAndClaw.getExtenderAndClawImage(extenderPosition);

    double imageX = (smallCanvasWidth - resizedImage.getWidth()) / 2;
    double imageY = (smallCanvasHeight - resizedImage.getHeight()) / 2;
    gcSmall.drawImage(resizedImage, imageX, imageY);
  }
}

