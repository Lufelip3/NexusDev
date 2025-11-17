/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

/**
 *
 * @author luis.fmleite
 */
public class Compra {
    
    private Double valorTotal;
    private int notaFiscalCompra;
    private String cpfCompra;
    private String cnpjCompra;

    /**
     * @return the valorTotal
     */
    public Double getValorTotal() {
        return valorTotal;
    }

    /**
     * @param valorTotal the valorTotal to set
     */
    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    /**
     * @return the notaFiscalCompra
     */
    public int getNotaFiscalCompra() {
        return notaFiscalCompra;
    }

    /**
     * @param notaFiscalCompra the notaFiscalCompra to set
     */
    public void setNotaFiscalCompra(int notaFiscalCompra) {
        this.notaFiscalCompra = notaFiscalCompra;
    }

    /**
     * @return the cpfCompra
     */
    public String getCpfCompra() {
        return cpfCompra;
    }

    /**
     * @param cpfCompra the cpfCompra to set
     */
    public void setCpfCompra(String cpfCompra) {
        this.cpfCompra = cpfCompra;
    }

    /**
     * @return the cnpjCompra
     */
    public String getCnpjCompra() {
        return cnpjCompra;
    }

    /**
     * @param cnpjCompra the cnpjCompra to set
     */
    public void setCnpjCompra(String cnpjCompra) {
        this.cnpjCompra = cnpjCompra;
    }

}
