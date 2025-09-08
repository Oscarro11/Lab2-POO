Proyecto: Laboratorio de POO 2 (juego de memoria)

Este repositorio contiene un programa desarrollado en **Java** que hace uso de caracteres especiales y/o emojis en la salida por consola.  
Debido a esto, es necesario configurar correctamente la **codificación de la consola de PowerShell** para que los caracteres se muestren de forma adecuada.

------------------------------------------------------------
🚀 Instrucciones de uso
------------------------------------------------------------

1. Clona este repositorio en tu equipo:
   git clone https://github.com/usuario/repositorio.git

2. Abre una ventana de PowerShell.

3. Antes de ejecutar el programa, asegúrate de configurar la consola con los siguientes comandos:
   chcp 65001
   [Console]::OutputEncoding = [System.Text.Encoding]::UTF8

   Estos comandos permiten que la consola soporte UTF-8, evitando errores de visualización como caracteres desconocidos o símbolos extraños.

4. Compila y ejecuta el programa Java:
   javac Main.java
   java Main

------------------------------------------------------------
📋 Requisitos
------------------------------------------------------------
- Tener instalado Java JDK versión 8 o superior.
- Uso de PowerShell como terminal recomendada.

------------------------------------------------------------
📖 Notas
------------------------------------------------------------
- Si no se ejecutan los comandos de configuración previos, es posible que la salida muestre caracteres corruptos (ejemplo:  ).
- El proyecto está pensado para ser portable y compatible con cualquier sistema donde PowerShell y Java estén disponibles.

------------------------------------------------------------
🤝 Contribuciones
------------------------------------------------------------
Las contribuciones son bienvenidas. Si deseas mejorar este proyecto, puedes hacer un fork del repositorio y abrir un pull request con tus cambios.