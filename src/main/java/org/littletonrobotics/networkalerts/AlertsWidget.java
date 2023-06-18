package org.littletonrobotics.networkalerts;

import edu.wpi.first.shuffleboard.api.widget.Description;
import edu.wpi.first.shuffleboard.api.widget.ParametrizedController;
import edu.wpi.first.shuffleboard.api.widget.SimpleAnnotatedWidget;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

/**
 * This Widget shows a robot arm.
 */
@Description(name = "AlertsIdo", dataTypes = Alerts.class, summary = "Displays a list of alerts.")
@ParametrizedController("AlertsWidget.fxml")
public final class AlertsWidget extends SimpleAnnotatedWidget<Alerts> {
  private Image _armClawClosedImage = new Image(getClass().getResourceAsStream("img/ArmClawClosed.png"));

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
    canvas.widthProperty().addListener(evt -> drawRobotArm());
    canvas.heightProperty().addListener(evt -> drawRobotArm());
  }

  private Image calcResizedImage(Image image, double width, double height) {
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

  private void drawRobotArm() {
    double width = root.getWidth();
    double height = root.getHeight();

    if (width != 0 && height != 0) {
      GraphicsContext gc = canvas.getGraphicsContext2D();
      gc.clearRect(0, 0, width, height);

      // $TODO - I could be better about caching it
      Image resizedImage = calcResizedImage(_armClawClosedImage, 523, 250);

      double imageX = (width - resizedImage.getWidth()) / 2;
      double imageY = (height - resizedImage.getHeight()) / 2;
      gc.drawImage(resizedImage, imageX, imageY);
    }
  }

  private void drawLine() {
    double width = root.getWidth();
    double height = root.getHeight();

    if (width != 0 && height != 0) {
      GraphicsContext gc = canvas.getGraphicsContext2D();
      gc.clearRect(0, 0, width, height);

      gc.setStroke(Color.BLACK);
      gc.setLineWidth(2);
      gc.strokeLine(0, height / 2, width, height / 2);
    }
  }

  @Override
  public Pane getView() {
    return root;
  }
}
