# Este módulo principal contiene las clases y módulos que representan el juego **Irrgarten**.
#
# @author Joaquin Avilés de la Fuente
# @author Arturo Olivares Martos
module Irrgarten

require_relative 'dice'
require_relative 'player'
require_relative 'monster'
require_relative 'labyrinth'
require_relative 'game_state'
require_relative 'game_character'   # // TODO: Hace falta game_character.rb?
require_relative 'orientation'

    # Clase que representa el juego Irrgarten. Esta clase se encarga de gestionar el estado del juego,
    # las acciones de los jugadores y los monstruos, y de generar un estado del juego que pueda ser
    # consultado por la interfaz de usuario.
    #
    # @author Joaquin Avilés de la Fuente
    # @author Arturo Olivares Martos
    class Game
        @@MAX_ROUNDS = 10 # Número máximo de rondas de cada combate

        ####---------------------- PERSONALIZACIÓN LABERINTO ----------------

        # Número de filas del laberinto
        @@ROWS = 10

        # Número de columnas del laberinto
        @@COLS = 10

        # Información de los monstruos a añadir al laberinto
        @@MONSTER_INIT = [
            ["Monster 0", 0, 0],
            ["Monster 1", 1, 1],
            ["Monster 2", 2, 2],
            ["Monster 3", 3, 3]
        ]

        # Información de los bloques de obstáculos a añadir al laberinto
        @@BLOCKS = [
            [Orientation::HORIZONTAL,   0, 0,   3],
            [Orientation::VERTICAL,     0, 1,   4],
            [Orientation::HORIZONTAL,   3, 0,   8],
            [Orientation::VERTICAL,     2, 2,   5]
        ]
        #### ---------------------- PERSONALIZACIÓN LABERINTO ----------------

        # Constructor de la clase Game. Inicializa los atributos de la clase.
        #
        # @param n_players [int] número de jugadores
        def initialize(n_players)

            @players = Array.new
            @monsters = Array.new

            # Inicializa el array de jugadores
            n_players.times do |i|
                @players.push(Player.new(i.to_s, Dice.random_intelligence, Dice.random_strength))
            end

            @current_player_index = Dice.who_starts(n_players)  # Índice del jugador que comienza la partida
            @current_player = @players[@current_player_index]    # Jugador que tiene el turno actual

            # Inicializa el laberinto
            @labyrinth = Labyrinth.new(@@ROWS, @@COLS, Dice.random_pos(@@ROWS), Dice.random_pos(@@COLS))
            @labyrinth.configure_labyrinth()
            @labyrinth.spread_players(@players)

            @log = "Game just started.\n"

        end

        # Delega en el método del laberinto que indica si hay un ganador.
        #
        # @return [boolean] **true** si hay un ganador, **false** en caso contrario
        def finished
            return @labyrinth.have_a_winner
        end


        #def next_step(preferred_direction)
            # Sig. Práctica
        #end

        # Genera una instancia de GameState integrando toda la información del estado del juego.
        #
        # @return [GameState] Objeto GameState con la información del estado del juego
        def game_state
        info_players = ""
            @players.each do |player|
                info_players += player.to_s + ",\t"
            end

        info_monsters = ""
            @monsters.each do |monster|
                info_monsters += monster.to_s + ",\t"
            end

            return GameState.new(@labyrinth.to_s, info_players, info_monsters, current_player_index, self.finished, @log)
        end

        private

        # Configura el laberinto añadiendo bloques de obstáculos y monstruos.
        # Los monstruos, además de en el laberinto son guardados en el contenedor propio de esta clase para
        # este tipo de objetos.
        def configure_labyrinth

            # Añade los bloques de obstáculos al laberinto
            @@BLOCKS.each do |block|
                orientation, row, col, length = block
                @labyrinth.add_block(orientation, row, col, length)
            end


            # Añade los monstruos al laberinto y al contenedor de monstruos
            @@MONSTER_INIT.each do |monster_info|
                name, row, col = monster_info

                monster = Monster.new(name, Dice.random_intelligence, Dice.random_strength)

                @monsters.push(monster)
                @labyrinth.add_monster(row, col, monster)
            end
        end

        # Actualiza los dos atributos que indican el jugador con el turno pasando al siguiente jugador.
        def next_player
            @current_player_index = (@current_player_index + 1) % @players.size
            @current_player = @players[@current_player_index]
        end

        # Comprueba si la dirección preferred_direction es válida para el actual jugador, 
        # delegando para ello en el método move
        # @see Player#move
        # @param preferred_direction [Directions] HoLA
        #
        # @return [Directions] ADIOS
        def actual_direction(preferred_direction)
            current_row=@current_player.row
            currect_col=@current_player.col
            valid_moves=@labyrinth.valid_moves(current_row, currect_col)

            output=@current_player.move(preferred_direction, valid_moves)
        end

        #def combat(monster)
            # Sig. Práctica
        #end

        #def manage_reward(winner)
            # Sig. Práctica
        #end

        #def manage_resurrection()
            # Sig. Práctica
        #end

        # Añade al final del atributo log el mensaje indicando que el jugador ha ganado el combate.
        def log_player_won
            @log += "Player #{@current_player_index} won the fight.\n"
        end

        # Añade al final del atributo log el mensaje indicando que el monstruo ha ganado el combate.
        def log_monster_won
            @log += "Monster won the fight.\n"
        end

        # Añade al final del atributo log el mensaje indicando que el jugador ha resucitado.
        def log_resurrected
            @log += "Player #{@current_player_index} resurrected.\n"
        end

        # Añade al final del atributo log el mensaje indicando que el jugador ha perdido el turno por estar muerto.
        def log_player_skip_turn
            @log += "Player #{@current_player_index} skipped turn (is dead).\n"
        end

        # Añade al final del atributo log el mensaje indicando que el jugador no ha
        # seguido las instrucciones del jugador humano (no fue posible).
        def log_player_no_orders
            @log += "Player #{@current_player_index} didn't follow orders, it was not possible.\n"
        end

        # Añade al final del atributo log el mensaje indicando que el jugador se ha movido a una casilla vacía o no le ha sido posible moverse.
        def log_no_monster
            @log += "Player #{@current_player_index} moved to an empty cell or it was not possible to move.\n"
        end

        # Añade al final del atributo log el mensaje indicando que se han producido rounds de max rondas de combate.
        #
        # @param rounds [int] número de rondas de combate que ya se han producido
        # @param max [int] número máximo de rondas de combate
        def log_rounds(rounds, max)
            @log += "Rounds: #{rounds}/#{max}.\n"
        end

    end
end
