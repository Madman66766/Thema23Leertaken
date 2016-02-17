package ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Christopher on 16/02/2016.
 */
public class CalculatorView extends JPanel {

    JLabel labelOutput;
    JLabel labelMessage;
    JLabel labelAmountOfCalculations;
    CalculatorController controller;

    public CalculatorView(CalculatorController controller) {

        this.controller = controller;
        setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        makeLabels(gridBagConstraints);
        makeButtons(gridBagConstraints, controller);
    }



    public void makeLabels(GridBagConstraints gridBagConstraints){
        labelOutput = new JLabel();

        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 3;

        add(labelOutput, gridBagConstraints);
        gridBagConstraints.gridx = 2;

        labelMessage = new JLabel();
        add(labelMessage, gridBagConstraints);

        labelAmountOfCalculations = new JLabel();
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridx = 0;
        add(labelAmountOfCalculations, gridBagConstraints);

        gridBagConstraints.gridwidth = 1;
    }

    public void makeButtons(GridBagConstraints gridBagConstraints, CalculatorController controller) {

        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridx = 0;
        JButton buttonFormatFixed = new JButton("Fixed");
        buttonFormatFixed.addActionListener(controller);
        add(buttonFormatFixed, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        JButton buttonFormatFloat = new JButton("Floating");
        buttonFormatFloat.addActionListener(controller);
        add(buttonFormatFloat, gridBagConstraints);
        gridBagConstraints.gridx = 2;
        JButton buttonFormatRational = new JButton("Rational");
        buttonFormatRational.addActionListener(controller);
        add(buttonFormatRational, gridBagConstraints);
        gridBagConstraints.gridx = 3;
        JButton buttonHelp = new JButton("Help");
        buttonHelp.addActionListener(controller);
        add(buttonHelp, gridBagConstraints);

        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridx = 0;
        JButton buttonDec = new JButton("Dec");
        buttonDec.addActionListener(controller);
        add(buttonDec, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        JButton buttonBin = new JButton("Bin");
        buttonBin.addActionListener(controller);
        add(buttonBin, gridBagConstraints);
        gridBagConstraints.gridx = 2;
        JButton buttonOct = new JButton("Oct");
        buttonOct.addActionListener(controller);
        add(buttonOct, gridBagConstraints);
        gridBagConstraints.gridx = 3;
        JButton buttonHex = new JButton("Hex");
        buttonHex.addActionListener(controller);
        add(buttonHex, gridBagConstraints);

        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridx = 0;
        JButton buttonMultiply = new JButton("*");
        buttonMultiply.addActionListener(controller);
        add(buttonMultiply, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        JButton buttonDivide = new JButton("/");
        buttonDivide.addActionListener(controller);
        add(buttonDivide, gridBagConstraints);
        gridBagConstraints.gridx = 2;
        JButton buttonAdd = new JButton("+");
        buttonAdd.addActionListener(controller);
        add(buttonAdd, gridBagConstraints);
        gridBagConstraints.gridx = 3;
        JButton buttonSubtract = new JButton("-");
        buttonSubtract.addActionListener(controller);
        add(buttonSubtract, gridBagConstraints);
        gridBagConstraints.gridx = 4;
        JButton buttonClear = new JButton("CE");
        buttonClear.addActionListener(controller);
        add(buttonClear, gridBagConstraints);

        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridx = 0;
        JButton button0 = new JButton("0");
        button0.addActionListener(controller);
        add(button0, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        JButton button1 = new JButton("1");
        button1.addActionListener(controller);
        add(button1, gridBagConstraints);
        gridBagConstraints.gridx = 2;
        JButton button2 = new JButton("2");
        button2.addActionListener(controller);
        add(button2, gridBagConstraints);
        gridBagConstraints.gridx = 3;
        JButton button3 = new JButton("3");
        button3.addActionListener(controller);
        add(button3, gridBagConstraints);
        gridBagConstraints.gridx = 4;
        JButton button4 = new JButton("4");
        button4.addActionListener(controller);
        add(button4, gridBagConstraints);

        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridx = 0;
        JButton button5 = new JButton("5");
        button5.addActionListener(controller);
        add(button5, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        JButton button6 = new JButton("6");
        button6.addActionListener(controller);
        add(button6, gridBagConstraints);
        gridBagConstraints.gridx = 2;
        JButton button7 = new JButton("7");
        button7.addActionListener(controller);
        add(button7, gridBagConstraints);
        gridBagConstraints.gridx = 3;
        JButton button8 = new JButton("8");
        button8.addActionListener(controller);
        add(button8, gridBagConstraints);
        gridBagConstraints.gridx = 4;
        JButton button9 = new JButton("9");
        button9.addActionListener(controller);
        add(button9, gridBagConstraints);

        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridx = 0;
        JButton buttonA = new JButton("A");
        buttonA.addActionListener(controller);
        add(buttonA, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        JButton buttonB = new JButton("B");
        buttonB.addActionListener(controller);
        add(buttonB ,gridBagConstraints);
        gridBagConstraints.gridx = 2;
        JButton buttonC = new JButton("C");
        buttonC.addActionListener(controller);
        add(buttonC, gridBagConstraints);
        gridBagConstraints.gridx = 3;
        JButton buttonD = new JButton("D");
        buttonD.addActionListener(controller);
        add(buttonD, gridBagConstraints);
        gridBagConstraints.gridx = 4;
        JButton buttonE = new JButton("E");
        buttonE.addActionListener(controller);
        add(buttonE, gridBagConstraints);
        gridBagConstraints.gridx = 5;
        JButton buttonF = new JButton("F");
        buttonF.addActionListener(controller);

        add(buttonF, gridBagConstraints);
    }

    public JLabel getLabelOutput(){
        return labelOutput;
    }

    public JLabel getLabelMessage() {
        return labelMessage;
    }

    public JLabel getLabelAmountOfCalculations(){
        return labelAmountOfCalculations;
    }
}
