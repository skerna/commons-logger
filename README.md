# Skerna SLog (Standar Logger)

Skerna Standar logger, proporciona una API común tanto para el código multiplataforma
como para el código especifico (JAVA, JAVASCRIPT, C , OBETIVE C) permitiendo interactuar con una API solidad idependiente
del destino de compilación.

#### Actualmente solo es operable con 

- Java [Log4j || Slf4j, JUL] 
- NodeJs  [Console.log]
- Browsers [Console.log]
 

```kotlin
    class HotelService{
        val log = Logger.logger<HotelService>()
        
        fun addReservation(reservation:Reservation){
            log.debug("New Reservation ${reservation.id}")
        }
    }

```
 
### Configuración
Activar uno de los niveles, aunque tambien puede depender 
del archivo de configuración de cada Implementación

```kotlin
LoggerContext.enableDebug(true,this::class)
```

### Obtener Diag
```kotlin
LoggerContext.getDiagnostic()
```


# JAVA

Para java existe la posibilidad de configurar Tres tipos de Logs
- SLF4j
- JUL
- LOG4j
- Vertx

para configurar un logger desde -D properties usar la siguiente propiedad

r2b.logger-delegate-factory-class-name

##### Ejemplo
##### JUL 
```bash
-Dskerna.logger-delegate-factory-class-name=io.skerna.slog.internal.JULLogDelegateFactory
```
##### SLF4j
```bash
-Dskerna.logger-delegate-factory-class-name=io.skerna.slog.internal.SLF4JLogDelegateFactory
```
```bash
-Dskerna.logger-delegate-factory-class-name=io.skerna.slog.internal.SLF4JLogDelegateFactory
```

# Javascript

Usa el default console out

```kotlin
    init {
        LoggerFactory.setLogDelegateFactory(ConsoleLogDelegateFactory())
        
        val log = LoggerFactory.logger<SomeClass>()
        log.debug("test")
    }

```


### CONFIGURACIÓN JVM SLF4J && LOG4J2

```kotlin
    api("io.skerna.libs:skerna-slogs-jvm:${versionSlogs}")
    api("org.slf4j:slf4j-api:1.7.5")
    api("org.apache.logging.log4j:log4j-core:2.11.2")
    api("org.apache.logging.log4j:log4j-slf4j-impl:2.11.2")
```
Config Log4j2
```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN">
    <Properties>
        <Property name="logPath">target/cucumber-logs</Property>
        <Property name="rollingFileName">cucumber</Property>
    </Properties>
    <Appenders>
        <Console name="console" target="SYSTEM_OUT">
            <PatternLayout pattern="[%highlight{%-5level}] %d{DEFAULT} %c{1}.%M() - %msg%n%throwable{short.lineNumber}" />
        </Console>
        <RollingFile name="rollingFile" fileName="${logPath}/${rollingFileName}.log" filePattern="${logPath}/${rollingFileName}_%d{yyyy-MM-dd}.log">
            <PatternLayout pattern="[%highlight{%-5level}] %d{DEFAULT} %c{1}.%M() - %msg%n%throwable{short.lineNumber}" />
            <Policies>
                <!-- Causes a rollover if the log file is older than the current JVM's start time -->
                <OnStartupTriggeringPolicy />
                <!-- Causes a rollover once the date/time pattern no longer applies to the active file -->
                <TimeBasedTriggeringPolicy interval="1" modulate="true" />
            </Policies>
        </RollingFile>
    </Appenders>
    <Loggers>
        <Root level="DEBUG" additivity="false">
            <AppenderRef ref="console" />
            <AppenderRef ref="rollingFile" />
        </Root>
    </Loggers>
</Configuration>
```


