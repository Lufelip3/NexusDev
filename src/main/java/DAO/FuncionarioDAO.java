/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import BD.Conexao;
import Objetos.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author lucas.gmpedro
 */
public class FuncionarioDAO {

    public List<Funcionario> read() {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Funcionario> funcionarios = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM funcionario");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionario f = new Funcionario();
                f.setCpf(rs.getString("CPF"));
                f.setNome_Fun(rs.getString("Nome_Fun"));
                f.setTelefone_Fun(rs.getString("Telefone_Fun"));
                f.setCep_Fun(rs.getString("Cep_Fun"));
                f.setNumero_Fun(rs.getInt("Num_Fun"));
                f.setEmail_Fun(rs.getString("Email_Fun"));
                f.setSenha(rs.getString("Senha"));
                f.setCargo(rs.getString("Cargo"));
                funcionarios.add(f);
            }
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, "Falha ao obter dados: " + e);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return funcionarios;
    }

    public void create(Funcionario f) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO funcionario (CPF, Nome_Fun, Telefone_Fun, Cep_Fun, Num_Fun, Email_Fun, Senha, Cargo) VALUES (?,?,?,?,?,?,?,?)");
            stmt.setString(1, f.getCpf());
            stmt.setString(2, f.getNome_Fun());
            stmt.setString(3, f.getTelefone_Fun());
            stmt.setString(4, f.getCep_Fun());
            stmt.setInt(5, f.getNumero_Fun());
            stmt.setString(6, f.getEmail_Fun());
            stmt.setString(7, f.getSenhaHash());
            stmt.setString(8, f.getCargo());

            stmt.execute();
            JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao cadastrar: " + e);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public void updtae(Funcionario f) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE funcionario set Nome_Fun = ?, Telefone_Fun = ?, Cep_Fun = ?, Num_Fun = ?, Email_Fun = ? Senha = ? Cargo = ? where CPF = ?");
            stmt.setString(1, f.getNome_Fun());
            stmt.setString(2, f.getTelefone_Fun());
            stmt.setString(3, f.getCep_Fun());
            stmt.setInt(4, f.getNumero_Fun());
            stmt.setString(5, f.getEmail_Fun());
            stmt.setString(6, f.getSenhaHash());
            stmt.setString(7, f.getCargo());
            stmt.setString(8, f.getCpf());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Funcionário atualizado com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao atualizar: " + e);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public void delete(Funcionario f) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM funcionario where CPF = ?");
            stmt.setString(1, f.getCpf());

            stmt.execute();
            JOptionPane.showMessageDialog(null, "Funcionário removido com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao remover: " + e);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }
}
