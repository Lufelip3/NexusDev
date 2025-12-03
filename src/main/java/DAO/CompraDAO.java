package DAO;

import BD.Conexao;
import Objetos.Compra;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CompraDAO {

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
    
public int createAndReturnNota(Compra c) {
    String sql = "INSERT INTO compra (Valor_Total, CPF, CNPJ_Drog) VALUES (?, ?, ?)";

    try (Connection con = Conexao.getConnection();
         PreparedStatement stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

        stmt.setDouble(1, c.getValorTotal());
        stmt.setString(2, c.getCpfCompra());
        
        if (c.getCnpjCompra() == null) {
            stmt.setNull(3, Types.VARCHAR);
        } else {
            stmt.setString(3, c.getCnpjCompra());
        }

        int affected = stmt.executeUpdate();
        if (affected == 0) {
            JOptionPane.showMessageDialog(null, "Inserção falhou: nenhuma linha afetada.");
            return -1;
        }

        try (ResultSet rs = stmt.getGeneratedKeys()) {
            if (rs.next()) {
                return rs.getInt(1); // chave gerada
            } else {
                JOptionPane.showMessageDialog(null, "Nenhuma chave gerada pela inserção.");
                return -1;
            }
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao criar compra: " + e);
        return -1;
    }
}


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
            JOptionPane.showMessageDialog(null, "Erro ao atualizar total: " + e);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public void atualizarCnpj(int nota, String cnpj) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(
                "UPDATE compra SET CNPJ_Drog = ? WHERE NotaFiscal_Entrada = ?"
            );

            stmt.setString(1, cnpj);
            stmt.setInt(2, nota);

            stmt.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao definir CNPJ: " + e);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

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
}