/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import BD.Conexao;
import Objetos.Compra;
import java.math.BigDecimal;
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
public class CompraDAO {
public List<Compra> read() {
//    String sql = "INSERT INTO compra (Valor_Total, CPF, CNPJ_Drog) VALUES (?, ?, ?)";
//try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
//
//    stmt.setBigDecimal(1, new BigDecimal("123.45")); // exemplo de Valor_Total
//    stmt.setString(2, "123.456.789-00");             // CPF válido
//    stmt.setString(3, "12.345.678/0001-99");         // CNPJ válido
//
//    stmt.executeUpdate();
//
//    try (ResultSet rs = stmt.getGeneratedKeys()) {
//        if (rs.next()) {
//            long id = rs.getLong(1); // NotaFiscal_Entrada gerado automaticamente
//            System.out.println("NotaFiscal_Entrada gerado: " + id);
//        }
//    }
//}
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Compra> compras = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM compra");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Compra c = new Compra();
                c.setNotaFiscalCompra(rs.getInt("NotaFiscal_Entrada"));
                c.setValorTotal(rs.getDouble("Valor_Total"));
                c.setCpfCompra(rs.getString("CPF"));
                c.setCnpjCompra(rs.getString("CNPJ_Drog"));
                compras.add(c);
            }
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, "Falha ao obter dados: " + e);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return compras;
    }

    public void create(Compra c) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO compra (Valor_Total, CPF, CNPJ_Drog) VALUES (?,?,?)");
            stmt.setDouble(1, c.getValorTotal());
            stmt.setString(2, c.getCpfCompra());
            stmt.setString(3, c.getCnpjCompra());

            stmt.execute();
            JOptionPane.showMessageDialog(null, "Venda cadastrada com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao cadastrar: " + e);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public void updtae(Compra c) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE compra set Valor_Total = ?, CPF = ?, CNPJ_Drog = ? where NotaFiscal_Entrada = ?");
            stmt.setDouble(1, c.getValorTotal());
            stmt.setString(2, c.getCpfCompra());
            stmt.setString(3, c.getCnpjCompra());
            stmt.setInt(4, c.getNotaFiscalCompra());

            stmt.execute();
            JOptionPane.showMessageDialog(null, "Compra atualizada com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao atualizar: " + e);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public void delete(Compra c) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM compra where NotaFiscal_Entrada = ?");
            stmt.setInt(1, c.getNotaFiscalCompra());

            stmt.execute();
            JOptionPane.showMessageDialog(null, "Compra removida com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao remover: " + e);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }
}
