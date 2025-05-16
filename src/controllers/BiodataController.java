package controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.BiodataModel;
import connector.Connector;

public class BiodataController {            
    // Connection conn;
    Connector connector = new Connector();
    PreparedStatement preparedStatement;

    //ini bisa copy dari connector lama
    public void insertData(BiodataModel biodata) {
        try {
            String query = "INSERT INTO biodata (nama, umur, agama, gender, skills) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connector.conn.prepareStatement(query);
            preparedStatement.setString(1, biodata.getNama());
            preparedStatement.setInt(2, biodata.getUmur());
            preparedStatement.setString(3, biodata.getAgama());
            preparedStatement.setString(4, biodata.getGender());
            preparedStatement.setString(5, biodata.getSkills());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //ini bisa copy dari connector lama
    public void updateData(BiodataModel data) {
        try {
            String query = "UPDATE biodata SET nama=?, umur=?, agama=?, gender=?, skills=? WHERE id=?";
            preparedStatement = connector.conn.prepareStatement(query);
            preparedStatement.setString(1, data.getNama());
            preparedStatement.setInt(2, data.getUmur());
            preparedStatement.setString(3, data.getAgama());
            preparedStatement.setString(4, data.getGender());
            preparedStatement.setString(5, data.getSkills());
            preparedStatement.setInt(6, data.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //ini bisa copy dari connector lama
    public void deleteData(int id) {
        try {
            String query = "DELETE FROM biodata WHERE id=?";
            preparedStatement = connector.conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    //ini bisa copy dari connector lama dengan sedikit modifikasi
    public List<BiodataModel> getAllData() {
        List<BiodataModel> dataList = new ArrayList<>();
        try {
            preparedStatement = connector.conn.prepareStatement("SELECT * FROM biodata");
            ResultSet rs = preparedStatement.executeQuery("SELECT * FROM biodata");
            while (rs.next()) {
                dataList.add(new BiodataModel(
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getInt("umur"),
                    rs.getString("agama"),
                    rs.getString("gender"),
                    rs.getString("skills")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    // public BiodataModel getDataById(int id) {
    //     BiodataModel data = null;
    //     try {
    //         String query = "SELECT * FROM biodata WHERE id=?";
    //         PreparedStatement preparedStatement = connector.conn.prepareStatement(query);
    //         preparedStatement.setInt(1, id);
    //         ResultSet rs = preparedStatement.executeQuery();
    //         if (rs.next()) {
    //             data = new BiodataModel(
    //                 rs.getInt("id"),
    //                 rs.getString("nama"),
    //                 rs.getInt("umur"),
    //                 rs.getString("agama"),
    //                 rs.getString("gender"),
    //                 rs.getString("skills")
    //             );
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    //     return data;
    // }

}
