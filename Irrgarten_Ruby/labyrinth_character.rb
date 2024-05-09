# encoding: UTF-8


module Irrgarten

    # Clase que representa un personaje del laberinto, ya sea monstruo ({Monster}) o jugador ({Player}).
    #
    # @abstract Implementar los métodos {#attack} y {#defend} en las clases hijas.
    #
    # @author Joaquin Avilés de la Fuente
    # @author Arturo Olivares Martos
    class LabyrinthCharacter

        # Posición inválida
        @@INVALID_POS = -1

        # Constructor de la clase {LabyrinthCharacter}. Inicializa los atributos de la clase.
        # La posición inicial del personaje es inválida
        #
        # @param name [String] Nombre del personaje
        # @param intelligence [float] Inteligencia del personaje
        # @param strength [float] Fuerza del personaje
        # @param health [float] Salud del personaje
        def initialize(name, intelligence, strength, health)
            @name = name.to_s
            @intelligence = intelligence.to_f
            @strength = strength.to_f
            @health = health.to_f

            @row = @@INVALID_POS
            @col = @@INVALID_POS
        end


        protected
        # Consultor de @name
        # @return [String] nombre del personaje
        attr_reader :name

        # Consultor de @intelligence
        # @return [float] inteligencia del personaje
        attr_reader :intelligence

        # Consultor de @strength
        # @return [float] fuerza del personaje
        attr_reader :strength

        # Consultor y modificador de @health
        # @return [float] salud del personaje
        attr_accessor :health

        public
        # Consultor de @row
        # @return [int] fila de la posición del personaje
        attr_reader :row

        # Consultor de @col
        # @return [int] columna de la posición del personaje
        attr_reader :col

        # Modificador de la posición del personaje
        #
        # @param row [int] fila de la posición del personaje
        # @param col [int] columna de la posición del personaje
        def pos(row, col)
            @row = row
            @col = col
        end

        # Método que busca asemejarse a un constructor de copia.
        # Copia los atributos de un personaje a otro.
        #
        # @param other [LabyrinthCharacter] personaje al que se le copiarán los atributos
        # @note Al terminar se copian los atributos del personaje **other** al personaje que llama al método
        def copy(other)
            @name = other.name
            @intelligence = other.intelligence
            @strength = other.strength
            @health = other.health
            pos(other.row, other.col)
        end

        # Método que informa sobre si un personaje ha muerto o no.
        # Un personaje ha muerto si su salud es menor o igual que 0
        #
        # @return [boolean] **true** si el personaje ha muerto, **false** en caso contrario
        def dead
            return @health <= 0
        end

        protected
        # Este método decrementa en una unidad el atributo que representa la salud del personaje.
        def got_wounded
            @health -= 1
        end

        public


        # Método que genera una cadena de caracteres con la información del personaje
        #
        # @return [String] cadena de caracteres con la información del personaje
        def to_s

            # Formato para mostrar los datos flotantes del personaje
            formato='%.6f'

            return "#{@name}[i:#{format(formato,@intelligence)}, s:#{format(formato,@strength)}, "+
            "h:#{format(formato,@health)}, p:(#{@row}, #{@col})]"
        end

        # Método que realiza un ataque.
        #
        # @abstract Método abstracto. Implementar en las clases hijas.
        # @raise [NotImplementedError] Si se llama en esta clase.
        def attack
            raise NotImplementedError
        end

        # Método que defiende al personaje.
        #
        # @abstract Método abstracto. Implementar en las clases hijas.
        # @raise [NotImplementedError] Si se llama en esta clase.
        def defend
            raise NotImplementedError
        end


    end # class
end # module
