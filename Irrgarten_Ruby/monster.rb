module Irrgarten

require_relative 'dice'

    # Clase que representa a los monstruos del juego.
    #
    # @author Joaquin Avilés de la Fuente
    # @author Arturo Olivares Martos
    class Monster
        @@INITIAL_HEALTH = 5    # Salud inicial de los monstruos


        # Posición inválida
        @@INVALID_POS = -1

        # Formato para mostrar los datos flotantes del monstruo
        @@FORMATO='%.10f'

        # Constructor de la clase Monster. Inicializa los atributos de la clase.
        # La posición inicial del monstruo es inválida
        #
        # @param name [String] Nombre del monstruo
        # @param intelligence [float] Inteligencia del monstruo
        # @param strength [float] Fuerza del monstruo
        def initialize(name, intelligence, strength)
            @name = name
            @intelligence = intelligence.to_f
            @strength = strength.to_f
            @health = @@INITIAL_HEALTH

            @row = @@INVALID_POS
            @col = @@INVALID_POS
        end

        # Método que informa si el monstruo ha muerto o no.
        # Un monstruo ha muerto si su salud es menor o igual que 0
        #
        # @return [boolean] **true** si el monstruo ha muerto, **false** en caso contrario
        def dead
            return @health <= 0
        end

        # Método que devuelve la intensidad del ataque de un monstruo.
        # @see Dice#intensity
        #
        # @return [float] intensidad del ataque del monstruo
        def attack
            return Dice.intensity(@strength)
        end

        # Método que comprueba si el monstruo sobrevive después del ataque recibido
        # basándonos en su inteligencia. Antes de todo se comprueba si el monstruo está muerto
        #
        # @param received_attack [float] ataque recibido por un jugador
        #
        # @return [boolean] booleano que indica si el monstruo está muerto, después de recibir 
        # el ataque
        def defend(received_attack)
            is_dead=dead # también se puede self.dead

            # Comprobamos si ya está muerto y sino le atacamos
            if (!is_dead)
                defensive_energy=Dice.intensity(@intelligence)
                puts "d: "+defensive_energy.to_s+ " a: "+received_attack.to_s
                if(defensive_energy<received_attack)
                    # Se reduce la vida del monstruo
                    got_wounded
                    is_dead=dead
                end
            end
            return is_dead
        end

        # Modificador de la posición del monstruo
        #
        # @param row [int] fila de la posición del monstruo
        # @param col [int] columna de la posición del monstruo
        def pos(row, col)
            @row = row
            @col = col
        end

        # Método que genera una cadena de caracteres con la información del monstruo
        #
        # @return [String] cadena de caracteres con la información del monstruo
        def to_s
            return "M[n:#{@name}, i:#{format(@@FORMATO,@intelligence)}, "+
                    "s:#{format(@@FORMATO,@strength)}, h:#{format(@@FORMATO,@health)}, "+
                    "p:(#{@row}, #{@col})]"
        end

        private

        # Método que reduce la salud del monstruo en 1
        def got_wounded
            @health -= 1
        end
    end

end
