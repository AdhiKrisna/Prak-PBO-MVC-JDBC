package views;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class ResultPage extends JFrame  {
    JLabel labelNama, labelUmur, labelAgama, labelGender, labelSkill;
    JButton tombolKembali;

    public ResultPage(String nama, int umur, String agama, String gender, List<String> skillList) {
        setTitle("Result Page");
        setSize(700, 350);
        setVisible(true);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Label
        labelNama = new JLabel("Nama: " + nama);
        labelUmur = new JLabel("Umur: " + umur);    
        labelAgama = new JLabel("Agama: " + agama);
        labelGender = new JLabel("Gender: " + gender);
        labelSkill = new JLabel("Skill: " + String.join(", ", skillList));

        // Tombol kembali
        tombolKembali = new JButton("Kembali");

        // Menambahkan komponen ke frame
        add(labelNama);
        add(labelUmur);
        add(labelAgama);
        add(labelGender);
        add(labelSkill);
        add(tombolKembali);

        // cara 2
        tombolKembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Menutup ResultPage
            }
        });


        setLayoutPosition();
    }

    private void setLayoutPosition() {
        labelNama.setBounds(20, 20, 200, 20);
        labelUmur.setBounds(20, 50, 200, 20);
        labelAgama.setBounds(20, 80, 200, 20);
        labelGender.setBounds(20, 110, 200, 20);
        labelSkill.setBounds(20, 140, 200, 20);
        tombolKembali.setBounds(20, 170, 100, 20);
    }


}
