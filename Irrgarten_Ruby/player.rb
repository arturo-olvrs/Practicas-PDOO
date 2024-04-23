# encoding: UTF-8

require_relative 'dice'
require_relative 'weapon'
require_relative 'shield'
require_relative 'directions'


module Irrgarten

    # Clase que representa al jugador del juego.
    #
    # @author Joaquin Avilés de la Fuente
    # @author Arturo Olivares Martos
    class Player

        @@MAX_WEAPONS = 2       # Número máximo de armas que puede tener un jugador
        @@MAX_SHIELDS = 3       # Número máximo de escudos que puede tener un jugador
        @@INITIAL_HEALTH = 10   # Salud inicial de los jugadores
        @@HITS2LOSE = 3         # Número de golpes que puede recibir un jugador antes de morir

        # Posición inválida
        @@INVALID_POS = -1

        # Formato para mostrar los datos flotantes del jugador
        @@FORMATO='%.10f'

        # Constructor de la clase Player. Inicializa los atributos de la clase.
        # La posición inicial del jugador es inválida
        #
        # @param number [char] Número del jugador
        # @param intelligence [float] Inteligencia del jugador
        # @param strength [float] Fuerza del jugador
        def initialize(number, intelligence, strength)
            @number = number
            @name = "Player #{@number}"
            @intelligence = intelligence.to_f
            @strength = strength.to_f
            @health = @@INITIAL_HEALTH
            @weapons = Array.new           # Array de armas
            @shields = Array.new           # Array de escudos

            @row = @@INVALID_POS
            @col = @@INVALID_POS

            @consecutive_hits = 0   # Número de golpes consecutivos recibidos. Al crearse está en 0
        end

        # Método que resucita al jugador, poniendo su salud a su valor inicial,
        # vaciando su lista de armas y escudos, y poniendo los golpes consecutivos a 0
        def resurrect
            @health = @@INITIAL_HEALTH
            @weapons.clear
            @shields.clear
            reset_hits()
        end

        # Consultor de @row
        # @return [int] fila de la posición del jugador
        attr_reader :row

        # Consultor de @col
        # @return [int] columna de la posición del jugador
        attr_reader :col

        # Consultor de @number
        # @return [char] número del jugador
        attr_reader :number

        # Modificador de la posición del jugador
        #
        # @param row [int] fila de la posición del jugador
        # @param col [int] columna de la posición del jugador
        def pos(row, col)
            @row = row
            @col = col
        end

        # Método que informa sobre si un jugador ha muerto o no.
        # Un jugador ha muerto si su salud es menor o igual que 0
        #
        # @return [boolean] **true** si el jugador ha muerto, **false** en caso contrario
        def dead
            return @health <= 0
        end

        # Comprueba si la dirección pasada hacia la que se pretende desplazar el personaje
        # es válida, devolviendola en caso de que lo sea o no se pueda mover hacia ninguna posición, es decir,
        # valid_moves esté vacío. Si no está en valid_moves y dicho array no está vacío, se devuelve la primera dirección
        # guardada en el array
        #
        # @param direction [Directions] dirección a la que se pretende desplazar el personaje
        # @param valid_moves [Array::Directions]
        #
        # @return [Directions] dirección a la que se quiere desplazar (tendremos que ver si es válida)
        def move(direction, valid_moves)
            size=valid_moves.length

            # El método del array es include?(<element>)
            contained=valid_moves.include?(direction)

            if ( (size>0) && (!contained) )
                firs_element=valid_moves[0] # Se puede también array.at(pos)
            else
                direction
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

            # Rewward de armas
            wReward.times do |i|
                wnew=new_weapon
                receive_weapon(wnew)
            end

            # Rewward de escudos
            sReward.times do |i|
                snew=new_shield
                receive_shield(snew)
            end

            # Rewward de vida
            extra_health=Dice.health_reward
            @health+=extra_health
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

            return "#{@name}[i:#{format(@@FORMATO,@intelligence)}, s:#{format(@@FORMATO,@strength)}, "+
            "h:#{format(@@FORMATO,@health)}, w: "+to_weapons+", sh: "+to_shields+", "+
            "p:(#{@row}, #{@col}), ch:#{@consecutive_hits}]"
            # // TODO: Comprobar que funciona igual que en Java

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
            @weapons.each do |wi|
                discard=wi.discard
                if(discard)
                    @weapons.delete(wi) # elimina el elemento wi del array
                end
            end

            size=@weapons.length
            if(size<@@MAX_WEAPONS)
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
            @shields.each do |si|
                discard=si.discard
                if(discard)
                    @shields.delete(si) # elimina el elemento si del array
                end
            end

            size=@shields.length
            if(size<@@MAX_SHIELDS)
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
            puts "d: "+defense.to_s+ " a: "+received_attack.to_s
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

        # Este método decrementa en una unidad el atributo que representa la salud del jugador.
        def got_wounded
            @health -= 1
        end

        # Incrementa en una unidad el contador de impactos consecutivos.
        def inc_consecutive_hits
            @consecutive_hits += 1
        end

    end
end
