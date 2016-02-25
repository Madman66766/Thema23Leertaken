package classifier;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Christopher on 24/02/2016.
 */
public class View extends JFrame {

    private JLabel labelTitle;
    private JLabel labelQuestion;
    private int counter = -1;
    private String answer[];
    private String features[];
    private String item;

    public View(String title, String features[]){
        super(title);
        this.features = features;
        answer = new String[9];
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(buildPanel());
        pack();
        setVisible(true);

    }

    private JPanel buildPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        labelTitle = new JLabel("Enter item name: ");
        labelTitle.setFont(new Font("", Font.PLAIN, 30));
        labelQuestion = new JLabel();
        labelQuestion.setFont(new Font("", Font.PLAIN, 30));
        JTextField textField = new JTextField();
        textField.addActionListener(e -> {
            counter++;
            switch (counter) {
                case 0:
                    item = textField.getText();
                    labelTitle.setText("Item: " + item);
                    labelQuestion.setText("Does " + item + " have " + features[counter].toLowerCase()+ "?");
                    textField.setText("");
                    break;
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    labelTitle.setText("Item: " + item);
                    labelQuestion.setText("Does " + item + " have " + features[counter].toLowerCase()+ "?");
                    answer[counter] = textField.getText();
                    textField.setText("");
                    break;
                case 8:
                    answer[counter] = textField.getText();
                    break;
            }
        });
        panel.add(labelTitle);
        panel.add(labelQuestion);
        panel.add(textField);

        return panel;

    }



}
