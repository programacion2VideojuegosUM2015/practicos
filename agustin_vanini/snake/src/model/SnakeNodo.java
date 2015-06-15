package model;

import java.awt.Graphics;

import javax.swing.ImageIcon;

/**
 * La clase <code>SnakeNodo</code> representa un nodo de la víbora. Toda parte de la víbora será 
 * un SnakeNodo.<p>
 * El objeto posee dos variables enteras que nos indican la posición actual del nodo en el mapa.
 * Tiene la referencia al nodo siguiente y al anterior nodo en el cuerpo de la vibora.
 * Además tiene un ImageIcon con la imagen que vamos a dibujar.
 * @author nico
 * @date 15-jun-2005 19:00:28
 */
public class SnakeNodo implements Posicionable{
	/** Guardo la posición X del nodo en el mapa.*/
    private int x;
    /** Guardo la posición Y del nodo en el mapa.*/
    private int y;
    /** Imagen que representa el nodo de la víbora.*/
    private ImageIcon snakeNodeIcon;
    
    /** Para facilitar el movimiento de la vibora tenemos referencias del nodo siguiente y el anterior */
    private SnakeNodo previousNode, nextNode;

    /**
     * Inicializo un <code>SnakeNodo</code> enviando dos variables X e Y indicando la posición del nodo.
     * @param x posición en x del nuevo nodo.
     * @param y posición en y del nuevo nodo.
     */
    public SnakeNodo(int x, int y) {
        this.x = x;
        this.y = y;
        this.snakeNodeIcon = new ImageIcon(getClass().getResource("../gui/images/snake_node.png"));
    }
    
    /**
     * Inicializo un <code>SnakeNodo</code> enviando un arreglo que contiene los valores de la
     * siguiente posición donde deberá actualizarse el nodo.
     * @param next arreglo de enteros con dos valores ordenados X,Y.
     * @deprecated
     */
    public SnakeNodo(int[] next) {
        this(next[0],next[1]);
    }
    
    /**
     * 
     * @param x envio el nuevo valor X que deseo asignarle al nodo
     * @param y envio el nuevo valor Y que deseo asignarle al nodo
     */
    public void setPosition(int x, int y){
    	this.setX(x);
    	this.setY(y);
    }

    /**
     * 
     * @return El valor en X de la posición actual del nodo
     */
    public int getX() {
        return x;
    }

    /**
     * 
     * @param x envio el nuevo valor X que deseo asignarle al nodo
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * 
     * @return El valor en Y de la posición actual del nodo
     */
    public int getY() {
        return y;
    }

    /**
     * 
     * @param y envio el nuevo valor Y que deseo asignarle al nodo
     */
    public void setY(int y) {
        this.y = y;
    }
    
    /**
     * 
     * @return retorna el nodo siguiente
     */
    public SnakeNodo getNextNode() {
		return nextNode;
	} 
    
    /**
     * 
     * @param recibe el nodo que tiene en la posición siguiente
     */
    public void setNextNode(SnakeNodo nextNode) {
		this.nextNode = nextNode;
	}
    
    /**
     * 
     * @return retorna el nodo anterior
     */
    public SnakeNodo getPreviousNode() {
		return previousNode;
	}
    
    /**
     * 
     * @param recibe el nodo que tiene en la posición anterior
     */
    public void setPreviousNode(SnakeNodo previousNode) {
		this.previousNode = previousNode;
	}
 

	/**
	 * En este método defino como voy a pintar el nodo de la víbora.
	 * @param g Recibo los gráficos donde dibujaré el icono del objeto SnakeNodo.
	 */
	public void paint(Graphics g) {
		snakeNodeIcon.paintIcon(null, g, this.getX(), this.getY());
	}
}
