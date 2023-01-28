package Classes;

import Enums.GenderTyep;
import Enums.ProductType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class ProducType {
    private ProductType productType;
    private GenderTyep genderTyep;

    public ProducType(ProductType productType) {
        this.productType = productType;
    }


}
