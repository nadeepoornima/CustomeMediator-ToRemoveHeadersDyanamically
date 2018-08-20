# The Custom Mediator to remove set headers in a property mediator as a dynamic manner

This is a custom class mediator to remove the transport level headers in a dynamic manner. Here the set of headers has saved in the registry as a xml file.
<HEADERS>
     <HEADER>X-ING-header1</HEADER>
     <HEADER>X-ING-header2</HEADER>
</HEADERS>
Then it will ready by a proxy and get all headers to a property mediator.By this code, it will read and dynimally remove from the message context.

**Sample Proxy**:
<inSequence>
         <property expression="get-property('registry','conf:/SAFHeadersConfig.xml')"
                   name="safHeadersList"/>
         <class name="com.wso2.customclassmediators.samples.RemoveDynamicHeaders"/> <!-- class mediator has cofigured with package name and the class name -->
         <log level="full">
            <property name="message" value="++++++++++++ After remove headers +++++++++++"/>
         </log>
         <send>
            <endpoint>
               <http uri-template="http://www.mocky.io/v2/5b6d7e11330000a827a36e13"/>
            </endpoint>
         </send>
      </inSequence>

## Set up and Run

1. First clone the project
2. Export the project to your IDE through pom.xml
3. Run the "mvn clean install" command to build the project
4. Get the jar (ClassMediator-1.0-SNAPSHOT.jar) and put it to the lib folder (<ESB_HOME>/repository/components/lib) of the ESB
5. Restart the server to apply the change
6. Invoke the proxy to see the result
