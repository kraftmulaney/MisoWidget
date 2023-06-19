package org.littletonrobotics.networkalerts;

import edu.wpi.first.shuffleboard.api.widget.Description;
import edu.wpi.first.shuffleboard.api.widget.ParametrizedController;
import edu.wpi.first.shuffleboard.api.widget.SimpleAnnotatedWidget;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

/**
 * This Widget shows a robot arm.
 */
@Description(name = "AlertsIdo", dataTypes = Alerts.class, summary = "Displays a list of alerts.")
@ParametrizedController("AlertsWidget.fxml")
public final class AlertsWidget extends SimpleAnnotatedWidget<Alerts> {
  private double m_tileWidth;
  private double m_tileHeight;
  private ExtenderAndClaw m_extenderAndClaw = new ExtenderAndClaw();

  @FXML
  @SuppressWarnings("checkstyle:MemberNameCheck")
  private Pane root;

  @FXML
  @SuppressWarnings("checkstyle:MemberNameCheck")
  private Canvas canvas;

  @FXML
  private void initialize() {
    canvas.widthProperty().bind(root.widthProperty());
    canvas.heightProperty().bind(root.heightProperty());

    // Redraw when size changes
    canvas.widthProperty().addListener(evt -> redrawRobotArm());
    canvas.heightProperty().addListener(evt -> redrawRobotArm());

    // Redraw on data change
    dataProperty().addListener((newValue) -> redrawRobotArm());
  }

  /*
  // $TODO - Remove this code.  I left it here for sample of rotation
  private Image getResizedImage(Image image, double width, double height) {
    double pivotX = 167;
    double pivotY = 340;

    ImageView imageView = new ImageView(image);

    imageView.setFitWidth(width);
    imageView.setFitHeight(height);
    imageView.setPreserveRatio(true);
    imageView.setSmooth(true); // apply smoothing

    // Apply rotation
    Rotate rotate = new Rotate(90, pivotX, pivotY);
    imageView.getTransforms().add(rotate);

    SnapshotParameters parameters = new SnapshotParameters();
    parameters.setFill(Color.TRANSPARENT);
    Image resizedImage = imageView.snapshot(parameters, null);

    return resizedImage;
  }
  */

  private void redrawRobotArm() {
    m_tileWidth = root.getWidth();
    m_tileHeight = root.getHeight();

    if (m_tileWidth != 0 && m_tileHeight != 0) {
      GraphicsContext gc = canvas.getGraphicsContext2D();
      gc.clearRect(0, 0, m_tileWidth, m_tileHeight);

      // Get arm position from Network Tables
      Alerts armData = getData();

      Image resizedImage = m_extenderAndClaw.getExtenderAndClawImage(
        new ExtenderPosition(
            armData.getPercentExtended(),
            armData.getIsClawOpen()));

      double imageX = (m_tileWidth - resizedImage.getWidth()) / 2;
      double imageY = (m_tileHeight - resizedImage.getHeight()) / 2;
      gc.drawImage(resizedImage, imageX, imageY);
    }
  }

  @Override
  public Pane getView() {
    return root;
  }
}
