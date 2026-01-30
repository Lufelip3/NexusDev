/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import BD.Conexao;
import Objetos.CatalogoMedicamento;
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
public class CatalogoDAO {

    public List<CatalogoMedicamento> read() {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<CatalogoMedicamento> catalogoMedicamento = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM catalogo_medicamento");
            rs = stmt.executeQuery();

            while (rs.next()) {
                CatalogoMedicamento cm = new CatalogoMedicamento();
                cm.setNomeCatalogo(rs.getString("Nome_CatMed"));
                cm.setCodCatMed(rs.getInt("Cod_CatMed"));
                cm.setDescCatalogo(rs.getString("Desc_CatMed"));
                cm.setValorCatalogo(rs.getDouble("Valor_CatMed"));
                cm.setCnpjLabCat(rs.getString("CNPJ_Lab"));
                cm.setDatacompraItemCat(rs.getString("datacompraItemCat"));
                cm.setDataValItemCat(rs.getString("dataValItemCat"));
                cm.setQuantidade(rs.getInt("quantidade"));
                catalogoMedicamento.add(cm);
            }
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, "Falha ao obter dados: " + e);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return catalogoMedicamento;
    }

    public List<CatalogoMedicamento> readCNPJ(String cnpj) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<CatalogoMedicamento> catalogoMedicamento = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM catalogo_medicamento WHERE CNPJ_Lab = ? AND quantidade > 0");
            stmt.setString(1, cnpj);
            rs = stmt.executeQuery();

            while (rs.next()) {
                CatalogoMedicamento cm = new CatalogoMedicamento();
                cm.setNomeCatalogo(rs.getString("Nome_CatMed"));
                cm.setCodCatMed(rs.getInt("Cod_CatMed"));
                cm.setDescCatalogo(rs.getString("Desc_CatMed"));
                cm.setValorCatalogo(rs.getDouble("Valor_CatMed"));
                cm.setCnpjLabCat(rs.getString("CNPJ_Lab"));
                cm.setDatacompraItemCat(rs.getString("datacompraItemCat"));
                cm.setDataValItemCat(rs.getString("dataValItemCat"));
                cm.setQuantidade(rs.getInt("quantidade"));
                catalogoMedicamento.add(cm);
            }
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, "Falha ao obter dados: " + e);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return catalogoMedicamento;
    }

    public void create(CatalogoMedicamento cm) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO catalogo_medicamento (Nome_CatMed, Desc_CatMed, Valor_CatMed, CNPJ_Lab, datacompraItemCat, dataValItemCat, quantidade) VALUES (?,?,?,?,?,?,?)");
            stmt.setString(1, cm.getNomeCatalogo());
            stmt.setString(2, cm.getDescCatalogo());
            stmt.setDouble(3, cm.getValorCatalogo());
            stmt.setString(4, cm.getCnpjLabCat());
            stmt.setString(5, cm.getDatacompraItemCat());
            stmt.setString(6, cm.getDataValItemCat());
            stmt.setInt(7, cm.getQuantidade());

            stmt.execute();
            JOptionPane.showMessageDialog(null, "Catálogo cadastrado com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao cadastrar: " + e);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public void atualizarQuantidade(int codCatMed, int quantidadeComprada) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE catalogo_medicamento SET quantidade = quantidade - ? WHERE Cod_CatMed = ?");
            stmt.setInt(1, quantidadeComprada);
            stmt.setInt(2, codCatMed);

            stmt.execute();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao atualizar quantidade do catálogo: " + e);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public void update(CatalogoMedicamento cm) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE catalogo_medicamento SET Nome_CatMed = ?, Desc_CatMed = ?, Valor_CatMed = ?, CNPJ_Lab = ?, datacompraItemCat = ?, dataValItemCat = ?, quantidade = ? WHERE Cod_CatMed = ?");
            stmt.setString(1, cm.getNomeCatalogo());
            stmt.setString(2, cm.getDescCatalogo());
            stmt.setDouble(3, cm.getValorCatalogo());
            stmt.setString(4, cm.getCnpjLabCat());
            stmt.setString(5, cm.getDatacompraItemCat());
            stmt.setString(6, cm.getDataValItemCat());
            stmt.setInt(7, cm.getQuantidade());
            stmt.setInt(8, cm.getCodCatMed());

            stmt.execute();
            JOptionPane.showMessageDialog(null, "Catálogo atualizado com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao atualizar: " + e);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public void delete(CatalogoMedicamento cm) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM catalogo_medicamento WHERE Cod_CatMed = ?");
            stmt.setInt(1, cm.getCodCatMed());

            stmt.execute();
            JOptionPane.showMessageDialog(null, "Catálogo removido com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao remover: " + e);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }
}
