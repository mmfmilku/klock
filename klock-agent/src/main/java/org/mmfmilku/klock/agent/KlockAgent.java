package org.mmfmilku.klock.agent;

import org.mmfmilku.klock.transformer.EncryptClassTransformer;

import java.lang.instrument.Instrumentation;

public class KlockAgent {

    private static final String KKey = "klock.key";

    private static final String KEncrypt = "klock.algorithm";

    public static void premain(String agentArgs, Instrumentation instrumentation) {

        System.out.println("this is klock-agent code");

        System.out.println(System.getProperties().entrySet().toString());

        String key = System.getProperty(KKey, "klock-key");
        String encrypt = System.getProperty(KEncrypt, "DES");

        instrumentation.addTransformer(new EncryptClassTransformer(key, encrypt), true);

        System.out.println("--------------------agent finished------------------");

    }

}

