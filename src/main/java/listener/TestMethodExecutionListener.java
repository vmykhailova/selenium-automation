package listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class TestMethodExecutionListener implements ITestListener {

    List<String> testInfo = new ArrayList<String>();
    @Override
    public void onTestStart(ITestResult result) {
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestNGMethod method = result.getMethod();
        String testName = method.getMethodName();
        long duration =  result.getEndMillis() - result.getStartMillis();
        String dots = getDots(testName.length());
        testInfo.add(testName + dots + Duration.ofMillis(duration).getSeconds());
    }

    @Override
    public void onTestFailure(ITestResult result) {

    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
        for (String info:testInfo) {
            System.out.println(info);
        }
    }

    private String getDots(int methodNameSize){
        String dots = "";
        for (int i = 0; i < 30 - methodNameSize; i++) {
            dots+=".";
        }

        return dots;
    }
}
