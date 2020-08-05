package xyz.fourthirdskiwidrive.dungeonshud.rooms.corridors.twobytwo;

import xyz.fourthirdskiwidrive.dungeonshud.rooms.Corridor;

public class Pit extends Corridor {
    public Pit(int x, int z, int r) {
        super(x, z, r, RoomShape.SQ_2_2);
    }
}