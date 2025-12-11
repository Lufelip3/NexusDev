/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import DAO.ItensDAO;
import Objetos.Itens;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author andrey.munhoz
 */
public class ItensTableModel extends AbstractTableModel{
    private List<Itens> dados = new ArrayList<>();
    private String[] colunas = {"Código", "Quantidade", "Valor", "Data de Validade", "Nota fiscal", "Código do medicamento"};
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
                return dados.get(linha).getCodigoItem();
            case 1:
                return dados.get(linha).getQuantidadeItem();
            case 2:
                return dados.get(linha).getValorItem();
            case 3:
                return dados.get(linha).getDataValItem();
            case 4:
                return dados.get(linha).getNotaFiscalCompraItem();
            case 5:
                return dados.get(linha).getCodMedItem();
        }
        return null;
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        switch (coluna) {
            case 0:
                dados.get(linha).setCodigoItem(Integer.parseInt((String)valor));
                break;
            case 1:
                dados.get(linha).setQuantidadeItem(Integer.parseInt((String)valor));
                break;
            case 2:
                dados.get(linha).setValorItem(Double.valueOf((String)valor));
                break;
            case 3:
                dados.get(linha).setDataValItem((String)valor);
                break;
            case 4:
                dados.get(linha).setNotaFiscalCompraItem(Integer.parseInt((String)valor));
                break;
            case 5:
                dados.get(linha).setCodMedItem(Integer.parseInt((String)valor));
                break;
        }
        this.fireTableRowsUpdated(linha, linha);
    }

    public void addLinha(Itens i) {
    addItem(i);
}


    public void removeLinha(int linha) {
        this.dados.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }

    public Itens pegaDadosLinha(int linha) {
        return dados.get(linha);
    }

    private void lerDados() {
        ItensDAO idao = new ItensDAO();

        for (Itens i : idao.read()) {
            this.addLinha(i);

        }
        this.fireTableDataChanged();
    }

    public void recarregaTabela() {
        this.dados.clear();
        lerDados();
        this.fireTableDataChanged();
    }
    public void addItem(Itens item) {
    dados.add(item); 
    int lastIndex = dados.size() - 1;
    fireTableRowsInserted(lastIndex, lastIndex);
}

}
