package com.mygdx.nikoo;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class AdministradorColisiones implements ContactListener {

	@Override
	public void beginContact(Contact contact) {
		
		
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
		
		if(fa == null || fb == null) return;
		
		
		//pies
		if(   (fb.getUserData() != null && fb.getUserData().equals("foot"))   ||  (fa.getUserData() != null && fa.getUserData().equals("foot") )   ){
			
			Jugador.numFootContacts++;
		}
		
		//rompen globo a jugador

		if(  fa.getUserData() != null && fb.getUserData() != null && ((fb.getUserData().equals("globohit") && fa.getUserData().equals("hitenemigo"))    ||  ( fa.getUserData().equals("globohit") && fb.getUserData().equals("hitenemigo")))   ){
						
		Jugador.damage = true;
		}
		
		//rompen globo a enemigo

		if(  fa.getUserData() != null && fb.getUserData() != null && ((fb.getUserData().equals("englobohit") && fa.getUserData().equals("hit"))    ||  ( fa.getUserData().equals("englobohit") && fb.getUserData().equals("hit")))   ){
		
			
			Balloon.puntos += 100;
			if(Balloon.puntos == 1000 || Balloon.puntos == 2500 || Balloon.puntos == 3500 ){
				Balloon.vidas ++;
				Juego.b004.play();
			}
			
			if(fa.getUserData().equals("englobohit")){
			
				Enemigo.enDamage = fa.getBody().getFixtureList().get(3).getUserData().toString();
				
			}
			
			if(fb.getUserData().equals("englobohit")){
				
				Enemigo.enDamage = fb.getBody().getFixtureList().get(3).getUserData().toString();
				
			}
		}
		
		
		
		
		
		
	}

	@Override
	public void endContact(Contact contact) {
		
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
		
		if(fa == null || fb == null) return;
		
			
		//pies
		if(   (fb.getUserData() != null && fb.getUserData().equals("foot"))   ||  (fa.getUserData() != null && fa.getUserData().equals("foot") )   ){
			
			Jugador.numFootContacts--;
		}
		
		
		//rompen globo a jugador

		if(  fa.getUserData() != null && fb.getUserData() != null && ((fb.getUserData().equals("globohit") && fa.getUserData().equals("hitenemigo"))    ||  ( fa.getUserData().equals("globohit") && fb.getUserData().equals("hitenemigo")))   ){
						
		Jugador.damage = false;
		}
		
		
		//rompen globo a enemigo

		if(  fa.getUserData() != null && fb.getUserData() != null && ((fb.getUserData().equals("englobohit") && fa.getUserData().equals("hit"))    ||  ( fa.getUserData().equals("englobohit") && fb.getUserData().equals("hit")))   ){
		
			if(fa.getUserData().equals("englobohit")){
			
				Enemigo.enDamage = "null";
				
			}
			
			if(fb.getUserData().equals("englobohit")){
				
				Enemigo.enDamage = "null";
				
			}
		}
		
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub

	}

}
