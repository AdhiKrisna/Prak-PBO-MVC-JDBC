package views;

import javax.swing.*;

import Models.BiodataModel;
import controllers.BiodataController;

import java.util.ArrayList;
import java.util.List;
import java.awt.event.*;

public class InputPage extends JFrame implements ActionListener {

    JButton tombolSubmit;
    JButton tombolClear;
    JButton tombolLihatData;
    JLabel labelNama, labelUmur, labelAgama, labelGender, labelSkill;
    JTextField fieldNama, fieldUmur;
    String[] namaAgama = { "Islam", "Kristen", "Katolik", "Hindu", "Budha", "Konghucu" };
    ButtonGroup groupGender;
    JRadioButton radioLaki, radioPerempuan;
    JComboBox<String> comboAgama = new JComboBox<>(namaAgama);
    JCheckBox checkWeb, checkMobile, checkGame, checkUIUX, checkDS, checkAI;
    BiodataController controller = new BiodataController();

    private void initComponents() {
        setTitle("Form Page");
        setSize(700, 350);
        setVisible(true);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

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

        // submit button
        tombolSubmit = new JButton("Submit");
        tombolClear = new JButton("Clear");
        tombolLihatData = new JButton("Lihat Data");

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
        add(tombolSubmit);
        add(tombolClear);
        add(tombolLihatData);

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

        tombolSubmit.setBounds(150, 200, 100, 30);
        tombolClear.setBounds(260, 200, 100, 30);
        tombolLihatData.setBounds(370, 200, 120, 30);

        tombolSubmit.addActionListener(this);
        tombolClear.addActionListener(this);
        tombolLihatData.addActionListener(this);
    }

    // method untuk menangani event
    // cara 1
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == tombolSubmit) {
            submitAction();
        }
        else if (e.getSource() == tombolLihatData) {
            new ReadPage();
        }
        else {
           clearAction();
        }

    }

    private void submitAction() {
        try {
            String nama = fieldNama.getText().trim();
            int umur = Integer.parseInt(fieldUmur.getText());
            String agama = comboAgama.getSelectedItem().toString();
            String gender = "";
            List<String> skill = new ArrayList<>();
            if (radioLaki.isSelected()) {
                gender = "Laki-laki";
            } else if (radioPerempuan.isSelected()) {
                gender = "Perempuan";
            }
            // skill
            if (checkWeb.isSelected()) {
                skill.add("Web Developer");
            }
            if (checkMobile.isSelected()) {
                skill.add("Mobile Developer");
            }
            if (checkGame.isSelected()) {
                skill.add("Game Developer");
            }
            if (checkUIUX.isSelected()) {
                skill.add("UI/UX Designer");
            }
            if (checkDS.isSelected()) {
                skill.add("Data Scientist");
            }
            if (checkAI.isSelected()) {
                skill.add("AI Engineer");
            }

            String skillStr = String.join(", ", skill);

            if (!radioLaki.isSelected() && !radioPerempuan.isSelected()) {
                JOptionPane.showMessageDialog(this, "Pilih salah satu gender", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (skill.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Pilih minimal satu skill", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            UIManager.put("OptionPane.yesButtonText", "Simpan");
            UIManager.put("OptionPane.noButtonText", "Batal");

            int response = JOptionPane.showConfirmDialog(this,
                    "Nama: " + nama + "\nUmur: " + umur + "\nAgama: " + agama + "\nGender: "
                            + gender + "\nSkill: " + skillStr,
                    "Konfirmasi Data", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

            if (response == JOptionPane.YES_OPTION) {
                new ResultPage(nama, umur, agama, gender, skill);
                // this.dispose();
                // simpan data ke database
                BiodataModel insertBiodata = new BiodataModel(nama, umur, agama, gender, skillStr);
                controller.insertData(insertBiodata);

            } else {
                JOptionPane.getRootFrame().dispose();
            }

        } catch (Exception ex) { // ini akan mengecek smua error yang ada
            // jika field nama atau umur kosong
            // jika field umur bukan angka

            // maka dari itu, kita akan mengontrol pesan error untuk nama dan umur jika
            // field kosong pada sebuah if statement
            if (fieldNama.getText().isEmpty() || fieldUmur.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tolong isi semua field", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // sehingga jika error yang tersisa tinggal field umur bukan angka, maka akan
            // muncul pesan error
            JOptionPane.showMessageDialog(this, "Tolong isi umur dengan angka", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    private void clearAction() {
        fieldNama.setText("");
        fieldUmur.setText("");
        groupGender.clearSelection();
        checkWeb.setSelected(false);
        checkMobile.setSelected(false);
        checkGame.setSelected(false);
        checkUIUX.setSelected(false);
        checkDS.setSelected(false);
        checkAI.setSelected(false);
        comboAgama.setSelectedIndex(0);
    }

    public InputPage() {
        initComponents();
        setLayoutPosition();
    }

}
