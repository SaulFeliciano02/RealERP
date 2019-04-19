package logico;

public class Proveedores extends Persona {
	
	public float saldo;
	public String domicilio;
	public String correo;
	public String rnc;
	public Rubro rubro;
	public String sitioWeb;

	public Proveedores(String codigo, String nombre, String telefono, String domicilio, String correo,
			String rnc, Rubro rubro, String sitioWeb) {
		super(codigo, nombre, telefono);
		this.saldo = 0;
		this.domicilio = domicilio;
		this.correo = correo;
		this.rnc = rnc;
		this.rubro = rubro;
		this.sitioWeb = sitioWeb;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo += saldo;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getRnc() {
		return rnc;
	}

	public void setRnc(String rnc) {
		this.rnc = rnc;
	}

	public Rubro getRubro() {
		return rubro;
	}

	public void setRubro(Rubro rubro) {
		this.rubro = rubro;
	}

	public String getSitioWeb() {
		return sitioWeb;
	}

	public void setSitioWeb(String sitioWeb) {
		this.sitioWeb = sitioWeb;
	}

}
