package mx.unam.sistop.tarea3;

import lombok.Value;
import mx.unam.sistop.tarea3.utilidades.UtilidadesNumericas;

import java.util.ArrayList;
import java.util.List;

@Value
public class CargaAleatoria {
    private static final int MAX_TIEMPO_DE_EJECUCION_POR_PROCESO = 10;
    private static final int TOTAL_PROCESOS = 10;

    List<Proceso> procesos;

    public static CargaAleatoria generar() {
        List<Proceso> procesosGenerados = new ArrayList<>();

        int tiempoTotal = 0, tiempoDeLlegadaAnterior = 0;
        for (int i = 0; i < TOTAL_PROCESOS; i++) {
            int tiempoDeLlegada = UtilidadesNumericas.obtenerEnteroInclusivo(tiempoDeLlegadaAnterior, tiempoTotal - 1);
            tiempoDeLlegadaAnterior = tiempoDeLlegada;

            int tiempoDeEjecucion = UtilidadesNumericas.obtenerEnteroPositivoAleatorio(MAX_TIEMPO_DE_EJECUCION_POR_PROCESO);
            tiempoTotal += tiempoDeEjecucion;

            Proceso proceso = new Proceso(tiempoDeLlegada, tiempoDeEjecucion);

            procesosGenerados.add(proceso);
        }

        return new CargaAleatoria(procesosGenerados);
    }

    public int getTiempoTotalDeEjecucion() {
        return procesos.stream()
                .mapToInt(Proceso::getTiempoDeEjecucion)
                .reduce(0, Integer::sum);
    }
}
