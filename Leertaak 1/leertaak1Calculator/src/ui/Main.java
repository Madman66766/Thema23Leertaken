package ui;

import javax.swing.*;

/**
 * Created by Christopher on 16/02/2016.
 */
public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        CalculatorController controller = new CalculatorController();

        frame.setContentPane(controller.getView());
        frame.setJMenuBar(controller.getView().makeMenuBar(controller));

        frame.pack();
        frame.setVisible(true);

        while(controller.isRunning());
    }
}
