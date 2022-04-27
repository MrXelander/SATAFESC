/*
    **Clase tomada de https://github.com/CodigosdeProgramacion/hash_java/blob/master/Hash.java
    **Esta clase sirve para encriptar las contraseñas con un algoritmo MD5 o SHA1
 */
package modelo;

/**
 *
 * @author aleja
 */
public class Hash {
    
    /* Retorna un hash a partir de un tipo y un texto */
    public String getHash(String txt, String hashType) { //los metodos no son estaticos, debido al uso de diferentes clases
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance(hashType);
            byte[] array = md.digest(txt.getBytes());
            StringBuilder sb = new StringBuilder(); //se cambio el StringBuffer por StringBuilder (es más actual)
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
 
    /* Retorna un hash MD5 a partir de un texto */
    public String md5(String txt) {
        return getHash(txt, "MD5");
    }
 
    /* Retorna un hash SHA1 a partir de un texto */
    public String sha1(String txt) {
        return getHash(txt, "SHA1");
    }
}
