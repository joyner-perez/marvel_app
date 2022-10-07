# marvel_app

La key para frimar la app y la llave pública y privada para conectarse a la API de Marvel no están incluidas en el proyecto.

Para ejecutarlo debe comentar la parte relacionada con la firma de la app o crear una firma nueva.

Para la llave pública y privada de la API, debe crear las suyas en: https://developer.marvel.com/docs
Luego crear un fichero .properties con los suguientes campos:
  1. publicKey=XXXXXXXXXXXXXXXX
  2. privateKey=YYYYYYYYYYYYYYY
  
Luego editar el fichero key.properties, la ruta del campo apiFile que haga referencia a su fichero .properties creado
