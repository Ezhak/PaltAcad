package businessInterface;

import java.util.List;

import entity.Alumno;

public interface IAlumnoBusiness {

    boolean delete(Alumno alumno);

    boolean insert(Alumno alumno);

    List<Alumno> selectAll();

    List<Alumno> selectAllFromCurso(int id);

    Alumno selectOne(int legajo);

    boolean update(Alumno alumno);
}
