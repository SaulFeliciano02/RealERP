package logico;

public class Masa extends UnidadMedida {

	public Masa(String categoria, String nombre, String abreviatura) {
		super(categoria, nombre, abreviatura);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public float Conversion(String comparador, float valorTransformar) {
		
		float transformado = 0;
		
		if(this.getNombre().equalsIgnoreCase("Grano"))
		{
			switch(comparador)
			{
				case "Grano":
					transformado = valorTransformar;
					break;
				case "Onza":
					transformado = valorTransformar * 0.0023f;
					break;
				case "Libra":
					transformado = valorTransformar * 0.0014f;
					break;
				case "Miligramo":
					transformado = valorTransformar * 64.7989f;
					break;
				case "Gramo":
					transformado = valorTransformar * 0.065f;
					break;
				case "Kilogramo":
					transformado = valorTransformar * 0.000065f;
					break;
			}
		}
		
		if(this.getNombre().equalsIgnoreCase("Onza"))
		{
			switch(comparador)
			{
				case "Onza":
					transformado = valorTransformar;
					break;
				case "Grano":
					transformado = valorTransformar * 437.5f;
					break;
				case "Libra":
					transformado = valorTransformar * 0.0625f;
					break;
				case "Miligramo":
					transformado = valorTransformar * 28349.5f;
					break;
				case "Gramo":
					transformado = valorTransformar * 28.3f;
					break;
				case "Kilogramo":
					transformado = valorTransformar * 0.028f;
					break;
			}
		}
		
		if(this.getNombre().equalsIgnoreCase("Libra"))
		{
			switch(comparador)
			{
				case "Libra":
					transformado = valorTransformar;
					break;
				case "Grano":
					transformado = valorTransformar * 7000f;
					break;
				case "Onza":
					transformado = valorTransformar * 16f;
					break;
				case "Miligramo":
					transformado = valorTransformar * 453592f;
					break;
				case "Gramo":
					transformado = valorTransformar * 453.592f;
					break;
				case "Kilogramo":
					transformado = valorTransformar * 0.45f;
					break;
			}
		}
		
		if(this.getNombre().equalsIgnoreCase("Miligramo"))
		{
			switch(comparador)
			{
				case "Miligramo":
					transformado = valorTransformar;
					break;
				case "Grano":
					transformado = valorTransformar * 0.015f;
					break;
				case "Onza":
					transformado = valorTransformar * 0.000035f;
					break;
				case "Libra":
					transformado = valorTransformar * 0.0000022f;
					break;
				case "Gramo":
					transformado = valorTransformar * 0.001f;
					break;
				case "Kilogramo":
					transformado = valorTransformar * 0.000001f;
					break;
			}
		}
		
		if(this.getNombre().equalsIgnoreCase("Gramo"))
		{
			switch(comparador)
			{
				case "Gramo":
					transformado = valorTransformar;
					break;
				case "Grano":
					transformado = valorTransformar * 15.43f;
					break;
				case "Onza":
					transformado = valorTransformar * 0.035f;
					break;
				case "Libra":
					transformado = valorTransformar * 0.0022f;
					break;
				case "Miligramo":
					transformado = valorTransformar * 1000f;
					break;
				case "Kilogramo":
					transformado = valorTransformar * 0.001f;
					break;
			}
		}
		
		if(this.getNombre().equalsIgnoreCase("Kilogramo"))
		{
			switch(comparador)
			{
				case "Kilogramo":
					transformado = valorTransformar;
					break;
				case "Grano":
					transformado = valorTransformar * 15432.36f;
					break;
				case "Onza":
					transformado = valorTransformar * 35.3f;
					break;
				case "Libra":
					transformado = valorTransformar * 2.20f;
					break;
				case "Miligramo":
					transformado = valorTransformar * 100000f;
					break;
				case "Gramo":
					transformado = valorTransformar * 1000f;
					break;
			}
		}
		
		return transformado;
	}
	
}
