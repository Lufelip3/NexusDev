/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
    
import BD.Conexao;
import Obejtos.Produto;
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
    Connection con = Conexao.getConnection();
    PreparedStatement stmt = null;
    Resultset rs = null;
    List<Medicamento> medicamentos 
}
