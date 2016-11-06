package palmer.cristina.domain;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by User on 06/11/2016.
 */
@Entity
public class Statistic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Enumerated(EnumType.STRING)
    private Position position;
    private double avgBaskets;
    private double avgAssists;
    private double avgRebound;

    public Statistic(Position position, double avgBaskets, double avgAssists, double avgRebound) {
        this.position = position;
        this.avgBaskets = avgBaskets;
        this.avgAssists = avgAssists;
        this.avgRebound = avgRebound;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public double getAvgBaskets() {
        return avgBaskets;
    }

    public void setAvgBaskets(double avgBaskets) {
        this.avgBaskets = avgBaskets;
    }

    public double getAvgAssists() {
        return avgAssists;
    }

    public void setAvgAssists(double avgAssists) {
        this.avgAssists = avgAssists;
    }

    public double getAvgRebound() {
        return avgRebound;
    }

    public void setAvgRebound(double avgRebound) {
        this.avgRebound = avgRebound;
    }
}
