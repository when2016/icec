package org.iece.tools.sys;

import org.icec.common.utils.CryptoUtils;
import org.mindrot.jbcrypt.BCrypt;

public class UserTool {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String passwd="123456";
		String encryptPassword=CryptoUtils.bcrypt(passwd);
		System.out.println(encryptPassword);
		boolean flag=BCrypt.checkpw(passwd, encryptPassword);
		System.out.println(flag);
	}

}
