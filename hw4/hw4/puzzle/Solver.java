package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import hw4.puzzle.Solver.SearchNode;

import javax.naming.directory.SearchControls;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;

public class Solver {
    private LinkedList<WorldState> path = new LinkedList<>();
    private HashSet<WorldState> recordOfState = new HashSet<>();
    class SearchNode{
        private WorldState w;
        private SearchNode previous;
        private int distanceFromInitial;
        private int distanceToEnd;
        private int distanceSum;

        public SearchNode(WorldState w, SearchNode previous, int distanceFromInitial) {
            this.w = w;
            this.previous = previous;
            this.distanceFromInitial = distanceFromInitial;
            distanceToEnd = w.estimatedDistanceToGoal();
            distanceSum = distanceFromInitial + distanceToEnd;
        }

    }

    public Solver(WorldState initial){
        MinPQ<SearchNode> q = new MinPQ<>(new Comparator<SearchNode>() {
            @Override
            public int compare(SearchNode o1, SearchNode o2) {
                return (o1.distanceSum) - (o2.distanceSum);
            }
        });
        q.insert(new SearchNode(initial,null,0));
        recordOfState.add(initial);
        int flag = 0;
        while(!q.isEmpty()){
            SearchNode oldMin = q.min();
            if(oldMin.w.isGoal()){
                break;
            }
            q.delMin();
            for(WorldState s:oldMin.w.neighbors()){
                if(oldMin.previous == null || (oldMin.previous != null && !s.equals(oldMin.previous.w))){
//                if(recordOfState.add(s)){
//                    System.out.println(s);
                    flag++;
                    q.insert(new SearchNode(s,oldMin,oldMin.distanceFromInitial+1));
                }
            }
        }
        System.out.println(flag);
        SearchNode trackPath =  q.min();
        while (trackPath != null){
            path.addFirst(trackPath.w);
            trackPath = trackPath.previous;
        }

    }
    public int moves(){
        return path.size() - 1;
    }
    public Iterable<WorldState> solution(){
        return path;
    }
}
