package controller.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.AvengerDaoImpl;
import model.Avenger;
import model.Death;

public class AvengerFactory {

	private static final String REGEX_GET_NAME_FROM_URL = "\\/([\\w-?,?.?]+)_";
	AvengerDaoImpl aDao = new AvengerDaoImpl();

	public static Avenger createAvenger(String line) {
		String[] split = line.split(",");

		List<Death> deaths = new ArrayList<Death>();
		for (int i = 10; i <= 19; i++) {
			Boolean death = getDeath(split, i);
			i++;
			Boolean ressurge = getDeath(split, i);
			deaths.add(Death.builder().death(death).ressurge(ressurge).build());
		}

		Avenger a = Avenger.builder()
							.name(getName(split[0], split[1]))
							.current(split[3])
							.gender(split[4])
							.year(Integer.parseInt(split[7]))
							.deaths(deaths).build();

//		System.out.println(a);
//		if (line.contains("Tippy")) {
//			for (int i = 0 ; i < 20 ; i++) {
//				System.out.println(split[i]);
//			}
//			System.out.println(getName(split[0],split[1]));
//		}

		return a;
	}

	private static Boolean getDeath(String[] split, int i) {
		Boolean death = null;
		if ("YES".equalsIgnoreCase(split[i].trim())) {
			death = true;
		} else if ("NO".equalsIgnoreCase(split[i].trim())) {
			death = false;
		}
		return death;
	}

	private static String getName(String url, String name) {
		if (name.trim().isEmpty()) {
			Pattern pattern = Pattern.compile(REGEX_GET_NAME_FROM_URL);
			Matcher matcher = pattern.matcher(url);
			if (matcher.find()) {
				return matcher.group(1).replaceAll("_", " ");
			} 
		} 
		
		return name.replaceAll("\"", "");
	}

}
