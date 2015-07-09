
public class MyLink {
	double capacity;
	double weight;
	int id;
	
	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	



	public MyLink(MyLink findEdge) {
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		return "E" + id;
	}
	
	public MyLink(double weight, int edgeCount) {
		this.id = edgeCount;
		this.weight = weight;
		// this.capacity = capacity;
	}

}
