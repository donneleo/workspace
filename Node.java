import java.util.ArrayList;



public class Node implements Comparable<Node>{

		public int streetNumber;
		public double distance_from_source = Double.MAX_VALUE;
		public boolean visited;
		public ArrayList<Edge> edges = new ArrayList<Edge>();
		public Node previousNode;

		public Node(int streetNumber) {
			this.streetNumber = streetNumber;
			this.edges = new ArrayList<>();
		}

		public void addNeighbourNode(Edge edge) {
			this.edges.add(edge);
		}

		public ArrayList<Edge> getEdges(){
			return edges;
		}

		public void setEdges(ArrayList<Edge> edges) {
			this.edges = edges;
		}

		public Node getPreviousNode() {
			return previousNode;
		}
		public void setPreviousNode(Node previousNode) {
			this.previousNode = previousNode;
		}

		public double getDistanceFromSource() {
			return distance_from_source;
		}

		public void setDistanceFromSource(double distance_from_source) {
			this.distance_from_source = distance_from_source;
		}

		public boolean isVistied() {
			return visited;
		}

		public void isVisited(boolean visited) {
			this.visited = visited;
		}


		@Override
		public int compareTo(Node nextNode) {
			return Double.compare(this.distance_from_source, nextNode.distance_from_source);
		}
	}
