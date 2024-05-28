package pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatedUserResponse {

	private int code;
	private String meta;
	private CreatedUserData data;

}
