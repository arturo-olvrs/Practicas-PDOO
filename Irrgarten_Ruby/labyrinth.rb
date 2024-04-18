module Irrgarten

require_relative 'dice'
require_relative 'player' 
require_relative 'monster' 
require_relative 'directions'
require_relative 'orientation' 

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

        # Método que posiciona los jugadores en el tablero, creando para ellos una posición
        # random y vacía en él. Delega su funcionamienot en el método put_player2D
        # @see Labyrinth#put_player2D
        #
        # @param players [Player []] vector de jugadores a introducir en el tablero
        def spread_players(players)
            players.each do |p|
                pos=self.random_empty_pos # int []
                self.put_player2D(-1,-1, pos[@@ROW], pos[@@COL], p)
                # //TODO: poner atributo de clase @@INVALID_POS=-1??? como en Player
            end
        end

        # Método que informa sobre si hay un ganador; es decir, si ningún jugador ha llegado a la salida.
        #
        # @return [boolean] **true** si hay un ganador y **false** si no lo hay
        def have_a_winner
            return @players[@exit_row][@exit_col] != nil
        end

        # Método que genera una cadena de caracteres con la información del laberinto
        #
        # @return [string] cadena de caracteres con la información del laberinto
        def to_s
            str = ""
            @labyrinth.each do |row|
                row.each do |cell|
                    str += cell + " "
                end
                str += "\n"
            end
            return str
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

        # Método que calcula la nueva posición a la que se desplazará el jugador, y después delega
        # su ejecución en el método put_player2D para actualizar la posición del jugador y devolver 
        # el posible monstruo al que se enfrentará el jugador (puede ser que no haya combate y por tanto
        # no hay monstruo)
        # @see Labyrinth#put_player_2D
        # 
        # @param direction [Directions] dirección a la que se quiere desplazar el jugador
        # @param player [Player] jugador que se moverá
        #
        # @return [Monster] posible monstruo con el que se enfrentará el jugador, si en la 
        # nueva casilla hay un monstruo
        def put_player(direction, player)
            old_row=player.row
            old_col=player.col

            new_pos=self.dir_2_pos(old_row,old_col,direction)

            # Devolvemos el monstruo
            monster=self.put_player2D(old_row, old_col, new_pos[@@ROW], new_pos[@@COL], player)

            return monster
        end

        # Método que coloca en el laberinto una serie de bloques. Destacar que en el caso
        # de que se encuentre una casilla que no este vacía (mirar método empty_pos) se dejará
        # de colocar bloques, aunque la siguiente casilla si este vacía
        # 
        # @param orientation [Orientation] orientación hacia la que se colocan los  bloques
        # @param start_row [int] fila de la casilla inicial
        # @param start_col [int] columna de la casilla inicial
        # @param length [int] longitud de los bloques que se pondrán
        def add_block(orientation, start_row, start_col, length)
            if(orientation==Orientation::VERTICAL)
                inc_row=1
                inc_col=0
            else
                inc_row=0
                inc_col=1
            end

            row=start_row
            col=start_col

            while ( (self.pos_ok(row,col)) && (self.empty_pos(row,col)) &&  # si pongo && abajo no funciona
                                                                (length>0) ) do
                @labyrinth[row][col]=@@BLOCK_CHAR
                length-=1
                row+=inc_row
                col+=inc_col
            end
        end

        # Método que obtiene una array de las direcciones válidas a partir
        # de una posición, es decir, comprueba si nos podemos desplazar hacia las 
        # direcciones posibles, y en caso afirmativo las añade al array.
        #
        # @param row [int] fila de la casilla
        # @param col [int] columna de la casilla
        #
        # @return [Directions []] array de las direcciones posibles hacia las que 
        # nos podemos desplazar desde la casilla dada
        def valid_moves(row, col)
            # Array de direcciones
            output=Array.new

            if(self.can_step_on(row+1, col))
                output.push(Directions::DOWN)
            end
            if(self.can_step_on(row-1, col))
                output.push(Directions::UP)
            end
            if(self.can_step_on(row, col+1))
                output.push(Directions::RIGHT)
            end
            if(self.can_step_on(row, col-1))
                output.push(Directions::LEFT)
            end

            return output
        end

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
            case direction  # switch en Ruby
                when Directions::LEFT
                    return row, col-1
                when Directions::RIGHT
                    return row, col+1
                when Directions::UP
                    return row-1, col
                when Directions::DOWN
                    return row+1, col
                # else para caso por defecto
                # Importante que no hace falta break
            end
        end

        # Utilizando el dado, genera una posición aleatoria en el laberinto (fila y columna) asegurando que esta esté vacía.
        # Genera internamente posiciones hasta que se cumple esta restricción y una vez generada se devuelve.
        # Si no hay posiciones vacías se producirá un bucle infinito.
        #
        # @return [int []] posición aleatoria en el laberinto
        def random_empty_pos
            begin
                row = Dice.random_pos(@n_rows)
                col = Dice.random_pos(@n_cols)
            end while !empty_pos(row, col)

            return row, col
        end

        # Método que actualiza la posición del jugador pasado, a la posición dada, actualizando
        # el estado de la casilla antigua y nueva. 
        #
        # Destacar que se comprueba si la posición nueva
        # es válida y si el numero del jugador pasado no coincide con el que hay en @players
        # no se cambia el estado de la casilla antigua del jugador, es decir, puede ser que no se
        # cambia la casilla antigua y si lo haga la nueva, lo que puede provocar que el numero del
        # jugador aparezca en dos posiciones, más adelante veremos como se gestiona esto 
        # //TODO: ver si la explicación es correcta y explicar como se soluciona el problema
        # DESTACAR que aunque la old_pos sea errónea se sigue hacienod el proceso con la nueva posición
        # ya que es el caso de inicializar los jugadores a posiciones
        #
        # Devuelve el monstruo de la nueva casilla si hay un combate en la posición actualizada.
        #
        # @param old_row [int] fila de la antigua posición
        # @param old_col [int] col de la antigua posición
        # @param row [int] fila de la nueva posición
        # @param col [int] col de la nueva posición
        # @param player [Player] jugador cuya posición se quiere actualizar
        #
        # @return [Monster] monstruo que se encuentra en la nueva posición (si hay combate) a la que
        # se ha movido el jugador. Si no hay ningún monstruo en la nueva casilla se pasará nil.
        def put_player2D(old_row, old_col, row, col, player)
            output=nil

            if(self.can_step_on(row, col))
                if(self.pos_ok(old_row, old_col))
                    p=@players[old_row][old_col]

                    # Comprobamos que el jugador pasado coincide con la casilla de 
                    # players, sino es así no se cambia el estado de la casilla antigua
                    # del jugador
                    if(p==player)
                        self.update_old_pos(old_row, old_col)
                        @players[old_row][old_col]=nil
                    end
                end

                # No da problema aunque la variable usada y el método se llamen igual
                monster_pos=self.monster_pos(row,col)

                if(monster_pos)
                    # Hay monstruo y cambiamos estado de casilla
                    @labyrinth[row][col]=@@COMBAT_CHAR
                    output=@monsters[row][col]
                else
                    number=player.number
                    # Ponemos en la casilla el número del jugador
                    @labyrinth[row][col]=number
                end

                # Actualizamos el array y el jugador con la nueva posición
                @players[row][col]=player
                player.pos(row,col)
            end

            return output
        end

    end
end
