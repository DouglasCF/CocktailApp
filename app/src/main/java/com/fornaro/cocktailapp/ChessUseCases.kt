package com.fornaro.cocktailapp

class ChessUseCases(

) {
    private var game: Game? = null
    private var turn: Player? = null
    private var currentSelectedCoordinate: Coordinate? = null

    fun initGame(): Game {
        if (game == null) {
            game = Game(
                board = createInitialBoard(),
                player1 = Player(ColorType.White),
                player2 = Player(ColorType.Black)
            )
            turn = game?.player1
        } else {
            throw ChessError.GameAlreadyInitialized
        }
        return game ?: throw ChessError.GameNotInitialized
    }

    fun movePiece(from: String, to: String) {
        game?.let { game ->
            val fromCoordinate = game.board.coordinates.find { it.value == from }
                ?: throw ChessError.InvalidCoordinate
            val toCoordinate = game.board.coordinates.find { it.value == to }
                ?: throw ChessError.InvalidCoordinate
            toCoordinate.piece = fromCoordinate.piece
            fromCoordinate.piece = null
        } ?: throw ChessError.GameNotInitialized
    }

    fun selectCoordinate(value: String) {
        game?.let { game ->
            val coordinate = game.board.coordinates.find { it.value == value }
                ?: throw ChessError.InvalidCoordinate

            if (currentSelectedCoordinate == null) {
                // There is no selected coordinate yet
                // Let's select the coordinate
                currentSelectedCoordinate = coordinate
            } else {
                // There is a selected coordinate
                // Let's check if the current coordinate being selected has a piece with the same player's color
                coordinate.piece?.let {

                }
            }
        } ?: throw ChessError.GameNotInitialized
    }

    private fun changeTurn() {
        game?.let { game ->
            turn = if (turn == game.player1) game.player2 else game.player1
        } ?: throw ChessError.GameNotInitialized
    }

    private fun createInitialBoard(): Board {
        val list = mutableListOf<Coordinate>()
        var currentColorType = ColorType.White

        // Iterate through the board, create the coordinate with its correct value
        for (v in 8 downTo 1) {
            currentColorType = if (currentColorType == ColorType.Black) ColorType.White
            else ColorType.Black
            for (h in 97..104) { // convert to letter
                currentColorType = if (currentColorType == ColorType.Black) ColorType.White
                else ColorType.Black
                val coordinate = Coordinate(
                    value = h.toChar().toString() + v.toString(),
                    color = currentColorType
                )
                list.add(coordinate)
            }
        }
        return Board(list).apply { resetBoard(this) }
    }

    private fun resetBoard(board: Board) = with(board.coordinates) {
        // Remove all pieces
        forEach { it.piece = null }

        // Add all pieces in the correct initial state
        // Black
        get(0).piece = Piece(type = PieceType.Rook, color = ColorType.Black)
        get(1).piece = Piece(type = PieceType.Knight, color = ColorType.Black)
        get(2).piece = Piece(type = PieceType.Bishop, color = ColorType.Black)
        get(3).piece = Piece(type = PieceType.Queen, color = ColorType.Black)
        get(4).piece = Piece(type = PieceType.King, color = ColorType.Black)
        get(5).piece = Piece(type = PieceType.Bishop, color = ColorType.Black)
        get(6).piece = Piece(type = PieceType.Knight, color = ColorType.Black)
        get(7).piece = Piece(type = PieceType.Rook, color = ColorType.Black)
        for (i in 8..15) {
            get(i).piece = Piece(type = PieceType.Pawn, color = ColorType.Black)
        }

        // White
        for (i in 48..55) {
            get(i).piece = Piece(type = PieceType.Pawn, color = ColorType.White)
        }
        get(56).piece = Piece(type = PieceType.Rook, color = ColorType.White)
        get(57).piece = Piece(type = PieceType.Knight, color = ColorType.White)
        get(58).piece = Piece(type = PieceType.Bishop, color = ColorType.White)
        get(59).piece = Piece(type = PieceType.Queen, color = ColorType.White)
        get(60).piece = Piece(type = PieceType.King, color = ColorType.White)
        get(61).piece = Piece(type = PieceType.Bishop, color = ColorType.White)
        get(62).piece = Piece(type = PieceType.Knight, color = ColorType.White)
        get(63).piece = Piece(type = PieceType.Rook, color = ColorType.White)
    }
}

data class Game(
    val board: Board,
    val player1: Player,
    val player2: Player
) {
    fun printBoard() {
        val breakCount = 8
        var currentVertical = 0
        board.coordinates.forEach {
            if (currentVertical == breakCount) {
                println()
                currentVertical = 0
            }
            print(it.draw)
            currentVertical++
        }
    }
}

data class Board(
    val coordinates: List<Coordinate>
)

data class Coordinate(
    val value: String,
    val color: ColorType,
    var piece: Piece? = null
) {
    val draw: String
        get() = piece?.draw ?: when (color) {
            ColorType.White -> "□"
            ColorType.Black -> "■"
        }
}

data class Piece(
    val type: PieceType,
    val color: ColorType
) {
    val draw: String
        get() = when (color) {
            ColorType.White -> when (type) {
                PieceType.King -> "♔"
                PieceType.Queen -> "♕"
                PieceType.Rook -> "♖"
                PieceType.Bishop -> "♗"
                PieceType.Knight -> "♘"
                PieceType.Pawn -> "♙"
            }
            ColorType.Black -> when (type) {
                PieceType.King -> "♚"
                PieceType.Queen -> "♛"
                PieceType.Rook -> "♜"
                PieceType.Bishop -> "♝"
                PieceType.Knight -> "♞"
                PieceType.Pawn -> "♟"
            }
        }
}

data class Player(
    val color: ColorType
)

enum class PieceType { King, Queen, Rook, Bishop, Knight, Pawn }

enum class ColorType { White, Black }

sealed class ChessError : Throwable() {
    object GameAlreadyInitialized : ChessError()
    object GameNotInitialized : ChessError()
    object InvalidCoordinate : ChessError()
}