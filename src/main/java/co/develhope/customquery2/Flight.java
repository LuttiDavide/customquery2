package co.develhope.customquery2;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;
    private String fromAirport;
    private String toAirport;
    private Status status;

    public void setStatus(co.develhope.customquery2.Status value) {
    }
}
