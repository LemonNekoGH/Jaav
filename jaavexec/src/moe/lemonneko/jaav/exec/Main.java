package moe.lemonneko.jaav.exec;

import java.io.*;
import java.lang.reflect.Method;

public class Main extends ClassLoader {
    public static void main(String[] args) throws Exception {
        if (args.length == 0){
            System.out.println("error: no input executable file.");
            return;
        }
        String path = args[0];
        Class<?> clazz = new Main().findClass(path);
        if (clazz == null){
            return;
        }
        Method mainMethod = clazz.getDeclaredMethod("main",String[].class);
        mainMethod.invoke(null, (Object) new String[]{});
    }

    @Override
    protected Class<?> findClass(String name) {
        try {
            File file = new File(name);
            if (!file.exists()){
                System.out.println("error: file not exists.");
                return null;
            }
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            byte[] bytes = new byte[(int) file.length()];
            bis.read(bytes);
            String fileName = file.getName();
            String fileNameWithoutExt = fileName.substring(0,fileName.lastIndexOf('.'));
            return defineClass(fileNameWithoutExt,bytes,0,bytes.length);
        }catch (Exception e){
            System.out.println("error: load file failed.");
            return null;
        }
    }
}
