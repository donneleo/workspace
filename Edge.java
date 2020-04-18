public class Edge{ 
		public int destination;
		public double weight;

		public Edge(int destination, double weight) 
		{
			this.destination = destination;
			this.weight = weight;
		}

		public int getDestination() {
			return destination;
		}

		public double getWeight(){
			return weight;
		}
}