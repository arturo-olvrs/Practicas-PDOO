require_relative 'Controller/controller'
require_relative 'UI/textUI'

require_relative 'dice'
require_relative 'directions'
require_relative 'game_character'
require_relative 'game'
require_relative 'labyrinth'
require_relative 'orientation'
require_relative 'monster'
require_relative 'shield'
require_relative 'player'
require_relative 'weapon'

module Irrgarten

    # Esta clase permite realizar las pruebas unitarias de la práctica 1
    # haciendo uso de un método llamado main
    #
    # @author Joaquin Avilés de la Fuente
    # @author Arturo Olivares Martos
    class TestP1

        # Método que realiza las pruebas unitarias de la práctica 3
        def self.main
            vista=UI::TextUI.new  # Los dos puntos porque están en otro módulo
            juego=Game.new(4)

            controller=Control::Controller.new(juego,vista)
            controller.play
        end
    end


    TestP1.main
end

