# Introduction #
This document presents guidelines for setting up an OOSTethys PERL server. The Server will create an Open Geospatial Consortium (OGC) Sensor Observation Service (SOS) that can run as a PERL based CGI web application under a web server, such as Apache or IIS.

# Installing the OOSTethys PERL SOS as a CGI program #

## File List ##
  * oostethys\_sos.cgi  -  the CGI script
  * sos\_config.xml - empty configuration file.
  * example\_sos\_config.xml -  An example config file using values from GoMOOS
  * sosGetCapabilities.xml - 1.0 template
  * sosGetObservation.xml - 1.0 template
  * sosDescribeSystem.xml - 1.0 template
  * difGetCapabilities.xml - DIF template
  * difGetObservation.xml - DIF template
  * difDescribeSystem.xml - DIF template

## System Requirements ##
  1. PERL 5
  1. Web Server such as Apache set up to run CGI scripts
  1. CGI
  1. XML::LibMXL
  1. XML:::LibXML::XPathContext
  1. Observations in a database or ASCII text files.

# Installation Steps #

  1. Put the oostethys\_sos.cgi script into your cgi-bin directory and make sure it has execute permission.  Typically this in  the /cgi-bin/ directory of you Web server.  At GoMOOS I installed it in a subdirectory  /cgi-bin/sos/  (Note:  If you are ftp'ing from a PC to your server or using a tool such as DreamWeaver to move the files to you Web server make sure that the script is transferred using binary mode)
  1. Put the six XML template files in the same directory
    * sosGetCapabilities.xml
    * sosGetObservation.xml
    * sosDescribeSystem.xml
    * difGetCapabilities.xml
    * difGetObservation.xml
    * difDescribeSystem.xml
  1. Put the sos\_config.xml file in the same directory.
  1. Test the server. See if your GetCapabilities request is working.  http://localhost/cgi-bin/oostethy_sos.cgi?request=GetCapabilities&service=SOS&version=1.0.0 GetCapbilties should return XML. Not a very interesting response since the sos\_config.xml has null values.


### Perl Module Installation Tips ###

Installing XML::LibXML
The oostethys\_sos CGI script relies on the existence of libxml2 and it's Perl module interface XML::LibXML. It also relies on the CGI.pm perl module, but that is a fairly common module usually included with the basic Perl installation.
Red Hat Linux

Perl Module on CPAN
http://search.cpan.org/dist/XML-LibXML/

Libxml2 C libary downloads.
http://xmlsoft.org/

Typical CPAN install
> perl -MCPAN -e 'shell'
That dumps you to a shell prompt like:
shell> install XML::LibXML
which should take care of fetching, configuring dependencies, etc.

If this fails, you will probably need to install libxml2 and libxml2-devel. on
redhat (and I believe also Debian?) there are separate packages for binaries
and header files. that is, if you want the stuff in /usr/include, which you
need for building software on top of any given package, then you generally
need to install the -devel package as well...
Libxml2 tar balls and RPM's

[ftp://xmlsoft.org/libxml2/](ftp://xmlsoft.org/libxml2/)
Installing XML::LibXML::XPathContext

Perl Module on CPAN
Download: http://search.cpan.org/~pajas/XML-LibXML-XPathContext-0.07/
Documentation: http://search.cpan.org/~pajas/XML-LibXML-XPathContext-0.07/XPathContext.pm


Typical CPAN install

> perl -MCPAN -e 'shell'

That dumps you to a shell prompt like:

shell> install XML::LibXML::XPathContext

which should take care of fetching, configuring dependencies, etc.

### Windows Server Installation Notes ###

Robert Leeman from SmartBay, St. John's NL, Canada contributed notes on installing ActiveState Perl and required Perl Modules on Microsoft Windows server.
[View PDF here](http://www.oostethys.org/downloads/sos-cookbook-perl/SOS%20with%20Perl%20-%20MS%20Windows.pdf/view)

## Editing the sos\_config.xml file ##

See: [PERLServerAsciiConfig](PERLServerAsciiConfig.md) if you are serving observations from ASCII files.

See: [PERLServerRDBMSConfig](PERLServerRDBMSConfig.md) if you are serving observations from a relational database.