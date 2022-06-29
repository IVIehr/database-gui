import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class homePage extends JDialog {
    private JPanel home;
    private JComboBox<String> tablesBox;
    private JLabel enterText;
    private JLabel enterText2;
    private JButton CRUDOperationButton;

    private String tableName;

    public homePage(JFrame parent, String user) {
        super(parent);
        setTitle("home page");
        setContentPane(home);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        initComboBox();
        enterText.setText("successful authentication of: " + user);
        enterText2.setText("you can do CRUD operation on database");
        tablesBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableName = Objects.requireNonNull(tablesBox.getSelectedItem()).toString();
            }
        });
        CRUDOperationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (tableName) {
                    case "factory" -> {
                        CRUD_on_factory crud = new CRUD_on_factory(null);
                    }
                    case "factoryperson" -> {
                        CRUD_on_factoryperson crud = new CRUD_on_factoryperson(null);
                    }
                    case "finalproduct" -> {
                        CRUD_on_finalproduct crud = new CRUD_on_finalproduct(null);
                    }
                    case "machine" -> {
                        CRUD_on_machine crud = new CRUD_on_machine(null);
                    }
                    case "manager" -> {
                        CRUD_on_manager crud = new CRUD_on_manager(null);
                    }
                    case "payment" -> {
                        CRUD_on_paymnet crud = new CRUD_on_paymnet(null);
                    }
                    case "rawmaterial" -> {
                        CRUD_on_rawmaterial crud = new CRUD_on_rawmaterial(null);
                    }
                    case "task" -> {
                        CRUD_on_task crud = new CRUD_on_task(null);
                    }
                    case "taskassignment" -> {
                        CRUD_on_taskassignment crud = new CRUD_on_taskassignment(null);
                    }
                    case "worker" -> {
                        CRUD_on_worker crud = new CRUD_on_worker(null);
                    }
                    case "workson" -> {
                        CRUD_on_workson crud = new CRUD_on_workson(null);
                    }
                }
            }
        });
        setVisible(true);
    }

    private void initComboBox() {
        tablesBox.addItem("factory");
        tablesBox.addItem("factoryperson");
        tablesBox.addItem("finalproduct");
        tablesBox.addItem("machine");
        tablesBox.addItem("manager");
        tablesBox.addItem("payment");
        tablesBox.addItem("rawmaterial");
        tablesBox.addItem("task");
        tablesBox.addItem("taskassignment");
        tablesBox.addItem("worker");
        tablesBox.addItem("workson");
    }
}
