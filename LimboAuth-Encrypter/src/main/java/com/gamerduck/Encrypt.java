package com.gamerduck;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.DosFileAttributes;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.loohp.limbo.Limbo;

public class Encrypt {
	SecretKey key;
	public Encrypt() {
		try {
			KeyGen gen = new KeyGen(); 
			File file = new File(Limbo.getInstance().getPluginManager().getPlugin("LimboAuth").getDataFolder() + "/" + "secret.key");
			if (!file.exists()) {
				key = gen.generateKey();
				file.createNewFile();
				gen.saveKey(file, key);
				setHiddenAttrib(Paths.get(file.getPath()));
			} else {
				key = gen.loadKey(file);
			}
		} catch (IOException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	public String encrypt(String s) {
		String temp = "FAIL";
		 try {
	            Cipher cipher = Cipher.getInstance("AES");
	            cipher.init(Cipher.ENCRYPT_MODE, key);
	            byte[] encrypted = cipher.doFinal(s.getBytes());
	            temp = new String(encrypted);
	        }
	        catch(Exception e) 
	        {e.printStackTrace();}
		return temp;
	}
	public String dencrypt(String s) {
		String temp = "FAIL";
		 try {
	            Cipher cipher = Cipher.getInstance("AES");
	            cipher.init(Cipher.DECRYPT_MODE, key);
	            temp = new String(cipher.doFinal(s.getBytes()));
	        }
	        catch(Exception e) 
	        {e.printStackTrace();}
		return temp;
	}
	
	 private static void setHiddenAttrib(Path filePath) {		
		    try {
		      DosFileAttributes attr = Files.readAttributes(filePath, DosFileAttributes.class);
		      System.out.println(filePath.getFileName() + " Hidden attribute is " + attr.isHidden());
		      Files.setAttribute(filePath, "dos:hidden", true);
		      attr = Files.readAttributes(filePath, DosFileAttributes.class);
		      System.out.println(filePath.getFileName() + " Hidden attribute is " + attr.isHidden());
		    } catch (IOException e) {
		      e.printStackTrace();
		    } 
		  }
}
class KeyGen
{
   private final String ALGO = "AES";
   private final int KEYSZ = 256;// 128 default; 192 and 256 also possible
   
   public KeyGen() {}
   
   public SecretKey generateKey() throws NoSuchAlgorithmException
   {
       KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGO);
       keyGenerator.init(KEYSZ); 
       SecretKey key = keyGenerator.generateKey();
       return key;
   }
   
   public void saveKey(File file, SecretKey key) throws IOException
   {
	   try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
		   String encodedKey = Base64.getEncoder().encodeToString(key.getEncoded());
		    bufferedWriter.write(encodedKey);
		} catch (IOException e) {
		}
   }
   
   public SecretKey loadKey(File file) throws IOException
   {
	   SecretKey temp = null;
	   try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
		    String line = bufferedReader.readLine();
			   byte[] decodedKey = Base64.getDecoder().decode(line);
			   System.out.println(line);
			   temp = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES"); 
	   } catch (FileNotFoundException e ) {
		   
	   }
	   return temp;
	   
   }

}
