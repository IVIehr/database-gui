import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class CRUD_on_paymnet extends JDialog {
    private JTable table1;
    private JTextField paymentworker_idField;
    private JTextField paymentmanager_idFiled;
    private JButton addButton;
    private JTextField updateField;
    private JButton updateButton;
    private JTextField deleteField;
    private JButton deleteButton;
    private JPanel crudOperation;
    private JTextField salaryField;

    Connection connection;
    PreparedStatement preparedStatement;

    int salary, paymentmanager_id, paymentworker_id;

    public CRUD_on_paymnet(JFrame parent) {
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
        salary = Integer.parseInt(salaryField.getText());
        paymentmanager_id = Integer.parseInt(paymentmanager_idFiled.getText());
        paymentworker_id = Integer.parseInt(paymentworker_idField.getText());

        try {
            preparedStatement = connection.prepareStatement("insert into payment(salary,paymentmanager_id,paymentworker_id)values(?,?,?)");
            preparedStatement.setString(1, String.valueOf(salary));
            preparedStatement.setString(2, String.valueOf(paymentmanager_id));
            preparedStatement.setString(3, String.valueOf(paymentworker_id));
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record Added!");
            salaryField.setText("");
            paymentworker_idField.setText("");
            paymentmanager_idFiled.setText("");
            salaryField.requestFocus();
            paymentworker_idField.requestFocus();
            paymentmanager_idFiled.requestFocus();
        } catch (SQLException e1) {

            e1.printStackTrace();
        }
    }

    public void read() {
        try {
            preparedStatement = connection.prepareStatement("select * from payment");
            ResultSet rs = preparedStatement.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        int update = Integer.parseInt(updateField.getText());
        salary = Integer.parseInt(salaryField.getText());
        paymentmanager_id = Integer.parseInt(paymentmanager_idFiled.getText());
        paymentworker_id = Integer.parseInt(paymentworker_idField.getText());
        try {
            preparedStatement = connection.prepareStatement("update payment set salary = ?,paymentmanager_id = ? , paymentmanager_id = ? where paymentmanager_id = ?");
            preparedStatement.setString(1, String.valueOf(salary));
            preparedStatement.setString(2, String.valueOf(paymentmanager_id));
            preparedStatement.setString(3, String.valueOf(paymentmanager_id));
            preparedStatement.setString(4, String.valueOf(update));
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record Updated!");
            salaryField.setText("");
            paymentworker_idField.setText("");
            paymentmanager_idFiled.setText("");
            salaryField.requestFocus();
            paymentworker_idField.requestFocus();
            paymentmanager_idFiled.requestFocus();
            updateField.setText("");
            updateField.requestFocus();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public void delete() {
        paymentmanager_id = Integer.parseInt(deleteField.getText());
        try {
            preparedStatement = connection.prepareStatement("delete from payment where paymentmanager_id = ?");
            preparedStatement.setString(1, String.valueOf(paymentmanager_id));
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
