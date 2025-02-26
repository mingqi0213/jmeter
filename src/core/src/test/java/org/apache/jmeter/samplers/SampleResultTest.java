package org.apache.jmeter.samplers;

import static org.junit.jupiter.api.Assertions.*;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.CoreMatchers;

import org.apache.jmeter.junit.JMeterTestCase;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.test.JMeterSerialTest;
import org.apache.jmeter.assertions.AssertionResult;

import org.junit.jupiter.api.Test;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.AfterEach;

public class SampleResultTest extends JMeterTestCase implements JMeterSerialTest {

    @AfterEach
    public void tearDown() {
        JMeterUtils.setProperty("sampleresult.useNanoTime", "true");
        JMeterUtils.setProperty("sampleresult.nanoThreadSleep", "5000");
    }
    
    @Test
    public void testToStringReturnsSampleLabel() {
        SampleResult result = new SampleResult();
        result.setSampleLabel("Test Label");
        assertEquals("Test Label", result.toString());
    }

    @Test
    public void testToDebugStringContainsImportantFields() {
        SampleResult result = new SampleResult();
        result.setSampleLabel("Debug Label");
        result.setResponseCode("200");
        result.setResponseMessage("OK");
        result.setThreadName("TestThread-1");
        result.setDataEncoding("UTF-8");

        String debugString = result.toDebugString();

        assertThat(debugString, CoreMatchers.containsString("Debug Label"));
        assertThat(debugString, CoreMatchers.containsString("responseCode='200'"));
        assertThat(debugString, CoreMatchers.containsString("responseMessage='OK'"));
        assertThat(debugString, CoreMatchers.containsString("threadName='TestThread-1'"));
        assertThat(debugString, CoreMatchers.containsString("dataEncoding='UTF-8'"));
    }

    @Test
    public void testSetAndGetResponseData() {
        SampleResult result = new SampleResult();
        byte[] responseData = "Sample Response".getBytes(StandardCharsets.UTF_8);
        result.setResponseData(responseData);

        assertArrayEquals(responseData, result.getResponseData());
    }

    @Test
    public void testGetResponseDataAsString() {
        SampleResult result = new SampleResult();
        result.setResponseData("Test Response".getBytes(StandardCharsets.UTF_8));

        assertEquals("Test Response", result.getResponseDataAsString());
    }

    @Test
    public void testSetAndGetResponseCode() {
        SampleResult result = new SampleResult();
        result.setResponseCode("404");

        assertEquals("404", result.getResponseCode());
    }

    @Test
    public void testSetAndGetResponseMessage() {
        SampleResult result = new SampleResult();
        result.setResponseMessage("Not Found");

        assertEquals("Not Found", result.getResponseMessage());
    }

    @Test
    public void testSetAndGetThreadName() {
        SampleResult result = new SampleResult();
        result.setThreadName("Thread-1");

        assertEquals("Thread-1", result.getThreadName());
    }

    @Test
    public void testSetAndGetDataEncoding() {
        SampleResult result = new SampleResult();
        result.setDataEncoding("ISO-8859-1");

        assertEquals("ISO-8859-1", result.getDataEncodingNoDefault());
    }

    @Test
    public void testSetAndGetTimeStamp() {
        SampleResult result = new SampleResult();
        result.setTimeStamp(1680000000000L);

        assertEquals(1680000000000L, result.getTimeStamp());
    }

    @Test
    public void testSetAndGetStartTimeEndTime() {
        SampleResult result = new SampleResult();
        result.setStartTime(1680000000000L);
        result.setEndTime(1680000005000L);

        assertEquals(1680000000000L, result.getStartTime());
        assertEquals(1680000005000L, result.getEndTime());
    }

    @Test
    public void testSetAndGetElapsedTime() {
        SampleResult result = new SampleResult();
        long fakeStartTime = System.currentTimeMillis();
        long fakeEndTime = fakeStartTime + 5000;
    
        result.setStartTime(fakeStartTime);
        result.setEndTime(fakeEndTime);
        
        assertEquals(5000, result.getTime());
    }

    @Test
    public void testSetAndGetSuccessfulStatus() {
        SampleResult result = new SampleResult();
        result.setSuccessful(TRUE);

        assertTrue(result.isSuccessful());
    }

    @Test
    public void testSetAndGetFirstAssertionFailureMessage() {
        SampleResult result = new SampleResult();

        AssertionResult assertion1 = new AssertionResult("Failure 1");
        assertion1.setFailure(true);
        assertion1.setFailureMessage("Error 1");

        AssertionResult assertion2 = new AssertionResult("Failure 2");
        assertion2.setFailure(true);
        assertion2.setFailureMessage("Error 2");

        result.addAssertionResult(assertion1);
        result.addAssertionResult(assertion2);

        assertEquals("Error 1", result.getFirstAssertionFailureMessage());
    }

    @Test
    public void testCleanAfterSample() {
        SampleResult result = new SampleResult();
        result.setResponseData("Test Response".getBytes(StandardCharsets.UTF_8));

        result.cleanAfterSample();

        assertEquals("Test Response", result.getResponseDataAsString());
    }
}