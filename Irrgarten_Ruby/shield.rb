module Irrgarten
# Author: Joaquín Avilés de la Fuente
# Author: Arturo Olivares Martos

    require_relative 'dice'

    # Clase Shield
    # 
    # Esta clase representa los escudos que utiliza el jugador en las defensas
    # durante los combates.

    class Shield

        # Constructor de la clase
        #
        # @param protection protección del escudo
        # @param uses usos que tiene el escudo
        def initialize(protection, uses)
            @protection = protection
            @uses = uses
        end

        # Método que devuelve la protección del escudo y decrementa 
        # el número de usos en uno
        #
        # @return Si tiene algún uso disponible devuelve la protección del escudo
        # en caso contrario (uses=0) devuelve 0
        def attack
            if @uses > 0
                @uses -= 1
                return @protection
            else
                return 0 
            end
        end

        # Método que indica si se descartará el escudo 
        # 
        # @return devuelve true o false si se descarta o no
        def discard
            return Dice.dicard_element(@uses)
        end

        # Método que muestra en una cadena el estado del escudo, en cuanto a 
        # uso y protección
        #
        # @return devuelve una cadena que muestra usos y protección del escudo
        def to_s
            return "S[#{@protection}, #{@uses}]"
        end

    end


end
