package xyz.fourthirdskiwidrive.dungeonshud.rooms;

import java.util.ArrayList;
import java.util.Arrays;

public class Fairy extends Room {
    public Fairy (int x, int z, int r) {
        super(x, z, r);

        Secrets = new ArrayList<> (
        );
    }

    @Override
    public RoomType getRoomType() {
        return RoomType.FAIRY;
    }
}
