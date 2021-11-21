package daoInterface;

import java.util.List;

import entity.Curso;

public interface ICursoDAO {

    boolean insert(Curso curso);

    List<Curso> selectAll();

    List<Curso> selectAll(int legajo);

    Curso selectOne(int id);
}
