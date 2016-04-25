import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class MuhammadEmadUlEhsan_source {
	
	static Map<String, Object> vars = new HashMap<String, Object>();
	
	static Stack<String> stack = new Stack<String>();
	
	public static int addAndSave(String line) {
		if (line.indexOf("+") < line.indexOf("=")) {
			System.out.println("Error: Invalid assignment");
			return 0;
		}
		
		// y = y + x;
		String[] data = line.split("=");
		
		if (!vars.containsKey(data[0].trim())) {
			// left side is const
			System.out.println("Error: Cannot assign to const or undefined");
			return 0;
		}
		
		String[] pair = data[1].split("\\+");
		
//		System.out.println("Data: " + data[0] + " " + data[1]);
//		System.out.println("Pair: " + pair[0] + " " + pair[1]);
		
		// check whether pair contains variables or constants
		int v1 = 0;
		if (vars.containsKey(pair[0].trim())) {
			// var
			v1 = Integer.parseInt(vars.get(pair[0].trim()).toString());
		}
		else { 
			// const
			v1 = Integer.parseInt(pair[0].trim());
		}
		
		int v2 = 0;
		if (vars.containsKey(pair[1].trim())) {
			v2 = Integer.parseInt(vars.get(pair[1].trim()).toString());
		}
		else {
			v2 = Integer.parseInt(pair[1].trim());
		}
		
		// add and update
		vars.put(data[0].trim(), v1 + v2);
		
		return v1+v2;
	}
	
	public static int subtractAndSave(String line) {
		if (line.indexOf("-") < line.indexOf("=")) {
			System.out.println("Error: Invalid assignment");
			return 0;
		}
		
		// y = y + x;
		String[] data = line.split("=");
		
		if (!vars.containsKey(data[0].trim())) {
			// left side is const
			System.out.println("Error: Cannot assign to const or undefined");
			return 0;
		}
		
		String[] pair = data[1].split("\\-");
		
//		System.out.println("Data: " + data[0] + " " + data[1]);
//		System.out.println("Pair: " + pair[0] + " " + pair[1]);
		
		// check whether pair contains variables or constants
		int v1 = 0;
		if (vars.containsKey(pair[0].trim())) {
			// var
			v1 = Integer.parseInt(vars.get(pair[0].trim()).toString());
		}
		else { 
			// const
			v1 = Integer.parseInt(pair[0].trim());
		}
		
		int v2 = 0;
		if (vars.containsKey(pair[1].trim())) {
			v2 = Integer.parseInt(vars.get(pair[1].trim()).toString());
		}
		else {
			v2 = Integer.parseInt(pair[1].trim());
		}
		
		// add and update
		vars.put(data[0].trim(), v1 - v2);
		return v1-v2;
	}
	
	public static int multiplyAndSave(String line) {
		if (line.indexOf("*") < line.indexOf("=")) {
			System.out.println("Error: Invalid assignment");
			return 0;
		}
		
		// y = y + x;
		String[] data = line.split("=");
		
		if (!vars.containsKey(data[0].trim())) {
			// left side is const
			System.out.println("Error: Cannot assign to const or undefined");
			return 0;
		}
		
		String[] pair = data[1].split("\\*");
		
//		System.out.println("Data: " + data[0] + " " + data[1]);
//		System.out.println("Pair: " + pair[0] + " " + pair[1]);
		
		// check whether pair contains variables or constants
		int v1 = 0;
		if (vars.containsKey(pair[0].trim())) {
			// var
			v1 = Integer.parseInt(vars.get(pair[0].trim()).toString());
		}
		else { 
			// const
			v1 = Integer.parseInt(pair[0].trim());
		}
		
		int v2 = 0;
		if (vars.containsKey(pair[1].trim())) {
			v2 = Integer.parseInt(vars.get(pair[1].trim()).toString());
		}
		else {
			v2 = Integer.parseInt(pair[1].trim());
		}
		
		// add and update
		vars.put(data[0].trim(), v1 * v2);
		return v1*v2;
	}

	public static int divideAndSave(String line) {
		if (line.indexOf("/") < line.indexOf("=")) {
			System.out.println("Error: Invalid assignment");
			return 0;
		}
		
		// y = y + x;
		String[] data = line.split("=");
		
		if (!vars.containsKey(data[0].trim())) {
			// left side is const
			System.out.println("Error: Cannot assign to const or undefined");
			return 0;
		}
		
		String[] pair = data[1].split("\\/");
		
//		System.out.println("Data: " + data[0] + " " + data[1]);
//		System.out.println("Pair: " + pair[0] + " " + pair[1]);
		
		// check whether pair contains variables or constants
		int v1 = 0;
		if (vars.containsKey(pair[0].trim())) {
			// var
			v1 = Integer.parseInt(vars.get(pair[0].trim()).toString());
		}
		else { 
			// const
			v1 = Integer.parseInt(pair[0].trim());
		}
		
		int v2 = 0;
		if (vars.containsKey(pair[1].trim())) {
			v2 = Integer.parseInt(vars.get(pair[1].trim()).toString());
		}
		else {
			v2 = Integer.parseInt(pair[1].trim());
		}
		
		// add and update
		vars.put(data[0].trim(), v1 / v2);
		return v1 / v2;
	}
	
	static void Let(String line) {
		// Let x = y;
		line = line.replace("Let ", "");
		line = line.replace(";", "").trim();
		
		// now line: x = y
		String[] pair = line.split("=");
		vars.put(pair[0].trim(), pair[1].trim());
	}
	
	static void Print(String line) {
		// Print x
		line = line.replace("Print ", "").trim();
		System.out.println(vars.get(line));
	}
	
	public static void printState() {
		
		Set<String> keys = vars.keySet();
		for (String k : keys) {
			System.out.println(k + " -> " + vars.get(k));
		}
	}
	

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\alphahack\\workspace\\interpreter\\src\\code.txt"));
		String line = "";
		int i = 0;
		while ((line = br.readLine()) != null) {
			
			System.out.println("Line# " + (++i) + ": " + line);
			line = line.replace(";", "");
			if (line.trim().length() == 0) {
				continue;
			}
			if (line.contains("+")) {
				addAndSave(line);
//				System.out.println("y = " + vars.get("y"));
			}
			else if (line.contains("-")) {
				subtractAndSave(line);
//				System.out.println("y = " + vars.get("y"));
			}
			else if (line.contains("*")) {
				multiplyAndSave(line);
//				System.out.println("y = " + vars.get("y"));
			}
			else if (line.contains("/")) {
				divideAndSave(line);
//				System.out.println("y = " + vars.get("y"));
			}
			else if (line.contains("Print")) {
				Print(line);
			}
			else if (line.contains("Let")) {
				Let(line);
			}
			
			printState();
		}
		
//		vars.put("x", 2);
//		vars.put("y", 4);
//		
//		String line = "y = x + y";
//		
//		line = "y = y - x";
//		
//		line = "y = y * x";
//		
//		line = "y = y / x";
//		
//		line = "Print x";
//		
//		line = "Let z = 10;";
//		System.out.println("Z: " + vars.get("z"));
	}
}
