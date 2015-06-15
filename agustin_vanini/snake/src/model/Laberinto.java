package model;

import gui.SnakePanel;

import javax.swing.*;
import java.awt.*;


/**
 * La clase <code>Labertino</code> contiene un arreglo de <code>Bloques</code> 
 * que componen al Laberinto.
 * 
 * @author nico
 * @date: 15-jul-2005 14:22:58
 *
 */
public abstract class Laberinto {

    private int labX[], labY[];
    /** */
    private ImageIcon block;

    protected void setBlock(ImageIcon imageIcon) {
        block = imageIcon;
    }


    public boolean esta(int x, int y) {
        for (int i = 0; i < labX.length; i++) {
            if (labX[i] == x && labY[i] == y) {
                return true;
            }
        }
        return false;
    }

    public int[] getLabY() {
        return labY;
    }

    public void setLabY(int[] labY) {
        this.labY = labY;
    }

    public int[] getLabX() {
        return labX;
    }

    public void setLabX(int[] labX) {
        this.labX = labX;
    }

    public void paintLaberinto(Laberinto currentLaberinto, Graphics g, SnakePanel snakePanel) {
        for (int i = 0; i < labX.length; i++) {
            block.paintIcon(snakePanel, g, labX[i], labY[i]);
        }
    }
}
