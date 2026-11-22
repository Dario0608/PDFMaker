# PDFMaker - Fusionador de PDFs en Java

Una aplicación de consola sencilla y eficiente construida en Java para combinar múltiples archivos PDF en un solo documento. El programa lee una carpeta específica, extrae todos los documentos válidos y los une generando un único archivo de salida.

##  Características
* Escanea automáticamente el directorio indicado buscando archivos con extensión `.pdf`.
* Fusiona los documentos respetando el orden alfabético.
* Procesa la fusión directamente en la memoria RAM para mayor velocidad.

##  Tecnologías utilizadas
* **Lenguaje:** Java 
* **Librería principal:** Apache PDFBox (v3.x)
* **Gestor de dependencias:** Maven
