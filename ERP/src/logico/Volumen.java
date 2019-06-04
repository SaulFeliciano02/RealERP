package logico;

public class Volumen extends UnidadMedida {

	public Volumen(String nombre, String abreviatura) {
		super(nombre, abreviatura);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public float Conversion(String comparador, float valorTransformar) {
		
		float transformado = 0;
		
		if(this.getNombre().equalsIgnoreCase("Pulgadas Cb"))
		{
			switch(comparador)
			{
				case "Pies  Cb":
					transformado = valorTransformar * 1728f;
					break;
				case "Yardas Cb":
					transformado = valorTransformar * 46656f;
					break;
				case "Cuchara de té":
					transformado = valorTransformar * 0.30078125f;
					break;
				case "Cuchara de madera":
					transformado = valorTransformar * 0.90234375f;
					break;
				case "Onza fluida":
					transformado = valorTransformar * 1.8046875f;
					break;
				case "Taza":
					transformado = valorTransformar * 14.4375f;
					break;
				case "Medio litro":
					transformado = valorTransformar * 28.875f;
					break;
				case "Cuarto de galón":
					transformado = valorTransformar * 57.75f;
					break;
				case "Galón":
					transformado = valorTransformar * 231f;
					break;
				case "Barril":
					transformado = valorTransformar * 9702f;
					break;
				case "Milímetros cb":
					transformado = valorTransformar * 0.000061f;
					break;
				case "Centímetros cb":
					transformado = valorTransformar * 0.061023744f;
					break;
				case "Metros cb":
					transformado = valorTransformar * 61023.74409f;
					break;
				case "Mililitros":
					transformado = valorTransformar * 0.061023744f;
					break;
				case "Litros":
					transformado = valorTransformar * 61.02374409f;
					break;
			}
		}
		
		if(this.getNombre().equalsIgnoreCase("Pies  Cb"))
		{
			switch(comparador)
			{
				case "Pulgadas Cb":
					transformado = valorTransformar * 0.000578704f;
					break;
				case "Yardas Cb":
					transformado = valorTransformar * 27f;
					break;
				case "Cuchara de té":
					transformado = valorTransformar * 0.000174063f;
					break;
				case "Cuchara de madera":
					transformado = valorTransformar * 0.00052219f;
					break;
				case "Onza fluida":
					transformado = valorTransformar * 0.001044379f;
					break;
				case "Taza":
					transformado = valorTransformar * 0.008355035f;
					break;
				case "Medio litro":
					transformado = valorTransformar * 0.016710069f;
					break;
				case "Cuarto de galón":
					transformado = valorTransformar * 0.033420139f;
					break;
				case "Galón":
					transformado = valorTransformar * 0.133680556f;
					break;
				case "Barril":
					transformado = valorTransformar * 5.614583333f;
					break;
				case "Milímetros cb":
					transformado = valorTransformar * 0.00000035f;
					break;
				case "Centímetros cb":
					transformado = valorTransformar * 0.000035f;
					break;
				case "Metros cb":
					transformado = valorTransformar * 35.31466672f;
					break;
				case "Mililitros":
					transformado = valorTransformar * 0.000035f;
					break;
				case "Litros":
					transformado = valorTransformar * 0.035f;
					break;
			}
		}
		
		if(this.getNombre().equalsIgnoreCase("Yardas Cb"))
		{
			switch(comparador)
			{
				case "Pulgadas Cb":
					transformado = valorTransformar * 0.000021f;
					break;
				case "Pies  Cb":
					transformado = valorTransformar * 0.037037037f;
					break;
				case "Cuchara de té":
					transformado = valorTransformar * 0.0000064f;
					break;
				case "Cuchara de madera":
					transformado = valorTransformar * 0.000019f;
					break;
				case "Onza fluida":
					transformado = valorTransformar * 0.000039f;
					break;
				case "Taza":
					transformado = valorTransformar * 0.000309446f;
					break;
				case "Medio litro":
					transformado = valorTransformar * 0.000618891f;
					break;
				case "Cuarto de galón":
					transformado = valorTransformar * 0.001237783f;
					break;
				case "Galón":
					transformado = valorTransformar * 0.004951132f;
					break;
				case "Barril":
					transformado = valorTransformar * 0.207947531f;
					break;
				case "Milímetros cb":
					transformado = valorTransformar * 0.0000000013f;
					break;
				case "Centímetros cb":
					transformado = valorTransformar * 0.0000013f;
					break;
				case "Metros cb":
					transformado = valorTransformar * 1.307950619f;
					break;
				case "Mililitros":
					transformado = valorTransformar * 0.0000013f;
					break;
				case "Litros":
					transformado = valorTransformar * 0.001307951f;
					break;
			}
		}
		
		if(this.getNombre().equalsIgnoreCase("Cuchara de té"))
		{
			switch(comparador)
			{
				case "Pulgadas Cb":
					transformado = valorTransformar * 3.324675325f;
					break;
				case "Pies  Cb":
					transformado = valorTransformar * 5745.038961f;
					break;
				case "Yardas Cb":
					transformado = valorTransformar * 155116.0519f;
					break;
				case "Cuchara de madera":
					transformado = valorTransformar * 3f;
					break;
				case "Onza fluida":
					transformado = valorTransformar * 6f;
					break;
				case "Taza":
					transformado = valorTransformar * 48f;
					break;
				case "Medio litro":
					transformado = valorTransformar * 96f;
					break;
				case "Cuarto de galón":
					transformado = valorTransformar * 192f;
					break;
				case "Galón":
					transformado = valorTransformar * 768f;
					break;
				case "Barril":
					transformado = valorTransformar * 32256f;
					break;
				case "Milímetros cb":
					transformado = valorTransformar * 0.000202884f;
					break;
				case "Centímetros cb":
					transformado = valorTransformar * 0.202884136f;
					break;
				case "Metros cb":
					transformado = valorTransformar * 202884.1362f;
					break;
				case "Mililitros":
					transformado = valorTransformar * 0.202884136f;
					break;
				case "Litros":
					transformado = valorTransformar * 202.8841362f;
					break;
			}
		}
		
		if(this.getNombre().equalsIgnoreCase("Cuchara de madera"))
		{
			switch(comparador)
			{
				case "Pulgadas Cb":
					transformado = valorTransformar * 1.108225108f;
					break;
				case "Pies  Cb":
					transformado = valorTransformar * 1915.012987f;
					break;
				case "Yardas Cb":
					transformado = valorTransformar * 51705.35065f;
					break;
				case "Cuchara de té":
					transformado = valorTransformar * 0.333333333f;
					break;
				case "Onza fluida":
					transformado = valorTransformar * 2f;
					break;
				case "Taza":
					transformado = valorTransformar * 16f;
					break;
				case "Medio litro":
					transformado = valorTransformar * 32f;
					break;
				case "Cuarto de galón":
					transformado = valorTransformar * 64f;
					break;
				case "Galón":
					transformado = valorTransformar * 256f;
					break;
				case "Barril":
					transformado = valorTransformar * 10752f;
					break;
				case "Milímetros cb":
					transformado = valorTransformar * 0.000068f;
					break;
				case "Centímetros cb":
					transformado = valorTransformar * 0.067628045f;
					break;
				case "Metros cb":
					transformado = valorTransformar * 67628.0454f;
					break;
				case "Mililitros":
					transformado = valorTransformar * 0.067628045f;
					break;
				case "Litros":
					transformado = valorTransformar * 67.6280454f;
					break;
			}
		}
		
		if(this.getNombre().equalsIgnoreCase("Onza fluida"))
		{
			switch(comparador)
			{
				case "Pulgadas Cb":
					transformado = valorTransformar * 0.554112554f;
					break;
				case "Pies  Cb":
					transformado = valorTransformar * 957.5064935f;
					break;
				case "Yardas Cb":
					transformado = valorTransformar * 25852.67532f;
					break;
				case "Cuchara de té":
					transformado = valorTransformar * 0.166666667f;
					break;
				case "Cuchara de madera":
					transformado = valorTransformar * 0.5f;
					break;
				case "Taza":
					transformado = valorTransformar * 8f;
					break;
				case "Medio litro":
					transformado = valorTransformar * 16f;
					break;
				case "Cuarto de galón":
					transformado = valorTransformar * 32f;
					break;
				case "Galón":
					transformado = valorTransformar * 128f;
					break;
				case "Barril":
					transformado = valorTransformar * 5376f;
					break;
				case "Milímetros cb":
					transformado = valorTransformar * 0.000034f;
					break;
				case "Centímetros cb":
					transformado = valorTransformar * 0.033814023f;
					break;
				case "Metros cb":
					transformado = valorTransformar * 33814.0227f;
					break;
				case "Mililitros":
					transformado = valorTransformar * 0.033814023f;
					break;
				case "Litros":
					transformado = valorTransformar * 33.8140227f;
					break;
			}
		}
		
		if(this.getNombre().equalsIgnoreCase("Taza"))
		{
			switch(comparador)
			{
				case "Pulgadas Cb":
					transformado = valorTransformar * 0.069264069f;
					break;
				case "Pies  Cb":
					transformado = valorTransformar * 119.6883117f;
					break;
				case "Yardas Cb":
					transformado = valorTransformar * 3231.584416f;
					break;
				case "Cuchara de té":
					transformado = valorTransformar * 0.020833333f;
					break;
				case "Cuchara de madera":
					transformado = valorTransformar * 0.0625f;
					break;
				case "Onza fluida":
					transformado = valorTransformar * 0.125f;
					break;
				case "Medio litro":
					transformado = valorTransformar * 2f;
					break;
				case "Cuarto de galón":
					transformado = valorTransformar * 4f;
					break;
				case "Galón":
					transformado = valorTransformar * 16f;
					break;
				case "Barril":
					transformado = valorTransformar * 672f;
					break;
				case "Milímetros cb":
					transformado = valorTransformar * 0.0000042f;
					break;
				case "Centímetros cb":
					transformado = valorTransformar * 0.004226753f;
					break;
				case "Metros cb":
					transformado = valorTransformar * 4226.752838f;
					break;
				case "Mililitros":
					transformado = valorTransformar * 0.004226753f;
					break;
				case "Litros":
					transformado = valorTransformar * 4.226752838f;
					break;
			}
		}
		
		if(this.getNombre().equalsIgnoreCase("Medio litro"))
		{
			switch(comparador)
			{
				case "Pulgadas Cb":
					transformado = valorTransformar * 0.034632035f;
					break;
				case "Pies  Cb":
					transformado = valorTransformar * 59.84415584f;
					break;
				case "Yardas Cb":
					transformado = valorTransformar * 1615.792208f;
					break;
				case "Cuchara de té":
					transformado = valorTransformar * 0.010416667f;
					break;
				case "Cuchara de madera":
					transformado = valorTransformar * 0.03125f;
					break;
				case "Onza fluida":
					transformado = valorTransformar * 0.0625f;
					break;
				case "Taza":
					transformado = valorTransformar * 0.5f;
					break;
				case "Cuarto de galón":
					transformado = valorTransformar * 2f;
					break;
				case "Galón":
					transformado = valorTransformar * 8f;
					break;
				case "Barril":
					transformado = valorTransformar * 336f;
					break;
				case "Milímetros cb":
					transformado = valorTransformar * 0.0000021f;
					break;
				case "Centímetros cb":
					transformado = valorTransformar * 0.002113376f;
					break;
				case "Metros cb":
					transformado = valorTransformar * 2113.376419f;
					break;
				case "Mililitros":
					transformado = valorTransformar * 0.002113376f;
					break;
				case "Litros":
					transformado = valorTransformar * 2.113376419f;
					break;
			}
		}
		
		if(this.getNombre().equalsIgnoreCase("Cuarto de galón"))
		{
			switch(comparador)
			{
				case "Pulgadas Cb":
					transformado = valorTransformar * 0.017316017f;
					break;
				case "Pies  Cb":
					transformado = valorTransformar * 29.92207792f;
					break;
				case "Yardas Cb":
					transformado = valorTransformar * 807.8961039f;
					break;
				case "Cuchara de té":
					transformado = valorTransformar * 0.005208333f;
					break;
				case "Cuchara de madera":
					transformado = valorTransformar * 0.015625f;
					break;
				case "Onza fluida":
					transformado = valorTransformar * 0.03125f;
					break;
				case "Taza":
					transformado = valorTransformar * 0.25f;
					break;
				case "Medio litro":
					transformado = valorTransformar * 0.5f;
					break;
				case "Galón":
					transformado = valorTransformar * 4f;
					break;
				case "Barril":
					transformado = valorTransformar * 168f;
					break;
				case "Milímetros cb":
					transformado = valorTransformar * 0.0000011f;
					break;
				case "Centímetros cb":
					transformado = valorTransformar * 0.001056688f;
					break;
				case "Metros cb":
					transformado = valorTransformar * 1056.688209f;
					break;
				case "Mililitros":
					transformado = valorTransformar * 0.001056688f;
					break;
				case "Litros":
					transformado = valorTransformar * 1.056688209f;
					break;
			}
		}
		
		if(this.getNombre().equalsIgnoreCase("Galón"))
		{
			switch(comparador)
			{
				case "Pulgadas Cb":
					transformado = valorTransformar * 0.004329004f;
					break;
				case "Pies  Cb":
					transformado = valorTransformar * 7.480519481f;
					break;
				case "Yardas Cb":
					transformado = valorTransformar * 201.974026f;
					break;
				case "Cuchara de té":
					transformado = valorTransformar * 0.001302083f;
					break;
				case "Cuchara de madera":
					transformado = valorTransformar * 0.00390625f;
					break;
				case "Onza fluida":
					transformado = valorTransformar * 0.0078125f;
					break;
				case "Taza":
					transformado = valorTransformar * 0.0625f;
					break;
				case "Medio litro":
					transformado = valorTransformar * 0.125f;
					break;
				case "Cuarto de galón":
					transformado = valorTransformar * 0.25f;
					break;
				case "Barril":
					transformado = valorTransformar * 42f;
					break;
				case "Milímetros cb":
					transformado = valorTransformar * 0.00000026f;
					break;
				case "Centímetros cb":
					transformado = valorTransformar * 0.000264172f;
					break;
				case "Metros cb":
					transformado = valorTransformar * 264.1720524f;
					break;
				case "Mililitros":
					transformado = valorTransformar * 0.000264172f;
					break;
				case "Litros":
					transformado = valorTransformar * 0.264172052f;
					break;
			}
		}
		
		if(this.getNombre().equalsIgnoreCase("Barril"))
		{
			switch(comparador)
			{
				case "Pulgadas Cb":
					transformado = valorTransformar * 0.000103072f;
					break;
				case "Pies  Cb":
					transformado = valorTransformar * 0.178107607f;
					break;
				case "Yardas Cb":
					transformado = valorTransformar * 4.80890538f;
					break;
				case "Cuchara de té":
					transformado = valorTransformar * 0.000031f;
					break;
				case "Cuchara de madera":
					transformado = valorTransformar * 0.000093f;
					break;
				case "Onza fluida":
					transformado = valorTransformar * 0.000186012f;
					break;
				case "Taza":
					transformado = valorTransformar * 0.001488095f;
					break;
				case "Medio litro":
					transformado = valorTransformar * 0.00297619f;
					break;
				case "Cuarto de galón":
					transformado = valorTransformar * 0.005952381f;
					break;
				case "Galón":
					transformado = valorTransformar * 0.023809524f;
					break;
				case "Milímetros cb":
					transformado = valorTransformar * 0.0000000063f;
					break;
				case "Centímetros cb":
					transformado = valorTransformar * 0.0000063f;
					break;
				case "Metros cb":
					transformado = valorTransformar * 6.28981077f;
					break;
				case "Mililitros":
					transformado = valorTransformar * 0.0000063f;
					break;
				case "Litros":
					transformado = valorTransformar * 0.006289811f;
					break;
			}
		}
		
		if(this.getNombre().equalsIgnoreCase("Milímetros cb"))
		{
			switch(comparador)
			{
				case "Pulgadas Cb":
					transformado = valorTransformar * 16387.064f;
					break;
				case "Pies  Cb":
					transformado = valorTransformar * 28316846.59f;
					break;
				case "Yardas Cb":
					transformado = valorTransformar * 764554858f;
					break;
				case "Cuchara de té":
					transformado = valorTransformar * 4928.921594f;
					break;
				case "Cuchara de madera":
					transformado = valorTransformar * 14786.76478f;
					break;
				case "Onza fluida":
					transformado = valorTransformar * 29573.52956f;
					break;
				case "Taza":
					transformado = valorTransformar * 236588.2365f;
					break;
				case "Medio litro":
					transformado = valorTransformar * 473176.473f;
					break;
				case "Cuarto de galón":
					transformado = valorTransformar * 946352.946f;
					break;
				case "Galón":
					transformado = valorTransformar * 3785411.784f;
					break;
				case "Barril":
					transformado = valorTransformar * 158987294.9f;
					break;
				case "Centímetros cb":
					transformado = valorTransformar * 1000f;
					break;
				case "Metros cb":
					transformado = valorTransformar * 1000000000f;
					break;
				case "Mililitros":
					transformado = valorTransformar * 1000f;
					break;
				case "Litros":
					transformado = valorTransformar * 1000000f;
					break;
			}
		}
		
		if(this.getNombre().equalsIgnoreCase("Centímetros cb"))
		{
			switch(comparador)
			{
				case "Pulgadas Cb":
					transformado = valorTransformar * 16.387064f;
					break;
				case "Pies  Cb":
					transformado = valorTransformar * 28316.84659f;
					break;
				case "Yardas Cb":
					transformado = valorTransformar * 764554.858f;
					break;
				case "Cuchara de té":
					transformado = valorTransformar * 4.928921594f;
					break;
				case "Cuchara de madera":
					transformado = valorTransformar * 14.78676478f;
					break;
				case "Onza fluida":
					transformado = valorTransformar * 29.57352956f;
					break;
				case "Taza":
					transformado = valorTransformar * 236.5882365f;
					break;
				case "Medio litro":
					transformado = valorTransformar * 473.176473f;
					break;
				case "Cuarto de galón":
					transformado = valorTransformar * 946.352946f;
					break;
				case "Galón":
					transformado = valorTransformar * 3785.411784f;
					break;
				case "Barril":
					transformado = valorTransformar * 158987.2949f;
					break;
				case "Milímetros cb":
					transformado = valorTransformar * 0.001f;
					break;
				case "Metros cb":
					transformado = valorTransformar * 1000000f;
					break;
				case "Mililitros":
					transformado = valorTransformar * 1f;
					break;
				case "Litros":
					transformado = valorTransformar * 1000f;
					break;
			}
		}
		
		if(this.getNombre().equalsIgnoreCase("Metros cb"))
		{
			switch(comparador)
			{
				case "Pulgadas Cb":
					transformado = valorTransformar * 0.000016f;
					break;
				case "Pies  Cb":
					transformado = valorTransformar * 0.028316847f;
					break;
				case "Yardas Cb":
					transformado = valorTransformar * 0.764554858f;
					break;
				case "Cuchara de té":
					transformado = valorTransformar * 0.0000049f;
					break;
				case "Cuchara de madera":
					transformado = valorTransformar * 0.000015f;
					break;
				case "Onza fluida":
					transformado = valorTransformar * 0.000030f;
					break;
				case "Taza":
					transformado = valorTransformar * 0.000236588f;
					break;
				case "Medio litro":
					transformado = valorTransformar * 0.000473176f;
					break;
				case "Cuarto de galón":
					transformado = valorTransformar * 0.000946353f;
					break;
				case "Galón":
					transformado = valorTransformar * 0.003785412f;
					break;
				case "Barril":
					transformado = valorTransformar * 0.158987295f;
					break;
				case "Milímetros cb":
					transformado = valorTransformar * 0.000000001f;
					break;
				case "Centímetros cb":
					transformado = valorTransformar * 0.000001f;
					break;
				case "Mililitros":
					transformado = valorTransformar * 0.000001f;
					break;
				case "Litros":
					transformado = valorTransformar * 0.001f;
					break;
			}
		}
		
		if(this.getNombre().equalsIgnoreCase("Mililitros"))
		{
			switch(comparador)
			{
				case "Pulgadas Cb":
					transformado = valorTransformar * 16.387064f;
					break;
				case "Pies  Cb":
					transformado = valorTransformar * 28316.84659f;
					break;
				case "Yardas Cb":
					transformado = valorTransformar * 764554.858f;
					break;
				case "Cuchara de té":
					transformado = valorTransformar * 4.928921594f;
					break;
				case "Cuchara de madera":
					transformado = valorTransformar * 14.78676478f;
					break;
				case "Onza fluida":
					transformado = valorTransformar * 29.57352956f;
					break;
				case "Taza":
					transformado = valorTransformar * 236.5882365f;
					break;
				case "Medio litro":
					transformado = valorTransformar * 473.176473f;
					break;
				case "Cuarto de galón":
					transformado = valorTransformar * 946.352946f;
					break;
				case "Galón":
					transformado = valorTransformar * 3785.411784f;
					break;
				case "Barril":
					transformado = valorTransformar * 158987.2949f;
					break;
				case "Milímetros cb":
					transformado = valorTransformar * 0.001f;
					break;
				case "Centímetros cb":
					transformado = valorTransformar * 1f;
					break;
				case "Metros cb":
					transformado = valorTransformar * 1000000f;
					break;
				case "Litros":
					transformado = valorTransformar * 1000f;
					break;
			}
		}
		
		if(this.getNombre().equalsIgnoreCase("Litros"))
		{
			switch(comparador)
			{
				case "Pulgadas Cb":
					transformado = valorTransformar * 0.016387064f;
					break;
				case "Pies  Cb":
					transformado = valorTransformar * 28.31684659f;
					break;
				case "Yardas Cb":
					transformado = valorTransformar * 764.554858f;
					break;
				case "Cuchara de té":
					transformado = valorTransformar * 0.004928922f;
					break;
				case "Cuchara de madera":
					transformado = valorTransformar * 0.014786765f;
					break;
				case "Onza fluida":
					transformado = valorTransformar * 0.02957353f;
					break;
				case "Taza":
					transformado = valorTransformar * 0.236588237f;
					break;
				case "Medio litro":
					transformado = valorTransformar * 0.473176473f;
					break;
				case "Cuarto de galón":
					transformado = valorTransformar * 0.946352946f;
					break;
				case "Galón":
					transformado = valorTransformar * 3.785411784f;
					break;
				case "Barril":
					transformado = valorTransformar * 158.9872949f;
					break;
				case "Milímetros cb":
					transformado = valorTransformar * 0.000001f;
					break;
				case "Centímetros cb":
					transformado = valorTransformar * 0.001f;
					break;
				case "Metros cb":
					transformado = valorTransformar * 1000f;
					break;
				case "Mililitros":
					transformado = valorTransformar * 0.001f;
					break;
			}
		}
		
		return transformado;
	}
	
}
