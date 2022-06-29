import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class CRUD_on_rawmaterial extends JDialog {
    private JTable table1;
    private JTextField rawmaterialmachine_idField;
    private JTextField rawmaterialfactory_idField;
    private JButton addButton;
    private JTextField updateField;
    private JButton updateButton;
    private JTextField deleteField;
    private JButton deleteButton;
    private JPanel crudOperation;

    Connection connection;
    PreparedStatement preparedStatement;

    int rawmaterialfactory_id, rawmaterialmachine_id, idrawmaterial;

    public CRUD_on_rawmaterial(JFrame parent) {
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
        rawmaterialfactory_id = Integer.parseInt(rawmaterialfactory_idField.getText());
        rawmaterialmachine_id = Integer.parseInt(rawmaterialmachine_idField.getText());

        try {
            preparedStatement = connection.prepareStatement("insert into rawmaterial(rawmaterialfactory_id,rawmaterialmachine_id)values(?,?)");
            preparedStatement.setString(1, String.valueOf(rawmaterialfactory_id));
            preparedStatement.setString(2, String.valueOf(rawmaterialmachine_id));
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record Added!");
            rawmaterialfactory_idField.setText("");
            rawmaterialmachine_idField.setText("");
            rawmaterialmachine_idField.requestFocus();
            rawmaterialfactory_idField.requestFocus();
        } catch (SQLException e1) {

            e1.printStackTrace();
        }
    }

    public void read() {
        try {
            preparedStatement = connection.prepareStatement("select * from rawmaterial");
            ResultSet rs = preparedStatement.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        rawmaterialfactory_id = Integer.parseInt(rawmaterialfactory_idField.getText());
        rawmaterialmachine_id = Integer.parseInt(rawmaterialmachine_idField.getText());
        idrawmaterial = Integer.parseInt(updateField.getText());
        try {
            preparedStatement = connection.prepareStatement("update rawmaterial set rawmaterialfactory_id = ?,rawmaterialmachine_id = ? where idrawmaterial = ?");
            preparedStatement.setString(1, String.valueOf(rawmaterialfactory_id));
            preparedStatement.setString(2, String.valueOf(rawmaterialmachine_id));
            preparedStatement.setString(3, String.valueOf(idrawmaterial));
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record Updated!");
            rawmaterialfactory_idField.setText("");
            rawmaterialmachine_idField.setText("");
            updateField.setText("");
            updateField.requestFocus();
            rawmaterialmachine_idField.requestFocus();
            rawmaterialfactory_idField.requestFocus();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public void delete() {
        idrawmaterial = Integer.parseInt(deleteField.getText());
        try {
            preparedStatement = connection.prepareStatement("delete from rawmaterial where idrawmaterial = ?");
            preparedStatement.setString(1, String.valueOf(idrawmaterial));
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
