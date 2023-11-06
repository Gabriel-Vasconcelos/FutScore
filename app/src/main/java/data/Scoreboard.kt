package data

import java.util.Date

data class Scoreboard(
    val matchName: String,
    val teamOne: String,
    val teamTwo: String,
    val scoreTeamOne: Int,
    val scoreTeamTwo: Int,
    val timer: String,
    val gameDate: Date
)