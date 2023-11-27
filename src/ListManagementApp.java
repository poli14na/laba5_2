import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListManagementApp extends JFrame {
    private DefaultListModel<String> listModel;
    private JList<String> listView;
    private JCheckBox oddCheckbox;
    private JCheckBox evenCheckbox;
    private JComboBox<String> choiceBox;

    public ListManagementApp() {
        super("List Management App");

        listModel = new DefaultListModel<>();
        listView = new JList<>(listModel);
        oddCheckbox = new JCheckBox("Выбрать нечетные строки");
        evenCheckbox = new JCheckBox("Выбрать четные строки");
        choiceBox = new JComboBox<>();

        setupUI();
    }

    private void setupUI() {
        setLayout(new BorderLayout());

        add(new JScrollPane(listView), BorderLayout.CENTER);

        JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(new FlowLayout());

        controlsPanel.add(oddCheckbox);
        controlsPanel.add(evenCheckbox);
        controlsPanel.add(choiceBox);

        add(controlsPanel, BorderLayout.SOUTH);

        oddCheckbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateListSelection();
            }
        });

        evenCheckbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateListSelection();
            }
        });

        updateList();

        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateList() {
        listModel.clear();

        for (int i = 1; i <= 10; i++) {
            listModel.addElement("Строка " + i);
        }

        updateListSelection();
    }

    private void updateListSelection() {
        listModel.clear();

        for (int i = 1; i <= 10; i++) {
            if ((oddCheckbox.isSelected() && i % 2 != 0) || (evenCheckbox.isSelected() && i % 2 == 0)) {
                listModel.addElement("Строка " + i);
            }
        }

        updateChoiceBox();
    }

    private void updateChoiceBox() {
        choiceBox.removeAllItems();
        for (int i = 0; i < listModel.size(); i++) {
            choiceBox.addItem(listModel.getElementAt(i));
        }
    }
}