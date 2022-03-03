package fr.norsys;

import java.util.Objects;

public class Country {
    private String name;
    private int nbrPopulation;

    public Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNbrPopulation() {
        return nbrPopulation;
    }

    public void setNbrPopulation(int nbrPopulation) {
        this.nbrPopulation = nbrPopulation;
    }

    //this is used on distinct
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return nbrPopulation == country.nbrPopulation && Objects.equals(name, country.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, nbrPopulation);
    }
}
