package fr.norsys;

import java.util.Objects;

public class Address {
    private Country country;

    public Address(Country country) {
        this.country = country;
    }

    public Country getCountry() {
        return this.country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(country, address.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country);
    }
}
