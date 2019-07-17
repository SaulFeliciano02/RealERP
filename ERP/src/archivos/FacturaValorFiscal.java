package archivos;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.print.Doc;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.swing.text.StyleConstants.FontConstants;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
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

public class FacturaValorFiscal {
	
	private static String file;
	
	public static void CrearFactura(Factura factura)
	{
		Archivos.carpeta();
		file = "c:/ERPdata/data/facturascreditofiscal/FCF" + Controladora.getInstance().getMisFacturas().size() + factura.getFecha().format(DateTimeFormatter.BASIC_ISO_DATE) + ".pdf"; //DateTimeFormatter.BASIC_ISO_DATE es un formato de fecha sin guión
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
		
		Paragraph p, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11;
		ColumnText ct = new ColumnText(writer.getDirectContent());
		ColumnText ct2 = new ColumnText(writer.getDirectContent());
		List list, list2;
		float subtotal = 0;
		float itbistotal = 0;
		float descuentototal = 0;
		try {
		       /*	ct.setSimpleColumn(0,0,0,0);
		        p=new Paragraph();
		        list=new List();
		        list.add(new ListItem("Nombre de prueba para la empresa"));
		        list.add(new ListItem("Sucursal prueba"));
		        list.add(new ListItem("RNC: 123Prueba"));
		        list.add(new ListItem("Fecha: " + LocalDate.now()));
		        p.add(list);
		        ct.addElement(p);
		        ct.go();
		        ct2.setSimpleColumn(0,0,200,200);
		        p2=new Paragraph();
		        list=new List();
		        list.add(new ListItem("Factura de crédito fiscal"));
		        list.add(new ListItem("NCF: B0100000572"));
		        list.add(new ListItem("Vencimiento secuencia: " + LocalDate.of(LocalDate.now().getYear()+2, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth())));
		        p2.add(list);
		        ct2.addElement(p2);
		        ct2.go();*/
			
		        /*PdfPTable table = new PdfPTable(3);
		        table.setWidthPercentage(100);
		        table.addCell(getCell("Nombre de prueba para la empresa", PdfPCell.ALIGN_LEFT));
		        table.addCell(getCell("Sucursal prueba", PdfPCell.ALIGN_LEFT));
		        table.addCell(getCell("RNC: 123Prueba", PdfPCell.ALIGN_LEFT));
		        table.addCell(getCell("Fecha: " + LocalDate.now(), PdfPCell.ALIGN_LEFT));
		        table.addCell(getCell("Text in the middle", PdfPCell.ALIGN_CENTER));
		        table.addCell(getCell("Factura de crédito fiscal", PdfPCell.ALIGN_RIGHT));
		        table.addCell(getCell("NCF: B0100000572", PdfPCell.ALIGN_RIGHT));
		        table.addCell(getCell("Vencimiento secuencia: " + LocalDate.of(LocalDate.now().getYear()+2, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth()), PdfPCell.ALIGN_RIGHT));
		        document.add(table);*/
				
				Chunk glue = new Chunk(new VerticalPositionMark());
				p = new Paragraph("" + Controladora.getInstance().getMiEmpresa().getNombre());
				p.add(new Chunk(glue));
				p.add("Factura de Crédito Fiscal");
				//p.setFont((Font) FontConstants.Bold);
				document.add(p);
				
				p2 = new Paragraph("Domicilio: " + Controladora.getInstance().getMiEmpresa().getDomicilio());
				p2.add(new Chunk(glue));
				p2.add("NCF: B0100000572");
				document.add(p2);
				
				p3 = new Paragraph("RNC: " + Controladora.getInstance().getMiEmpresa().getRnc());
				p3.add(new Chunk(glue));
				p3.add("Vencimiento secuencia:");
				document.add(p3);
				
				p4 = new Paragraph("Fecha: " + LocalDate.now());
				p4.add(new Chunk(glue));
				p4.add("" + LocalDate.of(LocalDate.now().getYear()+2, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth()));
				document.add(p4);
				
				p5=new Paragraph();
		        list=new List();
		        list.add(new ListItem("RNC CLIENTE: 987654321"));
		        list.add(new ListItem("NOMBRE O RAZÓN SOCIAL:"));
		        list.add(new ListItem("NOMBRE DE PRUEBA EMPRESA CLIENTE"));
		        p5.add(list);
		        document.add(p5);
		        
		        p6 = new Paragraph("                                                                  ");
		        document.add(p6);
		        
		        p7 = new Paragraph("                                                                  ");
		        document.add(p7);
		        
		        PdfPTable table = new PdfPTable(4); // 4 columns.

	            PdfPCell cell1 = new PdfPCell(new Paragraph("CANT."));
	            PdfPCell cell2 = new PdfPCell(new Paragraph("DESCRIPCIÓN"));
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
	            	PdfPCell cellDinamic3 = new PdfPCell(new Paragraph("0"));
	            	table.addCell(cellDinamic3);
	            	PdfPCell cellDinamic4 = new PdfPCell(new Paragraph("" + estandar.getValor()));
	            	table.addCell(cellDinamic4);
	            	subtotal+=estandar.getValor();
				}

	            for (CantKitsUtilizados kit : factura.getKitFacturados()) {
	            	PdfPCell cellDinamic = new PdfPCell(new Paragraph(kit.getCantidad()));
	            	table.addCell(cellDinamic);
	            	PdfPCell cellDinamic2 = new PdfPCell(new Paragraph(kit.getNombre()));
	            	table.addCell(cellDinamic2);
	            	PdfPCell cellDinamic3 = new PdfPCell(new Paragraph("0"));
	            	table.addCell(cellDinamic3);
	            	PdfPCell cellDinamic4 = new PdfPCell(new Paragraph("" + kit.getValor()));
	            	table.addCell(cellDinamic4);
	            	subtotal+=kit.getValor();
				}
	            
	            for (ServicioUtilizado serv : factura.getServiciosFacturados()) {
	            	PdfPCell cellDinamic = new PdfPCell(new Paragraph(""));
	            	table.addCell(cellDinamic);
	            	PdfPCell cellDinamic2 = new PdfPCell(new Paragraph(serv.getNombre()));
	            	table.addCell(cellDinamic2);
	            	PdfPCell cellDinamic3 = new PdfPCell(new Paragraph("0"));
	            	table.addCell(cellDinamic3);
	            	PdfPCell cellDinamic4 = new PdfPCell(new Paragraph("" + serv.getValor()));
	            	table.addCell(cellDinamic4);
	            	subtotal+=serv.getValor();
				}

	            document.add(table);
		        
	            p8 = new Paragraph();
	            p8.add(new Chunk(glue));
	            p8.add("SUBTOTAL:  " + subtotal);
	            document.add(p8);
	            p9 = new Paragraph();
	            p9.add(new Chunk(glue));
	            p9.add("DESC.:  0");
	            document.add(p9);
	            p10 = new Paragraph();
	            p10.add(new Chunk(glue));
	            p10.add("ITBIS:  0");
	            document.add(p10);
	            p11 = new Paragraph();
	            p11.add(new Chunk(glue));
	            p11.add("TOTAL:  " + Math.round(subtotal + descuentototal + itbistotal));
	            document.add(p11);
	            
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
	
	/*public static PdfPCell getCell(String text, int alignment) {
	    PdfPCell cell = new PdfPCell(new Phrase(text));
	    cell.setPadding(0);
	    cell.setHorizontalAlignment(alignment);
	    cell.setBorder(PdfPCell.NO_BORDER);
	    return cell;
	}*/
}
