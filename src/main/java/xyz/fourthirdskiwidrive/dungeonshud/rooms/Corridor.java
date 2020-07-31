package xyz.fourthirdskiwidrive.dungeonshud.rooms;

public class Corridor extends Room {
    private int variant;
    public Corridor (int x, int z, int r, int v) {
        super(x, z, r);
        variant = v;

        //TODO: Write switch/case for the secrets according to variant
        /*
        super.Secrets = new ArrayList<SecretSubPosition> (
            Arrays.asList(

            )
        );
        */
    }

    @Override
    public RoomType getRoomType() {
        return RoomType.CORRIDOR;
    }
}
