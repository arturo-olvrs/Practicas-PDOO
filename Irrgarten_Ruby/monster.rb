# encoding: UTF-8

require_relative 'dice'
require_relative 'labyrinth_character'


module Irrgarten

    # Clase que representa a los monstruos del juego.
    #
    # @author Joaquin Avilés de la Fuente
    # @author Arturo Olivares Martos
    class Monster < LabyrinthCharacter

        # Salud inicial de los monstruos
        @@INITIAL_HEALTH = 5

        # Constructor de la clase Monster. Inicializa los atributos de la clase.
        # La posición inicial del monstruo es inválida
        #
        # @param name [String] Nombre del monstruo
        # @param intelligence [float] Inteligencia del monstruo
        # @param strength [float] Fuerza del monstruo
        def initialize(name, intelligence, strength)
            super(name, intelligence, strength, @@INITIAL_HEALTH)
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
                if(defensive_energy<received_attack)
                    # Se reduce la vida del monstruo
                    got_wounded
                    is_dead=dead
                end
            end
            return is_dead
        end

        # Método que genera una cadena de caracteres con la información del monstruo
        #
        # @return [String] cadena de caracteres con la información del monstruo
        def to_s

            return super + "]"
        end
        # // TODO: Revisar los to_s


    end # class
end # module
