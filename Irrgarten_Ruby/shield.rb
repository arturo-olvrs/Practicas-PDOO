# encoding: UTF-8

require_relative 'combat_element'


module Irrgarten

    # Esta clase representa los escudos que utiliza el jugador en las defensas
    # durante los combates.
    #
    # @author Joaquin Avilés de la Fuente
    # @author Arturo Olivares Martos
    class Shield < CombatElement

        # Método que devuelve la protección del escudo y decrementa el número de usos en uno.
        #
        # @see CombatElement#produce_effect
        #
        # @return [float] Si tiene algún uso disponible devuelve la protección del escudo. En caso contrario (uses=0) devuelve 0
        def protect
            produce_effect
        end

        # Método que muestra en una cadena el estado del escudo, en cuanto a
        # uso y protección
        #
        # @return [String] devuelve una cadena que muestra usos y protección del escudo
        def to_s
            return "S" + super
        end

    end # class
end # module
