package daoInterface;

import java.util.List;

import entity.Docente;

public interface IDocenteDAO {

    boolean delete(Docente docente);

    boolean insert(Docente docente);

    List<Docente> selectAll();

    Docente selectOne(int legajo);

    Docente selectOne(int legajo, String contrasenia);

    boolean update(Docente docente);
}
