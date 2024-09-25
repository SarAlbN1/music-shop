package co.musicshop.fis.models;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Instrument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "instrument_id")
    private Long id;

    @NonNull
    private String name;
    
    @NonNull
    private String type;

    @NonNull
    private String brand;

    @NonNull
    private Double price;
    
    @NonNull
    private String photo;
 // no-argument constructor


    public Instrument(String name, String type, String brand, Double price, String photo) {

        this.name = name;

        this.type = type;

        this.brand = brand;

        this.price = price;

        this.photo = photo;

    }
    
}

