module Irrgarten

require_relative 'dice'
# require_relative 'player' # No hace falta
# require_relative 'monster'  # No hace falta
require_relative 'directions'
require_relative 'orientation' # // TODO: Hace falta orientation.rb?

    # Clase que representa el tablero en sí, el laberinto del juego.
    #
    # @author Joaquin Avilés de la Fuente
    # @author Arturo Olivares Martos
    class Labyrinth

        @@BLOCK_CHAR = 'X'      # Caracter que representa un obstáculo
        @@EMPTY_CHAR = '-'      # Caracter que representa un espacio vacío
        @@MONSTER_CHAR = 'M'    # Caracter que representa un monstruo
        @@COMBAT_CHAR = 'C'     # Caracter que representa un combate
        @@EXIT_CHAR = 'E'       # Caracter que representa la salida

        # Variable que representa el orden de la fila en un array con dos componentes
        @@ROW = 0

        # Variable que representa el orden de la columna en un array con dos componentes
        @@COL = 1

        # Constructor de la clase Labyrinth. Inicializa los atributos de la clase.
        #
        # @param n_rows [int] número de filas del laberinto
        # @param n_cols [int] número de columnas del laberinto
        # @param exit_row [int] fila de la salida
        # @param exit_col [int] columna de la salida
        def initialize(n_rows, n_cols, exit_row, exit_col)
            @n_rows = n_rows.to_i
            @n_cols = n_cols.to_i
            @exit_row = exit_row.to_i
            @exit_col = exit_col.to_i

            @monsters  = Array.new(@n_rows) {Array.new(@n_cols)} # Matriz de monstruos
            @players   = Array.new(@n_rows) {Array.new(@n_cols)} # Matriz de jugadores
            @labyrinth = Array.new(@n_rows) {Array.new(@n_cols, @@EMPTY_CHAR)} # Matriz del laberinto. Valor por defecto: @@EMPTY_CHAR

            @labyrinth[exit_row][exit_col] = @@EXIT_CHAR # Pone la salida en el laberinto
        end


        #def spread_players(players)
            # Sig. Práctica
        #end

        # Método que informa sobre si hay un ganador; es decir, si ningún jugador ha llegado a la salida.
        #
        # @return [boolean] **true** si hay un ganador y **false** si no lo hay
        def have_a_winner
            return @players[exit_row][exit_col] != nil
        end

        # Método que genera una cadena de caracteres con la información del laberinto
        #
        # @return [string] cadena de caracteres con la información del laberinto
        def to_s
            @labyrinth.each do |row|
                row.each do |cell|
                    print cell
                end
                print "\n"
            end

            # // TODO: Imprimir también la posición de los jugadores? Esperar a ver qué se tiene en @players
        end

        # Si la posición suministrada está dentro del tablero y está vacía,
        # anota en el laberinto la presencia de un monstruo, guarda la referencia del monstruo en el
        # atributo contenedor adecuado e indica al monstruo cual es su posición actual (Monster#pos).
        #
        # Si la posición no está vacía, no hace nada.
        #
        # Si la posición no está dentro del tablero, no hace nada.
        #
        # @param row[int] fila de la posición
        # @param col [int] columna de la posición
        # @param monster [Monster] monstruo que se añade al laberinto
        def add_monster(row, col, monster)
            if (pos_ok(row, col) && empty_pos(row, col))
                @labyrinth[row][col] = @@MONSTER_CHAR
                monster.pos(row, col)
                @monsters[row][col] = monster
            end
        end

        # def put_player(direction, player)
            # Sig. Práctica
        # end

        #def add_block(orientation, start_row, start_col, length)
            # Sig. Práctica
        #end

        #def valid_moves(row, col)
            # Sig. Práctica
        #end

        private
        # Método que indica si la posición suministrada está dentro del tablero
        #
        # @param row [int] fila de la posición
        # @param col [int] columna de la posición
        # @return [boolean] **true** si la posición suministrada está dentro del tablero y **false** si no lo está.
        def pos_ok(row, col)
            return (0<=row && row<@n_rows) && (0<=col && col<@n_cols)
        end

        # Método que indica si la posición suministrada está vacía
        #
        # @param row [int] fila de la posición
        # @param col [int] columna de la posición
        # @return [boolean] **true** si la posición suministrada está vacía y **false** si no lo está.
        def empty_pos(row, col)
            return (@labyrinth[row][col] == @@EMPTY_CHAR) && (@players[row][col] == nil)
        end

        # Método que indica si la posición suministrada alberga exclusivamente un monstruo
        #
        # @param row [int] fila de la posición
        # @param col [int] columna de la posición
        # @return [boolean] **true** si la posición suministrada alberga exclusivamente un monstruo y **false** si no es así.
        def monster_pos(row, col)
            return (@labyrinth[row][col] == @@MONSTER_CHAR) # && (@players[row][col] == nil). No es necesario pq si está habrá un combate
        end

        # Método que indica si la posición suministrada es la de salida
        #
        # @param row [int] fila de la posición
        # @param col [int] columna de la posición
        # @return [boolean] **true** si la posición suministrada es la de salida y **false** si no lo es.
        def exit_pos(row, col)
            return (@labyrinth[row][col] == @@EXIT_CHAR)
        end

        # Método que indica si la posición suministrada alberga un combate (jugador y monstruo)
        #
        # @param row [int] fila de la posición
        # @param col [int] columna de la posición
        # @return [boolean] **true** si la posición suministrada alberga un combate (jugador y monstruo) y **false** si no es así.
        def combat_pos(row, col)
            return (@labyrinth[row][col] == @@COMBAT_CHAR)
        end

        # Indica si la posición suministrada está dentro del laberinto y se puede acceder; es decir,
        # corresponde con una de estas tres opciones: casilla vacía, casilla donde habita un monstruo o salida
        #
        # @param row [int] fila de la posición
        # @param col [int] columna de la posición
        # @return [boolean] **true** si la posición suministrada está dentro del laberinto y se puede acceder y **false** si no es así.
        def can_step_on(row, col)
            return pos_ok(row, col) && (empty_pos(row, col) || monster_pos(row, col) || exit_pos(row, col))
        end

        # Este método solo realiza su función si la posición suministrada está dentro del laberinto.
        # Si es el caso, si en esa posición el laberinto estaba indicando el estado de combate,
        # el estado de esa casilla del laberinto pasa a indicar que simplemente hay un monstruo.
        # En otro caso, el estado de esa casilla del laberinto pasa a indicar que está vacía.
        #
        # Este método es llamado cuando un jugador abandona una casilla y se encarga de dejar la casilla que
        # se abandona en el estado correcto.
        #
        # @param row [int] fila de la posición
        # @param col [int] columna de la posición
        def update_old_pos(row, col)
            if (pos_ok(row, col))
                # Si la posición es la de un combate, pone un monstruo en la posición
                if (combat_pos(row, col))
                    @labyrinth[row][col] = @@MONSTER_CHAR
                else # Si no, pone la posición vacía
                    @labyrinth[row][col] = @@EMPTY_CHAR
                end
            end
        end


        # Este método calcula la posición del laberinto a la que se llegaría si desde la posición suministrada
        # se avanza en la dirección pasada como parámetro. No es necesario realizar comprobaciones relativas a no
        # generar posiciones fuera del laberinto.
        #
        # @param row [int] fila de la posición
        # @param col [int] columna de la posición
        # @param direction [Directions] dirección en la que se avanza
        #
        # @return [int []] posición a la que se llegaría si se avanza en la dirección pasada como parámetro
        def dir_2_pos(row, col, direction)
            case direction
                when Directions::LEFT
                    return row, col-1
                when Directions::RIGHT
                    return row, col+1
                when Directions::UP
                    return row-1, col
                when Directions::DOWN
                    return row+1, col
            end
        end

        # Utilizando el dado, genera una posición aleatoria en el laberinto (fila y columna) asegurando que esta esté vacía.
        # Genera internamente posiciones hasta que se cumple esta restricción y una vez generada se devuelve.
        # Si no hay posiciones vacías se producirá un bucle infinito.
        #
        # @return [int []] posición aleatoria en el laberinto
        def random_empty_pos
            begin
                row = Dice.rand(@n_rows)
                col = Dice.rand(@n_cols)
            end while !empty_pos(row, col)

            return row, col
        end

        # def put_player2D(old_row, old_col, row, col, player)
            # Sig. Práctica
        #end

    end
end
