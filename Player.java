import java.awt.*;

public class Player extends GraphicsObject {

    Color color;
    public double width;
    public double height;

    public Player(double x, double y) {
        super(x, y);
        this.speed_x = 15.0;
        this.width = 60.0;
        this.height = 20.0;
    }

    public void draw(Graphics g) {
        g.setColor(color.BLUE);
        g.fillRect((int)Math.round(x), (int)Math.round(y), (int)Math.round(width), (int)Math.round(height));
    }
}
