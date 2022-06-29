import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class CRUD_on_workson extends JDialog{
    private JTextField updateField;
    private JButton updateButton;
    private JTextField deleteField;
    private JButton deleteButton;
    private JButton addButton;
    private JTextField worksontask_idField;
    private JTextField endTimeField;
    private JTextField startTimeField;
    private JTextField worksonworker_idField;
    private JTable table1;
    private JPanel crudOperation;

    Connection connection;
    PreparedStatement preparedStatement;

    int worksontask_id, worksonworker_id;
    Date startTime, endTime;

    public CRUD_on_workson(JFrame parent) {
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
        startTime = Date.valueOf(startTimeField.getText());
        endTime = Date.valueOf(endTimeField.getText());
        worksontask_id = Integer.parseInt(endTimeField.getText());
        worksonworker_id = Integer.parseInt(startTimeField.getText());

        try {
            preparedStatement = connection.prepareStatement("insert into workson(startTime,endTime,worksontask_id,worksonworker_id)values(?,?,?,?)");
            preparedStatement.setString(1, String.valueOf(startTime));
            preparedStatement.setString(2, String.valueOf(endTime));
            preparedStatement.setString(3, String.valueOf(worksontask_id));
            preparedStatement.setString(4, String.valueOf(worksonworker_id));
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record Added!");
            worksontask_idField.setText("");
            worksonworker_idField.setText("");
            endTimeField.setText("");
            startTimeField.setText("");
            worksontask_idField.requestFocus();
            worksonworker_idField.requestFocus();
            endTimeField.requestFocus();
            startTimeField.requestFocus();
        } catch (SQLException e1) {

            e1.printStackTrace();
        }
    }

    public void read() {
        try {
            preparedStatement = connection.prepareStatement("select * from workson");
            ResultSet rs = preparedStatement.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        startTime = Date.valueOf(startTimeField.getText());
        endTime = Date.valueOf(endTimeField.getText());
        worksontask_id = Integer.parseInt(endTimeField.getText());
        worksonworker_id = Integer.parseInt(startTimeField.getText());
        int update = Integer.parseInt(updateField.getText());
        try {
            preparedStatement = connection.prepareStatement("update workson set startTime = ?,endTime = ?,worksontask_id = ?,worksonworker_id = ? where idfactory = ?");
            preparedStatement.setString(1, String.valueOf(startTime));
            preparedStatement.setString(2, String.valueOf(endTime));
            preparedStatement.setString(3, String.valueOf(worksontask_id));
            preparedStatement.setString(4, String.valueOf(worksonworker_id));
            preparedStatement.setString(5, String.valueOf(update));
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record Updated!");
            worksontask_idField.setText("");
            worksonworker_idField.setText("");
            endTimeField.setText("");
            startTimeField.setText("");
            updateField.setText("");
            worksontask_idField.requestFocus();
            worksonworker_idField.requestFocus();
            endTimeField.requestFocus();
            startTimeField.requestFocus();
            updateField.requestFocus();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public void delete() {
        worksontask_id  = Integer.parseInt(deleteField.getText());
        try {
            preparedStatement = connection.prepareStatement("delete from workson where \tworksontask_id  = ?");
            preparedStatement.setString(1, String.valueOf(	worksontask_id ));
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
