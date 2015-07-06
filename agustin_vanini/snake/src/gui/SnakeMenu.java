package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class SnakeMenu extends JFrame {
    JPanel panel;
    JButton comenzar, salir;
    JComboBox nivelCombo,laberinto;
    JLabel nivelLabel,laberintoLabel;

    public SnakeMenu() {
        initGui();
        super.setTitle("Menu");
        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        comenzar.setFocusable(true);
    }

    private void initGui() {
        panel = new JPanel();
        nivelCombo = new JComboBox(new String[]{"Normal", "Lento", "Rapido"});
        //nivel.setSelectedIndex(2);
        nivelLabel = new JLabel("Seleccione un nivel:");
        laberinto = new JComboBox(new String[]{"Nada","Uno","Dos","Tres","Cuatro"});
        //laberinto.setSelectedIndex(2);
        laberintoLabel = new JLabel("Seleccione un Laberinto:");

        comenzar = new JButton(new AbstractAction("Comenzar") {

            public void actionPerformed(ActionEvent e) {
                int sleepInterval;
                int nivel = nivelCombo.getSelectedIndex();
                switch(nivel){
                    case 0:
                        sleepInterval = 100;
                        break;
                    case 1:
                        sleepInterval = 180;
                        break;
                    default:
                        sleepInterval = 60;
                        break;
                }
                dispose();
                new SnakeApp(sleepInterval,laberinto.getSelectedIndex());
            }
        });


        salir = new JButton(new AbstractAction("Salir") {

            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(5,5,5,5);
        panel.add(nivelLabel, c);
        c.gridx = 1;
        c.gridy = 0;
        panel.add(nivelCombo, c);
        c.gridx = 0;
        c.gridy = 2;
        panel.add(laberintoLabel, c);
        c.gridx = 1;
        c.gridy = 2;
        panel.add(laberinto, c);
        c.gridx = 0;
        c.gridy = 3;
        panel.add(comenzar, c);
        c.gridx = 1;
        c.gridy = 3;
        panel.add(salir, c);

    }
}
