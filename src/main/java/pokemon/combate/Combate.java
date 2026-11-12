package pokemon.combate;

import pokemon.combate.movimientos.Ataque;
import pokemon.combate.movimientos.Estado;
import pokemon.combate.movimientos.Mejora;
import pokemon.combate.movimientos.Movimiento;
import pokemon.database.PokemonCRUD;
import pokemon.entrenador.Entrenador;
import pokemon.funcionalidad.Funcion;
import pokemon.pokemon.ListaEstados;
import pokemon.pokemon.Pokemon;
import pokemon.tienda.Objeto;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Motor del combate por turnos. No sabe nada de la interfaz: cada acción devuelve una lista de
 * {@link Paso} (un mensaje + cómo están los dos Pokémon en ese momento) que la pantalla va
 * mostrando uno a uno con sus animaciones.
 */
public class Combate {

    public enum Resultado { EN_CURSO, VICTORIA, DERROTA, RENDICION }

    /** Animación asociada a un paso. */
    public enum Efecto {
        NINGUNO, ATACA_JUGADOR, ATACA_RIVAL, GOLPE_JUGADOR, GOLPE_RIVAL, ENTRA_JUGADOR, ENTRA_RIVAL,
        SALE_JUGADOR, SALE_RIVAL, DEBILITA_JUGADOR, DEBILITA_RIVAL, MEJORA_JUGADOR, MEJORA_RIVAL, SUBE_NIVEL
    }

    /** Cómo está un lado del combate en un momento concreto. */
    public record Foto(String nombre, int nivel, double vida, double vidaMax, double estamina, double estaminaMax,
                       ListaEstados estado, String sprite, String spriteEspalda, boolean[] vivos) {
    }

    /** Un mensaje del combate y el estado de ambos lados justo después de que ocurra. */
    public record Paso(String texto, Efecto efecto, Foto jugador, Foto rival) {
    }

    private static final double PROB_CRITICO = 1.0 / 16;
    private static final double PRECISION = 0.95;

    private final List<Pokemon> equipoJugador;
    private final Rival rival;
    private int activoJugador;
    private int activoRival;
    private Resultado resultado = Resultado.EN_CURSO;
    private boolean necesitaCambio;
    private int dineroGanado;
    private int dineroPerdido;
    private int turno = 1;
    private final Set<Pokemon> conProgreso = new LinkedHashSet<>();
    private List<Paso> pasos;

    public Combate(List<Pokemon> equipoJugador, int inicial, Rival rival) {
        this.equipoJugador = equipoJugador;
        this.rival = rival;
        this.activoJugador = Math.max(0, Math.min(inicial, equipoJugador.size() - 1));
        for (Pokemon p : equipoJugador) {
            PokemonCRUD.completarMovimientos(p);
            p.prepararCombate();
        }
    }

    // =====================================================================================
    // Consultas
    // =====================================================================================

    public Pokemon jugador() {
        return equipoJugador.get(activoJugador);
    }

    public Pokemon pokemonRival() {
        return rival.getEquipo().get(activoRival);
    }

    public List<Pokemon> getEquipoJugador() {
        return equipoJugador;
    }

    public int getActivoJugador() {
        return activoJugador;
    }

    public Rival getRival() {
        return rival;
    }

    public Resultado getResultado() {
        return resultado;
    }

    /** true si el Pokémon del jugador se ha debilitado y hay que elegir otro antes de seguir. */
    public boolean necesitaCambio() {
        return necesitaCambio;
    }

    public int getDineroGanado() {
        return dineroGanado;
    }

    public int getDineroPerdido() {
        return dineroPerdido;
    }

    public int getTurno() {
        return turno;
    }

    public Foto fotoJugador() {
        return foto(equipoJugador, activoJugador);
    }

    public Foto fotoRival() {
        return foto(rival.getEquipo(), activoRival);
    }

