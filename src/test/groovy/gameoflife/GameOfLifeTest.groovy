package gameoflife

import groovy.util.GroovyTestCase

class GameOfLifeTest extends GroovyTestCase {

  void testBlinker() {

    def game = new GameOfLife(
      boardSize: 4,
      today: [
        [1,2], [2,2], [3,2]
      ]
    )

    assert game.isCellAlive(1,2)

    assert 2 == game.neighborCount(1,1)
    assert 1 == game.neighborCount(1,2)
    assert 2 == game.neighborCount(1,3)
    assert 3 == game.neighborCount(2,1)
    assert 2 == game.neighborCount(2,2)
    assert 3 == game.neighborCount(2,3)
    assert 2 == game.neighborCount(3,1)
    assert 1 == game.neighborCount(3,2)
    assert 2 == game.neighborCount(3,3)

    assert [[2,1], [2,2], [2,3]] == game.tomorrow()
  }

  void testGlider() {
    def game = new GameOfLife(
      boardSize: 40,
      today: [
        [1,1], [3,1], [2,2], [3,2], [2,3]
      ]
    )

    assert [[3,1], [1,2], [3,2], [2,3], [3,3]] == game.tomorrow()
  }

}
