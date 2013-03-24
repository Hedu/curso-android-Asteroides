package org.example.asteroides;

import android.app.Activity;
import android.os.Bundle;

public class Juego extends Activity {
	
	private VistaJuego vistaJuego;
	
	@Override
	protected void onCreate(Bundle bundle){
		super.onCreate(bundle);
		setContentView(R.layout.juego);
		vistaJuego = (VistaJuego) findViewById(R.id.VistaJuego);
	}
	
	@Override protected void onPause() {
		   super.onPause();
		   vistaJuego.getThread().pausar();
		   vistaJuego.pararSensorListener();
		}
		 
		@Override protected void onResume() {
		   super.onResume();
		   vistaJuego.getThread().reanudar();
		   vistaJuego.activarSensorListener();
		}
		
 
		@Override protected void onDestroy() {
		   vistaJuego.getThread().detener();
		   super.onDestroy();
		}
		
}
