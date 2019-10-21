# Actividad UF2-2. Tarea de refuerzo. Comunicación cliente-servidor con identificación cliente y usando IP pública.

Se presenta la actividad con la clase Sevidor e HiloEscuchador en un paquete itt y la clase SocketCliente directamente del src. De esta manera, al pasar la clase compilada mis compañer@s podrán ejecutarla más facilmente.

Las pruebas las he realizado con el compañero Rubén Beltrán Muñoz, al cual le he facilitado la clase SocketCliente compilada con la configuración del puerto y la la dirección de conexion de la maquina que ejecuta el programa Servidor. Desde su máquina ha conectado con el programa servidor que se ejecutaba en mi equipo.

He utilizado el servicio **[No-Ip](https://www.noip.com)** para crear un hostname grauito y redireccionado hacia mi equipo. De esta manera aunque mi rotuer actualice la ip, gracias a este servicio no será necesario actualizar la clase SocketCliente, e incluso la podrán ejecutar otros clientes desde mi propia red local.

Para ello previamente he abierto en mi Router y en el Firewall de windows el puerto de comunicación TCP 2202.

Al mismo tiempo, también he realizado otra conexión desde mi portatil contectado a través del 4G del móvil, y también desde mi propia, la que se ejecuta el servidor, tambíen he arrancado otro cliente, para tener 3 clientes conectados simultaneamente.

A continuación se muestra las capturas de las conexión.

**Desde el equipo de Beltrán.**
![](Image&#32;007.png)

**Desde mi equipo** al a **izquierda el Servidor** y a la derecha un cliente.
![](Image&#32;008.png) ![](Image&#32;009.png)![](Image&#32;009.png)![](Image&#32;010.png)

**Desde el portatil con 4G**
![](Image&#32;011.png)

Las clases realizadas se encuentran comentadas paso por paso. Algunos comentarios contienen la etiqueta `STUDY`, la cual utilizo junto con la extensión **To do Tree** de VS Code, para marcar donde debo repasar y estudiar, y asi acceder de una forma clara y directa en una lista de acciones que crea la extensión.

La tecnología utilizada `Java` con las clases `Sockets`,  `ServerSocket`,  `BufferedReader`, `InputStreamReader`, `PrintStream`, `URL`, `URL.openStream()`.

Como herramienta IDE/Editor **Vs Code**, con las siguientes extensiones:
- [Java Extension Pack](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack).
- [Todo Tree](https://marketplace.visualstudio.com/items?itemName=Gruntfuggly.todo-tree)
- [Markdown All in One](https://marketplace.visualstudio.com/items?itemName=yzhang.markdown-all-in-one).
- [Markdown PDF](https://marketplace.visualstudio.com/items?itemName=yzane.markdown-pdf).

Acompaño la actividad con este documento *Markdonw* con la webgrafía utilizada para investión y resolver dudas.

## Webgrafía.
- [Getting the 'external' IP address in Java](https://stackoverflow.com/questions/2939218/getting-the-external-ip-address-in-java)
- [Thread java 8. setName(String name)](https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html)
- [URL Java 8](https://docs.oracle.com/javase/8/docs/api/java/net/URL.html)
- [Escribir en un fichero de texto con Java](http://lineadecodigo.com/java/escribir-en-un-fichero-de-texto-con-java/) 
- [Agregar contenido a un archivo sin sobrescribir el contenido](https://es.stackoverflow.com/questions/68526/agregar-contenido-a-un-archivo-sin-sobrescribir-el-contenido)