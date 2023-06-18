package org.littletonrobotics.networkalerts;

import edu.wpi.first.shuffleboard.api.widget.Description;
import edu.wpi.first.shuffleboard.api.widget.ParametrizedController;
import edu.wpi.first.shuffleboard.api.widget.SimpleAnnotatedWidget;
import javafx.fxml.FXML;
import javafx.scene.Group;
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
  private final double m_scaleImages = 0.25;
  private double m_tileWidth;
  private double m_tileHeight;

  private final Image m_armExtenderImage = new Image(getClass().getResourceAsStream(
      "img/ArmExtender.png"));
  private final Image m_armClawClosedImage = new Image(getClass().getResourceAsStream(
      "img/ArmClawClosed.png"));
  private final Image m_armClawOpenedImage = new Image(getClass().getResourceAsStream(
      "img/ArmClawOpen.png"));
  private final double m_pxLeftClawOpen = 4;
  private final double m_pxLeftClawClosed = 0;

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
  }

  private ImageView getScaledViewOfImage(Image img) {
    ImageView view = new ImageView(img);
    view.setFitWidth(img.getWidth() * m_scaleImages);
    view.setFitHeight(img.getHeight() * m_scaleImages);
    view.setSmooth(true);

    return view;
  }

  private Image calcExtenderAndClawImage(boolean isClawOpen) {
    Image clawToUse = isClawOpen
        ? m_armClawOpenedImage :
        m_armClawClosedImage;

    // The open claw needs to be shifted left a few pixels, to avoid gap between it
    // and the exxtender.
    double pxLeftMarginClaw = isClawOpen
        ? m_pxLeftClawOpen
        : m_pxLeftClawClosed;

    ImageView extenderView = getScaledViewOfImage(m_armExtenderImage);
    ImageView clawView = getScaledViewOfImage(clawToUse);

    double groupHeight = Math.max(
        m_armExtenderImage.getHeight(),
        clawToUse.getHeight());

    // Combine image views
    @SuppressWarnings("checkstyle:VariableDeclarationUsageDistance")
    Group group = new Group(extenderView, clawView);
    clawView.setLayoutX((m_armExtenderImage.getWidth() - pxLeftMarginClaw) * m_scaleImages);
    clawView.setLayoutY((groupHeight - clawToUse.getHeight()) * m_scaleImages / 2);
    extenderView.setLayoutY((groupHeight - m_armExtenderImage.getHeight()) * m_scaleImages / 2);

    // Create snapshot of combined image
    SnapshotParameters parameters = new SnapshotParameters();
    parameters.setFill(Color.BLUE); // $TODO (Color.TRANSPARENT);
    return group.snapshot(parameters, null);
  }

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

  private void redrawRobotArm() {
    m_tileWidth = root.getWidth();
    m_tileHeight = root.getHeight();

    if (m_tileWidth != 0 && m_tileHeight != 0) {
      GraphicsContext gc = canvas.getGraphicsContext2D();
      gc.clearRect(0, 0, m_tileWidth, m_tileHeight);

      // $TODO - I could be better about caching it
      Image resizedImage = calcExtenderAndClawImage(false);

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
