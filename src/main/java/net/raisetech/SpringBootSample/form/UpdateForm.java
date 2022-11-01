package net.raisetech.SpringBootSample.form;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@Data
public class UpdateForm {
    @NotBlank
    @Pattern(regexp = "^[0-9]{1,3}")
    private int id;
    @NotBlank
    @Size(max = 20)
    private String name;
}
