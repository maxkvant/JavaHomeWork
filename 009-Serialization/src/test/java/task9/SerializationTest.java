package task9;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SerializationTest {
    @org.junit.Test
    public void trackTest() throws Exception {
        Track track1 = new Track();
        track1.name = "Macarena\n\n";
        track1.rating = 101;
        track1.length = 3;

        Track track2 = new Track();
        track2.name = "Out Of The Black\n";
        track2.rating = 99;
        track2.length = 4;

        ByteArrayOutputStream outstr = new ByteArrayOutputStream(10000);
        Serialization.serialize(track1, outstr);
        Serialization.serialize(track2, outstr);

        ByteArrayInputStream instr = new ByteArrayInputStream(outstr.toByteArray());
        outstr.close();

        Track track4 = (Track) Serialization.deserialize(instr, Track.class);
        Track track3 = (Track) Serialization.deserialize(instr, Track.class);
        instr.close();

        assertEquals(track4.name, "Macarena\n\n");
        assertEquals(track4.rating, 101);
        assertEquals(track4.length, 3);

        assertEquals(track3.name, "Out Of The Black\n");
        assertEquals(track3.rating, 99);
        assertEquals(track3.length, 4);
    }

    @org.junit.Test
    public void primitivesTest() {
        try {
            Primitives primitives1 = new Primitives();
            primitives1.booleanField = true;

            ByteArrayOutputStream outstr = new ByteArrayOutputStream(10000);
            Serialization.serialize(primitives1, outstr);

            ByteArrayInputStream instr = new ByteArrayInputStream(outstr.toByteArray());
            outstr.close();

            Primitives primitives2 = (Primitives) Serialization.deserialize(instr, Primitives.class);
            assertTrue(primitives2.booleanField);
            assertEquals(null, primitives2.stringField);
            instr.close();
        } catch (Exception e) {
            assertFalse(true);
        }
    }
}