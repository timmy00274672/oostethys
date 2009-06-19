/**
 *
 */
package org.oostethys.servlet;

import net.opengis.ows.x11.ExceptionReportDocument;
import net.opengis.ows.x11.ExceptionReportDocument.ExceptionReport;

import net.opengis.sos.x10.CapabilitiesDocument;

import org.oostethys.test.OOSTethysTest;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletConfig;


/**
 * Tests from the TEAM engine tests for SOS.
 * @author Jesper Zedlitz &lt;jze@informatik.uni-kiel.de&gt;
 *
 */
public class SOS_ServletTest extends OOSTethysTest {
    SOS_Servlet servlet = new SOS_Servlet();

    /**
      * @see junit.framework.TestCase#setUp()
      */
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        MockServletConfig config = new MockServletConfig("sos");
        servlet.init(config);
    }

    public void testContentType_1() throws Exception {
        MockHttpServletRequest request =
            new MockHttpServletRequest("GET", "/oostethys/sos");
        request.setParameter("service", "SOS");
        request.setParameter("version", "1.0.0");
        request.setParameter("request", "GetCapabilities");

        MockHttpServletResponse response = new MockHttpServletResponse();

        // invoke servlet
        servlet.doGet(request, response);

        // get the response
        String responseString = response.getContentAsString();

        assertEquals("response type is text/xml", "text/xml",
            response.getContentType());

        CapabilitiesDocument.Factory.parse(responseString);
    }

    /**
     * Parameter service missing.
     * @throws Exception
     */
    public void testGetCapabilities_Exceptions_1() throws Exception {
        MockHttpServletRequest request =
            new MockHttpServletRequest("GET", "/oostethys/sos");
        request.setParameter("version", "1.0.0");
        request.setParameter("request", "GetCapabilities");

        MockHttpServletResponse response = new MockHttpServletResponse();

        // invoke servlet
        servlet.doGet(request, response);

        // get the response
        String responseString = response.getContentAsString();

        ExceptionReport er =
            ExceptionReportDocument.Factory.parse(responseString)
                                           .getExceptionReport();

        assertEquals("correct exception code", "MissingParameterValue",
            er.getExceptionArray(0).getExceptionCode());
    }

    /**
     * Invalid value for parameter "service"
     * @throws Exception
     */
    public void testGetCapabilities_Exceptions_2() throws Exception {
        MockHttpServletRequest request =
            new MockHttpServletRequest("GET", "/oostethys/sos");
        request.setParameter("service", "ASDF");
        request.setParameter("version", "1.0.0");
        request.setParameter("request", "GetCapabilities");

        MockHttpServletResponse response = new MockHttpServletResponse();

        // invoke servlet
        servlet.doGet(request, response);

        // get the response
        String responseString = response.getContentAsString();

        ExceptionReport er =
            ExceptionReportDocument.Factory.parse(responseString)
                                           .getExceptionReport();

        assertEquals("correct exception code", "InvalidParameterValue",
            er.getExceptionArray(0).getExceptionCode());
    }

    /**
     * Unknown SOS version
     * @throws Exception
     */
    public void testGetCapabilities_Exceptions_3() throws Exception {
        MockHttpServletRequest request =
            new MockHttpServletRequest("GET", "/oostethys/sos");
        request.setParameter("service", "SOS");
        request.setParameter("acceptversions", "2000-01-01");
        request.setParameter("request", "GetCapabilities");

        MockHttpServletResponse response = new MockHttpServletResponse();

        // invoke servlet
        servlet.doGet(request, response);

        // get the response
        String responseString = response.getContentAsString();

        ExceptionReport er =
            ExceptionReportDocument.Factory.parse(responseString)
                                           .getExceptionReport();

        assertEquals("correct exception code", "VersionNegotiationFailed",
            er.getExceptionArray(0).getExceptionCode());
    }

    /**
     * GetCapabilities with an incorrect KVP query string, triggering the missing parameter value exception.
     * @throws Exception
     */
    public void testGetCapabilities_Exceptions_5() throws Exception {
        MockHttpServletRequest request =
            new MockHttpServletRequest("GET",
                "/oostethys/sos?request~GetCapabilities!service~!SOSversion~1.0.0");

        MockHttpServletResponse response = new MockHttpServletResponse();

        // invoke servlet
        servlet.doGet(request, response);

        // get the response
        String responseString = response.getContentAsString();

        ExceptionReport er =
            ExceptionReportDocument.Factory.parse(responseString)
                                           .getExceptionReport();

        assertEquals("correct exception code", "MissingParameterValue",
            er.getExceptionArray(0).getExceptionCode());
    }

    /**
     * Sending a request that is non-conformant to a schema associated with an SOS operation
     * causes the server to return a valid error report message with an exceptionCode value
     * of InvalidRequest.
     */
    public void testGeneral_InvalidRequest_1() throws Exception {
        MockHttpServletRequest request =
            new MockHttpServletRequest("POST", "/oostethys/sos");
        request.setContent("foobar".getBytes());

        MockHttpServletResponse response = new MockHttpServletResponse();

        // invoke servlet
        servlet.doPost(request, response);

        // get the response
        String responseString = response.getContentAsString();

        ExceptionReport er =
            ExceptionReportDocument.Factory.parse(responseString)
                                           .getExceptionReport();

        assertEquals("correct exception code", "InvalidRequest",
            er.getExceptionArray(0).getExceptionCode());
    }
}