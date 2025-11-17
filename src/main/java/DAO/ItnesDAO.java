/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import BD.Conexao;
import Objetos.Itens;
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
public class ItnesDAO {
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
                i.setCodCatMedItem(rs.getInt("Cod_Item"));
                i.setDataValItem(rs.getString("DataVal_Item"));
                i.setQuantidadeItem(rs.getInt("Qtd_Item"));
                i.setValorItem(rs.getDouble("Valor_Item"));
                i.setDataValItem(rs.getString("Data_Venda"));
                i.setNotaFiscalCompraItem(rs.getInt("NotaFiscal_Entrada"));
                i.setCodCatMedItem(rs.getInt("Cod_CatMed"));
                i.setCodMedItem(rs.getInt("Cod_Med"));
                itens.add(i);
            }
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, "Falha ao obter dados: " + e);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return itens;
    }

    public void create(Itens i) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO item (DataVal_Item, Qtd_Item, Valor_Item, Data_Venda, NotaFiscal_Entrada, Cod_CatMed,Cod_Med) VALUES (?,?,?,?,?,?,?)");
            stmt.setString(1, i.getDataValItem());
            stmt.setInt(2, i.getQuantidadeItem());
            stmt.setDouble(3, i.getValorItem());
            stmt.setString(4, i.getDataVendaItem());
            stmt.setInt(5, i.getNotaFiscalCompraItem());
            stmt.setInt(6, i.getCodCatMedItem());
            stmt.setInt(7, i.getCodMedItem());

            stmt.execute();
            JOptionPane.showMessageDialog(null, "Item cadastrado com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao cadastrar: " + e);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public void updtae(Itens i) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE item set DataVal_Item = ?, Qtd_Item = ?, Valor_Item = ?, Data_Venda = ?, NotaFiscal_Entrada = ? Cod_CatMed = ? Cod_Med = ? where Cod_Item = ?");
            stmt.setString(1, i.getDataValItem());
            stmt.setInt(2, i.getQuantidadeItem());
            stmt.setDouble(3, i.getValorItem());
            stmt.setString(4, i.getDataVendaItem());
            stmt.setInt(5, i.getNotaFiscalCompraItem());
            stmt.setInt(6, i.getCodCatMedItem());
            stmt.setInt(7, i.getCodMedItem());
            stmt.setInt(8, i.getCodigoItem());

            stmt.execute();
            JOptionPane.showMessageDialog(null, "Item atualizado com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao atualizar: " + e);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public void delete(Itens i) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM item where Cod_Item = ?");
            stmt.setInt(1, i.getCodigoItem());

            stmt.execute();
            JOptionPane.showMessageDialog(null, "Item removido com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao remover: " + e);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }
}
