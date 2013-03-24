package org.example.asteroides;

import android.app.Activity;
import android.app.Fragment.SavedState;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Asteroides extends Activity implements OnClickListener{

	 public static AlmacenPuntuaciones almacen = new AlmacenPuntuacionesArray();
	 MediaPlayer mp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button bjugar =(Button) findViewById(R.id.button1);

        bjugar.setOnClickListener(this);
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
        mp =  MediaPlayer.create(this, R.raw.audio);
        mp.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
	    super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void lanzarJuego(View view){
		Intent intent = new Intent(this, Juego.class);
		startActivity(intent);
	}
	
	public void lanzarPreferencias(View view){
		Intent intent = new Intent(this, Preferencias.class);
		startActivity(intent);
	}
	
	public void lanzarAcercaDe(View view){
		Intent intent = new Intent(this, AcercaDe.class);
		startActivity(intent);
	}
	
	public void lanzarSalir(View view){
		finish();
	}
	
	public void lanzarPuntuaciones(View view){
		Intent intent = new Intent(this, Puntuaciones.class);
		startActivity(intent);
	}
	
	
    @Override 
    public boolean onOptionsItemSelected(MenuItem item) {
	     switch (item.getItemId()) {
	     case R.id.acercaDe:
	            lanzarAcercaDe(null);
	            break;
	            
	     case R.id.config:
             lanzarPreferencias(null);

             break;
	     	    
    	 case R.id.puntuaciones:
	        lanzarPuntuaciones(null);
	
	        break;
	     }
	
	     return true; /** true -> consumimos el item, no se propaga*/

    }

	
	public void onClick(View v) {
		if (v.getId() == R.id.button1) {
			lanzarJuego(v);
		}
		
	}
	
	@Override protected void onStart() {

		   super.onStart();

		   Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
		   
		  
		}

		 

		@Override protected void onResume() {

		   super.onResume();

		   Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
		   mp.start();

		}

		 

		@Override protected void onPause() {


		   super.onPause();
		   Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
		   
		}

		 

		@Override protected void onStop() {


		   super.onStop();
		   Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
		   mp.stop();
		}

		 

		@Override protected void onRestart() {

		   super.onRestart();

		   Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
		}

		 

		@Override protected void onDestroy() {

			super.onDestroy();
		   Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
		}
		
		@Override
		protected void onSaveInstanceState(Bundle outState) {
			// TODO Auto-generated method stub
			super.onSaveInstanceState(outState);
			if (mp != null){
				int pos=mp.getCurrentPosition();
				outState.putInt("currentPos", pos);
			}
		}
		
		@Override
		protected void onRestoreInstanceState(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onRestoreInstanceState(savedInstanceState);
			if (savedInstanceState != null && mp != null){
				mp.seekTo(savedInstanceState.getInt("currentPos"));
			}
		}
}