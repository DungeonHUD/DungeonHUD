package xyz.fourthirdskiwidrive.dungeonshud.rooms;

public enum RoomType {
    CORRIDOR,                               // Any room that essentially connects things. Can have minibosses. (brown on map)
    START,                                  // The starting room (green on map)
    FAIRY,                                  // The fairy room (pink on map)
    WATCHER,                                // The boss room in which the watcher fight happens (Red on map)
    PUZZLE,                                 // Any puzzle room (Tic tac toe, riddle, water, etc) (purple on map)
    MINIBOSS                                // Miniboss rooms (yellow on map)
}
