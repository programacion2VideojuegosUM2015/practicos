package ar.edu.um.VJ2015;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class PoliticosAparecen {
	
	private Texture texturaCristina;
	private Texture texturaCapitanich;
	private Texture texturaMenen;
	private Texture texturaMaxi;
	private Texture texturaKicillof;
	
	private float direccion;
	private float velocidad;
	//se crean dos arreglos uno que contiene los objetos politicos y los otros las plantillas de dichos objetos
	public Array<Politico> politicos;
	private Array<PoliticoTemplate> politicoTemplate;
	private long lastDropTime;
	private int numeroTemplate;
	
	public PoliticosAparecen(){
		
		texturaCristina = new Texture(Gdx.files.internal("cristina.jpg"));
		texturaCapitanich = new Texture(Gdx.files.internal("capitanich.jpg"));
		texturaMenen = new Texture(Gdx.files.internal("menen.jpg"));
		texturaMaxi = new Texture(Gdx.files.internal("maxi.jpg"));
		texturaKicillof = new Texture(Gdx.files.internal("kicillof.jpg"));
		
		velocidad = 250;
		direccion = 1;
		politicos= new Array<Politico>();
		
		//se agregan las plantillas al array
		politicoTemplate = new Array<PoliticoTemplate>();
		politicoTemplate.add(new PoliticoTemplate(50, 500, texturaCristina));
		politicoTemplate.add(new PoliticoTemplate(30, 400, texturaCapitanich));
		politicoTemplate.add(new PoliticoTemplate(30, 300, texturaMenen));
		politicoTemplate.add(new PoliticoTemplate(20, 300, texturaMaxi));
		politicoTemplate.add(new PoliticoTemplate(20, 300, texturaKicillof));
	}
	//metodo para pintar a los politicos que aparecen	
	public void draw(SpriteBatch batch){
			for (Politico politico : politicos) {
				batch.draw(politico.getTextura(), politico.getRectangulo().getX(), politico.getRectangulo().getY());
			}	
		}
	
	public void update(float delta){
			
			if (TimeUtils.nanoTime() - lastDropTime > 1000000000)
				spawnPolitico();
			
			//recorre array y le da direccion a los politicos
			for (Politico politico : politicos) {
				politico.getRectangulo().x = politico.getRectangulo().x + velocidad * delta * direccion ;
			if (politico.getRectangulo().x >= 350){
					politicos.removeValue(politico, true);
			}
			
			}
		}
	//metodo para agregar los politicos al arreglo y ponerlos en el juego	
	private void spawnPolitico() {
			float x = 0;
			float y = MathUtils.random(100, 600 - 64); ;
			int numero = MathUtils.random(0,100);
			if(numero>0 && numero<5){
				numeroTemplate = 0;
			}
			if(numero>5 && numero<25){
				numeroTemplate = 1;
			}
			if(numero>25 && numero<55){
				numeroTemplate = 2;
			}
			if(numero>55 && numero<85){
				numeroTemplate = 3;
			}
			if(numero>85 && numero<100){
				numeroTemplate = 4;
			}
			int temp = numeroTemplate;
			PoliticoTemplate t = politicoTemplate.get(temp);
			Politico politico = new Politico(t.getTextura(),t.getPuntaje(),t.getVelocidad(),x,y);
			politicos.add(politico);
			lastDropTime = TimeUtils.nanoTime();
		}
	//metodo para la colision con el arma y dar el game over	
	public void colisionConArma(Revolver revolver,MataCorrupto mataCorrupto){
			for(Politico politico : politicos){
				if(politico.getRectangulo().overlaps(revolver.getContornoRevolver())){
					 mataCorrupto.setScreen(new GameOver(mataCorrupto));
				}
			}
		}
	//metodo para quitar de la ram las texturas
	public void dispose() {
			texturaCristina.dispose();
			texturaCapitanich.dispose();
			texturaKicillof.dispose();
			texturaMaxi.dispose();
			texturaMenen.dispose();
		}
	
}
