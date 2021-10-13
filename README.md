# QMP

## Diagrama
![](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ariel-/tp-dds-qmp/master/assets/qmp.puml)

## Enunciado
1. [Parte 1](https://docs.google.com/document/d/1k1f-9AuIohlBGB2soSNePJ6jLxM37_tZeSD-hW_esIQ)
2. [Parte 2](https://docs.google.com/document/d/10j6XB9zIhl5xox2xBEDEFsgPmueHMkyvLSHcLxl_27Y)
3. Parte 3 (no)
4. [Parte 4](https://docs.google.com/document/d/1sy9S9EeIQr8fhatKnfTCgOfjVniJDu2viI-Av0gn0xY)
5. [Parte 5](https://docs.google.com/document/d/1wS622pMwZrDK9ilL_hEt5bBE04vKUKZILx8cIQ-aQzU)
6. [Parte 6](https://docs.google.com/document/d/1NxqhJj70kt-_4aw-CawlISdJZyedzoOcLAVJAZVZISE)
7. [Parte 7](https://docs.google.com/document/d/1ERlDghk47Yc1_V1SQ7oCnZAC4bubHx7ZhQXS3naKMJA)

## Decisiones Tomadas
* ServicioClima es una interfaz interna, _adapta_ a los posibles proveedores de clima. Esto resuelve el requerimiento de poder agregar nuevos proveedores
* Al implementar una caché entre el servicio externo y el proveedor de clima, resuelve el requerimiento de no generar gastos innecesarios por consultar al servicio externo, como contraparte se pierde un poco de actualidad en los datos.
* Pasar el momento actual como parámetro responde a la _testeabilidad_ del sistema, ya que no tengo que depender de la hora real actual y puedo testear con momentos arbitrarios.

## API REST

- Como usuarie de QueMePongo quiero ver todas las prendas que tengo en mi guardarropa desde el navegador para poder administrarlas
  * URI + Método: GET `/prendas`
  * Body: vacío
  * Códigos de respuesta: 200 OK
  * Respuesta tipo: `[{ id, color, tipo ... }, { ... }, ...]`
    
- Como usuario de QueMePongo, quiero crear una prenda desde el navegador
  * URI + Método: POST `/prendas`
  * Body: `{ color, tipo, ... }`
  * Códigos de respuesta: 201 Created
  * Respuesta tipo: `{ nuevoID, color, tipo, ... }`

- Como usuarie de QueMePongo quiero ver una prenda en particular que tengo en mi guardarropa para poder editarla
  - Ver:
    * URI + Método: GET `/prendas/{id}`
    * Body: vacio
    * Códigos de respuesta: 200 OK, 404 Not Found
    * Respuesta tipo: (200) `{ id, color, tipo, ... }`, (404) vacío
  - Editar:
    * URI + Método: PUT `/prendas/{id}`
    * Body: `{color, tipo, ...}`
    * Códigos de respuesta: 200 OK, 404 Not Found
    * Respuesta tipo: (200) `{ id, nuevoColor, nuevoTipo, ... }`, (404) vacío

- Como usuarie de QueMePongo, quiero poder eliminar una prenda de mi guardarropa
  * URI + Método: DELETE `/prendas/{id}`
  * Body: vacio
  * Códigos de respuesta: 200 (OK), podría ser 204 (No Content); o bien 404 (Not Found) si no existe
  * Respuesta tipo: vacía

- Como usuarie de QueMePongo, quiero poder ver mis eventos para administrarlos
  * URI + Método: GET `/eventos`
  * Body: vacio
  * Códigos de respuesta: 200
  * Respuesta tipo: `[{id, fecha, lugar, ...}, {...}, ...]`

- Como usuarie de QueMePongo, quiero poder abrir las sugerencias de prendas para un evento
en mi navegador
  * URI + Método: GET `/eventos/{id}/sugerencias`
  * Body: vacio
  * Códigos de respuesta: 200 o bien 404
  * Respuesta tipo: (200) `[{ id, color, tipo ... }, { ... }, ...]`, (404) vacio
