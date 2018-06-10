/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import javazoom.jl.decoder.JavaLayerException;
import static sun.audio.AudioPlayer.player;

/**
 *
 * @author Fercho
 */
public class Hablar {
//hacemos una solitud a una url en este caso a google translate para obtener la voz - 
private static byte[] getLenguaje(String url) {
    URL urlpagina = null;
    InputStream isr = null;
    URLConnection urlCon = null;
    ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
    byte[] byteChunk = new byte[4096];
    int noOfBytes = 0;
    try {
        urlpagina = new URL(url);
        urlCon = urlpagina.openConnection();
        urlCon.addRequestProperty("User- Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)");
        urlCon.connect();
        isr = urlCon.getInputStream();
        while ((noOfBytes = isr.read(byteChunk)) > 0) {
        byteOutputStream.write(byteChunk, 0, noOfBytes);
    }
    isr.close();
    } catch (Exception ex){
        ex.printStackTrace();
        return null;
    } return byteOutputStream.toByteArray();
} //despues de hacer la solicitud a google transale pasamos a llamar la libreria JLayer1 para pasarle la respuesta de google translate a su formato de sonido -
public static boolean Lee(String texto){
    //String url = "http://translate.google.com/translate_tts?ie=UTF-8&q=||||&tl=es&total=1&idx=0&textlen=6&prev=input";
    //url = url.replace("||||", texto.replace(" ", "%20"));
    //url = url.replace("\n", "");
    //url = url.replace("\"", "");
    String url ="http://translate.google.com/translate_tts?ie=UTF-8&total=1&idx=0&textlen=32&client=tw-ob&q=Esto+es+una+prueba&tl=es";
    byte[] cad = getLenguaje(url);
    if (cad == null){
    return false;
    } InputStream is = new ByteArrayInputStream(cad);
    //creamos el objeto de JLAyer1 y ejecutamos el sonido que sera el cual nosotros le pusimos de texto - Player player = new Player(is);
    //player.play();
    
    return true;
    }    
}