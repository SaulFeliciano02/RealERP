package archivos;

import java.io.File;

public class Archivos{

    public static void carpeta(){

	//La carpeta1 se creara en la ruta designada
        File Carpeta1= new File("c:\\ERPdata");

	//La carpeta 2 se creara dentro de la carpeta 1
        File Carpeta2= new File("c:\\ERPdata\\data");
       
        
       if(Carpeta1.exists()){
	}
	
	    if(!Carpeta1.exists()){
            Carpeta1.mkdir();
            Carpeta2.mkdir();
           
            }
    }
}

