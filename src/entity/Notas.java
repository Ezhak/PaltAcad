package entity;

import java.math.BigDecimal;

public class Notas {

    private Curso curso;
    private Alumno alumno;
    private BigDecimal parcial1;
    private BigDecimal parcial2;
    private BigDecimal recu1;
    private BigDecimal recu2;
    private boolean estado;

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public BigDecimal getParcial1() {
        return parcial1;
    }

    public void setParcial1(BigDecimal parcial1) {
        this.parcial1 = parcial1;
    }

    public BigDecimal getParcial2() {
        return parcial2;
    }

    public void setParcial2(BigDecimal parcial2) {
        this.parcial2 = parcial2;
    }

    public BigDecimal getRecu1() {
        return recu1;
    }

    public void setRecu1(BigDecimal recu1) {
        this.recu1 = recu1;
    }

    public BigDecimal getRecu2() {
        return recu2;
    }

    public void setRecu2(BigDecimal recu2) {
        this.recu2 = recu2;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
