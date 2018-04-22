package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Controller {

    @FXML
    private Pane canvas;

    @FXML
    private Circle ball;

    private Timeline timeline;

    @FXML
    public void initialize() {
        ball.relocate(5, 5);

        timeline = new Timeline(new KeyFrame(Duration.millis(20), new BallHandler(canvas, ball)));
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    public void run() {
        timeline.play();
    }
}
