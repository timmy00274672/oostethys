package org.oostethys.sos.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.oostethys.sos.Netcdf2sos100;
import org.oostethys.test.OOSTethysTest;

import junit.framework.TestCase;

public class Netcdf2sos_config_test_errors extends OOSTethysTest {
	Netcdf2sos100 ns = null;

	protected void setUp() throws Exception {

	}

	public void testNotFoundConfigXML() {

		Netcdf2sos100 ns = new Netcdf2sos100();
		try {
			
			
			ns.setUrlOostethys(new URL("file:/ddhdhdhd"));
			ns.process((Map)null, null);
			assertTrue("should not reach this point", 1 == 2);
		} catch (Exception e) {
//			System.out.println("message "+e.getMessage());
			assertNotNull(e);
			e.printStackTrace();
		}
	}
	
	public void testNotFoundNCFile() {

		Netcdf2sos100 ns = new Netcdf2sos100();
		try {
			
			ns.setUrlOostethys(getURL("oostethys_wrongurlnc.xml"));
			ns.process((Map)null, null);
			assertTrue("should not reach this point", 1 == 2);
		} catch (Exception e) {
			System.out.println("message "+e.getMessage());
			assertNotNull(e);
			e.printStackTrace();
		}
	}

	public void testMalformedXML() {

		Netcdf2sos100 ns = new Netcdf2sos100();
		try {
			
			ns.setUrlOostethys(new URL("file:test/oostethys_malformed.xml"));
			ns.process((Map)null, null);
			assertTrue("should not reach this point", 1 == 2);
		} catch (Exception e) {
			System.out.println("message "+e.getMessage());
			assertNotNull(e);
			e.printStackTrace();
		}
	}

	
	public void testNotValidXML() {
// todo - need to check for validation !!
		Netcdf2sos100 ns = new Netcdf2sos100();
		try {
			ns.setUrlOostethys(new URL("file:test/oostethys_notvalid.xml"));
			ns.process((Map)null, null);
			assertTrue("should not reach this point", 1 == 2);
		} catch (Exception e) {
			System.out.println("message "+e.getMessage());
			assertNotNull(e);
			e.printStackTrace();
		}
	}
	
	public void testNotFoundVariable(){
		Netcdf2sos100 ns = new Netcdf2sos100();
		try {
			ns.setUrlOostethys(new URL("file:test/oostethys_notfoundvars.xml"));
			ns.process((Map)null, null);
			assertTrue("should not reach this point", 1 == 2);
		} catch (Exception e) {
			System.out.println("message "+e.getMessage());
			assertNotNull(e);
			e.printStackTrace();
		}
	}
//	public void testFoundVariableDifficultSemantics(){
//		Netcdf2sos100 ns = new Netcdf2sos100();
//		try {
//			ns.setUrlOostethys(getURL("oostethys_foundVarsLat.xml"));
//			
//			ns.process((Map)null, null);
//			assertTrue("should not reach this point", 1 == 2);
//		} catch (Exception e) {
//			e.printStackTrace();
////			System.out.println("message "+e.getMessage());
//			assertTrue(e.getMessage().contains("The following variable was not found "));
//			assertNotNull(e);
//			e.printStackTrace();
//		}
//	}

}
