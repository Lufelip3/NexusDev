/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 *
 * @author luis.fmleite
 */
public class Funcionario {

    private String nome_Fun;
    private String cpf;
    private String telefone_Fun;
    private String Cep_Fun;
    private int numero_Fun;
    private String email_Fun;
    private String senha;
    private String senhaHash;
    private String cargo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public boolean verificarSenha(String senhaDigitada) {
        return getEncoder().matches(senhaDigitada, this.getSenha());
    }

    /**
     * @return the nome_Fun
     */
    public String getNome_Fun() {
        return nome_Fun;
    }

    /**
     * @param nome_Fun the nome_Fun to set
     */
    public void setNome_Fun(String nome_Fun) {
        this.nome_Fun = nome_Fun;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the telefone_Fun
     */
    public String getTelefone_Fun() {
        return telefone_Fun;
    }

    /**
     * @param telefone_Fun the telefone_Fun to set
     */
    public void setTelefone_Fun(String telefone_Fun) {
        this.telefone_Fun = telefone_Fun;
    }

    /**
     * @return the Cep_Fun
     */
    public String getCep_Fun() {
        return Cep_Fun;
    }

    /**
     * @param Cep_Fun the Cep_Fun to set
     */
    public void setCep_Fun(String Cep_Fun) {
        this.Cep_Fun = Cep_Fun;
    }

    /**
     * @return the numero_Fun
     */
    public int getNumero_Fun() {
        return numero_Fun;
    }

    /**
     * @param numero_Fun the numero_Fun to set
     */
    public void setNumero_Fun(int numero_Fun) {
        this.numero_Fun = numero_Fun;
    }

    /**
     * @return the email_Fun
     */
    public String getEmail_Fun() {
        return email_Fun;
    }

    /**
     * @param email_Fun the email_Fun to set
     */
    public void setEmail_Fun(String email_Fun) {
        this.email_Fun = email_Fun;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the senhaHash
     */
    public String getSenhaHash() {
        return senhaHash;
    }

    /**
     * @param senhaHash the senhaHash to set
     */
    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

    /**
     * @return the cargo
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * @param cargo the cargo to set
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    /**
     * @return the encoder
     */
    public BCryptPasswordEncoder getEncoder() {
        return encoder;
    }

    /**
     * @param encoder the encoder to set
     */
    public void setEncoder(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }
