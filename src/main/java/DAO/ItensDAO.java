package DAO;

import BD.Conexao;
import Objetos.Itens;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ItensDAO {

    public List<Itens> read() {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Itens> itens = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM item");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Itens i = new Itens();
                i.setCodigoItem(rs.getInt("Cod_Item"));
                i.setDataValItem(rs.getString("DataVal_Item"));
                i.setQuantidadeItem(rs.getInt("Qtd_Item"));
                i.setValorItem(rs.getDouble("Valor_Item"));
                i.setDataVendaItem(rs.getString("Data_Venda"));
                i.setNotaFiscalCompraItem(rs.getInt("NotaFiscal_Entrada"));
                i.setCodCatMedItem(rs.getInt("Cod_CatMed"));
                i.setCodMedItem(rs.getInt("Cod_Med"));
                itens.add(i);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar: " + e);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return itens;
    }

    public void create(Itens i) {

        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(
                "INSERT INTO item (DataVal_Item, Qtd_Item, Valor_Item, Data_Venda, NotaFiscal_Entrada, Cod_CatMed, Cod_Med)"
              + " VALUES (?, ?, ?, ?, ?, ?, ?)"
            );

            stmt.setString(1, i.getDataValItem());
            stmt.setInt(2, i.getQuantidadeItem());
            stmt.setDouble(3, i.getValorItem());
            stmt.setString(4, i.getDataVendaItem());
            stmt.setInt(5, i.getNotaFiscalCompraItem());
            stmt.setInt(6, i.getCodCatMedItem());
            stmt.setInt(7, i.getCodMedItem());

            stmt.execute();

            // Recalcular TOTAL após inserir item
            double total = totalDaNota(i.getNotaFiscalCompraItem());
            new CompraDAO().atualizarValorTotal(i.getNotaFiscalCompraItem(), total);

            JOptionPane.showMessageDialog(null, "Item cadastrado com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + e);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public double totalDaNota(int notaFiscal) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement(
                "SELECT SUM(Valor_Item * Qtd_Item) AS Total "
              + "FROM item WHERE NotaFiscal_Entrada = ?"
            );

            stmt.setInt(1, notaFiscal);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getDouble("Total");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao somar itens: " + e);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return 0;
    }
}
