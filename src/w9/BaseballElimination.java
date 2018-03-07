package w9;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;

/**
 * an immutable data type that represnts a sports division and determines which teams are mathematically eliminated
 *
 * @author huangjie
 * @create 2018-03-06-10:41
 **/
public class BaseballElimination {
    private final HashMap<String, Integer> teams;
    private final int[] wins;
    private final int[] losses;
    private final int[] remaining;
    private final int[][] g;
    private final boolean[] isEliminated;
    private final Bag<String>[] cer;

    // create a baseball division from given filename in format specified below
    public BaseballElimination(String filename) {
        double EPSILON = 1E-11;
        In in = new In(filename);
        int n = in.readInt();
        teams = new HashMap<>(n);
        wins = new int[n];
        losses = new int[n];
        remaining = new int[n];
        g = new int[n][n];
        isEliminated = new boolean[n];
        // cer的元素在下面初始化
        cer = (Bag<String>[]) new Bag[n];

        String[] names = new String[n];

        for (int i = 0; i < n; i++) {
            String name = in.readString();
            teams.put(name, i);
            names[i] = name;
            wins[i] = in.readInt();
            losses[i] = in.readInt();
            remaining[i] = in.readInt();
            for (int j = 0; j < n; j++) {
                g[i][j] = in.readInt();
            }
            // 在这里初始化cer的元素
            cer[i] = new Bag<>();
        }

        // Trivial elimination
        for (String team1 : names) {
            for (String team2 : names) {
                if (team1 == team2) continue;
                else if (wins(team1) + remaining(team1) < wins(team2)) {
                    isEliminated[teams.get(team1)] = true;
                    cer[teams.get(team1)].add(team2);
                }
            }
        }

        // Nontrivial elimination
        int V = n * (n - 1) / 2 + 2;
        for (int i = 0; i < n; i++) {
            if (!isEliminated[i]) {
                int minWins = wins[i] + remaining[i];
                FlowNetwork G = new FlowNetwork(V);
                int v = 0;
                for (int j = 0; j < n; j++) {
                    if (j == i) continue;
                    G.addEdge(new FlowEdge(v++, V - 1, minWins - wins[j]));
                }
                for (int j = 0; j < n; j++) {
                    if (j == i) continue;
                    for (int k = 0; k < n; k++) {
                        if (k == i || k <= j) continue;
                        if (j < i) G.addEdge(new FlowEdge(v, j, Double.POSITIVE_INFINITY));
                        else G.addEdge(new FlowEdge(v, j - 1, Double.POSITIVE_INFINITY));
                        if (k < i) G.addEdge(new FlowEdge(v, k, Double.POSITIVE_INFINITY));
                        else G.addEdge(new FlowEdge(v, k - 1, Double.POSITIVE_INFINITY));
                        G.addEdge(new FlowEdge(V - 2, v, g[j][k]));
                        v++;
                    }
                }
                FordFulkerson ff = new FordFulkerson(G, V - 2, V - 1);
                for (FlowEdge e : G.adj(V - 2)) {
                    if (Math.abs(e.residualCapacityTo(V - 2) - e.capacity()) > EPSILON) isEliminated[i] = true;
                }
                for (int j = 0; j < n - 1; j++) {
                    if (!ff.inCut(j)) continue;
                    if (j < i) cer[i].add(names[j]);
                    else cer[i].add(names[j + 1]);
                }
            }
        }
    }

    public static void main(String[] args) {
        BaseballElimination division = new BaseballElimination(args[0]);
        for (String team : division.teams()) {
            if (division.isEliminated(team)) {
                StdOut.print(team + " is eliminated by the subset R={ ");
                for (String t : division.certificateOfElimination(team)) StdOut.print(t + " ");
                StdOut.println("}");
            } else StdOut.println(team + " is no eliminated");
        }
    }

    // number of teams
    public int numberOfTeams() {
        return teams.size();
    }

    // all teams
    public Iterable<String> teams() {
        return teams.keySet();
    }

    // number of wins for given team
    public int wins(String team) {
        validate(team);
        return wins[teams.get(team)];
    }

    // number of losses for given team
    public int losses(String team) {
        validate(team);
        return losses[teams.get(team)];
    }

    // number of remaining games for given team
    public int remaining(String team) {
        validate(team);
        return remaining[teams.get(team)];
    }

    // number of remaining games between team1 and team2
    public int against(String team1, String team2) {
        validate(team1);
        validate(team2);
        return g[teams.get(team1)][teams.get(team2)];
    }

    // is given team eliminated?
    public boolean isEliminated(String team) {
        validate(team);
        return isEliminated[teams.get(team)];
    }

    // subset R of teams that eliminates given team; null if not eliminated
    public Iterable<String> certificateOfElimination(String team) {
        validate(team);
        return cer[teams.get(team)].isEmpty() ? null : cer[teams.get(team)];
    }

    private void validate(String team) {
        if (!teams.containsKey(team)) throw new IllegalArgumentException("can not find this team");
    }

}

