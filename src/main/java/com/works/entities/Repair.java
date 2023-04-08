package com.works.entities;

import com.works.utils.EnumStatus;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Data
public class Repair extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rid;

    @NotBlank(message = "Enter the name of the product to be repaired.")
    private String name;

    @Length(min = 5,max = 50,message = "Min 5 characters, max 100 characters.")
    private String detail;

    @Enumerated
    private EnumStatus status;

}
