// utility
import java.awt.*;
import java.util.ArrayList;

// graphics

// events
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// swing
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SpaceInvaders extends JPanel implements ActionListener, KeyListener, Runnable {

    private final int canvasWidth;
    private final int canvasHeight;
    private final Color backgroundColor;

    private final int framesPerSecond = 25;
    private final int msPerFrame = 1000 / framesPerSecond;
    private Timer timer;
    private int frame = 0;

    // FIXME list your game objects here
    public ArrayList<PlayerShots> playerShots;
    public Player player;
    public TheGroup theGroup;

    /* Constructor for a Space Invaders game
     */
    public SpaceInvaders() {
        // fix the window size and background color
        this.canvasWidth = 800;
        this.canvasHeight = 600;
        this.backgroundColor = Color.WHITE;
        setPreferredSize(new Dimension(this.canvasWidth, this.canvasHeight));

        // set the drawing timer
        this.timer = new Timer(msPerFrame, this);

        // FIXME initialize your game objects
        this.player = new Player(370, 560);
        this.theGroup = new TheGroup(60, 20);
        this.playerShots = new ArrayList<>();
    }

    /* Start the game
     */
    @Override
    public void run() {
        // show this window
        display();
        // set a timer to redraw the screen regularly
        this.timer.start();
    }

    /* Create the window and display it
     */
    private void display() {
        JFrame jframe = new JFrame();
        jframe.addKeyListener(this);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setContentPane(this);
        jframe.pack();
        jframe.setVisible(true);
    }

    /* Run all timer-based events
     *
     * @param e  An object describing the timer
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // update the game objects
        update();

        // draw every object (this calls paintComponent)
        repaint(0, 0, this.canvasWidth, this.canvasHeight);

        // increment the frame counter
        this.frame++;
    }

    /* Paint/Draw the canvas.
     *
     * This function overrides the paint function in JPanel. This function is
     * automatically called when the panel is made visible.
     *
     * @param g The Graphics for the JPanel
     */
    @Override
    protected void paintComponent(Graphics g) {
        // clear the canvas before painting
        clearCanvas(g);

        if (hasWonGame()) {
            paintWinScreen(g);
        } else if (hasLostGame()) {
            paintLoseScreen(g);
        } else {
            paintGameScreen(g);
        }
    }

    /* Clear the canvas
     *
     * @param g The Graphics representing the canvas
     */
    private void clearCanvas(Graphics g) {
        Color oldColor = g.getColor();
        g.setColor(this.backgroundColor);
        g.fillRect(0, 0, this.canvasWidth, this.canvasHeight);
        g.setColor(oldColor);
    }

    /* Respond to key release events
     *
     * A key release is when you let go of a key
     * 
     * @param e  An object describing what key was released
     */
    public void keyReleased(KeyEvent e) {
        // you can leave this function empty
    }

    /* Respond to key type events
     *
     * A key type is when you press then let go of a key
     * 
     * @param e  An object describing what key was typed
     */
    public void keyTyped(KeyEvent e) {
        // you can leave this function empty
    }

    /* Respond to key press events
     *
     * A key type is when you press then let go of a key
     * 
     * @param e  An object describing what key was typed
     */
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (this.player.x >= 60) {
            this.player.x -= this.player.speed_x; }
            // FIXME what happens when left arrow is pressed
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (player.x <= 680) {
            this.player.x += this.player.speed_x; }
            // FIXME what happens when right arrow is pressed
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            this.playerShots.add(new PlayerShots(this.player.x + 27, 560));
            // FIXME what happens when space bar is pressed
        }
    }

    /* Update the game objects
     */
    private void update() {
        this.theGroup.update();
        int i = 0;
        while (i < this.playerShots.size()) {
            this.playerShots.get(i).update();
            i += 1;
        }
    }
    // FIXME update game objects here

    /* Check if the player has lost the game
     * 
     * @returns  true if the player has lost, false otherwise
     */
    private boolean hasLostGame() {
        if (this.player.speed_x != (0))
        return false; {
        } return true;
    }
    // FIXME delete this when ready

    /* Check if the player has won the game
     * 
     * @returns  true if the player has won, false otherwise
     */
    public boolean hasWonGame() {
        for(Invaders b : this.theGroup.theGroup) {
            if(!b.deleted) return false;
        } return true;
    }
    // FIXME delete this when ready

    private void finalCollision() {
        for (int i = 0; i < this.theGroup.theGroup.size(); i += 1) {
            Invaders block = this.theGroup.theGroup.get(i);
            if (block.deleted ==  false) {
                double leftBlock = block.x;
                double rightBlock = block.x + block.width;
                double bottomBlock = block.y + block.height;
                double leftPlayer = this.player.x;
                double rightPlayer = this.player.x + this.player.width;
                double topPlayer = this.player.y;
                if (((leftBlock < leftPlayer && rightBlock > leftPlayer)
                        || (leftBlock < rightPlayer && rightBlock > rightPlayer))
                        && ((bottomBlock > topPlayer ))) {
                    this.player.speed_x = 0;
                }
            }
        }
    }

    private void collision() {
        for (int i = 0; i < this.theGroup.theGroup.size(); i += 1) {
            for (int j = 0; j < this.playerShots.size(); j += 1) {
                Invaders block = this.theGroup.theGroup.get(i);
                PlayerShots shot = this.playerShots.get(j);
                if (block.deleted == false) {
                    double leftBlock = block.x;
                    double rightBlock = block.x + block.width;
                    double topBlock = block.y;
                    double bottomBlock = block.y + block.height;
                    double leftShot = shot.x;
                    double rightShot = shot.x + shot.width;
                    double topShot = shot.y;
                    double bottomShot = shot.y + shot.height;
                    if (((leftBlock < leftShot && rightBlock > leftShot)
                            || (leftBlock < rightShot && rightBlock > rightShot))
                            && ((topBlock < topShot && bottomBlock > topShot)
                            || (topBlock < bottomShot && bottomBlock > bottomShot))) {
                        this.theGroup.theGroup.get(i).deleted = true;
                        this.playerShots.remove(j);
                    }
                    if (shot.y <= 20) {
                        this.playerShots.remove(j);
                    }
                }
            }
        }
    }

    public void attacking() {
        for (int j = 0; j < this.theGroup.theGroup.size(); j += 1) {
            for (int i = 0; i < this.theGroup.theGroup.get(j).invadersShotsArr.size(); i += 1) {
                InvadersShots shot = this.theGroup.theGroup.get(j).invadersShotsArr.get(i);
                double leftShot = shot.x;
                double rightShot  = shot.x + shot.width;
                double topShot  = shot.y;
                double bottomShot = shot.y + shot.height;
                double leftPlayer = this.player.x;
                double rightPlayer = this.player.x + this.player.width;
                double topPlayer = this.player.y;
                double bottomPlayer = this.player.y + this.player.height;
                if (((bottomPlayer > bottomShot && topPlayer < bottomShot) ||
                        (bottomPlayer > topShot && topPlayer < topShot)) &&
                        ((leftPlayer < leftShot && rightPlayer > leftShot) ||
                                (leftPlayer < rightShot && rightPlayer > rightShot))) {
                    this.player.speed_x = 0;
                }
            }
        }
    }

    public void pastTheLine() {
        for (int i = 0; i < this.theGroup.theGroup.size(); i += 1) {
            Invaders invaders = this.theGroup.theGroup.get(i);
            if (invaders.y >= this.player.y + this.player.height && invaders.deleted == false) {
                this.player.speed_x = 0;
            }
        }
    }

    /* Paint the screen during normal gameplay
     *
     * @param g The Graphics for the JPanel
     */
    private void paintGameScreen(Graphics g) {
        this.player.draw(g);
        this.theGroup.draw(g);
        int i = 0;
        while (i < this.playerShots.size()) {
            this.playerShots.get(i).draw(g);
            i += 1;
        }
        this.collision();
        this.finalCollision();
        this.attacking();
        this.pastTheLine();
    }
    // FIXME draw game objects here

    /* Paint the screen when the player has won
     *
     * @param g The Graphics for the JPanel
     */
    private void paintWinScreen(Graphics g) {
        g.setColor(new Color( 51, 204, 255));
        g.fillRect(250, 225, 300, 150);
        g.setColor(new Color(255,51,51));
        g.setFont(new Font("Monospaced", Font.PLAIN, 14));
        g.drawString("Well DONE! You won the game! :)", 275, 300);
    }
    // FIXME draw the win screen here

    /* Paint the screen when the player has lost
     *
     * @param g The Graphics for the JPanel
     */
    private void paintLoseScreen(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(250, 225, 300, 150);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Monospaced", Font.PLAIN, 12));
        g.drawString("Oh NO! You have lost... :(", 275, 300);
    }
    // FIXME draw the game over screen here

    public static void main(String[] args) {
        SpaceInvaders invaders = new SpaceInvaders();
        EventQueue.invokeLater(invaders);
    }
}
