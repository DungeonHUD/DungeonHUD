package xyz.fourthirdskiwidrive.dungeonshud.rooms;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import xyz.fourthirdskiwidrive.dungeonshud.ChunkSliceManager;

import java.util.ArrayList;

public class DungeonLayoutIdentifier {
    public enum RoomWallType {
        ROOM_WALL_TYPE_CONNECTED,
        ROOM_WALL_TYPE_SEALED,
        ROOM_WALL_TYPE_DOOR,
        ROOM_WALL_TYPE_BLOOD_DOOR,
    }

    public static class DIRECTIONS {
        public static final int NORTH = 0;
        public static final int EAST  = 1;
        public static final int SOUTH = 2;
        public static final int WEST  = 3;
    }


    public static class RoomSection {

        public boolean IsPartOfARoomYet = false;
        public boolean hasBloodDoor = false;

        //Clockwise, negative z (North) first, then positive x, positive z, negative x
        public RoomWallType[] walls = new RoomWallType[4];
    }

    public static ArrayList<Room> identifyAllRooms(int xSectionCount, int ySectionCount, ChunkSliceManager manager) {
        ArrayList<Room> ret = new ArrayList<>();

        RoomSection[][] Sections = new RoomSection[xSectionCount][ySectionCount];

        Block[][] worldSlice = new Block[xSectionCount * 32 + 1][ySectionCount * 32 + 1];

        //Initialize every block in the worldSlice with either air or what is there
        for (int i = 0; i < worldSlice.length; ++i) {
            for (int j = 0; j < worldSlice[i].length; ++j) {
                //If the block is outside the scope, make it air
                if(i < 1 || j < 1) {
                    worldSlice[i][j] = Blocks.AIR;
                }
                //Otherwise, find the block and set it, using some bitwise operations to make it faster and cleaner
                else {
                    worldSlice[i][j] = manager.slices.get((i-1) >> 4).get((j-1) >> 4).blocks[(i-1) & 0b1111][(j-1) & 0b1111].getBlock();
                }
            }
        }

        //REMEMBER THAT THE WORLD SLICE HAS BLOCKS INDEXED AS 1 GREATER THAN THE ACTUAL COORDINATE IN EACH DIRECTION
        final int ZOFFSET_NORTH = 0;
        final int XOFFSET_WEST  = 0;
        final int XOFFSET_EAST  = 32;
        final int ZOFFSET_SOUTH = 32;

        for (int i = 0; i < xSectionCount; ++i) {
            for (int j = 0; j < ySectionCount; ++j) {
                final int XOFFSET = i * 32;
                final int ZOFFSET = j * 32;

                RoomSection currentRoomSection = new RoomSection();

                currentRoomSection.walls[DIRECTIONS.NORTH] = getWallType(worldSlice[XOFFSET]);
                currentRoomSection.walls[DIRECTIONS.SOUTH] = getWallType(worldSlice[XOFFSET + XOFFSET_EAST]);

                Block[] wallWest = new Block[33];
                for(int k = 0; k < 33; ++k) {
                    wallWest[k] = worldSlice[XOFFSET + k][ZOFFSET];
                }

                currentRoomSection.walls[DIRECTIONS.WEST] = getWallType(wallWest);

                Block[] wallEast = new Block[33];
                for(int k = 0; k < 33; ++k) {
                    wallEast[k] = worldSlice[XOFFSET + k][ZOFFSET + ZOFFSET_SOUTH];
                }

                currentRoomSection.walls[DIRECTIONS.EAST] = getWallType(wallEast);

                Sections[i][j] = currentRoomSection;

            }
        }

        //Ok so now i just have to identify rooms based off of the sections

        //A few rules:
        // 1. Fairy rooms always have 2 doors, 0 connections
        // 2. If it has 1 door, 0 connections, it must be Start, Watcher, or Puzzle
        // 3. If it has ANY connections, it is a corridor, and identification expansion must happen
        // 4. If it has 1 door, 0 connections, and the door is a blood door, it is the watcher room. There is only 1 of these, so it only needs to be checked once

        for(int i = 0; i < xSectionCount; ++i) {
            for(int j = 0; j < ySectionCount; ++j) {
                if(Sections[i][j].IsPartOfARoomYet) continue;
                ret.add(identifyIndividualRoom(Sections, i, j));
            }
        }

        return ret;
    }

    private static Room identifyIndividualRoom(RoomSection[][] sections, int sectionx, int sectionz) {
        int doorCount = 0;
        int connectCount = 0;
        boolean bloodDoorPresent = false;

        for(RoomWallType wall : sections[sectionx][sectionz].walls) {
            if (wall == RoomWallType.ROOM_WALL_TYPE_BLOOD_DOOR) bloodDoorPresent = true;
            if (wall == RoomWallType.ROOM_WALL_TYPE_BLOOD_DOOR ||
                wall == RoomWallType.ROOM_WALL_TYPE_DOOR)       doorCount++;
            if (wall == RoomWallType.ROOM_WALL_TYPE_CONNECTED)  connectCount++;
        }

        if(bloodDoorPresent && connectCount == 0 && doorCount == 1)
            return new Watcher(sectionx, sectionz, 0);
        else if(!bloodDoorPresent && connectCount == 0 && doorCount == 2)
            return new Fairy(sectionx, sectionz, 0);
        //If doorCount is 1 and connectCount is 0, its gotta be a puzzle or the start. For now, its all tic tac toe puzzle, because I
        //want to start writing the code for larger corridor rooms
        //TODO: Make this identify puzzle and start rooms. Maybe also miniboss rooms if those exist?
        else if(doorCount == 1 && connectCount == 0) {
            return new Puzzle(sectionx, sectionz, 0, Puzzle.PuzzleType.TICTACTOE);
        }

        return new GenericRoom(sectionx, sectionz, 0);
    }

    private static RoomWallType getWallType(Block[] wall) {
        assert wall.length == 33;
        if(wall[16] == Blocks.COAL_BLOCK ) {
            return RoomWallType.ROOM_WALL_TYPE_DOOR;
        }

        else if (wall[16] == Blocks.RED_TERRACOTTA) {
            return RoomWallType.ROOM_WALL_TYPE_BLOOD_DOOR;
        }

        //If the corner isn't air, that means its connected
        else if(wall[0] != Blocks.AIR ||
           wall[32] != Blocks.AIR) {
            return RoomWallType.ROOM_WALL_TYPE_CONNECTED;
        }
        else {
            for (Block b : wall) {
                if(b != Blocks.AIR) return RoomWallType.ROOM_WALL_TYPE_DOOR;
            }
            return RoomWallType.ROOM_WALL_TYPE_SEALED;
        }
    }
}
