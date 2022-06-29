import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class CRUD_on_taskassignment extends JDialog{
    private JTable table1;
    private JTextField taskassignmentmachine_idField;
    private JTextField taskassignmentmanager_idField;
    private JTextField taskidField;
    private JButton addButton;
    private JTextField updateField;
    private JButton updateButton;
    private JTextField deleteField;
    private JButton deleteButton;
    private JPanel crudOperation;

    Connection connection;
    PreparedStatement preparedStatement;

    int taskid, taskassignmentmanager_id, taskassignmentmachine_id;

    public CRUD_on_taskassignment(JFrame parent) {
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
        taskid = Integer.parseInt(taskidField.getText());
        taskassignmentmanager_id = Integer.parseInt(taskassignmentmanager_idField.getText());
        taskassignmentmachine_id = Integer.parseInt(taskassignmentmachine_idField.getText());

        try {
            preparedStatement = connection.prepareStatement("insert into taskassignment(taskid,taskassignmentmanager_id ,taskassignmentmachine_id )values(?,?,?)");
            preparedStatement.setString(1, String.valueOf(taskid));
            preparedStatement.setString(2, String.valueOf(taskassignmentmanager_id));
            preparedStatement.setString(3, String.valueOf(taskassignmentmachine_id));
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record Added!");
            taskidField.setText("");
            taskassignmentmanager_idField.setText("");
            taskassignmentmachine_idField.setText("");
            taskidField.requestFocus();
            taskassignmentmanager_idField.requestFocus();
            taskassignmentmachine_idField.requestFocus();
        } catch (SQLException e1) {

            e1.printStackTrace();
        }
    }

    public void read() {
        try {
            preparedStatement = connection.prepareStatement("select * from taskassignment");
            ResultSet rs = preparedStatement.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        int update = Integer.parseInt(updateField.getText());
        taskid = Integer.parseInt(taskidField.getText());
        taskassignmentmanager_id = Integer.parseInt(taskassignmentmanager_idField.getText());
        taskassignmentmachine_id = Integer.parseInt(taskassignmentmachine_idField.getText());
        try {
            preparedStatement = connection.prepareStatement("update taskassignment set taskid = ?,taskassignmentmanager_id = ?,taskassignmentmachine_id = ? where taskid = ?");
            preparedStatement.setString(1, String.valueOf(taskid));
            preparedStatement.setString(2, String.valueOf(taskassignmentmanager_id));
            preparedStatement.setString(3, String.valueOf(taskassignmentmanager_id));
            preparedStatement.setString(4, String.valueOf(update));
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record Updated!");
            taskidField.setText("");
            taskassignmentmanager_idField.setText("");
            taskassignmentmachine_idField.setText("");
            taskidField.requestFocus();
            taskassignmentmanager_idField.requestFocus();
            taskassignmentmachine_idField.requestFocus();
            updateField.setText("");
            updateField.requestFocus();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public void delete() {
        taskid = Integer.parseInt(deleteField.getText());
        try {
            preparedStatement = connection.prepareStatement("delete from taskassignment where taskid = ?");
            preparedStatement.setString(1, String.valueOf(taskid));
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
