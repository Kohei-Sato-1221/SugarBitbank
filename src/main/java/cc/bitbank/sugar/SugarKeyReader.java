package cc.bitbank.sugar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.stream.Stream;

public class SugarKeyReader {
	public static SugarKeyReader skr = new SugarKeyReader();
//	private final String KEY_FILE_PATH = "/Users/kohei.sato/bitbank.key";
	private final String KEY_FILE_PATH = "/home/kohei.sato/work/bitbank.key";
	private static HashMap<String, String> keyMap;
	
	private SugarKeyReader(){
		keyMap = new HashMap<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(KEY_FILE_PATH)));
			Stream<String> lines = br.lines();
			lines.forEach(line -> {
				String[] keyAndValue = line.split(",");
				keyMap.put(keyAndValue[0], keyAndValue[1]);
			});
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static SugarKeyReader getReader() {
		return skr;
	}
	
	
	public void showKeyValue() {
		System.out.println("apikey:" + keyMap.get("apikey"));
		System.out.println("secretkey:" + keyMap.get("secretkey"));
	}
	
	public String getApiKey() {
		return keyMap.get("apikey");
	}

	public String getSecretKey() {
		return keyMap.get("secretkey");
	}
}
