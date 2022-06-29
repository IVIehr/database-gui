import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class CRUD_on_task extends JDialog{
    private JTable table1;
    private JTextField inputQuantityField;
    private JTextField idinputMaterialField;
    private JTextField taskDescriptionField;
    private JTextField idoutputMaterialField;
    private JTextField outputProductionDateField;
    private JTextField taskmachine_idField;
    private JTextField taskworker_idField;
    private JTextField taskmanager_idField;
    private JButton addButton;
    private JTextField updateField;
    private JButton updateButton;
    private JTextField deleteField;
    private JButton deleteButton;
    private JPanel crudOperation;

    Connection connection;
    PreparedStatement preparedStatement;

    int idinputMaterial, inputQuantity, idtask, idoutputMaterial,taskmachine_id,taskworker_id,taskmanager_id;
    String taskDescription;
    Date outputProductionDate;

    public CRUD_on_task(JFrame parent) {
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
        taskmachine_id = Integer.parseInt(taskmachine_idField.getText());
        taskworker_id = Integer.parseInt(taskworker_idField.getText());
        inputQuantity = Integer.parseInt(inputQuantityField.getText());
        taskmanager_id = Integer.parseInt(taskmanager_idField.getText());
        taskDescription = taskDescriptionField.getText();
        outputProductionDate = Date.valueOf(outputProductionDateField.getText());
        idinputMaterial = Integer.parseInt(idinputMaterialField.getText());
        idoutputMaterial = Integer.parseInt(idoutputMaterialField.getText());

        try {
            preparedStatement = connection.prepareStatement("insert into task(taskDescription,idinputMaterial,inputQuantity,idoutputMaterial ,outputProductionDate,taskmachine_id ,taskworker_id ,taskmanager_id)values(?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, taskDescription);
            preparedStatement.setString(2, String.valueOf(idinputMaterial));
            preparedStatement.setString(3, String.valueOf(inputQuantity));
            preparedStatement.setString(4, String.valueOf(idoutputMaterial));
            preparedStatement.setString(5, String.valueOf(outputProductionDateField));
            preparedStatement.setString(6, String.valueOf(taskmachine_id));
            preparedStatement.setString(7, String.valueOf(taskworker_id));
            preparedStatement.setString(8, String.valueOf(taskmanager_id));
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record Added!");
            taskDescriptionField.setText("");
            idinputMaterialField.setText("");
            inputQuantityField.setText("");
            idoutputMaterialField.setText("");
            outputProductionDateField.setText("");
            taskmachine_idField.setText("");
            taskworker_idField.setText("");
            taskmanager_idField.setText("");
            taskDescriptionField.requestFocus();
            idinputMaterialField.requestFocus();
            inputQuantityField.requestFocus();
            idoutputMaterialField.requestFocus();
            outputProductionDateField.requestFocus();
            taskmachine_idField.requestFocus();
            taskworker_idField.requestFocus();
            taskmanager_idField.requestFocus();
        } catch (SQLException e1) {

            e1.printStackTrace();
        }
    }

    public void read() {
        try {
            preparedStatement = connection.prepareStatement("select * from task");
            ResultSet rs = preparedStatement.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        taskmachine_id = Integer.parseInt(taskmachine_idField.getText());
        taskworker_id = Integer.parseInt(taskworker_idField.getText());
        inputQuantity = Integer.parseInt(inputQuantityField.getText());
        taskmanager_id = Integer.parseInt(taskmanager_idField.getText());
        taskDescription = taskDescriptionField.getText();
        outputProductionDate = Date.valueOf(outputProductionDateField.getText());
        idinputMaterial = Integer.parseInt(idinputMaterialField.getText());
        idoutputMaterial = Integer.parseInt(idoutputMaterialField.getText());
        idtask = Integer.parseInt(updateField.getText());
        try {
            preparedStatement = connection.prepareStatement("update task set taskDescription = ?,idinputMaterial = ?,inputQuantity = ?,idoutputMaterial = ?,outputProductionDate = ?,taskmachine_id = ?,taskworker_id = ?,taskmanager_id = ?where idfactoryperson = ?");
            preparedStatement.setString(1, taskDescription);
            preparedStatement.setString(2, String.valueOf(idinputMaterial));
            preparedStatement.setString(3, String.valueOf(inputQuantity));
            preparedStatement.setString(4, String.valueOf(idoutputMaterial));
            preparedStatement.setString(5, String.valueOf(outputProductionDateField));
            preparedStatement.setString(6, String.valueOf(taskmachine_id));
            preparedStatement.setString(7, String.valueOf(taskworker_id));
            preparedStatement.setString(8, String.valueOf(taskmanager_id));
            preparedStatement.setString(9, String.valueOf(idtask));
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record Updated!");
            taskDescriptionField.setText("");
            idinputMaterialField.setText("");
            inputQuantityField.setText("");
            idoutputMaterialField.setText("");
            outputProductionDateField.setText("");
            taskmachine_idField.setText("");
            taskworker_idField.setText("");
            taskmanager_idField.setText("");
            taskDescriptionField.requestFocus();
            idinputMaterialField.requestFocus();
            inputQuantityField.requestFocus();
            idoutputMaterialField.requestFocus();
            outputProductionDateField.requestFocus();
            taskmachine_idField.requestFocus();
            taskworker_idField.requestFocus();
            taskmanager_idField.requestFocus();
            updateField.setText("");
            updateField.requestFocus();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public void delete() {
        idtask = Integer.parseInt(deleteField.getText());
        try {
            preparedStatement = connection.prepareStatement("delete from task where idtask = ?");
            preparedStatement.setString(1, String.valueOf(idtask));
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
