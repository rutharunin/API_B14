package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class FootballPojo {
    private int count;
    private List<FootballCompetitionPogo> competitions;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<FootballCompetitionPogo> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(List<FootballCompetitionPogo> competitions) {
        this.competitions = competitions;
    }
}
