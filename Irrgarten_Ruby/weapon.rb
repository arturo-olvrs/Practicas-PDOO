# encoding: UTF-8

require_relative 'dice'

module Irrgarten

    # Esta clase representa las armas que utiliza el jugador en los ataques
    # durante los combates.
    #
    # @author Joaquin Avilés de la Fuente
    # @author Arturo Olivares Martos
    class Weapon

        # Constructor de la clase
        #
        # @param power [float] poder del arma
        # @param uses [int] usos que tiene el arma
        def initialize(power, uses)
            @power = power.to_f
            @uses = uses.to_i
        end

        # Método que devuelve el poder del arma y decrementa el número de usos en uno
        #
        # @return [float] Si tiene algún uso disponible devuelve el poder del arma. En caso contrario (uses=0) devuelve 0
        def attack
            if @uses > 0
                @uses -= 1
                return @power
            else
                return 0.0
            end
        end

        # Método que indica si se descartará el arma en función de sus usos
        #
        # @see Dice#dicard_element
        # @return [boolean] devuelve **true** o **false** si se descarta o no
        def discard
            return Dice.dicard_element(@uses)
        end

        # Método que muestra en una cadena el estado del arma, en cuanto a  uso y poder.
        #
        # @return [String] devuelve una cadena que muestra usos y poder del arma.
        def to_s
            return "W[#{@power}, #{@uses}]"
        end

    end # class
end # module
