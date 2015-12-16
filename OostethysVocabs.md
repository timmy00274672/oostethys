Reviewing java code for vocabulary-related elements ..

Starting from [Voc](http://code.google.com/p/oostethys/source/browse/trunk/component/server/java/oostethys-java-server/src/main/java/org/oostethys/voc/Voc.java) (which is a list of URIs currently with OGC URNs and MMI URLs), I find references in several classes including:

  * [Netcdf2sos1001](http://code.google.com/p/oostethys/source/browse/trunk/component/server/java/oostethys-java-server/src/main/java/org/oostethys/sos/Netcdf2sos100.java) (called from [SOS\_Servlet](http://code.google.com/p/oostethys/source/browse/trunk/component/server/java/oostethys-java-server/src/main/java/org/oostethys/servlet/SOS_Servlet.java)).

  * [ObservationNetcdf](http://code.google.com/p/oostethys/source/browse/trunk/component/server/java/oostethys-java-server/src/main/java/org/oostethys/model/impl/ObservationNetcdf.java) (implements [Observation](http://code.google.com/p/oostethys/source/browse/trunk/component/server/java/oostethys-java-server/src/main/java/org/oostethys/model/Observation.java))


  * [VariableMapper](http://code.google.com/p/oostethys/source/browse/trunk/component/server/java/oostethys-java-server/src/main/java/org/oostethys/netcdf/util/VariableMapper.java)


## some comments ##

  1. Need to determine what need to be hard-coded (depending on the internal mechanisms in oostethys) and what not ..
  1. 