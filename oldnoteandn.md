New and Noteworthy (before 0.4.2)

0.4.1

> Updated dependency of oostethys-xmlbeans.jar to 0.3.2

> Fixed [issue 51](https://code.google.com/p/oostethys/issues/detail?id=51)

oostethys-oostethys-java-server\_0.4.1\_r299\_2009-10-20T1318-0400.zip

  * Fixed issue with not being able to open Opendap queries URLs

0.4.0\_r299\_2009-10-13T1444-0400

  * Added support to provide OpenDAP queries in the configuration file. For example the following URL can be provided in the configuration file http://www.smast.umassd.edu:8080/thredds/dodsC/FVCOM/NECOFS/Forecasts/NECOFS_FVCOM_OCEAN_MASSBAY_FORECAST.nc.dods?lon[28482],lat[28482],time[1:72],zeta[1:72][28482]
  * In the configuration file the URI of each variable should be provided. The toolkit could not do good guesses all the times since not all the netCDF and OpenDAP servers are CF compliant.
  * All the code is now available as MAVEN modules.
  * NETCDF JAVA library was updated to 4.1.
  * Check page provides random view of the data.
  * Team Engine checks was added to the build. This means that the SOS services created by the toolkit is being tested with OGC test code.

0.3.11\_20091007

  * Added check functionality to test the configuration where errors are displayed in a web page.

0.3.10\_20090529

  * Minor bugs fixex

0.3.9\_20090428

  * Minor bugs fixex

0.3.8\_20081122

  * Code and issues were moved to Google Code
  * URI assignment for time variable is now properly assign using ogc iso8601
  * Remove the obligation to provide hasOutputFormat as parameter in the describeSensor request
  * All Tests input files were moved to src/test/
  * Fixed issue: Double String transformation and Locales

0.3.7\_20081018

  * If no time is given in the getObservation request - then the last record is responded - following OOSTethys best practices

0.3.6\_20081016

  * Fixed [issue 2172752](https://code.google.com/p/oostethys/issues/detail?id=2172752). Problem with not providing correct min lat and lon

0.3.5\_20081015

  * getObservation request does not need format OUTPUTFORMAT parameter
  * xlink href values in the xslt oostethys2getCapabilities are now being trimmed

0.3.4\_20081008

  * Last position of the sensor is now available via Sensorml
  * If units are not provided in the nc file, and thus in the oostetxys.xml, then they are not shown in the sensorML and getObservation results.

0.3.3\_20081002

  * Changed in the getCapabilities DescribeSystem for DescribeSensor
  * Relaxed the constraints when calling the getCapabilities. No parameters are required

0.3.2\_20081001

  * [2040589](2040589.md) Fixed issue: ISO date not processing properly JODA is now used for reading ISO String dates
  * [2140645](2140645.md) Fixed issue: getObs returning nothing when responseMode is not given

0.3.1\_20080930

  * [2136348](2136348.md) Fixed issue processing requests - string cast exception

0.3.0\_20080930

  * [2001015](2001015.md) Enabled exceptionXML reports
  * Enabled post xml request

0.2.3\_20080912

  * changed the capabilities declared operation DescribeSystem for DescribeSensor

0.2.2\_20080808
Noteworthy

  * A check functionality was added to the server. A check.jsp page will now show configuration errors. There is a direct link to this page from the welcome page. Example of errors are:
> > o configuration file is not found
> > o configuration file is malformed or not valid
> > o if netCDF file cannot be open
> > o if declared variables are not found


> Screenshot presenting errors of a non valid configuration file:
> errofeedback.png
  * The toolkit is now using the TimeUnit API (ucar.nc2.units.TimeUnit), which is a great class that handles time Unidata UDUNITS.

0.2.1\_20080721
Minor Changes

Added missing mmiutil.jar