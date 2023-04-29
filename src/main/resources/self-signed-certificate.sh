keytool -genkey -alias localhost -keyalg RSA -keystore localhost.keystore \
          -validity 3650 -storetype JKS \
          -dname "CN=localhost, OU=Spring, O=Pivotal, L=Holualoa, ST=HI, C=US"
          -keypass localpass -storepass localpass
          
keytool -export -alias localhost -keystore localhost.keystore -file localhost_cert -storepass localpass
keytool -importcert -keystore localhost.truststore -alias localhost -storepass localpass -file localhost_cert -noprompt

#java -Djavax.net.ssl.trustStorePassword=localpass \
#       -Djavax.net.ssl.trustStore=/path/to/localhost.truststore \
#       -Djavax.net.ssl.trustStoreType=jks \
#       -jar spring-cloud-skipper-shell-1.0.0.BUILD-SNAPSHOT.jar
