import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener{

    JFrame frame;
    Font font = new Font("sans-serif", 0, 18);
    JPanel panel;
    JTextField text;

    String[] btnStrings = {"AC", "DEL", "%", "/",
                           "7", "8", "9", "*",
                           "4", "5", "6", "-",
                           "1", "2", "3", "+",
                           "E", "0", ".", "="};

    Calculator() {
        frame = new JFrame("Calculator");
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setSize(450, 630);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        text = new JTextField();
        text.setFont(font);
        text.setBorder(null);
        text.setBounds(10, 0, 420, 100);
        text.setHorizontalAlignment(SwingConstants.RIGHT);
        text.setEditable(false);

        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 0, 0));
        panel.setBorder(null);
        panel.setBounds(0, 100, 450, 500);

        for (String str: btnStrings) {
            JButton button = new JButton(str);
            button.setBackground(Color.black);
            button.setPreferredSize(new Dimension(100, 100));
            button.setBorder(null);
            button.setFocusable(false);
            button.setForeground(Color.white);
            button.setFont(font);
            button.addActionListener(this);
            panel.add(button);
        }

        frame.add(text);
        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String action = ((JButton)e.getSource()).getText();

        switch (action) {
            case "AC" -> text.setText("");
            case "DEL" -> {
                String currentText = text.getText();
                if (currentText.length() == 0) return;
                text.setText(currentText.substring(0, currentText.length() - 1));
            }
            case "=" -> text.setText((new EquationSolver(text.getText())).getResult());
            default -> text.setText(text.getText().concat(action));
        }
    }
}
