import java.awt.*;
import java.util.ArrayList;

public class Invaders extends GraphicsObject {

    Color color;
    public double height;
    public double width;
    public ArrayList<InvadersShots> invadersShotsArr;
    boolean deleted = false;

    public Invaders(double x, double y) {
        super(x,y);
        this.width = 60.0;
        this.height = 20.0;
        this.invadersShotsArr = new ArrayList<>();
    }

    public void update(double x, double y) {
        this.y = y;
        this.x = x;
        for (int i = 0; i < this.invadersShotsArr.size(); i += 1) {
            this.invadersShotsArr.get(i).update();
        }
        for (int i = 0; i < this.invadersShotsArr.size(); i += 1) {
            InvadersShots shot = this.invadersShotsArr.get(i);
            if (shot.y >= 560) {
                shot.speed_y = 0;
                invadersShotsArr.remove(shot);
            }
        }
    }

    public void shoot(){
        this.invadersShotsArr.add(new InvadersShots(this.x + this.width/2 - 3, this.y + this.height));
    }

    public void draw(Graphics g){
        if (this.deleted) {
            g.setColor(color.WHITE);
        } else {
            g.setColor(Color.BLACK);
            g.fillRect((int) Math.round(x), (int) Math.round(y), (int) Math.round(width), (int) Math.round(height));
        }
        for (int i = 0; i < this.invadersShotsArr.size(); i += 1) {
            this.invadersShotsArr.get(i).draw(g);
        }
    }
}

