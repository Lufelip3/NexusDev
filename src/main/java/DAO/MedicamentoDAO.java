/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
    
import BD.Conexao;
import Objetos.Medicamento;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.PreparableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class MedicamentoDAO {
    public List<Medicamento> read() {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Medicamento> medicamentos = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM tbl_Produto");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Medicamento m = new Medicamento();
                m.setCodigoMed(rs.getInt("Cod_Med"));
                m.setNomeMed(rs.getString("Nome_Med"));
                m.setDescricaoMed(rs.getString("Desc_Med"));
                m.setValorMed(rs.getDouble("Valor_Med"));
                m.setQuantEstoqueMed(rs.getInt("Qtd_Med"));
                m.setDataValidadeMed(rs.getString("DataVal_Med"));
                medicamentos.add(m);
            }
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, "Falha ao obter dados: " + e);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return medicamentos;
    }

    public void create(Medicamento m) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO medicamento (Cod_Med, Nome_Med, Desc_Med, Valor_Med, Qtd_Med, DataVal_Med) VALUES (?,?,?,?,?,?)");
            stmt.setInt(1, m.getCodigoMed());
            stmt.setString(2, m.getNomeMed());
            stmt.setString(3, m.getDescricaoMed());
            stmt.setDouble(4, m.getValorMed());
            stmt.setInt(5, m.getQuantEstoqueMed());
            stmt.setString(6, m.getDataValidadeMed());

            stmt.execute();
            JOptionPane.showMessageDialog(null, "Medicamento cadastrado com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao cadastrar: " + e);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public void updtae(Medicamento m) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE medicamento set Nome_Med = ?, Desc_Med = ?, Valor_Med = ?, Qtd_Med = ?, DataVal_Med = ? where Cod_Med = ?");
            stmt.setString(1, m.getNomeMed());
            stmt.setString(2, m.getDescricaoMed());
            stmt.setDouble(3, m.getValorMed());
            stmt.setInt(4, m.getQuantEstoqueMed());
            stmt.setString(5, m.getDataValidadeMed());
            stmt.setInt(6, m.getCodigoMed());

            stmt.execute();
            JOptionPane.showMessageDialog(null, "Medicamento atualizado com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao atualizar: " + e);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public void delete(Medicamento m) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM medicamento where Cod_Med = ?");
            stmt.setInt(1, m.getCodigoMed());

            stmt.execute();
            JOptionPane.showMessageDialog(null, "Medicamento removido com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao remover: " + e);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }
}
