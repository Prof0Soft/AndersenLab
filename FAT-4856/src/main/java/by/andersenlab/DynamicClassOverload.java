package by.andersenlab;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Own ClassLoader for load specific classes.
 */
public class DynamicClassOverload extends ClassLoader {
    private Map<String, Class<?>> classesHash = new HashMap<>();
    private final String[] classPath;

    public DynamicClassOverload(String[] classPath) {
        this.classPath = classPath;
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> result = findClass(name);
        if (resolve) {
            resolveClass(result);
        }
        return result;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> result = classesHash.get(name);
        if (result != null) {
            return result;
        }

        String buf = name.replace('.', '/');

        if (name.toLowerCase().contains("staticclassforload"))
            return findSystemClass(name);

        File f = findFile(buf, ".class");
        if (f == null) {
            return findSystemClass(name);
        }

        try {
            byte[] classBytes = loadFileAsBytes(f);
            result = defineClass(name, classBytes, 0, classBytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        }

        classesHash.put(name, result);
        return result;
    }

    protected java.net.URL findResource(String name) {
        File f = findFile(name, "");
        if (f == null) {
            return null;
        }
        try {
            return f.toURI().toURL();
        } catch (java.net.MalformedURLException e) {
            return null;
        }
    }

    private static byte[] loadFileAsBytes(File file)
            throws IOException {
        byte[] result = new byte[(int) file.length()];
        try (FileInputStream f = new FileInputStream(file)) {
            f.read(result, 0, result.length);
        }
        return result;
    }

    private File findFile(String name, String extension) {
        File fl;
        String buffer = name.replace('/', File.separatorChar);

        for (int k = 0; k < classPath.length; k++) {

            String buff2 = (new File(classPath[k])).getPath()
                    + File.separatorChar
                    + buffer
                    + extension;

            fl = new File(buff2);
            if (fl.exists()) {
                return fl;
            }
        }
        return null;
    }
}
