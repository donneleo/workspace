
public class Edge{ 
		public Node source;
		public Node destination;
		public double weight;

		public Edge(Node source, Node destination, double weight) 
		{
			this.source = source;
			this.destination = destination;
			this.weight = weight;
		}

		public Node getSource() {
			return source;
		}
		public void setSource(Node node) {
			this.source = source;
		}

		public Node getDestination() {
			return destination;
		}
		public void setDestination(Node node) {
			this.destination = destination;
		}

		public double getWeight(){
			return weight;
		}
		public void setWeight(double weight) {
			this.weight = weight;
		}


	}
