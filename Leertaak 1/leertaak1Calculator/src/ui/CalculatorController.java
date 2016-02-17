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
    boolean running = true;

    public CalculatorController(){
        view = new CalculatorView(this);
        calculator = new Calculator();
        running = true;
        updateCalculatior();
    }

    public CalculatorView getView() {
        return view;
    }

    public boolean isRunning() {
        return running;
    }

    void printHelp() {
        JOptionPane.showMessageDialog(new Panel(), "\n" +
                " Choose one of the following commands:\n" +
                " +            (sum the last two operands)\n" +
                " -            (substract the last operand from the previous one)\n" +
                " *            (multiply the last two operands)\n" +
                " /            (divide the last two operands)\n" +
                " Bin          (switch to binary base)\n" +
                " Dec          (switch to base 10)\n" +
                " Oct          (switch to octal base)\n" +
                " Hex          (switch to hexadecimal base)\n" +
                " Fixed        (switch to fixed point format)\n" +
                " Float        (switch to floating point format)\n" +
                " Rational          (switch to rational format)\n" +
                " CE          (remove last operand)\n" +
                " Help         (print this command list)", "Question", JOptionPane.INFORMATION_MESSAGE);
    }

    private void updateCalculatior() {
        view.getLabelOutput().setText("["+calculator.getBase().getName()+","
                + calculator.getFormat().getName()+","
                + calculator.firstOperand() + ", "
                + calculator.secondOperand() + "]");
        view.getLabelAmountOfCalculations().setText("Amount of performed calculations: " +calculator.getAmountOfCalculations());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton test = (JButton) e.getSource();
        switch (test.getText()){
            case "+":
                calculator.add();
                updateCalculatior();
                break;
            case "-":
                calculator.subtract();
                updateCalculatior();
                break;
            case "*":
                calculator.multiply();
                updateCalculatior();
                break;
            case "/":
                calculator.divide();
                updateCalculatior();
                break;
            case "Bin":
                calculator.setBase(new BinaryBase());
                updateCalculatior();
                break;
            case "Oct":
                calculator.setBase(new OctalBase());
                updateCalculatior();
                break;
            case "Dec":
                calculator.setBase(new DecimalBase());
                updateCalculatior();
                break;
            case "Hex":
                calculator.setBase(new HexBase());
                updateCalculatior();
                break;
            case "Fixed":
                calculator.setFormat(new FixedPointFormat());
                updateCalculatior();
                break;
            case "Float":
                calculator.setFormat(new FloatingPointFormat());
                updateCalculatior();
                break;
            case "Rational":
                calculator.setFormat(new RationalFormat());
                updateCalculatior();
                break;
            case "CE":
                calculator.delete();
                updateCalculatior();
                break;
            case "Help":
                printHelp();
                break;
            default:
                try{
                    calculator.addOperand(test.getText());
                }catch(FormatException ex){
                    view.getLabelMessage().setText("Wrong operand: " + ex.getMessage());
                } catch (NumberBaseException ex) {
                    view.getLabelMessage().setText("Wrong operand for current base : " + ex.getMessage());
                }
                updateCalculatior();
                break;
        }
    }
}
