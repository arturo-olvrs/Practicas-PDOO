# encoding: UTF-8

require_relative 'combat_element'

module Irrgarten

    # Esta clase representa las armas que utiliza el jugador en los ataques
    # durante los combates.
    #
    # @author Joaquin Avilés de la Fuente
    # @author Arturo Olivares Martos
    class Weapon < CombatElement

        # Método que devuelve el poder del arma y decrementa el número de usos en uno
        #
        # @see CombatElement#produce_effect
        #
        # @return [float] Si tiene algún uso disponible devuelve el poder del arma. En caso contrario (**uses**==**0**) devuelve 0
        def attack
            self.produce_effect
        end

        # Método que muestra en una cadena el estado del arma, en cuanto a  uso y poder.
        #
        # @return [String] devuelve una cadena que muestra usos y poder del arma.
        def to_s
            return "W" + super
        end

    end # class
end # module
