# encoding: UTF-8

require_relative 'dice'
require_relative 'weapon'
require_relative 'shield'
require_relative 'labyrinth_character'


module Irrgarten

    # Clase que representa al jugador del juego.
    #
    #
    # @author Joaquin Avilés de la Fuente
    # @author Arturo Olivares Martos
    class Player < LabyrinthCharacter

        @@MAX_WEAPONS = 2       # Número máximo de armas que puede tener un jugador
        @@MAX_SHIELDS = 3       # Número máximo de escudos que puede tener un jugador
        @@INITIAL_HEALTH = 10   # Salud inicial de los jugadores
        @@HITS2LOSE = 3         # Número de golpes que puede recibir un jugador antes de morir

        # Constructor de la clase Player. Inicializa los atributos de la clase.
        # La posición inicial del jugador es inválida
        #
        # @param number [char] Número del jugador
        # @param intelligence [float] Inteligencia del jugador
        # @param strength [float] Fuerza del jugador
        def initialize(number, intelligence, strength)
            @number = number.to_s
            name = "Player #{@number}"
            super(name, intelligence, strength, @@INITIAL_HEALTH)

            @weapons = Array.new           # Array de armas
            @shields = Array.new           # Array de escudos

            @consecutive_hits = 0   # Número de golpes consecutivos recibidos. Al crearse está en 0
        end



        # Consultor de @number
        # @return [char] número del jugador
        attr_reader :number

        protected   # // TODO: Qué visibilidad tienen en Ruby??
        # Consultor de @weapons
        # @return [Array::Weapon] array de armas del jugador
        attr_reader :weapons

        # Consultor de @shields
        # @return [Array::Shield] array de escudos del jugador
        attr_reader :shields

        # Consultor de @consecutive_hits
        # @return [int] número de golpes consecutivos recibidos
        attr_reader :consecutive_hits
        public


        # Método que busca asemejarse a un constructor de copia.
        # Copia los atributos de un jugador a otro.
        #
        # @param other [Player] jugador al que se quiere copiar
        # @note Al terminar se copian los atributos del jugador **other** al jugador que llama al método
        def copy(other)
            super(other)
            @number = other.number
            @weapons = other.weapons
            @shields = other.shields
            @consecutive_hits = other.consecutive_hits
        end

        # Método que resucita al jugador, poniendo su salud a su valor inicial,
        # vaciando su lista de armas y escudos, y poniendo los golpes consecutivos a 0
        def resurrect
            @health = @@INITIAL_HEALTH
            @weapons.clear
            @shields.clear
            reset_hits()
        end


        # Comprueba si la dirección pasada hacia la que se pretende desplazar el personaje
        # es válida, devolviendola en caso de que lo sea o no se pueda mover hacia ninguna posición, es decir,
        # valid_moves esté vacío. Si no está en valid_moves y dicho array no está vacío, se devuelve la primera dirección
        # guardada en el array
        #
        # @param direction [Directions] dirección a la que se pretende desplazar el personaje
        # @param valid_moves [Array::Directions] lista de direcciones válidas a las que se puede mover el jugador
        #
        # @return [Directions] dirección a la que se quiere desplazar (tendremos que ver si es válida)
        def move(direction, valid_moves)
            size = valid_moves.length

            # El método del array es include?(<element>)
            contained = valid_moves.include?(direction)


            if ( (size>0) && (!contained) )
                return valid_moves[0] # Se puede también array.at(pos)
            else
                return direction
            end
        end

        # Calcula la suma de la fuerza del jugador y la suma de lo aportado por sus armas (sum_weapons).
        #
        # @return [float] la intensidad del ataque
        def attack
            return @strength + self.sum_weapons # Self no es necesario
        end

        # Este método delega su funcionalidad en el método manage_hit de Player
        # @see Player#manage_hit
        #
        # @param received_attack [float] ataque recibido
        #
        # @return [boolean] devuelve true si ha muerto, y false en caso contraio
        def defend(received_attack)
            return manage_hit(received_attack)
        end

        # Método que recompensa al jugador con armas, escudos y vida extra delegando
        # para ello en métodos de Dice, como weapons_reward, shields_reward y health_reward
        # @see Dice#weapons_reward
        # @see Dice#shields_reward
        # @see Dice#health_reward
        def receive_reward
            wReward=Dice.weapons_reward
            sReward=Dice.shields_reward

            # Reward de armas
            wReward.times do |i|
                receive_weapon(self.new_weapon)
            end

            # Reward de escudos
            sReward.times do |i|
                receive_shield(self.new_shield)
            end

            # Reward de vida
            @health += Dice.health_reward
        end

        # Método que genera una cadena de caracteres con la información del jugador
        #
        # @return [String] cadena de caracteres con la información del jugador
        def to_s
            # Guardamos la info de todas las armas en un string
            to_weapons="["
            tam_weapons=@weapons.size
            # Se incluye 0 y tam_weapons-1
            for i in 0..(tam_weapons-1) do
                to_weapons+=@weapons[i].to_s

                # NO hace la parte de delante si i == (tam_weapons-1)
                to_weapons += ", " unless i == (tam_weapons - 1)
            end
            to_weapons+="]"

            # Guardamos la info de todas las armas en un string
            to_shields="["
            tam_shields=@shields.size
            # Se incluye 0 y tam_shields-1
            for i in 0..(tam_shields-1) do
                to_shields+=@shields[i].to_s

                # NO hace la parte de delante si i == (tam_weapons-1)
                to_shields += ", " unless i == (tam_shields - 1)
            end
            to_shields+="]"

            return super + ", ch:#{@consecutive_hits}, w:"+to_weapons+", sh:"+to_shields + " ]"
        end

        private

        # Método que añade un nuevo arma (si es posible) al array de armas del jugador. Primero
        # intenta eliminar algún arma usando el método discard de Weapon y luego si el numero
        # de armas del jugador es menor estricto al numero de armas máximas a llevar, se añade
        # el nuevo arma.
        # @see Weapon#discard
        #
        # @param w [Weapon] arma a intentar añadir al jugador
        def receive_weapon(w)

            # En primer lugar, eliminamos las armas debidas
            @weapons.delete_if do |wi|
                wi.discard
            end

            # Si caben, se añade el arma
            if(@weapons.length < @@MAX_WEAPONS)
                @weapons.push(w) # funciona también @weapons<<(wi)
            end
        end

        # Método que añade un nuevo escudo (si es posible) al array de escudos del jugador. Primero
        # intenta eliminar algún escudo usando el método discard de Shield y luego si el número
        # de escudos del jugador es menor estricto al numero de escudos máximos a llevar, se añade
        # el nuevo escudo.
        # @see Shield#discard
        #
        # @param s [Shield] escudo a intentar añadir al jugador
        def receive_shield(s)
            @shields.delete_if do |si|
                si.discard
            end

            if(@shields.length < @@MAX_SHIELDS)
                @shields.push(s)
            end
        end

        # Método que genera una nueva arma
        #
        # @return [Weapom] devuelve un nuevo objeto de tipo Weapon. NO lo añade a la lista de armas
        def new_weapon
            return Weapon.new(Dice.weapon_power, Dice.uses_left)
        end

        # Método que genera un nuevo escudo
        #
        # @return [Shield] devuelve un nuevo objeto de tipo Shield. NO lo añade a la lista de escudos
        def new_shield
            return Shield.new(Dice.shield_power, Dice.uses_left)
        end

        # // TODO: Protected en ruby es igual que en Java??

        protected
        # Devuelve la suma del resultado de llamar al método attack de todas sus armas.
        #
        # @return [float] la apotación de las armas al ataque
        def sum_weapons
            sum = 0
            @weapons.each do |w|
                sum += w.attack
            end
            return sum
        end

        # Devuelve la suma del resultado de llamar al método protect de todos sus escudos.
        #
        # @return [float] la apotación de los escudos a la defensa
        def sum_shields
            sum = 0
            @shields.each do |s|
                sum += s.protect
            end
            return sum
        end

        # Calcula la suma de la inteligencia con el aporte de los escudos (sum_shields).
        #
        # @return [float] la energía defensiva
        def defensive_energy
            return @intelligence + sum_shields
        end
        public

        # Método que gestiona la defensa de un ataque. En el caso de que el jugador
        # haya recibido un número máximo de golpes consecutivos, determinado por
        # @@HITS2LOSE, se considerará que ha perdido, al igual que si muere
        #
        # @param received_attack [float] ataque a defender
        #
        # @return [boolean] devuelve true si ha muerto o ha alcanzado el máximo número
        # de ataques, y false en caso contrario
        def manage_hit(received_attack)
            defense=self.defensive_energy
            if(defense<received_attack)
                self.got_wounded # recibe daño
                self.inc_consecutive_hits
            else
                self.reset_hits
            end

            if ( (@consecutive_hits==@@HITS2LOSE) || self.dead )
                self.reset_hits
                lose=true
            else
                lose=false
            end
            return lose
        end

        # Fija el valor del contador de impactos consecutivos a cero.
        def reset_hits
            @consecutive_hits = 0
        end

        # Incrementa en una unidad el contador de impactos consecutivos.
        def inc_consecutive_hits
            @consecutive_hits += 1
        end

    end # class
end # module
