The current version of the Toolkit (1.0.1) includes major upgrades from previous versions but all attempts have been made to make it backward compatible with
existing installations existing configuration files.

The script now supports IOOS PO DIF output schemas as an option.
Major changes have been made as well to make sure it passes the OGC's CITE testing suite at:
http://cite.opengeospatial.org/ and http://cite.opengeospatial.org/betaTesting

But an existing installation which uses the sos\_config.xml and has observations in ASCII files should continue to work.

You must make sure to save you existing sos\_config.xml file.
Also any Globals which were set in the older version of the script must be saved.

For those who were using a Relational Database for both Metadata and observations you must save all the Global variables set your previous version of
the script in the sections marked as LOCAL EDITS

In particular the three sub routines, which have local edits:
getMetaDB() and getDataDB() and perhaps get\_start\_time().
All should be saved and should replace the existing routines in the oostethys\_sos.cgi script using copy and paste.

N.B. There is one change in both those routines.  The SQL query which previously required or allowed for the use of DB field called "program" is now called "provider" to refer to the data provider.