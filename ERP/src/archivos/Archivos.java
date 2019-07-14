package archivos;

import java.io.File;

public class Archivos{

    public static void carpeta(){

	//La carpeta1 se creara en la ruta designada
        File Carpeta1= new File("c:\\ERPdata");

	//La carpeta 2 se creara dentro de la carpeta 1
        File Carpeta2= new File("c:\\ERPdata\\data");
       
    //La carpeta 3 se creara dentro de la carpeta 2
        File Carpeta3= new File("c:\\ERPdata\\data\\inventario");
    
    //La carpeta 4 se creara dentro de la carpeta 2
        File Carpeta4= new File("c:\\ERPdata\\data\\facturascreditofiscal");
        
    //La carpeta 5 se creara dentro de la carpeta 2
        File Carpeta5= new File("c:\\ERPdata\\data\\facturasdeconsumo");
        
       if(Carpeta1.exists()){
	}
	
	    if(!Carpeta1.exists()){
            Carpeta1.mkdir();
            Carpeta2.mkdir();
            Carpeta3.mkdir();
            Carpeta4.mkdir();
            Carpeta5.mkdir();
            }
    }
}

