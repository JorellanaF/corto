/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Jorge Orellana <00103717@uca.edu.sv>
 */
public class Pelicula {
    private int id;
    private String nombre;
    private String director;
    private String pais;
    private String clasificacion;
    private String annio;
    private boolean existencia;
    
    public Pelicula(){
        
    }   

    public Pelicula(int id, String nombre, String director, String pais, String clasificacion, String annio, boolean existencia) {
        this.id = id;
        this.nombre = nombre;
        this.director = director;
        this.pais = pais;
        this.clasificacion = clasificacion;
        this.annio = annio;
        this.existencia = existencia;
    }

    public Pelicula(String nombre, String director, String pais, String clasificacion, String annio, boolean existencia) {
        this.nombre = nombre;
        this.director = director;
        this.pais = pais;
        this.clasificacion = clasificacion;
        this.annio = annio;
        this.existencia = existencia;
    }

    public Pelicula(String director, String pais, String clasificacion, String annio, boolean existencia) {
        this.director = director;
        this.pais = pais;
        this.clasificacion = clasificacion;
        this.annio = annio;
        this.existencia = existencia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getAnnio() {
        return annio;
    }

    public void setAnnio(String annio) {
        this.annio = annio;
    }

    public boolean isExistencia() {
        return existencia;
    }

    public void setExistencia(boolean existencia) {
        this.existencia = existencia;
    }

}
