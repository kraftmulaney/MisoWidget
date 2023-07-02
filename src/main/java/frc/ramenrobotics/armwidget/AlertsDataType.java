package frc.ramenrobotics.armwidget;

import edu.wpi.first.shuffleboard.api.data.ComplexDataType;
import java.util.Map;
import java.util.function.Function;

/**
 * Represents data of the {@link Alerts} type.
 */
public final class AlertsDataType extends ComplexDataType<Alerts> {

  /**
   * The name of data of this type as it would appear in a WPILib sendable's
   * {@code .type} entry; a differential drive base a {@code .type} of
   * "DifferentialDrive", a sendable chooser has it set to "String Chooser"; a
   * hypothetical 2D point would have it set to "Point2D".
   */
  private static final String TYPE_NAME = Constants.kAnimatedArmWidget;

  /**
   * The single instance of the point type. By convention, this is a
   * {@code public static final} field and the constructor is private to ensure
   * only a single instance of the data type exists.
   */
  public static final AlertsDataType Instance = new AlertsDataType();

  private AlertsDataType() {
    super(TYPE_NAME, Alerts.class);
  }

  @Override
  public Function<Map<String, Object>, Alerts> fromMap() {
    return map -> new Alerts(
        (double) map.getOrDefault("percentRaised", 0.0),
        (double) map.getOrDefault("percentExtended", 0.0),
        (boolean) map.getOrDefault("isClawOpen", false));
  }

  @Override
  public Alerts getDefaultValue() {
    return new Alerts(0.0, 0.0, false);
  }
}
