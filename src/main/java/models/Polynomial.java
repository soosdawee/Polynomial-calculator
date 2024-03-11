package models;

import exceptions.DifferentPowerException;
import exceptions.IsZeroException;
import exceptions.SpaceDetectedException;
import exceptions.ZeroDivisorException;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class Polynomial {
    private TreeMap<Integer, Monomial> polynomials;

    public Polynomial () {
        this.polynomials = new TreeMap<Integer, Monomial>(Comparator.reverseOrder());
    }

    public Polynomial (TreeMap<Integer, Monomial> polynomials) {
        this.polynomials = polynomials;
    }

    public TreeMap<Integer, Monomial> getPolynomials() {
        return polynomials;
    }

    public void setPolynomials(TreeMap<Integer, Monomial> polynomials) {
        this.polynomials = polynomials;
    }

    public void createPolynomial(String input) throws NumberFormatException, SpaceDetectedException {
        String monomials[] = input.split("(?=[+-])");
        for (String m : monomials) {
            String[] parts = m.split("(?=[x^])");
            Float number = 1f;
            Integer power = 0;

            if (parts.length == 1) {
                number = Float.parseFloat(parts[0]);
            } else if (parts.length == 2) {
                number = Float.parseFloat(parts[0]);
                power = 1;
            } else {
                for (String p : parts) {
                    if (p.matches("[+-]?\\d+")) {
                        number = Float.parseFloat(p);
                    } else if (p.matches("\\^[+-]?\\d+")) {
                        power = Integer.parseInt(p.substring(1));
                    } else if (p.equals(" ")) {
                        throw new SpaceDetectedException("Alert");
                    }
                }
            }
            polynomials.put(power, new Monomial(number, power));
        }
    }

    public Polynomial add(Polynomial toAdd) throws IsZeroException, DifferentPowerException {
        TreeMap<Integer, Monomial> resultSet = new TreeMap<>(Comparator.reverseOrder());

        for (Map.Entry<Integer, Monomial> entry : polynomials.entrySet()) {
            if (toAdd.polynomials.containsKey(entry.getKey())) {
                resultSet.put(entry.getKey(), new Monomial(entry.getValue().add(toAdd.polynomials.get(entry.getKey())).getNumber(), entry.getKey()));
            } else {
                resultSet.put(entry.getKey(), new Monomial(entry.getValue().getNumber(), entry.getKey()));
            }
        }

        for (Map.Entry<Integer, Monomial> entry : toAdd.polynomials.entrySet()) {
            if (!polynomials.containsKey(entry.getKey())) {
                resultSet.put(entry.getKey(), new Monomial(entry.getValue().getNumber(), entry.getKey()));
            }
        }

        if (resultSet.size() == 1 && resultSet.firstEntry().getValue().getNumber() == 0) {
            throw new IsZeroException("Alert");
        }

        return new Polynomial(resultSet);
    }

    public Polynomial subtract(Polynomial toSubtract) throws IsZeroException {
        TreeMap<Integer, Monomial> resultSet = new TreeMap<>(Comparator.reverseOrder());

        for (Map.Entry<Integer, Monomial> entry : polynomials.entrySet()) {
            if (toSubtract.polynomials.containsKey(entry.getKey())) {
                resultSet.put(entry.getKey(), new Monomial(-toSubtract.polynomials.get(entry.getKey()).getNumber() + entry.getValue().getNumber(), entry.getKey()));
            } else {
                resultSet.put(entry.getKey(), new Monomial(entry.getValue().getNumber(), entry.getKey()));
            }
        }

        for (Map.Entry<Integer, Monomial> entry : toSubtract.polynomials.entrySet()) {
            if (!polynomials.containsKey(entry.getKey())) {
                resultSet.put(entry.getKey(), new Monomial(-entry.getValue().getNumber(), entry.getKey()));
            }
        }

        if (resultSet.size() == 1 && resultSet.firstEntry().getValue().getNumber() == 0) {
            throw new IsZeroException("Alert");
        }

        return new Polynomial(resultSet);
    }

    public Polynomial multiply(Polynomial multiplyWith) throws IsZeroException{
        TreeMap<Integer, Monomial> resultSet = new TreeMap<>(Comparator.reverseOrder());
        Monomial aux;

        for (Map.Entry<Integer, Monomial> firstEntry : polynomials.entrySet()) {
            for (Map.Entry<Integer, Monomial> secondEntry : multiplyWith.polynomials.entrySet()) {
                aux = firstEntry.getValue().multiply(secondEntry.getValue());

                if (resultSet.containsKey(aux.getPower())) {
                    resultSet.get(aux.getPower()).setNumber(resultSet.get(aux.getPower()).getNumber() + aux.getNumber());
                } else {
                    resultSet.put(aux.getPower(), aux);
                }
            }
        }

        if (resultSet.size() == 1 && resultSet.firstEntry().getValue().getNumber() == 0) {
            throw new IsZeroException("Alert");
        }

        return new Polynomial(resultSet);
    }

    public void derivative() throws IsZeroException {
        for (Map.Entry<Integer, Monomial> entry : polynomials.entrySet()) {
            entry.getValue().derivative();
        }

        if (polynomials.size() == 1 && polynomials.firstEntry().getValue().getNumber() == 0) {
            throw new IsZeroException("Alert");
        }
    }

    public void integral() throws IsZeroException {
        for (Map.Entry<Integer, Monomial> entry : polynomials.entrySet()) {
            entry.getValue().integral();
        }

        if (polynomials.size() == 1 && polynomials.firstEntry().getValue().getNumber() == 0) {
            throw new IsZeroException("Alert");
        }
    }

    public static Polynomial[] divide(Polynomial dividend, Polynomial divisor) throws IsZeroException, ZeroDivisorException {
        TreeMap<Integer, Monomial> result = new TreeMap<>(Comparator.reverseOrder());
        TreeMap<Integer, Monomial> remainder = new TreeMap<>(dividend.polynomials);

        if (divisor.getPolynomials().isEmpty() || divisor.getPolynomials().size() == 1 && divisor.getPolynomials().firstEntry().getValue().getNumber() == 0) {
            throw new ZeroDivisorException("Alert");
        }

        while (!remainder.isEmpty() && remainder.firstKey() >= divisor.polynomials.firstKey()) {
            int powerDifference = remainder.firstKey() - divisor.polynomials.firstKey();
            Float coefficientDifference = remainder.firstEntry().getValue().getNumber() / divisor.polynomials.firstEntry().getValue().getNumber();

            result.put(powerDifference, new Monomial(coefficientDifference, powerDifference));

            for (int power : divisor.polynomials.keySet()) {
                Float coefficient = divisor.polynomials.get(power).getNumber() * coefficientDifference;

                if (remainder.containsKey(power + powerDifference)) {
                    remainder.get(power + powerDifference).setNumber(remainder.get(power + powerDifference).getNumber() - coefficient);
                } else {
                    remainder.put(power + powerDifference, new Monomial(-coefficient, power + powerDifference));
                }
            }

            remainder.remove(remainder.firstKey());
        }

        Polynomial[] toReturn = new Polynomial[2];
        toReturn[0] = new Polynomial(result);
        toReturn[1] = new Polynomial(remainder);

        return toReturn;
    }

    public String toString() {
        StringBuilder toReturn = new StringBuilder();
        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        for (Map.Entry<Integer, Monomial> entry : polynomials.entrySet()) {
            if (entry.getValue().getNumber() > 0) {
                toReturn.append("+" + decimalFormat.format(entry.getValue().getNumber()) + "x^" + entry.getValue().getPower().toString());
            } else if (entry.getValue().getNumber() < 0) {
                toReturn.append( decimalFormat.format(entry.getValue().getNumber()) + "x^" + entry.getValue().getPower().toString());
            }
        }

        return toReturn.toString();
    }

    public static Boolean isNull(Polynomial polynomial) {
        if (polynomial == null) {
            return true;
        }

        return false;
    }
}
