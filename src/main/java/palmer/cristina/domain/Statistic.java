package palmer.cristina.domain;

import javax.persistence.*;

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
    private int minBaskets;
    private int maxBaskets;

    public Statistic(Position position, int minBaskets, int maxBaskets, double avgBaskets) {
        this.position = position;
        this.minBaskets = minBaskets;
        this.maxBaskets = maxBaskets;
        this.avgBaskets = avgBaskets;
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

    public int getMinBaskets() {
        return minBaskets;
    }

    public void setMinBaskets(int minBaskets) {
        this.minBaskets = minBaskets;
    }

    public int getMaxBaskets() {
        return maxBaskets;
    }

    public void setMaxBaskets(int maxBaskets) {
        this.maxBaskets = maxBaskets;
    }
}
