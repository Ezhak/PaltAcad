package daoInterface;

import java.util.List;

import entity.Alumno;

public interface IAlumnoDAO {

    boolean delete(Alumno alumno);

    boolean insert(Alumno alumno);

    List<Alumno> selectAll();

    List<Alumno> selectAllFromCurso(int id);

    Alumno selectOne(int legajo);

    boolean update(Alumno alumno);
}
