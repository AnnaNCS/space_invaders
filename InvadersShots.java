import java.awt.*;

public class InvadersShots extends GraphicsObject {

    Color color;
    public double width ;
    public double height;

    public InvadersShots(double x, double y) {
        super(x, y);
        this.speed_y = 5.0;
        this.width = 6.0;
        this.height = 6.0;
    }

    public void draw(Graphics g) {
        g.setColor(color.RED);
        g.fillRect((int) Math.round(x), (int) Math.round(y), (int) Math.round(height), (int) Math.round(width));
    }

    public void update() {
        this.y += this.speed_y;
        if (this.y == 680)
            this.speed_y = 0;
        }
}
