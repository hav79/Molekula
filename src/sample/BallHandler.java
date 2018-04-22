package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class BallHandler implements EventHandler<ActionEvent> {

    private final Pane pane;
    private final Circle ball;
    private double dx = 7;
    private double dy = 3;

    public BallHandler(Pane pane, Circle ball) {
        this.pane = pane;
        this.ball = ball;
    }

    @Override
    public void handle(ActionEvent event) {

        ball.setLayoutX(ball.getLayoutX() + dx);
        ball.setLayoutY(ball.getLayoutY() + dy);

        Bounds bounds = pane.getBoundsInLocal();

        if (ball.getLayoutX() <= (bounds.getMinX() + ball.getRadius())
                || ball.getLayoutX() >= (bounds.getMaxX() - ball.getRadius())) {
            dx = -dx;
        }

        if (ball.getLayoutY() <= (bounds.getMinY() + ball.getRadius())
                || ball.getLayoutY() >= (bounds.getMaxY() - ball.getRadius())) {
            dy = -dy;
        }

    }
}
