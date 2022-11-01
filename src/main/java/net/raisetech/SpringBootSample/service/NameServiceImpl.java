package net.raisetech.SpringBootSample.service;

import net.raisetech.SpringBootSample.entity.Movie;
import net.raisetech.SpringBootSample.entity.Name;
import net.raisetech.SpringBootSample.form.CreateForm;
import net.raisetech.SpringBootSample.form.UpdateForm;
import net.raisetech.SpringBootSample.repository.NameMapper;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.lang.reflect.InaccessibleObjectException;
import java.util.List;
import java.util.Optional;

@Service
public class NameServiceImpl implements NameService {
    private NameMapper nameMapper;

    public NameServiceImpl(NameMapper nameMapper) {
        this.nameMapper = nameMapper;
    }
    @Override
    public List<Name> findAll() {
        return nameMapper.findAll();
    }
    @Override
    public Name findById(int id) throws NullPointerException {
        Optional<Name> name = nameMapper.findById(id);
        if (name.isPresent()) {
            return name.get();
        } else {
            throw new NullPointerException();
        }
    }
    @Override
    public List<Movie> findByYear(int year) {
        return this.nameMapper.findByYear(year);
    }
    @Override
    public void createName(CreateForm form) {
        nameMapper.createName(form);
    }
    @Override
    public void updateName(UpdateForm form) throws NotFoundException {
        if (nameMapper.updateName(form) == 0) {
            throw new NotFoundException("Name ID Not Found");
        }
    }
    @Override
    public void deleteName(int id) throws NotFoundException {
        if (nameMapper.deleteName(id) == 0) {
            throw new NotFoundException("Name ID Not Found");
        }
    }
}
