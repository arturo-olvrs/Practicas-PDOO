
require 'io/console'
require_relative 'directions'

module UI

  class TextUI

    # Esta clae se encarga de recibir las ordenes de los jugadores y transimitirlas
    # al juego para que haga las gestiones necesarias. También muestra en consola
    # el estado del juego, es decir, el laberinto y los jugadores, monstruos, etc.
    #
    # @author Joaquin Avilés de la Fuente
    # @author Arturo Olivares Martos

    #https://gist.github.com/acook/4190379
    # 
    # @return [String] El carácter o secuencia de caracteres leídos desde la entrada estándar. 
    #   Si se presiona la tecla de escape, intenta leer secuencias adicionales para identificar
    #   teclas especiales. //TODO: sacado de ChatGPT para que no me diga que no está documentado
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

    # def show_game(game_state)
      # //TODO: preguntar si sirve para algo (está vacío el método)
    # end

  end # class   

end # module   

