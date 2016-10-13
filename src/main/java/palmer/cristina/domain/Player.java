package palmer.cristina.domain;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by Cristina on 10/10/2016.
 */

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private LocalDate birthDate;
    private int baskets;
    private int assists;
    private int rebound;
    @Enumerated(EnumType.STRING)
    private Position position;

    public Player() {
    }

    public Player(String name, LocalDate birthDate, int baskets, int assists, int rebound, Position position) {
        this.name = name;
        this.birthDate = birthDate;
        this.baskets = baskets;
        this.assists = assists;
        this.rebound = rebound;
        this.position = position;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public int getBaskets() {
        return baskets;
    }

    public void setBaskets(int baskets) {
        this.baskets = baskets;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getRebound() {
        return rebound;
    }

    public void setRebound(int rebound) {
        this.rebound = rebound;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", baskets=" + baskets +
                ", assists=" + assists +
                ", rebound=" + rebound +
                ", position=" + position +
                '}';
    }
}