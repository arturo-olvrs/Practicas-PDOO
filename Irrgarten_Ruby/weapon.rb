module Irrgarten
# Author: Joaquin Avilés de la Fuente
# Author: Arturo Olivares Martos

require_relative 'dice'

    # Clase Weapon
    # 
    # Esta clase representa las armas que utiliza el jugador en los ataques
    # durante los combates.

    class Weapon

        # Constructor de la clase
        #
        # @param power poder del arma
        # @param uses usos que tiene el arma
        def initialize(power, uses)
            @power = power
            @uses = uses
        end

        # Método que devuelve el poder del arma y decrementa 
        # el número de usos en uno
        #
        # @return Si tiene algún uso disponible devuelve el poder del arma
        # en caso contrario (uses=0) devuelve 0
        def attack
            if @uses > 0
                @uses -= 1
                return @power
            else
                return 0
            end
        end

        # Método que indica si se descartará el arma 
        # 
        # @return devuelve true o false si se descarta o no
        def discard
            return Dice.dicard_element(@uses)
        end

        # Método que muestra en una cadena el estado del arma, en cuanto a  uso y poder
        #
        # @return devuelve una cadena que muestra usos y poder del arma
        def to_s
            return "W[#{@power}, #{@uses}]"
        end

    end

end