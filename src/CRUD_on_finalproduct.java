import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class CRUD_on_finalproduct extends JDialog {
    private JTextField updateField;
    private JButton updateButton;
    private JTextField deleteField;
    private JButton deleteButton;
    private JButton addButton;
    private JTextField finalproductmachine_idField;
    private JTextField finalproductfactory_idField;
    private JTable table1;
    private JPanel crudOperation;

    Connection connection;
    PreparedStatement preparedStatement;

    int finalproductfactory_id, finalproductmachine_id, idfinalproduct;

    public CRUD_on_finalproduct(JFrame parent) {
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
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
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
        finalproductfactory_id = Integer.parseInt(finalproductfactory_idField.getText());
        finalproductmachine_id = Integer.parseInt(finalproductmachine_idField.getText());

        try {
            preparedStatement = connection.prepareStatement("insert into finalproduct(finalproductfactory_id,finalproductmachine_id)values(?,?)");
            preparedStatement.setString(1, String.valueOf(finalproductfactory_id));
            preparedStatement.setString(2, String.valueOf(finalproductmachine_id));
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record Added!");
            finalproductfactory_idField.setText("");
            finalproductmachine_idField.setText("");
            finalproductmachine_idField.requestFocus();
            finalproductfactory_idField.requestFocus();
        } catch (SQLException e1) {

            e1.printStackTrace();
        }
    }

    public void read() {
        try {
            preparedStatement = connection.prepareStatement("select * from finalproduct");
            ResultSet rs = preparedStatement.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        finalproductfactory_id = Integer.parseInt(finalproductfactory_idField.getText());
        finalproductmachine_id = Integer.parseInt(finalproductmachine_idField.getText());
        idfinalproduct = Integer.parseInt(updateField.getText());
        try {
            preparedStatement = connection.prepareStatement("update finalproduct set finalproductfactory_id = ?,finalproductmachine_id = ? where idfinalproduct = ?");
            preparedStatement.setString(1, String.valueOf(finalproductfactory_id));
            preparedStatement.setString(2, String.valueOf(finalproductmachine_id));
            preparedStatement.setString(3, String.valueOf(idfinalproduct));
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record Updated!");
            finalproductfactory_idField.setText("");
            finalproductmachine_idField.setText("");
            updateField.setText("");
            updateField.requestFocus();
            finalproductmachine_idField.requestFocus();
            finalproductfactory_idField.requestFocus();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public void delete() {
        idfinalproduct = Integer.parseInt(deleteField.getText());
        try {
            preparedStatement = connection.prepareStatement("delete from finalproduct where idfinalproduct = ?");
            preparedStatement.setString(1, String.valueOf(idfinalproduct));
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
