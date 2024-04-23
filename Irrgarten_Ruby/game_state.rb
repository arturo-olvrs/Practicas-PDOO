# encoding: UTF-8
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
        # @param labyrinth [String] estado del laberinto
        # @param players [String] estado de los jugadores
        # @param monsters [String] estado de los monstruos
        # @param current_player [int] índice del jugador que tiene el turno
        # @param winner [boolean] Indica si hay ya un ganador o no
        # @param log [String] cadena de caracteres con eventos interesantes
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

        # Método que devuelve el estado del laberinto
        # @return [String] Devuelve el estado del laberinto
        attr_reader :labyrinth

        # Método que devuelve el estado de los jugadores
        # @return [String] Devuelve el estado de los jugadores
        attr_reader :players

        # Método que devuelve el estado de los monstruos
        # @return [String] Devuelve el estado de los monstruos
        attr_reader :monsters

        # Método que devuelve el índice del jugador que tiene el turno
        # @return [int] Devuelve el índice del jugador que tiene el turno
        attr_reader :current_player

        # Método que devuelve si hay un ganador o no
        # @return [boolean] Devuelve si hay un ganador o no
        attr_reader :winner

        # Método que devuelve la cadena de caracteres con eventos interesantes
        # @return [String] Devuelve la cadena de caracteres con eventos interesantes
        attr_reader :log
    end

end
