package model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Death {

	private Boolean death;
	private Boolean ressurge;
	
	public Death(Boolean death, Boolean ressurge) {
		this.death = death;
		this.ressurge = ressurge;
	}

}
