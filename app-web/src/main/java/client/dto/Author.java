package client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Author implements Serializable {
    private static final Long serialVersionUID = 1L;
    private int id;
    private String firstName;
    private String lastName;
    private List<Book> books;
}
