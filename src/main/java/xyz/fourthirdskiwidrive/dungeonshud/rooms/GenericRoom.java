package xyz.fourthirdskiwidrive.dungeonshud.rooms;

public class GenericRoom extends Room {
    public GenericRoom (int x, int y, int r) {
        super(x,y,r);
    }
    @Override
    public RoomType getRoomType() {
        return null;
    }
}
