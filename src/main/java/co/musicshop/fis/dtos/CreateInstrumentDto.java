package co.musicshop.fis.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInstrumentDto {

    private Long id;
    private String name;
    private String type;
    private String brand;
    private Double price;
    private String photo;


}

