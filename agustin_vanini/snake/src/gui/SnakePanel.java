package gui;

import control.*;

import javax.swing.*;

import model.Laberinto;
import model.Snake;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class SnakePanel extends JPanel {
	
	//TODO crear variables estaticas que tengan el ancho y alto del panel. Ademas cuantos pixels por cuadro
	
    public String direction;//TODO al snake controller

    public int x, y;//TODO a la snake esta es la posicion de la head
    
    int appleX, appleY, bonusX, bonusY, applesCantidad, bonusCantidad;//TODO al game controller
    public int velocity;//TODO al snake controller
    
    //TODO repartir estos iconos entre el gamecontroller, snake o snakecontroller
    public ImageIcon snakeHeadIcon, appleIcon, bonusIcon, bodyIcon;
    
    public StatusBar statusBar;
    
    boolean conBordes, isBonusActivated, estaVivo;//TODO gamecontroller y snakecontroller
    
    
    public Snake snake;
    
    public SnakeThread snakeThread;
    public JFrame frame;
    
    public boolean seMovio;//TODO snakecontroller
    public JLabel screen;
    public int speed = 20;//TODO rename y al snakecontroller
    
    public boolean invisible;//TODO snakecontroller o gamecontroller
    public boolean normalSkin;//TODO snakecontroller
    public boolean pause;//TODO gamecontroller

    Laberinto laberinto;//TODO gamecontroller

    public SnakePanel(final StatusBar stbar, final JFrame frame, int nivel, int lab) {
        velocity = nivel;
        normalSkin = true;
        x = 400;
        y = 300;
        if (lab == 1) {
            conBordes = true;
        } else if (lab == 2) {
            laberinto = new LabOne();
        } else if (lab == 3) {
            laberinto = new LabTwo();
            x = 60;
            y = 480;
        } else if (lab == 4) {
            laberinto = new LabThree();
        }
        this.frame = frame;
        snake = new Snake(x, y, Snake.DERECHA);
        this.statusBar = stbar;
        appleIcon = new ImageIcon(getClass().getResource("images/apple20.png"));
        snakeHeadIcon = new ImageIcon(getClass().getResource("images/right.png"));
        bodyIcon = new ImageIcon(getClass().getResource("images/snake_node.png"));
        direction = Snake.DERECHA;
        estaVivo = true;
        setFocusable(true);
        setPreferredSize(new Dimension(800, 600));
        addKeyBoardListener();
        screen = new JLabel();
        screen.setIcon(new ImageIcon(getClass().getResource("images/grass.jpg")));

        setLayout(new BorderLayout());
        add(screen, BorderLayout.CENTER);
        newApple();

        snakeThread = new SnakeThread();
        snakeThread.setStatusBar(stbar);

        snakeThread.start();

    }

    private void newApple() {
        do {
            appleX = (int) (Math.random() * 20);      //40
            appleY = (int) (Math.random() * 30);
            appleX = appleX * 40;//20
            appleY = appleY * 20;
        } while (snake.crashOwnBody(appleX, appleY));

        if (laberinto != null) {
            if (laberinto.esta(appleX, appleY))
                newApple();

        }

        if (applesCantidad != 0 && applesCantidad % 5 == 0) {
            newBonus();
        }
        if (applesCantidad != 0)
            snake.addNodo();
    }

    private void newBonus() {
        int b = (int) (Math.random() * 4 + 1);
        bonusIcon = new ImageIcon(getClass().getResource("images/" + b + ".png"));
        do {
            bonusX = (int) (Math.random() * 38);
            bonusY = (int) (Math.random() * 30);
            bonusX = bonusX * 20;
            bonusY = bonusY * 20;
            isBonusActivated = true;
        } while (snake.crashOwnBody(bonusX, bonusY) || snake.crashOwnBody(bonusX + 20, bonusY) ||
                (appleX == bonusX && appleY == bonusY) || (bonusX + 20 == appleX && bonusY == appleY));

        if (laberinto != null) {
            if (laberinto.esta(bonusX, bonusY) || laberinto.esta(bonusX + 20, bonusY))
                newBonus();

        }
        new Thread() {
            public void run() {
                try {
                    while (pause) {
                        Thread.sleep(velocity);
                    }

                    Thread.sleep(velocity * 40);


                    isBonusActivated = false;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void addKeyBoardListener() {
        this.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                int opt = e.getKeyCode();
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    pause = true;
                    String cheat = JOptionPane.showInputDialog("Tipeamela");
                    Cheats c = new Cheats(SnakePanel.this);
                    pause = false;
                    if (cheat != null)
                        c.cheat(cheat);

                } else if (e.getKeyCode() == KeyEvent.VK_P) {
                    pause = !pause;
                }


                if (seMovio) {
                    switch (opt) {
                        case KeyEvent.VK_DOWN:
                            if (direction != Snake.ARRIBA) {
                                direction = Snake.ABAJO;
                            }
                            break;
                        case KeyEvent.VK_UP:
                            if (direction != Snake.ABAJO) {
                                direction = Snake.ARRIBA;
                            }
                            break;
                        case KeyEvent.VK_RIGHT:
                            if (direction != Snake.IZQUIERDA) {
                                direction = Snake.DERECHA;
                            }
                            break;
                        case KeyEvent.VK_LEFT:
                            if (direction != Snake.DERECHA) {
                                direction = Snake.IZQUIERDA;
                            }
                            break;
                    }
                    seMovio = false;
                    String iconName = direction;
                    if (!normalSkin) {
                        iconName = iconName + "Red";
                    }
                    snakeHeadIcon = new ImageIcon(getClass().getResource("images/" + iconName + ".png"));
                }
            }

        });
    }

    public void gameOver() {
        int n = JOptionPane.showConfirmDialog(SnakePanel.this,
                "Perdiste, queres volver a jugar?",
                "Game Over",
                JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            frame.dispose();
            new SnakeMenu();
        } else
            System.exit(0);
    }

    public void paint(Graphics g) {
        super.paint(g);
        if (conBordes) {
            g.setColor(Color.BLACK);
            ((Graphics2D) (g)).draw3DRect(0, 0, 798, 598, true);
            ((Graphics2D) (g)).draw3DRect(2, 2, 794, 594, true);
        }

        snakeHeadIcon.paintIcon(this, g, x, y);
        
        
        snake.paint(g);
        
        snake.move(x, y);
        appleIcon.paintIcon(this, g, appleX, appleY);
        if (isBonusActivated)
            bonusIcon.paintIcon(this, g, bonusX, bonusY);
        revalidate();
        if (laberinto != null) {//todo remove
            laberinto.paintLaberinto(laberinto, g, this);
        }
        if (invisible) {
            ImageIcon invinsible = new ImageIcon(getClass().getResource("images/invinsible.png"));
            invinsible.paintIcon(this, g, 770, 0);

        }
    }


    public void changeSnakeSkin() {
        normalSkin = !normalSkin;
        if (normalSkin) {
            bodyIcon = new ImageIcon(getClass().getResource("images/body.png"));
            snakeHeadIcon = new ImageIcon(getClass().getResource("images/" + direction + ".png"));
        } else {
            bodyIcon = new ImageIcon(getClass().getResource("images/bodyRed.png"));
            snakeHeadIcon = new ImageIcon(getClass().getResource("images/" + direction + "Red.png"));
        }

    }

    public class SnakeThread extends Thread {
        private StatusBar statusBar;
        final SnakeSound bonusSound;
        final SnakeSound appleBeep;
        private static final int MAX_Y = 580;
        private static final int MAX_X = 780;

        public SnakeThread() {
            appleBeep = new SnakeSound("beep.wav");
            bonusSound = new SnakeSound("over.wav");
        }

        public void run() {
            try {
                while (estaVivo) {
                    if (direction == Snake.ARRIBA) {
                        y -= speed;
                        if (y < 0) {
                            if (choco()) {
                                y = MAX_Y;
                            }
                        }
                    } else if (direction == Snake.ABAJO) {
                        y += speed;
                        if (y > MAX_Y) {
                            if (choco()) {
                                y = 0;
                            }
                        }
                    } else if (direction == Snake.IZQUIERDA) {
                        x -= speed;
                        if (x < 0) {
                            if (choco()) {
                                x = MAX_X;
                            }
                        }
                    } else {
                        x += speed;
                        if (x > MAX_X) {
                            if (choco()) {
                                x = 0;
                            }
                        }
                    }

                    if (appleX == x && appleY == y) {
                        statusBar.setApples(applesCantidad += 1);
                        newApple();
                        appleBeep.play();

                    }

                    if (isBonusActivated) {

                        if (bonusX == x && bonusY == y) {
                            statusBar.setBonus(bonusCantidad += 1);
                            isBonusActivated = false;
                            bonusSound.play();
                        } else if (isBonusActivated && (bonusX + 20 == x && bonusY == y)) {
                            statusBar.setBonus(bonusCantidad += 1);
                            isBonusActivated = false;
                            bonusSound.play();
                        }
                    }
                    if (laberinto != null) {
                        if (!invisible && laberinto.esta(x, y)) { //todo remove
                            estaVivo = false;
                        }
                    }
                    if (!invisible && snake.crashOwnBody(x, y)) {
                        estaVivo = false;
                    }

                    if (!estaVivo) {
                        gameOver();
                    } else
                        repaint();
                    seMovio = true;
                    Thread.sleep(velocity);

                    while (pause) {
                        Thread.sleep(velocity);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private boolean choco() {
            estaVivo = !conBordes;
            return estaVivo;
        }

        public void setStatusBar(StatusBar statusBar) {
            this.statusBar = statusBar;
        }
    }

}
