import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que implementa el listener de los botones del Buscaminas. De alguna
 * manera tendrá que poder acceder a la ventana principal. Se puede lograr
 * pasando en el constructor la referencia a la ventana. Recuerda que desde la
 * ventana, se puede acceder a la variable de tipo ControlJuego
 * 
 * @author Alberto Ventura Hermoso
 * @version 1.0
 **
 */
public class ActionBoton implements ActionListener {
	private VentanaPrincipal ventana;
	private int posicion_x;
	private int posicion_y;

	/**
	 * Constructor por defecto
	 */
	public ActionBoton() {
		// TODO
	}

	/**
	 * Constructor parametrizado
	 * 
	 * @param ventana
	 * @param posicion_x
	 * @param posicion_y
	 */
	public ActionBoton(VentanaPrincipal ventana, int posicion_x, int posicion_y) {
		this.ventana = ventana;
		this.posicion_x = posicion_x;
		this.posicion_y = posicion_y;
	}

	/**
	 * Acción que ocurrirá cuando pulsamos uno de los botones de juego.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO
		if (ventana.juego.abrirCasilla(posicion_x, posicion_y)) {
			ventana.mostrarNumMinasAlrededor(posicion_x, posicion_y);
			ventana.actualizarPuntuacion();
		} else {
			if (ventana.juego.getPuntuacion() == (ventana.juego.LADO_TABLERO * ventana.juego.LADO_TABLERO)
					- ventana.juego.MINAS_INICIALES) {
				ventana.mostrarFinJuego(false);

			} else {
				ventana.mostrarFinJuego(true);
			}
		}
		ventana.refrescarPantalla();
	}
}
