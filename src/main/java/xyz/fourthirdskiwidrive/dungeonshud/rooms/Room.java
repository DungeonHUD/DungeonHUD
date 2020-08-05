package xyz.fourthirdskiwidrive.dungeonshud.rooms;


import java.security.InvalidParameterException;
import java.util.ArrayList;

public abstract class Room {

    public ArrayList<SecretSubPosition> Secrets;
    protected final int xstart;
    protected final int zstart;
    protected final int rotation;

    public class SecretSubPosition {
        public int x;
        public int y;
        public int z;
    }
    public class SecretPosition {
        public int x;
        public int y;
        public int z;
    }

    public Room (int x, int z, int r) {
        xstart = x;
        zstart = z;
        rotation = r;
    }

    public ArrayList<SecretPosition> getSecretPositions() throws InvalidParameterException {
        ArrayList<SecretPosition> ret = new ArrayList<>();

        for(SecretSubPosition secret : Secrets) {
            SecretPosition temp = new SecretPosition();
            temp.y = secret.y;
            switch(rotation) {
                case 0:
                    temp.x = xstart + secret.x;
                    temp.z = zstart + secret.z;
                    break;
                case 1:
                    temp.x = xstart + (32 - secret.z);
                    temp.y = zstart + secret.x;
                    break;
                case 2:
                    temp.x = xstart + (32 - secret.x);
                    temp.z = zstart + (32 - secret.z);
                    break;
                case 3:
                    temp.x = xstart + secret.z;
                    temp.z = xstart + (32 - secret.x);
                    break;
                default:
                    throw new InvalidParameterException("Room rotation must be between 0 and 3");
            }

            ret.add(temp);
        }

        return ret;
    }
    public abstract RoomType getRoomType();
}
