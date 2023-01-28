package Classes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.File;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor


public class Product {
    private String name;
    private String narx;
    private String description;
    private String yili;
    private String address;
    private Boolean isActive;
    private String phoneNumber;
    private ProducType producType;
    private Date sana;
    private File rasm;
    private Integer korilganlarSoni;

}
