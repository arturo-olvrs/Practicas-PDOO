# encoding: UTF-8

require_relative 'player'
require_relative 'dice'


module Irrgarten

    # Clase que representa un jugador Fuzzy (mareado). No es ta determinista en sus acciones.
    #
    # @author Joaquin Avilés de la Fuente
    # @author Arturo Olivares Martos
    class FuzzyPlayer < Player

        # Constructor de la clase {FuzzyPlayer}.
        def initialize(other_player)
            copy(other_player)
        end


        # Mueve al jugador en la dirección indicada en función de si es válida o no, y
        # de la inteligencia del jugador. A mayor inteligencia, mayor probabilidad de moverse en la dirección deseada.
        #
        # @see Dice#nextStep
        # @see Player#move
        #
        # @param direction [Directions] Dirección a la que se pretende desplazar el personaje
        # @param valid_moves [Array<Directions>] Lista de direcciones válidas a las que se puede mover el jugador
        #
        # @return [Directions] Dirección a la que se quiere desplazar el {FuzzyPlayer} (tendremos que ver si es válida)
        def move(direction, valid_moves)

            Dice.nextStep(super, valid_moves, intelligence)
        end

        # Calcula la la suma de lo aportado por sus armas ({#sum_weapons}) y
        # de un valor aleatorio menor que su fuerza (por ser Fuzzy)
        #
        # @return [float] la intensidad del ataque
        def attack
            return self.sum_weapons + Dice.intensity(@strength)
        end


        # Método que genera una cadena de caracteres con la información del jugador
        #
        # @return [String] cadena de caracteres con la información del jugador
        def to_s
            return "(Fuzzy) " + super
        end

        protected
        # Calcula la suma del aporte de los escudos ({#sum_shields}) y
        # de un valor aleatorio menor que su inteligencia (por ser Fuzzy)
        #
        # @return [float] La energía defensiva
        def defensive_energy
            return self.sum_shields + Dice.intensity(@intelligence)
        end

    end # class
end # module
