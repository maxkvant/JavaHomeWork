package task9;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Serialization can serialize and deserialize objects,
 * which fields are public and have a primitive type,
 * which class have an empty constructor.
 */

public class Serialization {
    private static final String nullString = (new Random())
            .ints()
            .mapToObj(String::valueOf)
            .limit(10)
            .collect(Collectors.joining());

    /**
     * serializes object to stream.
     * object fields should be public and have a primitive type.
     */

    public static void serialize(Object object, OutputStream stream) throws Exception {
        Class objectClass = object.getClass();
        DataOutputStream dataStream = new DataOutputStream(stream);
        for (Field field : objectClass.getFields()) {
            if (field.getType() == String.class) {
                String value = (String) field.get(object);
                if (value == null) {
                    value = nullString;
                }
                dataStream.writeUTF(value);
            } else if (field.getType() == byte.class) {
                dataStream.writeByte((Byte) field.get(object));
            } else if (field.getType() == short.class) {
                dataStream.writeShort((Short) field.get(object));
            } else if (field.getType() == char.class) {
                dataStream.writeChar((Character) field.get(object));
            } else if (field.getType() == int.class) {
                dataStream.writeInt((Integer) field.get(object));
            } else if (field.getType() == long.class) {
                dataStream.writeLong((Long) field.get(object));
            } else if (field.getType() == float.class) {
                dataStream.writeFloat((Float) field.get(object));
            } else if (field.getType() == double.class) {
                dataStream.writeDouble((Double) field.get(object));
            } else if (field.getType() == boolean.class) {
                dataStream.writeBoolean((Boolean) field.get(object));
            } else {
                throw new Exception("can't parse " + field.getName());
            }
        }
    }

    /**
     * deserialize object, which was serialized by "serialize" from stream;
     * returns instance of objectClass.
     * objectClass should have an empty constructor.
     */

    public static Object deserialize(InputStream stream, Class<?> objectClass) throws Exception {
        Object object = objectClass.getConstructor().newInstance();
        DataInputStream dataStream = new DataInputStream(stream);
        for (Field field : objectClass.getFields()) {
            if (field.getType() == String.class) {
                String value = dataStream.readUTF();
                if (value.equals(nullString)) {
                    value = null;
                }
                field.set(object, value);
            } else if (field.getType() == byte.class) {
                field.set(object, dataStream.readByte());
            } else if (field.getType() == short.class) {
                field.set(object, dataStream.readShort());
            } else if (field.getType() == char.class) {
                field.set(object, dataStream.readChar());
            } else if (field.getType() == int.class) {
                field.set(object, dataStream.readInt());
            } else if (field.getType() == long.class) {
                field.set(object, dataStream.readLong());
            } else if (field.getType() == float.class) {
                field.set(object, dataStream.readFloat());
            } else if (field.getType() == double.class) {
                field.set(object, dataStream.readDouble());
            } else if (field.getType() == boolean.class) {
                field.set(object, dataStream.readBoolean());
            } else {
                throw new Exception("can't parse " + field.getName());
            }
        }
        return object;
    }
}
