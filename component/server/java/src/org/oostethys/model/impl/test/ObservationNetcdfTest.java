package org.oostethys.model.impl.test;

import java.net.URL;
import java.text.DecimalFormat;

import org.jaxen.VariableContext;
import org.mmi.util.ResourceLoader;
import org.oostethys.model.VariableQuantity;
import org.oostethys.model.VariablesConfig;
import org.oostethys.model.impl.ObservationNetcdf;
import org.oostethys.model.impl.VariableQuantityImpl;
import org.oostethys.netcdf.util.TimeUtil;
import org.oostethys.voc.Voc;

import ucar.nc2.units.DateUnit;

import junit.framework.TestCase;

public class ObservationNetcdfTest extends TestCase {
	protected void setUp() throws Exception {
	}

	public void testCTDMBARI(){
		URL url = ResourceLoader.getUrlResource("test/ctd0010.nc");
		VariablesConfig config = new VariablesConfig();
		
		config.addVariable("esecs");
		config.addVariable("Longitude");
		config.addVariable("Latitude");
		config.addVariable("NominalDepth");
		config.addVariable("Temperature");

		config.addVariable("Conductivity");
		config.addVariable("Salinity");
		config.addVariable("Pressure");
		
		ObservationNetcdf oni = new ObservationNetcdf();
		oni.setURL(url);
		oni.setVariablesConfig(config);
		oni.setDepthIsGiven(true);
		oni.setValue_TIME("2008-04-11/2008-10-11");
		oni.setNumberOfRecords(100);
	
		try {
			oni.process();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error");
			
		}
		
		System.out.println("records \n"+oni.getAsRecords());
		
		assertTrue(oni.getAsRecords().startsWith("2"));
		assertTrue(oni.getAsRecords().startsWith("2008-06-09T09:36:19Z,36.69623,-122.39965,10,12.1338,3.82432,33.17029,10.052 2008-06-09T09:46:20Z,36.69623,-122.39965,10,12.1324,3.82408,33.169254,9.956 "));
		
		assertEquals(-122.39965,oni.getMinLon());
		assertEquals(-122.39965,oni.getMaxLon());
		assertEquals(36.69623,oni.getMinLat());
		assertEquals(36.69623,oni.getMaxLat());
// assertTrue(10-oni.getMinZ()==0);
// assertTrue(10-oni.getMaxZ()==0);
//		double minTime = oni.getMinTime();
	
	
		 DecimalFormat df = new DecimalFormat("#0");
		long lminTime= Long.parseLong(df.format(oni.getMinTime()));
		long lmaxTime = Long.parseLong(df.format(oni.getMaxTime()));
		
		assertEquals("2008-06-09T09:36:19Z",TimeUtil.getISOFromMillisec(lminTime));
		assertEquals("2008-06-10T02:06:21Z",TimeUtil.getISOFromMillisec(lmaxTime));
	}
}
