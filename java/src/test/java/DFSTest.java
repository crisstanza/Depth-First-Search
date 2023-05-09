import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.crisstanza.dfs.DFS;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.platform.commons.annotation.Testable;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Testable
public final class DFSTest {

    public static List<String[]> testData() {
        return Arrays.asList(
                new String[]{"{\"label\": \"x\", \"left\": \"y\", \"right\": \"z\"}, {\"label\": \"z\"}, {\"label\": \"y\"}", "x y z"},
                new String[]{"{\"label\": \"Lorem Ipsum\"}", "Lorem Ipsum"}
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void test(final String input, final String expected) throws JsonProcessingException {
        final String graph = "[" + input + "]";
        final DFS solver = new DFS(graph);
        final List<String> output = solver.start();
        final String actual = String.join(" ", output);
        assertEquals(expected, actual);
    }

    @Test
    public void testException() throws JsonProcessingException {
        final String graph = "[]";
        final DFS solver = new DFS(graph);
        assertThrows(ArrayIndexOutOfBoundsException.class, solver::start);
    }
}
