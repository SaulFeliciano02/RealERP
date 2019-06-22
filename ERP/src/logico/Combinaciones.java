package logico;

import java.util.ArrayList;

public class Combinaciones {
	
	public ArrayList<Atributos> listaAtributos;
	public String numeroSerie;
	public float existenciaActual;
	public Partida partida;
	public Atributos atributo1;
	public Atributos atributo2;
	public Atributos atributo3;
	
	
	public Combinaciones(String numeroSerie, float existenciaActual, ArrayList<Atributos> listaAtributos) {
		super();
		this.listaAtributos = listaAtributos;
		this.numeroSerie = numeroSerie;
		this.existenciaActual = existenciaActual;
		this.atributo1 = listaAtributos.get(0);
		this.atributo2 = listaAtributos.get(1);
		if(listaAtributos.size()>2)
		{
			this.atributo3 = listaAtributos.get(2);
		}
		else {
			this.atributo3 = new Atributos("", null);
		}
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

	public String getAtributo1() {
		return atributo1.getNombre();
	}

	public void setAtributo1(Atributos atributo1) {
		this.atributo1 = atributo1;
	}

	public String getAtributo2() {
		return atributo2.getNombre();
	}

	public void setAtributo2(Atributos atributo2) {
		this.atributo2 = atributo2;
	}

	public String getAtributo3() {
		return atributo3.getNombre();
	}

	public void setAtributo3(Atributos atributo3) {
		this.atributo3 = atributo3;
	}
	
	
	
}
