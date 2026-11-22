import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.io.IOUtils;

public class Creador {
    static void main(String[] args) {
        //Revisamos que al menos se hayan proporcionado argumentos
        if (args.length == 0) {
            System.err.println("Error: Debes proporcionar la ruta de la carpeta como argumento.");
            return;
        }

        String ruta = args[0];
        File file = new File(ruta);
        List<File> archivosPDF = new ArrayList<>();

        //Listamos los archivos del directorio y filtramos por cuya extensión sea .pdf
        if (file.exists() && file.isDirectory()) {
            File[] archivos = file.listFiles();
            if (archivos != null) {
                for (File archivo : archivos) {
                    String nombreArchivo = archivo.getName();
                    int index = nombreArchivo.lastIndexOf(".");

                    if (index > 0) {
                        String extension = nombreArchivo.substring(index + 1);
                        if (extension.equalsIgnoreCase("pdf")) {
                            archivosPDF.add(archivo);
                        }
                    }
                }
            }
        } else {
            System.err.println("La ruta especificada no existe o no es un directorio válido.");
            return;
        }

        //Insanciamos el objeto que nos permitirá fusionar los pdf
        PDFMergerUtility pdfMerger = new PDFMergerUtility();

        //File.separator lo hace compatible con Windows y Mac
        String rutaFinal = ruta + File.separator + "PDF_fusionado.pdf";

        try {
            //Establecemos dónde se guardará nuestro archivo final
            pdfMerger.setDestinationFileName(rutaFinal);

            //Añadimos los archivos al ensamblador (pdfMerger)
            for (File f : archivosPDF) {
                pdfMerger.addSource(f);
            }
            //Creamos el archivo final.pdf
            pdfMerger.mergeDocuments(IOUtils.createMemoryOnlyStreamCache());
            System.out.println("Archivo creado con éxito en: " + rutaFinal);

        } catch (FileNotFoundException e) {
            System.err.println("Error al encontrar los archivos: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error de lectura/escritura durante la fusión: " + e.getMessage());
        }
    }
}