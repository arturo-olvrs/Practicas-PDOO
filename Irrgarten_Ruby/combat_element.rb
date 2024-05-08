# encoding: UTF-8

require_relative 'dice'


module Irrgarten

    # Esta clase representa un elemento de combate, que puede ser un arma ({Weapon}) o un escudo ({Shield}).
    #
    # @abstract No se debe instanciar directamente.
    #
    # @author Joaquin Avilés de la Fuente
    # @author Arturo Olivares Martos
    class CombatElement

        # Constructor de la clase
        #
        # @param effect [float] efecto del elemento de combate
        # @param uses [int] usos que tiene el escudo
        def initialize(effect, uses)
            @effect = effect.to_f
            @uses = uses.to_i
        end

        protected
        # Método que produce el efecto del elemento de combate y decrementa el número de usos en uno.
        #
        # @return [float] Si tiene algún uso disponible devuelve el uso del elemento. En caso contrario (**uses**==**0**) devuelve 0.
        def produce_effect
            if @uses > 0
                @uses -= 1
                return @effect
            else
                return 0.0
            end
        end

        public

        # Método que indica si se descartará el elemento de combate.
        #
        # @see Dice#dicard_element
        # @return [boolean] devuelve **true** o **false** si se descarta o no
        def discard
            return Dice.dicard_element(@uses)
        end

        # Método que muestra en una cadena el estado del elemento, en cuanto a
        # uso y efecto.
        #
        # @return [String] devuelve una cadena que muestra usos y efecto del elemento
        def to_s
            return "[#{@effect}, #{@uses}]"
        end


    end # class
end # module
