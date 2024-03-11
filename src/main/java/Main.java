import controllers.CalculatorController;
import views.CalculatorView;

public class Main {
    public static void main(String[] args) {
        CalculatorView calculatorView = new CalculatorView();
        CalculatorController calculatorController = new CalculatorController(calculatorView);
    }
}
