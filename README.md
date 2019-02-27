# Skerna SLog (Standar Logger)

Skerna Standar logger, proporciona una API común tanto para el código multiplataforma
como para el código especifico (JAVA, JAVASCRIPT, C , OBETIVE C) permitiendo interactuar con una API solidad idependiente
del destino de compilación.

#### Actualmente solo es operable con 

- Java [Log4j || Slf4j, JUL] 
- NodeJs  [Console.log]
- Browsers [Console.log]
 
### Uso

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
-skerna.logger-delegate-factory-class-name=io.skerna.slog.internal.JULLogDelegateFactory
```
##### SLF4j
```bash
-skerna.logger-delegate-factory-class-name=io.skerna.slog.internal.SLF4JLogDelegateFactory
```
```bash
-skerna.logger-delegate-factory-class-name=io.skerna.slog.internal.SLF4JLogDelegateFactory
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


