package model;

import java.awt.Graphics;

/**
 * La interfaz <code>Posicionable</code> nos indica que toda clase que la implemente
 * se comportar� como un objeto que tiene una posici�n en el mapa del juego. Siendo esta posici�n
 * un punto con coordenadas X e Y. Si tiene una posici�n en el mapa es muy probable que vamos a querer 
 * mostrarlo en el mapa, por lo tanto cada objeto posicionable sabr� pintarse en el mapa utilizando
 * su posici�n.
 * 
 * @author nico
 *
 */
public interface Posicionable {

	/**
	 * Con este m�todo de acceso p�blico podremos saber en todo momento el valor de X.
	 * @return El valor actual del objeto en el eje x.
	 */
	public int getX();
	
	/**
     * Para cambiar el valor de la posici�n en el eje X utilizo esta funci�n enviando el nuevo valor.
     * @param x Recibo el nuevo valor para la posici�n en el eje X
     */
	public void setX(int x);
	
	/**
	 * Con este m�todo de acceso p�blico podremos saber en todo momento el valor de Y.
	 * @return El valor actual del objeto en el eje y.
	 */
    public int getY();
	
    /**
     * Para cambiar el valor de la posici�n en el eje Y utilizo esta funci�n enviando el nuevo valor.
     * @param y Recibo el nuevo valor para la posici�n en el eje Y
     */
	public void setY(int y);
	
	/**
	 * En este m�todo se definir� la manera de pintar el objeto que implemente esta interfaz. 
	 * @param g Recibo los gr�ficos del mapa donde dibujaremos el objeto Posicionable en su actual posici�n.
	 */
	public void paint(Graphics g);
	
}
