package org.mmfmilku.klock.file;

import java.io.*;

public class FileUtil {

    private static final String ROOT_PATH = System.getProperty("user.dir") + File.separator;

    private static final String RESOURCES_PATH = "src\\main\\resources\\";

    public static byte[] getResources(String path) {
        return getFileData(ROOT_PATH + RESOURCES_PATH + path);
    }

    

    /*加载文件数据*/
    public static byte[] getFileData(String filePath) {
        System.out.println("load file:" + filePath);
        byte[] data = new byte[0];
        File f = new File(filePath);
        if (!f.exists()) {
            System.out.println("file not exists!");
            return data;
        }

        InputStream in = null;
        try {
            in = new FileInputStream(f);
            data = readFromStream(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static byte[] readFromStream(InputStream in) {
        byte[] data = null;
        if (in == null)
            throw new NullPointerException("null InputStream");
        ByteArrayOutputStream out = null;
        try {
            out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int size = 0;
            while ((size = in.read(buffer)) != -1)
                out.write(buffer, 0, size);
            data = out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data;
    }

    public static boolean writeResourcesFile(byte[] data, String name) {

        File f = new File(ROOT_PATH + RESOURCES_PATH + name);
        OutputStream out = null;

        try {
            out = new FileOutputStream(f);

            out.write(data, 0, data.length);

            f.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return true;
    }

}
