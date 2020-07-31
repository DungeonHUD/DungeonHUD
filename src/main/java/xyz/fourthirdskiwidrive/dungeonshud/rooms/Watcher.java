package xyz.fourthirdskiwidrive.dungeonshud.rooms;

import java.util.ArrayList;
import java.util.Arrays;

public class Watcher extends Room {
    public Watcher (int x, int z, int r) {
        super(x,z,r);

        Secrets = new ArrayList<>(
        );
    }
    @Override
    public RoomType getRoomType() {
        return RoomType.WATCHER;
    }
}