    private static Foto foto(List<Pokemon> equipo, int activo) {
        Pokemon p = equipo.get(activo);
        boolean[] vivos = new boolean[equipo.size()];
        for (int i = 0; i < equipo.size(); i++) vivos[i] = !equipo.get(i).estaDebilitado();
        return new Foto(p.getNombreMostrado(), p.getNivel(), p.getVida(), p.getVitalidad(), p.getEstaminaActual(),
                p.getEstamina(), p.getEstado(), p.getFoto(), p.getFotoEspalda(), vivos);
    }

    // =====================================================================================
    // Acciones del jugador
    // =====================================================================================

    /** Mensajes de presentación del combate. */
    public List<Paso> inicio() {
        pasos = new ArrayList<>();
        paso("¡" + rival.getNombre() + " quiere luchar!", Efecto.NINGUNO);
        paso(rival.getNombre() + " saca a " + pokemonRival().getNombre() + ".", Efecto.ENTRA_RIVAL);
        paso("¡Adelante, " + jugador().getNombreMostrado() + "!", Efecto.ENTRA_JUGADOR);
        return pasos;
    }

    /** El jugador usa el movimiento número indice (0..3). */
    public List<Paso> usarMovimiento(int indice) {
        pasos = new ArrayList<>();
        Movimiento m = jugador().getMovimientos().get(indice);
        int rivalAntes = activoRival;
        if (vaPrimero(jugador(), pokemonRival())) {
            actuar(true, m);
            // Si el rival ha caído, el que sale nuevo no ataca en este turno.
            if (activoRival == rivalAntes) accionRival();
        } else {
            accionRival();
            if (sigueEnPie()) actuar(true, m);
        }
        finDeTurno();
        return pasos;
    }

    /** El jugador descansa para recuperar estamina. El rival actúa después. */
    public List<Paso> descansar() {
        pasos = new ArrayList<>();
        descansar(true);
        accionRival();
        finDeTurno();
        return pasos;
    }

    /**
     * Usa un objeto de la mochila sobre un Pokémon del equipo (que no tiene por qué ser el activo:
     * Revivir se usa sobre uno debilitado). Gasta una unidad del objeto.
     */
    public List<Paso> usarObjeto(Objeto objeto, Pokemon objetivo) {
        pasos = new ArrayList<>();
        PokemonCRUD.cambiarCantidad(objeto, -1);
        paso(Entrenador.getNombre() + " usó " + objeto.getNombre() + ".", Efecto.NINGUNO);
        switch (objeto.getCategoria()) {
            case CURACION -> {
                double curado = objetivo.curar(objetivo.getVitalidad() * objeto.getValor() / 100);
                paso(objetivo.getNombreMostrado() + " recuperó " + (int) Math.round(curado) + " puntos de vida.",
                        objetivo == jugador() ? Efecto.MEJORA_JUGADOR : Efecto.NINGUNO);
            }
            case ESTAMINA -> {
                double antes = objetivo.getEstaminaActual();
                objetivo.setEstaminaActual(antes + objetivo.getEstamina() * objeto.getValor() / 100);
                paso(objetivo.getNombreMostrado() + " recuperó " + (int) Math.round(objetivo.getEstaminaActual() - antes)
                        + " de estamina.", objetivo == jugador() ? Efecto.MEJORA_JUGADOR : Efecto.NINGUNO);
            }
            case REVIVIR -> {
                objetivo.setVida(objetivo.getVitalidad() * objeto.getValor() / 100);
                objetivo.setEstado(null);
                paso("¡" + objetivo.getNombreMostrado() + " se ha recuperado y puede volver a luchar!", Efecto.NINGUNO);
            }
            default -> paso("No tuvo ningún efecto.", Efecto.NINGUNO);
        }
        accionRival();
        finDeTurno();
        return pasos;
    }

