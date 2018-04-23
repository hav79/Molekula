package sample;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Ball extends Circle {
    private double dx = 7;
    private double dy = 3;

    public Ball(double radius, Paint fill) {
        super(radius, fill);
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    public void reverseX() {
        dx = -dx;
    }

    public void reverseY() {
        dy = -dy;
    }
}
