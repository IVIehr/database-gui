import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class CRUD_on_factoryperson extends JDialog{
    private JTextField updateField;
    private JButton updateButton;
    private JTextField deleteField;
    private JButton deleteButton;
    private JButton addButton;
    private JTextField passcodeField;
    private JTextField lastNameField;
    private JTextField firstNameField;
    private JTextField addressField;
    private JTextField baseSalaryField;
    private JTable table1;
    private JPanel crudOperation;
    private JTextField phoneNoField;
    private JTextField qualificationField;
    private JTextField factoryIdField;

    Connection connection;
    PreparedStatement preparedStatement;

    int phoneNo, baseSalary,idfactoryperson, factory_id;
    String firstName,lastName,passcode, address, qualification;

    public CRUD_on_factoryperson(JFrame parent) {
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
        firstName = firstNameField.getText();
        lastName = lastNameField.getText();
        passcode = passcodeField.getText();
        address = addressField.getText();
        qualification = qualificationField.getText();
        baseSalary = Integer.parseInt(baseSalaryField.getText());
        phoneNo = Integer.parseInt(phoneNoField.getText());
        factory_id = Integer.parseInt(factoryIdField.getText());

        try {
            preparedStatement = connection.prepareStatement("insert into factoryperson(firstName,lastName,passcode,address,baseSalary,phoneNo,qualification,factory_id )values(?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, passcode);
            preparedStatement.setString(4, address);
            preparedStatement.setString(5, String.valueOf(baseSalary));
            preparedStatement.setString(6, String.valueOf(phoneNo));
            preparedStatement.setString(7, qualification);
            preparedStatement.setString(8, String.valueOf(factory_id));
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record Added!");
            firstNameField.setText("");
            lastNameField.setText("");
            passcodeField.setText("");
            addressField.setText("");
            baseSalaryField.setText("");
            phoneNoField.setText("");
            qualificationField.setText("");
            factoryIdField.setText("");
            firstNameField.requestFocus();
            lastNameField.requestFocus();
            passcodeField.requestFocus();
            addressField.requestFocus();
            baseSalaryField.requestFocus();
            phoneNoField.requestFocus();
            qualificationField.requestFocus();
            factoryIdField.requestFocus();
        } catch (SQLException e1) {

            e1.printStackTrace();
        }
    }

    public void read() {
        try {
            preparedStatement = connection.prepareStatement("select * from factoryperson");
            ResultSet rs = preparedStatement.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        firstName = firstNameField.getText();
        lastName = lastNameField.getText();
        passcode = passcodeField.getText();
        address = addressField.getText();
        qualification = qualificationField.getText();
        baseSalary = Integer.parseInt(baseSalaryField.getText());
        phoneNo = Integer.parseInt(phoneNoField.getText());
        factory_id = Integer.parseInt(factoryIdField.getText());
        idfactoryperson = Integer.parseInt(updateField.getText());
        try {
            preparedStatement = connection.prepareStatement("update factoryperson set firstName = ?,lastName = ?,passcode = ?,address = ?,baseSalary = ?,phoneNo = ?,qualification = ?,factory_id = ? where idfactoryperson = ?");
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, passcode);
            preparedStatement.setString(4, address);
            preparedStatement.setString(5, String.valueOf(baseSalary));
            preparedStatement.setString(6, String.valueOf(phoneNo));
            preparedStatement.setString(7, qualification);
            preparedStatement.setString(8, String.valueOf(factory_id));
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record Updated!");
            baseSalaryField.setText("");
            passcodeField.setText("");
            addressField.setText("");
            lastNameField.setText("");
            firstNameField.setText("");
            updateField.setText("");
            updateField.requestFocus();
            baseSalaryField.requestFocus();
            passcodeField.requestFocus();
            addressField.requestFocus();
            lastNameField.requestFocus();
            firstNameField.requestFocus();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public void delete() {
        idfactoryperson = Integer.parseInt(deleteField.getText());
        try {
            preparedStatement = connection.prepareStatement("delete from factoryperson where idfactoryperson = ?");
            preparedStatement.setString(1, String.valueOf(idfactoryperson));
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
