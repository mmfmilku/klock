package org.mmfmilku.klock.mojo;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.mmfmilku.klock.encrypt.Base64Util;
import org.mmfmilku.klock.file.FileUtil;

import java.io.*;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;

/*
* 类文件加密;
* 加密后内容 = 文件魔数 + hash(原文) + encrypt(原文)
* */
@Mojo(name = "kencrypt", defaultPhase = LifecyclePhase.PACKAGE)
public class KEncryptMojo extends AbstractMojo {

    @Parameter(property = "project.build.finalName")
    private String finalName;

    @Parameter(property = "project.packaging")
    private String packaging;

    @Parameter(property = "project.build.directory")
    private String buildDir;

    @Parameter(property = "project.build.outputDirectory")
    private String outputDir;

    @Parameter(property = "file.separator")
    private String fileSeparator;

    // 是否全加密
    @Parameter(defaultValue = "false")
    private boolean fullEncrypt;

    // 需要加密的类文件
    @Parameter
    private Set<String> encryptDirs;

    private Log logger = getLog();

    private static final String fileHead = "klock";

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        System.out.println("fullEncrypt::" + fullEncrypt);
        System.out.println(encryptDirs.toString());

        String filePath = buildDir + fileSeparator + finalName + "." + packaging;
        logger.info("--- start encrypt your package file " + filePath + " ---");

        File f = new File(filePath);
        if (!f.exists()) {
            logger.error("package file not exists:" + filePath);
            return;
        }

        File outFile = new File(buildDir + fileSeparator + "test-out.jar");

        JarFile jarFile = null;
        OutputStream out = null;
        JarOutputStream jarOut = null;
        try {
            jarFile = new JarFile(f);
            out = new FileOutputStream(outFile);
            jarOut = new JarOutputStream(out);
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                System.out.println("-----------------");
                JarEntry jarEntry = entries.nextElement();
                String fileName = jarEntry.getName();
                System.out.println(fileName);
                jarOut.putNextEntry(new JarEntry(fileName));
                if (jarEntry.isDirectory()) {
                    continue;
                }
                InputStream inputStream = jarFile.getInputStream(jarEntry);
                byte[] data = FileUtil.readFromStream(inputStream);
                if (fileName.endsWith(".class") &&
                        (fullEncrypt || encryptDirs.contains(fileName.substring(0, fileName.indexOf(".class"))))) {
                    System.out.println("encrypt file:" + fileName);
                    data = Base64Util.encode(data);
                }
//                String base64Str = new String(base64);
//                System.out.println("文件内容:" + new String(Base64Util.decode(base64Str.getBytes())));
                jarOut.write(data);
            }
        } catch (IOException e) {
            logger.error(e);
        } finally {
            if (jarFile != null) {
                try {
                    jarFile.close();
                } catch (IOException e) {
                    logger.error(e);
                }
            }
            if (jarOut != null) {
                try {
                    jarOut.close();
                } catch (IOException e) {
                    logger.error(e);
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    logger.error(e);
                }
            }
        }

    }
}
