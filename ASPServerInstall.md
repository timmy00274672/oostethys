# OOSTethys ASP Version Installation #

**THE PACKAGE**

This package is an ASP version of the OOSTethys SOS implementation. Go to the [Downloads](http://code.google.com/p/oostethys/downloads/list) page and download the _oostethys\_asp\_sos.zip_ file. The compressed package should contain the following:

(1) oostethys\_server.asp - this is the the main module;

(2) sosDescribeSensor.inc - this is the additional module needed when a _DescribeSensor_ request is made;

(3) sosGetCapabilities.inc - this is the additional module needed when a _GetCapabilities_ request is made;

(4) sosGetObaservation.inc - this is the additional module needed when a _GetObservation_ request is made;

and

(5) sos\_config.inc - this is the configuration file.

**INSTALLATION**

It is assumed here that the server is already setup to run ASP (usually pre-installed on Windows Server). To install the package:

(1) Extract the files copy the content to folder accessible by the web server (usually, this is a folder in the InetPub directory) and make sure that it has execute permission. If you are using Internet Information Service (IIS), check the Web Service Extension and ASP should be enabled;

(2) Using an editor, edit the sos\_config.inc to reflect the proper values for the service, provider and operations.  The attached sample file is an example of an actual configuration for the LUMCON implementation. Note that the section on the offering should be done for all offerings and should be manually edited promptly if there are changes.

(3) The sos\_config.inc refers to text files containing the latest observation. The provider will need to produce one TXT file for each platform and sensor every hour. There is no convention on the naming of the files and you may overwrite the files every hour to keep the storage size in control. The generation of this files are not presented here but they can be generated in many different ways, including the programming of another script
to access the databse and write these files and using the Task Scheduler to run the script every hour or as may be necessary.

The files should have the following format

[

&lt;Platform&gt;

,

&lt;Time&gt;

,

&lt;Latitude&gt;

,

&lt;Longitude&gt;

,

&lt;Depth&gt;

,

&lt;Datum&gt;

]

1. Platform.  This should match the localPlatform name.

2. Time.  This must be in an accepted ISO8601 format;  e.g. 2006-09-27T10:00:00Z

3. Latitude: in decimal degrees  e.g. 43.7985  (WARNING! This in not degrees, minutes, seconds format).

4. Longitude: in decimal degrees e.g. -70.3459

5. Depth:  in meters.

6. Datum value.

Example (multiple salinity sensors at various depth but only one set is transmitted in one hour from CSI06):

CSI06,2006-09-28T15:00:00Z,42.5277,-70.5665,-1,31.28
CSI06,2006-09-28T15:00:00Z,42.5277,-70.5665,-20,32.07
CSI06,2006-09-28T15:00:00Z,42.5277,-70.5665,-50,32.46

Another example (air temperature from CSI05 but this Buoy is reporting every 15 mins to the hour; hence 4 sets; the filename can be CSI05\_AirTemperature.txt):

CSI05,2006-09-28T15:15:00Z,42.5277,-70.5665,27.3
CSI05,2006-09-28T15:30:00Z,42.5277,-70.5665,26.7
CSI05,2006-09-28T15:45:00Z,42.5277,-70.5665,27.0
CSI05,2006-09-28T16:00:00Z,42.5277,-70.5665,27.3

The data presented above are the data for the last one hour only; i.e. if it is 4:45PM, the results should be the ones that covers 2PM to 3PM. Then configure the Task Scheduler (I suspect you are using a Windows server) to run the programs every hour. At 5PM, it should generate the data for 4PM to 5PM; at 6PM, it should generate the data from 5PM-6PM.