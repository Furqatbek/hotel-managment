package uz.mehmonxona.managment.Mehmonxona.domain.enumeration;

public enum RoomTypes {
    ONE,
    TWIN,
    DOUBLE,
    TRIPLE_LUXE,
    DOUBLE_LUXE,
    TWIN_LUXE,
    FAMILY;

    public static RoomTypes fromString(String string) {
        for (RoomTypes type : RoomTypes.values()) {
            if (type.toString().equals(string)) {
                return type;
            }
        }
        throw new IllegalArgumentException(string);
    }
}
