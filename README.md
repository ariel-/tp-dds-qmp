# QMP

## Diagrama
![](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ariel-/tp-dds-qmp/master/assets/qmp.puml)

## Enunciado
1. [Parte 1](https://docs.google.com/document/d/1k1f-9AuIohlBGB2soSNePJ6jLxM37_tZeSD-hW_esIQ)
2. [Parte 2](https://docs.google.com/document/d/10j6XB9zIhl5xox2xBEDEFsgPmueHMkyvLSHcLxl_27Y)
3. Parte 3 (no)
4. [Parte 4](https://docs.google.com/document/d/1sy9S9EeIQr8fhatKnfTCgOfjVniJDu2viI-Av0gn0xY)
5. [Parte 5](https://docs.google.com/document/d/1wS622pMwZrDK9ilL_hEt5bBE04vKUKZILx8cIQ-aQzU)

## Decisiones Tomadas
* ServicioClima es una interfaz interna, _adapta_ a los posibles proveedores de clima. Esto resuelve el requerimiento de poder agregar nuevos proveedores
* Al implementar una caché entre el servicio externo y el proveedor de clima, resuelve el requerimiento de no generar gastos innecesarios por consultar al servicio externo, como contraparte se pierde un poco de actualidad en los datos.
* Pasar el momento actual como parámetro responde a la _testeabilidad_ del sistema, ya que no tengo que depender de la hora real actual y puedo testear con momentos arbitrarios.
