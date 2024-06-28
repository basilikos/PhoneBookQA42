package helpers;

import interfaces.TestHelper;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import static helpers.PropertiesReaderXML.getProperty;

public class RetryAnalyzer implements IRetryAnalyzer, TestHelper {

    private static int maxValue = Integer.parseInt(getProperty("maxValue", XML_FILE_PATH));
    int retryCount = 0;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (retryCount < maxValue) {
            retryCount++;
            return true;
        }
        return false;
    }
}
