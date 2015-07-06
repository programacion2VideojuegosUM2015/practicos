package control;

import javax.swing.*;

import model.Laberinto;


public class LabThree extends Laberinto{

    public int x, y;

    public LabThree() {
        setBlock(new ImageIcon(getClass().getResource("../gui/images/lab.png")));

        int[] labX = new int[186];
        int[] labY = new int[186];

        for (int i = 0; i < 40; i++) {
            labX[i] = i * 20;
            labY[i] = 0;
        }
        for (int i = 40; i < 80; i++) {
            labX[i] = (i - 40) * 20;
            labY[i] = 580;
        }
        for (int i = 80, j = 80; i < 109; i++) {
            if (i < 92 || i > 97) {
                labX[j] = 0;
                labY[j] = (i - 79) * 20;
                j++;
            }
        }
        for (int i = 109, j = 109; i < 138; i++) {
            if (i < 121 || i > 126) {
                labX[j] = 780;
                labY[j] = (i - 108) * 20;
                j++;
            }
        }
        labX[138] = 200;
        labX[139] = 200;
        labX[140] = 200;
        labX[141] = 200;
        labX[142] = 200;

        labY[138] = 160;
        labY[139] = 180;
        labY[140] = 200;
        labY[141] = 220;
        labY[142] = 240;

        for (int i = 143; i < 157; i++) {
            labX[i] = 240 + ((i - 143) * 20);
            labY[i] = 200;
        }

        labX[157] = 540;
        labX[158] = 540;
        labX[159] = 540;
        labX[160] = 540;
        labX[161] = 540;

        labY[157] = 160;
        labY[158] = 180;
        labY[159] = 200;
        labY[160] = 220;
        labY[161] = 240;


        labX[162] = 200;
        labX[163] = 200;
        labX[164] = 200;
        labX[165] = 200;
        labX[166] = 200;

        labY[162] = 380;
        labY[163] = 400;
        labY[164] = 420;
        labY[165] = 440;
        labY[166] = 460;

        for (int i = 167; i < 181; i++) {
            labX[i] = 240 + ((i - 167) * 20);

            labY[i] = 420;
        }

        labX[181] = 540;
        labX[182] = 540;
        labX[183] = 540;
        labX[184] = 540;
        labX[185] = 540;

        labY[181] = 380;
        labY[182] = 400;
        labY[183] = 420;
        labY[184] = 440;
        labY[185] = 460;

        setLabX(labX);
        setLabY(labY);

    }
}