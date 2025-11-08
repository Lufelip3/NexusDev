/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import DAO.DrogariaDAO;
import Objetos.Drogaria;
import Objetos.Medicamento;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author luis.fmleite
 */
public class DrogariaTableModel extends AbstractTableModel {

    private List<Drogaria> dados = new ArrayList<>();
    private String[] colunas = {"CNPJ", "Nome", "Telefone", "Email", "CEP", "Numero"};
    
    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0:
                return dados.get(linha).getCnpjDrogaria();
            case 1:
                return dados.get(linha).getNomeDrogaria();
            case 2:
                return dados.get(linha).getTelefoneDrogaria();
            case 3:
                return dados.get(linha).getEmailDrogaria();
            case 4:
                return dados.get(linha).getCepDrogaria();
            case 5:
                return dados.get(linha).getNumeroDrogaria();
        }
        return null;
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        switch (coluna) {
            case 0:
                dados.get(linha).setCnpjDrogaria((String) valor);
                break;
            case 1:
                dados.get(linha).setNomeDrogaria((String) valor);
                break;
            case 2:
                dados.get(linha).setTelefoneDrogaria((String) valor);
                break;
            case 3:
                dados.get(linha).setEmailDrogaria((String) valor);
                break;
            case 4:
                dados.get(linha).setCepDrogaria((String) valor);
                break;
            case 5:
                dados.get(linha).setNumeroDrogaria(Integer.parseInt((String) valor));
                break;
        }
        this.fireTableRowsUpdated(linha, linha);
    }

    public void addLinha(Drogaria d) {
        this.dados.add(d);
        this.fireTableDataChanged();
    }

    public void removeLinha(int linha) {
        this.dados.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }

    public Drogaria pegaDadosLinha(int linha) {
        return dados.get(linha);
    }

    private void lerDados() {
        DrogariaDAO ddao = new DrogariaDAO();

        for (Drogaria d : ddao.read()) {
            this.addLinha(d);

        }
        this.fireTableDataChanged();
    }

    public void recarregaTabela() {
        this.dados.clear();
        lerDados();
        this.fireTableDataChanged();
    }
    
}
