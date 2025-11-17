/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import DAO.ItnesDAO;
import Objetos.CatalogoMedicamento;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author andrey.munhoz
 */
public class CatalogoTableModel extends AbstractTableModel{
    private List<CatalogoMedicamento> dados = new ArrayList<>();
    private String[] colunas = {"Nome", "Código", "Descrição","Valor"};

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
                return dados.get(linha).getNomeCatalogo();
            case 1:
                return dados.get(linha).getCodigoCatalogo();
            case 2:
                return dados.get(linha).getDescCatalogo();
            case 3:
                return dados.get(linha).getValorCatalogo();
        }
        return null;
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        switch (coluna) {
            case 0:
                dados.get(linha).setNomeCatalogo((String)valor);
                break;
            case 1:
                dados.get(linha).setCodigoCatalogo(Integer.parseInt((String)valor));
                break;
            case 2:
                dados.get(linha).setDescCatalogo((String)valor);
                break;
            case 3:
                dados.get(linha).setValorCatalogo(Double.valueOf((String)valor));
                break;
        }
        this.fireTableRowsUpdated(linha, linha);
    }

    public void addLinha(CatalogoMedicamento cd) {
        this.dados.add(cd);
        this.fireTableDataChanged();
    }

    public void removeLinha(int linha) {
        this.dados.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }

    public CatalogoMedicamento pegaDadosLinha(int linha) {
        return dados.get(linha);
    }

    private void lerDados() {
        CatalogoDAO cdao = new CatalogDAO();

        for (CatalogoMedicamento cd : idao.read()) {
            this.addLinha(cd);

        }
        this.fireTableDataChanged();
    }

    public void recarregaTabela() {
        this.dados.clear();
        lerDados();
        this.fireTableDataChanged();
    }
}
