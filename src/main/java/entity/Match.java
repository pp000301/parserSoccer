package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Match {
    private String commandHomeTitle;
    private String commandAwyTitle;
    private String score;
    private List<Integer> timeOfGoal;
}
