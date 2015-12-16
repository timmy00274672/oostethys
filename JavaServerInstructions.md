# Introduction #

This document presents guidelines for setting up an OOSTethys JAVA server. The Server will create an Open Geospatial Consortium (OGC) Sensor Observation Service (SOS) that can run as a web application in a Java web server, such as Tomcat and Jetty.


# Installing war file (5 min) #

  1. [Download](http://code.google.com/p/oostethys/downloads/list) the distribution zip file from Google Code. The file name looks like this: ` oostethys-oostethys-java-server-0.3.9-r276.zip `.
  1. Unzip it. The zip file contains a war file.
  1. Put the oostethys.war file in your web server. For example for TOMCAT put it under the webapps folder. TOMCAT will unzip the war file. You should see that an oostethys directory was created in the web server and that it contains the structure in Figure 1.

> ![http://oostethys.googlecode.com/svn/wiki/JavaServerInstructions.attach/wardirexp.png](http://oostethys.googlecode.com/svn/wiki/JavaServerInstructions.attach/wardirexp.png)

  1. Open a web browser and type the URL of this application. For example: http://localhost:8080/oostethys/ . If you see the a welcome page the OOSTethys server is properly installed. The welcome page presents a check link, so you could test the oostethys server.
  1. Now you could edit the configuration file (oostethys.xml) with any text editor, and add required metadata information. Details here :

# Editing the configuration file (10 min) #

The OOSTethys configuration file describes the details of a data (or service or stream) source, necessary to publish an SOS service, based on OOSTethys conventions.
Oostethys has components. A component is a systems, which could represent an observing system, platform or sensor.

A system has metadata ( systemName, identifier, etc..), and could have other components or an output. For example an observing system could have more than one platform. but will have no output. A sensor could have output but no components.

The output has a sourceConfiguration, which details the properties to parser the sources and publish an SOS service. The following source is available: OOSTethys - JAVA - NetCDF

This configuration file describe the details of a NetCDF file, necessary to publish an SOS service, based on OOSTethys conventions. The code bellow is an example of the oostethys-netcdf configuration.

```
<output>
   <sourceConfiguration>
     <oostethys-netcdf>
       <fileURL>myNetCDFURL.nc</fileURL>
       <variables>
          <variable shortName="esecs" dimension="time" uri="http://mmisw.org/ont/cf/parameter/iso_19118_time"/>
          <variable shortName="Longitude" dimension="longitude" uri="urn:ogc:phenomenon:longitude:wgs84"/>
          <variable shortName="Latitude" dimension="latitude" uri="urn:ogc:phenomenon:latitude:wgs84"/>
          <variable shortName="NominalDepth" dimension="depth" uri="http://mmisw.org/ont/cf/parameter/depth"/>
          <variable shortName="Temperature" dimension="no" uri="http://mmisw.org/ont/cf/parameter/sea_water_temperature"/>
          <variable shortName="Conductivity" dimension="no" uri="http://mmisw.org/ont/cf/parameter/conductivity"/>
          <variable shortName="Pressure" dimension="no" uri="http://mmisw.org/ont/cf/parameter/pressure"/>
          <variable shortName="Salinity" dimension="no" uri="http://mmisw.org/ont/cf/parameter/sea_water_salinity"/>
       </variables>
    </oostethys-netcdf>
   </sourceConfiguration>
  </output>

```

  * **fileURL**: URL of the NETCDF file or OPeNDAP link. It could also be a relative path to the WEB\_INF/classes folder of the oostethys web application.
> > It also allows a simple OPeNDAP url with ranges, where you can specify the minimum and maximum ranges for each variable. For example: http://xxxx-nc.dods?time[0:3],depth[0:3],latitude[0:1],longitude[0:1],echo_intensity_beam1[0:8514][0:59][0:0][0:0]


> A more concrete example: http://www.smast.umassd.edu:8080/thredds/dodsC/FVCOM/NECOFS/Forecasts/NECOFS_FVCOM_OCEAN_MASSBAY_FORECAST.nc.dods?lon[28482],lat[28482],time[1:72],zeta[1:72][28482]

  * **variable/shortName** : title of the variable that appears after the data type in the variables description section of netcdf file header. In the following example Salinity and NominalDepth are the shortNames. These are unique within a file.
> > ...

```

      float Salinity(esecs=8265, NominalDepth=1, Latitude=1, Longitude=1);
           :long_name = "Salinity";
           :units = "";
           :standard_name = "sea_water_salinity";
           :missing_value = -99999.0f; // float
           :_FillValue = -99999.0f; // float

```

> ...

```
        float NominalDepth(NominalDepth=1);
           :long_name = "Depth";
           :units = "m";
           :standard_name = "depth";
           :_CoordinateAxisType = "Height";
          
```
> ...
  * **Dimension**: If it is a dimension in the data set ( e.g. time, lat and lon are often dimensions. There are predefine dimensions. If the variable is not a dimension then the value is no. If the attribute is not provided then it is not a dimension.
  * **uri**: Is the URI of this variable. Often time is an OGC URN or a URI that represents the identifier of the variable. We use MMI identifiers created from the CF vocabulary. At the MMI registry you can search for more URIs.

# Checking the Server #

In the welcome page you should see a check link. If you follow it, you will be provided with errors or a success confirmation message. Examples are shown bellow.

![http://oostethys.googlecode.com/svn/wiki/JavaServerInstructions.attach/errorfeedback.png](http://oostethys.googlecode.com/svn/wiki/JavaServerInstructions.attach/errorfeedback.png)