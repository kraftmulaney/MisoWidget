package org.littletonrobotics.networkalerts;

import javafx.scene.Group;
import javafx.scene.SnapshotParameters;
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
  public Image redrawRobotArm(
      ArmPosition armPosition,
      ExtenderPosition extenderPosition) {

    Group extenderAndClawGroup = m_extenderAndClaw.getExtenderAndClawGroup(extenderPosition);

    // $TODO - Cache the final image snapshot and only redraw when armPosition changes
    SnapshotParameters parameters = new SnapshotParameters();
    parameters.setFill(Color.TRANSPARENT);
    return extenderAndClawGroup.snapshot(parameters, null);
  }
}

