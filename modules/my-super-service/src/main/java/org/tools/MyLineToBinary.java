package org.tools;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MyLineToBinary {
    public MyLineToBinary() {
    }

    public static byte[] objToBytes(Object obj) {
        ByteArrayOutputStream byteArrayOutputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
            byte[] var3 = byteArrayOutputStream.toByteArray();
            return var3;
        } catch (IOException var17) {
            var17.printStackTrace();
        } finally {
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException var16) {
                    var16.printStackTrace();
                }
            }

            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException var15) {
                    var15.printStackTrace();
                }
            }

        }

        return null;
    }

    public static Object restore(byte[] bytes) {
        ByteArrayInputStream byteArrayInputStream = null;
        ObjectInputStream objectInputStream = null;

        try {
            byteArrayInputStream = new ByteArrayInputStream(bytes);
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Object var3 = objectInputStream.readObject();
            return var3;
        } catch (ClassNotFoundException | IOException var17) {
            var17.printStackTrace();
        } finally {
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException var16) {
                    var16.printStackTrace();
                }
            }

            if (byteArrayInputStream != null) {
                try {
                    byteArrayInputStream.close();
                } catch (IOException var15) {
                    var15.printStackTrace();
                }
            }

        }

        return null;
    }
}

