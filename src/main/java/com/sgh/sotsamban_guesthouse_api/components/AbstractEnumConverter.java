package com.sgh.sotsamban_guesthouse_api.components;

import com.sgh.sotsamban_guesthouse_api.enums.GenericEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public abstract class AbstractEnumConverter<E extends Enum<E> & GenericEnum<E, T>, T>
        implements AttributeConverter<E, T> {

    private final Class<E> enumClass;

    public AbstractEnumConverter(Class<E> enumClass) {
        this.enumClass = enumClass;
    }

    @Override
    public T convertToDatabaseColumn(E attribute) {
        return attribute != null ? attribute.getValue() : null;
    }

    @Override
    public E convertToEntityAttribute(T dbData) {
        if (dbData == null) {
            return null;
        }

        for (E enumValue : enumClass.getEnumConstants()) {
            if (enumValue.getValue().equals(dbData)) {
                return enumValue;
            }
        }

        return null;
    }
}
