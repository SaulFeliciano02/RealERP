package logico;

public class PreguntaSeguridad {
	private String pregunta1;
	private String respuesta1;
	private String pregunta2;
	private String respuesta2;

	public PreguntaSeguridad(String pregunta1, String respuesta1, String pregunta2, String respuesta2) {
		super();
		this.pregunta1 = pregunta1;
		this.respuesta1 = respuesta1;
		this.pregunta2 = pregunta2;
		this.respuesta2 = respuesta2;
	}
	public String getPregunta1() {
		return pregunta1;
	}
	public void setPregunta1(String pregunta1) {
		this.pregunta1 = pregunta1;
	}
	public String getRespuesta1() {
		return respuesta1;
	}
	public void setRespuesta1(String respuesta1) {
		this.respuesta1 = respuesta1;
	}
	public String getPregunta2() {
		return pregunta2;
	}
	public void setPregunta2(String pregunta2) {
		this.pregunta2 = pregunta2;
	}
	public String getRespuesta2() {
		return respuesta2;
	}
	public void setRespuesta2(String respuesta2) {
		this.respuesta2 = respuesta2;
	}
	
	

}
