package Classes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Savatcha {
    private Long chatId;
    private String productName;
    private String productPhone;
    private String productPrice;
    private String productAddress;
}
