package dev.alex;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;

public class RSA {
    private String dataToCrypt;
    private Key privateKey2;
    private byte[] cryptedDAtaInCrypted;

    public RSA() {
    }

    public RSA(String dataToCrypt) {
        this.dataToCrypt = dataToCrypt;
    }

    public String getDataToCrypt() {
        return dataToCrypt;
    }

    public void setDataToCrypt(String dataToCrypt) {
        this.dataToCrypt = dataToCrypt;
    }

    public void crypt()
    {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            Key publicKey =keyPair.getPublic();
            Key privateKey = keyPair.getPrivate();
            System.out.println("Private"+privateKey.toString());
            privateKey2=privateKey;
            System.out.println("===========================");
            System.out.println("Public "+publicKey.toString());
            System.out.println("============================");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] dataCrypted = cipher.doFinal(getDataToCrypt().getBytes());
            cryptedDAtaInCrypted=dataCrypted;
            System.out.println("Result"+dataCrypted);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }

    public void decrypt()
    {
        try {
            //String privatekey ="Privatesun.security.rsa.RSAPrivateCrtKeyImpl@1a085";
            Cipher decrypt = Cipher.getInstance("RSA");
            decrypt.init(Cipher.DECRYPT_MODE,privateKey2);
            byte[] decryptedData = decrypt.doFinal(cryptedDAtaInCrypted);
            System.out.println("=======================");
            for(byte b:decryptedData)
            {
                System.out.print((char)b);
            }

            //System.out.println("Decrypted data "+(char)decryptedData);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }
}
