package muti_network;

import kr.re.nsr.crypto.symm.LEA;
//import system.user.MD5.CryptoUtil;
import kr.re.nsr.crypto.BlockCipher.Mode;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import kr.re.nsr.crypto.BlockCipherMode;
import kr.re.nsr.crypto.BlockCipherModeAE;
import kr.re.nsr.crypto.Mac;

public class crypto {
	//인코더, 디코더
	static Base64.Encoder encoder = Base64.getEncoder().withoutPadding();
	static Base64.Decoder decoder = Base64.getDecoder();
	// 객체 초기화
	// CTR 모드
	static BlockCipherMode cipher = new LEA.CTR(); 
	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub		
		// 초기화		
		String plain = "텍스트";
		String keyp ="abcdefghiaxcvzxq";
		String ivp = "abcdeaxziaxcvzxq";
		byte[] key = sha256(keyp);
		byte[] iv = ivp.getBytes();
		// 암호화
		String enc_text = Encrypt(plain, key, iv);
		System.out.println(enc_text);
		// 복호화
		String dec_text = Decrypt(enc_text, key, iv);
		System.out.println(dec_text);
	}
	public static String Encrypt (String plain, byte[] key, byte[] iv) {
		cipher.init(Mode.ENCRYPT, key, iv);
		byte[] ct = cipher.doFinal(plain.getBytes());
		return encoder.encodeToString(ct);
	}
	public static String Decrypt (String enc_txt, byte[] key, byte[] iv) throws Throwable {
		cipher.init(Mode.DECRYPT, key, iv);
		byte[] pt = cipher.doFinal(decoder.decode(enc_txt));
		return new String(pt, "UTF8");
	}
	public static byte[] sha256(String msg)  throws NoSuchAlgorithmException {

	    MessageDigest md = MessageDigest.getInstance("SHA-256");

	    md.update(msg.getBytes());

	    return md.digest();

	}
}
