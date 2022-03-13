import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class MyPanel extends JFrame {

    Color[] colors = new Color[10];

    int tCol = 0;

    int mX, mY;
    int x, y;
    int w = 3, h = 3;
    boolean dragged = false;
    boolean resizbl = false;
    String title;

    class MyMouse implements MouseListener {

        public void mouseClicked(MouseEvent e) {
        }

        public void mousePressed(MouseEvent e) {
            int tX = e.getX();
            int tY = e.getY();

            if (tX > 1105 && tX < 1186) {
                if (tY > 60 && tY < 140) {
                    tCol = 0;
                    repaint();
                }
                if (tY > 156 && tY < 236) {
                    tCol = 1;
                    repaint();
                }
                if (tY > 202 && tY < 282) {
                    tCol = 2;
                    repaint();
                }
            }
        }
        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }
    }

    class MyMouseWheel implements MouseWheelListener {

        public void mouseWheelMoved(MouseWheelEvent e) {

            w += e.getWheelRotation();
            h += e.getWheelRotation();

            if (w < 2) {
                w = 2;
                h = 2;
            } else if (w > 50) {
                w = 50;
                h = 50;
            }
            repaint();
        }
    }

    class MyMouseMotion implements MouseMotionListener {

        public void mouseDragged(MouseEvent e) {
            mX = e.getX();
            mY = e.getY();
            dragged = true;
            repaint();
        }

        public void mouseMoved(MouseEvent e) {
            int tX = e.getX();
            int tY = e.getY();

            if (tX > 0 && tX < 100 * colors.length && tY > 0 && tY < 50)
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            else setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }

    MyPanel() {
        setTitle("PAINTER 2000");
        setBounds(0, 30, 1200, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        addMouseListener(new MyMouse());
        addMouseMotionListener(new MyMouseMotion());
        addMouseWheelListener(new MyMouseWheel());

        setFocusable(true);

        getContentPane().setBackground(new Color(15, 15, 15));

        colors[1] = new Color(0, 140, 140);
        colors[0] = new Color(255, 222, 0);
        colors[2] = new Color(177, 50, 84);
        colors[3] = new Color(212, 137, 106);
        colors[6] = new Color(240, 219, 176);
        colors[4] = new Color(62, 156, 191);
        colors[5] = new Color(79, 106, 143);

    }


    public void paint(Graphics g) {

        getContentPane().setBackground(new Color(15, 15, 15));

        for (int idx = 0; idx < 7; idx++) {
            g.setColor(colors[idx]);
            g.fillRect(1106, 60 + idx * 96, 80, 80);

        }

        g.setColor(Color.WHITE);

        g.setColor(colors[tCol]);
        g.fillOval(700 + (100 / 2 - w / 2), 50 / 2 - h / 2, w, h);

        if (dragged) {
            g.setColor(colors[tCol]);
            g.fillOval(mX - w / 2, mY - w / 2, w, h);
        }

    }

}