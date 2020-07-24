package org.mmfmilku.klock.agent;

import org.mmfmilku.klock.transformer.EncryptClassTransfoemer;

import java.lang.instrument.Instrumentation;

public class KlockAgent {

    private static final String KKey = "klock.key";

    private static final String KEncrypt = "klock.encrypt";

    public static void premain(String agentArgs, Instrumentation instrumentation) {

        System.out.println("this is klock-agent code");

        String key = System.getProperty(KKey);
        String encrypt = System.getProperty(KEncrypt);

        instrumentation.addTransformer(new EncryptClassTransfoemer(key, encrypt), true);

        System.out.println("--------------------agent finished------------------");

    }

}

