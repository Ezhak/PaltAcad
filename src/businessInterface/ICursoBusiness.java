package businessInterface;

import java.util.List;

import entity.Curso;

public interface ICursoBusiness {

    boolean insert(Curso curso);

    List<Curso> selectAll();

    List<Curso> selectAll(int legajo);

    Curso selectOne(int id);
}
