/**
 * product name :CRDS(Credit Risk Decision System)
 */
package crds.pub.util;

import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

import sun.misc.BASE64Encoder;
/**
 * @specification :加密算法
 * @version : 1.0
 * @auther : LuGuohui
 * @date : Aug 20, 2008 1:58:25 PM
 * @email : luguohui99@gmail.com
 */
public class Encryption {

	private static String Algorithm = "DES"; //定义 加密算法,可用 DES,DESede,Blowfish

	static {
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
	}

	/**
	 * @specification :加密
	 * @param :String showText 明文
	 * @return :String 密文
	 * @exception :
	 */
	public static String encode(String showText) throws Exception {
		byte[] key = "信用评级".getBytes();
		SecretKey deskey = new javax.crypto.spec.SecretKeySpec(key, Algorithm);
		Cipher c1 = Cipher.getInstance(Algorithm);
		c1.init(Cipher.ENCRYPT_MODE, deskey);
		byte[] cipherByte = c1.doFinal(showText.getBytes());
		BASE64Encoder enc = new BASE64Encoder();
		String string = enc.encode(cipherByte);
		return string;
	}

}