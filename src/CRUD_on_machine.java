import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class CRUD_on_machine extends JDialog {
    private JTable table1;
    private JTextField machineModelField;
    private JTextField machineNameField;
    private JButton addButton;
    private JTextField updateField;
    private JButton updateButton;
    private JTextField deleteField;
    private JButton deleteButton;
    private JPanel crudOperation;
    private JTextField idmanagerField;

    Connection connection;
    PreparedStatement preparedStatement;

    int idmanager, idmachine;
    String machineName, machineModel;

    public CRUD_on_machine(JFrame parent) {
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
        idmanager = Integer.parseInt(idmanagerField.getText());
        machineModel = machineModelField.getText();
        machineName = machineNameField.getText();

        try {
            preparedStatement = connection.prepareStatement("insert into machine(machineName,machineModel,idmanager)values(?,?,?)");
            preparedStatement.setString(1, machineName);
            preparedStatement.setString(2, machineModel);
            preparedStatement.setString(3, String.valueOf(idmanager));
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record Added!");
            machineNameField.setText("");
            machineModelField.setText("");
            idmanagerField.setText("");
            machineModelField.requestFocus();
            machineNameField.requestFocus();
            idmanagerField.requestFocus();
        } catch (SQLException e1) {

            e1.printStackTrace();
        }
    }

    public void read() {
        try {
            preparedStatement = connection.prepareStatement("select * from machine");
            ResultSet rs = preparedStatement.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        idmanager = Integer.parseInt(idmanagerField.getText());
        machineModel = machineModelField.getText();
        machineName = machineNameField.getText();
        idmachine = Integer.parseInt(updateField.getText());
        try {
            preparedStatement = connection.prepareStatement("update finalproduct set machineName = ?,machineModel = ? , idmanager = ? where idmachine = ?");
            preparedStatement.setString(1, machineName);
            preparedStatement.setString(2, machineModel);
            preparedStatement.setString(3, String.valueOf(idmanager));
            preparedStatement.setString(4, String.valueOf(idmachine));
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record Updated!");
            machineNameField.setText("");
            machineModelField.setText("");
            idmanagerField.setText("");
            machineModelField.requestFocus();
            machineNameField.requestFocus();
            idmanagerField.requestFocus();
            updateField.setText("");
            updateField.requestFocus();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public void delete() {
        idmachine = Integer.parseInt(deleteField.getText());
        try {
            preparedStatement = connection.prepareStatement("delete from machine where idmachine = ?");
            preparedStatement.setString(1, String.valueOf(idmachine));
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
