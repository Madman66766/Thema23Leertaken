import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Christopher on 10/02/2016.
 */
public class StatistiekenView extends JPanel implements ActionListener {

    private DobbelsteenModel d;

    private JLabel labelText0 = new JLabel();
    private JLabel labelText1 = new JLabel();
    private JLabel labelText2 = new JLabel();
    private JLabel labelText3 = new JLabel();
    private JLabel labelText4 = new JLabel();
    private JLabel labelText5 = new JLabel();
    private JLabel labelText6 = new JLabel();

    private int amountOfOne, amountOfTwo, amountOfThree, amountOfFour, amountOfFive, amountOfSix = 0;

    public StatistiekenView(){
        setLayout(new GridLayout(0, 1));
        add(labelText0);
        add(labelText1);
        add(labelText2);
        add(labelText3);
        add(labelText4);
        add(labelText5);
        add(labelText6);
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(80,80);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        d = (DobbelsteenModel) e.getSource();
        increase(d.getWaarde());
        int amountOfThrows = amountOfOne + amountOfTwo + amountOfThree + amountOfFour + amountOfFive + amountOfSix ;
        if (amountOfThrows == 1)
            labelText0.setText(amountOfThrows + " worp");
        else
            labelText0.setText(amountOfThrows + " worpen");
        labelText1.setText("1: " + amountOfOne);
        labelText2.setText("2: " + amountOfTwo);
        labelText3.setText("3: " + amountOfThree);
        labelText4.setText("4: " + amountOfFour);
        labelText5.setText("5: " + amountOfFive);
        labelText6.setText("6: " + amountOfSix);
    }

    private void increase(int i) {
        switch (i) {
            case 1:
                amountOfOne++;
                break;
            case 2:
                amountOfTwo++;
                break;
            case 3:
                amountOfThree++;
                break;
            case 4:
                amountOfFour++;
                break;
            case 5:
                amountOfFive++;
                break;
            case 6:
                amountOfSix++;
                break;
        }
    }
}
