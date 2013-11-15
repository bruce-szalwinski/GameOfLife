package gameoflife

class GameOfLife {

  def glider =  [
             [1,1], [3,1], [2,2], [3,2], [2,3]
          ]

  public static def gosper_gun = [
    [2,6], [2,7], [3,6], [3,7], // block 1
    [36,4], [36,5], [37,4], [37,5], // block 2
    
    [12,6], [12,7], [12,8],
    [13,5], [13,9],
    [14,4], [14,10],
    [15,4], [15,10],
    [16,7],
    [17,5], [17,9],
    [18,6], [18,7], [18,8],
    [19,7],

    [22,4], [22,5], [22,6],
    [23,4], [23,5], [23,6],
    [24,3], [24,7],
    [26,2], [26,3], [26,7], [26,8]
  ]

  public static def another_gun = [
    [2,2], [3,2], [4,2], [5,2], [6,2], [7,2], [8,2], [9,2],
    [11,2], [12,2], [13,2], [14,2], [15,2],
    [19,2], [20,2], [21,2],
    [28,2], [29,2], [30,2], [31,2], [32,2], [33,2], [34,2],
    [36,2], [37,2], [38,2], [39,2], [40,2]
  ]

  def today = []
  def boardSize = 0

  def neighborCount(x,y) {

    def ordinals = [
      [-1, -1], [0, -1], [1, -1],
      [-1,  0],        , [1, 0],
      [-1,  1], [0,  1], [1, 1]
    ]
    def neighbors = 0

    ordinals.each {
      if (isCellAlive(x + it[0], y + it[1])) {
        ++neighbors
      }
    }

    return neighbors
  }

  def isCellAlive(x,y) {
    def alive = false
    today.each { cell ->
      if (cell[0] == x && cell[1] == y) {
        alive = true
      }
    }

    return alive
  }    

  def tomorrow() {
    def anotherDay = []

    for (y in 0 .. boardSize) {
      for (x in 0 .. boardSize) {
        def cellState = isCellAlive(x,y)
        def neighbors = neighborCount(x,y)

        if (cellState) {
          if (neighbors == 2 || neighbors == 3) {
            anotherDay.add([x,y])
          }
        } else {
          if (neighbors == 3) {
            anotherDay.add([x,y])
          }
        }
      }
    }

    return anotherDay
  } 

  def showBoard() {
    def str = ""

    for (y in 0 .. boardSize) {
      for (x in 0 .. boardSize) {
        if (isCellAlive(x,y)) {
          str += 'x'
        } else {
          str += ' '
        }
      }
      str += '\n'
    }

    return str   
  }

  def clearScreen() {
    print "\033[2J\033[;H"
  }
  
  def display(iteration) {
      clearScreen()
      println "iteration: ${iteration}"
      println "number of cells: ${today.size()}"
      println showBoard()
  }

  def playGame(n) {
    n.times {
      sleep 50
      display(it)
      today = tomorrow()
    }      
  } 

  public static void main(String[] args) {

      GameOfLife game = new GameOfLife(
          boardSize: 50,
          today: gosper_gun
      )

      game.playGame(5000) 

  }
}
