# Actividad UF3-2. Servidor RESTful buscador de personas.

Se presenta la actividad de dos formas de montar los servicios.

- WebServiceA. Sólo como beans la clase Person.
- WebServiceB. Con un beans People, para mostar con @XmlRootElement en el xml personalizado.

Realizando la actividad, he comprobado que al pasar un Objeto de elementos, como en este caso el ArrayList de Personas, por dejecto Jersey en XML te genera el root que el cree oportuno siguiendo las reglas ortográficas del ingles para el plural. Por ejemplo, si la clase es Person, el RootElement en xml en este caso seria People. Si la clase fuera Child, el @RootElement en este caso seria Childrens. Por esto, si tenemos la clase Persona, por defecto y siguiendo las reglas del plural en ingles, Jersey forma el XML de varios elementos de esta clase con el elemento root Personas, añadiendo una s al final de Persona, termina el vocal.


**WebService A XML**
![](img-001.png)


**WebService B XML**
![](img-002.png)

Con JSON, sino se crea una clase de Grupo, este no indica el tipo de elemento que es, sin embargo al aplicar la clase de grupo, el JSON es generado teniendo encuenta el nombre de la clase como elemento PADRE de un array de objetos. Aunque para Json no es necesario indicar el Root principal.

**WebService A JSON**
![](img-003.png)


**WebService B JSON**
![](img-004.png)
 
Las clases realizadas se encuentran comentadas paso por paso. Algunos comentarios contienen la etiqueta `STUDY`, la cual utilizo junto con la extensión **To do Tree** de VS Code, para marcar donde debo repasar y estudiar, y asi acceder de una forma clara y directa en una lista de acciones que crea la extensión.

La tecnología utilizada `Java EE`, `RESTful`, `Jersey`, `Tomcat 9`.

Como herramienta IDE/Editor **Eclipse 2019-09** y **Vs Code**, con las siguientes extensiones:
- [Jersy](https://eclipse-ee4j.github.io/jersey/)
- [Apache Tomcat](https://tomcat.apache.org/)]
- [Java Extension Pack](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack).
- [Todo Tree](https://marketplace.visualstudio.com/items?itemName=Gruntfuggly.todo-tree)
- [Markdown All in One](https://marketplace.visualstudio.com/items?itemName=yzhang.markdown-all-in-one).
- [Markdown PDF](https://marketplace.visualstudio.com/items?itemName=yzane.markdown-pdf).

Acompaño la actividad con este documento *Markdonw* con la webgrafía utilizada para investión y resolver dudas.

## Webgrafía.
[XmlRootElement GenericEntity Jersey and Resteasy](https://stackoverflow.com/questions/34494933/xmlrootelement-genericentity-jersey-and-resteasy)
[Is there a way to get the Jersey automatically generated plural element name around a collection of JAXB elements with EclipseLink Moxy?](https://stackoverflow.com/questions/18925455/is-there-a-way-to-get-the-jersey-automatically-generated-plural-element-name-aro)
[Collection<?> wrap elements in plural name of actual type in JAXB](https://stackoverflow.com/questions/26844411/collection-wrap-elements-in-plural-name-of-actual-type-in-jaxb)