/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Cromik
 */
public class Servicio {


    public static void main(String[] args) throws Exception {
       //String encriptado = Encriptar("manager\nM05adm1n\n192.168.2.253");
        //System.out.println(encriptado);
        //grabardatos("C:/librerias/coneccion.txt",encriptado);
        
        //for (String arg : muestraContenido("C:/librerias/coneccion.txt")) {
          //   String desencriptado = Desencriptar(arg);
          //  System.out.println(desencriptado);
        //}

        
        buscar("coneccion0.txt", new File("C:\\librerias\\cat1\\subcat2"));
       
       
        
    }
    
    
    
    public static String Encriptar(String texto) {
 
        String secretKey = "qualityinfosolutions"; //llave para encriptar datos
        String base64EncryptedString = "";
 
        try {
 
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
 
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);
 
            byte[] plainTextBytes = texto.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            base64EncryptedString = new String(base64Bytes);
 
        } catch (Exception ex) {
        }
        return base64EncryptedString;
    }
 
    public static String Desencriptar(String textoEncriptado) throws Exception {
 
        String secretKey = "qualityinfosolutions"; //llave para encriptar datos
        String base64EncryptedString = "";
 
        try {
            byte[] message = Base64.decodeBase64(textoEncriptado.getBytes("utf-8"));
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
 
            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, key);
 
            byte[] plainText = decipher.doFinal(message);
 
            base64EncryptedString = new String(plainText, "UTF-8");
 
        } catch (Exception ex) {
        }
        return base64EncryptedString;
    }
    
    public static List<String> muestraContenido(String archivo) throws FileNotFoundException, IOException {
        String cadena;
        List<String> info = new ArrayList<>();
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        while ((cadena = b.readLine()) != null) {
            //System.out.println(cadena);
            info.add(cadena);
        }
        b.close();
        return info;
    }

    public static void grabardatos(String URL,String datos) throws IOException {
        File archivo = new File(URL);
        BufferedWriter bw;
        if (archivo.exists()) {
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write(datos);
       
        } else {
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write("Acabo de crear el fichero de texto.");
        }
        bw.close();

    }

    
     public static void buscar (String argFichero, File argFile) {
        File[] lista = argFile.listFiles();
 
        if (lista != null) {
            for (File elemento : lista) {
                if (elemento.isDirectory()) {
                    buscar (argFichero, elemento);
                } else if (argFichero.equalsIgnoreCase(elemento.getName())) {
                    //System.out.println (elemento.getParentFile());
                    System.out.println (elemento.getAbsoluteFile());
                }
            }
        }
    }
     
     
     
         public StreamedContent getTempPdfFile() throws IOException {
        File testPdfFile = new File("C:\\librerias\\cat1\\subcat2\\archivo.pdf");
        return new DefaultStreamedContent(new FileInputStream(testPdfFile), "application/pdf",
                "prueba.pdf");
    }

    public void downloadPdf() throws IOException {
        File file = new File("C:\\librerias\\cat1\\subcat2\\subcat2.zip");

        /*try {
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();

            ec.responseReset();
            ec.setResponseContentType("application/zip");
            ec.setResponseHeader("Content-Disposition", "attachment;filename=\"4005.zip\"");

            FileInputStream is = new FileInputStream(file);
            OutputStream os = ec.getResponseOutputStream();

            byte[] buffer = new byte[1024];
            int length = 0;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            os.flush();
            is.close();
            fc.responseComplete();
        } catch (Exception e) {

        <h:form>
            <h:commandButton action = "#{Lector.comprimir()}" value="sdasdas" />
            
        </h:form>
        
        
        }*/
    }

   


}
