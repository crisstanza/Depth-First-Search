package io.github.crisstanza.dfs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class DFS {

    private final ObjectMapper mapper = new ObjectMapper();

    private final Node[] graph;

    public DFS(String graph) throws JsonProcessingException {
        this.graph = mapper.readValue(graph, Node[].class);
    }

    public List<String> start() {
        final List<String> output = new ArrayList<>();
        this.dfs(this.graph[0], output);
        return output;
    }

    private void dfs(final Node node, final List<String> output) {
        output.add(node.label);
        node.visited = true;
        this.neighbours(node).stream().forEach(neighbour -> {
            if (!neighbour.visited) {
                this.dfs(neighbour, output);
            }
        });
    }

    private List<Node> neighbours(Node node) {
        Stream<Node> left = Arrays.stream(this.graph).filter(candidate -> equals(node.left, candidate.label));
        Stream<Node> right = Arrays.stream(this.graph).filter(candidate -> equals(node.right, candidate.label));
        return Stream.concat(left, right).collect(Collectors.toList());
    }

    private boolean equals(final String value1, final String value2) {
        if (value1 == null || value2 == null) {
            return false;
        }
        return value1.equals(value2);
    }

}
