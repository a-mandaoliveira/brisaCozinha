/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author anton
 */
public class Mesa{
    private int idMesa;
    private int numeroMesa;
    private int qntdLugar;
    private Double valorReserva;
    private String status;

    public Mesa(int idMesa, int numeroMesa, int qntdLugar, String status, Double valorReserva) {
        this.idMesa = idMesa;
        this.numeroMesa = numeroMesa;
        this.qntdLugar = qntdLugar;
        this.status = status;
        this.valorReserva = valorReserva;
    }

    public Mesa(int numeroMesa, int qntdLugar, Double valorReserva, String status) {
        this.numeroMesa = numeroMesa;
        this.qntdLugar = qntdLugar;
        this.valorReserva = valorReserva;
        this.status = status;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public int getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public int getQntdLugar() {
        return qntdLugar;
    }

    public void setQntdLugar(int qntdLugar) {
        this.qntdLugar = qntdLugar;
    }

    public Double getValorReserva() {
        return valorReserva;
    }

    public void setValorReserva(Double valorReserva) {
        this.valorReserva = valorReserva;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}