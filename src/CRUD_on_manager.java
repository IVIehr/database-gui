import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class CRUD_on_manager extends JDialog{
    private JTable table1;
    private JTextField idfactoryperson_managerField;
    private JButton addButton;
    private JTextField deleteField;
    private JButton deleteButton;
    private JPanel crudOperation;

    Connection connection;
    PreparedStatement preparedStatement;

    int idfactoryperson_manager;

    public CRUD_on_manager(JFrame parent) {
        super(parent);
        setTitle("CRUD operation");
        setContentPane(crudOperation);
        setMinimumSize(new Dimension(700, 800));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        connect();
        read();
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                create();
                read();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delete();
                read();
            }
        });
        setVisible(true);
    }

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/db_project", "root", "");
//            connection.close();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public void create() {
        idfactoryperson_manager  = Integer.parseInt(idfactoryperson_managerField.getText());

        try {
            preparedStatement = connection.prepareStatement("insert into manager(idfactoryperson_manager )values(?)");
            preparedStatement.setString(1, String.valueOf(idfactoryperson_manager));
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record Added!");
            idfactoryperson_managerField.setText("");
            idfactoryperson_managerField.requestFocus();
        } catch (SQLException e1) {

            e1.printStackTrace();
        }
    }

    public void read() {
        try {
            preparedStatement = connection.prepareStatement("select * from manager");
            ResultSet rs = preparedStatement.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        idfactoryperson_manager  = Integer.parseInt(deleteField.getText());
        try {
            preparedStatement = connection.prepareStatement("delete from manager where idfactoryperson_manager  = ?");
            preparedStatement.setString(1, String.valueOf(idfactoryperson_manager ));
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record Deleted!");
            read();
            deleteField.setText("");
            deleteField.requestFocus();
        } catch (SQLException e1) {

            e1.printStackTrace();
        }
    }
}
