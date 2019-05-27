package logico;

import java.util.ArrayList;

public class Combinaciones {
	
	public ArrayList<Atributos> listaAtributos;
	public String numeroSerie;
	public float existenciaActual;
	public Partida partida;
	
	public Combinaciones(String numeroSerie, float existenciaActual, ArrayList<Atributos> listaAtributos) {
		super();
		this.listaAtributos = listaAtributos;
		this.numeroSerie = numeroSerie;
		this.existenciaActual = existenciaActual;
		//this.partida = partida;
	}

	public ArrayList<Atributos> getListaAtributos() {
		return listaAtributos;
	}

	public String getNumeroSerie() {
		return numeroSerie;
	}

	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	public Partida getPartida() {
		return partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}

	public float getExistenciaActual() {
		return existenciaActual;
	}
	
	public void disminuirExistenciaActual(float reduc) {
		this.existenciaActual -= reduc; 
	}
	
	public void agregarALaExistencia(float sum) {
		this.existenciaActual += sum;
	}
	
	public void agregarAtributo(Atributos atributo) {
		this.listaAtributos.add(atributo);
	}
	
}
