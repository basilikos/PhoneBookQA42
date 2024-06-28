package helpers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class Logger {

    private static PrintStream printStream;

    public static void setupLogger(String filePath) throws IOException {
        File logFile = new File(filePath);

        if (!logFile.exists()) {
            logFile.getParentFile().mkdirs();
            logFile.createNewFile();
        }

        PrintStream printStream = new PrintStream(new FileOutputStream(logFile));

        System.setOut(printStream);
        System.setErr(printStream);
    }

    public static void closeLogger() {
        if (printStream != null) {
            printStream.close();
        }
    }

}
