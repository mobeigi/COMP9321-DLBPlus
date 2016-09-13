package edu.unsw.comp9321;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Extractor {
	private static int limit = 20000;
	private static String path = "C:/Users/Erik-z3462813/workspace/Ass1/WebContent/WEB-INF/dblp.xml";
	private static Pattern reg = Pattern.compile("</article>|</inproceedings>|</proceedings>|</book>|</incollection>|</phdthesis>|</mastersthesis>|</www>");
	private static File file = new File("C:/Users/Erik-z3462813/workspace/Ass1/WebContent/WEB-INF/sample.xml");
	
	public static void main(String[] args) throws IOException {
		runExtract();
	}
	
	private static void runExtract() throws IOException{
		int count = 0;
		String line = "";
		BufferedReader br = new BufferedReader(new FileReader(path));
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
		Matcher matcher;
		
		while((line = br.readLine()) != null){
			if(count == limit){
				break;
			}
			matcher = reg.matcher(line);
			boolean isMatched = matcher.matches();
			
			if(isMatched){
				count++;
			}
			
			bw.write(line);
			bw.newLine();
		}
		
		bw.close();
		br.close();
		
		System.out.println("Complete!");
		
		
	}
}
