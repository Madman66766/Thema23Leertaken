package ui;

import multiformat.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Christopher on 16/02/2016.
 */
public class CalculatorController implements ActionListener{

    Calculator calculator;
    CalculatorView view;
    final String BEGIN_STATE = "0.0";
    String operand_1, operand_2, operation, arithmeticExpression;
    boolean isCalculated, isFirstOperand, running = true;

    public CalculatorController(){
        view = new CalculatorView(this);
        calculator = new Calculator();
        reset();
        updateCalculator();
    }

    public CalculatorView getView() {
        return view;
    }

    public boolean isRunning() {
        return running;
    }

    public void printHelp() {
        JOptionPane.showMessageDialog(new Panel(), "\n" +
                " Choose one of the following commands:\n" +
                " +            (sum the last two operands)\n" +
                " -            (subtract the last operand from the previous one)\n" +
                " *            (multiply the last two operands)\n" +
                " /            (divide the last two operands)\n" +
                " Bin          (switch to binary base)\n" +
                " Dec          (switch to base 10)\n" +
                " Oct          (switch to octal base)\n" +
                " Hex          (switch to hexadecimal base)\n" +
                " Fixed        (switch to fixed point format)\n" +
                " Float        (switch to floating point format)\n" +
                " Rational     (switch to rational format)\n" +
                " CE           (clear everything)\n" +
                " Help         (print this command list)", "Question", JOptionPane.INFORMATION_MESSAGE);
    }

    private void reset(){
        operand_1 = "";
        operand_2 = "";
        operation = null;
        arithmeticExpression = BEGIN_STATE;
        isFirstOperand = true;
        isCalculated = false;
        view.getLabelError().setText("Currently no errors");
        view.getLabelError().setForeground(Color.darkGray);
    }

    private void updateCalculator() {
        view.getLabelAmountOfCalculations().setText("Amount of calculations performed : " +calculator.getAmountOfCalculations());
        view.getLabelBase().setText("Current base: " + calculator.getBase().getName());
        view.getLabelFormat().setText("Current format: " + calculator.getFormat().getName());

        if (isCalculated) {
            view.getLabelOutput().setText(calculator.secondOperand());
            isCalculated = false;
        }
        else
            view.getLabelOutput().setText(arithmeticExpression);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AbstractButton source = (AbstractButton) e.getSource();

        switch (source.getText()){
            case "Bin":
            case "Oct":
            case "Dec":
            case "Hex":
                switch (source.getText()){
                    case "Bin":
                        calculator.setBase(new BinaryBase());
                        break;
                    case "Oct":
                        calculator.setBase(new OctalBase());
                        break;
                    case "Dec":
                        calculator.setBase(new DecimalBase());
                        break;
                    case "Hex":
                        calculator.setBase(new HexBase());
                        break;
                }
                isCalculated = true;
                updateCalculator();
                break;
            case "Fixed":
            case "Float":
            case "Rational":
                switch (source.getText()) {
                    case "Fixed":
                        calculator.setFormat(new FixedPointFormat());
                        break;
                    case "Float":
                        calculator.setFormat(new FloatingPointFormat());
                        break;
                    case "Rational":
                        calculator.setFormat(new RationalFormat());
                        break;
                }
                isCalculated = true;
               updateCalculator();
                break;
            case "CE":
                calculator.delete();
                reset();
                updateCalculator();
                break;
            case "Print help":
                printHelp();
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                try{
                    calculator.addOperand(operand_1);
                    operation = source.getText();
                    arithmeticExpression = calculator.secondOperand() + " " + source.getText() + " ";
                    isFirstOperand = false;
                    updateCalculator();
                }catch(FormatException ex){
                    view.getLabelError().setText("Wrong operand: " + ex.getMessage());
                    view.getLabelError().setForeground(Color.red);
                } catch (NumberBaseException ex) {
                    view.getLabelError().setText("Error: " + ex.getMessage());
                    view.getLabelError().setForeground(Color.red);
                }
                break;
            case "=":
                try{
                    calculator.addOperand(operand_2);
                    switch (operation) {
                        case "+":
                            calculator.add();
                            break;
                        case "-":
                            calculator.subtract();
                            break;
                        case "*":
                            calculator.multiply();
                            break;
                        case "/":
                            calculator.divide();
                            break;
                    }
                    isCalculated = true;
                    updateCalculator();
                    reset();
                }catch(FormatException ex){
                    view.getLabelError().setText("Wrong operand: " + ex.getMessage());
                } catch (NumberBaseException ex) {
                    view.getLabelError().setText("Error: " + ex.getMessage());
                }
                break;
            default:
                if (isFirstOperand) {
                    operand_1 = operand_1.concat(source.getText());
                    System.out.println(" Operand1 = [" + operand_1 + "]");
                } else {
                    operand_2 = operand_2.concat(source.getText());
                    System.out.println("Operand 2 = [" + operand_2 + "]");
                }
                if (arithmeticExpression.equals(BEGIN_STATE)) {
                    arithmeticExpression = source.getText();
                } else {
                    arithmeticExpression = arithmeticExpression.concat(source.getText());
                }
                updateCalculator();
                break;
        }
    }
}