    /**
     * Cambia el Pokémon activo. Si el anterior estaba debilitado el cambio es gratis;
     * si no, gasta el turno y el rival ataca al que entra.
     */
    public List<Paso> cambiar(int indice) {
        pasos = new ArrayList<>();
        boolean obligado = necesitaCambio;
        necesitaCambio = false;
        if (!obligado) {
            paso("¡" + jugador().getNombreMostrado() + ", vuelve!", Efecto.SALE_JUGADOR);
        }
        activoJugador = indice;
        conProgreso.add(jugador());
        paso("¡Adelante, " + jugador().getNombreMostrado() + "!", Efecto.ENTRA_JUGADOR);
        if (!obligado) {
            accionRival();
            finDeTurno();
        }
        return pasos;
    }

    /** El jugador se rinde: pierde dinero como si hubiera perdido. */
    public List<Paso> rendirse() {
        pasos = new ArrayList<>();
        resultado = Resultado.RENDICION;
        paso(Entrenador.getNombre() + " se ha rendido.", Efecto.NINGUNO);
        terminar();
        return pasos;
    }

    // =====================================================================================
    // Lógica del turno
    // =====================================================================================

    private boolean sigueEnPie() {
        return !jugador().estaDebilitado() && !pokemonRival().estaDebilitado();
    }

    private static boolean vaPrimero(Pokemon a, Pokemon b) {
        if (a.getVelocidad() != b.getVelocidad()) return a.getVelocidad() > b.getVelocidad();
        return Funcion.probabilidad(0.5);
    }

    /** Acción del rival: usa el movimiento elegido o descansa si no tiene estamina. */
    private void accionRival() {
        if (!sigueEnPie() || resultado != Resultado.EN_CURSO) return;
        Movimiento m = elegirMovimientoRival();
        if (m == null) {
            descansar(false);
        } else {
            actuar(false, m);
        }
    }

    /** IA sencilla: casi siempre el ataque que más daño hace; a veces estados o mejoras. */
    private Movimiento elegirMovimientoRival() {
        Pokemon yo = pokemonRival();
        Pokemon otro = jugador();
        List<Movimiento> usables = new ArrayList<>();
        for (Movimiento m : yo.getMovimientos()) if (yo.puedeUsar(m)) usables.add(m);
        if (usables.isEmpty()) return null;

        // Si va mal de vida y tiene un movimiento que cura, lo usa a veces.
        if (yo.getVida() < yo.getVitalidad() * 0.35) {
            for (Movimiento m : usables) {
                if (m instanceof Mejora mj && mj.getVitalidad() > 0 && Funcion.probabilidad(0.6)) return m;
            }
        }
        // Estados: solo si el jugador no tiene ya uno.
        if (otro.getEstado() == null) {
            for (Movimiento m : usables) {
                if (m instanceof Estado e && e.getEstado() != null && Funcion.probabilidad(0.25)) return m;
            }
        }
        // Mejoras de estadísticas al principio del combate.
        if (yo.getVida() > yo.getVitalidad() * 0.7) {
            for (Movimiento m : usables) {
                if (m instanceof Mejora mj && mj.getVitalidad() == 0 && Funcion.probabilidad(0.15)) return m;
            }
        }
        Movimiento mejor = null;
        double mejorDanio = -1;
        List<Movimiento> ataques = new ArrayList<>();
        for (Movimiento m : usables) {
            if (m instanceof Ataque a) {
                ataques.add(a);
                double d = danioEsperado(yo, otro, a);
                if (d > mejorDanio) {
                    mejorDanio = d;
                    mejor = a;
                }
            }
        }
        if (mejor == null) return usables.get(Funcion.random(0, usables.size() - 1));
        if (ataques.size() > 1 && Funcion.probabilidad(0.25)) return ataques.get(Funcion.random(0, ataques.size() - 1));
        return mejor;
    }

