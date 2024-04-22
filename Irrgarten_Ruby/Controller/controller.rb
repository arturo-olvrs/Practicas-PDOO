require_relative '../game'
require_relative '../UI/textUI'
require_relative '../directions'

# Este módulo contiene el controlador del juego que irá mostrando
# la información del juego, así como actualizándolo
#
# @author Joaquin Avilés de la Fuente
# @author Arturo Olivares Martos
module Control

    # Esta clase se encarga mantener el control general del juego, es decir, su visualización
    # el estado del juego, su finalización, etc.
    #
    # @author Joaquin Avilés de la Fuente
    # @author Arturo Olivares Martos
    class Controller

      # Constructor del controlador
      #
      # @param game [Game] juego
      # @param view [TextUI] visualización
      def initialize(game,view)
        @game = game
        @view = view
      end
      
      # Juego
      def play
        end_of_game = false
        while (!end_of_game)
          @view.show_game(@game.game_state) 
          direction = @view.next_move 
          end_of_game = @game.next_step(direction)
        end
        @view.show_game(@game.game_state)
      end
    end # class   
  end # module        