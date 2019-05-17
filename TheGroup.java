import java.awt.*;
import java.util.ArrayList;

public class TheGroup extends GraphicsObject {


    ArrayList<Invaders> theGroup;

    public TheGroup(double x, double y) {
        super(x, y);
        this.theGroup = new ArrayList<>();
        this.speed_x = 3.0;
        this.speed_y = 20.0;

        for (int j = 0; j < 5; j +=1){
            for (int i = 0; i < 5; i +=1){
                this.theGroup.add(new Invaders(this.x + 66 * i, this.y + 30 * j));
            }
        }
    }

    public void update() {
        this.x += speed_x;
        if (this.x == 390) {
            this.y += this.speed_y;
            this.speed_x = -3.0;
        } else if (this.x == 60) {
            this.y += this.speed_y;
            this.speed_x = 3.0;
        }

        for (int j = 0; j < 5; j += 1) {
            for (int i = 0; i < 5; i += 1) {
                this.theGroup.get(j + (i * 5)).update(this.x + 66 * i, this.y + 30 * j);
                if (Math.random() < 0.002) {
                    Invaders block = theGroup.get(j + (i * 5));
                    if (block.deleted == false) {
                        this.theGroup.get(j + (i * 5)).shoot();
                    }
                }
            }
        }
    }

    public void draw(Graphics g) {
        for (Invaders p : theGroup) {
            p.draw(g);
        }
    }
}
