package archivos;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.List;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

import logico.CantKitsUtilizados;
import logico.CantProductosUtilizados;
import logico.Controladora;
import logico.Factura;
import logico.ServicioUtilizado;

public class FacturaDeConsumo {

	private static String file;
	
	public static void CrearFactura(Factura factura)
	{
		Archivos.carpeta();
		file = "c:/ERPdata/data/facturasdeconsumo/FDC" + Controladora.getInstance().getMisFacturas().size() + factura.getFecha().format(DateTimeFormatter.BASIC_ISO_DATE) + ".pdf"; //DateTimeFormatter.BASIC_ISO_DATE es un formato de fecha sin gui�n
		Document document = new Document();
		PdfWriter writer = null;

		try {
			writer = PdfWriter.getInstance(document, new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			System.out.println("Error al crear el pdf");
			e.printStackTrace();
		} catch (DocumentException e) {
			System.out.println("Error al crear el pdf");
			e.printStackTrace();
		}
		
		document.open();
		
		Paragraph p, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p19, p20, p21, p22, p23, p24;
		ColumnText ct = new ColumnText(writer.getDirectContent());
		ColumnText ct2 = new ColumnText(writer.getDirectContent());
		List list, list2;
		float itbis = 0;
		float subtotal = 0;
		float itbistotal = 0;
		float descuentototal = 0;
		try {
			
			Chunk glue = new Chunk(new VerticalPositionMark());
			p = new Paragraph("" + Controladora.getInstance().getMiEmpresa().getNombre());
			p.add(new Chunk(glue));
			p.add("Factura de Consumo");
			//p.setFont((Font) FontConstants.Bold);
			document.add(p);
			
			p2 = new Paragraph("Domicilio: " + Controladora.getInstance().getMiEmpresa().getDomicilio());
			p2.add(new Chunk(glue));
			if (String.valueOf(Controladora.getInstance().getMisFacturas().size()).length() < 8)
			{
				int digitos = String.valueOf(Controladora.getInstance().getMisFacturas().size()).length();
				int i;
				String ceros = "";
				for(i = digitos; i < 8; i++)
				{
					ceros += "0";
				}
				
				p2.add("NCF: B02" + ceros + Controladora.getInstance().getMisFacturas().size());
				//factura.setCodigo("B02" + ceros + Controladora.getInstance().getMisFacturas().size());
			}
			else
			{
				p2.add("NCF: B02" + Controladora.getInstance().getMisFacturas().size());
				//factura.setCodigo("B02" + Controladora.getInstance().getMisFacturas().size());
			}
			
			document.add(p2);
			
			p3 = new Paragraph("RNC: " + Controladora.getInstance().getMiEmpresa().getRnc());
			document.add(p3);
			
			p4 = new Paragraph("Fecha: " + LocalDate.now());
			document.add(p4);
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
			p24 = new Paragraph("Hora: " + factura.getHora().format(dtf));
			document.add(p24);
	        
	        p6 = new Paragraph("                                                                  ");
	        document.add(p6);
	        
	        p7 = new Paragraph("                                                                  ");
	        document.add(p7);
	        
	        p19 = new Paragraph("----------------------------------------------------------------------------------------------------------------------------");
	        p20 = new Paragraph("													INFORMACI�N DE LA FACTURA												");
	        p21 = new Paragraph("----------------------------------------------------------------------------------------------------------------------------");
	        p23 = new Paragraph("                                                                              											     ");
	        document.add(p19);
	        document.add(p20);
	        document.add(p21);
	        document.add(p23);
	        
	        PdfPTable table = new PdfPTable(4); // 4 columns.

            PdfPCell cell1 = new PdfPCell(new Paragraph("CANT."));
            PdfPCell cell2 = new PdfPCell(new Paragraph("DESCRIPCI�N"));
            PdfPCell cell3 = new PdfPCell(new Paragraph("ITBIS"));
            PdfPCell cell4 = new PdfPCell(new Paragraph("VALOR"));
            
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            
            for (CantProductosUtilizados estandar : factura.getProdFacturados()) {
            	if(estandar.getUnidad() != null)
            	{
            		PdfPCell cellDinamic = new PdfPCell(new Paragraph(estandar.getCantidad() + " " + estandar.getUnidad()));
	            	table.addCell(cellDinamic);
            	}
            	else
            	{
            		PdfPCell cellDinamic = new PdfPCell(new Paragraph(estandar.getCantidad()));
	            	table.addCell(cellDinamic);
            	}
            	
            	PdfPCell cellDinamic2 = new PdfPCell(new Paragraph(estandar.getNombre()));
            	table.addCell(cellDinamic2);
            	DecimalFormat format = new DecimalFormat("##.00");
            	itbis = estandar.getProducto().getCostoitbis();
            	PdfPCell cellDinamic3 = new PdfPCell(new Paragraph("" + format.format(itbis)));
            	table.addCell(cellDinamic3);
            	itbistotal += itbis;
            	PdfPCell cellDinamic4 = new PdfPCell(new Paragraph("" + estandar.getValor()));
            	table.addCell(cellDinamic4);
            	subtotal+=estandar.getValor();
			}

            for (CantKitsUtilizados kit : factura.getKitFacturados()) {
            	PdfPCell cellDinamic = new PdfPCell(new Paragraph(kit.getCantidad()));
            	table.addCell(cellDinamic);
            	PdfPCell cellDinamic2 = new PdfPCell(new Paragraph(kit.getNombre()));
            	table.addCell(cellDinamic2);
            	DecimalFormat format = new DecimalFormat("##.00");
            	itbis = kit.getKit().getCostoitbis();
            	PdfPCell cellDinamic3 = new PdfPCell(new Paragraph("" + format.format(itbis)));
            	table.addCell(cellDinamic3);
            	itbistotal += itbis;
            	PdfPCell cellDinamic4 = new PdfPCell(new Paragraph("" + kit.getValor()));
            	table.addCell(cellDinamic4);
            	subtotal+=kit.getValor();
			}
            
            for (ServicioUtilizado serv : factura.getServiciosFacturados()) {
            	PdfPCell cellDinamic = new PdfPCell(new Paragraph(""));
            	table.addCell(cellDinamic);
            	PdfPCell cellDinamic2 = new PdfPCell(new Paragraph(serv.getNombre()));
            	table.addCell(cellDinamic2);
            	DecimalFormat format = new DecimalFormat("##.00");
            	itbis = serv.getServicio().getCostoitbis();
            	PdfPCell cellDinamic3 = new PdfPCell(new Paragraph("" + format.format(itbis)));
            	table.addCell(cellDinamic3);
            	itbistotal += itbis;
            	PdfPCell cellDinamic4 = new PdfPCell(new Paragraph("" + serv.getValor()));
            	table.addCell(cellDinamic4);
            	subtotal+=serv.getValor();
			}

            document.add(table);
	        
            p8 = new Paragraph();
            p8.add(new Chunk(glue));
            p8.add("SUBTOTAL:  " + (subtotal - itbistotal));
            document.add(p8);
            p9 = new Paragraph();
            p9.add(new Chunk(glue));
            p9.add("DESC.:  0");
            document.add(p9);
            p10 = new Paragraph();
            p10.add(new Chunk(glue));
            DecimalFormat format = new DecimalFormat("##.00");
            p10.add("ITBIS: " + format.format(itbistotal));
            document.add(p10);
            p11 = new Paragraph();
            p11.add(new Chunk(glue));
            p11.add("TOTAL:  " + Math.round(subtotal));
            document.add(p11);
            p22 = new Paragraph();
            p22.add(new Chunk(glue));
            p22.add("Atendido por: " + factura.getNombreUsuarioFact());
            document.add(p22);
            if(factura.getCantcopias() > 1)
            {
            	Paragraph p12 = new Paragraph();
                p12.add(new Chunk(glue));
                p12.add("");
                document.add(p12);
                Paragraph p13 = new Paragraph();
                p13.add(new Chunk(glue));
                p13.add("");
                document.add(p13);
                Paragraph p14 = new Paragraph();
                p14.add(new Chunk(glue));
                p14.add("");
                document.add(p14);
                Paragraph p15 = new Paragraph();
                p15.add(new Chunk(glue));
                p15.add("");
                document.add(p15);
                Paragraph p16 = new Paragraph();
                p16.add(new Chunk(glue));
                p16.add("Copias: ");
                document.add(p16);
                Paragraph p17 = new Paragraph();
                p17.add(new Chunk(glue));
                p17.add("Original: cliente");
                document.add(p17);
                Paragraph p18 = new Paragraph();
                p18.add(new Chunk(glue));
                p18.add("Copia: vendedor");
                document.add(p18);
            }


	        document.close();
	        writer.close();
	        
	        openFile(file);
	        
	        
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void openFile(String file)
	{
		 Desktop desktop = Desktop.getDesktop();
	        if(!Desktop.isDesktopSupported()){
	            System.out.println("Desktop is not supported");
	            return;
	        }
	        
	     File fileToOpen = new File(file);
	     
	     if(fileToOpen.exists())
	     {
	    	 try {
				desktop.open(fileToOpen);
			} catch (IOException e) {
				System.out.println("Error al abrir el pdf");
				e.printStackTrace();
			} 
	     }  	 
	}
}
