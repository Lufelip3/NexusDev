/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import BD.Conexao;
import Objetos.Drogaria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author luis.fmleite
 */
public class DrogariaDAO {
    public List<Drogaria> read() {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Drogaria> drogarias = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM funcionario");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Drogaria d = new Drogaria();
                d.setCnpjDrogaria(rs.getString("CNPJ_Drog"));
                d.setNomeDrogaria(rs.getString("Nome_Drog"));
                d.setEmailDrogaria(rs.getString("Email_Drog"));
                d.setTelefoneDrogaria(rs.getString("Telefone_Drog"));
                d.setNumeroDrogaria(rs.getInt("Num_Drog"));
                d.setCepDrogaria(rs.getString("Cep_Drog"));
                drogarias.add(d);
            }
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, "Falha ao obter dados: " + e);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return drogarias;
    }

    public void create(Drogaria d) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO drogaria (CNPJ_Drog, Nome_Drog, Telefone_Drog, Cep_Drog, Num_Drog, Email_Drog) VALUES (?,?,?,?,?,?)");
            stmt.setString(1, d.getCnpjDrogaria());
            stmt.setString(2, d.getNomeDrogaria());
            stmt.setString(3, d.getTelefoneDrogaria());
            stmt.setString(4, d.getCepDrogaria());
            stmt.setInt(5, d.getNumeroDrogaria());
            stmt.setString(6, d.getEmailDrogaria());

            stmt.execute();
            JOptionPane.showMessageDialog(null, "Drogaria cadastrado com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao cadastrar: " + e);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public void updtae(Drogaria d) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE drogaria (Nome_Drog = ?, Telefone_Drog = ?, Cep_Drog = ?, Num_Drog = ?, Email_Drog = ? where CNPJ_Drog = ?");
            stmt.setString(1, d.getNomeDrogaria());
            stmt.setString(2, d.getTelefoneDrogaria());
            stmt.setString(3, d.getCepDrogaria());
            stmt.setInt(4, d.getNumeroDrogaria());
            stmt.setString(5, d.getEmailDrogaria());
            stmt.setString(6, d.getCnpjDrogaria());

            stmt.execute();
            JOptionPane.showMessageDialog(null, "Drogaria atualizado com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao atualizar: " + e);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public void delete(Drogaria d) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM funcionario where CPF = ?");
            stmt.setString(1, d.getCnpjDrogaria());

            stmt.execute();
            JOptionPane.showMessageDialog(null, "Drogaria removido com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao remover: " + e);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }
}
