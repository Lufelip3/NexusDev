/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import BD.Conexao;
import DAO.MedicamentoDAO;
import Objetos.Funcionario;
import Objetos.Medicamento;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luis.fmleite
 */

    private List<Funcionario> dados = new ArrayList<>(); 
    private String[] colunas = {"cpf", "telefone_Fun", "Cep_Fun", "numero_Fun", "email_Fun"};
    private String cpf;
    private String telefone_Fun;
    private String Cep_Fun;
    private int numero_Fun;
    private String email_Fun; 
    
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
                return dados.get(linha).getCpf();
            case 1:
                return dados.get(linha).getTelefone_Fun();
            case 2:
                return dados.get(linha).getCep_Fun();
            case 3:
                return dados.get(linha).getNumero_Fun();
            case 4:
                return dados.get(linha).getEmail_Fun();
            
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
            dados.get(linha).setTelefone_Fun((String) valor);
            break;
        case 2:
            dados.get(linha).setCep_Fun((String) valor);
            break;
        case 3: 
            dados.get(linha).setNumero_Fun(Integer.parseInt((String) valor));
            break;
        case 4:
            dados.get(linha).setEmail_Fun((String) valor);
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
    // 1. Correção da sintaxe de instanciação
    FuncionarioTable fun = new FuncionarioTable(); 

    // 2. Correção da lógica/semântica: Deve iterar sobre Funcionario (não Medicamento)
    for (Funcionario f : fun.read()) {
        this.addLinha(f);
    }
    }

    public void recarregaTabela() {
        this.dados.clear();
        lerDados();
        this.fireTableDataChanged();
    }

    private void fireTableRowsUpdated(int linha, int linha0) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void fireTableDataChanged() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void fireTableRowsDeleted(int linha, int linha0) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


