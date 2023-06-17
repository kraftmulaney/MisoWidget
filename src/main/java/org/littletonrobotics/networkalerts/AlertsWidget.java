package org.littletonrobotics.networkalerts;

import org.littletonrobotics.networkalerts.Alerts.AlertItem;
import org.littletonrobotics.networkalerts.Alerts.AlertType;

import edu.wpi.first.shuffleboard.api.widget.Description;
import edu.wpi.first.shuffleboard.api.widget.ParametrizedController;
import edu.wpi.first.shuffleboard.api.widget.SimpleAnnotatedWidget;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;

@Description(name = "AlertsIdo", dataTypes = Alerts.class, summary = "Displays a list of alerts.")
@ParametrizedController("AlertsWidget.fxml")
public final class AlertsWidget extends SimpleAnnotatedWidget<Alerts> {

  private Image errorIcon = new Image(getClass().getResourceAsStream("img/error.png"));
  private Image warningIcon = new Image(getClass().getResourceAsStream("img/warning.png"));
  private Image infoIcon = new Image(getClass().getResourceAsStream("img/info.png"));
  // $TODO private Image ArmBaseIcon = new
  // Image(getClass().getResourceAsStream("img/armbase.png"));
  private Image PivotArmIcon = new Image(getClass().getResourceAsStream("img/PivotArm.png"));

  @FXML
  private Pane root;

  @FXML
  private Canvas canvas;

  @FXML
  @SuppressWarnings("incomplete-switch")
  private void initialize() {
    canvas.widthProperty().bind(root.widthProperty());
    canvas.heightProperty().bind(root.heightProperty());

    // Redraw when size changes
    canvas.widthProperty().addListener(evt -> DrawLine());
    canvas.heightProperty().addListener(evt -> DrawLine());
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

  private void DrawLine() {
    double width = root.getWidth();
    double height = root.getHeight();

    if (width != 0 && height != 0) {
      GraphicsContext gc = canvas.getGraphicsContext2D();
      gc.clearRect(0, 0, width, height);

      /*
       * $TODO
       * gc.setStroke(Color.BLACK);
       * gc.setLineWidth(2);
       * gc.strokeLine(0, height / 2, width, height / 2);
       */

      // $TODO - I could be better about caching it
      Image resizedImage = calcResizedImage(PivotArmIcon, 523, 250);

      double imageX = (width - resizedImage.getWidth()) / 2;
      double imageY = (height - resizedImage.getHeight()) / 2;
      gc.drawImage(resizedImage, imageX, imageY);
    }
  }

  @Override
  public Pane getView() {
    return root;
  }
}
