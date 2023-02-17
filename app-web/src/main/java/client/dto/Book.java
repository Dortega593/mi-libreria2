package client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Serializable {
    private static final Long serialVersionUID = 1L;
    private int id;
    private String isbn;
    private Author author;
    private String title;
    private double price;
}
