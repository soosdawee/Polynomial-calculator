package controllers;

import exceptions.DifferentPowerException;
import exceptions.IsZeroException;
import exceptions.SpaceDetectedException;
import exceptions.ZeroDivisorException;
import models.Polynomial;
import views.CalculatorView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorController {
    private CalculatorView view;

    public CalculatorController(CalculatorView view) {
        this.view = view;

        this.view.addAddListener(new AddListener());
        this.view.addSubtractListener(new SubtractListener());
        this.view.addMultiplyListener(new MultiplyListener());
        this.view.addDerivativeListener(new DerivativeListener());
        this.view.addIntegralListener(new IntegralListener());
        this.view.addDivisionListener(new DivisionListener());
    }

    class AddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.refreshBoxes();
            String input1 = view.getFirstPolynomial();
            String input2 = view.getSecondPolynomial();

            try {
                Polynomial firstPolynomial = new Polynomial();
                firstPolynomial.createPolynomial(input1);

                Polynomial secondPolynomial = new Polynomial();
                secondPolynomial.createPolynomial(input2);

                view.setResultPolynomial(firstPolynomial.add(secondPolynomial).toString());
            } catch (SpaceDetectedException ee) {
                view.showErrorMessage("Do not use spaces!");
            } catch (IsZeroException ex) {
                view.setResultPolynomial("0");
            } catch (NumberFormatException er) {
                view.showErrorMessage("Incorrect format! Please consult the documentation!");
            } catch (DifferentPowerException ed) {
                view.showErrorMessage("Something went wrong internally, try again");
            }
        }
    }

    class SubtractListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.refreshBoxes();
            String input1 = view.getFirstPolynomial();
            String input2 = view.getSecondPolynomial();

            try {
                Polynomial firstPolynomial = new Polynomial();
                firstPolynomial.createPolynomial(input1);

                Polynomial secondPolynomial = new Polynomial();
                secondPolynomial.createPolynomial(input2);

                view.setResultPolynomial(firstPolynomial.subtract(secondPolynomial).toString());
            } catch (IsZeroException ex) {
                view.setResultPolynomial("0");
            } catch (NumberFormatException er) {
                view.showErrorMessage("Incorrect format! Please consult the documentation!");
            } catch (SpaceDetectedException ee) {
                view.showErrorMessage("Do not use spaces!");
            }
        }
    }

    class MultiplyListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.refreshBoxes();
            String input1 = view.getFirstPolynomial();
            String input2 = view.getSecondPolynomial();

            try {
                Polynomial firstPolynomial = new Polynomial();
                firstPolynomial.createPolynomial(input1);

                Polynomial secondPolynomial = new Polynomial();
                secondPolynomial.createPolynomial(input2);

                view.setResultPolynomial(firstPolynomial.multiply(secondPolynomial).toString());
            } catch (IsZeroException ex) {
                view.setResultPolynomial("0");
            } catch (NumberFormatException er) {
                view.showErrorMessage("Incorrect format! Please consult the documentation!");
            } catch (SpaceDetectedException ee) {
                view.showErrorMessage("Do not use spaces!");
            }
        }
    }

    class DerivativeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.refreshBoxes();
            view.refreshSecondPolynomial();
            String input = view.getFirstPolynomial();

            try {
                Polynomial firstPolynomial = new Polynomial();
                firstPolynomial.createPolynomial(input);

                firstPolynomial.derivative();

                view.setResultPolynomial(firstPolynomial.toString());
            } catch (IsZeroException ex) {
                view.setResultPolynomial("0");
            } catch (NumberFormatException er) {
                view.showErrorMessage("Incorrect format! Please consult the documentation!");
            } catch (SpaceDetectedException ee) {
                view.showErrorMessage("Do not use spaces!");
            }
        }
    }

    class IntegralListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.refreshBoxes();
            view.refreshSecondPolynomial();
            String input = view.getFirstPolynomial();

            try {
                Polynomial firstPolynomial = new Polynomial();
                firstPolynomial.createPolynomial(input);

                firstPolynomial.integral();

                view.setResultPolynomial(firstPolynomial.toString());
            } catch (IsZeroException ex) {
                view.setResultPolynomial("0");
            } catch (NumberFormatException er) {
                view.showErrorMessage("Incorrect format! Please consult the documentation!");
            } catch (SpaceDetectedException ee) {
                view.showErrorMessage("Do not use spaces!");
            }
        }
    }

    class DivisionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.refreshBoxes();
            String input1 = view.getFirstPolynomial();
            String input2 = view.getSecondPolynomial();

            try {
                Polynomial firstPolynomial = new Polynomial();
                firstPolynomial.createPolynomial(input1);

                Polynomial secondPolynomial = new Polynomial();
                secondPolynomial.createPolynomial(input2);

                Polynomial[] toShow = new Polynomial[0];

                if (firstPolynomial.getPolynomials().firstKey() >= secondPolynomial.getPolynomials().firstKey()) {
                    toShow = Polynomial.divide(firstPolynomial, secondPolynomial);
                }
                else {
                    toShow = Polynomial.divide(secondPolynomial, firstPolynomial);
                }

                view.setResultPolynomial(toShow[0].toString());
                view.setRemainderPolynomial(toShow[1].toString());
            } catch (IsZeroException ex) {
                view.showErrorMessage(ex.getMessage());
            } catch (NumberFormatException er) {
                view.showErrorMessage("Incorrect format! Please check the documentation!");
            } catch (SpaceDetectedException ee) {
                view.showErrorMessage("Do not use spaces!");
            } catch (ZeroDivisorException ez) {
                view.showErrorMessage("You cannot divide by zero!");
            }
        }
    }
}
