package xyz.fourthirdskiwidrive.dungeonshud.rooms;

import xyz.fourthirdskiwidrive.dungeonshud.rooms.corridors.onebyone.Chest;

public abstract class Corridor extends Room {
    public enum RoomShape {
        SQ_1_1,     // Square, 1x1
        RC_1_2,     // Rectangular, 1x2
        RC_1_3,     // Rectangular, 1x3
        RC_1_4,     // Rectangular, 4x4
        SQ_2_2,     // Square, 2x2
        LS_2_2      // L-Shaped, 2x2
    }
    protected RoomShape roomShape;
    public Corridor (int x, int z, int r, RoomShape roomShape) {
        super(x, z, r);
        this.roomShape = roomShape;


    }

    @Override
    public RoomType getRoomType() {
        return RoomType.CORRIDOR;
    }

    public static Corridor inferCorridorType(DungeonLayoutIdentifier.RoomSection[][] section) {
        return new Chest(0, 0, 0);
    }
}
