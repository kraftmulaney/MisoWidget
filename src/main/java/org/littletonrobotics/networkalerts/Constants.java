package org.littletonrobotics.networkalerts;

/**
 * Constants for the robot arm widget.
 */
public class Constants {
  public static final double m_scaleImages = 0.25;

  // Claw and extender constants
  public static final double m_OpenClawMarginPixels = 4;
  public static final double m_ClosedClawMarginPixels = 0;
  public static final double m_FullyExtendedTrimRightPixels = 0;
  public static final double m_FullyRetractedTrimRightPixles = 200;

  // Robot base constants
  public static final double m_armPivotCenterX = 92;
  public static final double m_armPivotCenterY = 96;
  public static final double m_armPivotCenterToExtender = 120;

  // Arm rotation constants
  public static final double m_armDisplayRotationLowest = 50;
  public static final double m_armDisplayRotationHighest = -10;
}

