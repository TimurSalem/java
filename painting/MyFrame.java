import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyFrame extends JFrame{

    int tCol = 0;
    boolean flag = false;
    int size = 50;

    class Keyboard implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            int prsd = e.getKeyCode();
            if (prsd == 61) {
                size += 10;
            }
            if (prsd == 45) {
                size -= 10;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

    class Mouse implements MouseListener, MouseMotionListener, MouseWheelListener{

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            mousex = e.getX();
            mousey = e.getY();

            System.out.println(e.getX());

            flag = mousex < 1093;


            if (mousex > 1105 && mousex < 1186) {
                if (mousey > 60 && mousey < 60 + 80) {
                    tCol = 0;

                    repaint();
                }
                if (mousey > 60 + 96 && mousey < 60 + 96 + 80) {
                    tCol = 1;

                    repaint();
                }
                if (mousey > 60 + 2 * 96 && mousey < 60 + 2 * 96 + 80) {
                    tCol = 2;

                    repaint();
                }
                if (mousey > 60 + 3 * 96 && mousey < 60 + 3 * 96 + 80) {
                    tCol = 3;

                    repaint();
                }
                if (mousey > 60 + 4 * 96 && mousey < 60 + 4 * 96 + 80) {
                    tCol = 4;

                    repaint();
                }
                if (mousey > 60 + 5 * 96 && mousey < 60 + 5 * 96 + 80) {
                    tCol = 5;

                    repaint();
                }
                if (mousey > 60 + 6 * 96 && mousey < 60 + 6 * 96 + 80) {
                    tCol = 6;

                    repaint();
                }
                if (mousey > 60 + 7 * 96 && mousey < 60 + 7 * 96 + 80) {
                    tCol = 7;

                    repaint();
                }
            }
            if (mousex > 20 && 80 > mousex) {
                if (mousey > 650 && mousey < 730){
                    tCol = 7;

                    repaint();
                }

            }


        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            drx = e.getX();
            dry = e.getY();

            System.out.println(e.getX());

            flag = mousex < 1093;

        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {

        }
    }
    /**
     * This class use for create windows
     */

    String title;
    int x, y, w, h;
    int mX, mY;
    boolean resizbl;

    int mousex = 200;
    int mousey = 200;
    int drx = 200;
    int dry = 200;


    Timer updt;

    Color[] colors = new Color[10];

    MyFrame() {
        x = 100;
        y = 100;
        w = 800;
        h = 600;
        resizbl = false;
        title = "|(0)>(0)|";

    }

    /**
     * This class use for create windows
     * <p>
     * x - responsible for windows size
     */

    MyFrame(int x, int y, int wdht, int hght, boolean resizbl, boolean visbl, String name) {
        addMouseListener(new Mouse());
        addMouseMotionListener(new Mouse());
        addMouseWheelListener(new Mouse());
        addKeyListener(new Keyboard());

        this.x = x;
        this.y = y;
        w = wdht;
        h = hght;
        this.resizbl = resizbl;
        title = name;

    }

    void run() {

        Mouse moussfe = new Mouse();

        getContentPane().setBackground(new Color(15, 15, 15));

        setTitle(title);

        setBounds(x, y, w, h);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);

        setResizable(resizbl);


        colors[1] = new Color(0, 140, 140);
        colors[0] =  new Color(247, 229, 143);
        colors[2] = new Color(177, 50, 84);
        colors[3] = new Color(212, 137, 106);
        colors[6] = new Color(240, 219, 176);
        colors[4] = new Color(62, 156, 191);
        colors[5] = new Color(79, 106, 143);
        colors[7] = new Color(15, 15, 15);

        setFocusable(true);

        paintComponents(getGraphics());

        updt = new Timer(1, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setBackground(Color.BLACK);

            }
        });

        updt.start();


    }
    public void paint(Graphics g) {

        g.setColor(colors[tCol]);
        if (flag) {
            g.fillOval(mousex - size / 2, mousey - size / 2, size, size);
            g.fillOval(drx - size / 2, dry - size / 2, size, size);
            g.setColor(new Color(25, 25, 25));
            g.fillRect(1093, 0, 200, 1000);
            g.setColor(new Color(240, 240, 240));
            g.fillRect(20, 650, 60, 80);
            flag = false;
        }

        g.setColor(new Color(15, 15, 15));
        g.fillRect(19, 665, 62, 17);

        for (int idx = 0; idx < 7; idx++) {
            g.setColor(colors[idx]);
            g.fillRect(1106, 60 + idx * 96, 80, 80);

        }


        repaint();

        }
    }

