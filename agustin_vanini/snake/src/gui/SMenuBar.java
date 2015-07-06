package gui;


import javax.swing.*;
import java.awt.event.ActionEvent;

public class SMenuBar extends JMenuBar{

    JMenu menu,submenu;
    JMenuItem item;
    AbstractAction cambiarFondo;
    public SMenuBar(final SnakePanel sp){
        cambiarFondo = new AbstractAction(){

            public void actionPerformed(ActionEvent e) {
                String fondo = ((JMenuItem)e.getSource()).getText();
                sp.screen.setVisible(true);
                if(fondo.equals("Grass")){
                    sp.screen.setIcon(new ImageIcon(getClass().getResource("images/grass.jpg")));
                }else if(fondo.equals("Orange")){
                    sp.screen.setIcon(new ImageIcon(getClass().getResource("images/fondo2.jpg")));
                }else if(fondo.equals("Mac")){
                    sp.screen.setIcon(new ImageIcon(getClass().getResource("images/mac_background.jpg")));
                }else if(fondo.equals("None")){
                    sp.screen.setVisible(false);
                }else if(fondo.equals("Other...")){
                    sp.screen.setVisible(false);
                    new ColorChooser(sp);
                }
            }
        };
        
        menu = new JMenu("Options");
        submenu = new JMenu("Fondo");
        item = new JMenuItem(cambiarFondo);
        item.setText("Grass");
        submenu.add(item);
        item = new JMenuItem(cambiarFondo);
        item.setText("Orange");
        submenu.add(item);
        item = new JMenuItem(cambiarFondo);
        item.setText("Mac");
        submenu.add(item);
        item = new JMenuItem(cambiarFondo);
        item.setText("None");
        submenu.add(item);
        item.setText("Other...");
        submenu.add(item);
        menu.add(submenu);
        add(menu);
    }
}
