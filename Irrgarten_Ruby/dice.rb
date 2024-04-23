# encoding: UTF-8
module Irrgarten

    # Esta clase se encargará de tomar las decisiones relazionadas con el azar del juego.
    #
    # @author Joaquin Avilés de la Fuente
    # @author Arturo Olivares Martos
    class Dice
        @@MAX_USES = 5 # Número máximo de usos de armas y escudos.
        @@MAX_INTELLIGENCE = 10.0 # Valor máximo para la inteligencia de jugadores y monstruos.
        @@MAX_STRENGTH = 10.0 # Valor máximo para la fuerza de jugadores y monstruos.
        @@RESURRECT_PROB = 0.3 # Probabilidad de que un jugador sea resucitado en cada turno.
        @@WEAPONS_REWARD = 2 # Número máximo de armas recibidas al ganar un combate.
        @@SHIELDS_REWARD = 3  # Número máximo de escudos recibidos al ganar un combate.
        @@HEALTH_REWARD = 5 # Número máximo de unidades de salud recibidas al ganar un combate.
        @@MAX_ATTACK = 3 # Máxima potencia de las armas.
        @@MAX_SHIELD = 2 # Máxima potencia de los escudos.

        @@generator=Random.new # Generador de números aleatorios.

        # Método que devuelve de forma aleatoria una fila o columna aleatorio entre 0 y max-1, pues
        # max indica el número de filas o columnas que hay en el tablero
        #
        # @param max [int] max número de filas o columnas
        # @return [int] Devuelve un número aleatorio entero entre 0 y max-1
        def self.random_pos(max)
            @@generator.rand(max)
        end

        # Método que devuelve de forma aleatoria el índice de un jugador para que que comience la partida
        #
        # @param nplayers [int] nplayers número de jugadores
        # @return [int] Devuelve el índice del jugador que comienza la partida
        def self.who_starts(nplayers)
            @@generator.rand(nplayers)
        end

        # Método que devuelve de forma aleatoria un valor de inteligencia [0, {@@MAX_INTELLIGENCE}[
        #
        # @return [float] devuelve un valor de inteligencia
        def self.random_intelligence
            # Los tres puntos indican que excluye @@MAX_INTELLIGENCE
            @@generator.rand(0.0...@@MAX_INTELLIGENCE)
        end

        # Método que devuelve de forma aleatoria un valor de fuerza [0, {@@MAX_STRENGTH}[
        #
        # @return [float] devuelve un valor de fuerza
        def self.random_strength
            @@generator.rand(0.0...@@MAX_STRENGTH)
        end

        # Método que indica si un jugador resucita o no con probabilidad {@@RESURRECT_PROB}
        #
        # @return [boolean] devuelve **true** o **false** si resucita o no
        def self.resurrect_player
            # Por defecto .rand saca valor entre 0 y 1 con decimales
            return (@@generator.rand <= @@RESURRECT_PROB)
        end

        # Método que indica la cantidad de armas, de forma aleatoria, que recibirá el jugador al ganar un combate
        #
        # @return [int] devuelve el número de armas que recibirá el jugador
        def self.weapons_reward
            # Los dos puntos indican que incluye @@WEAPONS_REWARD
            @@generator.rand(0..@@WEAPONS_REWARD)
        end

        # Método que indica la cantidad de escudor, de forma aleatoria, que recibirá el jugador al ganar un combate
        #
        # @return [int] devuelve el número de escudos que recibirá el jugador
        def self.shields_reward
            @@generator.rand(0..@@SHIELDS_REWARD)
        end

        # Método que indica los puntos de salud, de forma aleatoria, que recibirá el jugador al ganar un combate
        #
        # @return [int] devuelve el número de puntos de salud que recibirá el jugador
        def self.health_reward
            @@generator.rand(0..@@HEALTH_REWARD)
        end

        # Método que indica la potencia del arma, de forma aleatoria en [0, {@@MAX_ATTACK}[
        #
        # @return [float] devuelve el valor de potencia del arma
        def self.weapon_power
            @@generator.rand(0.0...@@MAX_ATTACK)
        end

        # Método que indica la potencia del escudo, de forma aleatoria
        #
        # @return [float] devuelve el valor de potencia del escudo
        def self.shield_power
            @@generator.rand(0.0...@@MAX_SHIELD)
        end

        # Método que indica el número de usos de un escudo o arma, de forma aleatoria
        #
        # @return [int] Devuelve en número de usos de un escudo o arma.
        def self.uses_left
            @@generator.rand(0..@@MAX_USES)
        end

        # Método que indica la cantidad de competencia aplicada, de forma aleatoria en [0,competence[
        #
        # @param competence [float] competence cantidad de competencia
        # @return [float] Devuelve en la cantidad de competencia
        def self.intensity (competence)
            @@generator.rand(0.0...competence)
        end

        # Método que indica si se descarta un arma/escudo en función de sus usos disponibles
        #
        # @param uses_left [int] número de usos que le quedan al arma/escudo
        # @return [boolean] **true** o **false** si se descarta o no
        def self.dicard_element(uses_left)
            probabilidad=uses_left.to_f/@@MAX_USES.to_f
            return (@@generator.rand >= probabilidad)
        end

    end

end
