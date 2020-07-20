package org.mmfmilku.klock.agent;

import org.mmfmilku.klock.transformer.EncryptClassTransfoemer;

import java.lang.instrument.Instrumentation;

public class KlockAgent {

    public static void premain(String agentArgs, Instrumentation instrumentation) {

        System.out.println("this is klock-agent code");
        System.out.println(agentArgs);
        instrumentation.addTransformer(new EncryptClassTransfoemer(), true);
        System.out.println("--------------------agent finished------------------");

    }

}

