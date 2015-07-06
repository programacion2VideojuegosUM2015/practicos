package control;

import javax.swing.*;

import model.Laberinto;

public class LabOne extends Laberinto{

    public LabOne(){
        setBlock(new ImageIcon(getClass().getResource("../gui/images/lab.png")));

        int[] labX = new int[]{0,20,40, 0, 0,740,760,780,780,780,  0,  0,  0, 20, 40,
                                740,760,780,780,780,260,280,300,320,340,360,380,400,420,
                                440,460,260,280,300,320,340,360,380,400,420,
                                440,460};
        int[] labY = new int[]{0, 0, 0,20,40,  0,  0,  0, 20, 40,540,560,580,580,580,
                                580,580,580,560,540,200,200,200,200,200,200,200,200,200,
                                200,200,380,380,380,380,380,380,380,380,380,380,380};
        setLabX(labX);
        setLabY(labY);
    }

}

