package com.base.luiss.proyectobase.modelo;

/**
 * Created by luiss on 26/07/2016.
 */
public class ciudades {
    private String Country;
    private String City;

    public ciudades(String country, String city) {
        Country = country;
        City = city;
    }

    public ciudades() {
        Country = "";
        City = "";
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }
}
