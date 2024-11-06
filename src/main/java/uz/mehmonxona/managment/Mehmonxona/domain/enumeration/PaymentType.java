package uz.mehmonxona.managment.Mehmonxona.domain.enumeration;

public enum PaymentType {
    НАКД,
    ПЛАСТИК,
    ПЕРЕВОД;

    public static PaymentType fromString(String string) {
        for (PaymentType paymentType : PaymentType.values()) {
            if (paymentType.toString().equals(string)) {
                return paymentType;
            }
        }
        throw new IllegalArgumentException("Invalid PaymentType: " + string);
    }
}
