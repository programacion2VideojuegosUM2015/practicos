package gui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by IntelliJ IDEA.
 * User: guest
 * Date: 09-jun-2005
 * Time: 18:54:54
 * To change this template use File | Settings | File Templates.
 */
public class ColorChooser extends JFrame {
    JPanel panel, bjpanel;
    JSlider red, green, blue;
    JLabel redL, greenL, blueL;
    JButton aceptar, salir;
    Color result;
    Color anterior;

    public ColorChooser(JPanel bjpanel) {
        this.bjpanel = bjpanel;
        anterior = bjpanel.getBackground();
        initGui();
        getContentPane().add(panel);
        setUndecorated(true);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private void initGui() {
        panel = new JPanel();
        redL = new JLabel("Red:");
        greenL = new JLabel("Green:");
        blueL = new JLabel("Blue:");

        red = new JSlider(JSlider.HORIZONTAL, 0, 255, 20);
        red.setMajorTickSpacing(40);
        red.setMinorTickSpacing(10);
        red.setPaintTicks(true);
        red.setPaintLabels(true);

        green = new JSlider(JSlider.HORIZONTAL, 0, 255, 20);
        green.setMajorTickSpacing(40);
        green.setMinorTickSpacing(10);
        green.setPaintTicks(true);
        green.setPaintLabels(true);

        blue = new JSlider(JSlider.HORIZONTAL, 0, 255, 20);
        blue.setMajorTickSpacing(40);
        blue.setMinorTickSpacing(10);
        blue.setPaintTicks(true);
        blue.setPaintLabels(true);

        stateChanging(red);
        stateChanging(green);
        stateChanging(blue);

        aceptar = new JButton(new AbstractAction("Aceptar") {

            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        salir = new JButton(new AbstractAction("Salir") {

            public void actionPerformed(ActionEvent e) {
                bjpanel.setBackground(anterior);
                dispose();
            }
        });

        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;

        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(5, 5, 5, 5);
        panel.add(redL, c);

        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(5, 5, 5, 5);
        panel.add(red, c);

        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(5, 5, 5, 5);
        panel.add(greenL, c);

        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(5, 5, 5, 5);
        panel.add(green, c);

        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(5, 5, 5, 5);
        panel.add(blueL, c);

        c.gridx = 1;
        c.gridy = 2;
        c.insets = new Insets(5, 5, 5, 5);
        panel.add(blue, c);

        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(5, 5, 5, 5);
        panel.add(aceptar, c);

        c.gridx = 1;
        c.gridy = 3;
        c.insets = new Insets(5, 5, 5, 5);
        panel.add(salir, c);
    }
    
    public void stateChanging(JSlider slider) {
        slider.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (source == red) {
          
                        int fps = (int) source.getValue();
                        result = new Color(fps, green.getValue(), blue.getValue());

                    
                } else if (source == green) {
                   
                        int fps = (int) source.getValue();
                        result = new Color(red.getValue(), fps, blue.getValue());
                    
                } else {
                    
                        int fps = (int) source.getValue();
                        result = new Color(red.getValue(), green.getValue(), fps);
                    
                }
                bjpanel.setBackground(result);
            }
        });
    }

}
