package org.gamecenter.serializer.constants;

/**
 * Created by Chevis on 14-7-24.
 */
public enum FieldType {

    BYTE("BYTE"),
    WORD("WORD"),
    DWOARD("DWORD"),
    STRING("STRING"),
    BYTES("BYTES");


    private final String type;

    private FieldType(String type) {
        this.type = type;
    }

}
