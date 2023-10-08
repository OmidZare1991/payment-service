package com.payment.processor.util;

public enum PaymentType {
    ONLINE("online"), OFFLINE("offline");
    public final String name;

    PaymentType(String name) {
        this.name = name;
    }

    public static PaymentType getType(String name) {

        for (PaymentType paymentType : values()) {
            if (name.equals(paymentType.name)) {
                return paymentType;
            }
        }
        throw new IllegalArgumentException("Invalid PaymentType: " + name);
    }

    public String getName() {
        return name;
    }
}
