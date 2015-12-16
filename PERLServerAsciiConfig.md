Please read the [PERLServerInstall](PERLServerInstall.md) page first.

# Editing the sos\_config.xml file #

The  sos\_config.xml is a simple XML configuration file which contains the local information necessary to generate the SOS responses to the three SOS requests:

  * GetCapabiliites
  * GetObservation
  * DescribeSensor

At the top of the file, the Publisher section contains self-explanatory fields for information about your organization.  A crucial element is the SOSURL section.  This must contaion the URL for the cgi-bin SOS script, e.g. http://hostname/cgi-bin/oostethys_sos.cgi  Unless you  have installed the script in some other location or renamed the oostethys\_sos.cgi script it should always look like this.

The next section is the ObservationList section.  The ObservationList is made up a one or more Observation sections. Each Observation section contains information about one platform or sensor and a particular data type, in this version.   Typically a platform or buoy will report many observation types.  Each data type must have it's own Observation section.

localPlatformName the identifier by which you refer to the plaform locally.
platformURI a urn prefix to the localPlatformName which assures that the name is unique across the internet. Typically this will urn:hostname:source.mooring#local platform name.  E.g. at GoMOOS we have a localPlatformName of A01.  The platformURI is urn:gomoos.org:source.morring#A01

latitude, longitude are required and should be in decimal degrees.
startTime, endTime should cover the period for which readings are available and should be in an ISO8601 format in UTC, e.g. 2006-06-28T10:00:00Z.  endTime may be left empty and implies that data is available up until now and into the future.

observedProperty  This should be the local name you use for this data type, examples include: sea\_water\_temperature, WaveHeight, sea\_surface\_temperature.  There are no restrictions on what data type name you use, although  OOSTethys recommends using CF conventions.

uom  Units of Measure.  For each observedProperty you should enter the units of measure in which you are serving your observation. E.g. wind\_speed or WindSpeed or Winds  could meters per second 'mps' or 'm\_s-1 or centimeters per second 'cmps' or 'cm\_s-1', etc.  There are no restrictions or requirements on which units of measure you use.

!SOSDataFile this should be the  full path to a local file which contains data points for this sensor and datatype combination.  It can be left blank, in which case some sample data within the script will be served. This can be useful for testing purposes.  See Creating Data Files below.

If you have more than one platform / sensor the Observation section must be repeated for each one.

> See the GoMOOS example configuration file for an example which handles several buoys and data types.

## Required Fields ##
Not all of the elements in the sos\_config.xml file are required. Here is a list of the required elements.

  * OrganizationName
  * OrganizationAcronym
  * ContactPersonName
  * ContactPersonEmail
  * !OrganizationURL
  * !SOSURL
  * localPlatformName
  * platformURI
  * platformLongName
  * latitude
  * longitude
  * startTime
  * observedProperty
  * uom
  * !SOSDataFile

## Creating Data Files ##

Each sensor and data type has a field called: SOSDataFile  which should contain the full path of the file containing the data for that sensor and data type.

**Note:**   Typically a Web Server will not allow writing to the cgi-bin directory, otherwise the files could be placed in the same directory as the oostethys\_sos CGI script.  In that case you do not need a full path for the observation file name.  At GoMOOS we run a cron job which queries the GoMOOS database every hour and writes salinity data to a temporary data directory. We use the platform name followed by the data type with a txt extension but any name will work.
Format of the observation file
The observation file is made up of tuples with 6 fields separated by commas.   The 6 fields are:

  1. Platform.  This should match the localPlatform name from the sos\_config.xml file.
  1. Time.  This must be in an accepted ISO8601 format.  E.g. 2006-09-27T10:00:00Z
  1. Latitude: in decimal degress  E.g. 43.7985  This in not degrees, minutes, seconds format.
  1. Longitude: in decimal degrees E.g. -70.3459
  1. Depth:  in meters. E.g. 2
  1. Datum value.  E.g. 38.78


In the data file each record should be separated by a newline.  There would be separate records for different times and depths but each file should represent observations for a single sensor and data type.

Sample GoMOOS Data file

```
A01,2006-09-28T15:00:00Z,42.5277,-70.5665,-1,31.28
A01,2006-09-28T15:00:00Z,42.5277,-70.5665,-20,32.07
A01,2006-09-28T15:00:00Z,42.5277,-70.5665,-50,32.46
```
This represents the latest salinity readings for Buoy A01 at three different depths.
At GoMOOS we create a new file each hour with the latest readings, but since each record has a time stamp on it, readings for different times could be contained within one file.
Testing the SOS Service

The service is designed to be accessible in a web browser and will default to the GetCapabilities request.
E.g. http://www.gomoos.org/cgi-bin/sos/V1.0/oostethys_sos.cgi

### Sample requests: ###
GetCapabilities

  * http://www.gomoos.org/cgi-bin/sos/V1.0/oostethys_sos.cgi?request=GetCapabilities&service=SOS

DescribeSensor

  * http://www.gomoos.org/cgi-bin/sos/V1.0/oostethys_sos.cgi?request=DescribeSensor&procedure=A01&service=SOS&version=1.0.1

GetObservation

  * http://www.gomoos.org/cgi-bin/sos/V1.0/oostethys_sos.cgi?request=GetObservation&offering=A01&observedProperty=salinity&service=SOS&version=1.0.0