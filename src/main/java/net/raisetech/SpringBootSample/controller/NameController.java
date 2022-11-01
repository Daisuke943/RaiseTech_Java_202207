package net.raisetech.SpringBootSample.controller;

import net.raisetech.SpringBootSample.entity.Movie;
import net.raisetech.SpringBootSample.entity.Name;
import net.raisetech.SpringBootSample.form.CreateForm;
import net.raisetech.SpringBootSample.form.UpdateForm;
import net.raisetech.SpringBootSample.service.NameService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class NameController {
    private final NameService nameService;
    public NameController(NameService nameService) {
        this.nameService = nameService;
    }
    @GetMapping("/names")
    public List<NameResponse> getNames() {
        return nameService.findAll().stream().map(NameResponse::new).toList();
    }
    @GetMapping("/names/{id}")
    public ResponseEntity getName(@PathVariable("id") int id) {
        NameResponse nameResponse = new NameResponse();
        try {
            nameResponse = new NameResponse(nameService.findById(id));
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "ユーザーが見つかりませんでした"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(nameResponse);
    }
    @GetMapping("/movies")
    public ResponseEntity getMovies(@RequestParam(name = "published_year") int year) {
        List<MovieResponse> movieResponseList = nameService.findByYear(year).stream().map(MovieResponse::new).toList();
        // Listが空の場合はエラーメッセージをレスポンスとして返す
        if (movieResponseList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "映画が見つかりませんでした"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(movieResponseList);
    }
    @PostMapping("/names")
    public ResponseEntity createName(@RequestBody CreateForm form) {
        nameService.createName(form);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "name successfully created"));
    }
    @PatchMapping("/names/{id}")
    public ResponseEntity updateName(@PathVariable("id") int id, @RequestBody UpdateForm form) {
        form.setId(id);
        try {
            nameService.updateName(form);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "name successfully updated"));
    }
    @DeleteMapping("/names/{id}")
    public ResponseEntity deleteName(@PathVariable("id") int id) {
        try {
            nameService.deleteName(id);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "name successfully deleted"));
    }
}
