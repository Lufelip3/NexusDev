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

    /**
     * @return the nomeCatalogo
     */
    public String getNomeCatalogo() {
        return nomeCatalogo;
    }

    /**
     * @param nomeCatalogo the nomeCatalogo to set
     */
    public void setNomeCatalogo(String nomeCatalogo) {
        this.nomeCatalogo = nomeCatalogo;
    }

    /**
     * @return the codigoCatalogo
     */
    public int getCodigoCatalogo() {
        return codigoCatalogo;
    }

    /**
     * @param codigoCatalogo the codigoCatalogo to set
     */
    public void setCodigoCatalogo(int codigoCatalogo) {
        this.codigoCatalogo = codigoCatalogo;
    }

    /**
     * @return the descCatalogo
     */
    public String getDescCatalogo() {
        return descCatalogo;
    }

    /**
     * @param descCatalogo the descCatalogo to set
     */
    public void setDescCatalogo(String descCatalogo) {
        this.descCatalogo = descCatalogo;
    }

    /**
     * @return the valorCatalogo
     */
    public Double getValorCatalogo() {
        return valorCatalogo;
    }

    /**
     * @param valorCatalogo the valorCatalogo to set
     */
    public void setValorCatalogo(Double valorCatalogo) {
        this.valorCatalogo = valorCatalogo;
    }
    private String nomeCatalogo;
    private int codigoCatalogo;
    private String descCatalogo;
    private Double valorCatalogo;
    
}