    /** Un Pokémon usa un movimiento. */
    private void actuar(boolean esJugador, Movimiento m) {
        Pokemon atacante = esJugador ? jugador() : pokemonRival();
        Pokemon defensor = esJugador ? pokemonRival() : jugador();
        String quien = nombreEnCombate(atacante, esJugador);

        // Estados que pueden impedir actuar
        if (atacante.getEstado() == ListaEstados.CONFUSO) {
            paso(quien + " está confuso...", Efecto.NINGUNO);
            if (Funcion.probabilidad(1.0 / 3)) {
                atacante.recibirDanio(Math.max(1, atacante.getVitalidad() * 0.08));
                paso("¡Está tan confuso que se ha herido a sí mismo!", esJugador ? Efecto.GOLPE_JUGADOR : Efecto.GOLPE_RIVAL);
                if (atacante.estaDebilitado()) debilitado(esJugador);
                return;
            }
        } else if (atacante.getEstado() == ListaEstados.ENAMORADO && Funcion.probabilidad(0.25)) {
            paso(quien + " está enamorado y no se atreve a atacar.", Efecto.NINGUNO);
            return;
        }

        if (!atacante.puedeUsar(m)) {
            descansar(esJugador);
            return;
        }
        atacante.setEstaminaActual(atacante.getEstaminaActual() - m.costeEstamina());
        paso(quien + " usó " + m.getNombreBonito() + ".", esJugador ? Efecto.ATACA_JUGADOR : Efecto.ATACA_RIVAL);

        if (m instanceof Ataque a) {
            if (!Funcion.probabilidad(PRECISION)) {
                paso("¡Pero ha fallado!", Efecto.NINGUNO);
                return;
            }
            double multiplicador = TablaTipos.multiplicador(a.getTipo(), defensor);
            if (multiplicador == 0) {
                paso("No afecta a " + nombreEnCombate(defensor, !esJugador) + "...", Efecto.NINGUNO);
                return;
            }
            boolean critico = Funcion.probabilidad(PROB_CRITICO);
            double danio = calcularDanio(atacante, defensor, a, multiplicador, critico);
            defensor.recibirDanio(danio);
            paso(null, esJugador ? Efecto.GOLPE_RIVAL : Efecto.GOLPE_JUGADOR);
            if (critico) paso("¡Un golpe crítico!", Efecto.NINGUNO);
            String eficacia = TablaTipos.efectividadMovimiento(multiplicador);
            if (!eficacia.isEmpty()) paso(eficacia, Efecto.NINGUNO);
            if (defensor.estaDebilitado()) debilitado(!esJugador);
        } else if (m instanceof Estado e) {
            if (e.getEstado() == null || defensor.getEstado() != null) {
                paso("¡Pero no ha tenido ningún efecto!", Efecto.NINGUNO);
            } else {
                defensor.setEstado(e.getEstado(), e.duracion());
                paso("¡" + nombreEnCombate(defensor, !esJugador) + " ahora está " + e.getEstado().getNombre().toLowerCase() + "!",
                        esJugador ? Efecto.GOLPE_RIVAL : Efecto.GOLPE_JUGADOR);
            }
        } else if (m instanceof Mejora mj) {
            Efecto ef = esJugador ? Efecto.MEJORA_JUGADOR : Efecto.MEJORA_RIVAL;
            boolean algo = false;
            if (mj.getVitalidad() > 0) {
                double curado = atacante.curar(atacante.getVitalidad() * mj.getVitalidad() / 100.0);
                if (curado > 0) {
                    paso(quien + " recuperó " + (int) Math.round(curado) + " puntos de vida.", ef);
                    algo = true;
                }
            }
            if (atacante.mejorar(mj.getAtaque(), mj.getDefensa(), mj.getAtaqueEspecial(), mj.getDefensaEspecial())) {
                List<String> subidas = new ArrayList<>();
                if (mj.getAtaque() > 0) subidas.add("el ataque");
                if (mj.getDefensa() > 0) subidas.add("la defensa");
                if (mj.getAtaqueEspecial() > 0) subidas.add("el ataque especial");
                if (mj.getDefensaEspecial() > 0) subidas.add("la defensa especial");
                paso("¡Ha subido " + String.join(" y ", subidas) + " de " + quien + "!", ef);
                algo = true;
            }
            if (!algo) paso("¡Pero no ha tenido ningún efecto!", Efecto.NINGUNO);
        }
    }

