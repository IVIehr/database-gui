import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class CRUD_on_worker extends JDialog {
    private JTable table1;
    private JTextField noTasksCompletedField;
    private JTextField idfactoryperson_workerField;
    private JButton addButton;
    private JTextField updateField;
    private JButton updateButton;
    private JTextField deleteField;
    private JButton deleteButton;
    private JPanel crudOperation;

    Connection connection;
    PreparedStatement preparedStatement;

    int idfactoryperson_worker, noTasksCompleted;

    public CRUD_on_worker(JFrame parent) {
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
        idfactoryperson_worker = Integer.parseInt(idfactoryperson_workerField.getText());
        noTasksCompleted = Integer.parseInt(noTasksCompletedField.getText());

        try {
            preparedStatement = connection.prepareStatement("insert into worker(idfactoryperson_worker,noTasksCompleted)values(?,?)");
            preparedStatement.setString(1, String.valueOf(idfactoryperson_worker));
            preparedStatement.setString(2, String.valueOf(noTasksCompleted));
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record Added!");
            idfactoryperson_workerField.setText("");
            noTasksCompletedField.setText("");
            noTasksCompletedField.requestFocus();
            idfactoryperson_workerField.requestFocus();
        } catch (SQLException e1) {

            e1.printStackTrace();
        }
    }

    public void read() {
        try {
            preparedStatement = connection.prepareStatement("select * from worker");
            ResultSet rs = preparedStatement.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        idfactoryperson_worker = Integer.parseInt(idfactoryperson_workerField.getText());
        noTasksCompleted = Integer.parseInt(noTasksCompletedField.getText());
        int update = Integer.parseInt(updateField.getText());
        try {
            preparedStatement = connection.prepareStatement("update worker set idfactoryperson_worker = ?,noTasksCompleted = ? where idfactoryperson_worker = ?");
            preparedStatement.setString(1, String.valueOf(idfactoryperson_worker));
            preparedStatement.setString(2, String.valueOf(noTasksCompleted));
            preparedStatement.setString(3, String.valueOf(update));
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record Updated!");
            idfactoryperson_workerField.setText("");
            noTasksCompletedField.setText("");
            updateField.setText("");
            updateField.requestFocus();
            noTasksCompletedField.requestFocus();
            idfactoryperson_workerField.requestFocus();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public void delete() {
        idfactoryperson_worker = Integer.parseInt(deleteField.getText());
        try {
            preparedStatement = connection.prepareStatement("delete from worker where idfactoryperson_worker = ?");
            preparedStatement.setString(1, String.valueOf(idfactoryperson_worker));
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
