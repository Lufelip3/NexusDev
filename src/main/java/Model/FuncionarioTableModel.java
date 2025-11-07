/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import BD.Conexao;
import DAO.FuncionarioDAO;
import Objetos.Funcionario;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author luis.fmleite
 */
public class FuncionarioTableModel extends AbstractTableModel {
   private List<Funcionario> dados = new ArrayList<>();
    private String[] colunas = {"CPF", "Nome", "Email", "Telefone", "CEP", "Numero"};

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
                return dados.get(linha).getCpf();
            case 1:
                return dados.get(linha).getNome_Fun();
            case 2:
                return dados.get(linha).getEmail_Fun();
            case 3:
                return dados.get(linha).getTelefone_Fun();
            case 4:
                return dados.get(linha).getCep_Fun();
            case 5:
                return dados.get(linha).getNumero_Fun();
        }
        return null;
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        switch (coluna) {
            case 0:
                dados.get(linha).setCpf((String) valor);
                break;
            case 1:
                dados.get(linha).setNome_Fun((String) valor);
                break;
            case 2:
                dados.get(linha).setEmail_Fun((String) valor);
                break;
            case 3:
                dados.get(linha).setTelefone_Fun((String) valor);
                break;
            case 4:
                dados.get(linha).setCep_Fun((String) valor);
                break;
            case 5:
                dados.get(linha).setNumero_Fun(Integer.parseInt((String) valor));
                break;
        }
        this.fireTableRowsUpdated(linha, linha);
    }

    public void addLinha(Funcionario f) {
        this.dados.add(f);
        this.fireTableDataChanged();
    }

    public void removeLinha(int linha) {
        this.dados.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }

    public Funcionario pegaDadosLinha(int linha) {
        return dados.get(linha);
    }

    private void lerDados() {
        FuncionarioDAO fdao = new FuncionarioDAO();

        for (Funcionario f : fdao.read()) {
            this.addLinha(f);

        }
        this.fireTableDataChanged();
    }

    public void recarregaTabela() {
        this.dados.clear();
        lerDados();
        this.fireTableDataChanged();
    }
}
