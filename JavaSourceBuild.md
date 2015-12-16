# JAVA  Toolkit Source Building #

### Importing the project into eclipse using M2Eclipse ###

_This instructions were tested with 3.4 Ganimede_

To build the source of the  OOSTethys JAVA toolkit you need the following:
  * You will need a Java runtime environment ([JRE](http://www.eclipse.org/downloads/moreinfo/jre.php)) to use Eclipse (Java 5 JRE recommended).
  * Have Eclipse installed. Downloads [here](http://www.eclipse.org/downloads/). The Eclipse IDE for Java Developers will work.
  * Install needed plugins - Help / Install new software / add the link to the update site
    * Install subeclipse ( if you don't have it ):
    * update site:  http://subclipse.tigris.org/update_1.6.x
    * name: subeclipse
    * Instructions: http://subclipse.tigris.org/servlets/ProjectProcess?pageID=p4wYuA
    * Install the Maven Integration for Eclipse
      * update site: http://m2eclipse.sonatype.org/update/
      * name: m2eclipse
      * instructions: http://m2eclipse.sonatype.org/
    * Select Maven Integration and Maven Optional Components.
> > ![http://content.screencast.com/users/lbermudez/folders/Default/media/5604dd95-ba5b-4889-8f0a-af91e6595ab9/2009-09-11_1339_inst_m2e.png](http://content.screencast.com/users/lbermudez/folders/Default/media/5604dd95-ba5b-4889-8f0a-af91e6595ab9/2009-09-11_1339_inst_m2e.png)
  * Checkout SVN project
> > ![http://content.screencast.com/users/lbermudez/folders/Default/media/9d45a813-8f92-4675-a46b-6f63748ee515/2009-09-11_1316_checkout_svn.png](http://content.screencast.com/users/lbermudez/folders/Default/media/9d45a813-8f92-4675-a46b-6f63748ee515/2009-09-11_1316_checkout_svn.png)
    * Go to: File > New > Other > Maven >  Checkout Maven projects from SCM
    * SCM URL -> Select SVN and type the URL : https://oostethys.googlecode.com/svn/trunk/component/server/java/oostethys-java-server
    * note that it is http:// for read only and https:// for writing.
    * you are going to be asked a password - use this page: http://code.google.com/p/oostethys/source/checkout


> ![http://oostethys.googlecode.com/svn/wiki/JavaSourceBuild.attach/svn-selection.png](http://oostethys.googlecode.com/svn/wiki/JavaSourceBuild.attach/svn-selection.png)
> <a href='Hidden comment: 
[http://content.screencast.com/users/lbermudez/folders/Default/media/0f7551b3-672f-4ebf-9f77-68bce6d163d9/2009-09-11_1324_svn_path.png]
'></a>
  * click on finish
  * wait
> ![http://content.screencast.com/users/lbermudez/folders/Default/media/237dbc9a-0431-4996-8750-99e424352afa/2009-09-11_1326_opeeration_waiting.png](http://content.screencast.com/users/lbermudez/folders/Default/media/237dbc9a-0431-4996-8750-99e424352afa/2009-09-11_1326_opeeration_waiting.png)
  * You should be able to something like this:
> ![http://content.screencast.com/users/lbermudez/folders/Default/media/a6b31dac-bb3e-4339-b666-b06bf84d645a/2009-09-11_1330_run_test.png](http://content.screencast.com/users/lbermudez/folders/Default/media/a6b31dac-bb3e-4339-b666-b06bf84d645a/2009-09-11_1330_run_test.png)

### Running , Testing etc... ###


To run the test:
  * Right click on the project (oostethys-java-server)
  * Select **Run As**
  * Select **Maven test**

To create a war file:
  * Right click on the project (oostethys-java-server)
  * Select Run as **Maven build...**
  * In Goals
    * type **war** to file all the tasks that have war
    * select **war** from **org.apache.maven.plugins:maven-war-plugin**

> ![http://content.screencast.com/users/lbermudez/folders/Jing/media/2e997508-0890-4d0f-96e8-e4e6065d7c16/00000013.png](http://content.screencast.com/users/lbermudez/folders/Jing/media/2e997508-0890-4d0f-96e8-e4e6065d7c16/00000013.png)
  * run




## Building from the command line ##

(this section from Jesper's 2009-06-20 email)

Here are step-by-step instructions for the command line (you can skipthe first two steps if you already have Maven installed on your system):
  1. Download Maven from http://maven.apache.org/download.html
  1. extract the archive file: tar xvfj apache-maven-2.1.0-bin.tar.bz2
  1. check out the source code from the Subversion repository:
```
    svn co https://oostethys.googlecode.com/svn/trunk/component/server/java/oostethys-java-server
```
  1. change into project's directory  cd oostethys-java-server
  1. build the project: ../apache-maven-2.1.0/bin/mvn compile

Instead of "mvn compile" you can use "mvn jetty:run" to start a test server of "mvn test" to run the unit tests, etc.


### Importing the project into eclipse using IAM ###
(CR)
With the [Eclipse IAM](http://code.google.com/p/q4e/wiki/Installation) plugin for maven, I imported the "oostethys-java-server" directory by using the Maven 2 Project import option. It took a couple of mins (no progress bar or something) but it worked. (SVN feature was not activated, though.)