package logico;

public class Area extends UnidadMedida {

	public Area(String categoria, String nombre, String abreviatura) {
		super(categoria, nombre, abreviatura);
		// TODO Auto-generated constructor stub
	}

	@Override
	public float Conversion(String comparador, float valorTransformar) {
		
		float transformado = 0;
		
		if(this.getNombre().equalsIgnoreCase("Sq Pulgadas"))
		{
			switch(comparador)
			{
				case "Sq Pulgadas":
					transformado = valorTransformar;
					break;
				case "Sq Pies":
					transformado = valorTransformar * 0.0069f;
					break;
				case "Sq Yardas":
					transformado = valorTransformar * 0.00077f;
					break;
				case "Sq Milimetros":
					transformado = valorTransformar * 645.16f;
					break;
				case "Sq Centimetros":
					transformado = valorTransformar * 6.45f;
					break;
				case "Sq Metros":
					transformado = valorTransformar * 0.00065f;
					break;
			}
		}
		
		if(this.getNombre().equalsIgnoreCase("Sq Pies"))
		{
			switch(comparador)
			{	
				case "Sq Pies":
					transformado = valorTransformar;
					break;
				case "Sq Pulgadas":
					transformado = valorTransformar * 144f;
					break;
				case "Sq Yardas":
					transformado = valorTransformar * 0.11f;
					break;
				case "Sq Milimetros":
					transformado = valorTransformar * 92903.04f;
					break;
				case "Sq Centimetros":
					transformado = valorTransformar * 929.03f;
					break;
				case "Sq Metros":
					transformado = valorTransformar * 0.093f;
					break;
			}
		}
		
		if(this.getNombre().equalsIgnoreCase("Sq Yardas"))
		{
			switch(comparador)
			{
				case "Sq Yardas":
					transformado = valorTransformar;
					break;
				case "Sq Pulgadas":
					transformado = valorTransformar * 1296f;
					break;
				case "Sq Pies":
					transformado = valorTransformar * 9f;
					break;
				case "Sq Milimetros":
					transformado = valorTransformar * 836127.36f;
					break;
				case "Sq Centimetros":
					transformado = valorTransformar * 8361.27f;
					break;
				case "Sq Metros":
					transformado = valorTransformar * 0.84f;
					break;
			}
		}
		
		if(this.getNombre().equalsIgnoreCase("Sq Milimetros"))
		{
			switch(comparador)
			{
				case "Sq Milimetros":
					transformado = valorTransformar;
					break;
				case "Sq Pulgadas":
					transformado = valorTransformar * 0.0016f;
					break;
				case "Sq Pies":
					transformado = valorTransformar * 0.000011f;
					break;
				case "Sq Yardas":
					transformado = valorTransformar * 0.0000012f;
					break;
				case "Sq Centimetros":
					transformado = valorTransformar * 0.01f;
					break;
				case "Sq Metros":
					transformado = valorTransformar * 0.000001f;
					break;
			}
		}
		
		if(this.getNombre().equalsIgnoreCase("Sq Centimetros"))
		{
			switch(comparador)
			{
				case "Sq Centimetros":
					transformado = valorTransformar;
					break;
				case "Sq Pulgadas":
					transformado = valorTransformar * 0.16f;
					break;
				case "Sq Pies":
					transformado = valorTransformar * 0.0011f;
					break;
				case "Sq Yardas":
					transformado = valorTransformar * 0.00012f;
					break;
				case "Sq Milimetros":
					transformado = valorTransformar * 100f;
					break;
				case "Sq Metros":
					transformado = valorTransformar * 0.0001f;
					break;
			}
		}
		
		if(this.getNombre().equalsIgnoreCase("Sq Metros"))
		{
			switch(comparador)
			{
				case "Sq Metros":
					transformado = valorTransformar;
					break;
				case "Sq Pulgadas":
					transformado = valorTransformar * 1550.0031f;
					break;
				case "Sq Pies":
					transformado = valorTransformar * 10.76f;
					break;
				case "Sq Yardas":
					transformado = valorTransformar * 1.20f;
					break;
				case "Sq Milimetros":
					transformado = valorTransformar * 1000000f;
					break;
				case "Sq Centimetros":
					transformado = valorTransformar * 10000f;
					break;
			}
		}
		
		return transformado;
	}
}
