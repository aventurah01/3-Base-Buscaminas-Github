import java.util.ArrayList;
import java.util.Random;

/**
 * Clase gestora del tablero de juego. Guarda una matriz de enteros representado
 * el tablero. Si hay una mina en una posición guarda el número -1 Si no hay una
 * mina, se guarda cuántas minas hay alrededor. Almacena la puntuación de la
 * partida
 * 
 * @author jesusredondogarcia
 *
 */
public class ControlJuego {
	private final static int MINA = -1;
	final int MINAS_INICIALES = 20;
	final int LADO_TABLERO = 10;

	private int[][] tablero;
	private int puntuacion;

	public ControlJuego() {
		// Creamos el tablero:
		tablero = new int[LADO_TABLERO][LADO_TABLERO];

		// Inicializamos una nueva partida
		inicializarPartida();
		depurarTablero();
	}

	/**
	 * Método para generar un nuevo tablero de partida:
	 * 
	 * @pre: La estructura tablero debe existir.
	 * @post: Al final el tablero se habrá inicializado con tantas minas como marque
	 *        la variable MINAS_INICIALES. El resto de posiciones que no son minas
	 *        guardan en el entero cuántas minas hay alrededor de la celda
	 */
	public void inicializarPartida() {
		Random rmd = new Random();
		int restantes = MINAS_INICIALES;
		// TODO: Repartir minas e inicializar puntaci�n. Si hubiese un tablero anterior,

		// lo pongo todo a cero para inicializarlo.

		// Poner posiciones tablero a 0

		// Poner puntuación a 0
		puntuacion = 0;
		// Colocar minas, mientras queden minas por colocar saca posicion aleatoria que
		// no tenga mina y coloca mina
		while (restantes > 0) {
			int x = rmd.nextInt(LADO_TABLERO);
			int y = rmd.nextInt(LADO_TABLERO);
			if (tablero[x][y] != MINA) {
				tablero[x][y] = MINA;
				restantes--;
			}
		}
		// Al final del m�todo hay que guardar el n�mero de minas para las casillas que
		// no son mina:
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				if (tablero[i][j] != MINA) {
					tablero[i][j] = calculoMinasAdjuntas(i, j);
				}
			}
		}
	}

	/**
	 * Cálculo de las minas adjuntas: Para calcular el número de minas tenemos que
	 * tener en cuenta que no nos salimos nunca del tablero. Por lo tanto, como
	 * mucho la i y la j valdrán LADO_TABLERO-1. Por lo tanto, como poco la i y la j
	 * valdrán 0.
	 * 
	 * 
	 * @param i: posición vertical de la casilla a rellenar
	 * @param j: posición horizontal de la casilla a rellenar
	 * @return : El número de minas que hay alrededor de la casilla [i][j]
	 **/
	private int calculoMinasAdjuntas(int i, int j) {
		int conatdor_minas = 0;
		int i_inicial = i - 1;
		if (i_inicial < 0) {
			i_inicial = 0;
		}
		int i_final = i + 1;
		if (i_final >= LADO_TABLERO) {
			i_final = 0;
		}

		int j_inicial = j - 1;
		if (j_inicial < 0) {
			j_inicial = 0;
		}
		int j_final = j + 1;
		if (j_final >= LADO_TABLERO) {
			j_final = 0;
		}
		for (int vertical = i_inicial; vertical <= i_final; vertical++) {
			for (int horizaontal = j_inicial; horizaontal <= j_final; horizaontal++) {
				if (tablero[vertical][horizaontal] == MINA) {
					conatdor_minas++;
				}
			}
		}
		return conatdor_minas;
	}

	/**
	 * Método que nos permite abrir una casilla. Devuelve verdadero y suma
	 * puntuación.
	 * 
	 * @pre : La casilla nunca debe haber sido abierta antes, no es controlado por
	 *      el ControlJuego. Por lo tanto siempre sumaremos puntos
	 * @param i: posición verticalmente de la casilla a abrir
	 * @param j: posición horizontalmente de la casilla a abrir
	 * @return : Verdadero si no ha explotado una mina. Falso en caso contrario.
	 */
	public boolean abrirCasilla(int i, int j) {
		boolean abierta = false;
		if (tablero[i][j] != MINA) {
			abierta = true;
			puntuacion++;
		}
		return abierta;
	}

	/**
	 * Método que checkea si se ha terminado el juego porque se han abierto todas
	 * las casillas.
	 * 
	 * @return Devuelve verdadero si se han abierto todas las celdas que no son
	 *         minas.
	 **/
	public boolean esFinJuego() {
		boolean fin;
		if (LADO_TABLERO * LADO_TABLERO - MINAS_INICIALES == puntuacion) {
			fin = true;
		} else {
			fin = false;
		}
		return fin;
	}

	/**
	 * Método que pinta por pantalla toda la información del tablero, se utiliza
	 * para depurar
	 */
	public void depurarTablero() {
		System.out.println("---------TABLERO--------------");
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				System.out.print(tablero[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("\nPuntuación: " + puntuacion);
	}

	/**
	 * Método que se utiliza para obtener las minas que hay alrededor de una celda
	 * 
	 * @pre : El tablero tiene que estar ya inicializado, por lo tanto no hace falta
	 *      calcularlo, símplemente consultarlo
	 * @param i : posición vertical de la celda.
	 * @param j : posición horizontal de la cela.
	 * @return Un entero que representa el número de minas alrededor de la celda
	 */
	public int getMinasAlrededor(int i, int j) {
		return tablero[i][j];
	}

	/**
	 * Método que devuelve la puntuación actual
	 * 
	 * @return Un entero con la puntuación actual
	 */
	public int getPuntuacion() {
		return puntuacion;
	}

}
