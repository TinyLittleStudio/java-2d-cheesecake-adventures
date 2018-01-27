package eu.hackathon.cheesecake.utils.external;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtils {
    public static final String ENVIRONMENT_VARIABLE_OS_WIN = "WIN";
    public static final String ENVIRONMENT_VARIABLE_OS_MAC = "MAC";
    public static final String ENVIRONMENT_VARIABLE_OS_NUX = "NUX";

    private static String DIRECTORY = null;

    private static String PATH = null;

    private static boolean isInitialized = false;

    private FileUtils() {

    }

    private static void init() {
        if (isInitialized) {
            return;
        }
        String path = System.getenv("APPDATA") + "\\" + FileUtils.DIRECTORY;

        String operationSystem = System.getProperty("os.name").toUpperCase();

        if (operationSystem.contains(FileUtils.ENVIRONMENT_VARIABLE_OS_WIN)) {
            path = System.getenv("APPDATA") + "\\" + FileUtils.DIRECTORY;
        }

        if (operationSystem.contains(FileUtils.ENVIRONMENT_VARIABLE_OS_MAC)) {
            path = System.getProperty("user.home") + "/Library/Application " + "Support" + FileUtils.DIRECTORY;
        }

        if (operationSystem.contains(FileUtils.ENVIRONMENT_VARIABLE_OS_NUX)) {
            path = System.getProperty("user.dir") + "." + FileUtils.DIRECTORY;
        }

        File directory = new File(path);

        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                System.out.println("Could not create directory '" + FileUtils.PATH + "' !");
                return;
            }
        }
        FileUtils.PATH = path + "/";

        isInitialized = true;
    }

    public static void create(String arg) {
        if (FileUtils.PATH != null) {
            File file = new File(FileUtils.PATH + arg);

            if (!file.exists()) {
                if (!file.mkdir()) {
                    System.out.println("Could not create file '" + arg + "' in directory '" + FileUtils.PATH + "' !");
                }
            }
        }
    }

    public static File getFile(String arg) {
        if (FileUtils.PATH == null) {
            return null;
        }
        File file = new File(FileUtils.PATH + arg);

        if (file.exists()) {
            if (file.isFile()) {
                return file;
            }
        }
        return null;
    }

    public static File[] getFiles(String arg) {
        if (FileUtils.PATH == null) {
            return null;
        }
        File file = new File(FileUtils.PATH + arg);

        if (file.exists()) {
            if (file.isDirectory()) {
                if (file.listFiles() != null) {
                    return file.listFiles();
                }
            }
        }
        return null;
    }

    public static boolean install(String from, String to) {
        try {
            InputStream inputStream = FileUtils.class.getResourceAsStream(from);
            if (inputStream != null) {
                int readBytes;

                byte[] buffer = new byte[4096];

                String[] args = from.split("/");

                OutputStream outputStream = new FileOutputStream(to + args[args.length - 1]);

                while ((readBytes = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, readBytes);
                }
                inputStream.close();
                outputStream.close();
            }
        } catch (Exception ignored) {
            return false;
        }
        return true;
    }

    public static void setRootDirectory(String arg) {
        if (isInitialized) {
            return;
        }

        if (FileUtils.DIRECTORY == null || FileUtils.DIRECTORY.length() == 0) {
            FileUtils.DIRECTORY = arg;

            if (arg != null) {
                init();
            }
        }
    }

    public static String getRootDirectory() {
        return FileUtils.DIRECTORY;
    }

    public static String getTotalPath() {
        return FileUtils.PATH;
    }
}
