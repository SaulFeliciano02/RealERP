package logico;

public class Longitud extends UnidadMedida {

	public Longitud(String categoria, String nombre, String abreviatura) {
		super(categoria, nombre, abreviatura);
		// TODO Auto-generated constructor stub
	}

	@Override
	public float Conversion(String comparador, float valorTransformar) {
		
		float transformado = 0;
		
		if(this.getNombre().equalsIgnoreCase("Pulgadas"))
		{
			switch(comparador)
			{
				case "Pies":
					transformado = valorTransformar * 0.001f;
					break;
				case "Yardas":
					transformado = valorTransformar * 73.2f;
					break;
				case "Milimetros":
					transformado = valorTransformar * 39.37f;
					break;
				case "Centimetros":
					transformado = valorTransformar * 393.70f;
					break;
				case "Metros":
					transformado = valorTransformar * 39370f;
					break;
			}
		}
		
		if(this.getNombre().equalsIgnoreCase("Pies"))
		{
			switch(comparador)
			{
				case "Pulgadas":
					transformado = valorTransformar * 0.0833f;
					break;
				case "Yardas":
					transformado = valorTransformar * 3f;
					break;
				case "Milimetros":
					transformado = valorTransformar * 0.0033f;
					break;
				case "Centimetros":
					transformado = valorTransformar * 0.033f;
					break;
				case "Metros":
					transformado = valorTransformar * 3.28f;
					break;
			}
		}
		
		if(this.getNombre().equalsIgnoreCase("Yardas"))
		{
			switch(comparador)
			{
				case "Pulgadas":
					transformado = valorTransformar * 0.028f;
					break;
				case "Pies":
					transformado = valorTransformar * 2.03f;
					break;
				case "Milimetros":
					transformado = valorTransformar * 0.0011f;
					break;
				case "Centimetros":
					transformado = valorTransformar * 0.011f;
					break;
				case "Metros":
					transformado = valorTransformar * 1.09f;
					break;
			}
		}
		
		if(this.getNombre().equalsIgnoreCase("Milimetros"))
		{
			switch(comparador)
			{
				case "Pulgadas":
					transformado = valorTransformar * 25.4f;
					break;
				case "Pies":
					transformado = valorTransformar * 1859.28f;
					break;
				case "Yardas":
					transformado = valorTransformar * 914.4f;
					break;
				case "Centimetros":
					transformado = valorTransformar * 10f;
					break;
				case "Metros":
					transformado = valorTransformar * 1000f;
					break;
			}
		}
		
		if(this.getNombre().equalsIgnoreCase("Centimetros"))
		{
			switch(comparador)
			{
				case "Pulgadas":
					transformado = valorTransformar * 2.54f;
					break;
				case "Pies":
					transformado = valorTransformar * 185.93f;
					break;
				case "Yardas":
					transformado = valorTransformar * 91.44f;
					break;
				case "Milimetros":
					transformado = valorTransformar * 0.1f;
					break;
				case "Metros":
					transformado = valorTransformar * 100f;
					break;
			}
		}
		
		if(this.getNombre().equalsIgnoreCase("Metros"))
		{
			switch(comparador)
			{
				case "Pulgadas":
					transformado = valorTransformar * 0.0254f;
					break;
				case "Pies":
					transformado = valorTransformar * 1.86f;
					break;
				case "Yardas":
					transformado = valorTransformar * 0.914f;
					break;
				case "Milimetros":
					transformado = valorTransformar * 0.001f;
					break;
				case "Centimetros":
					transformado = valorTransformar * 0.01f;
					break;
			}
		}
		
		return transformado;
	}
	
	 
}
