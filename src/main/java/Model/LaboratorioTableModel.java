/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import DAO.LaboratorioDAO;
import Objetos.Laboratorio;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author andrey.munhoz
 */
public class LaboratorioTableModel extends AbstractTableModel{
    private List<Laboratorio> dados = new ArrayList<>();
    private String[] colunas = {"Nome", "CNPJ", "Telefone","E-mail","Número" ,"CEP"};

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
                return dados.get(linha).getNomeLab();
            case 1:
                return dados.get(linha).getCnpjLab();
            case 2:
                return dados.get(linha).getTelefoneLab();
            case 3:
                return dados.get(linha).getEmailLab();
            case 4:
                return dados.get(linha).getNumeroLab();
            case 5:
                return dados.get(linha).getCepLab();
        }
        return null;
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        switch (coluna) {
            case 0:
                dados.get(linha).setNomeLab((String)valor);
                break;
            case 1:
                dados.get(linha).setCnpjLab((String)valor);
                break;
            case 2:
                dados.get(linha).setTelefoneLab((String)valor);
                break;
            case 3:
                dados.get(linha).setEmailLab((String)valor);
                break;
            case 4:
                dados.get(linha).setNumeroLab(Integer.parseInt((String)valor));
                break;
            case 5:
                dados.get(linha).setCepLab((String)valor);
                break;
        }
        this.fireTableRowsUpdated(linha, linha);
    }

    public void addLinha(Laboratorio l) {
        this.dados.add(l);
        this.fireTableDataChanged();
    }

    public void removeLinha(int linha) {
        this.dados.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }

    public Laboratorio pegaDadosLinha(int linha) {
        return dados.get(linha);
    }

    private void lerDados() {
        LaboratorioDAO ldao = new LaboratorioDAO();

        for (Laboratorio l : ldao.read()) {
            this.addLinha(l);

        }
        this.fireTableDataChanged();
    }

    public void recarregaTabela() {
        this.dados.clear();
        lerDados();
        this.fireTableDataChanged();
    }
}
