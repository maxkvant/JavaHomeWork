package task;

import jdk.nashorn.internal.ir.Symbol;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class BroadcastLoader extends BroadcastCoordinator {
    public void load(String pathname, String rootPackage) {
        try {
            File file = new File(pathname);

            URL url = file.toURI().toURL();
            URL[] urls = new URL[]{url};

            ClassLoader classLoader = new URLClassLoader(urls);

            try(Stream<Path> paths = Files.walk(Paths.get(pathname)).filter(Files::isRegularFile)) {
                final String rootPathname = Paths.get(pathname).toString();

                paths.filter(path -> path.toString().endsWith(".class"))
                        .map(Path::toString)
                        .map(str -> rootPackage +
                                str
                                .substring(rootPathname.length() , str.length() - ".class".length())
                                .replace('/', '.'))
                        .forEach(name -> {
                                    try {
                                        Class<?> clazz = classLoader.loadClass(name);
                                        tryAddSender(clazz);
                                        tryAddFilter(clazz);
                                        tryAddReceiver(clazz);
                                    } catch (ClassNotFoundException e) {
                                        e.printStackTrace();
                                    }
                                }
                        );
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    void tryAddReceiver(Class<?> clazz) {
        try {
            Class<? extends BroadcastReceiver> receiverClass = (Class<? extends BroadcastReceiver>) clazz;
            Constructor[] constructors = receiverClass.getConstructors();
            if (constructors.length > 0) {
                add((BroadcastReceiver) constructors[0].newInstance());
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    void tryAddSender(Class<?> clazz) {
        try {
            Class<? extends BroadcastSender> receiverClass = (Class<? extends BroadcastSender>) clazz;
            Constructor[] constructors = receiverClass.getConstructors();
            if (constructors.length > 0) {
                add((BroadcastSender) constructors[0].newInstance());
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    void tryAddFilter(Class<?> clazz) {
        try {
            Class<? extends Filter> receiverClass = (Class<? extends Filter>) clazz;
            Constructor[] constructors = receiverClass.getConstructors();
            if (constructors.length > 0) {
                add((Filter) constructors[0].newInstance());
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }
}
