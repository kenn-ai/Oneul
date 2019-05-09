package kr.co.oneul.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.MediaType;

public class MediaUtils {

	private static Map<String, MediaType> mediaMap;

	static {
		mediaMap = new HashMap<String, MediaType>();
		mediaMap.put("JPG", MediaType.IMAGE_JPEG);
		mediaMap.put("GIF", MediaType.IMAGE_GIF);
		mediaMap.put("PNG", MediaType.IMAGE_PNG);
	}

	public static MediaType getMediaType(String type) {
		return mediaMap.get(type.toUpperCase());
	}

	public static boolean checkFileType(String str) {
		String allowPattern = ".+\\.(jpg|gif|png|jpeg)$";
		
		boolean result = false;

		Pattern p = Pattern.compile(allowPattern);
		Matcher m = p.matcher(str);
		result = m.matches();

		return result;
	}
}
