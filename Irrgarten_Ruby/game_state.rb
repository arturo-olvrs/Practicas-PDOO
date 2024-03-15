module Irrgarten

    # Esta clase permitirá, de forma muy sencilla, almacenar una representación del estado completo del
    # juego: el estado del laberinto, el estado de los jugadores, el estado de los monstruos, el índice del
    # jugador que tiene el turno, un indicador sobre si ya hay un ganador y un atributo adicional para
    # guardar en una cadena de caracteres eventos interesantes que hayan ocurrido desde el turno anterior.
    #
    # @author Joaquin Avilés de la Fuente
    # @author Arturo Olivares Martos
    class GameState
        
        # Método que devuelve el estado del juego
        #
        # @return devuelve el estado del juego
        def initialize (labyrinth, players, monsters, current_player, winner, log)
            @labyrinth=labyrinth
            @players=players
            @monsters=monsters
            @current_player=current_player # Representa el índice del jugador actual
            @winner=winner
            @log=log
        end

        # Método que devuelve el estado de cada atributo del juego
        attr_reader :labyrinth, :players, :monsters, 
                    :current_player, :winner, :log
    end

end