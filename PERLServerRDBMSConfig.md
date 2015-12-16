Please read the [PERLSOSInstall](PERLSOSInstall.md) page first.

# Editing the oostethys\_sos.cgi script #
## Overview ##

As downloaded, the oostethys\_sos.cgi script is set to use  the sos\_config.xml for SOS MetaData and to get observation data from local comma separated ASCII text files.  That is the default configuration. The oostethys\_sos.cgi script must be modified for it to work with a local relational database.

All the sections which must be modified are clearly marked and have comments in-line to help you make the correct modifications.  The sections of the file between the LOCAL EDIT and END LOCAL EDIT comments must be edited.
```
#### LOCAL EDIT

...
#### END LOCAL EDIT
```
The script requires basically two SQL statements:

  1. SQL to retrieve metadata about your sensors or platforms and what observations are being served from them.
  1. SQL to retrieve observations for a given sensor and observed property.


Depending on how your local database is configured you may require additional SQL statements. E.g.  at GoMOOS the SQL for a time series of observations is different for than the SQL for the latest observations since the latest observatins are handled via a view.  Similarly we required a special query to retrieve the beginTime for a platform since the GoMOOS platforms have been through multiple deployments.

I have left the GoMOOS SQL in place to give you an idea on how to edit things for your setup.
$use\_config Variable

In using this script there are two approaches which can be taken.

  * Get your SOS MetaData from the sos\_config.xml, which you must also edit, see: Editing the sos\_config.xml, but serve observation data from your database system. You will only need to edit the getDataDB() SQL statements.
  * Get both SOS MetaData and Observation data from your database system.  You will need to edit the SQL statements in the getMetaDB() routine and the getDataDB() routine.

This is controlled by a global variable near the top of the file $use\_config with the default set to 1.  Change it to $use\_config = 0;  to get both Metadata and Observation data from you local Database.  Keep it as $use\_config = 1; to get Metadata from the sos\_config.xml file while getting observation data from your database.  In that case you should set the `<SOSDataFile>` element to DataBase.  E.g.
```
<SOSDataFile>DataBase</SOSDataFile>
```

## Step by Step Instructions ##
  1. DBI.pm The script uses the standard Perl module: DBI.pm so step one is to un-comment the use DBI; at the top of the file.
  1. $use\_config If you want the script to get SOS MetaData from your sos\_config.xml file leave the my $use\_config = 1;  unchanged. In the sos\_config.xml  for the SOSDataFile element use DataBase as the value. `<SOSDataFile>DataBase</SOSDataFile>`  This way MetaData will come from the sos\_config.xml file but observation data will come from the getDataDB() routine.  If you do this you can skip Steps 3, 4 and 5. If you want both your MetaData and Observation data to come from you database you must set the $use\_config variable to 0.  In this case you must also edit both the Metadata SQL and the Observation SQL (steps 5 and 6)
  1. Units of Measure (UOM)   (Note: if you are using the sos\_config.xml file for your Metadata you can skip this step.) You must edit the %uom\_lookup table. Foreach observed property you wish to serve you should enter the units of measure you are using E.g. wind\_speed or WindSpeed or Winds  could meters per second 'mps' or 'm\_s-1 or centimeters per second 'cmps' or 'cm\_s-1', etc. The observed properites must match the names you use in your MetaData and Observation SQL.  There are no restrictions on what observedProperty names you use or on what units of measure you return. Both are just treated as strings by the script.
  1. Organization MetaData  (Note: if you are using the sos\_config.xml file for your Metadata you can skip this step.) These global variables are used to create the proper Organization MetaData.  Use the GoMOOS examples and enter your values.  Once again any values may be entered.  The most import variable here is the $sos\_url.  This must point to the URL of your script as accessible via the Web without the query string appended.  E.g.  http://www.gomoos.org/cgi-bin/sos/oostethys_sos.cgi
  1. _GetMetaDB()_ sub-routine  (Note: if you are using the sos\_config.xml file for your Metadata you can skip this step.)  This routine returns a hash of platforms with metadata about each platform including a list of all the observed properties obtainable from this platform. There is an example of the data structure returned in the comments before the routine.
    * The script uses standard DBI.pm methods
    * You need to edit the script to construct the proper DBI->connect call for you system.
    * You must edit the $sql\_statement variable to return the following list of fields with these names:        platform,longitude,latitude,start\_time,end\_time,description,observed\_property

This query must be ordered by platform, observed\_property, start\_time and end\_time are the range of dates for which observations are available.  Leaving end\_time a null or '' means that observations are available until 'now'.

Since at GoMOOS each buoy has had mulitple deployments  this required a seperate DBI call get\_start\_time() for each platform, something like:
```
SELECT min(time) as start_time, max(end_time) as end_time ....  WHERE platform = '$platform'";
```
> 6. _GetDataDB()_ sub-routine

> Even if you used sos\_config.xml for Metadata you must edit this routine to return observed Properties.  The inputs to this routine used for constructing the SQL are:
```
         $sensorID (the platform or offering

         $in_time the SOS request included either a single time or start and end time separated by a forward slash.  E.g. 2008-10-09T00:00/2008-10-09T12:00

         $params, a reference to an array of observedProperties @params.
             This is because SOS 1.0 allows GetObservation requests to include comma separated lists of observedProperties so the SQL must be able to handle this.

          $sensor_list is not currently used.
```
> The default response is to return the most recent readings when the $in\_time is the null string:  ''

> At GoMOOS we have a special table view which holds only recent readings so a different join\_table is set for those cases.

> The basic SQL  ($sql\_statement) should return:

> platform, observed\_property,date\_time,latitude,longitude,depth,observation

> Finally there are additional sections to add conditions to the SQL WHERE clause to the $sql\_statement to restrict the query based on a Bounding Box, or a time range.

> If $bbox input is not empty it will be a string like: 'min\_longitude, min\_latitude, max\_longitude, max\_latitude'

> If $in\_time is not empty it will be string like:  '2008-10-09T00:00/2008-10-09T12:00'  or just a single time.

> If $in\_time is empty the most recent observations should be returned.

> Finally the list of observed properites  (pointed to by $params) requested is looped thru to handle the case more than one has been requested.

The idea is to return and array of tuples as displayed below:
```
  $data = [
'A01,2008-13-02T18:00:00Z,43.5695,-70.055,1,3.171'
'A01,2008-13-02T18:00:00Z,43.5695,-70.055,2,3.157801'
'A01,2008-13-02T18:00:00Z,43.5695,-70.055,20,3.178'
];
```
In a case where multiple observedProperties have been requested e.g. observedProperty=wind\_speed,wind\_from\_direction the results would look like the example below:
```
  $data = [
'A01,2008-13-02T18:00:00Z,43.5695,-70.055,-3,5.6,45'
];
```