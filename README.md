# Práctica 03: construcción automática con Maven

Proyecto de Ingeniería de Software II que demuestra configuración, compilación,
pruebas, empaquetado e instalación mediante un modelo de construcción `pom.xml`.

La aplicación calcula el promedio simple de una o más notas entre 0 y 20.
Acepta decimales con punto y rechaza entradas no numéricas, valores fuera de
rango, NaN e infinitos. El resultado se presenta con dos decimales.

## Requisitos

- JDK 17 o posterior, con `java` y `javac` disponibles.
- Git y conexión a Internet para la primera descarga de Maven y dependencias.
- Maven 3.9.11 queda fijado por Maven Wrapper; no es necesario instalar Maven globalmente.

La ejecución local documentada utiliza Windows 11 y JDK 21.0.12.1. El compilador
genera clases compatibles con Java 17 mediante `maven.compiler.release=17`.

## Configuración y construcción en Windows

1. Instalar un JDK 17 o posterior desde el proveedor oficial. Configurar
   `JAVA_HOME` con la carpeta del JDK y añadir su carpeta `bin` a `PATH`.
2. Abrir PowerShell y verificar `java -version` y `javac -version`.
3. Clonar y entrar al proyecto:

   ```powershell
   git clone https://github.com/GinoSebastianD/IS2-PC3-GG.git
   cd IS2-PC3-GG
   ```

4. Verificar la herramienta y construir:

   ```powershell
   .\mvnw.cmd -v
   .\mvnw.cmd -B -ntp clean install
   ```

5. Ejecutar el archivo generado:

   ```powershell
   java -jar target/calculadora-notas-1.0.0.jar 14 16 18
   ```

   Resultado: tres notas procesadas y promedio `16.00`.

En Linux o macOS se utiliza `./mvnw -B -ntp clean install`. También puede abrirse
`pom.xml` como proyecto Maven en un IDE y ejecutar las mismas fases desde su panel Maven.

## Modelo de construcción

- Identidad: `pe.edu.is2:calculadora-notas:1.0.0`, empaquetado `jar`.
- Compilación: Java 17 y codificación UTF-8.
- Dependencia de pruebas: JUnit Jupiter 5.11.4 con alcance `test`.
- Plugins fijados: Clean 3.4.0, Resources 3.3.1, Compiler 3.13.0,
  Surefire 3.5.2, JAR 3.4.2 e Install 3.1.3.
- Clase principal del manifiesto: `pe.edu.is2.notas.App`.
- La aplicación no requiere bibliotecas externas en tiempo de ejecución.

`clean` elimina los productos previos. `install` recorre las fases previas de
compilación, prueba, empaquetado y verificación y luego instala el artefacto en
el repositorio local. No publica el JAR en GitHub ni en Maven Central.

## Pruebas y resultados

```powershell
.\mvnw.cmd -B -ntp test
java -jar target/calculadora-notas-1.0.0.jar 12.5 15
java -jar target/calculadora-notas-1.0.0.jar 21
```

La suite contiene 11 casos: promedio de varias notas, decimales, extremos de la
escala, una nota, lista vacía, lista nula y cinco entradas inválidas. El ejemplo
decimal devuelve `13.75`; la nota `21` produce un mensaje de error y código de salida 1.

- JAR generado: `target/calculadora-notas-1.0.0.jar`.
- Reportes de prueba generados: `target/surefire-reports/`.
- Informe con capturas: [Informe_Practica_03.pdf](docs/Informe_Practica_03.pdf).
- Evidencias y registros: [docs/evidencias](docs/evidencias/).
- Construcción en GitHub: [Actions](https://github.com/GinoSebastianD/IS2-PC3-GG/actions).

`target/` y las cachés se excluyen de Git porque se regeneran con la construcción.
GitHub Actions conserva el JAR y los reportes como un artefacto descargable de cada
ejecución correcta. El flujo configura Java 17, ejecuta `clean install` y prueba
el inicio del JAR con las notas 14, 16 y 18.

## Entorno aislado de las evidencias

Para conservar las descargas dentro del proyecto, la ejecución documentada usó
estas variables temporales antes de invocar el Wrapper:

```powershell
$env:MAVEN_USER_HOME = Join-Path (Get-Location) '.m2'
$env:MAVEN_OPTS = '-Dmaven.repo.local=.m2/repository'
```

La descarga inicial del Wrapper falló por una conexión TLS del entorno Windows.
Se descargó la distribución oficial Maven 3.9.11, se comprobó su SHA-512 y se
preparó su caché local; luego se ejecutó el Wrapper sin modificar sus scripts.
El registro inicial se conserva en `docs/evidencias/00-incidencia-descarga.log`.
También se fija el SHA-256 de la distribución en las propiedades del Wrapper.
Estas variables son opcionales; en un equipo normal Maven usa el repositorio
del usuario, habitualmente `~/.m2/repository`.

Las imágenes del visor de evidencias son capturas de registros reales guardados
durante la ejecución. No representan una ventana de terminal interactiva.

## Referencias técnicas

- [Apache Maven: primeros pasos](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html).
- [Ciclo de construcción de Maven](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html).
- [Maven Wrapper](https://maven.apache.org/tools/wrapper/).
- [JUnit 5.11.4: guía oficial](https://docs.junit.org/5.11.4/user-guide/).

Los scripts `mvnw` y `mvnw.cmd` fueron generados por el plugin oficial Maven Wrapper
3.3.4 y conservan sus avisos de licencia Apache 2.0.
