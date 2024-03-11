import exceptions.DifferentPowerException;
import exceptions.IsZeroException;
import exceptions.SpaceDetectedException;
import exceptions.ZeroDivisorException;
import models.Monomial;
import models.Polynomial;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFunctionalities {
    TreeMap<Integer, Monomial> firstMap;
    TreeMap<Integer, Monomial> secondMap;

    @BeforeEach
    void setUp() {
        firstMap = new TreeMap<>(Comparator.reverseOrder());
        secondMap = new TreeMap<>(Comparator.reverseOrder());
        firstMap.put(3, new Monomial(6f,3));
        firstMap.put(2, new Monomial(-5f,2));
        firstMap.put(0, new Monomial(11f,0));
        secondMap.put(1, new Monomial(2f, 1));
        secondMap.put(0, new Monomial(1f, 0));
    }

    @Test
    void testAdd() throws IsZeroException, DifferentPowerException {
        Polynomial firstPolynomial = new Polynomial(firstMap);
        Polynomial secondPolynomial = new Polynomial(secondMap);

        TreeMap<Integer, Monomial> thirdMap = new TreeMap<>();
        thirdMap.put(3, new Monomial(6f,3));
        thirdMap.put(2, new Monomial(-5f,2));
        thirdMap.put(0, new Monomial(12f,0));
        thirdMap.put(1, new Monomial(2f, 1));

        for (Map.Entry<Integer, Monomial> entry : thirdMap.entrySet()) {
            assertEquals(entry.getValue().getNumber(),
                    firstPolynomial.add(secondPolynomial).getPolynomials().get(entry.getKey()).getNumber());
        }
    }

    @Test
    void testSubtract() throws IsZeroException {
        Polynomial firstPolynomial = new Polynomial(firstMap);
        Polynomial secondPolynomial = new Polynomial(secondMap);

        TreeMap<Integer, Monomial> thirdMap = new TreeMap<>();
        thirdMap.put(3, new Monomial(6f, 3));
        thirdMap.put(2, new Monomial(-5f, 2));
        thirdMap.put(1, new Monomial(-2f, 1));
        thirdMap.put(0, new Monomial(10f, 0));

        for (Map.Entry<Integer, Monomial> entry : thirdMap.entrySet()) {
            assertEquals(entry.getValue().getNumber(),
                    firstPolynomial.subtract(secondPolynomial).getPolynomials().get(entry.getKey()).getNumber());
        }
    }

    @Test
    void testMultiply() throws IsZeroException {
        Polynomial firstPolynomial = new Polynomial(firstMap);
        Polynomial secondPolynomial = new Polynomial(secondMap);

        TreeMap<Integer, Monomial> thirdMap = new TreeMap<>();
        thirdMap.put(4, new Monomial(12f, 4));
        thirdMap.put(3, new Monomial(-4f, 3));
        thirdMap.put(2, new Monomial(-5f, 2));
        thirdMap.put(1, new Monomial(22f, 1));
        thirdMap.put(0, new Monomial(11f, 0));

        for (Map.Entry<Integer, Monomial> entry : thirdMap.entrySet()) {
            assertEquals(entry.getValue().getNumber(),
                    firstPolynomial.multiply(secondPolynomial).getPolynomials().get(entry.getKey()).getNumber());
        }
    }

    @Test
    void testDerivative() throws IsZeroException {
        Polynomial firstPolynomial = new Polynomial(firstMap);

        TreeMap<Integer, Monomial> thirdMap = new TreeMap<>();

        thirdMap.put(2, new Monomial(18f, 2));
        thirdMap.put(1, new Monomial(-10f, 1));

        firstPolynomial.derivative();

        for (Map.Entry<Integer, Monomial> entry : thirdMap.entrySet()) {
            assertEquals(entry.getValue().getNumber(),
                    firstPolynomial.getPolynomials().get(entry.getKey() + 1).getNumber());
        }
    }

    @Test
    void testIntegral() throws IsZeroException {
        Polynomial firstPolynomial = new Polynomial(firstMap);

        TreeMap<Integer, Monomial> thirdMap = new TreeMap<>();

        thirdMap.put(4, new Monomial(1.5f, 4));
        thirdMap.put(3, new Monomial(-1.6666666f, 3));
        thirdMap.put(1, new Monomial(11f, 1));

        firstPolynomial.integral();

        for (Map.Entry<Integer, Monomial> entry : thirdMap.entrySet()) {
            assertEquals(entry.getValue().getNumber(),
                    firstPolynomial.getPolynomials().get(entry.getKey() - 1).getNumber());
        }
    }

    @Test
    void testDivision() throws IsZeroException, ZeroDivisorException {
        Polynomial firstPolynomial = new Polynomial(firstMap);
        Polynomial secondPolynomial = new Polynomial(secondMap);

        for (Map.Entry<Integer, Monomial> entry : firstMap.entrySet()) {
            System.out.println(entry.getValue().getNumber());
        }
        for (Map.Entry<Integer, Monomial> entry : secondMap.entrySet()) {
            System.out.println(entry.getValue().getNumber());
        }

        TreeMap<Integer, Monomial> thirdMap = new TreeMap<>(Comparator.reverseOrder());
        thirdMap.put(2, new Monomial(3f,2));
        thirdMap.put(1, new Monomial(-4f,1));
        thirdMap.put(0, new Monomial(2f,0));

        TreeMap<Integer, Monomial> fourthMap = new TreeMap<>(Comparator.reverseOrder());
        fourthMap.put(0, new Monomial(9f, 0));

        Polynomial[] toTest = Polynomial.divide(firstPolynomial, secondPolynomial);

        for (Map.Entry<Integer, Monomial> entry : thirdMap.entrySet()) {
            assertEquals(entry.getValue().getNumber(), toTest[0].getPolynomials().get(entry.getKey()).getNumber());
        }

        for (Map.Entry<Integer, Monomial> entry : fourthMap.entrySet()) {
            assertEquals(entry.getValue().getNumber(), toTest[1].getPolynomials().get(entry.getKey()).getNumber());
        }
    }
}
