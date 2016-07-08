/**
 * product name :CRDS(Credit Risk Decision System)
 */
package crds.pub.util;

import java.io.IOException;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

import sun.misc.BASE64Decoder;
/**
 * @specification :解密算法
 * @version : 1.0
 * @auther : LuGuohui
 * @date : Aug 20, 2008 1:58:25 PM
 * @email : luguohui99@gmail.com
 */
public class EncryptionDecode {

	private static String Algorithm = "DES"; //定义 加密算法,可用 DES,DESede,Blowfish

	static {
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
	}

	/**
	 * @specification :解密
	 * @param :String encryText 密文
	 * @return :String 明文
	 * @exception :Exception J000008-加密解密出错，请与管理员联系
	 */
	public static String decode(String encryText) throws Exception {
		BASE64Decoder dec = new BASE64Decoder();
		byte[] byteArray = null;
		try {
			byteArray = dec.decodeBuffer(encryText);
		} catch (IOException e1) {
			e1.printStackTrace();
			throw new Exception("J000008");
		}
		byte[] key = "信用评级".getBytes();
		SecretKey deskey = new javax.crypto.spec.SecretKeySpec(key, Algorithm);
		Cipher c1 = Cipher.getInstance(Algorithm);
		c1.init(Cipher.DECRYPT_MODE, deskey);
		byte[] clearByte = c1.doFinal(byteArray);
		return new String(clearByte);
	}

}