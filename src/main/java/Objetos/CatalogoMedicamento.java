/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

/**
 *
 * @author luis.fmleite
 */
public class CatalogoMedicamento {

    
    private int codCatMed;
    private String nomeCatalogo;
    private String descCatalogo;
    private Double valorCatalogo;
    private String cnpjLab;

    public int getCodCatMed() {
        return codCatMed;
    }

    public void setCodCatMed(int codCatMed) {
        this.codCatMed = codCatMed;
    }

    public String getNomeCatalogo() {
        return nomeCatalogo;
    }

    public void setNomeCatalogo(String nomeCatalogo) {
        this.nomeCatalogo = nomeCatalogo;
    }

    public String getDescCatalogo() {
        return descCatalogo;
    }

    public void setDescCatalogo(String descCatalogo) {
        this.descCatalogo = descCatalogo;
    }

    public Double getValorCatalogo() {
        return valorCatalogo;
    }

    public void setValorCatalogo(Double valorCatalogo) {
        this.valorCatalogo = valorCatalogo;
    }

    /**
     * @return the cnpjLab
     */
    public String getCnpjLab() {
        return cnpjLab;
    }

    /**
     * @param cnpjLab the cnpjLab to set
     */
    public void setCnpjLab(String cnpjLab) {
        this.cnpjLab = cnpjLab;
    }
    
}
