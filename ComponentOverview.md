# Introduction #

As a new developer trying to use this project I first wanted to understand its high level components.  This is my attempt to document what I discovered.

At a high level here are the 3 major components
  1. ostethys-ogc-xmlbeans
  1. stethys-java-server
  1. stethys-java-client

## oostethys-ogc-xmlbeans Overview ##
This jar contains the classes generated from XSD's that represent entities required for and SOS service.  This jar is by far the most complicated because it requires knowledge of the entities defined within the these schemas.

### Individual Schemas ###
**TODO...**

| **schema** | **Description** | **URL** | **Dependent Schemas**|
|:-----------|:----------------|:--------|:|
| CCCC.xsd   | C is a letter in the alphabet | www.abc.com | a.xsd, b.xsd |

### Generated Packages ###
Groups of these XSD's define a entities for particular problems.
The compiled classes generated from these schemas can be divided into the following packages:

#### net.opengis.gml ####
The entities required to use GML http://en.wikipedia.org/wiki/Geography_Markup_Language

#### net.opengis.ogc ####
More general OCG entities required for below schemas http://www.opengeospatial.org/

#### net.opengis.swe.x101 ####
More general SWE entities required for below schemas http://www.opengeospatial.org/

#### net.opengis.om.x10 ####
Entities required for the Observation & Measurement sections of the SWE specification

#### net.opengis.sensorML.x101 ####
Entities required to for SensorML section of the SWE specification

#### net.opengis.sos.x10 ####
Entities required to for Sensor Observation section of the SWE specification

#### net.opengis.ows ####
Entities required to for the OGC Web Services sections of the SWE specification


## oostethys-java-server Overview ##
Creates a war to convert a netCDF file to a SOS observation.
Uses the 'oostethys-ogc-xmlbeans' library to define all required SOS entities.

### Code overview ###

org.oostethys.servlet
This is the main entry point, it provides access the SOS\_Servlet class which initiates the process

The majority of the classes that define NetCdf entities are contained within the following packages.

org.oostethys.sos
org.oostethys.model
org.oostethys.model.impl


#### Utilities and supporting Classes ####
Applications to test and support  oostethys
'org.oostethys.example'
CreateSweCommonFromNetcdf     – Sets up ...
Other                - Sets up ...

'org.oostethys.harvester'
CopyOfDataBase            - Copies database ...
Database            - provides access to database for ...

'org.oostethys.util'
SeaWater            - utility functions for seawater conversions

'org.oostethys.voc'
Voc                - defines observation types and constants

'org.oostethys.netcdf'
Utilities to convert NetCDF data types to types required by a SOS
TimeUtil
UnitsMapper
VariableMapper


## oostethys-java-client Overview ##
Provides ....