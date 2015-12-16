#SensorML generator driven by OOSTethys and MMI Ontology Registry -- Carlos Rueda

Service deployed at: http://mmisw.org/smlmor/


# Introduction #

This page **is intended to document** the specification, requirements, and development of the [ORR-enabled](http://mmisw.org/orr/) SensorML/TEDS generator (codenamed 'sml-mor,' a combination of SensorML and MMI Ontology Registry and repository).

# Goal #

The overall goal of this tool is to facilitate the creation of a formal document ([SensorML](http://vast.uah.edu/index.php?option=com_content&view=article&id=14&Itemid=52/) or TEDS) with the basic definition of a sensor system (system type, variables, subsystems..). The set of fields are provided by to the user in a web-based graphical user interface that is integrated with vocabulary terms and definitions from the [MMI Ontology Registry and repository](http://mmisw.org/orr/), ORR.

# Description #

The user defines systems, variables, and subsystems and the tool generates the corresponding SensorML or TEDS document. While filling out the fields, the user can access the ORR to help in the specification of system types and variables via corresponding definitions (which are captured using URIs). This integration is currently implemented with drop-down lists associated with certain fields that capture URIs for the definition.

### Note ###
In a more sophisticated UI mechanism the user would actually do some form of interactive search in order to identify the terms, definitions, and URIs that are appropriate for the field. This mechanism may be through some some kind of wizard involving the look-up of terms and relationships in the MMI ORR for a complete section is the GUI.

# Generation mechanism #

## SensorML ##
The generation of SensorML is based on an intermediate OOSTethys model instantiation of the spec given by the user. The OOSTethys model instantiation is then translated into a SensorML format via an XSLT file available in the OOSTehys codebase.

## TEDS ##
Not implemented yet.

# Status #

This is a proof-of-concept prototype. Some functionality is not yet fully implemented. For example, upon selection of an element from a drop-down list, not all related fields in the associated section are updated. Also, not all information is captured in the SensorML representation.


# Changes #

2011-10-12  0.0.2pre7
  * (internal) A fix made to properly populate the drop-down list for CF terms.
  * Minor adjustments to the UI.

2010-07-23  0.0.2pre6
  * (internal) SPARQL query result requested to be in CSV format (previously in JSON) because of [current limitation](http://code.google.com/p/mmisw/issues/detail?id=261) in the MMI ORR service.

2009-12-06  0.0.2pre5
  * Selection of URIs from the MMI Ontology Registry and Repository (ORR) now included in some of the fields (this is preliminary--the associated vocabularies, in particular [sensor types](http://mmisw.org/orr/#http://mmisw.org/ont/mmi/systemtype), are still preliminary).
  * Preliminary basic set of fields for contacts, system, and variables.
  * Any number of variables and systems, and unbounded system nesting
  * Generation of SensorML via oostethys model instantiation.
  * No TEDS generation
  * Generates of a tree dump for testing and comparison. This is enabled with the `_log=y` parameter (http://mmisw.org/smlmor/?_log=y). Once the SensorML is generated, click the 'refresh log' button at the bottom. Preliminary tests seems to show that there are missing elements in the generated sensorml document.


2009-11-18  0.0.2pre4
  * Fix for proper selection in the drop-down lists (needed for firefox at least).

2009-11-02 version 0.0.2pre3
  * Selection of URIs from the MMI ORR now included in some of the fields (this is preliminary--the associated vocabularies, in particular [sensor types](http://mmisw.org/orr/#http://mmisw.org/ont/mmi/systemtype), are still preliminary).

2009-10-14 version 0.0.2pre
  * interface improved based on tabpanels
  * http://mmisw.org/smlmor/?_log=y generates a tree dump for testing and comparison. The generated SensorML seems to be missing some settings to properly specify systems and variables.

2009-10-12 first version, 0.0.1
  * generates SensorML (see [ChangeLog](http://code.google.com/p/oostethys/source/browse/trunk/component/client/java/oostethys-java-smlmor/ChangeLog.txt)).
  * No yet integration with ORR


---

### Some related work ###
  * SensorML PrettyView: http://www.sensors.ws/SensorMLforms/upload.jsp
  * SensorML editor: http://code.google.com/p/sensorml-tools/
  * Sensor Interface Descriptors: http://52north.org/communities/sensorweb/incubation/sensorInterfaceDescriptors/index.html
  * Pine's SensorML Editor: http://marinemetadata.org/references/pinesensorml

Interesting:
  * xsd2rng: http://code.google.com/p/xsdtorngconverter/
  * rng2xhtml:http://debeissat.nicolas.free.fr/RNGtoHTMLform.php