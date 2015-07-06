package model;

import java.awt.Graphics;
import java.util.ArrayList;


/**
 * La clase <code>Snake</code> representa la v’bora en el juego.<p> 
 * Contiene una Lista de nodos que simulan ser el cuerpo de la v’bora.
 * @author nico
 * @date: 15-jun-2005 18:59:44 
 */
public class Snake {

    public static final String ARRIBA = "up";
    public static final String ABAJO = "down";
    public static final String IZQUIERDA = "left";
    public static final String DERECHA = "right";

    /** Este ArrayList contiene todos los nodos que componen el cuerpo de la v’bora.*/
    private ArrayList<SnakeNodo> snakeBody;
    
    /** Para facilitar el movimiento de la vibora actualizo las referencias al ultimo y primer nodo */
    private SnakeNodo lastNode,firstNode;
    

    public Snake(int x, int y, String direction) {

        SnakeNodo firstNode = null;
        SnakeNodo secondNode = null;
      
        if (direction == ARRIBA) {
            firstNode = new SnakeNodo(x, y + 20);
            secondNode = new SnakeNodo(x, y + 40);
        } else if (direction == ABAJO) {
            firstNode = new SnakeNodo(x, y - 20);
            secondNode = new SnakeNodo(x, y - 40);
        } else if (direction == IZQUIERDA) {
            firstNode = new SnakeNodo(x, y + 20);
            secondNode = new SnakeNodo(x, y + 40);
        } else if (direction == DERECHA){
            firstNode = new SnakeNodo(x, y);
            secondNode = new SnakeNodo(x - 20, y);
        }
        
        firstNode.setPreviousNode(secondNode);
        secondNode.setNextNode(firstNode);
 
        this.firstNode = firstNode;
        lastNode = secondNode;

        snakeBody = new ArrayList<SnakeNodo>();
        snakeBody.add(firstNode);
        snakeBody.add(secondNode);
    }

    /**
     * Esta funci—n se encarga de agregar un nodo en el cuerpo de la vibora,
     * actualizando las variables necesarias y haciendo los linkeos entre los nodos.
     * El nuevo nodo se agrega siempre como ultimo en el cuerpo.
     */
    public void addNodo() {
        SnakeNodo newNode = new SnakeNodo(lastNode.getX(),lastNode.getY());
        newNode.setNextNode(lastNode);
        lastNode.setPreviousNode(newNode);
        lastNode = newNode;
        snakeBody.add(newNode);
    }
        

    /**
     * Cuando la vibora se desplaza llamamos a este metodo para actualizar los nodos necesarios y
     * simular el movimiento. Para no desplazar inutilmente todos los nodos, hacemos algo mas eficiente
     * y llevamos el ultimo nodo a la ex posicion de la cabeza que recibimos con X e Y.
     * @param x recibo el valor de X de la anterior posici—n de la cabeza de la vibora
     * @param y recibo el valor de Y de la anterior posici—n de la cabeza de la vibora
     */
    public void move(int x, int y) {
    	lastNode.setPosition(x, y);
    	SnakeNodo temp = lastNode.getNextNode();
    	lastNode.setNextNode(null);
    	lastNode.setPreviousNode(firstNode);
    	firstNode.setNextNode(lastNode);
    	firstNode = lastNode;
    	lastNode = temp;

    }

    /**
     * Esta funcion nos indica si la vibora ha colisionado con una parte de su cuerpo.
     * Recibimos la posicion actual de la cabeza y verificamos si algun nodo del cuerpo 
     * se encuentra en esa misma posicion. Si encontramos uno retornamos true, de lo contrario
     * se retorna false.
     * @param x la posicion actual en X de la vibora
     * @param y la posicion actual en Y de la vibora
     * @return verdadero si colisono con su cuerpo, falso si anda libre por el campo.
     */
    public boolean crashOwnBody(int x, int y) {
        for (int i = 0; i < snakeBody.size(); i++) {
            SnakeNodo sn = (SnakeNodo) snakeBody.get(i);
            if (sn.getX() == x && sn.getY() == y)
                return true;

        }
        return false;
    }
    
    /**
     * La vibora sabe pintarse a si misma, itera todos los nodos de su cuerpo y llama
     * a la funcion paint de cada uno.
     * @param g recibimos los graphics donde vamos a pintar nuestra vibora.
     */
    public void paint(Graphics g){
    	for (SnakeNodo nodo : this.getSnakeBody()) {
            nodo.paint(g);
        }
    }
    
    /**
     * 
     * @return el arreglo que contiene los nodos de la vibora.
     */
    public ArrayList<SnakeNodo> getSnakeBody() {
		return snakeBody;
    }

}
