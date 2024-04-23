module Irrgarten

    require_relative 'dice'

    # Esta clase representa los escudos que utiliza el jugador en las defensas
    # durante los combates.
    #
    # @author Joaquin Avilés de la Fuente
    # @author Arturo Olivares Martos
    class Shield

        # Constructor de la clase
        #
        # @param protection [float] protección del escudo
        # @param uses [int] usos que tiene el escudo
        def initialize(protection, uses)
            @protection = protection.to_f
            @uses = uses.to_i
        end

        # Método que devuelve la protección del escudo y decrementa el número de usos en uno.
        #
        # @return [float] Si tiene algún uso disponible devuelve la protección del escudo. En caso contrario (uses=0) devuelve 0
        def protect
            if @uses > 0
                @uses -= 1
                return @protection
            else
                return 0.0
            end
        end

        # Método que indica si se descartará el escudo.
        #
        # @see Dice#dicard_element
        # @return [boolean] devuelve **true** o **false** si se descarta o no
        def discard
            return Dice.dicard_element(@uses)
        end

        # Método que muestra en una cadena el estado del escudo, en cuanto a
        # uso y protección
        #
        # @return [String] devuelve una cadena que muestra usos y protección del escudo
        def to_s
            return "S[#{@protection}, #{@uses}]"
        end

    end


end
