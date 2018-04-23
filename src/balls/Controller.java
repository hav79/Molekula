package balls;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
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
        ballCount.valueProperty().addListener((observable, oldValue, newValue)
                -> countLabel.setText(String.valueOf(newValue.intValue())));

        timeline = new Timeline(new KeyFrame(Duration.millis(20),
                event -> {
                    for (int i = 0; i < balls.size(); i++) {
                        Ball ball = balls.get(i);
                        // переместить шар
                        ball.setLayoutX(ball.getLayoutX() + ball.getDx());
                        ball.setLayoutY(ball.getLayoutY() + ball.getDy());

                        // проверить столкновения с границами
                        if (ball.getLayoutX() <= ball.getRadius()) {
                            ball.setLayoutX(ball.getRadius());
                            ball.reverseX();
                        } else if (ball.getLayoutX() >= (canvas.getWidth() - ball.getRadius())) {
                            ball.setLayoutX(canvas.getWidth() - ball.getRadius());
                            ball.reverseX();
                        }
                        if (ball.getLayoutY() <= ball.getRadius()) {
                            ball.setLayoutY(ball.getRadius());
                            ball.reverseY();
                        } else if (ball.getLayoutY() >= (canvas.getHeight() - ball.getRadius())) {
                            ball.setLayoutY(canvas.getHeight() - ball.getRadius());
                            ball.reverseY();
                        }

                        // попытка учесть столкновения шаров
                        for (int j =  i + 1; j < balls.size(); j++) {
                            if (i == j) continue;
                            Ball ball2 = balls.get(j);
                            double distance = Math.hypot(Math.abs(ball.getLayoutX() - ball2.getLayoutX()),
                                                         Math.abs(ball.getLayoutY() - ball2.getLayoutY()));
                            if (distance <= 2 * ball.getRadius()
                                    && !ball.wasCollision() && !ball2.wasCollision()) {
                                ball.collisionTo(ball2);
                                ball2.collisionTo(ball);
                            } else {
                                ball.setWasCollision(false);
                                ball2.setWasCollision(false);
                            }
                        }
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
            Ball newBall = new Ball();
            newBall.relocate(Math.random() * canvas.getWidth() * 0.9,
                    Math.random() * canvas.getHeight() * 0.9);
            canvas.getChildren().add(newBall);
            balls.add(newBall);
        }
    }
}
