import java.util.Stack

class ScoreManager {
    private val stackScore01 = Stack<Int>()
    private val stackScore02 = Stack<Int>()

    init {
        stackScore01.push(0)
        stackScore02.push(0)
    }

    fun updateScore(team: Int, newScore: Int) {
        if (team == 1) {
            stackScore01.push(newScore)
        } else if (team == 2) {
            stackScore02.push(newScore)
        }
    }

    fun undo(team: Int) {
        if (team == 1 && stackScore01.size > 1) {
            stackScore01.pop()
        } else if (team == 2 && stackScore02.size > 1) {
            stackScore02.pop()
        }
    }

    fun getScore(team: Int): Int {
        return if (team == 1) {
            stackScore01.peek()
        } else {
            stackScore02.peek()
        }
    }
}
