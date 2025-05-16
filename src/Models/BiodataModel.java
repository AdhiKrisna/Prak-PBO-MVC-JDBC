
package Models;


public class BiodataModel {
    private int id;
    private String nama;
    private int umur;
    private String agama;
    private String gender;
    private String skills;
   
    // untuk constructor yang ada id (read, edit)
    public BiodataModel(int id, String nama2, int umur2, String agama, String gender, String skills) {
        this.id = id;
        this.nama = nama2;
        this.umur = umur2;
        this.agama = agama;
        this.gender = gender;
        this.skills = skills;
    }
    // untuk constructor yang tidak ada id (input)
    public BiodataModel(String nama, int umur, String agama, String gender, String skills) {
            this.nama = nama;
            this.umur = umur;
            this.agama = agama;
            this.gender = gender;
            this.skills = skills;
        }
    
    // Getters
    public int getId() {
        return id;
    }

    // kebawah opsional

    public String getNama() {
        return nama;
    }

    public int getUmur() {
        return umur;
    }

    public String getAgama() {
        return agama;
    }

    public String getGender() {
        return gender;
    }

    public String getSkills() {
        return skills;
    }
    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

}
