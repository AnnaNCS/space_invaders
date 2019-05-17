import java.awt.*;

public class PlayerShots extends GraphicsObject {

    Color color;
    public double width;
    public double height;

    public PlayerShots(double x, double y) {
        super(x, y);
        this.speed_y = 20.0;
        this.width = 6.0;
        this.height = 6.0;
    }

    public void draw(Graphics g) {
        g.setColor(color.BLUE);
        g.fillRect((int) Math.round(x), (int) Math.round(y), (int) Math.round(height), (int) Math.round(width));
    }

    public void update() {
        this.y -= this.speed_y;
        }
}

