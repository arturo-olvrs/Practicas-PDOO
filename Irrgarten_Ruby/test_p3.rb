# encoding: UTF-8

require_relative 'Controller/controller'
require_relative 'UI/textUI'
require_relative 'game'

#HOLA
module Irrgarten

    # Código para realizar las pruebas de la práctica 3
    #
    # @author Joaquin Avilés de la Fuente
    # @author Arturo Olivares Martos

    # Número de jugadores
    NUM_JUGADORES = 4

    vista=UI::TextUI.new  # Los dos puntos porque están en otro módulo
    juego=Game.new(NUM_JUGADORES)

    controller=Control::Controller.new(juego,vista)
    controller.play
end
