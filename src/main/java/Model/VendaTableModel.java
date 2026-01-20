/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import DAO.VendaDAO;
import Objetos.Venda;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author andrey.munhoz
 */
public class VendaTableModel extends AbstractTableModel {

    private List<Venda> dados = new ArrayList<>();
    private String[] colunas = {"Nota Fiscal", "Data de Venda", "Valor", "CNPJ", "CPF"};

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
                return dados.get(linha).getNotaFiscalVenda();
            case 1:
                return dados.get(linha).getDataVenda();
            case 2:
                return dados.get(linha).getValorVenda();
            case 3:
                return dados.get(linha).getCnpjVenda();
            case 4:
                return dados.get(linha).getCpfVenda();
        }
        return null;
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        switch (coluna) {
            case 0:
                dados.get(linha).setNotaFiscalVenda(Integer.parseInt((String) valor));
                break;
            case 1:
                dados.get(linha).setDataVenda((String) valor);
                break;
            case 2:
                dados.get(linha).setValorVenda(Double.valueOf((String) valor));
                break;
            case 3:
                dados.get(linha).setCnpjVenda((String) valor);
                break;
            case 4:
                dados.get(linha).setCpfVenda((String) valor);
                break;
        }
        this.fireTableRowsUpdated(linha, linha);
    }

    public void addLinha(Venda v) {
        this.dados.add(v);
        this.fireTableDataChanged();
    }

    public void removeLinha(int linha) {
        this.dados.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }

    public Venda pegaDadosLinha(int linha) {
        return dados.get(linha);
    }

    private void lerDados() {
        VendaDAO vdao = new VendaDAO();

        for (Venda v : vdao.read()) {
            this.addLinha(v);

        }
        this.fireTableDataChanged();
    }

    public void recarregaTabela() {
        this.dados.clear();
        lerDados();
        this.fireTableDataChanged();
    }

    public void setVendas(List<Venda> vendas) {
        this.dados = vendas;
        fireTableDataChanged();
    }

}
