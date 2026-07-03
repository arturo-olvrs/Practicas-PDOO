# Programación y Diseño Orientado a Objetos (PDOO) — UGR 

Este repositorio contiene las prácticas correspondientes a la asignatura **Programación y Diseño Orientado a Objetos (PDOO)**, cursada durante el año académico 2023-24 en el **Doble Grado en Ingeniería Informática y Matemáticas**.

El proyecto central de la asignatura consiste en el codiseño e implementación desde cero de **Irrgarten**, un juego interactivo de supervivencia en un laberinto habitado por monstruos y jugadores. El desarrollo se ha realizado de forma paralela utilizando dos paradigmas lingüísticos de la orientación a objetos: **Java** (tipado estático) y **Ruby** (tipado dinámico).

---

## 👥 Autores y Créditos
Proyecto desarrollado en colaboración por:
*   **Arturo Olivares Martos** — [GitHub](https://github.com/arturo-olvrs)
*   **Joaquín Avilés de la Fuente** — [GitHub](https://github.com/joaquiin724)

**Profesor de Prácticas:** Miguel Lastra Leidinger

---

## 📂 Arquitectura del Repositorio

El repositorio está organizado de forma limpia en los siguientes bloques principales:
*   `Guiones/`: Contiene los documentos y directrices técnicas de cada una de las fases del proyecto.
*   `Irrgarten_Java/`: Implementación del juego en Java utilizando NetBeans, adaptando el sistema hacia entornos de escritorio estructurados.
*   `Irrgarten_Ruby/`: Implementación nativa del modelo lógico del juego en Ruby, explorando metaprogramación y dinamismo.
*   `Enunciado.pdf`: Documentación global con las reglas de negocio, combate, movimientos y lógica de turnos de Irrgarten.

---

## 🏛️ Lógica de Desarrollo y Patrones de Diseño

A lo largo de las prácticas, el juego evoluciona de forma incremental aplicando principios sólidos de ingeniería del software:

1.  **Fundamentos de POO:** Diseño del modelo de clases inicial (Jugadores, Monstruos, Laberinto, Dados de combate) encapsulando estados y comportamientos lógicos.
2.  **Relaciones y Visibilidad:** Uso estricto de herencia, polimorfismo, gestión de paquetes y niveles de visibilidad pública/privada/protegida.
3.  **Patrón Arquitectónico MVC (Modelo-Vista-Controlador):** Separación absoluta de la lógica interna del juego (Modelo), las pantallas de visualización (Vista) y la gestión del flujo de turnos (Controlador).
4.  **Interfaces de Usuario Multiplataforma (Práctica Final):** 
    *   **Interfaz de Texto:** Implementada en las fases intermedias para pruebas ágiles en terminal.
    *   **Interfaz Gráfica de Usuario (GUI):** Implementación avanzada en Java mediante contenedores e hilos visuales de **Swing (`JFrame`, `JDialog`, `JTextArea`)**. Se abstrae la comunicación con el controlador a través de una interfaz común `UI` y se diseñan cuadros de diálogo modales para capturar la entrada direccional mediante cursores interactivos.

---

## 🛠️ Tecnologías Empleadas
*   **Lenguajes:** Java (JDK) y Ruby.
*   **Librerías de GUI:** Java Swing & AWT (Manejo de eventos, repintados dinámicos con `repaint()` y componentes contenedores).
*   **Entorno recomendado:** NetBeans IDE para la gestión de formularios Swing y compilación del entorno Java.
