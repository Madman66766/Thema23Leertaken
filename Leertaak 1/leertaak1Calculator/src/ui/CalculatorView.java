package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Christopher on 16/02/2016.
 */
public class CalculatorView extends JPanel {

    JLabel labelAmountOfCalculations;
    JLabel labelBase;
    JLabel labelFormat;
    JLabel labelOutput;
    JLabel labelError;

    public CalculatorView(CalculatorController controller) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(makePanelTop());
        add(makePanelMiddle(controller));
        add(makePanelBottom());
    }

    public JMenuBar makeMenuBar(CalculatorController controller){
        JMenuItem menuItemHelp = new JMenuItem("Print help");
        menuItemHelp.addActionListener(controller);

        JMenu menuHelp = new JMenu("Help");
        menuHelp.add(menuItemHelp);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menuHelp);

        return menuBar;
    }

    private JPanel makePanelTop(){
        JPanel panel = new JPanel();

        labelOutput = new JLabel();
        labelOutput.setFont(new Font("Arial", Font.PLAIN, 30));
        panel.add(labelOutput);

        return panel;
    }

    private JPanel makePanelMiddle(ActionListener controller){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 5));
        String stringButtons[] = {
                "Fixed" , "Float" , "Rational", "CE" , ".",
                "Bin" , "Oct" , "Dec" , "Hex", "=",
                "+" , "-", "*", "/", "0",
                "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "A",
                "B", "C", "D", "E", "F"};
        for(String name: stringButtons){
            panel.add(makeButton(name, controller));
        }

        return panel;
    }

    private JButton makeButton(String name, ActionListener actionListener){
        JButton button = new JButton(name);
        button.addActionListener(actionListener);
        return button;
    }

    private JPanel makePanelBottom(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 0));
        labelAmountOfCalculations = new JLabel();
        panel.add(labelAmountOfCalculations);
        labelBase = new JLabel();
        panel.add(labelBase);
        labelFormat = new JLabel();
        panel.add(labelFormat);
        labelError = new JLabel();
        panel.add(labelError);

        return panel;
    }

    public JLabel getLabelAmountOfCalculations(){
        return labelAmountOfCalculations;
    }

    public JLabel getLabelBase() {
        return labelBase;
    }

    public JLabel getLabelFormat() {
        return labelFormat;
    }

    public JLabel getLabelOutput(){
        return labelOutput;
    }

    public JLabel getLabelError() {
        return labelError;
    }
}
