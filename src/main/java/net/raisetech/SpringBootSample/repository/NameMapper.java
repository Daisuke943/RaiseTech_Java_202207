package net.raisetech.SpringBootSample.repository;

import net.raisetech.SpringBootSample.entity.Movie;
import net.raisetech.SpringBootSample.entity.Name;
import net.raisetech.SpringBootSample.form.CreateForm;
import net.raisetech.SpringBootSample.form.UpdateForm;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Optional;

@Mapper
public interface NameMapper {
    @Select("SELECT * FROM names")
    List<Name> findAll();
    @Select("SELECT * FROM names where id = #{id}")
    Optional<Name> findById(int id);
    @Select("SELECT * FROM movies WHERE published_year = #{year}")
    List<Movie> findByYear(int year);
    @Insert("INSERT INTO names (name) VALUES (#{name})")
    void createName(CreateForm form);
    @Update("UPDATE names SET name = #{name} WHERE id = #{id}")
    int updateName(UpdateForm form);
    @Delete("DELETE FROM names WHERE id = #{id}")
    int deleteName(int id);
}
