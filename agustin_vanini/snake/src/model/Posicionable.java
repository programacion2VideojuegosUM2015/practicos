package model;

import java.awt.Graphics;

/**
 * La interfaz <code>Posicionable</code> nos indica que toda clase que la implemente
 * se comportará como un objeto que tiene una posición en el mapa del juego. Siendo esta posición
 * un punto con coordenadas X e Y. Si tiene una posición en el mapa es muy probable que vamos a querer 
 * mostrarlo en el mapa, por lo tanto cada objeto posicionable sabrá pintarse en el mapa utilizando
 * su posición.
 * 
 * @author nico
 *
 */
public interface Posicionable {

	/**
	 * Con este método de acceso público podremos saber en todo momento el valor de X.
	 * @return El valor actual del objeto en el eje x.
	 */
	public int getX();
	
	/**
     * Para cambiar el valor de la posición en el eje X utilizo esta función enviando el nuevo valor.
     * @param x Recibo el nuevo valor para la posición en el eje X
     */
	public void setX(int x);
	
	/**
	 * Con este método de acceso público podremos saber en todo momento el valor de Y.
	 * @return El valor actual del objeto en el eje y.
	 */
    public int getY();
	
    /**
     * Para cambiar el valor de la posición en el eje Y utilizo esta función enviando el nuevo valor.
     * @param y Recibo el nuevo valor para la posición en el eje Y
     */
	public void setY(int y);
	
	/**
	 * En este método se definirá la manera de pintar el objeto que implemente esta interfaz. 
	 * @param g Recibo los gráficos del mapa donde dibujaremos el objeto Posicionable en su actual posición.
	 */
	public void paint(Graphics g);
	
}
