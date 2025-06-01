/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author anton
 */
public class Reserva{
    private int id;
    private String dtReserva;
    private String horaReserva;
    private int numeroPessoas;
    private int mesa;
    private int cliente;

    public Reserva(String dtReserva, String horaReserva, int numeroPessoas, int mesa, int cliente) {
        this.dtReserva = dtReserva;
        this.horaReserva = horaReserva;
        this.numeroPessoas = numeroPessoas;
        this.mesa = mesa;
        this.cliente = cliente;
    }

    public Reserva(int id, String dtReserva, String horaReserva, int numeroPessoas, int mesa, int cliente) {
        this.id = id;
        this.dtReserva = dtReserva;
        this.horaReserva = horaReserva;
        this.numeroPessoas = numeroPessoas;
        this.mesa = mesa;
        this.cliente = cliente;
    }

    public String getDtReserva() {
        return dtReserva;
    }

    public void setDtReserva(String dtReserva) {
        this.dtReserva = dtReserva;
    }

    public String getHoraReserva() {
        return horaReserva;
    }

    public void setHoraReserva(String horaReserva) {
        this.horaReserva = horaReserva;
    }

    public int getNumeroPessoas() {
        return numeroPessoas;
    }

    public void setNumeroPessoas(int numeroPessoas) {
        this.numeroPessoas = numeroPessoas;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }
    
}
