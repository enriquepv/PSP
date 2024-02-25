package org.example;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;
/**
 * Método principal del programa.
 * Permite al usuario cifrar y descifrar datos utilizando AES.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el texto que desea cifrar:");
        String textoOriginal = scanner.nextLine();

        System.out.println("Ingrese la clave de cifrado (16 caracteres):");
        String claveString = scanner.nextLine();
        if (claveString.length() != 16) {
            System.out.println("La clave debe tener exactamente 16 caracteres.");
            return;
        }

        try {
            // Convertir la clave de String a bytes
            byte[] clave = claveString.getBytes("UTF-8");

            // Crear una instancia de la clase SecretKeySpec usando la clave
            SecretKeySpec key = new SecretKeySpec(clave, "AES");

            // Crear una instancia de Cipher para cifrar
            Cipher cifrador = Cipher.getInstance("AES");

            // Inicializar el cifrador en modo de cifrado con la clave
            cifrador.init(Cipher.ENCRYPT_MODE, key);

            // Cifrar el texto original
            byte[] textoCifrado = cifrador.doFinal(textoOriginal.getBytes());

            // Convertir el texto cifrado a una representación en base64
            String textoCifradoBase64 = Base64.getEncoder().encodeToString(textoCifrado);

            System.out.println("Texto cifrado: " + textoCifradoBase64);

            // Preguntar al usuario por la clave para descifrar
            System.out.println("Ingrese la clave para descifrar:");
            String claveDescifrado = scanner.nextLine();

            // Convertir la clave de String a bytes
            byte[] claveDescifradoBytes = claveDescifrado.getBytes("UTF-8");

            // Crear una nueva instancia de SecretKeySpec para la clave de descifrado
            SecretKeySpec claveDescifradoKey = new SecretKeySpec(claveDescifradoBytes, "AES");

            // Crear una instancia de Cipher para descifrar
            Cipher descifrador = Cipher.getInstance("AES");

            // Inicializar el descifrador en modo de descifrado con la clave de descifrado
            descifrador.init(Cipher.DECRYPT_MODE, claveDescifradoKey);

            // Descifrar el texto cifrado
            byte[] textoDescifrado = descifrador.doFinal(Base64.getDecoder().decode(textoCifradoBase64));

            // Mostrar el texto descifrado
            System.out.println("Texto descifrado: " + new String(textoDescifrado, "UTF-8"));

        } catch (Exception ex) {
            System.out.println("Error durante el cifrado o descifrado: " + ex.getMessage());
        }
    }
}
