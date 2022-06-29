import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginFrom extends JDialog {
    private JPanel loginPanel;
    private JTextField IDField;
    private JPasswordField passwordField;
    private JButton OKButton;
    private JButton cancelButton;

    public LoginFrom(JFrame parent) {
        super(parent);
        setTitle("Login");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(IDField.getText());
                String password = String.valueOf(passwordField.getPassword());

                factoryPerson = getAuthenticatedUser(id, password);

                if (factoryPerson != null) {
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginFrom.this,
                            "Invalid username or password", "Try again", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    public FactoryPerson factoryPerson;

    private FactoryPerson getAuthenticatedUser(int id, String password) {
        FactoryPerson factoryPerson = null;

        final String DB_URL = "jdbc:mysql://localhost/db_project";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM factoryperson WHERE idfactoryperson=? AND passcode=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(id));
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                factoryPerson = new FactoryPerson();
                factoryPerson.id = Integer.parseInt(resultSet.getString("idfactoryperson"));
                factoryPerson.passcode = resultSet.getString("passcode");
                factoryPerson.firstName = resultSet.getString("firstName");
                factoryPerson.lastName = resultSet.getString("lastName");
            }

            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return factoryPerson;
    }

    public static void main(String[] args) {
        LoginFrom loginFrom = new LoginFrom(null);
        FactoryPerson factoryPerson = loginFrom.factoryPerson;

        if (factoryPerson != null) {
            homePage homePage = new homePage(null,factoryPerson.firstName + " " + factoryPerson.lastName);
        } else System.out.println("authentication canceled");
    }
}
