package za2d2;

public class Test {

	public static void main(String[] args) throws EtazhException {
		House h1 = new House(10, 100, "ulica 1", 1, "Ivan Ivanov");
		House h2 = new House(20, 200, "ulica 2", 2, "Petar Petrov");
		House h3 = new House(30, 300, "ulica 3", 3, "Stoyan Stoyanov");
		House []arr = {h1, h2, h3};
		int index = h1.prostorna(arr);
		System.out.println(arr[index].height+"; "+arr[index].plosht+"; "+arr[index].adress+"; "+arr[index].brEt+"; "+arr[index].name);
		
	}

}
