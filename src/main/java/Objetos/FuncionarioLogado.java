/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

public class FuncionarioLogado {
    private static Funcionario funcionarioAtual;
    
    public static void setFuncionario(Funcionario f) {
        funcionarioAtual = f;
    }
    
    public static Funcionario getFuncionario() {
        return funcionarioAtual;
    }
    
    public static String getFuncao() {
        return funcionarioAtual != null ? funcionarioAtual.getFuncao() : null;
    }
    
    public static String getNome() {
        return funcionarioAtual != null ? funcionarioAtual.getNome_Fun() : null;
    }
    
    public static String getCpf() {
        return funcionarioAtual != null ? funcionarioAtual.getCpf() : null;
    }
    
    public static boolean isGerente() {
        return funcionarioAtual != null && "Gerente".equalsIgnoreCase(funcionarioAtual.getFuncao());
    }
    
    public static void logout() {
        funcionarioAtual = null;
    }
}