import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class CRUD_on_factory extends JDialog {
    private JPanel crudOperation;
    private JButton addButton;
    private JTable table1;
    private JTextField updateField;
    private JButton updateButton;
    private JTextField deleteField;
    private JButton deleteButton;
    private JTextField factoryNameField;
    private JTextField phoneNoFiled;
    private JTextField addressField;
    private JTextField zipCodeField;
    private JTextField noProductsField;

    Connection connection;
    PreparedStatement preparedStatement;

    int zipCode, noFinalProducts,idfactory;
    String factoryName, address, phoneNo;

    public CRUD_on_factory(JFrame parent) {
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
        factoryName = factoryNameField.getText();
        address = addressField.getText();
        phoneNo = phoneNoFiled.getText();
        zipCode = Integer.parseInt(zipCodeField.getText());
        noFinalProducts = Integer.parseInt(noProductsField.getText());

        try {
            preparedStatement = connection.prepareStatement("insert into factory(factoryName,phoneNo,address,zipCode,noFinalProducts)values(?,?,?,?,?)");
            preparedStatement.setString(1, factoryName);
            preparedStatement.setString(2, phoneNo);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, String.valueOf(zipCode));
            preparedStatement.setString(5, String.valueOf(noFinalProducts));
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record Added!");
            factoryNameField.setText("");
            addressField.setText("");
            phoneNoFiled.setText("");
            zipCodeField.setText("");
            noProductsField.setText("");
            factoryNameField.requestFocus();
            addressField.requestFocus();
            phoneNoFiled.requestFocus();
            zipCodeField.requestFocus();
            noProductsField.requestFocus();
        } catch (SQLException e1) {

            e1.printStackTrace();
        }
    }

    public void read() {
        try {
            preparedStatement = connection.prepareStatement("select * from factory");
            ResultSet rs = preparedStatement.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        factoryName = factoryNameField.getText();
        address = addressField.getText();
        phoneNo = phoneNoFiled.getText();
        zipCode = Integer.parseInt(zipCodeField.getText());
        noFinalProducts = Integer.parseInt(noProductsField.getText());
        idfactory = Integer.parseInt(updateField.getText());
        try {
            preparedStatement = connection.prepareStatement("update factory set factoryName = ?,phoneNo = ?,address = ?,zipCode = ? ,noFinalProducts = ? where idfactory = ?");
            preparedStatement.setString(1, factoryName);
            preparedStatement.setString(2, phoneNo);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, String.valueOf(zipCode));
            preparedStatement.setString(5, String.valueOf(noFinalProducts));
            preparedStatement.setString(6, String.valueOf(idfactory));
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record Updated!");
            factoryNameField.setText("");
            addressField.setText("");
            phoneNoFiled.setText("");
            zipCodeField.setText("");
            noProductsField.setText("");
            updateField.setText("");
            factoryNameField.requestFocus();
            addressField.requestFocus();
            phoneNoFiled.requestFocus();
            zipCodeField.requestFocus();
            noProductsField.requestFocus();
            updateField.requestFocus();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public void delete() {
        idfactory = Integer.parseInt(deleteField.getText());
        try {
            preparedStatement = connection.prepareStatement("delete from factory where idfactory = ?");
            preparedStatement.setString(1, String.valueOf(idfactory));
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
