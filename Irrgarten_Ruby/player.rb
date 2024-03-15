module Irrgarten

require_relative 'dice'
require_relative 'weapon'
require_relative 'shield'
require_relative 'directions'

    # Clase que representa al jugador del juego.
    #
    # @author Joaquin Avilés de la Fuente
    # @author Arturo Olivares Martos
    class Player
        
        @@MAX_WEAPONS = 2       # Número máximo de armas que puede tener un jugador
        @@MAX_SHIELDS = 3       # Número máximo de escudos que puede tener un jugador
        @@INITIAL_HEALTH = 10   # Salud inicial de los jugadores
        @@HITS2LOSE = 3         # Número de golpes que puede recibir un jugador antes de morir

        @@INVALID_POS = -1      # Posición inválida
        
        # Constructor de la clase Player. Inicializa los atributos de la clase.
        # La posición inicial del jugador es inválida
        #
        # @param number Número del jugador
        # @param intelligence Inteligencia del jugador
        # @param strength Fuerza del jugador
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

        # Consultores de @row, @col y @number
        attr_reader :row, :col, :number

        # Modificador de la posición del jugador
        #
        # @param row fila de la posición del jugador
        # @param col columna de la posición del jugador
        def pos(row, col)
            @row = row
            @col = col
        end

        # Método que informa sobre si un jugador ha muerto o no.
        # Un jugador ha muerto si su salud es menor o igual que 0
        #
        # @return true si el jugador ha muerto, false en caso contrario
        def dead
            return @health <= 0
        end

        def move(direction, valid_moves)
            # Sig. Práctica
        end

        # Calcula la suma de la fuerza del jugador y la suma de lo aportado por sus armas (sum_weapons).
        #
        # @return la intensidad del ataque
        def attack
            return @strength + self.sum_weapons
        end

        def defend(received_attack)
            # Este método delega su funcionalidad en el método manageHit. Sig. Práctica
        end

        def receive_reward
            # Sig. Práctica
        end

        # Método que genera una cadena de caracteres con la información del jugador
        #
        # @return cadena de caracteres con la información del jugador
        def to_s
            return "#{@name}[i:#{@intelligence}, s:#{@strength}, h:#{@health}, w:#{@weapons}, sh:#{@shields},
                    p:(#{@row}, #{@col}), ch:#{@consecutive_hits}]"
            
        end


        private
        def receive_weapon(w)
            # Sig. Práctica
        end

        def receive_shield(s)
            # Sig. Práctica
        end

        # Método que genera una nueva arma
        #
        # @return devuelve un nuevo objeto de tipo Weapon. NO lo añade a la lista de armas
        def new_weapon
            return Weapon.new(Dice.weapon_power, Dice.uses_left)
        end

        # Método que genera un nuevo escudo
        #
        # @return devuelve un nuevo objeto de tipo Shield. NO lo añade a la lista de escudos
        def new_shield
            return Shield.new(Dice.shield_power, Dice.uses_left)
        end

        # Devuelve la suma del resultado de llamar al método attack de todas sus armas.
        # 
        # @return la apotación de las armas al ataque
        def sum_weapons
            sum = 0
            @weapons.each do |w|
                sum += w.attack
            end
            return sum
        end

        # Devuelve la suma del resultado de llamar al método protect de todos sus escudos.
        #
        # @return la apotación de los escudos a la defensa
        def sum_shields
            sum = 0
            @shields.each do |s|
                sum += s.protect
            end
            return sum
        end

        # Calcula la suma de la inteligencia con el aporte de los escudos (sum_shields).
        #
        # @return la energía defensiva
        def defensive_energy
            return @intelligence + sum_shields
        end

        def manage_hit(received_attack)
            # Sig. Práctica
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