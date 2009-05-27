package org.oostethys.sos.test;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.oostethys.sos.Netcdf2sos100;
import org.oostethys.test.OOSTethysTest;

public class Netcdf2sos_unc_test extends OOSTethysTest {
	Netcdf2sos100 ns = null;

	protected void setUp() throws Exception {
		super.setUp();
		ns = new Netcdf2sos100();
		
		URL url = Thread.currentThread().getContextClassLoader().getResource(
				"oostethys-unc.xml");
		System.out.println(url);
		ns.setUrlOostethys(url);

	}
	
	
	public void testGetCapabilities() throws Exception {
			URL urlService = new URL("http://localhost:8080/oostethys/sos");
			ns.setServletURL(urlService.toString());
			
			
			Map<String, String[]> map = new HashMap<String, String[]>();
			map.put("REQUEST",createArray("GetCapabilities"));
			map.put("SERVICE", createArray("SOS"));
			map.put("VERSION", createArray("1.0.0"));
			
			ns.process(map,System.out);
	}

	public void atestDescribeSensor() throws Exception {
			Map<String, String[]> map = new HashMap<String, String[]>();
			map.put("Request",new String[]{ "describeSensor"});
			map.put("sensorid", new String[]{"urn:usgs:gov:model:um1-gom"});

			
			ns.process(map, null);
			ns.getDescribeSensor(System.out);
	}

	public void atestgetObservation() throws Exception {
			// 42.20551 -70.7238
			String minLon = "-71";
			String minLat = "40";
			String maxLon = "-69";
			String maxlat = "45";
			String bbox = minLon + "," + minLat + "," + maxLon + "," + maxlat;

			Map<String, String[]> map = new HashMap<String, String[]>();
			map.put("Request", new String[]{"getObservation"});
			map.put("bbox", new String[]{ bbox});

			// ns.setValue_BBOX(bbox);

			ns.process(map, null);
			ns.getObservation(System.out);
	}

}