package DAO;

import BD.Conexao;
import Objetos.Compra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CompraDAO {

    // ---------------------------------------------------------------------
    // LER TODAS AS COMPRAS
    // ---------------------------------------------------------------------
    public List<Compra> read() {

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
            JOptionPane.showMessageDialog(null, "Falha ao obter dados: " + e);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return compras;
    }

    // ---------------------------------------------------------------------
    // CRIAR COMPRA (SEM ITENS AINDA)
    // ---------------------------------------------------------------------
    public void create(Compra c) {

        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(
                "INSERT INTO compra (Valor_Total, CPF, CNPJ_Drog) VALUES (?,?,?)"
            );

            stmt.setDouble(1, c.getValorTotal());
            stmt.setString(2, c.getCpfCompra());
            stmt.setString(3, c.getCnpjCompra());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Compra iniciada com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao cadastrar: " + e);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    // ---------------------------------------------------------------------
    // ATUALIZAR COMPRA
    // ---------------------------------------------------------------------
    public void updtae(Compra c) {

        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(
                "UPDATE compra SET Valor_Total = ?, CPF = ?, CNPJ_Drog = ? WHERE NotaFiscal_Entrada = ?"
            );

            stmt.setDouble(1, c.getValorTotal());
            stmt.setString(2, c.getCpfCompra());
            stmt.setString(3, c.getCnpjCompra());
            stmt.setInt(4, c.getNotaFiscalCompra());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Compra atualizada com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao atualizar: " + e);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    // ---------------------------------------------------------------------
    // REMOVER COMPRA
    // ---------------------------------------------------------------------
    public void delete(Compra c) {

        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(
                "DELETE FROM compra WHERE NotaFiscal_Entrada = ?"
            );

            stmt.setInt(1, c.getNotaFiscalCompra());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Compra removida com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao remover: " + e);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    // ---------------------------------------------------------------------
    // ATUALIZAR VALOR TOTAL DA NOTA
    // Chamado automaticamente pelo ItensDAO
    // ---------------------------------------------------------------------
    public void atualizarValorTotal(int nota, double total) {

        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(
                "UPDATE compra SET Valor_Total = ? WHERE NotaFiscal_Entrada = ?"
            );

            stmt.setDouble(1, total);
            stmt.setInt(2, nota);

            stmt.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar valor total: " + e);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }
}
