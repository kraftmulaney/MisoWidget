package org.littletonrobotics.networkalerts;

import org.littletonrobotics.networkalerts.Alerts.AlertItem;
import org.littletonrobotics.networkalerts.Alerts.AlertType;

import edu.wpi.first.shuffleboard.api.widget.Description;
import edu.wpi.first.shuffleboard.api.widget.ParametrizedController;
import edu.wpi.first.shuffleboard.api.widget.SimpleAnnotatedWidget;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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

@Description(name = "AlertsIdo", dataTypes = Alerts.class, summary = "Displays a list of alerts.")
@ParametrizedController("AlertsWidget.fxml")
public final class AlertsWidget extends SimpleAnnotatedWidget<Alerts> {

  private Image errorIcon = new Image(getClass().getResourceAsStream("img/error.png"));
  private Image warningIcon = new Image(getClass().getResourceAsStream("img/warning.png"));
  private Image infoIcon = new Image(getClass().getResourceAsStream("img/info.png"));

  @FXML
  private Pane root;

  @FXML
  private Canvas canvas;

  @FXML
  @SuppressWarnings("incomplete-switch")
  private void initialize() {
    //canvas.widthProperty().bind(this.widthProperty());
    //canvas.heightProperty().bind(this.heightProperty());

    double width = 100; // $TODO canvas.getLayoutBounds().getWidth();
    double height = 100; // $TODO canvas.getLayoutBounds().getHeight();

    canvas.setWidth(width);
    canvas.setHeight(height);

    GraphicsContext gc = canvas.getGraphicsContext2D();
    gc.clearRect(0, 0, width, height);

    gc.setStroke(Color.BLACK);
    gc.setLineWidth(2);
    gc.strokeLine(0, height / 2, width, height / 2);
  }

  @Override
  public Pane getView() {
    return root;
  }
}
