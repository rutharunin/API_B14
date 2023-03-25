package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;
@JsonIgnoreProperties(ignoreUnknown = true)
public class FootballCompetitionPogo {
    private int id;
    private FootballAreaPojo area;
    private String name;
    private String code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FootballAreaPojo getArea() {
        return area;
    }

    public void setArea(FootballAreaPojo area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
