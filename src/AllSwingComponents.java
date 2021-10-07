import javax.swing.*;
import java.awt.*;

public class AllSwingComponents {

    AllSwingComponents() {
        JFrame frame = new JFrame("All Swing Components");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(700, 500);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        ImageIcon homeIcon = new ImageIcon("./src/java.png");
        JButton Btn = new JButton("I'm a Button");
        JLabel textLbl = new JLabel("This is a text label.");
        JTextField txtBox = new JTextField(20);
        JTextArea txtArea = new JTextArea("This text is default text for text area.", 5, 20);
        JPasswordField pwdField = new JPasswordField(15);
        JCheckBox chkBox = new JCheckBox("how Help", true);
        ButtonGroup radioGroup = new ButtonGroup();
        JRadioButton rb1 = new JRadioButton("Easy", true);
        JRadioButton rb2 = new JRadioButton("Medium");
        JRadioButton rb3 = new JRadioButton("Hard");
        radioGroup.add(rb1);
        radioGroup.add(rb2);
        radioGroup.add(rb3);
        String[] cityStrings = { "Mumbai", "London", "New York", "Sydney", "Tokyo" };
        JComboBox cities = new JComboBox(cityStrings);
        cities.setSelectedIndex(3);
        JFileChooser fileChooser = new JFileChooser();
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Tab 1", new JPanel());
        tabbedPane.addTab("Tab 2", new JPanel());
        JSlider volumeSlider = new JSlider(0, 100, 50);

        panel.add(new JLabel(homeIcon));
        panel.add(Btn);
        panel.add(textLbl);
        panel.add(txtBox);
        panel.add(txtArea);
        panel.add(pwdField);
        panel.add(chkBox);
        panel.add(rb1);
        panel.add(rb2);
        panel.add(rb3);
        panel.add(cities);
        panel.add(fileChooser);
        panel.add(tabbedPane);
        panel.add(volumeSlider);
        frame.add(panel);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new AllSwingComponents();
    }

}
