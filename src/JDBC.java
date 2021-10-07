import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class JDBC implements ActionListener {

    Connection con;
    JFrame frame;
    JTextField nameInput, movieInput;
    JButton addButton;
    JPanel dataPanel;
    Font font = new Font("sans-serif", Font.BOLD, 15);

    JDBC(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/umico",
                    "root", "admin");
            System.out.println("connected");
        }catch (SQLException e) {
            e.printStackTrace();
        }

        frame = new JFrame("JDBC");
        frame.setSize(500, 500);
        frame.setLayout(new FlowLayout());

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    con.close();
                    System.out.println("Connection closed");
                } catch (SQLException err) {
                    err.printStackTrace();
                }
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Favorite Movie");
        title.setFont(new Font("sans-serif", Font.BOLD, 18));

        nameInput = new JTextField();
        movieInput = new JTextField();
        addButton = new JButton("Add");
        addButton.setFocusable(false);
        addButton.setBorder(null);
        addButton.setBackground(Color.black);
        addButton.setForeground(Color.white);
        addButton.addActionListener(this);

        panel.add(title);
        panel.add(new JLabel("Name"));
        panel.add(nameInput);
        panel.add(new JLabel("Favorite Movie"));
        panel.add(movieInput);
        panel.add(addButton);

        dataPanel = new JPanel();

        frame.add(panel);
        try {
            populateData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        frame.add(dataPanel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    void populateData() throws SQLException {

        dataPanel.removeAll();

        PreparedStatement statement = con.prepareStatement("select * from FAVMOVIE",
                                                    ResultSet.TYPE_SCROLL_SENSITIVE,
                                                    ResultSet.CONCUR_UPDATABLE);
        ResultSet result = statement.executeQuery();
        int size =0;
        if (result != null){
            result.last();    // moves cursor to the last row
            size = result.getRow(); // get row id
            result.first();
        }

        dataPanel.setLayout(new GridLayout(size, 2));
        Border border = BorderFactory.createLineBorder(Color.black);

        JLabel name = new JLabel("Name");
        name.setBorder(border);
        name.setFont(font);
        dataPanel.add(name);

        JLabel movie = new JLabel("Favorite Movie");
        movie.setBorder(border);
        movie.setFont(font);
        dataPanel.add(movie);

        while(result.next()) {
            name = new JLabel(result.getString(1));
            name.setBorder(border);
            dataPanel.add(name);

            movie = new JLabel(result.getString(2));
            movie.setBorder(border);
            dataPanel.add(movie);
        }

        dataPanel.revalidate();
        dataPanel.repaint();
    }


    public static void main(String[] args) {

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        new JDBC();
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        String name = nameInput.getText(), movie = movieInput.getText();
        if(name.length() == 0 || movie.length() == 0) return;

        try {
            Statement statement = con.createStatement();
            statement.executeUpdate("INSERT INTO FAVMOVIE VALUES (\"" + name + "\",\"" + movie + "\")");
            populateData();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        nameInput.setText("");
        movieInput.setText("");
    }
}
