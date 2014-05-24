import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class InputHandler implements KeyListener {

    public class Key {

        public int presses, absorbs;
        public boolean down, clicked;

        public Key() {
            keys.add(this);
        }

        public void toggle(boolean pressed) {
            if (pressed != down) {
                down = pressed;
            }
            if (pressed) {
                presses++;
            }
        }

        public void tick() {
            if (absorbs < presses) {
                absorbs++;
                clicked = true;
            } else {
                clicked = false;
            }
        }

    }

    public ArrayList<Key> keys = new ArrayList<Key>();

    public Key left = new Key();
    public Key right = new Key();
    public Key attack = new Key();
    public Key pause = new Key();
    public Key restart = new Key();

    public Key up = new Key();
    public Key down = new Key();
    public Key z = new Key();

    public Key w_rifle = new Key();
    public Key w_minigun = new Key();
    public Key w_laser = new Key();
    public Key w_rl = new Key();

    public InputHandler(Program game) {
        game.addKeyListener(this);
    }

    public void releaseAll() {
        for (int i = 0; i < keys.size(); i++) {
            keys.get(i).down = false;
        }
    }

    public void tick() {
        for (int i = 0; i < keys.size(); i++) {
            keys.get(i).tick();
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        toggle(ke, true);
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        toggle(ke, false);
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    
    public char KeyChar=' ';
    private void toggle(KeyEvent ke, boolean pressed) {
        int c = ke.getKeyCode();
        KeyChar=ke.getKeyChar();
        if (c == KeyEvent.VK_A || c == KeyEvent.VK_LEFT) left.toggle(pressed);
        if (c == KeyEvent.VK_D || c == KeyEvent.VK_RIGHT) right.toggle(pressed);
        if (c == KeyEvent.VK_SPACE || c == KeyEvent.VK_ENTER) attack.toggle(pressed);
        if (c == KeyEvent.VK_ESCAPE) pause.toggle(pressed);
        if (c == KeyEvent.VK_R) restart.toggle(pressed);

        if (c == KeyEvent.VK_UP || c == KeyEvent.VK_W) up.toggle(pressed);
        if (c == KeyEvent.VK_DOWN || c == KeyEvent.VK_D) down.toggle(pressed);
        if (c == KeyEvent.VK_Z) z.toggle(pressed);

        if (c == KeyEvent.VK_1) w_rifle.toggle(pressed);
        if (c == KeyEvent.VK_2) w_minigun.toggle(pressed);
        if (c == KeyEvent.VK_3) w_laser.toggle(pressed);
        if (c == KeyEvent.VK_4) w_rl.toggle(pressed);
    }

}