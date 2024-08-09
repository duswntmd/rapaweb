package com.test.sku.pet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetDAO {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    private Connection getConn() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insertPet(PetVO pet) {
        String sql = "INSERT INTO pet (no, name, origin, weight, birth, price, pic) VALUES (?, ?, ?, ?, ?, ?, ?)";
        conn = getConn();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, pet.getNo()); // no 값을 수동으로 설정
            pstmt.setString(2, pet.getName());
            pstmt.setString(3, pet.getOrigin());
            pstmt.setDouble(4, pet.getWeight());
            pstmt.setDate(5, pet.getBirth());
            pstmt.setInt(6, pet.getPrice());
            pstmt.setString(7, pet.getPic());
            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            closeAll();
        }
        return false;
    }

    public List<PetVO> getAllPets() {
        List<PetVO> list = new ArrayList<>();
        String sql = "SELECT * FROM pet";
        conn = getConn();
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                PetVO pet = new PetVO();
                pet.setNo(rs.getInt("no"));
                pet.setName(rs.getString("name"));
                pet.setOrigin(rs.getString("origin"));
                pet.setWeight(rs.getDouble("weight"));
                pet.setBirth(rs.getDate("birth"));
                pet.setPrice(rs.getInt("price"));
                pet.setPic(rs.getString("pic"));
                list.add(pet);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            closeAll();
        }
        return list;
    }

    public PetVO getPetByNo(int no) {
        PetVO pet = null;
        String sql = "SELECT * FROM pet WHERE no = ?";
        conn = getConn();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, no);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                pet = new PetVO();
                pet.setNo(rs.getInt("no"));
                pet.setName(rs.getString("name"));
                pet.setOrigin(rs.getString("origin"));
                pet.setWeight(rs.getDouble("weight"));
                pet.setBirth(rs.getDate("birth"));
                pet.setPrice(rs.getInt("price"));
                pet.setPic(rs.getString("pic"));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            closeAll();
        }
        return pet;
    }

    public boolean updatePet(PetVO pet) {
        String sql = "UPDATE pet SET name=?, origin=?, weight=?, birth=?, price=?, pic=? WHERE no=?";
        conn = getConn();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, pet.getName());
            pstmt.setString(2, pet.getOrigin());
            pstmt.setDouble(3, pet.getWeight());
            pstmt.setDate(4, pet.getBirth());
            pstmt.setInt(5, pet.getPrice());
            pstmt.setString(6, pet.getPic());
            pstmt.setInt(7, pet.getNo());
            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            closeAll();
        }
        return false;
    }

    public boolean deletePet(int no) {
        String sql = "DELETE FROM pet WHERE no=?";
        conn = getConn();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, no);
            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            closeAll();
        }
        return false;
    }
    
    public List<PetVO> searchPets(String category, String keyword) {
        List<PetVO> list = new ArrayList<>();
        String sql = "SELECT * FROM pet WHERE " + category + " LIKE ?";
        try {
            conn = getConn();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + keyword + "%");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                PetVO pet = new PetVO();
                pet.setNo(rs.getInt("no"));
                pet.setName(rs.getString("name"));
                pet.setOrigin(rs.getString("origin"));
                pet.setWeight(rs.getDouble("weight"));
                pet.setBirth(rs.getDate("birth"));
                pet.setPrice(rs.getInt("price"));
                pet.setPic(rs.getString("pic"));
                list.add(pet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return list;
    }

    private void closeAll() {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
