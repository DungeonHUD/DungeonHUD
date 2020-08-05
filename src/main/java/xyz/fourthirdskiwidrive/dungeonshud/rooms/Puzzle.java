package xyz.fourthirdskiwidrive.dungeonshud.rooms;

public abstract class Puzzle extends Room {
    public enum PuzzleType {
        CREEPER,
        BLAZE,
        RIDDLE,
        TICTACTOE,
        WATERFALL,
        TELEPORT,
        BLOCK_PUSHING,
        THIN_ICE,
        SILVERFISH,
    }

    protected PuzzleType puzzleType;
    public Puzzle(int x, int z, int r, PuzzleType p) {
        super(x, z, r);
        this.puzzleType = p;
    }

    @Override
    public RoomType getRoomType() {
        return RoomType.PUZZLE;
    }

    public abstract PuzzleType getPuzzleType();


}
