package eliasstar.gson;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public final class OptionalTypeAdapterTests {

    private static Gson gson;
    private static Gson gsonWithNulls;

    @BeforeAll
    public static void initGson() {
        gson = new GsonBuilder().registerTypeAdapterFactory(OptionalTypeAdapterFactory.instance()).create();
        gsonWithNulls = gson.newBuilder().serializeNulls().create();
    }

    @Test
    public void testOptionalSerialization() {
        var testObj = new OptionalTestObject();
        assertEquals("{}", gson.toJson(testObj));
        assertEquals("{\"testString\":null}", gsonWithNulls.toJson(testObj));

        testObj.testOptional = Optional.of("test");
        assertEquals("{\"testOptional\":\"test\"}", gson.toJson(testObj));
        assertEquals("{\"testString\":null,\"testOptional\":\"test\"}", gsonWithNulls.toJson(testObj));

        testObj.testString = "test";
        assertEquals("{\"testString\":\"test\",\"testOptional\":\"test\"}", gson.toJson(testObj));
        assertEquals("{\"testString\":\"test\",\"testOptional\":\"test\"}", gsonWithNulls.toJson(testObj));

        testObj.testOptional = Optional.empty();
        assertEquals("{\"testString\":\"test\"}", gson.toJson(testObj));
        assertEquals("{\"testString\":\"test\"}", gsonWithNulls.toJson(testObj));
    }

    @Test
    public void testOptionalDeserialization() {
        var testObj = new OptionalTestObject();
        assertEquals(testObj, gson.fromJson("{}", OptionalTestObject.class));

        testObj.testOptional = Optional.of("test");
        assertEquals(testObj, gson.fromJson("{\"testOptional\":\"test\"}", OptionalTestObject.class));

        testObj.testString = "test";
        assertEquals(testObj, gson.fromJson("{\"testString\":\"test\",\"testOptional\":\"test\"}", OptionalTestObject.class));

        testObj.testOptional = Optional.empty();
        assertEquals(testObj, gson.fromJson("{\"testString\":\"test\"}", OptionalTestObject.class));
    }

    private static final class OptionalTestObject {

        private String testString;
        private Optional<String> testOptional;

        private OptionalTestObject() {
            testOptional = Optional.empty();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj != null && obj instanceof OptionalTestObject) {
                var other = (OptionalTestObject) obj;

                if (this == other)
                    return true;

                if (testString == null)
                    return other.testString == null && testOptional.equals(other.testOptional);

                return testString.equals(other.testString) && testOptional.equals(other.testOptional);
            }

            return false;
        }
    }

}