public class Edge{ 
		public Node destination;
		public double weight;

		public Edge(Node destination, double weight) 
		{
			this.destination = destination;
			this.weight = weight;
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