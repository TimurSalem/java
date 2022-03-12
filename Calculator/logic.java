
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


class Fraame extends JFrame {

    int w;
    int h;
    int g = 0;
    Double n = 0.0;
    String value = "";
    String conclusion = "";
    int button_y = 405;
    int sizeres = 55;

    JLabel result = new JLabel("0");

    Double num1 = 0.0;
    Double num2 = 0.0;

    public String calc(Double x, Double y, String sign) {

        return switch (sign) {
            case "+" -> Math.ceil((x + y) * 100000.0) / 100000.0 + "";
            case "-" -> Math.ceil((x - y) * 100000.0) / 100000.0 + "";
            case "×" -> Math.ceil((x * y) * 100000.0) / 100000.0 + "";
            case "÷" -> Math.ceil((x / y) * 100000.0) / 100000.0 + "";
            case "+/-" -> Double.toString(-x);


            case "%" -> Math.ceil((x * 100 / y) * 100000.0) / 100000.0 + "%";
            default -> "";
        };
    }


    Fraame(String name, int w, int h) {
        this.w = w;
        this.h = h;
        setTitle(name);
        addKeyListener(new Events());

    }

    public void command(JButton button) {
        System.out.println(button.getText());
        String typepressing = button.getText();
        try {
            if (Integer.parseInt(typepressing) >= 0 && Integer.parseInt(typepressing) < 10)
                value += typepressing;
            result.setText(value);
        } catch (NumberFormatException e) {
            switch (typepressing) {
                case "+", "-", "×", "÷", "%" -> {
                    num1 = Double.parseDouble(value);
                    value = "";
                    conclusion = typepressing;
                    num2 = 0.0;
                }
                case "=" -> {
                    if (num2 == 0.0) {
                        num2 = Double.parseDouble(value);
                        value = calc(num1, num2, conclusion);
                        n = num2;
                    } else {
                        num1 = Double.parseDouble(value);
                        value = calc(num1, num2, conclusion);
                    }
                }
                case "C" -> {
                    num2 = 0.0;
                    num1 = 0.0;
                    value = "";
                    conclusion = "";
                }
                case "+/-", "!" -> {
                    num1 = Double.parseDouble(value);
                    conclusion = typepressing;
                    value = calc(num1, 0.0, conclusion);
                }
            }
        }
        sizeres = 55;
        result.setFont(new Font("Arial Black", Font.BOLD, sizeres));

        if (value.length() > 10) {
            sizeres = 615 / value.length();
            result.setFont(new Font("Arial Black", Font.BOLD, sizeres));
        }

        result.setText(value);
    }

    public void start() {

        setIconImage(Toolkit.getDefaultToolkit().getImage(Fraame.class.getResource("/icon_Calc.png")));

        setResizable(false);
        result.setBounds(20, 20, 440, 55);
        result.setFont(new Font("Arial Black", Font.BOLD, sizeres));
        result.setForeground(Color.WHITE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(30, 30, 30));

        getRootPane().setDoubleBuffered(false);

        JTextArea numbers = new JTextArea();
        numbers.setBounds(100, 200, 100, 10);

        String[] actions = {"+", "-", "÷", "×", "!"};

        JButton C = new JButton("C");
        JButton percent = new JButton("%");
        JButton plus_or_minus = new JButton("+/-");
        JButton zero = new JButton("0");
        JButton equal = new JButton("=");

        zero.setFont(new Font("ZarubkaType", Font.BOLD, 35));
        C.setFont(new Font("Arial Black", Font.BOLD, 20));
        percent.setFont(new Font("Arial Black", Font.BOLD, 20));
        plus_or_minus.setFont(new Font("Arial Black", Font.BOLD, 20));
        equal.setFont(new Font("ZarubkaType", Font.BOLD, 35));

        C.setBounds(230, 90, 100, 100);
        percent.setBounds(125, 90, 100, 100);
        plus_or_minus.setBounds(20, 90, 100, 100);
        zero.setBounds(20, 510, 100, 100);
        equal.setBounds(125, 510, 205, 100);

        equal.setBackground(new Color(255, 146, 42));

        zero.addActionListener(e -> command(zero));
        plus_or_minus.addActionListener(e -> command(plus_or_minus));
        percent.addActionListener(e -> command(percent));
        C.addActionListener(e -> command(C));
        equal.addActionListener(e -> command(equal));

        equal.setBounds(128, 513, 199, 93);
        equal.setBackground(new Color(255, 146, 42));
        equal.setOpaque(true);
        equal.setBorderPainted(false);
        equal.setForeground(Color.WHITE);
        equal.setFont(new Font("ZarubkaType", Font.BOLD, 55));


        for (int i = 1; i < 10; i += 1) {
            JButton button = new JButton(Integer.toString(i));
            button.setBounds(g * 105 + 20, button_y, 100, 100);
            button.addActionListener(e -> command(button));
            button.setFont(new Font("ZarubkaType", Font.BOLD, 35));
            panel.add(button);
            g += 1;

            if (g >= 3) {
                button_y -= 105;
                g = 0;

            }

            if (i < 6) {
                JButton button_actions = new JButton(actions[i - 1]);
                button_actions.setBounds(340, (i - 1) * 105 + 90, 100, 100);
                button_actions.addActionListener(e -> command(button_actions));
                button_actions.setFont(new Font("ZarubkaType", Font.BOLD, 35));

                panel.add(button_actions);
            }
        }

        panel.add(result);
        panel.add(C);
        panel.add(percent);
        panel.add(plus_or_minus);
        panel.add(zero);
        panel.add(equal);

        Container container = getContentPane();
        container.add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setBounds(100, 100, w, h);
    }

    class Events implements KeyListener { // создаем новый класс Events  который перенимает интерфейс KeyListener


        public void keyPressed(KeyEvent e) {
            int prs = e.getKeyCode();
            JButton button = new JButton(Integer.toString(prs));
            command(button);

        }

        public void keyReleased(KeyEvent e) {
        }

        public void keyTyped(KeyEvent e) {
            int prs = e.getKeyCode();
            JButton button = new JButton(Integer.toString(prs));
            command(button);

        }
    }

}