    private void descansar(boolean esJugador) {
        Pokemon p = esJugador ? jugador() : pokemonRival();
        double antes = p.getEstaminaActual();
        p.setEstaminaActual(antes + p.getEstamina() * 0.35 + 10);
        String quien = nombreEnCombate(p, esJugador);
        String texto = esJugador ? quien + " descansa y recupera " + (int) Math.round(p.getEstaminaActual() - antes) + " de estamina."
                : quien + " está agotado y se pone a descansar.";
        paso(texto, esJugador ? Efecto.MEJORA_JUGADOR : Efecto.MEJORA_RIVAL);
    }

    /**
     * Fórmula de daño: potencia × (ataque / defensa), con bonificación por tipo propio (x1.5),
     * efectividad, golpe crítico (x1.5) y un poco de azar. Escala para que un Pokémon aguante unos 3-5 golpes.
     */
    static double calcularDanio(Pokemon atacante, Pokemon defensor, Ataque a, double multiplicador, boolean critico) {
        double atk = a.esFisico() ? atacante.getAtaqueCombate() : atacante.getAtaqueEspecialCombate();
        double def = a.esFisico() ? defensor.getDefensaCombate() : defensor.getDefensaEspecialCombate();
        // La raíz suaviza las diferencias extremas (p. ej. 130 de ataque contra 25 de defensa).
        double base = a.getPotencia() * Math.pow(atk / Math.max(1, def), 0.75) * 0.3 + 3;
        double nivel = 1 + (atacante.getNivel() - defensor.getNivel()) * 0.03;
        double stab = atacante.tieneTipo(a.getTipo()) ? 1.5 : 1.0;
        double azar = Funcion.random(0.85, 1.0);
        return Math.max(1, base * Math.max(0.5, nivel) * stab * multiplicador * (critico ? 1.5 : 1) * azar);
    }

    private static double danioEsperado(Pokemon atacante, Pokemon defensor, Ataque a) {
        double mult = TablaTipos.multiplicador(a.getTipo(), defensor);
        double atk = a.esFisico() ? atacante.getAtaqueCombate() : atacante.getAtaqueEspecialCombate();
        double def = a.esFisico() ? defensor.getDefensaCombate() : defensor.getDefensaEspecialCombate();
        return a.getPotencia() * (atk / Math.max(1, def)) * (atacante.tieneTipo(a.getTipo()) ? 1.5 : 1) * mult;
    }

    /** Daño de estados al final del turno y comprobación de fin de combate. */
    private void finDeTurno() {
        if (resultado != Resultado.EN_CURSO) return;
        for (boolean esJugador : new boolean[]{true, false}) {
            Pokemon p = esJugador ? jugador() : pokemonRival();
            Pokemon otro = esJugador ? pokemonRival() : jugador();
            if (p.estaDebilitado() || p.getEstado() == null) continue;
            String quien = nombreEnCombate(p, esJugador);
            Efecto golpe = esJugador ? Efecto.GOLPE_JUGADOR : Efecto.GOLPE_RIVAL;
            switch (p.getEstado()) {
                case ENVENENADO -> {
                    p.recibirDanio(Math.max(1, p.getVitalidad() * 0.08));
                    paso(quien + " sufre por el veneno.", golpe);
                }
                case MALDITO -> {
                    p.recibirDanio(Math.max(1, p.getVitalidad() * 0.12));
                    paso(quien + " sufre la maldición.", golpe);
                }
                case DRENADO -> {
                    double robado = p.recibirDanio(Math.max(1, p.getVitalidad() * 0.08));
                    if (!otro.estaDebilitado()) otro.curar(robado);
                    paso(nombreEnCombate(otro, !esJugador) + " le roba vida a " + quien + ".", golpe);
                }
                default -> {
                }
            }
            ListaEstados estado = p.getEstado();
            if (p.estaDebilitado()) {
                debilitado(esJugador);
            } else if (p.pasarTurnoEstado()) {
                paso(quien + " ya no está " + estado.getNombre().toLowerCase() + ".", Efecto.NINGUNO);
            }
            if (resultado != Resultado.EN_CURSO) return;
        }
        turno++;
    }

