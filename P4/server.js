var http = require("http");
var url = require("url");
var fs = require("fs");
var path = require("path");
var socketio = require("socket.io");
var MongoClient = require('mongodb').MongoClient;
var MongoServer = require('mongodb').Server;
var mimeTypes = {"html": "text/html", "jpeg": "image/jpeg", "jpg": "image/jpeg", "png": "image/png", "js": "text/javascript", "css": "text/css", "swf": "application/x-shockwave-flash"};

var httpServer = http.createServer(
	function(request, response) {
		var uri = url.parse(request.url).pathname;
		if (uri=="/") uri = "/cliente.html";
		var fname = path.join(process.cwd(), uri);
		fs.exists(fname, function(exists) {
			if (exists) {
				fs.readFile(fname, function(err, data){
					if (!err) {
						var extension = path.extname(fname).split(".")[1];
						var mimeType = mimeTypes[extension];
						response.writeHead(200, mimeType);
						response.write(data);
						response.end();
					}
					else {
						response.writeHead(200, {"Content-Type": "text/plain"});
						response.write('Error de lectura en el fichero: '+uri);
						response.end();
					}
				});
			}
			else{
				console.log("Peticion invalida: "+uri);
				response.writeHead(200, {"Content-Type": "text/plain"});
				response.write('404 Not Found\n');
				response.end();
			}
		});
	}
);

// Actuadores
var sistemaAC = 'Desactivado';
var estadoPersiana = 'Subida';

// Variables para el control de los actuadores
var minLuminosidad = 35; // Mínimo de luminosidad para subir la persiana
var maxLuminosidad = 75; // Máximo de luminosidad para bajar la persiana
var minTemperatura = 18; // Mínimo de temperatura para encender el aire acondicionado (suponemos que se enciende en Modo Calor)
var maxTemperatura = 28; // Mínimo de temperatura para encender el aire acondicionado (suponemos que se enciende en Modo Frío)

MongoClient.connect("mongodb://localhost:27017/", {useUnifiedTopology: true}, function(err, db) {
	httpServer.listen(8080);
	var io = socketio.listen(httpServer);

	var dbo = db.db("sensoresBD");
	dbo.createCollection("sensores", function(err, collection){
    	io.sockets.on('connection',
		function(client) {
            // Insertamos los datos recibidos de los sensores
			client.on('poner', function (data) {
				collection.insert(data, {safe:true}, function(err, result) {});

				console.log("Llega");

				// Ahora, con los datos de la temperatura y luminosidad, decidimos si lanzar los actuadores
				if(data.temperatura <= minTemperatura || data.temperatura >= maxTemperatura) {
					io.sockets.emit('encenderAC');
				}

				else {
					io.sockets.emit('apagarAC');
				}

				if(data.luminosidad <= minLuminosidad) {
					io.sockets.emit('subirPersiana');
				}

				else if(data.luminosidad >= maxLuminosidad) {
					io.sockets.emit('bajarPersiana');
				}
            });

			// Obtener el estado de la persiana
			client.on('getEstadoPersiana', function() {
				client.emit('estadoPersiana', estadoPersiana);
			});

			// Obtener el estado del aire acondicionado
			client.on('getEstadoAC', function() {
				client.emit('estadoAC', sistemaAC);
			});

			// Cambiar el estado de la persiana
			client.on('cambiarEstadoPersiana', function() {
				if(estadoPersiana == 'Subida') {
					estadoPersiana = 'Bajada';
				}

				else {
					estadoPersiana = 'Subida';
				}

				// Tenemos que notificar a todos los clientes activos
				io.sockets.emit('actualizarPersiana', estadoPersiana);
			});

			// Cambiar el estado de la persiana
			client.on('cambiarEstadoAC', function() {
				if(sistemaAC == 'Desactivado') {
					sistemaAC = 'Activado';
				}

				else {
					sistemaAC = 'Desactivado';
				}

				// Tenemos que notificar a todos los clientes activos
				io.sockets.emit('actualizarAC', sistemaAC);
			});
		});
    });
});

console.log("Servicio iniciado");