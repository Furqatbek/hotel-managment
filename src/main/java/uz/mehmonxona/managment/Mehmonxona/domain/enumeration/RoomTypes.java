package uz.mehmonxona.managment.Mehmonxona.domain.enumeration;

public enum RoomTypes {
    BIR_KISHILIK,
    IKKI_KISHILIK,
    UCH_KISHILIK,
    TORT_KISHILIK;

    public static RoomTypes fromString(String string) {
        for (RoomTypes type : RoomTypes.values()) {
            if (type.toString().equals(string)) {
                return type;
            }
        }
        throw new IllegalArgumentException(string);
    }
}
