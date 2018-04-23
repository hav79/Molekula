package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball extends Circle {

    private final Color[] colors = {Color.AQUAMARINE, Color.BEIGE, Color.BROWN, Color.CHOCOLATE, Color.CYAN,
            Color.FORESTGREEN, Color.FUCHSIA, Color.GOLD, Color.INDIGO, Color.KHAKI, Color.LIGHTCORAL,
            Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.SILVER, Color.VIOLET};

    private double v; // скорость
    private double angle;

    private double dx;
    private double dy;

    public Ball() {
        super(10);
        setFill(colors[(int) (Math.random() * colors.length)]);
        v = Math.random() * 10;
        angle = Math.random() * 360 * Math.PI / 180; // угол в радианах
        dx = v * Math.sin(angle);
        dy = v * Math.cos(angle);
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
