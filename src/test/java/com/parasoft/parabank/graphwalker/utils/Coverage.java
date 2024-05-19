package com.parasoft.parabank.graphwalker.utils;

/**
 * Holds coverage values for the graph walker tests
 */
public class Coverage {
    public static final String RandomEdgeCoverage100 = "random(edge_coverage(100))";
    public static final String QuickRandomEdgeCoverage100 = "quick_random(edge_coverage(100))";
    public static final String WeightedRandomEdgeCoverage100 = "weighted_random(edge_coverage(100))";
    public static final String ShortestAllPathsEdgeCoverage100 = "shortest_all_paths(edge_coverage(100))";
    public static final String DirectedChinesePostmanCoverage = "directed_chinese_postman(edge_coverage(100))";
    public static final String Default = RandomEdgeCoverage100;
}
