package org.littletonrobotics.networkalerts;

/**
 * Describes current position of extender and claw.
 */
public class ExtenderPosition {
  public double m_extendedPercent;
  public boolean m_isClawOpen;

  /**
   * Constructor.
   */
  public ExtenderPosition(double extendedPercent, boolean isClawOpen) {
    if (extendedPercent < 0) {
      extendedPercent = 0;
    }
    else if (extendedPercent > 1) {
      extendedPercent = 1;
    }

    m_extendedPercent = extendedPercent;
    m_isClawOpen = isClawOpen;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof ExtenderPosition)) {
      return false;
    }
    ExtenderPosition other = (ExtenderPosition) obj;
    return Double.compare(
        m_extendedPercent, other.m_extendedPercent) == 0
        && m_isClawOpen == other.m_isClawOpen;
  }
}

