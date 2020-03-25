package model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
public class Avenger {

	private String name;
	private String current;
	private String gender;
	private int year;
	private List<Death> deaths;
	
}
