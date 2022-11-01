package net.raisetech.SpringBootSample.service;

import net.raisetech.SpringBootSample.entity.Movie;
import net.raisetech.SpringBootSample.entity.Name;
import net.raisetech.SpringBootSample.form.CreateForm;
import net.raisetech.SpringBootSample.form.UpdateForm;
import org.apache.ibatis.javassist.NotFoundException;

import java.lang.reflect.InaccessibleObjectException;
import java.util.List;
import java.util.Optional;

public interface NameService {
    List<Name> findAll();
    Name findById(int id) throws NullPointerException;
    List<Movie> findByYear(int year);
    void createName(CreateForm form);
    void updateName(UpdateForm form) throws NotFoundException;
    void deleteName(int id) throws NotFoundException;
}
