package classifier;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Christopher on 24/02/2016.
 */
public class View extends JFrame {

    private JLabel labelTitle;
    private JLabel labelQuestion;
    private int counter = 0;
    private String answer[];
    private String featuresNames[];
    private String stringItem;
    private Feature[] features;
    private DecisionTree decisionTree;
    Item item = null;

    public View(String title, String featuresNames[],Feature[] features,DecisionTree decisionTree){
        super(title);
        this.featuresNames = featuresNames;
        this.features = features;
        this.decisionTree = decisionTree;
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
            switch (counter) {
                case 0:
                    stringItem = textField.getText();
                    labelTitle.setText("Item: " + stringItem);
                    labelQuestion.setText("Does " + stringItem + " have " + featuresNames[counter].toLowerCase()+ "?");
                    textField.setText("");
                    item = new Item(stringItem,features);
                    break;
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    labelTitle.setText("Item: " + stringItem);
                    labelQuestion.setText("Does " + stringItem + " have " + featuresNames[counter].toLowerCase()+ "?");
                    answer[counter-1] = textField.getText();
                    textField.setText("");
                    break;
                case 8:
                    answer[counter-1] = textField.getText();
                    textField.setText("");
                    break;
                case 9:
                    System.out.print(getCategory(item));
                    break;
                default:

            }
            counter++;
        });
        panel.add(labelTitle);
        panel.add(labelQuestion);
        panel.add(textField);

        return panel;

    }
    private String getCategory(Item item){
        for (int i = 0;i < featuresNames.length;i++){
            item.setFeatureValue(featuresNames[i],answer[i]);
        }
        String category = decisionTree.assignCategory(item);
        return category;
    }


}
