/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import DAO.MedicamentoDAO;
import Objetos.Medicamento;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class MedicamentoTableModel extends AbstractTableModel {
    
    private List<Medicamento> dados = new ArrayList<>();
}
