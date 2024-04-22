require 'io/console'
require_relative '../directions'

# Este módulo se encarga de recibir las indicaciones de los jugadores sobre las
# direcciones a la que se quieren mover y muestra la información que produce
#
# @author Joaquin Avilés de la Fuente
# @author Arturo Olivares Martos
module UI

  # Esta clae se encarga de recibir las ordenes de los jugadores y transimitirlas
  # al juego para que haga las gestiones necesarias. También muestra en consola
  # el estado del juego, es decir, el laberinto y los jugadores, monstruos, etc.
  #
  # @author Joaquin Avilés de la Fuente
  # @author Arturo Olivares Martos
  
  class TextUI

    # https://gist.github.com/acook/4190379
    # 
    # @return [String] El carácter o secuencia de caracteres leídos desde la entrada estándar. 
    #   Si se presiona la tecla de escape, intenta leer secuencias adicionales para identificar
    #   teclas especiales. 
    def read_char
      STDIN.echo = false
      STDIN.raw!
    
      input = STDIN.getc.chr
      if input == "\e" 
        input << STDIN.read_nonblock(3) rescue nil
        input << STDIN.read_nonblock(2) rescue nil
      end
    ensure
      STDIN.echo = true
      STDIN.cooked!
    
      return input
    end

    # Método que recibe las ordenes del jugador para desplazarse
    #
    # @return [Direction] dirección hacia la que desea desplazarse el jugador
    def next_move
      print "Where? "
      got_input = false
      while (!got_input)
        c = read_char
        case c
          when "\e[A"
            puts "UP ARROW"
            output = Irrgarten::Directions::UP
            got_input = true
          when "\e[B"
            puts "DOWN ARROW"
            output = Irrgarten::Directions::DOWN
            got_input = true
          when "\e[C"
            puts "RIGHT ARROW"
            output = Irrgarten::Directions::RIGHT
            got_input = true
          when "\e[D"
            puts "LEFT ARROW"
            output = Irrgarten::Directions::LEFT
            got_input = true
          when "\u0003"
            puts "CONTROL-C"
            got_input = true
            exit(1)
          else
            #Error
        end
      end
      output
    end

    # Método que muestra el estado completo del juego, mostrándo el laberinto,
    # los monstruos, los jugadores, mensaje con eventos importantes y si ha habido
    # un ganador.
    # 
    # @param game_state [GameState] estado actual del juego 
    def show_game(game_state)
      puts game_state.labyrinth
      puts "\n"
      puts game_state.players
      puts "\n"
      puts game_state.monsters
      puts "\n"
      
      puts game_state.log + "\n"

      if (game_state.winner)
        puts "We have a winner! Player " + game_state.current_player.to_s + "\n"
      else
        puts "Current player: " + game_state.current_player.to_s + "\n"
      end
    end

  end # class   

end # module 

