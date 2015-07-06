package control;

import model.Snake;

/**
 * La clase <code>SnakeController</code> se encarga de manejar todos los eventos que
 * alteran a la vibora como por ejemplo: movimiento, comer manzanas, crecer, chocar, etc.<p> 
 * 
 * Si seguimos el patron de disenio MVC (model view controller), el controlador se encarga de 
 * comunicar la vista con el modelo. En esta aplicacion recibe los eventos del teclado de la Vista
 * y modifica el estado de la vibora en el Modelo.
 * @author nico
 * @date: 15-jun-2005 18:59:44 
 */
public class SnakeController {
	public static final int ARRIBA = 1;
    public static final int ABAJO = 2;
    public static final int IZQUIERDA = 3;
    public static final int DERECHA = 4;
    
    private Snake snake;
    
	public SnakeController(Snake snake) {
		this.snake = snake;
	}
	
	/**
	 * Retorno la vibora que actualmente controla el SnakeController.
	 * @return snake
	 */
	public Snake getSnake() {
		return snake;
	}
	
}
