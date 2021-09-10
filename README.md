# M223 Punchclock

Folgende Schritte sind notwendig um die Applikation zu erstellen und zu starten: 
1. Stellen Sie sicher, dass OpenJDK 11 oder höher installiert und JAVA_HOME korrekt gesetzt ist.  
2. Installieren Sie (falls noch nicht vorhanden) Apache Maven 3.8.1 oder höher
3. Wechseln Sie auf der Kommandozeile in den Ordner dieser Appliation. 
`cd m223-helloworld-quarkus/`
4. Starten Sie die Applikation mit 
```shell script
./mvnw compile quarkus:dev
```

Folgende Dienste stehen während der Ausführung im Profil dev zur Verfügung:

REST-Schnittstelle der Applikation: http://localhost:8080/entries/
Swagger API: http://localhost:8080/q/swagger-ui/
Link für das GitHub Repository: https://github.com/alerispale0388/LB_Alessio_Rispoli.git


# Was kann die Applikation

In der Applikation kann man User bearbeiten, erstellen und entfernen. Das Gleiche kann man
auch für Activities machen. Man muss für dies jedoch angemeldet sein. Wenn man noch kein Login
hat, kann man sich neue registrieren. Nach der Registration kann man sich einlogen. Wenn man
eingeloggt ist, können die Funktionen benutzt werden. Wenn man sich ausloggt, muss man sich
beim nächsten mal wie üblich neu anmelden, da der Token verloren geht. 


# Abweichung zur Dokumentation

1. In der Entity Entry, sind die User und Projects als Objekte und nicht als id angegeben.