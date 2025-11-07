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
    private String[] colunas = {"Código", "Nome", "Descrição", "Quantidade", "Valor", "Data de validade"};

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
                return dados.get(linha).getCodigoMed();
            case 1:
                return dados.get(linha).getNomeMed();
            case 2:
                return dados.get(linha).getDescricaoMed();
            case 3:
                return dados.get(linha).getQuantEstoqueMed();
            case 4:
                return dados.get(linha).getValorMed();
            case 5:
                return dados.get(linha).getDataValidadeMed();
        }
        return null;
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        switch (coluna) {
            case 0:
                dados.get(linha).setCodigoMed(Integer.parseInt((String) valor));
                break;
            case 1:
                dados.get(linha).setNomeMed((String) valor);
                break;
            case 2:
                dados.get(linha).setDescricaoMed((String) valor);
                break;
            case 3:
                dados.get(linha).setQuantEstoqueMed(Integer.parseInt((String) valor));
                break;
            case 4:
                dados.get(linha).setValorMed(Double.valueOf((String) valor));
                break;
            case 5:
                dados.get(linha).setDataValidadeMed((String) valor);
                break;
        }
        this.fireTableRowsUpdated(linha, linha);
    }

    public void addLinha(Medicamento m) {
        this.dados.add(m);
        this.fireTableDataChanged();
    }

    public void removeLinha(int linha) {
        this.dados.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }

    public Medicamento pegaDadosLinha(int linha) {
        return dados.get(linha);
    }

    private void lerDados() {
        MedicamentoDAO mdao = new MedicamentoDAO();

        for (Medicamento m : mdao.read()) {
            this.addLinha(m);

        }
        this.fireTableDataChanged();
    }

    public void recarregaTabela() {
        this.dados.clear();
        lerDados();
        this.fireTableDataChanged();
    }

}
