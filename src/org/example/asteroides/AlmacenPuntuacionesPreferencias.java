package org.example.asteroides;

import java.util.Vector;

import android.content.Context;
import android.content.SharedPreferences;

public class AlmacenPuntuacionesPreferencias implements AlmacenPuntuaciones {

	private static String PREFERENCIAS = "puntuaciones";
    private Context context;
    final static int MAX_PUNTUACIONES = 4;
	
    public AlmacenPuntuacionesPreferencias(Context context) {
        this.context = context;
    }
    
	@Override
	public void guardarPuntuacion(int puntos, String nombre, long fecha) {
		 SharedPreferences preferencias =context.getSharedPreferences(PREFERENCIAS, Context.MODE_PRIVATE);
		 SharedPreferences.Editor editor = preferencias.edit();
		 int currentNumber = listaPuntuaciones(MAX_PUNTUACIONES).size();
		 if (currentNumber < MAX_PUNTUACIONES){
			 editor.putString("puntuacion"+currentNumber, puntos + " " + nombre);
		 }
		 else {
			 Vector<String> puntuaciones = listaPuntuaciones(MAX_PUNTUACIONES);
			 Vector<Integer> puntuacionesInt = new Vector<Integer>();
			 for (String puntuacion : puntuaciones){
				 puntuacionesInt.add(Integer.parseInt(puntuacion.substring(0, puntuacion.indexOf(" "))));
			 }
			 int min=0;
			 for (int i=1; i<MAX_PUNTUACIONES;i++){
				 if (puntuacionesInt.get(i) < puntuacionesInt.get(min)){
					 min=i;
				 }
			 }
			 if(puntuacionesInt.get(min) < puntos){
				 editor.putString("puntuacion"+min, puntos + " " + nombre);
			 }
			 
		 }
		 editor.commit();
	}

	@Override
	public Vector<String> listaPuntuaciones(int cantidad) {
		Vector<String> result = new Vector<String>();
        SharedPreferences preferencias =context.getSharedPreferences(PREFERENCIAS, Context.MODE_PRIVATE);
        for (int i=0; i< cantidad; i++){
        	String s = preferencias.getString("puntuacion"+i, "");
        	if (s != "") {
               result.add(s);
        	}
        }
        return result;
	}
	
}
