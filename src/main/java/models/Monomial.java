package models;

import exceptions.DifferentPowerException;

public class Monomial implements Comparable<Monomial>{
    private Float number;
    private Integer power;

    public Monomial(Float number, Integer power) {
        this.number = number;
        this.power = power;
    }

    public Monomial() { }

    public Float getNumber() { 
        return number;
    }

    public void setNumber(Float number) {
        this.number = number;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    @Override
    public int compareTo(Monomial o) {
        return this.power - o.power;
    }

    public Monomial add(Monomial toAdd) throws DifferentPowerException {
        if (power != toAdd.power) {
            throw new DifferentPowerException("The monomes to be added don't have the same power!");
        }

        Float auxNumber = number + toAdd.getNumber();

        return new Monomial(auxNumber, power);
    }

    public Monomial multiply(Monomial multiplyWith) {
        Float auxNumber = number * multiplyWith.getNumber();
        Integer auxPower = power + multiplyWith.getPower();

        return new Monomial(auxNumber, auxPower);
    }

    public void derivative() {
        this.number = this.number * this.power;
        this.power -= 1;
    }

    public void integral() {
        this.number = this.number / (this.power + 1);
        this.power += 1;
    }
}
