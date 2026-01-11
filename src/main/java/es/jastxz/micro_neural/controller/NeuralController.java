package es.jastxz.micro_neural.controller;

import es.jastxz.micro_neural.model.ErrorResponse;
import es.jastxz.micro_neural.model.MundoRequest;
import es.jastxz.micro_neural.model.TableroResponse;
import es.jastxz.services.ServicioPredicciones;
import es.jastxz.tipos.Movimiento;
import es.jastxz.util.Util;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/v0")
public class NeuralController {

    @PostMapping("/tresenraya")
    public ResponseEntity<?> calculaJugadaRaya(@RequestBody MundoRequest request) {
        return calculaJugada(request, Util.juego3enRaya);
    }

    @PostMapping("/gatos")
    public ResponseEntity<?> calculaJugadaGatos(@RequestBody MundoRequest request) {
        return calculaJugada(request, Util.juegoGato);
    }

    @PostMapping("/damas")
    public ResponseEntity<?> calculaJugadaDamas(@RequestBody MundoRequest request) {
        return calculaJugada(request, Util.juegoDamas);
    }

    private ResponseEntity<?> calculaJugada(MundoRequest request, String juego) {
        try {
            // Comprueba si los datos son inválidos
            if (datosNoValidos(request, juego)) {
                ErrorResponse error = new ErrorResponse(
                        400,
                        "Bad Request",
                        "Petición mal formulada");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
            }

            // Procesamiento normal
            ServicioPredicciones servicioPredicciones = new ServicioPredicciones();
            Movimiento movimiento;
            TableroResponse response;
            switch (juego) {
                case Util.juego3enRaya:
                    movimiento = servicioPredicciones.predecir3enRaya(request.getData(), request.getTurno());
                    response = new TableroResponse(movimiento.getTablero().getMatrix().getData());
                    break;
                case Util.juegoGato:
                    movimiento = servicioPredicciones.predecirGatos(request.getData(), request.getTurno());
                    response = new TableroResponse(movimiento.getTablero().getMatrix().getData());
                    break;
                case Util.juegoDamas:
                    movimiento = servicioPredicciones.predecirDamas(request.getData(), request.getTurno());
                    response = new TableroResponse(movimiento.getTablero().getMatrix().getData());
                    break;
                default:
                    ErrorResponse error = new ErrorResponse(
                            400,
                            "Bad Request",
                            "Juego no soportado");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Error 500 para cualquier otro problema no controlado
            ErrorResponse error = new ErrorResponse(
                    500,
                    "Internal Server Error",
                    "Error interno del servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    private boolean datosNoValidos(MundoRequest request, String juego) {
        boolean raya = juego.equals(Util.juego3enRaya);
        boolean gato = juego.equals(Util.juegoGato);
        boolean damas = juego.equals(Util.juegoDamas);
        boolean turnoNoValido = request.getTurno() < 1 || request.getTurno() > 2;

        boolean matrizNoValida = true;
        if (raya) {
            matrizNoValida = request.getData().length != 3 || request.getData()[0].length != 3
                    || request.getData()[1].length != 3 || request.getData()[2].length != 3;
        } else if (gato || damas) {
            matrizNoValida = request.getData().length != 8 || request.getData()[0].length != 8
                    || request.getData()[1].length != 8 || request.getData()[2].length != 8;
        }

        return matrizNoValida || turnoNoValido;
    }

}
