import javax.swing.*;
import java.awt.event.*;

class MyFrame extends JFrame implements ActionListener {
    int x = 100;
    int y = 100;

    int widht = 800;
    int height = 628;

    MyPanel myPanel = new MyPanel();

    MyFrame() {
        add(myPanel);

        addKeyListener(new Events());

        setTitle("Super Game 3000"); // Название окна
        setBounds(x, y, widht, height); // Координаты и размер

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        myPanel.x = (widht - myPanel.bg_2.getWidth(null)) / 2; // Ставим по середине
        myPanel.y = height - myPanel.bg_2.getHeight(null); // Ставим в низу

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    class Events implements KeyListener { // создаем новый класс Events  который перенимает интерфейс KeyListener

        public void keyTyped(KeyEvent e) {
        }

        public void keyPressed(KeyEvent e) {
            int prs = e.getKeyCode();

            if (prs == 68) {
                myPanel.directon_x = 1;
            } else if (prs == 65) {
                myPanel.directon_x = -1;
            }
        }

        public void keyReleased(KeyEvent e) {
        }

        public void actionPerformed(ActionEvent e) {
        }
    }

}
