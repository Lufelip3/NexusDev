/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import BD.Conexao;
import Objetos.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author andrey.munhoz
 */
public class VendaDAO {
    public List<Venda> read() {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Venda> vendas = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM venda");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Venda v = new Venda();
                v.setDataVenda(rs.getString("Data_Venda"));
                v.setValorVenda(rs.getDouble("Valor_Venda"));
                vendas.add(v);
            }
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, "Falha ao obter dados: " + e);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return vendas;
    }

    public void create(Venda v) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO venda (Data_Venda, Valor_Venda) VALUES (?,?)");
            stmt.setString(1, v.getDataVenda());
            stmt.setDouble(2, v.getValorVenda());

            stmt.execute();
            JOptionPane.showMessageDialog(null, "Venda cadastrado com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao cadastrar: " + e);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public void updtae(Venda v) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE venda set Data_Venda = ?, Valor_Venda = ?, where NotaFiscal = ?");
            stmt.setString(1, v.getDataVenda());
            stmt.setDouble(2, v.getValorVenda());
            stmt.setString(3, v.getNotaFiscalVenda());          

            stmt.execute();
            JOptionPane.showMessageDialog(null, "Venda atualizado com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao atualizar: " + e);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public void delete(Venda v) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM venda where NotaFiscal = ?");
            stmt.setString(1, v.getNotaFiscalVenda());

            stmt.execute();
            JOptionPane.showMessageDialog(null, "Venda removido com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao remover: " + e);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }
}
