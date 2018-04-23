package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;

public class Controller {

    @FXML
    private Pane canvas;

    @FXML
    private Button startStopButton;
    private boolean isRunning = false;

    @FXML
    private Slider ballCount;
    @FXML
    private Label countLabel;

    private ArrayList<Ball> balls = new ArrayList<>();
    private Timeline timeline;

    @FXML
    public void initialize() {
        timeline = new Timeline(new KeyFrame(Duration.millis(20),
                event -> {
                    for (Ball ball : balls) {
                        ball.setLayoutX(ball.getLayoutX() + ball.getDx());
                        ball.setLayoutY(ball.getLayoutY() + ball.getDy());

                        if (ball.getLayoutX() <= ball.getRadius()
                                || ball.getLayoutX() >= (canvas.getWidth() - ball.getRadius()))
                            ball.reverseX();

                        if (ball.getLayoutY() <= ball.getRadius()
                                || ball.getLayoutY() >= (canvas.getHeight() - ball.getRadius()))
                            ball.reverseY();
                    }
                }));
        timeline.setCycleCount(Timeline.INDEFINITE);

        startStopButton.setOnAction(event -> {
            if (isRunning) {
                timeline.stop();
                startStopButton.setText("Start");
                ballCount.setDisable(false);
                isRunning = false;
            } else {
                isRunning = true;
                ballCount.setDisable(true);
                startStopButton.setText("Stop");
                initializeBalls();
                timeline.play();
            }
        });
    }

    private void initializeBalls() {
        int count = (int) ballCount.getValue();
        balls.clear();
        canvas.getChildren().clear();
        for (int i = 0; i < count; i++) {
            Ball newBall = new Ball(10, Color.AQUAMARINE);
            newBall.relocate(Math.random() * canvas.getWidth() * 0.9,
                    Math.random() * canvas.getHeight() * 0.9);
            canvas.getChildren().add(newBall);
            balls.add(newBall);
        }
    }
}
