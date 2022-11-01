package net.raisetech.SpringBootSample.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Data
public class CreateForm {
    @NotBlank
    @Size(max = 20)
    private String name;
}
