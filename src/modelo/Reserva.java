package modelo;

import java.math.BigDecimal;
import java.sql.Date;

public class Reserva {
    private Integer id;
    private Date fechaEntrada;
    private Date fechaSalida;
    private BigDecimal valor;
    private String formaDePago;

    public Reserva(Date fechaEntrada, Date fechaSalida, BigDecimal valor, String formaDePago) {
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.valor = valor;
        this.formaDePago = formaDePago;
    }
    public Reserva(Integer id, Date fechaEntrada, Date fechaSalida, BigDecimal valor, String formaDePago) {
        this.id = id;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.valor = valor;
        this.formaDePago = formaDePago;
    }

    public int calcular(){
        return 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getFormaDePago() {
        return formaDePago;
    }

    public void setFormaDePago(String formaDePago) {
        this.formaDePago = formaDePago;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", fechaEntrada=" + fechaEntrada +
                ", fechaSalida=" + fechaSalida +
                ", valor=" + valor +
                ", formaDePago='" + formaDePago + '\'' +
                '}';
    }
}
