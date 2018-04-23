package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
//import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

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

        if (ball.getLayoutX() <= ball.getRadius()
                || ball.getLayoutX() >= (pane.getWidth() - ball.getRadius()))
            dx = -dx;

        if (ball.getLayoutY() <= ball.getRadius()
                || ball.getLayoutY() >= (pane.getHeight() - ball.getRadius()))
            dy = -dy;
    }
}
