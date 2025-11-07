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

    /**
     * @return the dataCompra
     */
    public String getDataCompra() {
        return dataCompra;
    }

    /**
     * @param dataCompra the dataCompra to set
     */
    public void setDataCompra(String dataCompra) {
        this.dataCompra = dataCompra;
    }

    /**
     * @return the valorCompra
     */
    public Double getValorCompra() {
        return valorCompra;
    }

    /**
     * @param valorCompra the valorCompra to set
     */
    public void setValorCompra(Double valorCompra) {
        this.valorCompra = valorCompra;
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

    private String dataCompra;
    private Double valorCompra;
    private int notaFiscalCompra;
}
