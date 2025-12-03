/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import DAO.CompraDAO;
import Objetos.Compra;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Andrey
 */
public class CompraTableModel extends AbstractTableModel {
   private List<Compra> dados = new ArrayList<>();
    private String[] colunas = {"Nota Fiscal", "Valor", "CNPJ", "CPF"};

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


    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0:
                return dados.get(linha).getNotaFiscalCompra();
            case 1:
                return dados.get(linha).getValorTotal();
            case 2:
                return dados.get(linha).getCnpjCompra();
            case 3:
                return dados.get(linha).getCpfCompra();
        }
        return null;
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        switch (coluna) {
            case 0:
                dados.get(linha).setNotaFiscalCompra((int) valor);
                break;
            case 1:
                dados.get(linha).setValorTotal((Double) valor);
                break;
            case 2:
                dados.get(linha).setCnpjCompra((String) valor);
                break;
            case 3:
                dados.get(linha).setCpfCompra((String) valor);
                break;
            
        }
        this.fireTableRowsUpdated(linha, linha);
    }

    public void addLinha(Compra c) {
        this.dados.add(c);
        this.fireTableDataChanged();
    }

    public void removeLinha(int linha) {
        this.dados.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }

    public Compra pegaDadosLinha(int linha) {
        return dados.get(linha);
    }

    private void lerDados() {
        CompraDAO cdao = new CompraDAO();

        for (Compra c : cdao.read()) {
            this.addLinha(c);

        }
        this.fireTableDataChanged();
    }

    public void recarregaTabela() {
        this.dados.clear();
        lerDados();
        this.fireTableDataChanged();
    }
}
