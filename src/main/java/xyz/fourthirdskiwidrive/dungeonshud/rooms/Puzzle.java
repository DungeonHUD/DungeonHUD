package xyz.fourthirdskiwidrive.dungeonshud.rooms;

public class Puzzle extends Room {
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

    private PuzzleType puzzleType;
    public Puzzle(int x, int z, int r, PuzzleType p) {
        super(x, z, r);
        this.puzzleType = p;

        //TODO: Write code for puzzle room secrets
        /*
        Secrets = new ArrayList<SecretSubPosition> (
            Arrays.asList(

            )
        );
         */
    }

    @Override
    public RoomType getRoomType() {
        return RoomType.PUZZLE;
    }


}
