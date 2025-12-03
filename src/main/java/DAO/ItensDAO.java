package DAO;

import BD.Conexao;
import Objetos.Itens;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;

public class ItensDAO {

    // -----------------------------------------------------
    //  LÊ TODOS OS ITENS
    // -----------------------------------------------------
    public List<Itens> read() {
        List<Itens> itens = new ArrayList<>();

        String sql = "SELECT * FROM item";

        try (Connection con = Conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

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
            JOptionPane.showMessageDialog(null, "Falha ao obter dados: " + e);
        }

        return itens;
    }

    // -----------------------------------------------------
    //  CALCULA O TOTAL DA NOTA (SOMA QUANTIDADE * VALOR)
    // -----------------------------------------------------
    public double totalDaNota(int notaFiscal) {
        String sql = """
                SELECT SUM(Valor_Item * Qtd_Item) AS total
                FROM item
                WHERE NotaFiscal_Entrada = ?
                """;

        try (Connection con = Conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, notaFiscal);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
                return rs.getDouble("total");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao calcular total da nota: " + e);
        }

        return 0;
    }

    // -----------------------------------------------------
    //  INSERE UM ITEM + ATUALIZA VALOR TOTAL DA NOTA
    // -----------------------------------------------------
    public void create(Itens i) {
        String sql = """
                INSERT INTO item
                (DataVal_Item, Qtd_Item, Valor_Item, Data_Venda, NotaFiscal_Entrada, Cod_CatMed, Cod_Med)
                VALUES (?,?,?,?,?,?,?)
                """;

        try (Connection con = Conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, i.getDataValItem());
            stmt.setInt(2, i.getQuantidadeItem());
            stmt.setDouble(3, i.getValorItem());
            stmt.setString(4, i.getDataVendaItem());
            stmt.setInt(5, i.getNotaFiscalCompraItem());
            stmt.setInt(6, i.getCodCatMedItem());
            stmt.setInt(7, i.getCodMedItem());

            stmt.executeUpdate();

            atualizarTotalDaNota(i);

            JOptionPane.showMessageDialog(null, "Item cadastrado com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao cadastrar: " + e);
        }
    }

    // -----------------------------------------------------
    //  ATUALIZA UM ITEM + REATUALIZA VALOR TOTAL DA NOTA
    // -----------------------------------------------------
    public void update(Itens i) {
        String sql = """
                UPDATE item
                SET DataVal_Item = ?, Qtd_Item = ?, Valor_Item = ?, Data_Venda = ?,
                    NotaFiscal_Entrada = ?, Cod_CatMed = ?, Cod_Med = ?
                WHERE Cod_Item = ?
                """;

        try (Connection con = Conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, i.getDataValItem());
            stmt.setInt(2, i.getQuantidadeItem());
            stmt.setDouble(3, i.getValorItem());
            stmt.setString(4, i.getDataVendaItem());
            stmt.setInt(5, i.getNotaFiscalCompraItem());
            stmt.setInt(6, i.getCodCatMedItem());
            stmt.setInt(7, i.getCodMedItem());
            stmt.setInt(8, i.getCodigoItem());

            stmt.executeUpdate();

            atualizarTotalDaNota(i);

            JOptionPane.showMessageDialog(null, "Item atualizado com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao atualizar: " + e);
        }
    }

    // -----------------------------------------------------
    //  DELETA UM ITEM + REATUALIZA TOTAL DA NOTA
    // -----------------------------------------------------
    public void delete(Itens i) {
        String sql = "DELETE FROM item WHERE Cod_Item = ?";

        int numeroNota = i.getNotaFiscalCompraItem();

        try (Connection con = Conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, i.getCodigoItem());
            stmt.executeUpdate();

            atualizarTotalDaNota(numeroNota);

            JOptionPane.showMessageDialog(null, "Item removido com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao remover: " + e);
        }
    }

    // -----------------------------------------------------
    //  FUNÇÃO CENTRAL: REATUALIZA VALOR TOTAL DA NOTA
    // -----------------------------------------------------
    private void atualizarTotalDaNota(Itens i) {
        atualizarTotalDaNota(i.getNotaFiscalCompraItem());
    }

    private void atualizarTotalDaNota(int nota) {
        double total = totalDaNota(nota);

        CompraDAO compraDAO = new CompraDAO();
        compraDAO.atualizarValorTotal(nota, total);
    }
}
