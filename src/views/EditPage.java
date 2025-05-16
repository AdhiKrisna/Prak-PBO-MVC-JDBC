package views;

import javax.swing.*;
import Models.BiodataModel;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import controllers.BiodataController;

public class EditPage extends JFrame implements ActionListener {
    int id;
    BiodataController controller = new BiodataController();

    JLabel labelNama, labelUmur, labelAgama, labelGender, labelSkill;
    JTextField fieldNama, fieldUmur;
    String[] namaAgama = { "Islam", "Kristen", "Katolik", "Hindu", "Budha", "Konghucu" };
    JComboBox<String> comboAgama = new JComboBox<>(namaAgama);
    JRadioButton radioLaki, radioPerempuan;
    ButtonGroup groupGender;
    JCheckBox checkWeb, checkMobile, checkGame, checkUIUX, checkDS, checkAI;
    JButton tombolUpdate;

    String selectedGender;
    String selectedSkills;
    private Runnable refreshTable;

    public EditPage(int id, String nama, int umur, String agama, String gender, String skills,
            Runnable refreshTable) {
        this.id = id;
        this.selectedGender = gender;
        this.selectedSkills = skills;
        this.refreshTable = refreshTable;

        initComponents();
        setLayoutPosition();

        fieldNama.setText(nama);
        fieldUmur.setText(String.valueOf(umur));
        comboAgama.setSelectedItem(agama);

        if (gender.equalsIgnoreCase("Laki-laki")) {
            radioLaki.setSelected(true);
        } else if (gender.equalsIgnoreCase("Perempuan")) {
            radioPerempuan.setSelected(true);
        }

        if (skills.contains("Web Developer"))
            checkWeb.setSelected(true);
        if (skills.contains("Mobile Developer"))
            checkMobile.setSelected(true);
        if (skills.contains("Game Developer"))
            checkGame.setSelected(true);
        if (skills.contains("UI/UX Designer"))
            checkUIUX.setSelected(true);
        if (skills.contains("Data Scientist"))
            checkDS.setSelected(true);
        if (skills.contains("AI Engineer"))
            checkAI.setSelected(true);
    }

    private void initComponents() {
        setTitle("Edit Data");
        setSize(700, 350);
        setVisible(true);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Label
        labelNama = new JLabel("Nama");
        labelUmur = new JLabel("Umur");
        labelAgama = new JLabel("Agama");
        labelGender = new JLabel("Gender");
        labelSkill = new JLabel("Skill");

        // Text Field
        fieldNama = new JTextField();
        fieldUmur = new JTextField();

        // radio gender
        radioLaki = new JRadioButton("Laki-laki");
        radioPerempuan = new JRadioButton("Perempuan");
        groupGender = new ButtonGroup();
        groupGender.add(radioLaki);
        groupGender.add(radioPerempuan);

        // checkbox skill
        checkWeb = new JCheckBox("Web Developer");
        checkMobile = new JCheckBox("Mobile Developer");
        checkGame = new JCheckBox("Game Developer");
        checkUIUX = new JCheckBox("UI/UX Designer");
        checkDS = new JCheckBox("Data Scientist");
        checkAI = new JCheckBox("AI Engineer");

        // button update
        tombolUpdate = new JButton("Update");
        tombolUpdate.addActionListener(this);

        // Add components
        add(labelNama);
        add(labelUmur);
        add(labelAgama);
        add(labelGender);
        add(labelSkill);
        add(fieldNama);
        add(fieldUmur);
        add(radioLaki);
        add(radioPerempuan);
        add(checkWeb);
        add(checkMobile);
        add(checkGame);
        add(checkUIUX);
        add(checkDS);
        add(checkAI);
        add(comboAgama);
        add(tombolUpdate);
    }

    private void setLayoutPosition() {
        labelNama.setBounds(10, 10, 120, 20);
        labelUmur.setBounds(10, 40, 120, 20);
        labelAgama.setBounds(10, 70, 120, 20);
        labelGender.setBounds(10, 100, 120, 20);
        labelSkill.setBounds(10, 130, 120, 20);

        fieldNama.setBounds(150, 10, 150, 20);
        fieldUmur.setBounds(150, 40, 150, 20);
        radioLaki.setBounds(150, 100, 100, 20);
        radioPerempuan.setBounds(250, 100, 100, 20);
        checkWeb.setBounds(150, 130, 150, 20);
        checkMobile.setBounds(300, 130, 150, 20);
        checkGame.setBounds(450, 130, 150, 20);
        checkUIUX.setBounds(150, 160, 150, 20);
        checkDS.setBounds(300, 160, 150, 20);
        checkAI.setBounds(450, 160, 150, 20);
        comboAgama.setBounds(150, 70, 150, 20);

        tombolUpdate.setBounds(150, 200, 100, 30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String nama = fieldNama.getText().trim();
            int umur = Integer.parseInt(fieldUmur.getText());
            String agama = comboAgama.getSelectedItem().toString();
            String gender = "";

            List<String> skill = new ArrayList<>();
            if (radioLaki.isSelected())
                gender = "Laki-laki";
            else if (radioPerempuan.isSelected())
                gender = "Perempuan";

            if (checkWeb.isSelected())
                skill.add("Web Developer");
            if (checkMobile.isSelected())
                skill.add("Mobile Developer");
            if (checkGame.isSelected())
                skill.add("Game Developer");
            if (checkUIUX.isSelected())
                skill.add("UI/UX Designer");
            if (checkDS.isSelected())
                skill.add("Data Scientist");
            if (checkAI.isSelected())
                skill.add("AI Engineer");

            if (nama.isEmpty() || fieldUmur.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tolong isi semua field", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (gender.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Pilih salah satu gender", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (skill.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Pilih minimal satu skill", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String skillStr = String.join(", ", skill);
            BiodataModel biodata = new BiodataModel(id, nama, umur, agama, gender, skillStr);
            controller.updateData(biodata);
            JOptionPane.showMessageDialog(this, "Data berhasil diperbarui!");
            if (refreshTable != null) {
                refreshTable.run(); // kasih sinyal ke ReadPage
            }
            this.dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Tolong isi umur dengan angka", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
