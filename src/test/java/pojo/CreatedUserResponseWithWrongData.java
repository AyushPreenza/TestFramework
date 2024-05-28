package pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreatedUserResponseWithWrongData {

    private int code;
    private String meta;
    private List<CreatedUserDataWithWrongData> data;

}
