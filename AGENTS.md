# Trabajo guiado de PDS

Estas instrucciones se aplican a este repositorio y sus subdirectorios, también al retomar el proyecto en otras conversaciones.

## KISS y referencias de la asignatura

- El usuario debe poder comprender, explicar y defender cada cambio ante su profesor. Priorizar la solución más sencilla que cumpla el comportamiento solicitado.
- Para proponer código, utilizar únicamente mecanismos y patrones ya presentes en este proyecto o en el material local de `/home/peylau/Projects/PDS/pds-2026`. Leer la implementación relevante antes de atribuirle un patrón; localizar archivos no equivale a haberlos revisado.
- Explicar de qué archivo o ejemplo procede cada mecanismo que resulte nuevo para el usuario y cómo se adapta. No presentar una decisión propia como una exigencia del enunciado, de TPV o de una skill. Los métodos vacíos de los ejemplos no demuestran una implementación.
- No introducir tecnologías, dependencias, capas, abstracciones o concurrencia adicionales por recomendaciones generales o mejoras hipotéticas. Si las referencias disponibles no permiten resolver un problema, explicarlo al usuario antes de proponer algo fuera de ellas.
- Distinguir las reglas del registro o acceso de la gestión de errores exigida por las llamadas Java utilizadas. No añadir comprobaciones sin explicar su desencadenante y su finalidad.
- Para este trabajo, el usuario ha elegido llamadas HTTP directas como las del ejemplo TPV, aceptando que la interfaz espere la respuesta. No reintroducir `Task`, hilos de trabajo u otros mecanismos de concurrencia salvo que el usuario solicite cambiar este enfoque.

## Forma de trabajo

- El usuario escribe los cambios de la aplicación manualmente. Leer y revisar archivos y Git; no modificar código, hacer commits, push, merges ni cambios de rama sin una nueva petición explícita. La edición de estas instrucciones fue solicitada expresamente.
- Explicar en español y desde lo básico. Proponer un único paso pequeño cada vez, indicando ruta absoluta, método o bloque, código anterior y nuevo, y el motivo del cambio.
- Esperar a que el usuario termine y revisar sus cambios antes de avanzar. No entregar un bloque grande de conceptos desconocidos de una sola vez.
- Usar herramientas y materiales locales. No conectar cuentas ni instalar plugins externos para este proyecto.
- No probar persistencia contra la H2 habitual `./data/pds_db`. Usar una base temporal aislada y distinguir lectura de código, compilación, pruebas HTTP y comprobación visual realmente realizadas.
