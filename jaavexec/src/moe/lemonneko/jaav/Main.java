package moe.lemonneko.jaav;

import java.io.*;

public class Main extends ClassLoader {
    public static void main(String[] args) throws Exception {
        if (args.length == 0){
            System.out.println("error: no input executable file.");
            return;
        }
        var path = args[0];
        var clazz = new Main().findClass(path);
        if (clazz == null){
            return;
        }
        var mainMethod = clazz.getDeclaredMethod("main",String[].class);
        mainMethod.invoke(null, (Object) new String[]{});
    }

    @Override
    protected Class<?> findClass(String name) {
        try {
            var file = new File(name);
            if (!file.exists()){
                System.out.println("error: file not exists.");
                return null;
            }
            var fis = new FileInputStream(file);
            var bis = new BufferedInputStream(fis);
            var bytes = bis.readAllBytes();
            var fileName = file.getName();
            var fileNameWithoutExt = fileName.substring(0,fileName.lastIndexOf('.'));
            return defineClass(fileNameWithoutExt,bytes,0,bytes.length);
        }catch (Exception e){
            System.out.println("error: load file failed.");
            return null;
        }
    }
}
