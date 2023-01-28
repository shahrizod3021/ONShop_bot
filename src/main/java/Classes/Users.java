package Classes;

import Enums.Role;
import lombok.*;
import org.telegram.telegrambots.meta.api.objects.File;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Users {
    private Integer id;
    private Long chatId;
    private String name;
    private String surname;
    private String phoneNumber;
    private List<Savatcha> savatchas = new ArrayList<>();
    private List<Product> products = new ArrayList<>();
    private Role role;

    public Users(Long chatId) {
        this.chatId = chatId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users)) return false;
        Users users = (Users) o;
        return getChatId().equals(users.getChatId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getChatId());
    }
}



