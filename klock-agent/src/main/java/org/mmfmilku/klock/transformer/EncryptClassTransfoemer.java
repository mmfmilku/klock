package org.mmfmilku.klock.transformer;

import org.mmfmilku.klock.encrypt.Base64Util;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class EncryptClassTransfoemer implements ClassFileTransformer {

    // class文件魔数
    private static final byte[] MAGIC_NUM = new byte[] {-54, -2, -70, -66};

    // 密钥
    private String key;

    // 加密算法
    private String encryptionAlgorithm;

    public EncryptClassTransfoemer() {
        key = "123abc";
        encryptionAlgorithm = "DES";
    }

    public EncryptClassTransfoemer(String key, String encryptionAlgorithm) {
        this.key = key;
        this.encryptionAlgorithm = encryptionAlgorithm;
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

        for (int i = 0; i < MAGIC_NUM.length; i++) {
            if (MAGIC_NUM[i] != classfileBuffer[i]) {
                System.out.println("非标准class文件:" + className);
                return Base64Util.decode(classfileBuffer);
            }
        }
        return classfileBuffer;

    }
}