    /** Gestiona que un Pokémon se haya debilitado: experiencia, siguiente Pokémon o fin del combate. */
    private void debilitado(boolean esJugador) {
        if (esJugador) {
            paso("¡" + jugador().getNombreMostrado() + " se ha debilitado!", Efecto.DEBILITA_JUGADOR);
            if (equipoJugador.stream().allMatch(Pokemon::estaDebilitado)) {
                resultado = Resultado.DERROTA;
                paso("¡A " + Entrenador.getNombre() + " no le quedan Pokémon que puedan luchar!", Efecto.NINGUNO);
                terminar();
            } else {
                necesitaCambio = true;
            }
            return;
        }

        Pokemon caido = pokemonRival();
        paso("¡El " + caido.getNombre() + " rival se ha debilitado!", Efecto.DEBILITA_RIVAL);
        darExperiencia(caido);

        int siguiente = -1;
        for (int i = 0; i < rival.getEquipo().size(); i++) {
            if (!rival.getEquipo().get(i).estaDebilitado()) {
                siguiente = i;
                break;
            }
        }
        if (siguiente < 0) {
            resultado = Resultado.VICTORIA;
            paso("¡Has derrotado a " + rival.getNombre() + "!", Efecto.NINGUNO);
            terminar();
        } else {
            activoRival = siguiente;
            paso(rival.getNombre() + " saca a " + pokemonRival().getNombre() + ".", Efecto.ENTRA_RIVAL);
        }
    }

    private void darExperiencia(Pokemon caido) {
        Pokemon p = jugador();
        if (p.estaDebilitado()) return;
        int xp = 18 + caido.getNivel() * 12 + (int) (caido.sumaStats() / 25);
        double vidaMaxAntes = p.getVitalidad();
        int niveles = p.ganarExperiencia(xp);
        conProgreso.add(p);
        paso(p.getNombreMostrado() + " ha ganado " + xp + " puntos de experiencia.", Efecto.NINGUNO);
        if (niveles > 0) {
            // Al subir de nivel crece la vida máxima: lo que crece se suma también a la vida actual.
            p.curar(p.getVitalidad() - vidaMaxAntes);
            paso("¡" + p.getNombreMostrado() + " ha subido al nivel " + p.getNivel() + "!", Efecto.SUBE_NIVEL);
        }
        PokemonCRUD.guardarProgreso(p);
    }

    /** Reparte el dinero al acabar y guarda el progreso. */
    private void terminar() {
        for (Pokemon p : conProgreso) PokemonCRUD.guardarProgreso(p);
        if (resultado == Resultado.VICTORIA) {
            double nivelMedio = rival.getEquipo().stream().mapToInt(Pokemon::getNivel).average().orElse(1);
            dineroGanado = Funcion.random(300, 500) + (int) Math.round(nivelMedio * 40) + rival.getEquipo().size() * 25;
            PokemonCRUD.actualizarDinero(Entrenador.getDinero() + dineroGanado);
            paso("Has ganado " + Funcion.dinero(dineroGanado) + ".", Efecto.NINGUNO);
        } else {
            dineroPerdido = Entrenador.getDinero() / 3;
            PokemonCRUD.actualizarDinero(Entrenador.getDinero() - dineroPerdido);
            if (dineroPerdido > 0) paso("Has perdido " + Funcion.dinero(dineroPerdido) + "...", Efecto.NINGUNO);
        }
    }

    private String nombreEnCombate(Pokemon p, boolean esJugador) {
        return esJugador ? p.getNombreMostrado() : "el " + p.getNombre() + " rival";
    }

    private void paso(String texto, Efecto efecto) {
        String t = texto;
        if (t != null && !t.isEmpty()) t = Character.toUpperCase(t.charAt(0)) + t.substring(1);
        pasos.add(new Paso(t, efecto, fotoJugador(), fotoRival()));
    }
}
