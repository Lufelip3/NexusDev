/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

/**
 *
 * @author luis.fmleite
 */
public class Medicamento {

    private String nomeMed;
    private int codigoMed;
    private String descricaoMed;
    private String dataValidadeMed;
    private int quantEstoqueMed;
    private Double valorMed;

    /**
     * @return the nomeMed
     */
    public String getNomeMed() {
        return nomeMed;
    }

    /**
     * @param nomeMed the nomeMed to set
     */
    public void setNomeMed(String nomeMed) {
        this.nomeMed = nomeMed;
    }

    /**
     * @return the codigoMed
     */
    public int getCodigoMed() {
        return codigoMed;
    }

    /**
     * @param codigoMed the codigoMed to set
     */
    public void setCodigoMed(int codigoMed) {
        this.codigoMed = codigoMed;
    }

    /**
     * @return the descricaoMed
     */
    public String getDescricaoMed() {
        return descricaoMed;
    }

    /**
     * @param descricaoMed the descricaoMed to set
     */
    public void setDescricaoMed(String descricaoMed) {
        this.descricaoMed = descricaoMed;
    }

    /**
     * @return the dataValidadeMed
     */
    public String getDataValidadeMed() {
        return dataValidadeMed;
    }

    /**
     * @param dataValidadeMed the dataValidadeMed to set
     */
    public void setDataValidadeMed(String dataValidadeMed) {
        this.dataValidadeMed = dataValidadeMed;
    }

    /**
     * @return the quantEstoqueMed
     */
    public int getQuantEstoqueMed() {
        return quantEstoqueMed;
    }

    /**
     * @param quantEstoqueMed the quantEstoqueMed to set
     */
    public void setQuantEstoqueMed(int quantEstoqueMed) {
        this.quantEstoqueMed = quantEstoqueMed;
    }

    /**
     * @return the valorMed
     */
    public Double getValorMed() {
        return valorMed;
    }

    /**
     * @param valorMed the valorMed to set
     */
    public void setValorMed(Double valorMed) {
        this.valorMed = valorMed;
    }
}
