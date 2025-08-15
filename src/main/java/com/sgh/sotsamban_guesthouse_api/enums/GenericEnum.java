package com.sgh.sotsamban_guesthouse_api.enums;

public interface GenericEnum<E extends Enum<E>, T> {
    T getValue();
    String getLabel();
}
