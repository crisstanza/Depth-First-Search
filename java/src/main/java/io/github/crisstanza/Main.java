package io.github.crisstanza;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.crisstanza.dfs.DFS;

import java.util.List;

public final class Main {

    public static void main(final String[] args) throws JsonProcessingException {
        final String input = "{\"label\":\"A\",\"left\":\"B\",\"right\":\"D\"},{\"label\":\"B\",\"left\":\"C\",\"right\":\"E\"},{\"label\":\"C\",\"left\":\"F\"},{\"label\":\"D\"},{\"label\":\"E\"},{\"label\":\"F\"}";
        final String graph = "[" + input + "]";
        final DFS solver = new DFS(graph);
        final List<String> output = solver.start();
        System.out.printf(String.join(" ", output));
    }

}